package modelo;

import java.util.ArrayList;
import java.util.Iterator;

import modelo.caracteristicas.*;
import modelo.excepcion.ErrorEdificioNoEstaEnPais;
import modelo.excepcion.ErrorElPaisNoEsta;

public class Partida {
	private int EdificiosVisitadosEnPaisFinal = 0;
	private Policia UnPolicia;
	private boolean LadronAtrapado; 
	private int PoliciaHorasSinDormir;
	private Ladron UnLadron;
	private BaseDeDatos BasedeDatos;
	private Viaje CostosDeViajes;
	private ObjetoRobado ObjetoRobado;
	
	//Constantes
	private final int HorasADormir = 8;
	private final int HorasDespierto = 15;
	
	
	public Partida(Policia UnPolicia,Ladron UnLadron,BaseDeDatos UnaBase,ObjetoRobado UnObjeto){
	
		this.UnPolicia = UnPolicia;
		UnPolicia.setPaisActual(UnLadron.paisActual());
		this.PoliciaHorasSinDormir = 0;
		this.LadronAtrapado = false;
		this.UnLadron = UnLadron;
		this.BasedeDatos = UnaBase;
		this.ObjetoRobado = UnObjeto;
		this.CostosDeViajes = new Viaje();	
	}
	
	public boolean SeTerminoLaPartida(){
		return this.LadronAtrapado;
	}
	
	public void ReducirHorasalPolicia(int horas){
		this.UnPolicia.reducirHoras(horas);
		this.PoliciaHorasSinDormir += horas;
		if (this.PoliciaHorasSinDormir > HorasDespierto){
			this.UnPolicia.reducirHoras(HorasADormir);
		}
		
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
		if ( PaisActual == UnLadron.paisFinal() ){
			if( EdificiosVisitadosEnPaisFinal == 0 ){
				EdificiosVisitadosEnPaisFinal++;
				return "Hay Algo Raro";
			}
			if (EdificiosVisitadosEnPaisFinal == 1){
				EdificiosVisitadosEnPaisFinal++;
				return "Hay Olor a Dope";
			}
			if (EdificiosVisitadosEnPaisFinal == 2){
				EdificiosVisitadosEnPaisFinal++;
				if ( UnLadron.tieneOrdenDeArresto() ){
					this.LadronAtrapado = true;
					return "Atrapado";
				}
				else{
					return "No Atrapado";
				}
			}
		}
		String PistaDeEdificio;
		int VecesVisitado;
		try{
			PistaDeEdificio = PaisActual.getPistaDeEdificio(NombreEdificio);
			VecesVisitado = PaisActual.getEdificoVecesVisitado(NombreEdificio);
		}
		catch (ErrorEdificioNoEstaEnPais e){
			return "ERROR";
		}
		if ( PistaDeEdificio.equals("Herida de Cuchillo") ) ReducirHorasalPolicia(2);
		else if ( PistaDeEdificio.equals("Herida de Bala") ) ReducirHorasalPolicia(3);
		if (VecesVisitado == 1) ReducirHorasalPolicia(2);
		else if (VecesVisitado == 0) ReducirHorasalPolicia(1);
		else ReducirHorasalPolicia(3);
		return PistaDeEdificio;
	}
	
	public void FiltrarLadron(Sexo unSexo,Hobby unHobby,Cabello unCabello,Senia unaSenia,Vehiculo unVehiculo){
			Caracteristicas CaracteristicasAFiltrar = new Caracteristicas(unSexo,unHobby,unCabello,unaSenia,unVehiculo);
			ArrayList<Ladron> PosiblesLadrones = BasedeDatos.filtarPorCaracteristicas(CaracteristicasAFiltrar);
			ReducirHorasalPolicia(3);
			if (PosiblesLadrones.size() == 1){
				//Hay solo un ladron, hay que emitir la orden de arresto
				Ladron UnicoLadron = PosiblesLadrones.get(0);
				UnicoLadron.emitirOrdenDeArresto();
			}
			
			//no se que se haria con los ladrones flitrados, supongo enviar los nombres para mostrar en pantalla.
	}
	
	public ArrayList<String> NombresPaisesAViajar(){
		
		ArrayList<Pais> PosiblesPaises = BasedeDatos.posiblesPaisesAViajar(UnLadron, UnPolicia.getPais());
		ArrayList<String> NombresPaisesAViajar = new ArrayList<String>();
		
		Iterator<Pais> iterador = PosiblesPaises.iterator();
		while(iterador.hasNext()){
			Pais UnPais = iterador.next();
			NombresPaisesAViajar.add( UnPais.getNombre() );
		}
		
		return NombresPaisesAViajar;
	}
	
	public void ViajarHacia(String NombrePais) throws ErrorElPaisNoEsta {
		int HorasDeViaje;
		Pais PaisDestino;
		try{
			PaisDestino = BasedeDatos.obtenerPaisPorNombre(NombrePais);
		}
		catch(ErrorElPaisNoEsta e){
			return;
		}
		HorasDeViaje = CostosDeViajes.viajarHacia(UnPolicia, PaisDestino);
		ReducirHorasalPolicia(HorasDeViaje);
		
	}

	public String NombrePaisActual() {
		Pais UnPais = UnPolicia.getPais();
		return UnPais.getNombre();
	}

	public int TiempoRestante() {
		return UnPolicia.getTiempo();
	}
	
}