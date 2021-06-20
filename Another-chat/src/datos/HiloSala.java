package datos;

public class HiloSala extends Thread{
	Servidor server;
	
	public HiloSala(Servidor server) {
		this.server = server;
		System.out.println("Nueva sala");
	}
	
	public void run() {
		server.ejecutar();
	}
}
