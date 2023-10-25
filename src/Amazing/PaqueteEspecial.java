package Amazing;

public class PaqueteEspecial extends Paquete{

	private double porcentajeAdicional;
	private double valorAdicional;
	
	public PaqueteEspecial(int identificador, double volumen, double precio, double porcentajeAdicional, double valorAdicional) {
		super(identificador, volumen, precio);
		this.porcentajeAdicional = porcentajeAdicional;
		this.valorAdicional = valorAdicional;
	}

}
