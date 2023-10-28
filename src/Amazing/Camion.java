package Amazing;

public class Camion extends Transporte {

	private final int valorExtraPorPaquete;
	
	public Camion(String identificador, int volumenMaximoDeCarga, int valorPorViaje, int valorExtraPorPaquete) {
		super(identificador, volumenMaximoDeCarga, valorPorViaje);
		this.valorExtraPorPaquete = valorExtraPorPaquete; 
	}
	
	@Override
	public boolean equals(Object other) {
		boolean res = false;
		if (!(other instanceof Camion))
			return res;
		
		res = super.equals(other);
		
		return res;
	}
	
	@Override
	double calcularPrecioViaje() {
		return this.valorPorViaje + this.valorExtraPorPaquete * cantidadPaquetes();
	}

	@Override
	protected boolean puedeLlevarEstePaquete(Paquete p) {
		if (!(p instanceof PaqueteEspecial))
			return false;
	
		return p.calcularVolumen() > 2000 &&
				super.puedeLlevarEstePaquete(p);
	}

}
