package modelo;

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
			if (otrasCaracteristicas.getSexo() != this.Sexo) return false;
		}
		if (otrasCaracteristicas.getHobby() != null){
			if (otrasCaracteristicas.getHobby() != this.Hobby) return false;
		}
		if (otrasCaracteristicas.getCabello() != null){
			if (otrasCaracteristicas.getCabello() != this.Cabello) return false;
		}
		if (otrasCaracteristicas.getSenia() != null){
			if (otrasCaracteristicas.getSenia() != this.Senia) return false;
		}
		if (otrasCaracteristicas.getVehiculo() != null){
			if (otrasCaracteristicas.getVehiculo() != this.Vehiculo) return false;
		}
		return true;
	}
	
	

}
