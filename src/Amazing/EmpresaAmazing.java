package Amazing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EmpresaAmazing implements IEmpresa {

	private String cuit;
	private HashMap<String, Transporte> transportes;
	private HashMap<Integer, Pedido> pedidos;

	private static int NUEVO_IDENTIFICADOR_PEDIDO = 1;
	private static int NUEVO_IDENTIFICADOR_PAQUETE = 1;

	public EmpresaAmazing(String string) {
		cuit = string;
	}// A VER SI FUNCIONAAAAAAAAAAAA
		// Prueba 2

	@Override
	public void registrarAutomovil(String patente, int volMax, int valorViaje, int maxPaq) {
		if (transportes.containsKey(patente))
			System.out.println("La patente ingresada ya existe");

		else {
			Transporte auto = new Automovil(patente, volMax, valorViaje, maxPaq);
			transportes.put(patente, auto);
			System.out.println("El automovil fue agregado exitosamente");
		}

	}

	@Override
	public void registrarUtilitario(String patente, int volMax, int valorViaje, int valorExtra) {
		if (transportes.containsKey(patente))
			System.out.println("La patente ingresada ya existe");

		else {
			Transporte util = new Utilitario(patente, volMax, valorViaje, valorExtra);
			transportes.put(patente, util);
			System.out.println("El transporte utilitario fué agregado exitosamente");
		}

	}

	@Override
	public void registrarCamion(String patente, int volMax, int valorViaje, int adicXPaq) {
		if (transportes.containsKey(patente))
			System.out.println("La patente ingresada ya existe");

		else {
			Transporte util = new Camion(patente, volMax, valorViaje, adicXPaq);
			transportes.put(patente, util);
			System.out.println("El transporte utilitario fué agregado exitosamente");
		}
	}

	private int generarNuevoIdentificadorDePedido() {
		return NUEVO_IDENTIFICADOR_PEDIDO ++;
	}

	private int generarNuevoIdentificadorDePaquete() {
		return NUEVO_IDENTIFICADOR_PEDIDO ++;
	}

	@Override
	public int registrarPedido(String cliente, String direccion, int dni) {

		int numPedido = generarNuevoIdentificadorDePedido();

		Pedido pedido = new Pedido(numPedido, dni, cliente, direccion);

		pedidos.put(dni, pedido);
		return numPedido;

	}

	@Override
	// originalmente iba todo int, despues ver si queda así o no
	public int agregarPaquete(int codPedido, int volumen, int precio, int costoEnvio) {
		int identificadorPaquete = generarNuevoIdentificadorDePaquete();
		Paquete paquete = new PaqueteOrdinario(identificadorPaquete, volumen, precio, costoEnvio);
		return agregarPaquete(codPedido, paquete);
	}

	@Override
	public int agregarPaquete(int codPedido, int volumen, int precio, int porcentaje, int adicional) {
		int identificadorPaquete = generarNuevoIdentificadorDePaquete();
		Paquete paquete = new PaqueteEspecial(identificadorPaquete, volumen, precio, porcentaje, adicional);
		return agregarPaquete(codPedido, paquete);
	}

	private int agregarPaquete(int codPedido, Paquete p) {
		Pedido pedido = buscarPedido(codPedido);
		pedido.agregarPaquete(p);
		return p.obtenerIdentificador();
	}

	private ArrayList<Pedido> listaPedidos(){
		ArrayList<Pedido> listaPedidos = new ArrayList<Pedido>();
		for (Map.Entry<Integer, Pedido> p: pedidos.entrySet()) {
			listaPedidos.add(p.getValue());
		}
		return listaPedidos;
	}
	
	@Override
	public boolean quitarPaquete(int codPaquete) {

		ArrayList<Pedido> pedidos = listaPedidos();
		
		for (Pedido pedido: pedidos) {
			if (pedido.tienesEstePaquete(codPaquete))
				return pedido.quitarPaquete(codPaquete);
		}
		
		return false;
	}

	@Override
	public double cerrarPedido(int codPedido) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String cargarTransporte(String patente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double costoEntrega(String patente) {

		Transporte t = buscarTransporte(patente);

		if (t == null || t.noTienePaquetes())
			return 0; // error

		return t.calcularPrecioViaje();
	}

	@Override
	public Map<Integer, String> pedidosNoEntregados() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double facturacionTotalPedidosCerrados() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hayTransportesIdenticos() {
		// TODO Auto-generated method stub
		return false;
	}

	private Transporte buscarTransporte(String patente) {
		return transportes.get(patente);
	}

	private Pedido buscarPedido(int codPedido) {
		return pedidos.get(codPedido);
	}

}
