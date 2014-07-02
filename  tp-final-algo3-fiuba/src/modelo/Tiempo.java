package modelo;

public class Tiempo {
	
	private int horas;

	public Tiempo(int cantidadDeHoras){
		horas = cantidadDeHoras;
	}
	public void reducirHoras(int cantidadDeHoras){
		horas = horas - cantidadDeHoras;
	}
	public int getHoras(){
		return this.horas;
	}
	
	public boolean tiempoAgotado(){
		if (horas <= 0 ){ 
			return true;}
		return false;
		
	}
}
