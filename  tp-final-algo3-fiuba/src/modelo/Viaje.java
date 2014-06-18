package modelo;

public class Viaje {
	
	public int viajarHacia(Policia UnPolicia,Pais PaisDestino){
		Pais PaisActual = UnPolicia.getPais();
		
		int kilometrosAViajar = PaisActual.DistanciaAPais(PaisDestino.getNombre()); //Calculo de kilometros entre paises
		
		int tiempoDeViaje = UnPolicia.CostoDeViaje(kilometrosAViajar);
		
		UnPolicia.setPaisActual(PaisDestino);
		return tiempoDeViaje;
		
	}
}
