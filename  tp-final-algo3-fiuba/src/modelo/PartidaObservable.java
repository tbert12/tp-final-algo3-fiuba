package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

import modelo.caracteristicas.Cabello;
import modelo.caracteristicas.Hobby;
import modelo.caracteristicas.Senia;
import modelo.caracteristicas.Sexo;
import modelo.caracteristicas.Vehiculo;


public class PartidaObservable extends Observable {
	
	
	private Partida UnaPartida;
	private Edificio EdificioActual;
	private String InformacionParaMostrar;
	
	public PartidaObservable(Partida UnaPartida){
		this.UnaPartida = UnaPartida;
	}
	

	public ArrayList<Edificio> getEdificiosAVisitar(){
		return UnaPartida.edificiosAMostrar();
	}
	
	public boolean partidaFinalizada(){
		return UnaPartida.seTerminoLaPartida();
	}
	
	public int getTiempoRestante(){
		return UnaPartida.tiempoRestante();
	}
	public String getEdificioActual(){
		return EdificioActual.getNombre();
	}
	public String getInformacionParaMostrar(){
		return InformacionParaMostrar;
	}
	
	public ArrayList<Pais> getPaisesAViajar(){
		return UnaPartida.paisesAViajar();
	}
	
	public String getPaisActual(){
		return UnaPartida.paisActual().getNombre();
	}
	
	
	public void visitarEdificio(Edificio edificio){
		EdificioActual = edificio;
		InformacionParaMostrar = "Pista: <br>" + UnaPartida.visitarEdificio(EdificioActual);
		setChanged();
		notifyObservers();
		
	}
	
	public void ViajarHacia(Pais UnPais){
		UnaPartida.viajarHacia(UnPais);
		InformacionParaMostrar = null;
		setChanged();
		notifyObservers();
	}

	private String nombreDeLadrones(ArrayList<Ladron> Ladrones){
		String Nombres = "Mensaje de la Interpool: <br>";
		if (Ladrones.size() == 0) Nombres = Nombres +  "No se han encontrado coincidencias con ningun sospechoso";
		else if (Ladrones.size() == 1) Nombres = Nombres +  "Orden de arresto emitida para: <br>";
		else Nombres = Nombres + "Se encontraron " + Integer.toString(Ladrones.size()) + " coincidencias"; 
		Iterator<Ladron> iterador = Ladrones.iterator();
		while (iterador.hasNext()){
			Nombres = Nombres + " -" + iterador.next().getNombre() + "<br>";
		}
		return Nombres;
	}
	
	public void filtrarLadron(Sexo unSexo,Hobby unHobby,Cabello unCabello,Senia unaSenia,Vehiculo unVehiculo){
		ArrayList<Ladron> LadronesFiltrado = UnaPartida.filtrarLadron(unSexo, unHobby, unCabello, unaSenia, unVehiculo);
		InformacionParaMostrar = nombreDeLadrones(LadronesFiltrado);
		setChanged();
		notifyObservers();
	}


	public String getInformacionPaisActual() {
		return UnaPartida.paisActual().getInformacion();
	}
}
