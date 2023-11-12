package Amazing;

import java.util.HashMap;
import java.util.Map;

public class EmpresaAmazing implements IEmpresa {

	private String cuit;
	private HashMap<String, Transporte> transportes;
	private HashMap<Integer, Pedido> pedidos;
	private static int NUEVO_IDENTIFICADOR_PEDIDO = 1;
	private static int NUEVO_IDENTIFICADOR_PAQUETE = 1;
	private double factura = 0;

	public EmpresaAmazing(String string) {
		
		if (string == null)
			throw new RuntimeException("cuit no puede ser null");
		cuit = string;
		this.transportes = new HashMap<String, Transporte>();
		this.pedidos = new HashMap<Integer, Pedido>();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Empresa Amazing CUIT ");
		sb.append(cuit.toString());
		for(Transporte t: transportes.values()) {
			sb.append(t.toString());
			sb.append('\n');
		}
		for(Pedido p: pedidos.values()) {
			sb.append(p.toString());
			sb.append('\n');
		}
		return sb.toString();
	}

	private int generarNuevoIdentificadorDePedido() {
		return NUEVO_IDENTIFICADOR_PEDIDO++;
	}

	private int generarNuevoIdentificadorDePaquete() {
		return NUEVO_IDENTIFICADOR_PAQUETE++;
	}

	@Override
	public void registrarAutomovil(String patente, int volMax, int valorViaje, int maxPaq) {
		if (transportes.containsKey(patente))
			throw new RuntimeException("Transporte con patente " + patente + " ya existente.");

		Transporte auto = new Automovil(patente, volMax, valorViaje, maxPaq);
		transportes.put(patente, auto);
	}

	@Override
	public void registrarUtilitario(String patente, int volMax, int valorViaje, int valorExtra) {
		if (transportes.containsKey(patente))
			throw new RuntimeException("Transporte con patente " + patente + " ya existente.");

		Transporte util = new Utilitario(patente, volMax, valorViaje, valorExtra);
		transportes.put(patente, util);
	}

	@Override
	public void registrarCamion(String patente, int volMax, int valorViaje, int adicXPaq) {
		if (transportes.containsKey(patente))
			throw new RuntimeException("Transporte con patente " + patente + " ya existente.");

		Transporte camion = new Camion(patente, volMax, valorViaje, adicXPaq);
		transportes.put(patente, camion);
	}

	@Override
	public int registrarPedido(String cliente, String direccion, int dni) {
		int numPedido = generarNuevoIdentificadorDePedido();
		Pedido pedido = new Pedido(numPedido, dni, cliente, direccion);
		pedidos.put(numPedido, pedido);
		return numPedido;
	}

	@Override
	public int agregarPaquete(int codPedido, int volumen, int precio, int costoEnvio) {
		int identificadorPaquete = generarNuevoIdentificadorDePaquete();
		Pedido pedido = buscarPedido(codPedido);
		
		if (pedido.estaCerrado())
			throw new RuntimeException("Pedido ya cerrado");
		
		pedido.agregarPaquete(identificadorPaquete, volumen, precio, costoEnvio);
		return identificadorPaquete;
	}

	@Override
	public int agregarPaquete(int codPedido, int volumen, int precio, int porcentaje, int adicional) {
		int identificadorPaquete = generarNuevoIdentificadorDePaquete();
		Pedido pedido = buscarPedido(codPedido);
		
		if (pedido.estaCerrado())
			throw new RuntimeException("Pedido ya cerrado");
		
		pedido.agregarPaquete(identificadorPaquete, volumen, precio, porcentaje, adicional);
		return identificadorPaquete;
	}

	@Override
	public boolean quitarPaquete(int codPaquete) {
		for (Pedido pedido : pedidos.values()) {
			
			if (pedido.tienesEstePaquete(codPaquete))
				return pedido.quitarPaquete(codPaquete);
		}
		
		throw new RuntimeException("Paquete inexistente.");
	}

	@Override
	public double cerrarPedido(int codPedido) {

		Pedido pedido = buscarPedido(codPedido);

		if (pedido.estaCerrado())
			throw new RuntimeException("Pedido ya cerrado");

		double costePedido = pedido.cerrar();
		factura += costePedido;

		return costePedido;
	}

	@Override
	public String cargarTransporte(String patente) {

		Transporte transporte = buscarTransporte(patente);

		StringBuilder carga = new StringBuilder();

		for (Pedido p: pedidos.values()) {
			if (p.estaCerrado() && !p.fueEntregado()) {
				carga.append(p.cargarEnTransporte(transporte));
			}
		}

		return carga.toString();
	}

	@Override
	public double costoEntrega(String patente) {

		Transporte t = buscarTransporte(patente);

		if (t.noTienePaquetes())
			throw new RuntimeException("Transporte vacío.");

		return t.calcularPrecioViaje();
	}

	@Override
	public Map<Integer, String> pedidosNoEntregados() {

		Map<Integer, String> lista = new HashMap<Integer, String>();

		for (Pedido p: pedidos.values()) {
			if (!p.fueEntregado() && p.estaCerrado())
				lista.put(p.obtenerIdentificador(), p.obtenerNombreCliente());
		}
		return lista;
	}

	@Override
	public double facturacionTotalPedidosCerrados() {
		return factura;
	}

	@Override
	public boolean hayTransportesIdenticos() {

		for (Transporte t1: transportes.values()) {

			for (Transporte t2: transportes.values()) {
				
				// Si coincide la patente son el mismo y no cuenta
				if (t1.obtenerPatente().equals(t2.obtenerPatente()))
					continue;

				if (t1.equals(t2))
					return true;
			}
		}
		return false;
	}

	private Transporte buscarTransporte(String patente) {
		Transporte t = transportes.get(patente);
		if (t == null) {
			throw new RuntimeException("Transporte con patente " + patente + " inexistente.");
		}
		return t;
	}

	private Pedido buscarPedido(int codPedido) {
		Pedido p = pedidos.get(codPedido);
		if (p == null) {
			throw new RuntimeException("Pedido con identificador " + codPedido + " inexistente.");
		}
		return p;

	}

}
