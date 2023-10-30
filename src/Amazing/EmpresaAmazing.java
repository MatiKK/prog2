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
		return cuit.toString();
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
		System.out.println(auto + " agregado.");
	}

	@Override
	public void registrarUtilitario(String patente, int volMax, int valorViaje, int valorExtra) {
		if (transportes.containsKey(patente))
			throw new RuntimeException("Transporte con patente " + patente + " ya existente.");

		Transporte util = new Utilitario(patente, volMax, valorViaje, valorExtra);
		transportes.put(patente, util);
		System.out.println(util + " agregado.");
	}

	@Override
	public void registrarCamion(String patente, int volMax, int valorViaje, int adicXPaq) {
		if (transportes.containsKey(patente))
			throw new RuntimeException("Transporte con patente " + patente + " ya existente.");

		Transporte camion = new Camion(patente, volMax, valorViaje, adicXPaq);
		transportes.put(patente, camion);
		System.out.println(camion + " agregado.");
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
			
			if (pedido.tienesEstePaquete(codPaquete)){
				
				if (pedido.estaCerrado())
					throw new RuntimeException("Pedido ya cerrado");
					
				return pedido.quitarPaquete(codPaquete);
			}
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

		for (Map.Entry<Integer, Pedido> pedido : pedidos.entrySet()) {

			Pedido p = pedido.getValue();

			for (Paquete paquete : p.carrito().values()) {

				if (transporte.puedeLlevarEstePaquete(paquete)) {

					transporte.cargarPaquete(paquete);
					paquete.entregar();
					carga.append(" + [ ");
					carga.append(pedido.getKey().toString());
					carga.append(" - ");
					carga.append(paquete.obtenerIdentificador());
					carga.append(" ] ");
					carga.append(p.obtenerDireccion());
					carga.append("\n");
				}
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

		for (Map.Entry<Integer, Pedido> pedido : pedidos.entrySet()) {
			Pedido p = pedido.getValue();
			if (!p.fueEntregado() && p.estaCerrado())
				lista.put(pedido.getKey(), p.obtenerNombreCliente());
		}
		return lista;
	}

	@Override
	public double facturacionTotalPedidosCerrados() {
		return factura;
	}

	@Override
	public boolean hayTransportesIdenticos() {

		boolean hayTransportesIdenticos = false;

		for (Map.Entry<String, Transporte> transportes1 : transportes.entrySet()) {

			Transporte t1 = transportes1.getValue();

			for (Map.Entry<String, Transporte> transportes2 : transportes.entrySet()) {

				Transporte t2 = transportes2.getValue();

				// Si coincide la patente son el mismo y no cuenta
				if (transportes1.getKey().equals(transportes2.getKey()))
					continue;

				hayTransportesIdenticos |= t1.equals(t2);
			}
		}
		return hayTransportesIdenticos;
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
