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
	private int cantidadPaquetesEntregados = 0;
	private double precio = 0;

	public Pedido(int numPedido, int dniCliente, String nombreCliente, String direccion) {
		
		if (dniCliente <= 0)
			throw new RuntimeException("Id de cliente inválido.");
		if (nombreCliente == null)
			throw new RuntimeException("El nombre del cliente no puede ser null.");
		if (dniCliente <= 0)
			throw new RuntimeException("La dirección no puede ser null.");
		
		this.carritoPaquetesComprados = new HashMap<Integer, Paquete>();
		this.numPedido = numPedido;
		this.dniCliente = dniCliente;
		this.nombreCliente = nombreCliente;
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Pedido id: ");
		sb.append(numPedido);
		sb.append("\nCliente: ");
		sb.append(nombreCliente.toString());
		sb.append(" - ");
		sb.append(dniCliente);
		sb.append("\nDirección: ");
		sb.append(direccion.toString());
		sb.append("Paquetes:\n");

		for (Paquete paquete : this.carritoPaquetesComprados.values()) {
			sb.append(paquete.toString());
			sb.append('\n');
		}

		return sb.toString();
	}

	public double calcularPrecio() {
		return this.precio;
	}

	public boolean estaCerrado() {
		return cerrado;
	}

	public boolean fueEntregado() {
		return entregado;
	}

	public void agregarPaquete(int identificadorPaquete, int volumen, int precio, int costoEnvio) {
		Paquete paquete = new PaqueteOrdinario(identificadorPaquete, volumen, precio, costoEnvio);
		agregarPaquete(paquete);
	}

	public void agregarPaquete(int identificadorPaquete, int volumen, int precio, int porcentaje, int adicional) {
		Paquete paquete = new PaqueteEspecial(identificadorPaquete, volumen, precio, porcentaje, adicional);
		agregarPaquete(paquete);
	}

	private void agregarPaquete(Paquete p) {

		if (this.estaCerrado())
			throw new RuntimeException("Pedido ya cerrado.");

		precio += p.calcularPrecio();
		carritoPaquetesComprados.put(p.obtenerIdentificador(), p);
	}

	boolean quitarPaquete(int identificadorPaquete) {
		if (this.fueEntregado() || this.estaCerrado())
			return false;
		Paquete p = carritoPaquetesComprados.remove(identificadorPaquete);
		if (p != null)
			precio -= p.calcularPrecio();
		return p != null;
	}

	public boolean tienesEstePaquete(int identificadorPaquete) {
		return carritoPaquetesComprados.containsKey(identificadorPaquete);
	}

	public double cerrar() {
		cerrado = true;
		for (Paquete paquete : this.carritoPaquetesComprados.values()) {
			paquete.cerrar();
		}
		return calcularPrecio();
	}

	public String obtenerNombreCliente() {
		return this.nombreCliente;
	}

	public int cantidadPaquetes() {
		return carritoPaquetesComprados.size();
	}
	
	public String cargarEnTransporte(Transporte t) {
		
		StringBuilder sb = new StringBuilder();
		for(Paquete p: this.carritoPaquetesComprados.values()) {
			if (t.puedeLlevarEstePaquete(p)) {
				p.entregar();
				this.cantidadPaquetesEntregados++;
				t.cargarPaquete(p);
				
				sb.append(" + [ ");
				sb.append(this.numPedido);
				sb.append(" - ");
				sb.append(p.obtenerIdentificador());
				sb.append(" ] ");
				sb.append(this.direccion);
				sb.append('\n');
				
			}
		}
		/* Cuando se carguen los paquetes de un pedido,
		se dirá que fue entregado si la cantidad de paquetes entregados 
		coincide con la cantidad de paquetes.
		No se pueden agregar paquetes en pedidos cerrados, por lo que agregar paquetes
		no afecta a esto.*/
		this.entregado = this.cantidadPaquetesEntregados == this.cantidadPaquetes();
		return sb.toString();
	}

}