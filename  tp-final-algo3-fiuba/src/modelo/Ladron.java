package modelo;

import modelo.excepcion.ErrorNoHayMasPaisesParaAvanzar;

public class Ladron {
	private Caracteristicas Caracteristicas;
	private String Nombre;
	private Trayectoria Trayectoria;
	private boolean OrdenDeArresto;
	
	public Ladron(String Nombre,Caracteristicas caracteristicas){
		this.Nombre = Nombre;
		this.Caracteristicas = caracteristicas;
		this.OrdenDeArresto = false;
	}
	
	public void addTrayectoria(Trayectoria trayecto){
		this.Trayectoria = trayecto;
	}
	
	public String getNombre(){
		return this.Nombre;
	}
	
	public String Sexo(){
		return Caracteristicas.getSexo();
	}
	
	public String Hobby(){
		return Caracteristicas.getHobby();
	}
	
	public String Cabello(){
		return Caracteristicas.getCabello();
	}
	
	public String Senia(){
		return Caracteristicas.getSenia();
	}
	
	public String Vehiculo(){
		return Caracteristicas.getVehiculo();
	}
	
	public Pais PaisFinal(){
		return Trayectoria.paisFinal();
	}
	public boolean CompararCaracteristicas(Caracteristicas otrasCaracteriscas){
		return Caracteristicas.CompararCon(otrasCaracteriscas);
	}
	
	public Pais PaisActual(){
		return this.Trayectoria.paisActual();
	}
	
	public Pais PaisAnterior(){
		return Trayectoria.PaisAnterior();
	}
	
	public boolean PasaPor(Pais pais){
		return this.Trayectoria.EstaEnTrayectoria(pais);
	}

	public Pais Avanzar() {
		try{
			return this.Trayectoria.avanzar();
		}
		catch (ErrorNoHayMasPaisesParaAvanzar e){
			//TODO Devolver un pais anterior al actual, lo necesito en Base
			return PaisAnterior();
		}
	}
	public void EmitirOrdenDeArresto(){
		OrdenDeArresto = true;
	}
	
	public boolean TieneOrdenDeArresto(){
		return OrdenDeArresto;
	}
}
