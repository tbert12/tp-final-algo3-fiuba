package modelo;

import java.util.ArrayList;
import java.util.Iterator;

public class BaseDeDatos {

	private ArrayList<Ladron> Ladrones = new ArrayList<Ladron>();
	private ArrayList<Pais> Paises = new ArrayList<Pais>();
	private final int CANTIDADPOSIBLESDESTINOS = 3;
	
	public void addSospechoso(Ladron ladron){
		this.Ladrones.add(ladron);
	}
	
	public void addPais(Pais pais){
		this.Paises.add(pais);
	}
	
	public ArrayList<Ladron> FiltarPorCaracteristicas(Caracteristicas caracteristicas){
		ArrayList<Ladron> LadronesCoincidentes = new ArrayList<Ladron>();
		Iterator<Ladron> Iterador = Ladrones.iterator();
		while (Iterador.hasNext()){
			Ladron LadronActual = Iterador.next();
			if (LadronActual.CompararCaracteristicas(caracteristicas)){
				LadronesCoincidentes.add(LadronActual);
			}
		}
		
		return LadronesCoincidentes;
		
	}
	
	public ArrayList<Pais> PosiblesPaisesAViajar(Ladron Ladron, Pais PaisActual){
		ArrayList<Pais> PosiblesPaises = new ArrayList<Pais>();
		Pais PaisDestino;
		
		if ( PaisActual == Ladron.PaisActual() ){
			//Se le delega al ladron, si no tiene mas para avanzar, devuelve el anterior
			//Como que vuelva a buscar mas pistas.
			PaisDestino = Ladron.Avanzar();
		}
		else {
			//Hay que hacer volver al pais anterior donde se equivoco
			PaisDestino = Ladron.PaisAnterior();
			 
		}
		PosiblesPaises.add(PaisDestino);
		
		while ( PosiblesPaises.size() < this.CANTIDADPOSIBLESDESTINOS ){
			Pais PaisRandom = Paises.get((int)(Math.random()*Paises.size()));
			while (Ladron.PasaPor(PaisRandom) || PaisActual == PaisRandom || PosiblesPaises.contains(PaisRandom)){
				PaisRandom = Paises.get((int)(Math.random()*Paises.size()));;
			}
			PosiblesPaises.add(PaisRandom);
		}
		return PosiblesPaises;
	}
	
	public ArrayList<Pais> ObtenerPaises() {
		return this.Paises;
	}
}
