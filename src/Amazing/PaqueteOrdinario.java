package Amazing;

public class PaqueteOrdinario extends Paquete{

	private final int costoDeEnvio;
	
	public PaqueteOrdinario(int identificador, int volumen, int precio, int costoDeEnvio) {
		super(identificador, volumen, precio);
		this.costoDeEnvio = costoDeEnvio;
	}
	
	public boolean equals(Object other) {
			
			if (!(other instanceof PaqueteOrdinario))
				return false;
			
			PaqueteOrdinario p = (PaqueteOrdinario) other;
			
			return super.equals(p) &&
					this.costoDeEnvio == p.costoDeEnvio;
			
		}
		
		public int hashCode() {
			
			return super.hashCode() + 45 * (int)costoDeEnvio;
			
		}
	
	
	@Override
	public double calcularPrecio() {

		return this.precio + this.costoDeEnvio;
	}
	
}
