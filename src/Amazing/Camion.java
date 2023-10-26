package Amazing;

public class Camion extends Transporte {

	private final double valorExtraPorViaje;
	
	public Camion(String identificador, double volumenMaximoDeCarga, double valorPorViaje, double valorExtraPorViaje) {
		super(identificador, volumenMaximoDeCarga, valorPorViaje);
		this.valorExtraPorViaje = valorExtraPorViaje; 
	}
	
	@Override
	public boolean equals(Object other) {
		boolean res = false;
		if (!(other instanceof Camion))
			return res;
		
		res = super.equals(other);
		
		return res;
	}
	
	protected boolean tienenCargaIdentica(Transporte t) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	double calcularPrecioViaje() {
		return this.valorPorViaje + this.valorExtraPorViaje * cantidadPaquetes();
	}

	@Override
	protected boolean puedeLlevarEstePaquete(Paquete p) {
		if (!(p instanceof PaqueteEspecial))
			return false;
	
		return p.calcularVolumen() > 2000 &&
				super.puedeLlevarEstePaquete(p);
	}

}
