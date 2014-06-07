package modelo;

public class RangoNovato implements Rango {
		
	private int VelocidadDeViaje = 900;

	@Override
	public int CostoDeViaje(int CantidadKilometros) {
		return CantidadKilometros/VelocidadDeViaje;
	}
	
}
