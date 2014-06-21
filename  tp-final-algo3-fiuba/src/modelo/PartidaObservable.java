package modelo;

import java.util.ArrayList;
import java.util.Observable;

public class PartidaObservable extends Observable {
	
	
	private Partida UnaPartida;
	private ArrayList<String> EdificiosAVisitar;
	private ArrayList<String> PosiblesPaisesAViajar;
	private int TiempoRestante;
	private ArrayList<String> LadronesFiltrados;
	private String PaisActual;
	
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
	
}
