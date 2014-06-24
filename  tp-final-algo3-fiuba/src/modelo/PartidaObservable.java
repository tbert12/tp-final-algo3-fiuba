package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;


public class PartidaObservable extends Observable {
	
	
	private Partida UnaPartida;
	private ArrayList<Edificio> EdificiosAVisitar;
	private ArrayList<Pais> PosiblesPaisesAViajar;
	private int TiempoRestante;
	private ArrayList<String> LadronesFiltrados;
	private String PaisActual;
	private String PistaEdificioActual;
	
	public PartidaObservable(Partida UnaPartida){
		this.UnaPartida = UnaPartida;
		EdificiosAVisitar = UnaPartida.edificiosAMostrar();
		PosiblesPaisesAViajar = UnaPartida.paisesAViajar();
		TiempoRestante = UnaPartida.tiempoRestante();
		PaisActual = UnaPartida.nombrePaisActual();
		
	}
	
	public ArrayList<String> EdificiosAVisitar(){
		ArrayList<String> NombresEdificios = new ArrayList<String>();
		Iterator<Edificio> iterador = EdificiosAVisitar.iterator();
		
		while(iterador.hasNext()){
			Edificio UnEdificio = iterador.next();
			NombresEdificios.add(UnEdificio.getNombre());
		}
		return NombresEdificios;
	}
	
	public int TiempoRestante(){
		return this.TiempoRestante;
	}
	
	public ArrayList<String> posiblesPaisesAViajar(){
		ArrayList<String> NombresPaises = new ArrayList<String>();
		Iterator<Pais> iterador = PosiblesPaisesAViajar.iterator();
		
		while(iterador.hasNext()){
			Pais UnPais = iterador.next();
			NombresPaises.add(UnPais.getNombre());
		}
		return NombresPaises;
	}
	
	public String paisActual(){
		return PaisActual;
	}
	
	public ArrayList<String> LadronesFiltrados(){
		return LadronesFiltrados;
	}
	
	public String PistaEdificioActual(){
		return PistaEdificioActual;
	}
	/*
	public void VisitarEdificio(String NombreEdificio){
		if( EdificiosAVisitar.contains(NombreEdificio)){
			PistaEdificioActual = UnaPartida.visitarEdificio(NombreEdificio);
			
			setChanged();
			notifyObservers();
		}
	}
	
	public void ViajarHacia(String UnPais){
		
		
		
		UnaPartida.viajarHacia(UnPais);
		
		PaisActual = UnaPartida.NombrePaisActual();
		setChanged();
		notifyObservers();
	}
	*/
}
