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
	
	
	private Partida unaPartida;
	private Edificio edificioActual;
	private String pistaActual;
	private ArrayList<Ladron> ladronesFiltrados;
	private ArrayList<Pais> paisesParaViajar;
	
	public PartidaObservable(Partida UnaPartida){
		this.unaPartida = UnaPartida;
		paisesParaViajar = UnaPartida.paisesAViajar();
	}
	

	public ArrayList<Edificio> getEdificiosAVisitar(){
		return unaPartida.edificiosAMostrar();
	}
	
	public boolean partidaFinalizada(){
		return unaPartida.seTerminoLaPartida();
	}
	
	public int getTiempoRestante(){
		return unaPartida.tiempoRestante();
	}
	public String getEdificioActual(){
		return edificioActual.getNombre();
	}
	
	public ArrayList<Pais> getPaisesAViajar(){
		return paisesParaViajar;
	}
	
	public String getPaisActual(){
		return unaPartida.paisActual().getNombre();
	}
	
	
	public void visitarEdificio(Edificio edificio){
		edificioActual = edificio;
		pistaActual = unaPartida.visitarEdificio(edificioActual);
		ladronesFiltrados = null;
		setChanged();
		notifyObservers();
		
	}
	
	public void ViajarHacia(Pais UnPais){
		unaPartida.viajarHacia(UnPais);
		paisesParaViajar = unaPartida.paisesAViajar();
		pistaActual = null;
		ladronesFiltrados = null;
		setChanged();
		notifyObservers();
	}
	
	public ArrayList<String> nombreDeSospechosos(){
		ArrayList<String> nombres = new ArrayList<String>();
		if (ladronesFiltrados == null) return null;
		Iterator<Ladron> iterador = ladronesFiltrados.iterator();
		while (iterador.hasNext()){
			nombres.add( iterador.next().getNombre() );
		}
		return nombres;
	}
	
	public void filtrarLadron(Sexo unSexo,Hobby unHobby,Cabello unCabello,Senia unaSenia,Vehiculo unVehiculo){
		ladronesFiltrados = unaPartida.filtrarLadron(unSexo, unHobby, unCabello, unaSenia, unVehiculo);
		pistaActual = null;
		setChanged();
		notifyObservers();
	}


	public String getInformacionPaisActual() {
		return unaPartida.paisActual().getInformacion();
	}


	public String getPistaActual() {
		return this.pistaActual;
	}


	public String getNombredelPolicia() {
		return this.unaPartida.getNombredelPolicia();
	}


	public String getRangodelPolicia() {
		return this.unaPartida.getRangodelPolicia();
	}


	public String getObjetoRobado() {
		return unaPartida.nombreObjetoRobado();
	}


	public boolean tiempoAgotado() {
		return unaPartida.tempoAgotado();
	}
	
	public void finalizarPartida(){
		unaPartida.finalizarPartida();
	}


	public boolean ladronArrestado() {
		return unaPartida.ladronAtrapado();
	}
}
