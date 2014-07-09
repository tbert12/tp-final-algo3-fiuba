package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vistas.sonidos.Audios;
import vistas.sonidos.Sonidos;
import vistas.botones.BotonParaPais;
import modelo.PartidaObservable;

public class ControladorViajarAPais implements ActionListener {
	private PartidaObservable partida;
	private BotonParaPais boton;
	private Sonidos sonidos;
	
	public ControladorViajarAPais(PartidaObservable partida, BotonParaPais boton) {
		this.partida = partida;
		this.boton = boton;
		this.sonidos = Sonidos.ObtenerSonidos();;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		partida.ViajarHacia(boton.obtenerPais());
		sonidos.reproducirSonido(Audios.VIAJAR);
	}

}
