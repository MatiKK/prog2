package Amazing;

public class Camion extends Transporte {

	private final int valorExtraPorPaquete;

	public Camion(String identificador, int volumenMaximoDeCarga, int valorPorViaje, int valorExtraPorPaquete) {
		super(identificador, volumenMaximoDeCarga, valorPorViaje);
		this.valorExtraPorPaquete = valorExtraPorPaquete;
	}

	@Override
	public String toString() {
		return "Camión " + identificador.toString();
	}

	@Override
	public boolean equals(Object other) {

		if (!(other instanceof Camion))
			return false;

		return super.equals(other);
	}

	@Override
	double calcularPrecioViaje() {
		return this.valorPorViaje + this.valorExtraPorPaquete * cantidadPaquetes();
	}

	@Override
	protected boolean puedeLlevarEstePaquete(Paquete p) {
		if (!(p instanceof PaqueteEspecial))
			return false;

		return p.calcularVolumen() > 2000 &&
				super.puedeLlevarEstePaquete(p);
	}

}
