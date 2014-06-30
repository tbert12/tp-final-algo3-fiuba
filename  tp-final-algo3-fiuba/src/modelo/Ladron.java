package modelo;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import modelo.caracteristicas.*;
import modelo.excepcion.ErrorNoHayMasPaisesParaAvanzar;

public class Ladron {
	private Caracteristicas CaracteristicasDelLadron;
	private String Nombre;
	private Trayectoria TrayectoriaDelLadron;
	private boolean Arrestado;
	
	public Ladron(String Nombre,Caracteristicas caracteristicas){
		this.Nombre = Nombre;
		this.CaracteristicasDelLadron = caracteristicas;
		this.Arrestado = false;
	}
	
	public void addTrayectoria(Trayectoria trayecto){
		this.TrayectoriaDelLadron = trayecto;
		//El ladron se va a esconder en un edificio del trayecto final
		Random random = new Random();
		Pais paisQueElLadronseOculta = trayecto.paisFinal();
		ArrayList<Edificio> EdificiosdePaisFinal = paisQueElLadronseOculta.getEdificios();
		int cantidaDeEdificios = EdificiosdePaisFinal.size();
		Edificio edificioRandomParaEsconderse = EdificiosdePaisFinal.get(random.nextInt(cantidaDeEdificios));
		edificioRandomParaEsconderse.setLadron();
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
		return TrayectoriaDelLadron.paisFinal();
	}
	public boolean compararCaracteristicas(Caracteristicas otrasCaracteriscas){
		return CaracteristicasDelLadron.compararCon(otrasCaracteriscas);
	}
	
	
	public Pais paisActual(){
		return this.TrayectoriaDelLadron.paisActual();
	}
	
	public Pais paisAnterior(){
		return TrayectoriaDelLadron.paisAnterior();
	}
	
	public boolean pasaPor(Pais pais){
		return this.TrayectoriaDelLadron.estaEnTrayectoria(pais);
	}

	public Pais avanzar() {
		try{
			return this.TrayectoriaDelLadron.avanzar();
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
		//Element elementoTrayectoria = doc.createElement("Trayectoria");
		//elementoTrayectoria.appendChild(this.TrayectoriaDelLadron.serializar(doc));
		elementoLadron.appendChild(elementoCaracteristicas);
		//elementoLadron.appendChild(elementoTrayectoria);
		elementoCaracteristicas.appendChild(this.CaracteristicasDelLadron.serializar(doc));
		elementoLadron.setAttribute("Nombre",this.Nombre);
		return elementoLadron;
	}
	public static Ladron hidratar(Node nodo) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Element elementoLadron = (Element)nodo;
		Caracteristicas caracteristicas = Caracteristicas.hidratar(elementoLadron.getElementsByTagName("Caracteristicas").item(0));
		//Trayectoria trayectoria = Trayectoria.hidratar(nodo.getChildNodes().item(1));
		Ladron nuevoLadron = new Ladron(elementoLadron.getAttribute("Nombre"),caracteristicas);
		//nuevoLadron.addTrayectoria(trayectoria);
		return nuevoLadron;
	}
}
