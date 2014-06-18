package modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import modelo.excepcion.ErrorNoHayMasPaisesParaAvanzar;

public class Ladron {
	private Caracteristicas CaracteristicasDelLadron;
	private String Nombre;
	private Trayectoria Trayectoria;
	private boolean OrdenDeArresto;
	
	public Ladron(String Nombre,Caracteristicas caracteristicas){
		this.Nombre = Nombre;
		this.CaracteristicasDelLadron = caracteristicas;
		this.OrdenDeArresto = false;
	}
	
	public void addTrayectoria(Trayectoria trayecto){
		this.Trayectoria = trayecto;
	}
	
	public String getNombre(){
		return this.Nombre;
	}
	
	public String Sexo(){
		return CaracteristicasDelLadron.getSexo();
	}
	
	public String Hobby(){
		return CaracteristicasDelLadron.getHobby();
	}
	
	public String Cabello(){
		return CaracteristicasDelLadron.getCabello();
	}
	
	public String Senia(){
		return CaracteristicasDelLadron.getSenia();
	}
	
	public String Vehiculo(){
		return CaracteristicasDelLadron.getVehiculo();
	}
	
	public Pais PaisFinal(){
		return Trayectoria.paisFinal();
	}
	public boolean CompararCaracteristicas(Caracteristicas otrasCaracteriscas){
		return CaracteristicasDelLadron.CompararCon(otrasCaracteriscas);
	}
	
	public Pais PaisActual(){
		return this.Trayectoria.paisActual();
	}
	
	public Pais PaisAnterior(){
		return Trayectoria.PaisAnterior();
	}
	
	public boolean PasaPor(Pais pais){
		return this.Trayectoria.EstaEnTrayectoria(pais);
	}

	public Pais Avanzar() {
		try{
			return this.Trayectoria.avanzar();
		}
		catch (ErrorNoHayMasPaisesParaAvanzar e){
			//TODO Devolver un pais anterior al actual, lo necesito en Base
			return PaisAnterior();
		}
	}
	public void EmitirOrdenDeArresto(){
		OrdenDeArresto = true;
	}
	
	public boolean TieneOrdenDeArresto(){
		return OrdenDeArresto;
	}
	public Element serializarLadron(Document doc){
		Element elementoLadron = doc.createElement("Ladron");
		Element elementoCaracteristicas = doc.createElement("Caracteristica");
		elementoLadron.appendChild(elementoCaracteristicas);
		elementoCaracteristicas.appendChild(this.CaracteristicasDelLadron.serializar(doc));
		elementoLadron.setAttribute("Nombre",this.Nombre);
		return elementoLadron;
	}
	public static Ladron ladronHidratar(Document doc,int pos){
		Element elementoLadron = (Element)doc.getElementsByTagName("Ladron").item(pos);
		Element elementoCaracteristicas = (Element)doc.getElementsByTagName("Caracteristica").item(pos);
		Caracteristicas caracteristicas = Caracteristicas.hidratar(elementoCaracteristicas.getChildNodes().item(0));
		Ladron nuevoLadron = new Ladron(elementoLadron.getAttribute("Nombre"),caracteristicas);
		
		
		return nuevoLadron;
	}
}
