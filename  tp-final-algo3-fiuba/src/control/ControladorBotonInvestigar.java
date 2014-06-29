package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vistas.VistaPartida;

public class ControladorBotonInvestigar implements ActionListener{
	VistaPartida vista;

	public ControladorBotonInvestigar(VistaPartida vista) {
		this.vista = vista;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		vista.MostrarEdificiosParaVisitar();
		
	}
}
