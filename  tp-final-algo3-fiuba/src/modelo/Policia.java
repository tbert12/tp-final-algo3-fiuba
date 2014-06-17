package modelo;

import org.w3c.dom.Element;

import org.w3c.dom.Document;

public class Policia {
	
	private String Nombre = "";
	private Rango Rango = new RangoNovato();
	private int CantidadDeArrestos = 0;
	private Tiempo Tiempo;
	private Pais PaisActual;

	public Policia(String Nombre, int CantidadDeArrestos){
		this.Nombre = Nombre;
		this.CantidadDeArrestos = CantidadDeArrestos;
		ChequeoDeRango();
		
	}

	public String getNombre(){
		return this.Nombre;
	}
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
		ChequeoDeRango();
		
	}
	private void ChequeoDeRango(){
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

	public void ReducirHoras(int horas){
		Tiempo.ReducirHoras(horas);
	}
	
	public boolean TiempoAgotado(){
		return Tiempo.TiempoAgotado();
	}
	public Element Serializar(Document doc){
		Element elementoPolicia =doc.createElement("Policia");
		elementoPolicia.setAttribute("Nombre",this.Nombre);
		elementoPolicia.setAttribute("Arrestos",""+this.CantidadDeArrestos);
		return elementoPolicia;
	}
		
	public static Policia Hidratar(Document doc){
		Element elementoPolicia = (Element)doc.getElementsByTagName("Policia").item(0);
		Policia nuevoPolicia = new Policia(elementoPolicia.getAttribute("Nombre"),Integer.parseInt(elementoPolicia.getAttribute("Arrestos")));
		return nuevoPolicia;
		
		
	}
		
		
				
		
		
	}
