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
	
	public void FiltrarLadron(String unSexo,String unHobby,String unCabello,String unaSenia,String unVehiculo){
			Caracteristicas CaracteristicasAFiltrar = new Caracteristicas(unSexo,unHobby,unCabello,unaSenia,unVehiculo);
			ArrayList<Ladron> PosiblesLadrones = BasedeDatos.FiltarPorCaracteristicas(CaracteristicasAFiltrar);
			
			if (PosiblesLadrones.size() == 1){
				//Hay solo un ladron, hay que emitir la orden de arresto
				Ladron UnicoLadron = PosiblesLadrones.get(0);
				UnicoLadron.EmitirOrdenDeArresto();
			}
			
			//no se que se haria con los ladrones flitrados, supongo enviar los nombres para mostrar en pantalla.
	}
	
}