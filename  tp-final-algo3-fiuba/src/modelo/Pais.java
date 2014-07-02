package modelo;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;



import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Pais{
	
	private String nombre;
	private String informacion;
	private Edificio[] edificios;
	private Coordenadas coordenadas;
	
	public Pais(String unNombre,Edificio[] losEdificios,Coordenadas coordenadas){
		this.nombre = unNombre;
		this.edificios = losEdificios;
		this.coordenadas = coordenadas;
		this.informacion = "Informacion generica";
	}
	
	public String getNombre(){
		return nombre;
	}
	public Pais copiar(){
		Edificio[] edificiosCopiados = new Edificio[3];
		for (int i = 0;i<edificios.length;i++){
			edificiosCopiados[i] = edificios[i].copiar();
		}
		
		Pais copiaDelPais = new Pais(this.nombre,edificiosCopiados,this.coordenadas);
		copiaDelPais.setInformacion(this.informacion);
		return copiaDelPais;
	}
	
	public void setInformacion(String UnaInfo){
		informacion = UnaInfo;
	}
	


	public String getInformacion(){
		return informacion;
	}
	
	public Coordenadas getCoordenadas(){
		return this.coordenadas;
	}
	
	public int distanciaAPais(Pais otroPais){
		return (int)this.coordenadas.DistanciaA(otroPais.getCoordenadas());
	}
	
	
	
	
	public ArrayList<Edificio> getEdificios(){
		ArrayList<Edificio> ListaEdificios = new ArrayList<Edificio>();
		
		for (int i=0; i<edificios.length; i++){
			ListaEdificios.add( edificios[i]);
		}
		return ListaEdificios;
	}
	
	public Element serializar(Document doc){
		Element elementoPais = doc.createElement("Pais");
		elementoPais.setAttribute("Nombre", this.nombre);
		elementoPais.setAttribute("Informacion",this.informacion);
		Element elementoEdificios = doc.createElement("Edificios");
		elementoPais.appendChild(elementoEdificios);
		for (Edificio edificio: this.edificios){
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
		result = prime * result + Arrays.hashCode(edificios);
		result = prime * result
				+ ((informacion == null) ? 0 : informacion.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		if (!Arrays.equals(edificios, other.edificios))
			return false;
		if (informacion == null) {
			if (other.informacion != null)
				return false;
		} else if (!informacion.equals(other.informacion))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (coordenadas == null) {
			if (other.coordenadas != null)
				return false;
		} else if (!coordenadas.equals(other.coordenadas))
			return false;
		return true;
	}
}
