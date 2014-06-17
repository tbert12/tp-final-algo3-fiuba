package modelo;

import java.util.ArrayList;
import java.util.Iterator;

import modelo.excepcion.ErrorNoHayMasPaisesParaAvanzar;
public class Trayectoria {
	
	private Iterator<Pais> IteradorPaisActual;
	private Pais PaisFinal;
	private Pais PaisActual;
	private ArrayList<Pais> Paises;
	//private int indice = 0;

	public Trayectoria (ArrayList<Pais> Paises){
		this.Paises = Paises;
		this.IteradorPaisActual = Paises.iterator();
		this.PaisActual = IteradorPaisActual.next();
		this.PaisFinal = Paises.get(Paises.size() -1);
	}
	
	public Pais avanzar() throws ErrorNoHayMasPaisesParaAvanzar{
		if (IteradorPaisActual.hasNext()){
			PaisActual = IteradorPaisActual.next();
			return PaisActual;
		}
		else {
			throw new ErrorNoHayMasPaisesParaAvanzar();
		}
	}
	
	public Pais PaisAnterior(){
		int indiceAnterior = Paises.indexOf(PaisActual) - 1; 
		Pais PaisAnterior = Paises.get(indiceAnterior);
		return PaisAnterior;
	}
	
	public boolean EstaEnTrayectoria(Pais UnPais){
		
		return Paises.contains(UnPais);
	}
	
	public Pais paisActual(){
		return PaisActual;
	}
	public Pais paisFinal(){
		return PaisFinal;
	}
}
