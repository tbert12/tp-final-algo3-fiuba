package modelo.rangos;

public class RangoSargento implements Rango {

	private int velocidadDeViaje = 1500;

	@Override
	public int costoDeViaje(int cantidadKilometros) {
		return cantidadKilometros/velocidadDeViaje;
	}
	
	@Override
	public Rango chequeoDeRango(int cantidadDeArrestos){
		return this;
	}
	@Override
	public String toString(){
		return "Sargento";
	}

}
