package control;

import java.awt.event.ActionEvent;

import vistas.VistaPartida;

public class ControladorBotonCaracteristica {

	VistaPartida vista;

	public ControladorBotonCaracteristica(VistaPartida vista) {
		this.vista = vista;
	}
	public void actionPerformed(ActionEvent arg0) {
		vista.MostrarCaracteristicasParaFiltrar();
		
	}
	
}
