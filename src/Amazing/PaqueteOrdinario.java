package Amazing;

public class PaqueteOrdinario extends Paquete{

	private double costoDeEnvio;
	
	public PaqueteOrdinario(int identificador, double volumen, double precio, double costoDeEnvio) {
		super(identificador, volumen, precio);
		this.costoDeEnvio = costoDeEnvio;
	}
	
	@Override
	public double calcularPrecio() {
		return precio + costoDeEnvio;
	}
	
}
