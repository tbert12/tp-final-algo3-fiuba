package modelo;

public class Policia {
	
	private Rango Rango = new RangoNovato();
	private int CantidadDeArrestos = 0;
	private Tiempo Tiempo;
	private Pais PaisActual;

	public Rango getRango() {
		return this.Rango;
	}

	public void setTiempo(Tiempo tiempo){
		this.Tiempo = tiempo;
	}
	
	public void setPaisActual(Pais pais){
		this.PaisActual = pais;
	}
	
	public Pais getPais(){
		return this.PaisActual;
	}
	
	public Tiempo getTiempo(){
		return this.Tiempo;
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

	public int CostoDeViaje(int kilometrosAViajar) {
		return this.Rango.CostoDeViaje(kilometrosAViajar);
	}

}