package modelo;

public class RangoSargento implements Rango {

	private int VelocidadDeViaje = 1500;

	@Override
	public int Viajar(int CantidadKilometros) {
		return CantidadKilometros/VelocidadDeViaje;
	}

}
