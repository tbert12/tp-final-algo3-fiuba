package modelo;

public class RangoInvestigador implements Rango {

	private int VelocidadDeViaje = 1300;

	@Override
	public int CostoDeViaje(int CantidadKilometros) {
		return CantidadKilometros/VelocidadDeViaje;
	}

}
