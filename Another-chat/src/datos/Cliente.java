package datos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import Ventanas.JChatCliente;

public class Cliente {
	private Socket socket;
	private int puerto;
	private String ip;
	private DataOutputStream salida;
	private DataInputStream entrada;
	
	public Cliente(int puerto, String ip) {
		this.puerto = puerto;
		this.ip = ip;
		
		try {
			socket = new Socket(ip, puerto);
			salida = new DataOutputStream(salida);
			entrada = new DataInputStream(entrada);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void enviarMensaje(String mensaje) {
		try {
			salida.writeUTF(mensaje);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void inicializarHiloCliente(JChatCliente ventana) {
		new HiloCliente(this.entrada, ventana).start();
	}
}
