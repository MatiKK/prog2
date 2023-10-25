package Amazing;

public class Utilitario extends Transporte{
	private double valorExtraPorCarga;

	public Utilitario(String identificador, double volumenMaximoDeCarga, double valorPorViaje, int valorExtra) {
		super(identificador, volumenMaximoDeCarga, valorPorViaje);
		// TODO Auto-generated constructor stub
	}

	@Override
	double calcularCosto() {
		// TODO Auto-generated method stub
		return 0;
	}

}
