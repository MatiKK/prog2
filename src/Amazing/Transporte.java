package Amazing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public abstract class Transporte {

	protected final String identificador;
	protected final int volumenMaximoDeCarga;
	protected final int valorPorViaje;
	protected HashMap<Integer, Paquete> paquetes;

	public Transporte(String identificador, int volumenMaximoDeCarga, int valorPorViaje) {
		
		if (identificador == null)
			throw new RuntimeException("El identificador no puede ser null.");
		if (volumenMaximoDeCarga <= 0)
			throw new RuntimeException("El volumen máximo no puede ser nulo o negativo.");
		if (valorPorViaje <= 0)
			throw new RuntimeException("El valor por viaje no puede ser nulo o negativo.");
		
		this.paquetes = new HashMap<Integer, Paquete>();
		this.identificador = identificador;
		this.volumenMaximoDeCarga = volumenMaximoDeCarga;
		this.valorPorViaje = valorPorViaje;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(identificador.toString());
		sb.append(" - volumen máximo de carga: ");
		sb.append(volumenMaximoDeCarga);
		sb.append(" - valor por viaje: ");
		sb.append(valorPorViaje);
		sb.append(" - paquetes: ");
		sb.append(cantidadPaquetes());
		return sb.toString();
	}
	
	
	@Override
	public boolean equals(Object other) {

		if (other == null
		|| this.getClass() != other.getClass())
			return false;

		Transporte other_t = (Transporte) other;

		return tienenCargaIdentica(other_t);
	}

	private boolean tienenCargaIdentica(Transporte t) {
		if (t == null)
			return false;
		if (this.noTienePaquetes() || t.noTienePaquetes())
			return false;
		if (this.cantidadPaquetes() != t.cantidadPaquetes())
			return false;

		ArrayList<Paquete> listaPaquetes = this.listaPaquetes();

		Iterator<Paquete> iteratorPaquetes_t = t.listaPaquetes().iterator();

		while (iteratorPaquetes_t.hasNext()) {

			Paquete paquete_t = (Paquete) iteratorPaquetes_t.next();

			if (!(listaPaquetes.contains(paquete_t)))
				return false;

		}
		return true;
	}

	abstract double calcularPrecioViaje();

	void cargarPaquete(Paquete p) {

		int identificadorPaquete = p.obtenerIdentificador();

		if (!(paquetes.containsKey(identificadorPaquete))) {
			paquetes.put(identificadorPaquete, p);
		}
	}

	protected ArrayList<Paquete> listaPaquetes() {
		return new ArrayList<Paquete>(paquetes.values());
	}

	protected boolean puedeLlevarEstePaquete(Paquete p) {

		double cargaActual = this.consultarCarga();
		double cargaDelPaquete = p.calcularVolumen();

		return !p.fueEntregado() 
			&& p.perteneceAPedidoCerrado()
			&& (cargaActual + cargaDelPaquete <= this.volumenMaximoDeCarga);
	}

	int consultarCarga() {

		int cargaTotal = 0;

		for (Paquete paquete : listaPaquetes())
			cargaTotal += paquete.calcularVolumen();

		return cargaTotal;
	}

	protected int cantidadPaquetes() {
		return this.paquetes.size();
	}

	protected boolean noTienePaquetes() {
		return this.paquetes.isEmpty();
	}

	public String obtenerPatente() {
		return this.identificador.toString();
	}
	
}