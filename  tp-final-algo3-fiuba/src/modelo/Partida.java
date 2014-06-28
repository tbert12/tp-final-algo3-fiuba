package modelo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import modelo.caracteristicas.*;

public class Partida {

	private Policia UnPolicia;
	private boolean LadronAtrapado; 
	private Ladron UnLadron;
	private BaseDeDatos BasedeDatos;
	private Viaje CostosDeViajes;
	private ObjetoRobado objetoQueFueRobado;
		
	
	public Partida(Policia UnPolicia,Ladron UnLadron,BaseDeDatos UnaBase,ObjetoRobado UnObjeto){
	
		this.UnPolicia = UnPolicia;
		UnPolicia.setPaisActual(UnLadron.paisActual());
		this.LadronAtrapado = false;
		this.UnLadron = UnLadron;
		this.BasedeDatos = UnaBase;
		this.objetoQueFueRobado = UnObjeto;
		this.CostosDeViajes = new Viaje();	
	}
	
	public boolean seTerminoLaPartida(){
		return this.LadronAtrapado;
	}
	
	public Pais paisActual(){
		return this.UnPolicia.getPais();
	}
	
	public String valorObjetoRobado(){
		return objetoQueFueRobado.getValor();
	}
	
	public String nombreObjetoRobado(){
		return objetoQueFueRobado.getNombre();
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
	
	public ArrayList<Ladron> filtrarLadron(Sexo unSexo,Hobby unHobby,Cabello unCabello,Senia unaSenia,Vehiculo unVehiculo){
			Caracteristicas CaracteristicasAFiltrar = new Caracteristicas(unSexo,unHobby,unCabello,unaSenia,unVehiculo);
			ArrayList<Ladron> PosiblesLadrones = BasedeDatos.filtarPorCaracteristicas(CaracteristicasAFiltrar);
			UnPolicia.reducirHorasPorFiltracion();
			if (PosiblesLadrones.size() == 1){
				//Hay solo un ladron, hay que emitir la orden de arresto
				Ladron UnicoLadron = PosiblesLadrones.get(0);
				UnPolicia.setSospechoso(UnicoLadron);
			}
			
			return PosiblesLadrones;
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
	public Element serializar(Document doc){
		Element elementoPartida = doc.createElement("Partida");
		elementoPartida.setAttribute("NombrePolicia",UnPolicia.getNombre());
		elementoPartida.setAttribute("NombreLadron",UnLadron.getNombre());
		Element elementoObjeto = doc.createElement("Objeto");
		elementoObjeto = objetoQueFueRobado.serializar(doc);
		elementoPartida.appendChild(elementoObjeto);
		return elementoPartida;
		
	}
	public static ArrayList<Object> hidratar(Node nodo){
		Element elementoPartida = (Element)nodo;
		ObjetoRobado ObjetoACargar =  ObjetoRobado.hidratar(elementoPartida.getFirstChild());
		ArrayList<Object> listadoCosas = new ArrayList<Object>();
		listadoCosas.add(elementoPartida.getAttribute("NombrePolicia"));
		listadoCosas.add(elementoPartida.getAttribute("NombreLadron"));
		listadoCosas.add(ObjetoACargar);
		return listadoCosas;
		
	}
	
}