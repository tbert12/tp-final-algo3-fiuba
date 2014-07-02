package modelo.rangos;

public interface Rango {
	
	public abstract int costoDeViaje(int cantidadKilometros);
	public abstract Rango chequeoDeRango(int cantidadDeArrestos);
	public abstract String toString();

}
