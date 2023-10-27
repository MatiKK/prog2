package Amazing;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Pedido {

	private int numPedido;
	
	private int dniCliente;
	private String nombreCliente;
	private String direccion;

	private HashMap<Integer, Paquete> carritoPaquetesComprados;
	private boolean entregado = false;
	private boolean cerrado = false;
	
	public Pedido(int numPedido, int dniCliente, String nombreCliente, String direccion) {
		this.numPedido = numPedido;
		this.dniCliente= dniCliente;
		this.nombreCliente = nombreCliente;
		this.direccion = direccion;
	}
	
	public double calcularPrecio() {
		
		double precio = 0;
		
		for (Map.Entry<Integer,Paquete> paqueteHash: carritoPaquetesComprados.entrySet()) {
			
			Paquete paquete = paqueteHash.getValue();
			
			double precioPaquete = paquete.calcularPrecio();
			
			precio += precioPaquete;
		}
		
		return precio;
		}
	
	public void entregar() {if (cerrado) entregado = true;}

	public boolean entregado() {return entregado;}
	
	public void agregarPaquete(Paquete p) {
		
		carritoPaquetesComprados.put(p.obtenerIdentificador(), p);
		
	}
	
	boolean quitarPaquete(int identificadorPaquete) {
		Paquete p = carritoPaquetesComprados.remove(identificadorPaquete);
		return p != null;
	
	}

	public boolean tienesEstePaquete(int codPaquete) {
		
		return carritoPaquetesComprados.containsKey(codPaquete);
		
	}

	public double cerrar() {
		cerrado = true;
		return calcularPrecio();
	}
	
	
	public boolean cerrado() {return cerrado;}

	public Object obtenerDireccion() {
		return direccion;
	}
	
}
