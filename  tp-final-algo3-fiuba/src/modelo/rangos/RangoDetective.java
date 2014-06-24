package modelo.rangos;

public class RangoDetective implements Rango {

	private int VelocidadDeViaje = 1100;

	@Override
	public int CostoDeViaje(int CantidadKilometros) {
		return CantidadKilometros/VelocidadDeViaje;
	}
	
	public void ChequeoDeRango(Rango rango,int cantDeArrestos){
		if (cantDeArrestos >= 10 && cantDeArrestos<20){
			Rango nuevoRango=new RangoInvestigador();
			rango = nuevoRango;
		}
	}
}
