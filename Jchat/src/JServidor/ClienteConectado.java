package JServidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClienteConectado implements Runnable {
	private String nick;
	private Long id;
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private Long chat;

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	private void enviarRespuesta(String respuesta) {
	}

	private boolean procesarComando(String cmd) {
		return false;
	}

	private String getNick() {
		return nick;
	}

	private void setNick(String nick) {
	}

	private Long getIdent() {
		return chat;
	}

	private void setIdent(Long id) {
	}

}
