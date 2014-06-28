package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vistas.botones.BotonParaPais;
import modelo.PartidaObservable;

public class ControladorViajarAPais implements ActionListener {
	private PartidaObservable partida;
	private BotonParaPais boton;
	
	public ControladorViajarAPais(PartidaObservable partida, BotonParaPais boton) {
		this.partida = partida;
		this.boton = boton;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		partida.ViajarHacia(boton.obtenerPais());
	}

}
