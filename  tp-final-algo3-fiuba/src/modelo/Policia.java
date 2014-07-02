package modelo;

import modelo.rangos.Rango;
import modelo.rangos.RangoNovato;




import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class Policia  {
	
	
	private String nombre;
	private Rango rango;
	private int cantidadDeArrestos;
	private Tiempo tiempo;
	private Pais paisActual;
	private int horasSinDormir = 0;
	private Ladron sospechoso;

	//Constantes
	private final int tiempoLimiteEnHoras = 154;
	private final int horasADormir = 8;
	private final int limiteHorasDespierto = 15;
	private final int horasPorFiltracion = 3;
	private final int horasHeridaCuchillo = 2;
	private final int horasHeridaArma = 4;


	public Policia(String nombre, int cantidadDeArrestos){
		this.nombre = nombre;
		this.cantidadDeArrestos = cantidadDeArrestos;
		this.sospechoso = null;
		this.rango = new RangoNovato();
		setTiempo();
		chequeoDeRango();
		
		
	}

	public String getNombre(){
		return this.nombre;
	}
	public Rango getRango() {
		return this.rango;
	}
	
	private void setTiempo(){
		this.tiempo = new Tiempo(this.tiempoLimiteEnHoras);
	}
	public void setSospechoso(Ladron unLadron){
		sospechoso = unLadron;
	}
	
	public void setPaisActual(Pais pais){
		this.paisActual = pais;
	}
	
	public Pais getPais(){
		return this.paisActual;
	}

	private void addArresto() {
		cantidadDeArrestos++;
		chequeoDeRango();		
	}
	
	public void reiniciar(){
		setTiempo();
		this.sospechoso = null;
	}
	public boolean arrestarSospechoso(Ladron ladronAArrestar){
		if( sospechoso == null || !sospechoso.equals(ladronAArrestar) ) return false;
		addArresto();
		ladronAArrestar.arrestar();
		return true;
	}
	
	private void chequeoDeRango(){
		this.rango = this.rango.chequeoDeRango(cantidadDeArrestos);
	}
	public int costoDeViaje(int kilometrosAViajar) {
		return this.rango.costoDeViaje(kilometrosAViajar);
	}

	private void reducirHoras(int horas){
		tiempo.reducirHoras(horas);
		horasSinDormir += horas;
		if (horasSinDormir > limiteHorasDespierto){
			dormir();
			horasSinDormir = 0;
		}
	}
	
	private void dormir(){
		this.tiempo.reducirHoras(horasADormir);
	}
	
	public void reducirHorasPorFiltracion() {
		reducirHoras(horasPorFiltracion);	
	}
	
	public void reducirHorasPorViaje(int cantidadHoras){
		reducirHoras(cantidadHoras);
	}
	
	public void reducirHorasalVisitar(int vecesVisitado){
		if (vecesVisitado == 1) reducirHoras(1);
		else if (vecesVisitado == 2) reducirHoras(2);
		else reducirHoras(3);
	}
	
	public void reducirHorasPorHeridaCuchillo(){
		reducirHoras(horasHeridaCuchillo);
	}
	
	public void reducirHorasPorHeridaArmaDeFuego(){
		reducirHoras(horasHeridaArma);
	}
	
	public boolean tiempoAgotado(){
		return tiempo.tiempoAgotado();
	}
	public Element serializar(Document doc){
		Element elementoPolicia =doc.createElement("Policia");
		elementoPolicia.setAttribute("Nombre",this.nombre);
		elementoPolicia.setAttribute("Arrestos",""+this.cantidadDeArrestos);
		return elementoPolicia;
	}
		
	public static Policia hidratar(Node nodo){
		//Como se va a guardar en orden acorde a la posicion dada
		//Le paso por parametro la posicion, para poder hidratar el correcto.
		Element elementoPolicia = (Element)nodo;
		Policia nuevoPolicia = new Policia(elementoPolicia.getAttribute("Nombre"),Integer.parseInt(elementoPolicia.getAttribute("Arrestos")));
		return nuevoPolicia;
		
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Policia other = (Policia) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	public int getTiempo() {
		return tiempo.getHoras();
	}

	public String toStringRango() {
		return this.rango.toString();
	}

	public int getArrestos() {
		return cantidadDeArrestos;
	}
	
}
