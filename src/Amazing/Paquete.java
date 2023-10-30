package Amazing;

public abstract class Paquete {

	protected int identificador;
	protected final int volumen;
	protected final int precio;
	protected boolean cerrado = false;
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
		StringBuilder sb = new StringBuilder("Paquete n° ");
		sb.append(this.obtenerIdentificador());
		sb.append(". Volumen: "+this.calcularVolumen());
		sb.append("- Precio: "+this.calcularPrecio());
		sb.append('\n');
		
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

	public boolean fueEntregado() {
		return entregado;
	}

	public void entregar() {
		entregado = true;
	}

	public void cerrar() {
		cerrado = true;
	}

	public boolean estaCerrado() {
		return cerrado;
	}

}
