package Amazing;

import java.util.HashMap;

public class Pedido {

	private int numPedido;
	
	private int dniCliente;
	private String nombreCliente;
	private String direccion;

	private HashMap<Integer, Paquete> carritoPaquetesComprados;
	private String estado;
	
	public Pedido(int numPedido, int dniCliente, String nombreCliente, String direccion) {
		this.numPedido = numPedido;
		this.dniCliente= dniCliente;
		this.nombreCliente = nombreCliente;
		this.direccion = direccion;
	}
	
	public double calcularPrecio() {return 0;}
	
	public void modificarEstado() {}

	public String consultarEstado() {return "";}
	
	public void agregarPaqueteOrdinario(int identificador, double volumen, double precio, double costoDeEnvio) {}
	
	public void agregarPaqueteEspecial(int identificador, double volumen, double precio, double porcentajeAdicional) {}
	
}
