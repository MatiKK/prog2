package Amazing;

public class PaqueteEspecial extends Paquete{

	private double porcentajeAdicional;
	private double valorAdicional;
	
	public PaqueteEspecial(int identificador, double volumen, double precio, double porcentajeAdicional, double valorAdicional) {
		super(identificador, volumen, precio);
		this.porcentajeAdicional = porcentajeAdicional;
		this.valorAdicional = valorAdicional;
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
