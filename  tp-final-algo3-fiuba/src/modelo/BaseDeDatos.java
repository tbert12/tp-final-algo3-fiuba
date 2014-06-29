package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import modelo.caracteristicas.Caracteristicas;

public class BaseDeDatos {

	private ArrayList<Ladron> Ladrones = new ArrayList<Ladron>();
	private ArrayList<Pais> Paises = new ArrayList<Pais>();
	private final int CANTIDADPOSIBLESDESTINOS = 3;
	
	public void addSospechoso(Ladron ladron){
		this.Ladrones.add(ladron);
	}
	public void addListaPaises(ArrayList<Pais> paises){
		Paises = paises;
	}
	
	public void addListaSospechosos(ArrayList<Ladron> ladrones){
		Ladrones = ladrones;
	}
	
	public void addPais(Pais pais){
		this.Paises.add(pais);
	}
	
	public ArrayList<Ladron> filtarPorCaracteristicas(Caracteristicas caracteristicas){
		ArrayList<Ladron> LadronesCoincidentes = new ArrayList<Ladron>();
		Iterator<Ladron> Iterador = Ladrones.iterator();
		while (Iterador.hasNext()){
			Ladron LadronActual = Iterador.next();
			if (LadronActual.compararCaracteristicas(caracteristicas)){
				LadronesCoincidentes.add(LadronActual);
			}
		}
		
		return LadronesCoincidentes;
		
	}
	
	public ArrayList<Pais> posiblesPaisesAViajar(Ladron Ladron, Pais PaisActual){
		ArrayList<Pais> PosiblesPaises = new ArrayList<Pais>();
		Pais PaisDestino;
		
		if ( PaisActual.equals( Ladron.paisActual() ) ){
			//Se le delega al ladron, si no tiene mas para avanzar, devuelve el anterior
			//Como que vuelva a buscar mas pistas.
			PaisDestino = Ladron.avanzar();
		}
		else if (PaisActual.equals( Ladron.paisAnterior() ) ){
			PaisDestino = Ladron.paisActual();
		}
		else {
			//Hay que hacer volver al pais anterior donde se equivoco
			PaisDestino = Ladron.paisAnterior();
			 
		}
		PosiblesPaises.add(PaisDestino);
		
		while ( PosiblesPaises.size() < this.CANTIDADPOSIBLESDESTINOS ){
			Pais PaisRandom = Paises.get((int)(Math.random()*Paises.size()));
			while (Ladron.pasaPor(PaisRandom) || PaisActual.equals(PaisRandom) || PosiblesPaises.contains(PaisRandom)){
				PaisRandom = Paises.get((int)(Math.random()*Paises.size()));;
			}
			PosiblesPaises.add(PaisRandom);
		}
		Collections.shuffle(PosiblesPaises);
		return PosiblesPaises;
	}
	
}
