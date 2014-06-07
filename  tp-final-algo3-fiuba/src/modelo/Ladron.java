package modelo;

public class Ladron {
	private String Sexo,Hobby,Cabello,Senia,Vehiculo;
	
	
	public Ladron(String UnSexo,String UnHobby,String UnCabello,String UnaSenia,String UnVehiculo){
		//Tenemos que ver como seteamos esto, y si seran objetos.
		this.Sexo = UnSexo;
		this.Hobby = UnHobby;
		this.Cabello = UnCabello;
		this.Senia = UnaSenia;
		this.Vehiculo = UnVehiculo;
	}
	
	public String Sexo(){
		return this.Sexo;
	}
	
}
