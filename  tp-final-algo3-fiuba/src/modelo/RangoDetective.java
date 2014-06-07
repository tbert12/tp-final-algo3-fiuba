package modelo;

public class RangoDetective implements Rango {

	private int VelocidadDeViaje = 1100;

	@Override
	public int CostoDeViaje(int CantidadKilometros) {
		return CantidadKilometros/VelocidadDeViaje;
	}

}
