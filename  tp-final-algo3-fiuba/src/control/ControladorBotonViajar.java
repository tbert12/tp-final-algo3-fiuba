package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vistas.Sonidos;
import vistas.VistaPartida;

public class ControladorBotonViajar implements ActionListener{
	private VistaPartida vista;
	private Sonidos sonidos;
	
	public ControladorBotonViajar(VistaPartida vista,Sonidos sonidos) {
		this.vista = vista;
		this.sonidos = sonidos;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		vista.MostrarPaisesParaViajar();
		sonidos.reproducirSonidoBoton();
		
	}

}
