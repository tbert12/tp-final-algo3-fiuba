package modelo.heridas;

import modelo.Policia;

public class HeridaCuchillo implements Herida {
	
	private String mensaje;
	
	public HeridaCuchillo(String UnMensaje) {
		mensaje = UnMensaje;
	}
	
	@Override
	public void herir(Policia UnPolicia) {
		UnPolicia.reducirHorasPorHeridaCuchillo();
	}

	@Override
	public String mensaje() {
		return mensaje;
	}

}
