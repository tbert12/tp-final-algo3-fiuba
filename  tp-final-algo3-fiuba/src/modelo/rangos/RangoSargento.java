package modelo.rangos;

public class RangoSargento implements Rango {

	private int VelocidadDeViaje = 1500;

	@Override
	public int costoDeViaje(int CantidadKilometros) {
		return CantidadKilometros/VelocidadDeViaje;
	}
	public void chequeoDeRango(Rango rango,int CantidadDeArrestos){
	}

}
