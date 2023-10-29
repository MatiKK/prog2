package Amazing;

public class Automovil extends Transporte{

	private final int limiteMaximoDePaquetes;

	public Automovil(String identificador, int volumenMaximoDeCarga, int valorPorViaje, int limiteMaximoDePaquetes) {
		super(identificador, volumenMaximoDeCarga, valorPorViaje);
		this.limiteMaximoDePaquetes = limiteMaximoDePaquetes;
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
	protected boolean puedeLlevarEstePaquete(Paquete p) {

		if (!(p instanceof PaqueteOrdinario))
			return false;

		return p.calcularVolumen() < 2000
				&& cantidadPaquetes() < this.limiteMaximoDePaquetes
				&& super.puedeLlevarEstePaquete(p);
	}

	@Override
	double calcularPrecioViaje() {
		return this.valorPorViaje;
	}

}