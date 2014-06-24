package modelo;

public class Tiempo {
	
	private int Horas;

	public Tiempo(int CantidadDeHoras){
		Horas = CantidadDeHoras;
	}
	public void reducirHoras(int CantidadDeHoras){
		Horas = Horas - CantidadDeHoras;
	}
	public int getHoras(){
		return this.Horas;
	}
	
	public boolean tiempoAgotado(){
		if (Horas <= 0 ){ 
			return true;}
		return false;
		
	}
}
