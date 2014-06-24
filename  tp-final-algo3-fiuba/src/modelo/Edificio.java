package modelo;

public class Edificio {
	
	
	private String Pista,Nombre;
	private int VecesVisitados;
	
	public Edificio(String UnNombre, String UnaPista){
		this.Nombre = UnNombre;
		this.Pista = UnaPista;
		this.VecesVisitados = 0;
	}
	
	public String getPista(){
		this.VecesVisitados++;
		return Pista;
	}
	public String getNombre(){
		return Nombre;
	}
	public int vecesVisitado(){
		return this.VecesVisitados;
	}
}

