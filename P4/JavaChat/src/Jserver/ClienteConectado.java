package Jserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.nio.channels.SocketChannel;
import java.util.Date;

import javax.swing.JOptionPane;

public class ClienteConectado implements Runnable {
	private String nick;
	private Long id;
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private Long chat;

	public ClienteConectado(Socket socket) {
		this.socket = socket;
		this.id = new Date().getTime();
	}

	boolean salir;

	@Override
	public void run() {

		try {

			System.out.println("conexion establecida con:" + socket.getRemoteSocketAddress());
			out= new DataOutputStream(socket.getOutputStream());
			in= new DataInputStream(socket.getInputStream());
			nick=in.readUTF();
			System.out.println("nick"+nick);
			ListaDeClientes.addCliente(this);

			do {
				
				
				
				
				String mensj = null;
				

				try {
					try {
					mensj=in.readUTF();
					}catch (SocketException e) {
						try {
							socket.close();
						}catch (Exception a) {
							// TODO: handle exception
						}
						System.out.println(
								"El usuario " + nick + " con la ip: " + socket.getRemoteSocketAddress() + "se ha deconectado");
						ListaDeClientes.deleteClienteById(id);
						break;
					}
					
						
						if (mensj.charAt(0)=='>') {
							enviarRespuesta(mensj);
						} else {
							if (chat == null) {
								procesarComando(mensj);
							} else {
								if (mensj.charAt(0)=='#') {
									procesarComando(mensj);
								} else {
									if (!ListaDeClientes.enviarMensaje(chat, mensj)) {
										enviarRespuesta("No se ha podido enviar el mensaje");
									}

								}
							}
						}

					
				} catch (Exception e) {
					e.printStackTrace();
				}

			} while (ListaDeClientes.getPosicionById(id) != -1);

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.err.print("Cerrando conexión con cliente [" + socket.getLocalAddress() + "]");

	}

	public void enviarRespuesta(String respuesta) throws IOException {
		out.writeUTF(respuesta);
	}

	private void procesarComando(String cmd) throws IOException {
		String[] st = cmd.split(" ");

		switch (st[0]) {
		case "#ayuda":
			if (st.length != 1) {
				enviarRespuesta("El comando " + cmd
						+ " no existe, para más información consulte los comandos escribiendo #ayuda");
			} else {
				enviarRespuesta(
						"#listar: lista todos los usuarios conectados.\n#charlar <usuario>: comienza la comunicación con el usuario <usuario>\n#salir: se desconecta del chat");
			}
			break;
		case "#listar":
			enviarRespuesta("En este momento están conectados " + ListaDeClientes.getNumConectados() + " usuarios:\n"
					+ ListaDeClientes.getNombres());
			break;
		case "#charlar":
			if(st.length==1) {
				enviarRespuesta("El comando " + cmd
						+ " necesita ademas el nick de la persona con la que deseas hablar, para mas informacion escribe #ayuda");
			}else if (ListaDeClientes.getIdByNick(st[1]) != -1) {
				enviarRespuesta("Ahora está conectado con " + st[1] + ". Escribe para hablarle");
				chat = ListaDeClientes.getIdByNick(st[1]);
			} else {
				enviarRespuesta("El usuario " + st[1] + " no existe o no está conectado");
			}
			break;
		case "#salir":
			socket.close();
			salir = true;
			System.out.println(
					"El usuario " + nick + " con la ip: " + socket.getRemoteSocketAddress() + "se ha deconectado");
			ListaDeClientes.deleteClienteById(id);
			break;
		default:
			enviarRespuesta("El comando " + cmd + " no existe , para más información consulte los comandos escribiendo #ayuda");
			break;
		}
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}