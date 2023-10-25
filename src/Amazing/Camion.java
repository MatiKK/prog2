package Amazing;

public class Camion extends Transporte {

	private double valorExtraPorViaje;
	
	public Camion(String identificador, double volumenMaximoDeCarga, double valorPorViaje, double valorExtraPorViaje) {
		super(identificador, volumenMaximoDeCarga, valorPorViaje);
		this.valorExtraPorViaje = valorExtraPorViaje; 
	}

	@Override
	double calcularCosto() {
		// TODO Auto-generated method stub
		return 0;
	}

}
