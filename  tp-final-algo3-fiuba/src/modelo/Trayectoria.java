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
	
	private Iterator<Pais> IteradorPaisActual;
	private Pais PaisFinal;
	private Pais PaisActual;
	private ArrayList<Pais> Paises;

	public Trayectoria (ArrayList<Pais> Paises){
		this.Paises = Paises;
		this.IteradorPaisActual = Paises.iterator();
		this.PaisActual = IteradorPaisActual.next();
		this.PaisFinal = Paises.get(Paises.size() -1);
	}
	
	public Pais avanzar() throws ErrorNoHayMasPaisesParaAvanzar{
		if (IteradorPaisActual.hasNext()){
			PaisActual = IteradorPaisActual.next();
			return PaisActual;
		}
		else {
			throw new ErrorNoHayMasPaisesParaAvanzar();
		}
	}
	
	public Pais paisAnterior(){
		int indiceAnterior = Paises.indexOf(PaisActual) - 1; 
		Pais PaisAnterior = Paises.get(indiceAnterior);
		return PaisAnterior;
	}
	
	public boolean estaEnTrayectoria(Pais UnPais){
		
		return Paises.contains(UnPais);
	}
	
	public Pais paisActual(){
		return PaisActual;
	}
	public Pais paisFinal(){
		return PaisFinal;
	}
	public Element serializar(Document doc){
		Element elementoTrayectoria = doc.createElement("Trayectoria");
		for ( int i = 0;i < Paises.size();i++){
			Element elementoPais = Paises.get(i).serializar(doc);
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
		result = prime * result + ((Paises == null) ? 0 : Paises.hashCode());
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
		if (Paises == null) {
			if (other.Paises != null)
				return false;
		} else if (!Paises.equals(other.Paises))
			return false;
		return true;
	}
}
