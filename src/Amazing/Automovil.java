package Amazing;

public class Automovil extends Transporte{

	private final int limiteMaximoDePaquetes;

	public Automovil(String identificador, int volumenMaximoDeCarga, int valorPorViaje, int limiteMaximoDePaquetes) {
		super(identificador, volumenMaximoDeCarga, valorPorViaje);

		if (limiteMaximoDePaquetes <= 0)
			throw new RuntimeException("El límite de paquetes no puede ser nulo o negativo.");
		this.limiteMaximoDePaquetes = limiteMaximoDePaquetes;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Automovil ");
		sb.append(super.toString());
		return sb.toString();
	}

	@Override
	protected boolean puedeLlevarEstePaquete(Paquete p) {

		if (!(p instanceof PaqueteOrdinario))
			return false;

		return p.calcularVolumen() < 2000
				&& cantidadPaquetes() < this.limiteMaximoDePaquetes
				&& super.puedeLlevarEstePaquete(p);
	}

	@Override
	double calcularPrecioViaje() {
		return this.valorPorViaje;
	}

}