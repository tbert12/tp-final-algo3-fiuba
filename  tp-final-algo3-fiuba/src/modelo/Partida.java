package modelo;

import java.util.ArrayList;

import modelo.excepcion.ErrorEdificioNoEstaEnPais;

public class Partida {
	private Policia UnPolicia;
	private Ladron UnLadron;
	private BaseDeDatos BasedeDatos;
	private Viaje CostosDeViajes;
	private ObjetoRobado ObjetoRobado;
	
	public Partida(Policia UnPolicia,Ladron UnLadron,BaseDeDatos UnaBase,ObjetoRobado UnObjeto){
	
		this.UnPolicia = UnPolicia;
		this.UnLadron = UnLadron;
		this.BasedeDatos = UnaBase;
		this.ObjetoRobado = UnObjeto;
		this.CostosDeViajes = new Viaje();	
	}
	
	public ArrayList<String> NombresDeEdificiosAMostrar(){
		Pais PaisActual = UnPolicia.getPais();
		return PaisActual.getNombresDeEdificios();
	}
	
	public String MostrarPistaDeEdificio(String NombreEdificio){
		Pais PaisActual = UnPolicia.getPais();
		try{
			return PaisActual.getPistaDeEdificio(NombreEdificio);
		}
		catch (ErrorEdificioNoEstaEnPais e){
			return "ERROR";
		}
		
	} 
	
	
}