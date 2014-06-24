package modelo.heridas;

import modelo.Policia;

public class HeridaArmaDeFuego implements Herida {

	private String mensaje;
	
	public HeridaArmaDeFuego(String UnMensaje) {
		mensaje = UnMensaje;
	}
	
	@Override
	public void herir(Policia UnPolicia) {
		UnPolicia.reducirHorasPorHeridaArmaDeFuego();
	}

	@Override
	public String mensaje() {
		return mensaje;
	}

}
