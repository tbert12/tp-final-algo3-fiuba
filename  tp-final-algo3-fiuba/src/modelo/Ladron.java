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
	private Caracteristicas caracteristicasDelLadron;
	private String nombre;
	private Trayectoria trayectoriaDelLadron;
	private boolean arrestado;
	
	public Ladron(String nombre,Caracteristicas caracteristicas){
		this.nombre = nombre;
		this.caracteristicasDelLadron = caracteristicas;
		this.arrestado = false;
	}
	
	public Ladron copiar(){
		Ladron ladronCopiado = new Ladron(this.nombre,this.caracteristicasDelLadron);
		return ladronCopiado;
	}
	public void addTrayectoria(Trayectoria trayecto){
		this.trayectoriaDelLadron = trayecto;
		//El ladron se va a esconder en un edificio del trayecto final
		Random random = new Random();
		Pais paisQueElLadronseOculta = trayecto.paisFinal();
		ArrayList<Edificio> EdificiosdePaisFinal = paisQueElLadronseOculta.getEdificios();
		int cantidaDeEdificios = EdificiosdePaisFinal.size();
		Edificio edificioRandomParaEsconderse = EdificiosdePaisFinal.get(random.nextInt(cantidaDeEdificios));
		edificioRandomParaEsconderse.setLadron(this);
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public Sexo getSexo(){
		return caracteristicasDelLadron.getSexo();
	}
	
	public Hobby getHobby(){
		return caracteristicasDelLadron.getHobby();
	}
	
	public Cabello getCabello(){
		return caracteristicasDelLadron.getCabello();
	}
	
	public Senia getSenia(){
		return caracteristicasDelLadron.getSenia();
	}
	
	public Vehiculo getVehiculo(){
		return caracteristicasDelLadron.getVehiculo();
	}
	
	public Pais paisFinal(){
		return trayectoriaDelLadron.paisFinal();
	}
	public boolean compararCaracteristicas(Caracteristicas otrasCaracteriscas){
		return caracteristicasDelLadron.compararCon(otrasCaracteriscas);
	}
	
	
	public Pais paisActual(){
		return this.trayectoriaDelLadron.paisActual();
	}
	
	public Pais paisAnterior(){
		return trayectoriaDelLadron.paisAnterior();
	}
	
	public boolean pasaPor(Pais pais){
		return this.trayectoriaDelLadron.estaEnTrayectoria(pais);
	}

	public Pais avanzar() {
		try{
			return this.trayectoriaDelLadron.avanzar();
		}
		catch (ErrorNoHayMasPaisesParaAvanzar e){
			
			return paisAnterior();
		}
	}
	public void arrestar(){
		arrestado = true;
	}
	
	public boolean estaArrestado(){
		return arrestado;
	}
	public Element serializar(Document doc){
		Element elementoLadron = doc.createElement("Ladron");
		Element elementoCaracteristicas = doc.createElement("Caracteristica");
		//Element elementoTrayectoria = doc.createElement("Trayectoria");
		//elementoTrayectoria.appendChild(this.TrayectoriaDelLadron.serializar(doc));
		elementoLadron.appendChild(elementoCaracteristicas);
		//elementoLadron.appendChild(elementoTrayectoria);
		elementoCaracteristicas.appendChild(this.caracteristicasDelLadron.serializar(doc));
		elementoLadron.setAttribute("Nombre",this.nombre);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		Ladron other = (Ladron) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	public void reiniciar() {
		this.arrestado = false;
		
	}
}
