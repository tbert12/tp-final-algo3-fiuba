package modelo;

public class Caracteristicas {
	
	private String Nombre,Sexo,Hobby,Cabello,Senia,Vehiculo;

	public Caracteristicas(String Nombre,String Sexo,String Hobby,String Cabello,String Senia,String Vehiculo){
		this.Nombre = Nombre;
		this.Sexo = Sexo;
		this.Hobby = Hobby;
		this.Cabello = Cabello;
		this.Senia = Senia;
		this.Vehiculo = Vehiculo;
	}
	
	public String getNombre() {
		return this.Nombre;
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
	
	

}
