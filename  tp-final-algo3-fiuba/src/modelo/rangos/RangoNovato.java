package modelo.rangos;

public class RangoNovato implements Rango {
		
	private int VelocidadDeViaje = 900;

	@Override
	public int costoDeViaje(int CantidadKilometros) {
		return CantidadKilometros/VelocidadDeViaje;
	}
	
	public Rango chequeoDeRango(int cantDeArrestos){
		if (cantDeArrestos < 5) return this;
		
		Rango nuevoRango = new RangoDetective();
		nuevoRango = nuevoRango.chequeoDeRango(cantDeArrestos);
		return nuevoRango;
	}
	@Override
	public String toString(){
		return "Novato";
	}
}