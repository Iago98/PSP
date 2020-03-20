package Jclient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class ServerSender implements Runnable{
	private DataOutputStream out;
	private  boolean active;
	private BufferedReader keyboard;

	public ServerSender(DataOutputStream out) {
		this.out=out;
		active=true;
	}

	
	public void stop() {
		active=false;
	}



	@Override
	public void run() {

		do {
			String mensaje="";
			Scanner scanner = new Scanner(System.in);
			try {
				mensaje= scanner.nextLine();
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			if(mensaje.equals("#salir")) {
				try {
					out.writeUTF(mensaje);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Cliente.shutDown();
				
			}else {
				try {
					out.writeUTF(mensaje);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}while(active);
		
	}

}