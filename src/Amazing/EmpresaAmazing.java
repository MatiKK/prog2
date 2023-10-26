package Amazing;

import java.util.HashMap;
import java.util.Map;

public class EmpresaAmazing implements IEmpresa{

	private String cuit;
	private HashMap<String, Transporte> transportes;
	private HashMap<Integer, Pedido> pedidos;
	
	
	public EmpresaAmazing(String string) {
		cuit = string;
	}//A VER SI FUNCIONAAAAAAAAAAAA
	//Prueba 2

	@Override
	public void registrarAutomovil(String patente, int volMax, int valorViaje, int maxPaq) {
		if (transportes.containsKey(patente)) System.out.println("La patente ingresada ya existe");
		
		else {
			Transporte auto = new Automovil(patente, volMax, valorViaje, maxPaq);
			transportes.put(patente, auto);
			System.out.println("El automovil fué agregado exitosamente");
		}
		
	}

	@Override
	public void registrarUtilitario(String patente, int volMax, int valorViaje, int valorExtra) {
		if (transportes.containsKey(patente)) System.out.println("La patente ingresada ya existe");
		
		else {
			Transporte util = new Utilitario(patente, volMax, valorViaje,valorExtra);
			transportes.put(patente, util);
			System.out.println("El transporte utilitario fué agregado exitosamente");
		}
		
	}
	//acá había una lllave de cierre "}"

	@Override
	public void registrarCamion(String patente, int volMax, int valorViaje, int adicXPaq) {
		if (transportes.containsKey(patente)) System.out.println("La patente ingresada ya existe");
		
		else {
			Transporte util = new Camion(patente, volMax, valorViaje,adicXPaq);
			transportes.put(patente, util);
			System.out.println("El transporte utilitario fué agregado exitosamente");
		}
	}

	@Override
	public int registrarPedido(String cliente, String direccion, int dni) {
		
		int cod=dni;
		while (pedidos.containsKey(cod))
			cod += 1;
		
		Pedido ped = new Pedido(cod, dni, cliente, direccion);
		pedidos.put(dni,ped);
		return dni;
	}

	@Override
	public int agregarPaquete(int codPedido, int volumen, int precio, int costoEnvio) {
		pedidos.get(codPedido).agregarPaquete(codPedido, volumen, precio, costoEnvio);
		return 0;
	}

	@Override
	public int agregarPaquete(int codPedido, int volumen, int precio, int porcentaje, int adicional) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean quitarPaquete(int codPaquete) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return 0;
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

}
