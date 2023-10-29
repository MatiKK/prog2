package Amazing;

import java.util.HashMap;
import java.util.Map;

public class Pedido {

	private int numPedido;
	
	private int dniCliente;
	private String nombreCliente;
	private String direccion;
	private boolean entregado = false;
	private boolean cerrado = false;
	
	private double precio = 0;
	
	private HashMap<Integer, Paquete> carritoPaquetesComprados;

	
	
	public Pedido(int numPedido, int dniCliente, String nombreCliente, String direccion) {
		
		this.carritoPaquetesComprados = new HashMap<Integer,Paquete>();
		
		this.numPedido = numPedido;
		this.dniCliente= dniCliente;
		this.nombreCliente = nombreCliente;
		this.direccion = direccion;
	}
	
	public double calcularPrecio() {
		return this.precio;
	}
	
	public void entregar() {
		
		if (entregado())
			throw new RuntimeException("Pedido ya entregado");
		
		if (cerrado) {
			boolean aux = true;
			for (Map.Entry<Integer, Paquete> paquete : carrito().entrySet()) {
				aux &=paquete.getValue().fueEntregado();
			}
			if (aux){
				entregado = true;
			}
		}
		
	}

	public boolean entregado() {
		return entregado;
	}
	
	public void agregarPaquete(Paquete p) {
		
		if (this.cerrado())
			throw new RuntimeException("Pedido ya cerrado.");
		
		precio += p.calcularPrecio();
		carritoPaquetesComprados.put(p.obtenerIdentificador(), p);
		
	}
	
	boolean quitarPaquete(int identificadorPaquete) {
		
		Paquete p = carritoPaquetesComprados.remove(identificadorPaquete);
		
		if (p != null)
			precio -= p.calcularPrecio();
		
		return p != null;
	
	}

	public boolean tienesEstePaquete(int codPaquete) {
		
		return carritoPaquetesComprados.containsKey(codPaquete);
		
	}

	public double cerrar() {
		cerrado = true;
		for (Map.Entry<Integer, Paquete> paquete : carrito().entrySet()) {
			Paquete p = paquete.getValue();
			p.cerrar();
		}
		
		return calcularPrecio();
	}
	
	
	public boolean cerrado() {return cerrado;}

	public String obtenerDireccion() {
		return direccion;
	}
	public HashMap <Integer, Paquete> carrito(){
		return carritoPaquetesComprados;
	}

	public String cliente() {
		return nombreCliente;
	}
	
	public int cantPaquetesCarrito() {
		return carritoPaquetesComprados.size();
	}
}
