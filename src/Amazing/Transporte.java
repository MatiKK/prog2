package Amazing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public abstract class Transporte {

	protected final String identificador;
	protected final double volumenMaximoDeCarga;
	protected final double valorPorViaje;
	protected HashMap<Integer, Paquete> paquetes;

	public Transporte(String identificador, double volumenMaximoDeCarga, double valorPorViaje) {
		
		this.paquetes = new HashMap<Integer,Paquete>();
		this.identificador = identificador;
		this.volumenMaximoDeCarga = volumenMaximoDeCarga;
		this.valorPorViaje = valorPorViaje;
	}
	
	public boolean equals(Object other) {
		boolean res = false;
		
		if (!(other instanceof Transporte))
			return res;
	 
		Transporte other_t = (Transporte) other;
		res = tienenCargaIdentica(other_t);
		
		return res;
	}
	
	protected boolean tienenCargaIdentica(Transporte t) {
		if (t == null)
			return false;
		
		if (this.cantidadPaquetes() != t.cantidadPaquetes())
			return false;
	
		boolean acumulador = true;
		LinkedList<Paquete> listaPaquetes_t = t.listaPaquetes();
		LinkedList<Paquete> listaPaquetes = this.listaPaquetes();
		
		Iterator<Paquete> iterator = listaPaquetes_t.iterator();
		
		while(iterator.hasNext()) {
			
			Paquete paqueteActual = (Paquete) iterator.next();
			
			acumulador &= listaPaquetes.contains(paqueteActual);
			
		}
		
		return acumulador;
	}

	abstract double calcularPrecioViaje();

	void cargarPaquete(Paquete p) {
		// AL PARECER HAY QUE ELEGIR NOSOTROS LOS PAQUETES Y TAMBIEN HACER ALGO PARA MARCAR PAQUETES CARGADOS Y NO CARGADOS

		int identificadorPaquete = p.obtenerIdentificador();

		if (!(paquetes.containsKey(identificadorPaquete))) {
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
	
	protected LinkedList<Paquete> listaPaquetes(){
		LinkedList<Paquete> paq = new LinkedList<Paquete>();
		for (Map.Entry<Integer, Paquete> p: paquetes.entrySet()) {
			
			Paquete paquete = p.getValue();
			paq.add(paquete);
		}
		return paq;
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
		return this.paquetes.isEmpty();
	}

	void cargarPaquetes(Map<Integer, Pedido> pedidos) {}
}