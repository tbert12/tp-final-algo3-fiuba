package modelo;

public class Ladron {
	private String Nombre,Sexo,Hobby,Cabello,Senia,Vehiculo;
	private Trayectoria Trayectoria;
	
	
	public Ladron(String UnSexo,String UnHobby,String UnCabello,String UnaSenia,String UnVehiculo){
		//Tenemos que ver como seteamos esto, y si seran objetos.
		this.Sexo = UnSexo;
		this.Hobby = UnHobby;
		this.Cabello = UnCabello;
		this.Senia = UnaSenia;
		this.Vehiculo = UnVehiculo;
	}
	
	public void addTrayectoria(Trayectoria trayecto){
		this.Trayectoria = trayecto;
	}
	
	public String toString(){
		return Nombre;
	}
	
	public String Sexo(){
		return this.Sexo;
	}
	
	public String Hobby(){
		return this.Hobby;
	}
	
	public String Cabello(){
		return this.Cabello;
	}
	
	public String Senia(){
		return this.Senia;
	}
	
	public String Vehiculo(){
		return this.Vehiculo;
	}
	
	public Pais PaisFinal(){
		return Trayectoria.paisFinal();
	}
}
