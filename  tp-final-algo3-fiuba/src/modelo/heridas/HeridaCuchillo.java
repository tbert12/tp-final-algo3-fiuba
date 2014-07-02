package modelo.heridas;



import modelo.Policia;

public class HeridaCuchillo implements Herida {
	
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
		HeridaCuchillo other = (HeridaCuchillo) obj;
		if (mensaje == null) {
			if (other.mensaje != null)
				return false;
		} else if (!mensaje.equals(other.mensaje))
			return false;
		return true;
	}

	private String mensaje;
	
	public HeridaCuchillo(String unMensaje) {
		mensaje = unMensaje;
	}
	
	
	@Override
	public void herir(Policia unPolicia) {
		unPolicia.reducirHorasPorHeridaCuchillo();
	}

	@Override
	public String mensaje() {
		return mensaje;
	}

}
