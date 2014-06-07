package modelo;

public class Tiempo {
	
	private int Horas;

	public Tiempo(int CantidadDeHoras){
		Horas = CantidadDeHoras;
	}
	public void ReducirHoras(int CantidadDeHoras){
		Horas = Horas - CantidadDeHoras;
	}
	public boolean TiempoAgotado(){
		if (Horas <= 0 ){ 
			return true;}
		return false;
		
	}
}
