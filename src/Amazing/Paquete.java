package Amazing;

public abstract class Paquete {

	protected int identificador;
	protected final int volumen;
	protected final int precio;
	protected boolean cerrado = false;
	protected boolean entregado = false;

	public Paquete(int identificador, int volumen, int precio) {
		this.identificador = identificador;
		this.volumen = volumen;
		this.precio = precio;
	}

	public boolean equals(Object other) {

		if (!(other instanceof Paquete))
			return false;

		Paquete p = (Paquete) other;

		return this.calcularVolumen() == p.calcularVolumen() &&
				this.calcularPrecio() == p.calcularPrecio();
	}

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
