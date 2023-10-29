package Amazing;

import java.util.HashMap;

public class Pedido {

	private int numPedido;
	private int dniCliente;
	private String nombreCliente;
	private String direccion;
	private boolean cerrado = false;
	private boolean entregado = false;
	private HashMap<Integer, Paquete> carritoPaquetesComprados;
	private double precio = 0;

	public Pedido(int numPedido, int dniCliente, String nombreCliente, String direccion) {
		this.carritoPaquetesComprados = new HashMap<Integer, Paquete>();
		this.numPedido = numPedido;
		this.dniCliente = dniCliente;
		this.nombreCliente = nombreCliente;
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Pedido n°" + numPedido + "\n");
		sb.append("Paquetes:\n");

		for (Paquete paquete : carrito().values()) {
			sb.append(paquete.toString());
		}

		return sb.toString();
	}

	public double calcularPrecio() {
		return this.precio;
	}

	public void entregar() {

		if (fueEntregado())
			throw new RuntimeException("Pedido ya entregado");

		if (this.estaCerrado()) {
			boolean todosLosPaquetesSeEntregaron = true;
			for (Paquete paquete : this.carrito().values()) {
				todosLosPaquetesSeEntregaron &= paquete.fueEntregado();
			}
			if (todosLosPaquetesSeEntregaron) {
				entregado = true;
			}
		}
	}

	public boolean estaCerrado() {
		return cerrado;
	}

	public boolean fueEntregado() {
		return entregado;
	}

	public void agregarPaquete(Paquete p) {

		if (this.estaCerrado())
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
		for (Paquete paquete : this.carrito().values()) {
			paquete.cerrar();
		}
		return calcularPrecio();
	}

	public String obtenerDireccion() {
		return this.direccion;
	}

	public String obtenerNombreCliente() {
		return this.nombreCliente;
	}

	public int cantidadPaquetes() {
		return carritoPaquetesComprados.size();
	}

	public HashMap<Integer, Paquete> carrito() {
		return this.carritoPaquetesComprados;
	}

}