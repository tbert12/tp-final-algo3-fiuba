package modelo;

public class RangoInvestigador implements Rango {

	private int VelocidadDeViaje = 1300;

	@Override
	public int CostoDeViaje(int CantidadKilometros) {
		return CantidadKilometros/VelocidadDeViaje;
	}
	
	public void ChequeoDeRango(Rango rango,int cantDeArrestos){
		if (cantDeArrestos >= 20){
			Rango nuevoRango=new RangoSargento();
			rango = nuevoRango;
		}

}
}
