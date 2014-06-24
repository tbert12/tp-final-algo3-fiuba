package modelo;

import java.util.ArrayList;
import java.util.HashMap;

import modelo.excepcion.ErrorEdificioNoEstaEnPais;
import modelo.excepcion.ErrorEdificioNoEstaEnPais;

@SuppressWarnings("unused")
public class Pais {
	
	private String Nombre;
	private String Informacion;
	private Edificio[] Edificios;
	private HashMap<String,Integer> DistanciaAPaises;
	
	public Pais(String UnNombre,Edificio[] LosEdificios){
		this.Nombre = UnNombre;
		this.Edificios = LosEdificios;
	}
	
	public String getNombre(){
		return Nombre;
	}
	
	//Capas Es mejor recibirlo por parametro cuando lo inicializamos.
	public void setInformacion(String UnaInfo){
		Informacion = UnaInfo;
	}
	
	public String getInformacion(){
		return Informacion;
	}
	
	public int distanciaAPais(String unPais){
		return DistanciaAPaises.get(unPais);
	}
	public void setDistancias(HashMap<String,Integer> unDicc){
		DistanciaAPaises = unDicc;
	}
	public ArrayList<String> getNombresDeEdificios(){
		ArrayList<String> NombresEdificios = new ArrayList<String>();
		
		for (int i=0; i<Edificios.length; i++){
			NombresEdificios.add( Edificios[i].getNombre() );
		}
		return NombresEdificios;
	}
	private Edificio getEdificio(String NombreDeEdficio)throws ErrorEdificioNoEstaEnPais{
		for (int i=0; i<Edificios.length; i++){
			if( Edificios[i].getNombre() == NombreDeEdficio ){
				return Edificios[i];
			}
		}
		throw new ErrorEdificioNoEstaEnPais();
	}
	
	public String getPistaDeEdificio(String NombreEdificio) throws ErrorEdificioNoEstaEnPais{
		return getEdificio(NombreEdificio).getPista();
	}
	public int getEdificoVecesVisitado(String NombreEdificio) throws ErrorEdificioNoEstaEnPais{
		return getEdificio(NombreEdificio).vecesVisitado();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Nombre == null) ? 0 : Nombre.hashCode());
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
		Pais other = (Pais) obj;
		if (Nombre == null) {
			if (other.Nombre != null)
				return false;
		} else if (!Nombre.equals(other.Nombre))
			return false;
		return true;
	}
}
