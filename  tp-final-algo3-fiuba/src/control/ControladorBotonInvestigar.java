package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.PartidaObservable;
import vistas.VistaPartida;

public class ControladorBotonInvestigar implements ActionListener{
	VistaPartida vista;
	PartidaObservable partida;

	public ControladorBotonInvestigar(VistaPartida vista,PartidaObservable partida) {
		this.vista = vista;
		this.partida = partida;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		vista.MostrarEdificiosParaVisitar();
		
	}
}
