package Amazing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class EmpresaAmazing implements IEmpresa {

	private String cuit;
	private HashMap<String, Transporte> transportes;
	private HashMap<Integer, Pedido> pedidos;
	private static int NUEVO_IDENTIFICADOR_PEDIDO = 1;
	private static int NUEVO_IDENTIFICADOR_PAQUETE = 1;
	private double factura = 0;

	public EmpresaAmazing(String string) {
		cuit = string;
		this.transportes = new HashMap<String, Transporte>();
		this.pedidos = new HashMap<Integer, Pedido>();
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

	@Override
	public boolean quitarPaquete(int codPaquete) {
		ArrayList<Pedido> pedidos = listaPedidos();
		for (Pedido pedido : pedidos) {
			if (pedido.tienesEstePaquete(codPaquete))
				return pedido.quitarPaquete(codPaquete);
		}
		return false;
	}

	@Override
	public double cerrarPedido(int codPedido) {
		
		Pedido pedido = buscarPedido(codPedido);
		
		if (pedido.cerrado())
			throw new Error("Paquete ya cerrado");
		
		double costePedido = pedido.cerrar();
		factura += costePedido;
						
		// Meter los paquetes del pedido en transportes que puedan llevarlos
		
		for (Map.Entry<Integer,Paquete> paquetes: pedido.carrito().entrySet()) {
			
			Paquete p = paquetes.getValue();
			
			for(Map.Entry<String,Transporte> transporte: transportes.entrySet()) {

				Transporte t = transporte.getValue();
				if (t.puedeLlevarEstePaquete(p)) {
					t.cargarPaquete(p);
					break;
				}
			}
		}
		
		return costePedido;
	}

	@Override
	public String cargarTransporte(String patente) {

		Transporte transporte = null;

		try {
			transporte = buscarTransporte(patente);
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		
		if (transporte.noTienePaquetes())
			return "";

		StringBuilder carga = new StringBuilder();

		LinkedList<Paquete> listaPaquetes = transporte.listaPaquetes();

		for (Map.Entry<Integer, Pedido> p : pedidos.entrySet()) {

			Pedido pedido = p.getValue();
			Iterator<Paquete> paquetesIterador = listaPaquetes.iterator();
			while (paquetesIterador.hasNext()) {

				Paquete paquete = paquetesIterador.next();

				if (pedido.tienesEstePaquete(paquete.obtenerIdentificador())) {

					listaPaquetes.remove(paquete);
					carga.append(p.getKey().toString());
					carga.append(" - ");
					carga.append(paquete.obtenerIdentificador());
					carga.append("] ");
					carga.append(pedido.obtenerDireccion());
					carga.append("\n");
				}
			}
		}
		return carga.toString();
	}

	@Override
	public double costoEntrega(String patente) {

		Transporte t = null;

		try {
			t = buscarTransporte(patente);
		} catch (Exception e) {
			e.printStackTrace();
		}

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
		return factura;
	}

	@Override
	public boolean hayTransportesIdenticos() {
		boolean res = false;
		for (Map.Entry<String, Transporte> t1 : transportes.entrySet()) {
			for (Map.Entry<String, Transporte> t2 : transportes.entrySet()) {
				res |= t1.equals(t2);
			}
		}
		
		return res;
	}
	

	private ArrayList<Pedido> listaPedidos() {
		ArrayList<Pedido> listaPedidos = new ArrayList<Pedido>();
		for (Map.Entry<Integer, Pedido> p : pedidos.entrySet()) {
			listaPedidos.add(p.getValue());
		}
		return listaPedidos;
	}

	private Transporte buscarTransporte(String patente) throws Exception {
		Transporte transporte = transportes.get(patente);
		if (transporte == null)
			throw new Exception("Transporte con patente " + patente + " no encontrado.");
		return transporte;
	}

	private Pedido buscarPedido(int codPedido) {
		return pedidos.get(codPedido);
	}

}
