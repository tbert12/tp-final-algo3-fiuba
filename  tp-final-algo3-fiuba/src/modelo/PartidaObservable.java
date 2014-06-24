package modelo;

import java.util.ArrayList;
import java.util.Observable;

import modelo.excepcion.ErrorElPaisNoEsta;

public class PartidaObservable extends Observable {
	
	
	private Partida UnaPartida;
	private ArrayList<String> EdificiosAVisitar;
	private ArrayList<String> PosiblesPaisesAViajar;
	private int TiempoRestante;
	private ArrayList<String> LadronesFiltrados;
	private String PaisActual;
	private String PistaEdificioActual;
	
	public PartidaObservable(Partida UnaPartida){
		this.UnaPartida = UnaPartida;
		EdificiosAVisitar = UnaPartida.NombresDeEdificiosAMostrar();
		PosiblesPaisesAViajar = UnaPartida.NombresPaisesAViajar();
		TiempoRestante = UnaPartida.TiempoRestante();
		PaisActual = UnaPartida.NombrePaisActual();
		
	}
	
	public ArrayList<String> EdificiosAVisitar(){
		return this.EdificiosAVisitar;
	}
	
	public int TiempoRestante(){
		return this.TiempoRestante;
	}
	
	public ArrayList<String> PosiblesPaisesAViajar(){
		return PosiblesPaisesAViajar;
	}
	public String PaisActual(){
		return PaisActual;
	}
	
	public ArrayList<String> LadronesFiltrados(){
		return LadronesFiltrados;
	}
	
	public String PistaEdificioActual(){
		return PistaEdificioActual;
	}
	
	public void VisitarEdificio(String NombreEdificio){
		if( EdificiosAVisitar.contains(NombreEdificio)){
			PistaEdificioActual = UnaPartida.MostrarPistaDeEdificio(NombreEdificio);
			
			setChanged();
			notifyObservers();
		}
	}
	
	public void ViajarHacia(String UnPais){
		try{
			UnaPartida.ViajarHacia(UnPais);
		}
		catch (ErrorElPaisNoEsta e){
			//Por Ahora Nada, Sabemos que no puede Pasar
			//Esto no deberia pasar nunca en realidad, porque se manejan internamente las opciones.
			//Si pasase esto, le estamos pifiando en algo de logica en realidad
		}
		PaisActual = UnaPartida.NombrePaisActual();
		setChanged();
		notifyObservers();
	}
	
}
