package Amazing;

public class PaqueteEspecial extends Paquete{

	private final double porcentajeAdicional;
	private final double valorAdicional;
	
	public PaqueteEspecial(int identificador, double volumen, double precio, double porcentajeAdicional, double valorAdicional) {
		super(identificador, volumen, precio);
		this.porcentajeAdicional = porcentajeAdicional;
		this.valorAdicional = valorAdicional;
	}
	
	
	public boolean equals(Object other) {
		
		if (!(other instanceof PaqueteEspecial))
			return false;
		
		PaqueteEspecial p = (PaqueteEspecial) other;
		
		return super.equals(p) &&
				this.porcentajeAdicional == p.porcentajeAdicional &&
				this.valorAdicional == p.valorAdicional;
		
	}
	
	public int hashCode() {
		
		return super.hashCode() + 3 * (int)valorAdicional + (int)porcentajeAdicional;
		
	}
	
	
	
	

	@Override
	public double calcularPrecio() {
		
		double precio = this.precio;

		precio = precio * (1 + this.porcentajeAdicional/100);

		// hijo de putaadsokafññl
		if (this.calcularVolumen() >= 3000)
			precio += valorAdicional;
		
		if (this.calcularVolumen() > 5000)
			precio += valorAdicional;
		
		return precio;
	}



}
