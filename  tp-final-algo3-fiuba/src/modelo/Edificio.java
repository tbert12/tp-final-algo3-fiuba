package modelo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import modelo.heridas.Herida;

public class Edificio {
	
	
	private String pista,nombre;
	private int vecesVisitado;
	private Herida herida;
	private Ladron ladronContenido;
	
	public Edificio(String unNombre){
		this.nombre = unNombre;
		Random random = new Random();
		String[] pistas = {"Nunca he visto a la persona que dices","No he visto a nadie como dices","Jamas he visto a esa persona","Lo siento, no.","La verdad que no he visto a nadie que responda a eso"};
		this.pista = pistas[random.nextInt(pistas.length-1)];
		this.vecesVisitado = 0;
		this.herida = null;
		this.ladronContenido = null;
	}
	
	public Edificio copiar(){
		Edificio edificioCopia = new Edificio(this.nombre);
		String pistaNueva = this.pista;
		edificioCopia.setPista(pistaNueva);
		return edificioCopia;
	}
	public void setHerida(Herida unaHerida){
		herida = unaHerida;
	}
	
	public void setLadron(Ladron unLadron){
		ladronContenido = unLadron;
	}
	public void setPista(String unaPista){
		this.pista = unaPista;
	}

	public String visitar(Policia unPolicia){
		this.vecesVisitado++;
		if (ladronContenido != null){
			if( unPolicia.arrestarSospechoso(ladronContenido)) return "Ladron Atrapado";
			return "No se emitio orden de arresto, el ladron se escapo";
		}
		
		if (this.herida == null){ 
			unPolicia.reducirHorasalVisitar(vecesVisitado);
			return pista;
		}
		else{
			herida.herir(unPolicia);
			return herida.mensaje();
		}

	}
	public void setearVisitas(int nroVisitas){
		this.vecesVisitado=nroVisitas;
	}
	public String getNombre(){
		return nombre;
	}

	public Element serializar(Document doc) {
		Element elementoEdificio = doc.createElement("Edificio");
		elementoEdificio.setAttribute("Nombre", this.nombre);
		elementoEdificio.setAttribute("Pista",this.pista);
		if (!(this.herida == null)){
		Element elementoHerida = doc.createElement("Herida");
		elementoHerida.setAttribute("TipoHerida",this.herida.getClass().getName());
		elementoHerida.setAttribute("Mensaje",this.herida.mensaje());
		elementoEdificio.appendChild(elementoHerida);
		}
		return elementoEdificio;
	}
	public static Edificio hidratar(Node nodo) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Element elementoEdificio = (Element) nodo;
		Element elementoHerida = (Element) elementoEdificio.getFirstChild();
		Edificio edificioADevolver = new Edificio(elementoEdificio.getAttribute("Nombre"));
		//edificioADevolver.setPista(elementoEdificio.getAttribute("Pista"));
		try{
		String tipoHerida=elementoHerida.getAttribute("TipoHerida");
		Class<?> ClaseHerida = Class.forName(tipoHerida);
		Constructor<?> ConstructorHerida = ClaseHerida.getConstructor(String.class);
		Object unaHeridaObject = ConstructorHerida.newInstance(elementoHerida.getAttribute("Mensaje"));
		edificioADevolver.setHerida((modelo.heridas.Herida) ClaseHerida.cast(unaHeridaObject));
		}
		catch(NullPointerException e){
			return edificioADevolver;
		}
		
		
		return edificioADevolver;
		
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
		Edificio other = (Edificio) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	public boolean tieneLadron() {
		return (ladronContenido!=null);
	}

	public String getPista() {
		return this.pista;
	}
}

