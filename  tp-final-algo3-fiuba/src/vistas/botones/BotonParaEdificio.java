package vistas.botones;

import javax.swing.JButton;

import modelo.Edificio;

public class BotonParaEdificio extends JButton {
	private static final long serialVersionUID = 1L;
	private Edificio edificio;
	
	public BotonParaEdificio(){
		super();
	}
	
	public void setEdificio(Edificio edificio){
		this.edificio = edificio;
	}
	public Edificio getEdificio(){
		return edificio;
	}
	
}
