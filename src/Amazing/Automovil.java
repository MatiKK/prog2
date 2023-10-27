package Amazing;

import java.util.Map;

public class Automovil extends Transporte{

	/* Esto también lo tiene Camion, tal vez crear clase Abstracta
	 * TransporteConValorExtraPorViaje o sino queda así
	 * */
	private final int limiteMaximoDePaquetes;


	public Automovil(String identificador, double volumenMaximoDeCarga, double valorPorViaje, int limiteMaximoDePaquetes) {
		super(identificador, volumenMaximoDeCarga, valorPorViaje);
		this.limiteMaximoDePaquetes = limiteMaximoDePaquetes;
		// TODO Auto-generated constructor stub
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
	void cargarPaquetes(Map<Integer, Pedido> pedidos) {
		for (Map.Entry<Integer, Pedido> pedido : pedidos.entrySet()){
			if (pedido.getValue().cerrado()) {
				paquetes = pedido.getValue().carrito();
				for (Map.Entry<Integer, Paquete> paquete : paquetes.entrySet()) {
					if (puedeLlevarEstePaquete(paquete.getValue())) {
						this.paquetes.put(paquete.getValue().obtenerIdentificador(), paquete.getValue());
					}
				}
			}
		}
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