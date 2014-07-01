package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vistas.Sonidos;
import vistas.botones.BotonParaEdificio;
import modelo.PartidaObservable;

public class ControladorVisitarEdificio implements ActionListener {
	
	private PartidaObservable partida;
	private BotonParaEdificio boton;
	private Sonidos sonidos;
	
	public ControladorVisitarEdificio(PartidaObservable partida, BotonParaEdificio boton,Sonidos sonido) {
		this.partida = partida;
		this.boton = boton;
		this.sonidos = sonido;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		partida.visitarEdificio(boton.getEdificio());
		sonidos.reproducirSonidoBoton();
	}

}
