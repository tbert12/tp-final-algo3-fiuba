package modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Caracteristicas {
	
	private String Sexo,Hobby,Cabello,Senia,Vehiculo;

	public Caracteristicas(String Sexo,String Hobby,String Cabello,String Senia,String Vehiculo){
		this.Sexo = Sexo;
		this.Hobby = Hobby;
		this.Cabello = Cabello;
		this.Senia = Senia;
		this.Vehiculo = Vehiculo;
	}

	public String getSexo() {
		return this.Sexo;
	}
	
	public String getHobby() {
		return this.Hobby;
	}
	
	public String getCabello() {
		return this.Cabello;
	}
	
	public String getSenia() {
		return this.Senia;
	}
	
	public String getVehiculo() {
		return this.Vehiculo;
	}
	
	public boolean CompararCon(Caracteristicas otrasCaracteristicas){
		if (otrasCaracteristicas.getSexo() != null){
			
			if (!otrasCaracteristicas.getSexo().equals(this.Sexo))return false;
			
		}
		if (otrasCaracteristicas.getHobby() != null){
			if (!otrasCaracteristicas.getHobby().equals(this.Hobby)) return false;
		
				
		}
		if (otrasCaracteristicas.getCabello() != null){
			if (!otrasCaracteristicas.getCabello().equals(this.Cabello)) return false;
		}
		if (otrasCaracteristicas.getSenia() != null){
			if (!otrasCaracteristicas.getSenia().equals(this.Senia)) return false;
		}
		if (otrasCaracteristicas.getVehiculo() != null){
			if (!otrasCaracteristicas.getVehiculo().equals(this.Vehiculo)) return false;
		}
		return true;
	}

	public Node serializar(Document doc) {
		Element elementoCaracteristica = doc.createElement("Caracteristicas");
		elementoCaracteristica.setAttribute("Sexo",this.Sexo);
		elementoCaracteristica.setAttribute("Senia", this.Senia);
		elementoCaracteristica.setAttribute("Hobby", this.Hobby);
		elementoCaracteristica.setAttribute("Cabello",this.Cabello);
		elementoCaracteristica.setAttribute("Vehiculo",this.Vehiculo);
		return elementoCaracteristica;
	}
	public static Caracteristicas hidratar(Node elementoCaracteristica){
		Caracteristicas caracteristicas = new Caracteristicas(((Element)elementoCaracteristica).getAttribute("Sexo"),((Element)elementoCaracteristica).getAttribute("Hobby"),((Element)elementoCaracteristica).getAttribute("Cabello"),((Element)elementoCaracteristica).getAttribute("Senia"),((Element)elementoCaracteristica).getAttribute("Vehiculo"));
		return caracteristicas;
	}
	
	

}
