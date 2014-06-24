package modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import modelo.caracteristicas.*;
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
	
	public Sexo getSexo(){
		return CaracteristicasDelLadron.getSexo();
	}
	
	public Hobby getHobby(){
		return CaracteristicasDelLadron.getHobby();
	}
	
	public Cabello getCabello(){
		return CaracteristicasDelLadron.getCabello();
	}
	
	public Senia getSenia(){
		return CaracteristicasDelLadron.getSenia();
	}
	
	public Vehiculo getVehiculo(){
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
			//Devolver un pais anterior al actual, lo necesito en Base
			return PaisAnterior();
		}
	}
	public void EmitirOrdenDeArresto(){
		OrdenDeArresto = true;
	}
	
	public boolean TieneOrdenDeArresto(){
		return OrdenDeArresto;
	}
	public Element Serializar(Document doc){
		Element elementoLadron = doc.createElement("Ladron");
		Element elementoCaracteristicas = doc.createElement("Caracteristica");
		elementoLadron.appendChild(elementoCaracteristicas);
		elementoCaracteristicas.appendChild(this.CaracteristicasDelLadron.Serializar(doc));
		elementoLadron.setAttribute("Nombre",this.Nombre);
		return elementoLadron;
	}
	public static Ladron Hidratar(Node nodo){
		Element elementoLadron = (Element)nodo;
		Element elementoCaracteristicas = (Element)nodo.getFirstChild();
		Caracteristicas caracteristicas = Caracteristicas.Hidratar(elementoCaracteristicas.getChildNodes().item(0));
		Ladron nuevoLadron = new Ladron(elementoLadron.getAttribute("Nombre"),caracteristicas);
		
		
		return nuevoLadron;
	}
}
