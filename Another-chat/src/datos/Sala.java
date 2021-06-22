package datos;

import java.util.LinkedList;
import java.util.List;

public class Sala {
	
	private static int puertoAI = 9000;
	private List<Servidor> servers = new LinkedList<Servidor>();
	public Sala() {
		
	}
	public void crearSala(String nombre) {
		Servidor server = new Servidor(puertoAI++, nombre);
		servers.add(server);
		new HiloSala(server).start();
	}
	
	public void unirseSala( ) {
		
	}
	
	public int getSala(int numSala) {
		return servers.get(numSala).getPuerto();
	}
	
	public int getCantidadPersonas(int posServer) {
		return servers.get(posServer).getCantConexiones();
	}
	
	public String getNombreSala(int posServer) {
		return servers.get(posServer).getNombre();
	}
}
