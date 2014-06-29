package modelo.rangos;

public interface Rango {
	
	public abstract int costoDeViaje(int CantidadKilometros);
	public abstract Rango chequeoDeRango(int CantidadDeArrestos);
	public abstract String toString();

}
