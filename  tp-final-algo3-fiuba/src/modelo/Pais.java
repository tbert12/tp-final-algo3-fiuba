package modelo;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Pais {
	
	private String Nombre;
	private String Informacion;
	private Edificio[] Edificios;
	private Coordenadas coordenadas;
	
	public Pais(String UnNombre,Edificio[] LosEdificios,Coordenadas coordenadas){
		this.Nombre = UnNombre;
		this.Edificios = LosEdificios;
		this.coordenadas = coordenadas;
		this.Informacion = "Informacion generica";
	}
	
	public String getNombre(){
		return Nombre;
	}
	
	//Capas Es mejor recibirlo por parametro cuando lo inicializamos.
	public void setInformacion(String UnaInfo){
		Informacion = UnaInfo;
	}
	
	public String getInformacion(){
		return Informacion;
	}
	
	public Coordenadas getCoordenadas(){
		return this.coordenadas;
	}
	
	public int distanciaAPais(Pais otroPais){
		return (int)this.coordenadas.DistanciaA(otroPais.getCoordenadas());
	}
	
	
	
	
	public ArrayList<Edificio> getEdificios(){
		ArrayList<Edificio> ListaEdificios = new ArrayList<Edificio>();
		
		for (int i=0; i<Edificios.length; i++){
			ListaEdificios.add( Edificios[i]);
		}
		return ListaEdificios;
	}
	
	public Element serializar(Document doc){
		Element elementoPais = doc.createElement("Pais");
		elementoPais.setAttribute("Nombre", this.Nombre);
		elementoPais.setAttribute("Informacion",this.Informacion);
		Element elementoEdificios = doc.createElement("Edificios");
		elementoPais.appendChild(elementoEdificios);
		for (Edificio edificio: this.Edificios){
			elementoEdificios.appendChild(edificio.serializar(doc));
		}
		Element elementoCoordenadas=doc.createElement("Coordenadas");
		elementoPais.appendChild(elementoCoordenadas);
		elementoCoordenadas.appendChild(this.coordenadas.serializar(doc));
		return elementoPais;
		
	}
	public static Pais hidratar(Node nodo) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Element elementoPais = (Element) nodo;
		Edificio[] edificios = new Edificio[3];
		NodeList nodosEdificios = elementoPais.getElementsByTagName("Edificios");
		for (int i=0;i<nodosEdificios.getLength();i++){
			 Element elementoEdificiosPais = (Element) nodosEdificios.item(i);
			 NodeList nodosEdificio=elementoEdificiosPais.getElementsByTagName("Edificio");
			for(int j=0;j<3;j++){
				Edificio unEdificio = Edificio.hidratar(nodosEdificio.item(j));
				edificios[j]=unEdificio;
			}
		}
		Element elementoCoordenadas = (Element)elementoPais.getElementsByTagName("Coordenadas").item(0);
		Node nodoCoordenadas = elementoCoordenadas.getElementsByTagName("Coordenadas").item(0);
		Coordenadas coordenadasDelPais = Coordenadas.hidratar(nodoCoordenadas);
		Pais paisADevolver = new Pais(elementoPais.getAttribute("Nombre"),edificios,coordenadasDelPais);
		paisADevolver.setInformacion(elementoPais.getAttribute("Informacion"));
		return paisADevolver;
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(Edificios);
		result = prime * result
				+ ((Informacion == null) ? 0 : Informacion.hashCode());
		result = prime * result + ((Nombre == null) ? 0 : Nombre.hashCode());
		result = prime * result
				+ ((coordenadas == null) ? 0 : coordenadas.hashCode());
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
		Pais other = (Pais) obj;
		if (!Arrays.equals(Edificios, other.Edificios))
			return false;
		if (Informacion == null) {
			if (other.Informacion != null)
				return false;
		} else if (!Informacion.equals(other.Informacion))
			return false;
		if (Nombre == null) {
			if (other.Nombre != null)
				return false;
		} else if (!Nombre.equals(other.Nombre))
			return false;
		if (coordenadas == null) {
			if (other.coordenadas != null)
				return false;
		} else if (!coordenadas.equals(other.coordenadas))
			return false;
		return true;
	}
}
