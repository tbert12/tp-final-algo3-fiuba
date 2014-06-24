package modelo.caracteristicas;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Caracteristicas {
	
	private Sexo UnSexo;
	private Hobby UnHobby;
	private Cabello UnCabello;
	private Senia unaSenia;
	private Vehiculo UnVehiculo;
	

	public Caracteristicas(Sexo UnSexo,Hobby UnHobby,Cabello UnCabello,Senia UnaSenia,Vehiculo UnVehiculo){
		this.UnSexo = UnSexo;
		this.UnHobby = UnHobby;
		this.UnCabello = UnCabello;
		this.unaSenia = UnaSenia;
		this.UnVehiculo = UnVehiculo;
	}

	public Sexo getSexo() {
		return this.UnSexo;
	}
	
	public Hobby getHobby() {
		return this.UnHobby;
	}
	
	public Cabello getCabello() {
		return this.UnCabello;
	}
	
	public Senia getSenia() {
		return this.unaSenia;
	}
	
	public Vehiculo getVehiculo() {
		return this.UnVehiculo;
	}
	
	public boolean compararCon(Caracteristicas otrasCaracteristicas){
		if (otrasCaracteristicas.getSexo() != null){
			
			if (!otrasCaracteristicas.getSexo().equals(this.UnSexo))return false;
			
		}
		if (otrasCaracteristicas.getHobby() != null){
			if (!otrasCaracteristicas.getHobby().equals(this.UnHobby)) return false;
		
				
		}
		if (otrasCaracteristicas.getCabello() != null){
			if (!otrasCaracteristicas.getCabello().equals(this.UnCabello)) return false;
		}
		if (otrasCaracteristicas.getSenia() != null){
			if (!otrasCaracteristicas.getSenia().equals(this.unaSenia)) return false;
		}
		if (otrasCaracteristicas.getVehiculo() != null){
			if (!otrasCaracteristicas.getVehiculo().equals(this.UnVehiculo)) return false;
		}
		return true;
	}

	public Node serializar(Document doc) {
		
		String sexo = null,hobby = null,senia = null,cabello = null,vehiculo = null;
		if (this.UnSexo != null) sexo = this.UnSexo.getString();
		if (this.UnHobby != null) hobby = this.UnHobby.getString();
		if (this.UnCabello != null) cabello = this.UnCabello.getString();
		if (this.unaSenia != null) senia = this.unaSenia.getString();
		if (this.UnVehiculo != null) vehiculo = this.UnVehiculo.getString();
		
		Element elementoCaracteristica = doc.createElement("Caracteristicas");
		elementoCaracteristica.setAttribute("Sexo",sexo);
		elementoCaracteristica.setAttribute("Senia", senia);
		elementoCaracteristica.setAttribute("Hobby", hobby);
		elementoCaracteristica.setAttribute("Cabello",cabello);
		elementoCaracteristica.setAttribute("Vehiculo",vehiculo);
		return elementoCaracteristica;
	}
	public static Caracteristicas hidratar(Node elementoCaracteristica){
		Caracteristicas caracteristicas = new Caracteristicas(Sexo.fromString(((Element)elementoCaracteristica).getAttribute("Sexo")),Hobby.fromString(((Element)elementoCaracteristica).getAttribute("Hobby")),Cabello.fromString(((Element)elementoCaracteristica).getAttribute("Cabello")),Senia.fromString(((Element)elementoCaracteristica).getAttribute("Senia")),Vehiculo.fromString(((Element)elementoCaracteristica).getAttribute("Vehiculo")) );
		return caracteristicas;
	}
	
	

}
