package Amazing;

public class Utilitario extends Transporte{

	private final double valorExtraPorViaje;
	
	public Utilitario(String identificador, double volumenMaximoDeCarga, double valorPorViaje, double valorExtraPorViaje) {
		super(identificador, volumenMaximoDeCarga, valorPorViaje);
		this.valorExtraPorViaje = valorExtraPorViaje;
	}

	@Override
	public boolean equals(Object other) {
		boolean res = false;
		if (!(other instanceof Automovil))
			return res;
		
		res = super.equals(other);
		
		return res;
	}
	
	@Override
	protected boolean tienenCargaIdentica(Transporte t) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	double calcularPrecioViaje() {
		return this.valorPorViaje * ((cantidadPaquetes() > 3)? valorExtraPorViaje : 1);
	}

}
