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
	private boolean Arrestado;
	
	public Ladron(String Nombre,Caracteristicas caracteristicas){
		this.Nombre = Nombre;
		this.CaracteristicasDelLadron = caracteristicas;
		this.Arrestado = false;
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
	
	public Pais paisFinal(){
		return Trayectoria.paisFinal();
	}
	public boolean compararCaracteristicas(Caracteristicas otrasCaracteriscas){
		return CaracteristicasDelLadron.compararCon(otrasCaracteriscas);
	}
	
	
	public Pais paisActual(){
		return this.Trayectoria.paisActual();
	}
	
	public Pais paisAnterior(){
		return Trayectoria.paisAnterior();
	}
	
	public boolean pasaPor(Pais pais){
		return this.Trayectoria.estaEnTrayectoria(pais);
	}

	public Pais avanzar() {
		try{
			return this.Trayectoria.avanzar();
		}
		catch (ErrorNoHayMasPaisesParaAvanzar e){
			//Devolver un pais anterior al actual, lo necesito en Base
			return paisAnterior();
		}
	}
	public void arrestar(){
		Arrestado = true;
	}
	
	public boolean estaArrestado(){
		return Arrestado;
	}
	public Element serializar(Document doc){
		Element elementoLadron = doc.createElement("Ladron");
		Element elementoCaracteristicas = doc.createElement("Caracteristica");
		elementoLadron.appendChild(elementoCaracteristicas);
		elementoCaracteristicas.appendChild(this.CaracteristicasDelLadron.serializar(doc));
		elementoLadron.setAttribute("Nombre",this.Nombre);
		return elementoLadron;
	}
	public static Ladron hidratar(Node nodo){
		Element elementoLadron = (Element)nodo;
		Element elementoCaracteristicas = (Element)nodo.getFirstChild();
		Caracteristicas caracteristicas = Caracteristicas.hidratar(elementoCaracteristicas.getChildNodes().item(0));
		Ladron nuevoLadron = new Ladron(elementoLadron.getAttribute("Nombre"),caracteristicas);
		
		
		return nuevoLadron;
	}
}
