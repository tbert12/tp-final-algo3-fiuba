package modelo;

public class Edificio {
	
	
	private String Pista,Nombre;
	
	public Edificio(String UnNombre, String UnaPista){
		this.Nombre = UnNombre;
		this.Pista = UnaPista;
	}
	
	public String getPista(){
		return Pista;
	}
	public String getNombre(){
		return Nombre;
	}
}

