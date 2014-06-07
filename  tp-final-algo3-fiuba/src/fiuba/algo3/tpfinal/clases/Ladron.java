package fiuba.algo3.tpfinal.clases;

public class Ladron {
	private String sexo,hobby,cabello,senia,vehiculo;
	
	
	public Ladron(String UnSexo,String UnHobby,String UnCabello,String UnaSenia,String UnVehiculo){
		//Tenemos que ver como seteamos esto, y si seran objetos.
		this.sexo = UnSexo;
		this.hobby = UnHobby;
		this.cabello = UnCabello;
		this.senia = UnaSenia;
		this.vehiculo = UnVehiculo;
	}
	
	public String Sexo(){
		return this.sexo;
	}
	
}
