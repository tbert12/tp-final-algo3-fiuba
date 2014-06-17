package modelo;

import modelo.excepcion.ErrorNoHayMasPaisesParaAvanzar;
import modelo.excepcion.ErrorNoHayPais;
public class Trayectoria {
	
	private Pais PaisActual;
	private Pais PaisFinal;
	private Pais[] Paises;
	private int indice = 0;

	public Trayectoria (Pais[] Paises) throws ErrorNoHayPais{
		if (Paises.length == 0){
			throw new ErrorNoHayPais();
		}
		
		this.Paises = Paises;
		this.PaisActual = Paises[0];
		this.PaisFinal = Paises[Paises.length-1];
	}
	
	public Pais avanzar() throws ErrorNoHayMasPaisesParaAvanzar{
		if (indice < Paises.length-1){
			indice += 1;
			PaisActual = Paises[indice];
			return PaisActual;
		}
		else {
			throw new ErrorNoHayMasPaisesParaAvanzar();
		}
	}
	
	public Pais PaisAnterior(){
		int indiceAnterior = indice - 1; 
		Pais PaisAnterior = Paises[indiceAnterior];
		return PaisAnterior;
	}
	
	public boolean EstaEnTrayectoria(Pais pais){
		for (int i = 0; i < Paises.length ; i++){
			if (Paises[i] == pais) return true;
		}
		return false;
	}
	
	public Pais paisActual(){
		return PaisActual;
	}
	public Pais paisFinal(){
		return PaisFinal;
	}
}
