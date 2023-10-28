package Amazing;

public abstract class Paquete {

	protected int identificador;
	protected double volumen;
	protected double precio;
	protected boolean entregado = false;
	
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
		return entregado;
	}
	
	public void entregar() {
		entregado = true;
	}


	public double calcularPrecio() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
