package Amazing;

public class Camion extends Transporte {

	private double valorExtraPorViaje;
	
	public Camion(String identificador, double volumenMaximoDeCarga, double valorPorViaje, double valorExtraPorViaje) {
		super(identificador, volumenMaximoDeCarga, valorPorViaje);
		this.valorExtraPorViaje = valorExtraPorViaje; 
	}

	@Override
	double calcularPrecioViaje() {
		return valorPorViaje + valorExtraPorViaje * cantidadPaquetes();
	}

	@Override
	protected boolean puedeLlevarEstePaquete(Paquete p) {
		if (!(p instanceof PaqueteEspecial))
			return false;
	
		return p.calcularVolumen() > 2000 &&
				super.puedeLlevarEstePaquete(p);
	}

}
