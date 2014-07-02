package modelo;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import modelo.excepcion.ErrorNoHayMasPaisesParaAvanzar;
public class Trayectoria {
	
	private Iterator<Pais> iteradorPaisActual;
	private Pais paisFinal;
	private Pais paisActual;
	private ArrayList<Pais> paises;

	public Trayectoria (ArrayList<Pais> paises){
		this.paises = paises;
		this.iteradorPaisActual = paises.iterator();
		this.paisActual = iteradorPaisActual.next();
		this.paisFinal = paises.get(paises.size() -1);
	}
	
	public Pais avanzar() throws ErrorNoHayMasPaisesParaAvanzar{
		if (iteradorPaisActual.hasNext()){
			paisActual = iteradorPaisActual.next();
			return paisActual;
		}
		else {
			throw new ErrorNoHayMasPaisesParaAvanzar();
		}
	}
	
	public Pais paisAnterior(){
		int indiceAnterior = paises.indexOf(paisActual) - 1; 
		Pais PaisAnterior = paises.get(indiceAnterior);
		return PaisAnterior;
	}
	
	public boolean estaEnTrayectoria(Pais unPais){
		
		return paises.contains(unPais);
	}
	
	public Pais paisActual(){
		return paisActual;
	}
	public Pais paisFinal(){
		return paisFinal;
	}
	public Element serializar(Document doc){
		Element elementoTrayectoria = doc.createElement("Trayectoria");
		for ( int i = 0;i < paises.size();i++){
			Element elementoPais = paises.get(i).serializar(doc);
			elementoTrayectoria.appendChild(elementoPais);
		}
		return elementoTrayectoria;
	}
	public static Trayectoria hidratar(Node nodo) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Element elementoTrayectoria= (Element)nodo;
		NodeList nodosPaises = elementoTrayectoria.getChildNodes();
		ArrayList<Pais> paises = new ArrayList<Pais>();
		for (int i = 0;i < nodosPaises.getLength();i++){
			paises.add(Pais.hidratar(nodosPaises.item(i)));
		}
		Trayectoria trayectoriaADevolver = new Trayectoria(paises);
		return trayectoriaADevolver;
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((paises == null) ? 0 : paises.hashCode());
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
		Trayectoria other = (Trayectoria) obj;
		if (paises == null) {
			if (other.paises != null)
				return false;
		} else if (!paises.equals(other.paises))
			return false;
		return true;
	}
}
