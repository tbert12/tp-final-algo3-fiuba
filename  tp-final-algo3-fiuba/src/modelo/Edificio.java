package modelo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import modelo.heridas.Herida;

public class Edificio {
	
	
	private String Pista,Nombre;
	private int VecesVisitado;
	private Herida Herida;
	private boolean ContieneLadron;
	
	public Edificio(String UnNombre, String UnaPista){
		this.Nombre = UnNombre;
		this.Pista = UnaPista;
		this.VecesVisitado = 0;
		this.Herida = null;
		this.ContieneLadron = false;
	}
	
	public void setHerida(Herida UnaHerida){
		Herida = UnaHerida;
	}
	
	public void setLadron(){
		ContieneLadron = true;
	}

	public String visitar(Policia UnPolicia){
		this.VecesVisitado++;
		if (ContieneLadron){
			if( UnPolicia.arrestarSospechoso()) return "Ladron Atrapado";
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
		elementoEdificio.setAttribute("VecesVisitado",""+VecesVisitado);
		elementoEdificio.setAttribute("ContieneLadron",String.valueOf(ContieneLadron));
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
		Edificio edificioADevolver = new Edificio(elementoEdificio.getAttribute("Nombre"),elementoEdificio.getAttribute("Pista"));
		edificioADevolver.setearVisitas(Integer.parseInt(elementoEdificio.getAttribute("VecesVisitado")));
		if (Boolean.valueOf(elementoEdificio.getAttribute("ContieneLadron"))){
			edificioADevolver.setLadron();
		}
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
		result = prime * result + (ContieneLadron ? 1231 : 1237);
		result = prime * result + ((Herida == null) ? 0 : Herida.hashCode());
		result = prime * result + ((Nombre == null) ? 0 : Nombre.hashCode());
		result = prime * result + ((Pista == null) ? 0 : Pista.hashCode());
		result = prime * result + VecesVisitado;
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
		if (ContieneLadron != other.ContieneLadron)
			return false;
		if (Herida == null) {
			if (other.Herida != null)
				return false;
		} else if (!Herida.equals(other.Herida))
			return false;
		if (Nombre == null) {
			if (other.Nombre != null)
				return false;
		} else if (!Nombre.equals(other.Nombre))
			return false;
		if (Pista == null) {
			if (other.Pista != null)
				return false;
		} else if (!Pista.equals(other.Pista))
			return false;
		if (VecesVisitado != other.VecesVisitado)
			return false;
		return true;
	}
}

