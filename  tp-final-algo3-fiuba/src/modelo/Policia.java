package modelo;

public class Policia {
	
	private Rango Rango = new RangoNovato();
	private int CantidadDeArrestos = 0;

	public Rango getRango() {
		return this.Rango;
	}

	public void AddArresto() {
		CantidadDeArrestos++;
		if ( CantidadDeArrestos >= 20 ){
			this.Rango = new RangoSargento();
			return;
		}
		if ( CantidadDeArrestos >= 10){
			this.Rango = new RangoInvestigador();
			return;
		}
		if ( CantidadDeArrestos >= 5){
			this.Rango = new RangoDetective();
			return;
		}
	}

}