package modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Coordenadas {
	private double latitud;
	private double longitud;
	
	//Un grado de latitud equivale a 111 kilometros
	private static double enKilometros = 1.6093; 

	public Coordenadas(double unaLatitud,double unaLongitud){
		this.latitud = unaLatitud;
		this.longitud = unaLongitud;
	}
	public double Haversine(Coordenadas otrasCoords) {
        final int R = 6371; 
        Double lat1 = this.latitud;
        Double lon1 = this.longitud;
        Double lat2 = otrasCoords.getLatitud();
        Double lon2 = otrasCoords.getLongitud();
        Double latDistance = toRad(lat2-lat1);
        Double lonDistance = toRad(lon2-lon1);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                   Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) *
                   Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        Double distance = R * c;
        return distance; 
  
 
    }
     
    private static Double toRad(Double value) {
        return value * Math.PI / 180;
    }
	public double getLatitud(){
		return latitud;
	}
	
	public double getLongitud(){
		return longitud;
	}

	public int DistanciaA(Coordenadas otrasCoordenadas){
		double distancia = Haversine(otrasCoordenadas);
		System.out.println(distancia);
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

	
