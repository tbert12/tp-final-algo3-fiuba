package modelo;

import java.util.Dictionary;

public class Pais {
	
	private String Nombre;
	private Edificio[] Edificios;
	private Dictionary<String,Integer> DistanciaAPaises;
	
	public Pais(String UnNombre,Edificio[] LosEdificios){
		this.Nombre = UnNombre;
		this.Edificios = LosEdificios;
	}
	
	public Edificio getEdificio(int indice){
		return Edificios[indice];
	}
	public String getNombre(){
		return Nombre;
	}
	
	public int DistanciaAPais(String unPais){
		return DistanciaAPaises.get(unPais);
	}
	public void setDistancias(Dictionary<String,Integer> unDicc){
		DistanciaAPaises = unDicc;
	}
}
