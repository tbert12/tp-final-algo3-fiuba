package modelo.rangos;

public interface Rango {
	
	public abstract int costoDeViaje(int CantidadKilometros);
	public abstract void chequeoDeRango(Rango rango,int CantidadDeArrestos);

}
