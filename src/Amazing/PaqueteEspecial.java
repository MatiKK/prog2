package Amazing;

public class PaqueteEspecial extends Paquete{

	private double porcentajeAdicional;
	private double valorAdicional;
	private double precio;
	private double volumen;

	public PaqueteEspecial(int identificador, double volumen, double precio, double porcentajeAdicional, double valorAdicional) {
		super(identificador, volumen, precio);
		this.porcentajeAdicional = porcentajeAdicional;
		this.valorAdicional = valorAdicional;
		this.precio = precio + precio * porcentajeAdicional;
	}

	@Override
	public double calcularPrecio() {

		if (volumen >3000) {
			if (volumen >5000) 
				return precio + valorAdicional *2;
			
			return precio + valorAdicional;
		}
		return precio;
	}



}
