package Amazing;

public abstract class Paquete {

	private int identificador;
	private double volumen;
	private double precio;
	
	public Paquete(int identificador, double volumen, double precio) {
		this.identificador = identificador;
		this.volumen = volumen;
		this.precio = precio;
	}
	

	
	public int obtenerIdentificador() {
		return this.identificador;
	}

	public double calcularVolumen() {
		return this.volumen;
	}

	public boolean fueEntregado() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
