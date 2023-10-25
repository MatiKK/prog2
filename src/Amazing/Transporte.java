package Amazing;

import java.util.HashMap;
import java.util.Map;

public abstract class Transporte {

	protected String identificador;
	protected double volumenMaximoDeCarga;
	protected double valorPorViaje;
	protected HashMap<Integer, Paquete> paquetes;

	public Transporte(String identificador, double volumenMaximoDeCarga, double valorPorViaje) {
		this.identificador = identificador;
		this.volumenMaximoDeCarga = volumenMaximoDeCarga;
		this.valorPorViaje = valorPorViaje;
	}

	double calcularPrecioViaje() {
		return valorPorViaje;
	};

	void cargarPaquete(Paquete p) {

		int identificadorPaquete = p.obtenerIdentificador();

		Paquete pYaExiste = buscarPaquete(identificadorPaquete);

		if (pYaExiste == null) {
			paquetes.put(identificadorPaquete, p);
		}

	}

	private Paquete buscarPaquete(int identificador) {
		return paquetes.get(identificador);
	}

	double consultarCarga() {

		double cargaTotal = 0;
		for (Map.Entry<Integer, Paquete> paquete : paquetes.entrySet()) {
			cargaTotal += paquete.getValue().calcularVolumen();
		}
		return cargaTotal;

	}

	protected int cantidadPaquetes() {
		return paquetes.size();
	}

}
