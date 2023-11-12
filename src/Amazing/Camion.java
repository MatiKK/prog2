package Amazing;

public class Camion extends Transporte {

	private final int valorExtraPorPaquete;

	public Camion(String identificador, int volumenMaximoDeCarga, int valorPorViaje, int valorExtraPorPaquete) {
		super(identificador, volumenMaximoDeCarga, valorPorViaje);

		if (valorExtraPorPaquete<= 0)
			throw new RuntimeException("El valor extra por paquete no puede ser nulo o negativo.");
		
		this.valorExtraPorPaquete = valorExtraPorPaquete;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Camión ");
		sb.append(super.toString());
		return sb.toString();
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
