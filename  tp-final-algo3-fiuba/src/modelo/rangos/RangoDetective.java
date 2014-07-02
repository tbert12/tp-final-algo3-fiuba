package modelo.rangos;

public class RangoDetective implements Rango {

	private int velocidadDeViaje = 1100;

	@Override
	public int costoDeViaje(int cantidadKilometros) {
		return cantidadKilometros/velocidadDeViaje;
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
