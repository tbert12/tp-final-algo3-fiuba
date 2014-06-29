package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vistas.VistaPartida;

public class ControladorBotonCaracteristica implements ActionListener {

	VistaPartida vista;

	public ControladorBotonCaracteristica(VistaPartida vista) {
		this.vista = vista;
	}
	public void actionPerformed(ActionEvent arg0) {
		vista.MostrarCaracteristicasParaFiltrar();
		
	}
	
}
