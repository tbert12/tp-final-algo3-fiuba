package modelo.heridas;



import modelo.Policia;

public class HeridaArmaDeFuego implements Herida {

	private String mensaje;
	
	public HeridaArmaDeFuego(String unMensaje) {
		mensaje = unMensaje;
	}

	
	@Override
	public void herir(Policia unPolicia) {
		unPolicia.reducirHorasPorHeridaArmaDeFuego();
	}

	@Override
	public String mensaje() {
		return mensaje;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mensaje == null) ? 0 : mensaje.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HeridaArmaDeFuego other = (HeridaArmaDeFuego) obj;
		if (mensaje == null) {
			if (other.mensaje != null)
				return false;
		} else if (!mensaje.equals(other.mensaje))
			return false;
		return true;
	}

}
