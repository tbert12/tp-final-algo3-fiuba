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
	private String pistaActual;
	private ArrayList<Ladron> LadronesFiltrados;
	
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
	
	public ArrayList<Pais> getPaisesAViajar(){
		return UnaPartida.paisesAViajar();
	}
	
	public String getPaisActual(){
		return UnaPartida.paisActual().getNombre();
	}
	
	
	public void visitarEdificio(Edificio edificio){
		EdificioActual = edificio;
		pistaActual = UnaPartida.visitarEdificio(EdificioActual);
		LadronesFiltrados = null;
		setChanged();
		notifyObservers();
		
	}
	
	public void ViajarHacia(Pais UnPais){
		UnaPartida.viajarHacia(UnPais);
		pistaActual = null;
		LadronesFiltrados = null;
		setChanged();
		notifyObservers();
	}
	
	public ArrayList<String> nombreDeSospechosos(){
		ArrayList<String> nombres = new ArrayList<String>();
		if (LadronesFiltrados == null) return null;
		Iterator<Ladron> iterador = LadronesFiltrados.iterator();
		while (iterador.hasNext()){
			nombres.add( iterador.next().getNombre() );
		}
		return nombres;
	}
	
	public void filtrarLadron(Sexo unSexo,Hobby unHobby,Cabello unCabello,Senia unaSenia,Vehiculo unVehiculo){
		LadronesFiltrados = UnaPartida.filtrarLadron(unSexo, unHobby, unCabello, unaSenia, unVehiculo);
		pistaActual = null;
		setChanged();
		notifyObservers();
	}


	public String getInformacionPaisActual() {
		return UnaPartida.paisActual().getInformacion();
	}


	public String getPistaActual() {
		return this.pistaActual;
	}


	public boolean tiempoAgotado() {
		return UnaPartida.tempoAgotado();
	}
}
