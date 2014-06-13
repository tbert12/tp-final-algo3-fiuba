package modelo;

import java.util.ArrayList;
import java.util.Iterator;

public class BaseDeDatos {

	private ArrayList<Ladron> Ladrones = new ArrayList<Ladron>();
	
	public void addSospechoso(Ladron ladron){
		this.Ladrones.add(ladron);
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
}
