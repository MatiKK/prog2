package Amazing;

public abstract class Paquete {

	protected int identificador;
	protected final int volumen;
	protected final int precio;
	protected boolean perteneceAPedidoCerrado = false;
	protected boolean entregado = false;

	public Paquete(int identificador, int volumen, int precio) {
		
		if (volumen <= 0)
			throw new RuntimeException("El volumen no puede ser nulo o negativo.");

		if (precio <= 0)
			throw new RuntimeException("El precio no puede ser nulo o negativo.");
		
		this.identificador = identificador;
		this.volumen = volumen;
		this.precio = precio;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Paquete id: ");
		sb.append(this.obtenerIdentificador());
		sb.append(" - estado: ");
		String estado = fueEntregado()?"entregado":"no entregado";
		sb.append(estado);
		sb.append(" - Volumen: "+this.calcularVolumen());
		sb.append(" - Precio: "+this.calcularPrecio());
		
		return sb.toString();
	}


	@Override
	public boolean equals(Object other) {

		if (!(other instanceof Paquete))
			return false;

		Paquete p = (Paquete) other;

		return this.calcularVolumen() == p.calcularVolumen() &&
				this.calcularPrecio() == p.calcularPrecio();
	}

	@Override
	public int hashCode() {
		return this.volumen * 10 + this.precio * 2 + 5003;
	}

	public abstract double calcularPrecio();

	public int obtenerIdentificador() {
		return this.identificador;
	}

	public int calcularVolumen() {
		return this.volumen;
	}

	public void entregar() {
		if (this.entregado)
			throw new RuntimeException("Paquete ya entregado");
		this.entregado = true;
	}

	public void cerrar() {
		if (this.perteneceAPedidoCerrado)
			throw new RuntimeException("Paquete ya pertenece a un pedido cerrado");
		this.perteneceAPedidoCerrado  = true;
	}

	public boolean perteneceAPedidoCerrado() {
		return this.perteneceAPedidoCerrado;
	}

	public boolean fueEntregado() {
		return this.entregado;
	}

}
