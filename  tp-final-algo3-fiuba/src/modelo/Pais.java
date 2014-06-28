package modelo;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import modelo.excepcion.ErrorEdificioNoEstaEnPais;
import modelo.excepcion.ErrorEdificioNoEstaEnPais;

@SuppressWarnings("unused")
public class Pais {
	
	private String Nombre;
	private String Informacion;
	private Edificio[] Edificios;
	private HashMap<String,Integer> DistanciaAPaises;
	private Coordenadas coordenadas;
	
	public Pais(String UnNombre,Edificio[] LosEdificios,Coordenadas coordenadas){
		this.Nombre = UnNombre;
		this.Edificios = LosEdificios;
		this.coordenadas = coordenadas;
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
	
	
	/* Metodos que utilizan Diccionario
	public int distanciaAPais(String unPais){
		return DistanciaAPaises.get(unPais);
	}
	
	*/
	public void setDistancias(HashMap<String,Integer> unDicc){
		DistanciaAPaises = unDicc;
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
		Element elementoDistancias=doc.createElement("Distancias");
		elementoPais.appendChild(elementoDistancias);
		int i = 0;
		for (Map.Entry<String, Integer> distanciaEntry : DistanciaAPaises.entrySet()){
			Element elementoDistancia = doc.createElement("Par"+i);
			elementoDistancia.setAttribute("Key", distanciaEntry.getKey());
			elementoDistancia.setAttribute("Value",String.valueOf(distanciaEntry.getValue()));
			elementoDistancias.appendChild(elementoDistancia);
			i++;
		}
		return elementoPais;
		
	}
	public static Pais hidratar(Node nodo) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Element elementoPais = (Element) nodo;
		Node elementoEdificios = elementoPais.getChildNodes().item(0);
		Edificio[] edificios = new Edificio[3];
		NodeList NodosEdificios = elementoEdificios.getChildNodes();
		for (int i=0;i<NodosEdificios.getLength();i++){
			Edificio unEdificio = Edificio.hidratar(NodosEdificios.item(i));
			edificios[i]=unEdificio;
		}
		Node elementoCoordenadas = elementoPais.getChildNodes().item(1);
		Coordenadas coordenadasDelPais = Coordenadas.hidratar(elementoCoordenadas.getFirstChild());
		Node elementoDistancias = elementoPais.getChildNodes().item(2);
		NodeList nodosDistancias= elementoDistancias.getChildNodes();
		HashMap<String,Integer> DistanciaAPaises = new HashMap<>();
		for (int i = 0;i<nodosDistancias.getLength();i++){
			Element elementoDistancia = (Element)nodosDistancias.item(i);
			DistanciaAPaises.put(elementoDistancia.getAttribute("Key"), Integer.parseInt(elementoDistancia.getAttribute("Value")));
		}
		Pais paisADevolver = new Pais(elementoPais.getAttribute("Nombre"),edificios,coordenadasDelPais);
		paisADevolver.setInformacion(elementoPais.getAttribute("Informacion"));
		paisADevolver.setDistancias(DistanciaAPaises);
		return paisADevolver;
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((DistanciaAPaises == null) ? 0 : DistanciaAPaises.hashCode());
		result = prime * result + Arrays.hashCode(Edificios);
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
		if (DistanciaAPaises == null) {
			if (other.DistanciaAPaises != null)
				return false;
		} else if (!DistanciaAPaises.equals(other.DistanciaAPaises))
			return false;
		if (!Arrays.equals(Edificios, other.Edificios))
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
