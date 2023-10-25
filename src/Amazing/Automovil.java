package Amazing;

public class Automovil extends Transporte{

	/* Esto también lo tiene Camion, tal vez crear clase Abstracta
	 * TransporteConValorExtraPorViaje o sino queda así
	 * */
	private int limiteMaximoDePaquetes;
	
	
	public Automovil(String identificador, double volumenMaximoDeCarga, double valorPorViaje, int limiteMaximoDePaquetes) {
		super(identificador, volumenMaximoDeCarga, valorPorViaje);
		this.limiteMaximoDePaquetes = limiteMaximoDePaquetes;
		// TODO Auto-generated constructor stub
	}

	
	@Override
	void cargarPaquete(Paquete p) {
		
		if (puedeLlevarEstePaquete(p))
			super.cargarPaquete(p);
	}
	
	@Override
	protected boolean puedeLlevarEstePaquete(Paquete p) {
		
		if (!(p instanceof PaqueteOrdinario))
			return false;
		
		return p.calcularVolumen() < 2000
				&& cantidadPaquetes() < this.limiteMaximoDePaquetes
				&& super.puedeLlevarEstePaquete(p);
	}

}