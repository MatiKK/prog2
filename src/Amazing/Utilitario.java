package Amazing;

public class Utilitario extends Transporte {

	private final int valorExtra;

	public Utilitario(String identificador, int volumenMaximoDeCarga, int valorPorViaje, int valorExtra) {
		super(identificador, volumenMaximoDeCarga, valorPorViaje);
		this.valorExtra = valorExtra;
	}

	@Override
	public String toString() {
		return "Utilitario " + identificador.toString();
	}

	@Override
	public boolean equals(Object other) {
		
		if (!(other instanceof Automovil))
			return false;

		return super.equals(other);
	}

	@Override
	double calcularPrecioViaje() {
		return this.valorPorViaje * ((cantidadPaquetes() > 3) ? valorExtra : 1);
	}

}