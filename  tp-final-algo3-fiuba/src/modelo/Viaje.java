package modelo;

public class Viaje {
	
	public int viajarHacia(Policia UnPolicia,Pais PaisDestino){
		Pais PaisActual = UnPolicia.getPais();
		
		int kilometrosAViajar = PaisActual.distanciaAPais(PaisDestino); //Calculo de kilometros entre paises
		
		int tiempoDeViaje = UnPolicia.costoDeViaje(kilometrosAViajar);
		
		UnPolicia.setPaisActual(PaisDestino);
		return tiempoDeViaje;
		
	}
}
