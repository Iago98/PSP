package Jserver;

import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ListaDeClientes {

	private static List<ClienteConectado> conectados;

	public static void initList() {
		conectados = new ArrayList<ClienteConectado>();
	};

	public static void addCliente(ClienteConectado c) {
		conectados.add(c);
	};

	public static int getNumConectados() {

		return conectados.size();
	};

	public static void deleteClienteById(Long id) {
		for (int i = 0; i < conectados.size(); i++) {
			if (conectados.get(i).getId() == id) {
				conectados.remove(i);
			}
		}
	};

	public static boolean enviarMensaje(Long id, String mensj) {
		try {
			
			String aux=">"+conectados.get(getPosicionById(id)).getNick()+" :"+mensj;
			conectados.get(getPosicionById(id)).enviarRespuesta(aux);
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	};

	public static String getNombres() {
		String str = "";
		for (int i = 0; i < conectados.size(); i++) {
			str = str + conectados.get(i).getNick() + "\n";
		}
		return str;
	};

	public static int getPosicionById(Long id) {
		int pos=-1;
		for (int i = 0; i < conectados.size(); i++) {
			if (Long.compare(id, conectados.get(i).getId())==0) {
				pos = i;
			}
		}
		return pos;
	};

	public static long getIdByNick(String nick) {
		long id = -1;
		for (int i = 0; i < conectados.size(); i++) {
			if (conectados.get(i).getNick().equalsIgnoreCase(nick)) {
				id = conectados.get(i).getId();
			}
		}
		return id;
	};
}