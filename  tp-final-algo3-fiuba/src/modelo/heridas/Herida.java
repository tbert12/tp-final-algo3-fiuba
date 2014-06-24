package modelo.heridas;

import modelo.Policia;

public interface Herida {
	public abstract void herir(Policia UnPolicia);
	public abstract String mensaje();
}
