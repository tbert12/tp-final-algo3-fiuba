package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import modelo.caracteristicas.Caracteristicas;

public class BaseDeDatos {

	private ArrayList<Ladron> ladrones;
	private ArrayList<Pais> paises;
	private final int CANTIDADPOSIBLESDESTINOS;
	
	public BaseDeDatos(ArrayList<Ladron> ladrones,ArrayList<Pais> paises){
		this.ladrones = ladrones;
		this.paises = paises;
		this.CANTIDADPOSIBLESDESTINOS = 3;
	}
	
	public ArrayList<Ladron> filtarPorCaracteristicas(Caracteristicas caracteristicas){
		ArrayList<Ladron> ladronesCoincidentes = new ArrayList<Ladron>();
		Iterator<Ladron> Iterador = ladrones.iterator();
		while (Iterador.hasNext()){
			Ladron ladronActual = Iterador.next();
			if (ladronActual.compararCaracteristicas(caracteristicas)){
				ladronesCoincidentes.add(ladronActual);
			}
		}
		
		return ladronesCoincidentes;
		
	}
	
	public ArrayList<Pais> posiblesPaisesAViajar(Ladron ladron, Pais paisActual){
		ArrayList<Pais> posiblesPaises = new ArrayList<Pais>();
		Pais paisDestino;
		
		if ( paisActual.equals( ladron.paisActual() ) ){
			//Se le delega al ladron, si no tiene mas para avanzar, devuelve el anterior
			//Como que vuelva a buscar mas pistas.
			paisDestino = ladron.avanzar();
		}
		else if (paisActual.equals( ladron.paisAnterior() ) ){
			paisDestino = ladron.paisActual();
		}
		else {
			//Hay que hacer volver al pais anterior donde se equivoco
			paisDestino = ladron.paisAnterior();
			 
		}
		posiblesPaises.add(paisDestino);
		
		while ( posiblesPaises.size() < this.CANTIDADPOSIBLESDESTINOS ){
			Pais paisRandom = paises.get((int)(Math.random()*paises.size()));
			while (ladron.pasaPor(paisRandom) || paisActual.equals(paisRandom) || posiblesPaises.contains(paisRandom)){
				paisRandom = paises.get((int)(Math.random()*paises.size()));;
			}
			posiblesPaises.add(paisRandom);
		}
		Collections.shuffle(posiblesPaises);
		return posiblesPaises;
	}
	
}
