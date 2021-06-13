package datos;

import java.io.DataInputStream;
import java.io.IOException;

import Ventanas.JChatCliente;

public class HiloCliente extends Thread{
	private DataInputStream entrada;
	private JChatCliente ventana;
	public HiloCliente(DataInputStream entrada, JChatCliente ventana) {
		this.entrada = entrada;
		this.ventana = ventana;
	}
	
	public void run() {
		String mensaje;
		while(true) {
			try {
				mensaje = entrada.readUTF();
				ventana.escribirMensajeEnTextArea(mensaje);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
