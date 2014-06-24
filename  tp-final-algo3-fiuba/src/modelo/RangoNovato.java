package modelo;

public class RangoNovato implements Rango {
		
	private int VelocidadDeViaje = 900;

	@Override
	public int CostoDeViaje(int CantidadKilometros) {
		return CantidadKilometros/VelocidadDeViaje;
	}
	
	public void ChequeoDeRango(Rango rango,int cantDeArrestos){
		if (cantDeArrestos >= 5 && cantDeArrestos<10){
			Rango nuevoRango=new RangoDetective();
			rango = nuevoRango;
		}
}
}