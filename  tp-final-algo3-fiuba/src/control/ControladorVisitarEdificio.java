package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vistas.botones.BotonParaEdificio;
import modelo.PartidaObservable;

public class ControladorVisitarEdificio implements ActionListener {
	
	private PartidaObservable partida;
	private BotonParaEdificio boton;
	
	public ControladorVisitarEdificio(PartidaObservable partida, BotonParaEdificio boton) {
		this.partida = partida;
		this.boton = boton;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		partida.visitarEdificio(boton.getEdificio());
	}

}
