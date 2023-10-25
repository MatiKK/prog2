package Amazing;

public class Utilitario extends Transporte{

	private double valorExtraPorViaje;
	
	public Utilitario(String identificador, double volumenMaximoDeCarga, double valorPorViaje, double valorExtraPorViaje) {
		super(identificador, volumenMaximoDeCarga, valorPorViaje);
		this.valorExtraPorViaje = valorExtraPorViaje;
	}

	@Override
	double calcularPrecioViaje() {
		return valorPorViaje * ((cantidadPaquetes() > 3)? valorExtraPorViaje : 1);
	}

}
