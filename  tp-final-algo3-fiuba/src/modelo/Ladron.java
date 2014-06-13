package modelo;

public class Ladron {
	private Caracteristicas Caracteristicas;
	private String Nombre;
	private Trayectoria Trayectoria;
	
	
	public Ladron(String Nombre,Caracteristicas caracteristicas){
		this.Nombre = Nombre;
		this.Caracteristicas = caracteristicas;
	}
	
	public void addTrayectoria(Trayectoria trayecto){
		this.Trayectoria = trayecto;
	}
	
	
	public String getNombre(){
		return this.Nombre;
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
	public boolean CompararCaracteristicas(Caracteristicas otrasCaracteriscas){
		return Caracteristicas.CompararCon(otrasCaracteriscas);
	}
}
