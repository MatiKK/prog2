package Amazing;

import java.util.HashMap;

public abstract class Transporte {

	private String identificador;
	private double volumenMaximoDeCarga;
	private double valorPorViaje;
	private HashMap<Integer,Paquete> paquetes;
	
	public Transporte(String identificador, double volumenMaximoDeCarga, double valorPorViaje) {
		this.identificador = identificador;
		this.volumenMaximoDeCarga = volumenMaximoDeCarga;
		this.valorPorViaje = valorPorViaje;
	}
	
	double calcularPrecioViaje() {return 0;}
	
	boolean cargarPaquete(Paquete p) {return false;}
	
	double consultarCarga() {return 0;}
	
	abstract double calcularCosto();
	
	
	
}
