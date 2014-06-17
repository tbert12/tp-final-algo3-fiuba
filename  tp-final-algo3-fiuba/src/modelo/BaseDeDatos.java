package modelo;

import java.util.ArrayList;
import java.util.Iterator;

public class BaseDeDatos {

	private ArrayList<Ladron> Ladrones = new ArrayList<Ladron>();
	private ArrayList<Pais> Paises = new ArrayList<Pais>();
	
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
	
	public ArrayList<Pais> PosiblesPaisesAViajar(Ladron Ladron, Pais Pais){
		int posicionDestino;
		int randomPos;
		Pais aux;
		ArrayList<Pais> PosiblesPaises = new ArrayList<Pais>();
		Pais PaisDestino;
		
		if ( Pais == Ladron.PaisActual() ){
			PaisDestino = Ladron.Avanzar();
		}
		else {
			PaisDestino = Ladron.PaisActual();
			 
		}
		posicionDestino = Paises.indexOf(PaisDestino);
		
		PosiblesPaises.add(PaisDestino);
		randomPos=0;
		//IMPORTANTE : el pais destino queda en la lista siempre a la izq 
		// Entonces en el juego la respuesta correcta siempre seria lo de la izq
		// hay havias formas de cambiarlo... ver si es necesario 
		while ( PosiblesPaises.size() < 3 ){
			while (posicionDestino == randomPos){
				randomPos = (int)(Math.random()*Paises.size());
			}
			aux = Paises.get(randomPos);
			
			PosiblesPaises.add(aux);
		}
		
		return PosiblesPaises;
	}
	
	public ArrayList<Pais> ObtenerPaises() {
		return this.Paises;
	}
}
