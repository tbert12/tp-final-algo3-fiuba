package modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

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

	public  Node serializar(Document doc) {
		Element elementoCoordenadas = doc.createElement("Coordenadas");
		elementoCoordenadas.setAttribute("Latitud",""+this.latitud);
		elementoCoordenadas.setAttribute("Longitud",""+this.longitud);
		
		return elementoCoordenadas;
	}
	public static Coordenadas hidratar(Node nodo){
		Element elementoCoordenadas = (Element)nodo;
		Coordenadas nuevasCoordenadas = new Coordenadas(Double.parseDouble(elementoCoordenadas.getAttribute("Latitud")),Double.parseDouble(elementoCoordenadas.getAttribute("Longitud")));
		return nuevasCoordenadas;
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(latitud);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitud);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordenadas other = (Coordenadas) obj;
		if (Double.doubleToLongBits(latitud) != Double
				.doubleToLongBits(other.latitud))
			return false;
		if (Double.doubleToLongBits(longitud) != Double
				.doubleToLongBits(other.longitud))
			return false;
		return true;
	}
	
}

	
