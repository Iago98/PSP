package Jclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
	private final int numPuerto=9;
	private static String dirDestino;
	private static ServerReceiver sr;
	private static  ServerSender ss;
	private  Socket socket;
	private DataInputStream in;
	private DataOutputStream out;

	public static void main(String[] args) throws UnknownHostException, IOException {
		if(args.length!=2) {
			System.out.println("error comando no valido ===> java ClienteChat <dirección_servidor> <nickname> Donde <nickname> es el nombre del usuario conectado al chat");
		}else {
			dirDestino=args[0];
			Cliente cliente = new Cliente(args[1]);
		}
	}

	public Cliente(String nick) throws UnknownHostException, IOException {
		
		this.socket= new Socket(dirDestino,numPuerto);
		out = new DataOutputStream(socket.getOutputStream());
		in = new DataInputStream(socket.getInputStream());
		System.out.println("nombre=" +nick);
		out.writeUTF(nick);
		ss= new ServerSender(out);
		sr= new ServerReceiver(in);
		Thread thrd = new Thread(sr);
        thrd.start();
		run();
		
	}

	private void run() {
		
		
		Thread thrd = new Thread(ss);
        thrd.start();
	}

	public static void shutDown() {
		System.out.println("Cerrando cliente");
		ss.stop();
		sr.stop();
	}

}