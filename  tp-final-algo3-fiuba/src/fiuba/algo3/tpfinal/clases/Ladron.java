package fiuba.algo3.tpfinal.clases;

public class Ladron {
	private String sexo,hobby,cabello,senia,vehiculo;
	
	public Ladron(String UnSexo,String UnHobby,String UnCabello,String UnaSenia,String UnVehiculo){
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
