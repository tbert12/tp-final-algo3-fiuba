package modelo.rangos;

public class RangoInvestigador implements Rango {

	private int VelocidadDeViaje = 1300;

	@Override
	public int costoDeViaje(int CantidadKilometros) {
		return CantidadKilometros/VelocidadDeViaje;
	}
	
	public void chequeoDeRango(Rango rango,int cantDeArrestos){
		if (cantDeArrestos >= 20){
			Rango nuevoRango=new RangoSargento();
			rango = nuevoRango;
		}

}
}
