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
	private ArrayList<Ladron> LadronesFiltrados;
	private Edificio EdificioActual;
	private String PistaActual;
	
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
	public String getPistaActual(){
		return PistaActual;
	}
	
	public ArrayList<Pais> getPaisesAViajar(){
		return UnaPartida.paisesAViajar();
	}
	
	public String getPaisActual(){
		return UnaPartida.paisActual().getNombre();
	}
	
	public ArrayList<String> getLadronesFiltrados(){
		ArrayList<String> NombresLadrones = new ArrayList<String>();
		Iterator<Ladron> iterador = LadronesFiltrados.iterator();
		
		while(iterador.hasNext()){
			Ladron ladron = iterador.next();
			NombresLadrones.add(ladron.getNombre());
		}
		return NombresLadrones;
	}
	
	public void visitarEdificio(Edificio edificio){
		EdificioActual = edificio;
		PistaActual = UnaPartida.visitarEdificio(EdificioActual);
		setChanged();
		notifyObservers();
		
	}
	
	public void ViajarHacia(Pais UnPais){
		UnaPartida.viajarHacia(UnPais);
		setChanged();
		notifyObservers();
	}

	public void filtratLadron(Sexo unSexo,Hobby unHobby,Cabello unCabello,Senia unaSenia,Vehiculo unVehiculo){
		LadronesFiltrados = UnaPartida.filtrarLadron(unSexo, unHobby, unCabello, unaSenia, unVehiculo);
		setChanged();
		notifyObservers();
	}
}
