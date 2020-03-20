package Jclient;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServerReceiver implements Runnable{
	private DataInputStream in;
	private boolean active;

	public ServerReceiver(DataInputStream in) {
		this.in=in;
		active=true;
	}

	

	public  void stop() {
		active=false;
		}



	@Override
	public void run() {

		do {
			String mensaje;
			try {
				mensaje = in.readUTF();
				System.out.println(mensaje);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			}while(active);
		
	}
}