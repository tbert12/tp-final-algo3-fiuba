package modelo;

import java.util.ArrayList;
import java.util.Iterator;

import modelo.excepcion.ErrorEdificioNoEstaEnPais;
import modelo.excepcion.ErrorElPaisNoEsta;

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
	
	public String ValorObjetoRobado(){
		return ObjetoRobado.getValor();
	}
	
	public String NombreObjetoRobado(){
		return ObjetoRobado.getNombre();
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
	
	public ArrayList<String> NombresPaisesAViajar(){
		
		ArrayList<Pais> PosiblesPaises = BasedeDatos.PosiblesPaisesAViajar(UnLadron, UnPolicia.getPais());
		ArrayList<String> NombresPaisesAViajar = new ArrayList<String>();
		
		Iterator<Pais> iterador = PosiblesPaises.iterator();
		while(iterador.hasNext()){
			Pais UnPais = iterador.next();
			NombresPaisesAViajar.add( UnPais.getNombre() );
		}
		
		return NombresPaisesAViajar;
	}
	
	public boolean ViajarHacia(String NombrePais){
		try{
			Pais PaisDestino = BasedeDatos.ObtenerPaisPorNombre(NombrePais);
			CostosDeViajes.viajarHacia(UnPolicia, PaisDestino);
			return true;
		}
		catch(ErrorElPaisNoEsta e){
			return false;
		}
	}
	
}