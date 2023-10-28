package Amazing;

public class Utilitario extends Transporte{

	private final int valorExtra;
	
	public Utilitario(String identificador, int volumenMaximoDeCarga, int valorPorViaje, int valorExtra) {
		super(identificador, volumenMaximoDeCarga, valorPorViaje);
		this.valorExtra = valorExtra;
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
	double calcularPrecioViaje() {
		return this.valorPorViaje * ((cantidadPaquetes() > 3)? valorExtra : 1);
	}

}
