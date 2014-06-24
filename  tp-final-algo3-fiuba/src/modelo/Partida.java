package modelo;

import java.util.ArrayList;
import modelo.caracteristicas.*;

public class Partida {

	private Policia UnPolicia;
	private boolean LadronAtrapado; 
	private Ladron UnLadron;
	private BaseDeDatos BasedeDatos;
	private Viaje CostosDeViajes;
	private ObjetoRobado ObjetoRobado;
		
	
	public Partida(Policia UnPolicia,Ladron UnLadron,BaseDeDatos UnaBase,ObjetoRobado UnObjeto){
	
		this.UnPolicia = UnPolicia;
		UnPolicia.setPaisActual(UnLadron.paisActual());
		this.LadronAtrapado = false;
		this.UnLadron = UnLadron;
		this.BasedeDatos = UnaBase;
		this.ObjetoRobado = UnObjeto;
		this.CostosDeViajes = new Viaje();	
	}
	
	public boolean seTerminoLaPartida(){
		return this.LadronAtrapado;
	}
	
	
	public String valorObjetoRobado(){
		return ObjetoRobado.getValor();
	}
	
	public String nombreObjetoRobado(){
		return ObjetoRobado.getNombre();
	}
	
	public ArrayList<Edificio> edificiosAMostrar(){
		Pais PaisActual = UnPolicia.getPais();
		return PaisActual.getEdificios();
	}
	
	public String visitarEdificio(Edificio UnEdificio){
				
		String PistaDeEdificio = UnEdificio.visitar(UnPolicia);
		
		if (UnLadron.estaArrestado()){
			this.LadronAtrapado = true;
		}
		
		return PistaDeEdificio;
	}
	
	public void FiltrarLadron(Sexo unSexo,Hobby unHobby,Cabello unCabello,Senia unaSenia,Vehiculo unVehiculo){
			Caracteristicas CaracteristicasAFiltrar = new Caracteristicas(unSexo,unHobby,unCabello,unaSenia,unVehiculo);
			ArrayList<Ladron> PosiblesLadrones = BasedeDatos.filtarPorCaracteristicas(CaracteristicasAFiltrar);
			UnPolicia.reducirHorasPorFiltracion();
			if (PosiblesLadrones.size() == 1){
				//Hay solo un ladron, hay que emitir la orden de arresto
				Ladron UnicoLadron = PosiblesLadrones.get(0);
				UnPolicia.setSospechoso(UnicoLadron);
			}
			
			//no se que se haria con los ladrones flitrados, supongo enviar los nombres para mostrar en pantalla.
	}
	
	public ArrayList<Pais> paisesAViajar(){
		
		ArrayList<Pais> PosiblesPaises = BasedeDatos.posiblesPaisesAViajar(UnLadron, UnPolicia.getPais());
		
		return PosiblesPaises;
	}
	
	public void viajarHacia(Pais PaisDestino){
		int HorasDeViaje;

		HorasDeViaje = CostosDeViajes.viajarHacia(UnPolicia, PaisDestino);
		UnPolicia.reducirHorasPorViaje(HorasDeViaje);
		
	}

	public String nombrePaisActual() {
		Pais UnPais = UnPolicia.getPais();
		return UnPais.getNombre();
	}

	public int tiempoRestante() {
		return UnPolicia.getTiempo();
	}
	
}