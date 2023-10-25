package Amazing;

import java.util.HashMap;
import java.util.Map;

public class EmpresaAmazing implements IEmpresa{

	private String cuit;
	private HashMap<Integer, Transporte> transportes;
	private HashMap<Integer, Pedido> pedidos;
	
	
	public EmpresaAmazing(String string) {
		cuit = string;
	}//A VER SI FUNCIONAAAAAAAAAAAA
	//Prueba 2

	@Override
	public void registrarAutomovil(String patente, int volMax, int valorViaje, int maxPaq) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registrarUtilitario(String patente, int volMax, int valorViaje, int valorExtra) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registrarCamion(String patente, int volMax, int valorViaje, int adicXPaq) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int registrarPedido(String cliente, String direccion, int dni) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int agregarPaquete(int codPedido, int volumen, int precio, int costoEnvio) {
		// TODO Auto-generated method stub
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
