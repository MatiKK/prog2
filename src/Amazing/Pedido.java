package Amazing;

import java.util.HashMap;

public class Pedido {

	private int numPedido;
	
	private int dniCliente;
	private String nombreCliente;
	private String direccion;

	private HashMap<Integer, Paquete> carritoPaquetesComprados;
	private boolean estado = false;
	
	public Pedido(int numPedido, int dniCliente, String nombreCliente, String direccion) {
		this.numPedido = numPedido;
		this.dniCliente= dniCliente;
		this.nombreCliente = nombreCliente;
		this.direccion = direccion;
	}
	
	public double calcularPrecio() {return 0;}
	
	public void entregar() {estado = true;}

	public boolean entregado() {return estado;}
	
	public void agregarPaquete(int identificador, double volumen, double precio, double costoDeEnvio) {
		
		int codPaquete = crearCodPaquete(identificador);
		
		Paquete paquete = new PaqueteOrdinario(codPaquete, volumen, precio, costoDeEnvio);
		carritoPaquetesComprados.put(codPaquete, paquete);
	}
	
	public void agregarPaquete(int identificador, double volumen, double precio, double porcentajeAdicional, double valorAdicional) {
		
		int codPaquete = crearCodPaquete(identificador);
		
		Paquete paquete = new PaqueteEspecial(codPaquete, volumen, precio, porcentajeAdicional, valorAdicional);  
		carritoPaquetesComprados.put(codPaquete, paquete);
	}
	
	private int crearCodPaquete (int cod) {
		
		
		// boludin creo que con return carritoPaquetesComprados.size() + 1 es lo mismo y O(1)
		while (carritoPaquetesComprados.containsKey(cod))
			cod+=1;
			
		return cod;
	}
	
}
