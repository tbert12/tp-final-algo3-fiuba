package modelo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import modelo.heridas.Herida;

public class Edificio {
	
	
	private String Pista,Nombre;
	private int VecesVisitado;
	private Herida Herida;
	private Ladron LadronContenido;
	
	public Edificio(String UnNombre){
		this.Nombre = UnNombre;
		Random random = new Random();
		String[] Pistas = {"Nunca he visto a la persona que dices","No he visto a nadie como dices","Jamas he visto a esa persona","Lo siento, no.","La verdad que no he visto a nadie que responda a eso"};
		this.Pista = Pistas[random.nextInt(Pistas.length-1)];
		this.VecesVisitado = 0;
		this.Herida = null;
		this.LadronContenido = null;
	}
	
	public Edificio copiar(){
		Edificio edificioCopia = new Edificio(this.Nombre);
		String pistaNueva = this.Pista;
		edificioCopia.setPista(pistaNueva);
		return edificioCopia;
	}
	public void setHerida(Herida UnaHerida){
		Herida = UnaHerida;
	}
	
	public void setLadron(Ladron unLadron){
		LadronContenido = unLadron;
	}
	public void setPista(String unaPista){
		this.Pista = unaPista;
	}

	public String visitar(Policia UnPolicia){
		this.VecesVisitado++;
		if (LadronContenido != null){
			if( UnPolicia.arrestarSospechoso(LadronContenido)) return "Ladron Atrapado";
			return "No se emitio orden de arresto, el ladron se escapo";
		}
		
		if (this.Herida == null){ 
			UnPolicia.reducirHorasalVisitar(VecesVisitado);
			return Pista;
		}
		else{
			Herida.herir(UnPolicia);
			return Herida.mensaje();
		}

	}
	public void setearVisitas(int nroVisitas){
		this.VecesVisitado=nroVisitas;
	}
	public String getNombre(){
		return Nombre;
	}

	public Element serializar(Document doc) {
		Element elementoEdificio = doc.createElement("Edificio");
		elementoEdificio.setAttribute("Nombre", this.Nombre);
		elementoEdificio.setAttribute("Pista",this.Pista);
		if (!(this.Herida == null)){
		Element elementoHerida = doc.createElement("Herida");
		elementoHerida.setAttribute("TipoHerida",this.Herida.getClass().getName());
		elementoHerida.setAttribute("Mensaje",this.Herida.mensaje());
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
		Edificio other = (Edificio) obj;
		if (Nombre == null) {
			if (other.Nombre != null)
				return false;
		} else if (!Nombre.equals(other.Nombre))
			return false;
		return true;
	}

	public boolean tieneLadron() {
		return (LadronContenido!=null);
	}

	public String getPista() {
		return this.Pista;
	}
}

