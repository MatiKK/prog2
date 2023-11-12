package Amazing;

public class Utilitario extends Transporte {

	private final int valorExtra;

	public Utilitario(String identificador, int volumenMaximoDeCarga, int valorPorViaje, int valorExtra) {
		super(identificador, volumenMaximoDeCarga, valorPorViaje);

		if (valorExtra <= 0)
			throw new RuntimeException("El valor extra no puede ser nulo o negativo.");
		this.valorExtra = valorExtra;
	}

	@Override
	public String toString() {
		return "Utilitario " + identificador.toString();
	}

	@Override
	double calcularPrecioViaje() {
		return this.valorPorViaje * ((cantidadPaquetes() > 3) ? valorExtra : 1);
	}

}