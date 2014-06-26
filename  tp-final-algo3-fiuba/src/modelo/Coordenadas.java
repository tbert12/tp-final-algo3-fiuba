package modelo;

public class Coordenadas {
	private double latitud;
	private double longitud;
	
	//Un grado de latitud equivale a 111 kilometros
	private static double enKilometros = 111; 

	public Coordenadas(double unaLatitud,double unaLongitud){
		this.latitud = unaLatitud;
		this.longitud = unaLongitud;
	}
	
	public double getLatitud(){
		return latitud;
	}
	
	public double getLongitud(){
		return longitud;
	}

	public int DistanciaA(Coordenadas otrasCoordenadas){
		//Formula de Pitagoras y suponemos Tierra plana
		double latitudTotal = Math.pow(this.latitud - otrasCoordenadas.getLatitud(), 2);
		double longitudTotal = Math.pow(this.longitud - otrasCoordenadas.getLongitud(), 2);
		double distancia = Math.sqrt(latitudTotal + longitudTotal);
		return (int)(distancia*enKilometros);
		
	}
}

	
