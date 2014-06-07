package modelo;

public class Pais {
	
	private String Nombre;
	private Edificio[] Edificios;
	
	public Pais(String UnNombre,Edificio[] LosEdificios){
		this.Nombre = UnNombre;
		this.Edificios = LosEdificios;
	}
	
	public Edificio getEdificio(int indice){
		return Edificios[indice];
	}
	public String Nombre(){
		return Nombre;
	}

}
