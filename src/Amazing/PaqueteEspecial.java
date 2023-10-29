package Amazing;

public class PaqueteEspecial extends Paquete {

	private final int porcentajeAdicional;
	private final int valorAdicional;

	public PaqueteEspecial(int identificador, int volumen, int precio, int porcentajeAdicional, int valorAdicional) {
		super(identificador, volumen, precio);
		this.porcentajeAdicional = porcentajeAdicional;
		this.valorAdicional = valorAdicional;
	}

	@Override
	public boolean equals(Object other) {

		if (!(other instanceof PaqueteEspecial))
			return false;

		PaqueteEspecial p = (PaqueteEspecial) other;

		return super.equals(p) && this.porcentajeAdicional == p.porcentajeAdicional
				&& this.valorAdicional == p.valorAdicional;
	}

	@Override
	public int hashCode() {
		return super.hashCode() + 31 * valorAdicional - 3 * porcentajeAdicional;
	}

	@Override
	public double calcularPrecio() {

		double precio = this.precio;

		precio = precio * (1 + (double) this.porcentajeAdicional / 100);

		if (this.calcularVolumen() >= 3000)
			precio += valorAdicional;

		if (this.calcularVolumen() > 5000)
			precio += valorAdicional;

		return precio;
	}

}
