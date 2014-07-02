package modelo.heridas;



import modelo.Policia;

public interface Herida {
	public abstract void herir(Policia unPolicia);
	public abstract String mensaje();
	
}
