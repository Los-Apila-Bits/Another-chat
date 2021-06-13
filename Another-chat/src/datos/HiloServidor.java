package datos;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class HiloServidor extends Thread {
	private List<Socket> sockets;
	private Socket socket;
	
	public HiloServidor(Socket socket, List<Socket> sockets) {
		this.socket = socket;
		this.sockets = sockets;
	}
	
	public void run() {
		DataInputStream entrada;
		DataOutputStream salida;
		String mensaje;
		
		try {
			entrada = new DataInputStream(this.socket.getInputStream());
			while(true) {
				mensaje = entrada.readUTF();
				System.out.println(mensaje);
				for (Socket envio : sockets) {
					salida = new DataOutputStream(envio.getOutputStream());
					salida.writeUTF(mensaje);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
