package modelo.rangos;

public class RangoDetective implements Rango {

	private int VelocidadDeViaje = 1100;

	@Override
	public int costoDeViaje(int CantidadKilometros) {
		return CantidadKilometros/VelocidadDeViaje;
	}
	
	public Rango chequeoDeRango(int cantDeArrestos){
		if (cantDeArrestos < 10) return this;
			
		Rango nuevoRango = new RangoInvestigador();
		nuevoRango = nuevoRango.chequeoDeRango(cantDeArrestos);
		return nuevoRango;
	}
	@Override
	public String toString(){
		return "Detective";
	}
}
