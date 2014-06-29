package modelo.rangos;

public class RangoInvestigador implements Rango {

	private int VelocidadDeViaje = 1300;

	@Override
	public int costoDeViaje(int CantidadKilometros) {
		return CantidadKilometros/VelocidadDeViaje;
	}
	@Override
	public Rango chequeoDeRango(int cantDeArrestos){
	if (cantDeArrestos < 20) return this;
		
	Rango nuevoRango = new RangoSargento();
	nuevoRango = nuevoRango.chequeoDeRango(cantDeArrestos);
	return nuevoRango;
	}
	@Override
	public String toString(){
		return "Investigador";
	}
}
