package Amazing;

public abstract class Paquete {

	protected int identificador;
	protected final double volumen;
	protected final double precio;
	protected boolean entregado = false;
	protected boolean cerrado = false;
	
	public Paquete(int identificador, double volumen, double precio) {
		this.identificador = identificador;
		this.volumen = volumen;
		this.precio = precio;
	}
	
	public boolean equals(Object other) {
		boolean res = true;
		
		if (!(other instanceof Paquete))
			return false;
		
		Paquete p = (Paquete) other;
		
		res= this.volumen == p.calcularVolumen() &&
				this.precio == p.calcularPrecio();
		return res;
		
	}
	
	public int hashCode() {
		
		return (int)volumen * 10 + (int)precio * 2 + 5000;
		
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
	
	public void cerrar() {
		cerrado = true;
	}
	
	public boolean cerrado() {
		return cerrado;
	}

	public abstract double calcularPrecio();
	
}
