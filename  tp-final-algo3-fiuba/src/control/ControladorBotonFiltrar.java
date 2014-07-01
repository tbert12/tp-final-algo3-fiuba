package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vistas.Sonidos;
import vistas.VistaPartida;

public class ControladorBotonFiltrar implements ActionListener {

	VistaPartida vista;
	Sonidos sonidos;

	public ControladorBotonFiltrar(VistaPartida vista) {
		this.vista = vista;
		this.sonidos = Sonidos.ObtenerSonidos();
	}
	public void actionPerformed(ActionEvent arg0) {
		vista.MostrarCaracteristicasParaFiltrar();
		sonidos.reproducirSonidoBoton();
		
	}
	
}
