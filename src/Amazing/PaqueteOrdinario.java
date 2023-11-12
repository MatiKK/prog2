package Amazing;

public class PaqueteOrdinario extends Paquete {

	private final int costoDeEnvio;

	public PaqueteOrdinario(int identificador, int volumen, int precio, int costoDeEnvio) {
		super(identificador, volumen, precio);

		if (costoDeEnvio <= 0)
			throw new RuntimeException("El costo de envío no puede ser nulo o negativo.");
		this.costoDeEnvio = costoDeEnvio;
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(" - costo de envío: ");
		sb.append(costoDeEnvio);
		
		return sb.toString();
	}
	

	@Override
	public boolean equals(Object other) {

		if (!(other instanceof PaqueteOrdinario))
			return false;

		PaqueteOrdinario p = (PaqueteOrdinario) other;

		return super.equals(p) && this.costoDeEnvio == p.costoDeEnvio;
	}

	@Override
	public int hashCode() {
		return super.hashCode() + this.costoDeEnvio * 21;
	}

	@Override
	public double calcularPrecio() {
		return this.precio + this.costoDeEnvio;
	}

}
