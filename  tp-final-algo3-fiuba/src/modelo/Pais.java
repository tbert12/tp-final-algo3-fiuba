package modelo;

import java.util.ArrayList;
import java.util.Dictionary;

import modelo.excepcion.ErrorEdificioNoEstaEnPais;
import modelo.excepcion.ErrorEdificioNoEstaEnPais;

@SuppressWarnings("unused")
public class Pais {
	
	private String Nombre;
	private Edificio[] Edificios;
	private Dictionary<String,Integer> DistanciaAPaises;
	
	public Pais(String UnNombre,Edificio[] LosEdificios){
		this.Nombre = UnNombre;
		this.Edificios = LosEdificios;
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
	public ArrayList<String> getNombresDeEdificios(){
		ArrayList<String> NombresEdificios = new ArrayList<String>();
		
		for (int i=0; i<Edificios.length; i++){
			NombresEdificios.add( Edificios[i].getNombre() );
		}
		return NombresEdificios;
	}
	public String getPistaDeEdificio(String NombreEdificio) throws ErrorEdificioNoEstaEnPais{
		for (int i=0; i<Edificios.length; i++){
			if( Edificios[i].getNombre() == NombreEdificio ){
				return Edificios[i].getPista();
			}
		}
		throw new ErrorEdificioNoEstaEnPais();
	}
	public int getEdificoVecesVisitado(String NombreEdificio) throws ErrorEdificioNoEstaEnPais{
		for (int i=0; i<Edificios.length; i++){
			if( Edificios[i].getNombre() == NombreEdificio ){
				return Edificios[i].VecesVisitado();
			}
		}
		throw new ErrorEdificioNoEstaEnPais();
	}
}
