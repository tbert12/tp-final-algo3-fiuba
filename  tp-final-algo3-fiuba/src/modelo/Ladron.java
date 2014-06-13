package modelo;

public class Ladron {
	private Caracteristicas Caracteristicas;
	private Trayectoria Trayectoria;
	
	
	public Ladron(Caracteristicas caracteristicas){
		this.Caracteristicas = caracteristicas;
	}
	
	public void addTrayectoria(Trayectoria trayecto){
		this.Trayectoria = trayecto;
	}
	
	public String Sexo(){
		return Caracteristicas.getSexo();
	}
	
	public String Hobby(){
		return Caracteristicas.getHobby();
	}
	
	public String Cabello(){
		return Caracteristicas.getCabello();
	}
	
	public String Senia(){
		return Caracteristicas.getSenia();
	}
	
	public String Vehiculo(){
		return Caracteristicas.getVehiculo();
	}
	
	public Pais PaisFinal(){
		return Trayectoria.paisFinal();
	}
}
