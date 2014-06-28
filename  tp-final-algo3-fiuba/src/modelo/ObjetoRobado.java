package modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class ObjetoRobado {
	
	private String Valor,Nombre;
	
	public ObjetoRobado(String UnNombre,String UnValor){
		this.Nombre = UnNombre;
		this.Valor = UnValor;
	}
	
	public String getValor(){
		return Valor;
	}
	
	public String getNombre(){
		return this.Nombre;
	}
	
	public Element serializar(Document doc){
		Element elementoObjeto = doc.createElement("Objeto");
		elementoObjeto.setAttribute("Nombre",this.Nombre);
		elementoObjeto.setAttribute("Valor",this.Valor);
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
		result = prime * result + ((Nombre == null) ? 0 : Nombre.hashCode());
		result = prime * result + ((Valor == null) ? 0 : Valor.hashCode());
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
		if (Nombre == null) {
			if (other.Nombre != null)
				return false;
		} else if (!Nombre.equals(other.Nombre))
			return false;
		if (Valor == null) {
			if (other.Valor != null)
				return false;
		} else if (!Valor.equals(other.Valor))
			return false;
		return true;
	}

}
