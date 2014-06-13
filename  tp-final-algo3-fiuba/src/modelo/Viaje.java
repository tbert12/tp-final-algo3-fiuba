package modelo;

public class Viaje {
	
	
	
	public void viajarHacia(Policia UnPolicia,Pais PaisDestino){
		Pais PaisActual = UnPolicia.getPais();
		
		int kilometrosAViajar = 123; //Calculo de kilometros entre paises
		
		int tiempoDeViaje = UnPolicia.CostoDeViaje(kilometrosAViajar);
		
		UnPolicia.setPaisActual(PaisDestino);
		UnPolicia.CostoDeViaje(tiempoDeViaje);
		
	}
}
