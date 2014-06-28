package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.PartidaObservable;
import vistas.VistaPartida;

public class ControlViajar implements ActionListener {
	
	private VistaPartida vista;
	PartidaObservable partida;
	
	public ControlViajar (VistaPartida unaVista,PartidaObservable unaPartida){
		this.vista = unaVista;
		this.partida = unaPartida;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		

	}

}
