package modelo;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import modelo.caracteristicas.*;

public class Partida {

	private Policia unPolicia;
	private Ladron unLadron;
	private BaseDeDatos basedeDatos;
	private Viaje costosDeViajes;
	private ObjetoRobado objetoQueFueRobado;
	private boolean partidaFinalizada;
		
	
	public Partida(Policia unPolicia,Ladron unLadron,BaseDeDatos unaBase,ObjetoRobado unObjeto){
	
		this.unPolicia = unPolicia;
		unPolicia.setPaisActual(unLadron.paisActual());
		this.partidaFinalizada = false;
		this.unLadron = unLadron;
		this.basedeDatos = unaBase;
		this.objetoQueFueRobado = unObjeto;
		this.costosDeViajes = new Viaje();	
	}
	
	public void finalizarPartida(){
		
		unPolicia.reiniciar();
		unLadron.reiniciar();
	}
	
	public boolean seTerminoLaPartida(){
		return this.partidaFinalizada;
	}
	public boolean ladronAtrapado(){
		return this.unLadron.estaArrestado();
	}
	
	public Pais paisActual(){
		return this.unPolicia.getPais();
	}
	
	public String valorObjetoRobado(){
		return objetoQueFueRobado.getValor();
	}
	
	public String nombreObjetoRobado(){
		return objetoQueFueRobado.getNombre();
	}
	
	public ArrayList<Edificio> edificiosAMostrar(){
		Pais PaisActual = unPolicia.getPais();
		return PaisActual.getEdificios();
	}
	
	public String visitarEdificio(Edificio unEdificio){
		
		
		String pistaDeEdificio = unEdificio.visitar(unPolicia);
		
		if (unEdificio.tieneLadron()){
			this.partidaFinalizada = true;
		}
		
		return pistaDeEdificio;
	}
	
	public ArrayList<Ladron> filtrarLadron(Sexo unSexo,Hobby unHobby,Cabello unCabello,Senia unaSenia,Vehiculo unVehiculo){
			Caracteristicas CaracteristicasAFiltrar = new Caracteristicas(unSexo,unHobby,unCabello,unaSenia,unVehiculo);
			ArrayList<Ladron> PosiblesLadrones = basedeDatos.filtarPorCaracteristicas(CaracteristicasAFiltrar);
			unPolicia.reducirHorasPorFiltracion();
			if (PosiblesLadrones.size() == 1){
				//Hay solo un ladron, hay que emitir la orden de arresto
				Ladron UnicoLadron = PosiblesLadrones.get(0);
				unPolicia.setSospechoso(UnicoLadron);
			}
			
			return PosiblesLadrones;
	}
	
	public ArrayList<Pais> paisesAViajar(){
		
		ArrayList<Pais> PosiblesPaises = basedeDatos.posiblesPaisesAViajar(unLadron, unPolicia.getPais());
		
		return PosiblesPaises;
	}
	
	public void viajarHacia(Pais paisDestino){
		int horasDeViaje;

		horasDeViaje = costosDeViajes.viajarHacia(unPolicia, paisDestino);
		unPolicia.reducirHorasPorViaje(horasDeViaje);
		
	}

	public String nombrePaisActual() {
		Pais unPais = unPolicia.getPais();
		return unPais.getNombre();
	}

	public int tiempoRestante() {
		return unPolicia.getTiempo();
	}
	public Element serializar(Document doc){
		Element elementoPartida = doc.createElement("Partida");
		elementoPartida.setAttribute("NombrePolicia",unPolicia.getNombre());
		elementoPartida.setAttribute("NombreLadron",unLadron.getNombre());
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
		return listadoCosas; //Devuelve en pos 0, el nombre del cana
							//Pos 1 el nombre del chorro
							//Pos 2 el objeto robado
	}
	public String getNombredelPolicia() {
		return this.unPolicia.getNombre();
	}

	public String getRangodelPolicia() {
		return this.unPolicia.toStringRango();
	}
	public String getNombreLadron(){
		return this.unLadron.getNombre();
	}
	
	public boolean tempoAgotado() {
		return unPolicia.tiempoAgotado();
	}
}