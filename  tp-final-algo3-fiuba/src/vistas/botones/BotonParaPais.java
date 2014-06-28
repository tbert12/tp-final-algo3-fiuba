package vistas.botones;

import javax.swing.JButton;

import modelo.Pais;

public class BotonParaPais extends JButton {
	private static final long serialVersionUID = 1L;
	private Pais pais;
	
	public BotonParaPais() {
		super();
	}
	
	public void setPais(Pais pais){
		this.pais = pais;
	}

	public Pais obtenerPais() {
		return pais;
	}
	
}
