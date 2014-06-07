package modelo;

public class RangoNovato implements Rango {
		
	private int VelocidadDeViaje = 900;

	@Override
	public int Viajar(int CantidadKilometros) {
		return CantidadKilometros/VelocidadDeViaje;
	}
	
}
