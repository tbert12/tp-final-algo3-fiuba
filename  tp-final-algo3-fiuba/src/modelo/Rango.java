package modelo;

public interface Rango {
	
	public abstract int CostoDeViaje(int CantidadKilometros);
	public abstract void ChequeoDeRango(Rango rango,int CantidadDeArrestos);

}
