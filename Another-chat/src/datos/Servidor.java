package datos;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
	private int puerto;
	private ServerSocket server;
	private List<Socket> sockets;
	
	public Servidor(int puerto) {
		this.puerto = puerto;
		try {
			server = new ServerSocket(this.puerto);
			sockets = new ArrayList<Socket>();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void ejecutar() {
		Socket socket;
		try {
			while(true) {
				socket = server.accept();
				sockets.add(socket);
				new HiloServidor(socket, sockets).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int getPuerto() {
		return this.puerto;
	}
}
