package modelo.caracteristicas;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Caracteristicas {
	
	private Sexo Sexo;
	private Hobby Hobby;
	private Cabello Cabello;
	private Senia Senia;
	private Vehiculo Vehiculo;
	

	public Caracteristicas(Sexo UnSexo,Hobby UnHobby,Cabello UnCabello,Senia UnaSenia,Vehiculo UnVehiculo){
		this.Sexo = UnSexo;
		this.Hobby = UnHobby;
		this.Cabello = UnCabello;
		this.Senia = UnaSenia;
		this.Vehiculo = UnVehiculo;
	}

	public Sexo getSexo() {
		return this.Sexo;
	}
	
	public Hobby getHobby() {
		return this.Hobby;
	}
	
	public Cabello getCabello() {
		return this.Cabello;
	}
	
	public Senia getSenia() {
		return this.Senia;
	}
	
	public Vehiculo getVehiculo() {
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

	public Node Serializar(Document doc) {
		Element elementoCaracteristica = doc.createElement("Caracteristicas");
		elementoCaracteristica.setAttribute("Sexo",this.Sexo);
		elementoCaracteristica.setAttribute("Senia", this.Senia);
		elementoCaracteristica.setAttribute("Hobby", this.Hobby);
		elementoCaracteristica.setAttribute("Cabello",this.Cabello);
		elementoCaracteristica.setAttribute("Vehiculo",this.Vehiculo);
		return elementoCaracteristica;
	}
	public static Caracteristicas Hidratar(Node elementoCaracteristica){
		Caracteristicas caracteristicas = new Caracteristicas(((Element)elementoCaracteristica).getAttribute("Sexo"),((Element)elementoCaracteristica).getAttribute("Hobby"),((Element)elementoCaracteristica).getAttribute("Cabello"),((Element)elementoCaracteristica).getAttribute("Senia"),((Element)elementoCaracteristica).getAttribute("Vehiculo"));
		return caracteristicas;
	}
	
	

}
