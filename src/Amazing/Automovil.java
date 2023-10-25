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
	
	private boolean puedeLlevarEstePaquete(Paquete p) {
		
		if (!(p instanceof PaqueteOrdinario))
			return false;

		double cargaActual = this.consultarCarga();
		double cargaDelPaquete = p.calcularVolumen();
		
		return cargaActual + cargaDelPaquete <= this.volumenMaximoDeCarga
				&& cantidadPaquetes() < this.limiteMaximoDePaquetes;
	}

}