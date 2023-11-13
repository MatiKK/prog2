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
		sb.append("\nEstado: ");
		String estado = (fueEntregado()? "Cerrado y entregado" :
			estaCerrado()? "Cerrado y no entregado" : "No cerrado");
		sb.append(estado);
		sb.append("\nCliente: ");
		sb.append(nombreCliente.toString());
		sb.append(" - ");
		sb.append(dniCliente);
		sb.append("\nDirección: ");
		sb.append(direccion.toString());
		sb.append("\nPaquetes:\n");

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

		carritoPaquetesComprados.put(p.obtenerIdentificador(), p);
		precio += p.calcularPrecio();
	}

	boolean quitarPaquete(int identificadorPaquete) {
		if (this.fueEntregado() || this.estaCerrado())
			return false;
		if (!this.tienesEstePaquete(identificadorPaquete))
			return false;
		
		Paquete p = carritoPaquetesComprados.remove(identificadorPaquete);
		precio -= p.calcularPrecio();
		return true;
	}

	public boolean tienesEstePaquete(int identificadorPaquete) {
		return carritoPaquetesComprados.containsKey(identificadorPaquete);
	}

	public double cerrar() {
		if (this.estaCerrado())
			throw new RuntimeException("Pedido ya cerrado.");
		this.cerrado = true;
		return calcularPrecio();
	}

	public String obtenerNombreCliente() {
		return this.nombreCliente;
	}

	private int cantidadPaquetes() {
		return carritoPaquetesComprados.size();
	}
	
	public String cargarEnTransporte(Transporte t) {
		
		if (!this.estaCerrado())
			return "";
		
		StringBuilder sb = new StringBuilder();
		
		int cantidadPaquetesEntregados = 0;
		
		for(Paquete paquete: this.carritoPaquetesComprados.values()) {
		
			if (t.puedeLlevarEstePaquete(paquete)) {
				t.cargarPaquete(paquete);
				paquete.entregar();
				sb.append(" + [ ");
				sb.append(this.numPedido);
				sb.append(" - ");
				sb.append(paquete.obtenerIdentificador());
				sb.append(" ] ");
				sb.append(this.direccion);
				sb.append('\n');
			}
			/* Pudo haber sido entregado pero por otro transporte,
			 * por eso no sumar en el condicional anterior */
			if (paquete.fueEntregado())
				cantidadPaquetesEntregados++;
		}
		
		/* Si la cantidad de paquetes que fueron entregados
		 * coincide con la cantidad total de paquetes del pedido,
		 * Se puede decir que el pedido está entregado */
		this.entregado = cantidadPaquetesEntregados == this.cantidadPaquetes();
		return sb.toString();
	}

	public int obtenerIdentificador() {
		return this.numPedido;
	}

}