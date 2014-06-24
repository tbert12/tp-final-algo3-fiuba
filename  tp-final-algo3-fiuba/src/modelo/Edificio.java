package modelo;

import modelo.heridas.Herida;

public class Edificio {
	
	
	private String Pista,Nombre;
	private int VecesVisitado;
	private Herida Herida;
	private boolean ContieneLadron;
	
	public Edificio(String UnNombre, String UnaPista){
		this.Nombre = UnNombre;
		this.Pista = UnaPista;
		this.VecesVisitado = 0;
		this.Herida = null;
		this.ContieneLadron = false;
	}
	
	public void setHeridaConCuchillo(Herida UnaHerida){
		Herida = UnaHerida;
	}
	
	public void setLadron(){
		ContieneLadron = true;
	}

	public String visitar(Policia UnPolicia){
		this.VecesVisitado++;
		if (ContieneLadron){
			if( UnPolicia.arrestarSospechoso()) return "Ladron Atrapado";
			return "No se emitio orden de arresto, el ladron se escapo";
		}
		
		if (this.Herida == null){ 
			UnPolicia.reducirHorasalVisitar(VecesVisitado);
			return Pista;
		}
		else{
			Herida.herir(UnPolicia);
			return Herida.mensaje();
		}

	}
	public String getNombre(){
		return Nombre;
	}
	
}

