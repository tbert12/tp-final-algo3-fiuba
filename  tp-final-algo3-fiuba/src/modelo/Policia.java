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

	public void AddArresto() {
		CantidadDeArrestos++;
		ChequeoDeRango();
		
	}
	
	/*
	public void HeridaArmaDeFuego(){
		this.Tiempo.ReducirHoras(HorasHeridaArmaDeFuego);
	}
	
	public void HeridaCuclillo(){
		this.Tiempo.ReducirHoras(HorasHeridaCuchillo);
	}
	public void Dormir(){
		this.Tiempo.ReducirHoras(HorasDormir);
	}
	*/
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
		
	public static Policia Hidratar(Document doc, int Pos){
		//Como se va a guardar en orden acorde a la posicion dada
		//Le paso por parametro la posicion, para poder hidratar el correcto.
		Element elementoPolicia = (Element)doc.getElementsByTagName("Policia").item(Pos);
		Policia nuevoPolicia = new Policia(elementoPolicia.getAttribute("Nombre"),Integer.parseInt(elementoPolicia.getAttribute("Arrestos")));
		return nuevoPolicia;
		
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Nombre == null) ? 0 : Nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Policia other = (Policia) obj;
		if (Nombre == null) {
			if (other.Nombre != null)
				return false;
		} else if (!Nombre.equals(other.Nombre))
			return false;
		return true;
	}
		
		
				
		
		
	}
