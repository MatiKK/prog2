package Amazing;

import java.util.ArrayList;
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
		if (this.cantidadPaquetes() == 0)
			return 0;
		return valorPorViaje;
	};

	void cargarPaquete(Paquete p) {

		int identificadorPaquete = p.obtenerIdentificador();

		Paquete pYaExiste = buscarPaquete(identificadorPaquete);

		if (pYaExiste == null) {
			paquetes.put(identificadorPaquete, p);
		}

	}
	
	ArrayList<Paquete> paquetesNoEntregados(){
		if (this.noTienePaquetes())
			return null;
		
		ArrayList<Paquete> paquetesNoEntregados = new ArrayList<Paquete>();
		
		for (Map.Entry<Integer, Paquete> p: paquetes.entrySet()) {
			
			Paquete paquete = p.getValue();
			if (!paquete.fueEntregado())
				paquetesNoEntregados.add(paquete);
		}
		return paquetesNoEntregados;
		
	}
	
	private Paquete buscarPaquete(int identificador) {
		return paquetes.get(identificador);
	}

	protected boolean puedeLlevarEstePaquete(Paquete p) {

		double cargaActual = this.consultarCarga();
		double cargaDelPaquete = p.calcularVolumen();
		
		return cargaActual + cargaDelPaquete <= this.volumenMaximoDeCarga;
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
	
	protected boolean noTienePaquetes() {
		return this.cantidadPaquetes() == 0;
	}

}