package modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class ObjetoRobado {
	
	private String valor,nombre;
	
	public ObjetoRobado(String unNombre,String unValor){
		this.nombre = unNombre;
		this.valor = unValor;
	}
	
	public String getValor(){
		return valor;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public Element serializar(Document doc){
		Element elementoObjeto = doc.createElement("Objeto");
		elementoObjeto.setAttribute("Nombre",this.nombre);
		elementoObjeto.setAttribute("Valor",this.valor);
		return elementoObjeto;
		
	}
	public static ObjetoRobado hidratar(Node nodo){
		Element elementoObjeto = (Element)nodo;
		ObjetoRobado objetoADevolver = new ObjetoRobado(elementoObjeto.getAttribute("Nombre"),elementoObjeto.getAttribute("Valor"));
		return objetoADevolver;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		ObjetoRobado other = (ObjetoRobado) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

}
