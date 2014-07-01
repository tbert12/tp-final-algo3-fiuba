package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vistas.Sonidos;
import vistas.VistaPartida;

public class ControladorBotonInvestigar implements ActionListener{
	private VistaPartida vista;
	private Sonidos sonidos;

	public ControladorBotonInvestigar(VistaPartida vista,Sonidos sonido) {
		this.vista = vista;
		this.sonidos = sonido;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		vista.MostrarEdificiosParaVisitar();
		sonidos.reproducirSonidoBoton();
	}
}
