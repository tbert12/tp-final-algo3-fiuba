package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vistas.VistaPartida;

public class ControladorBotonViajar implements ActionListener{
	private VistaPartida vista;

	public ControladorBotonViajar(VistaPartida vista) {
		this.vista = vista;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		vista.MostrarPaisesParaViajar();
		
	}

}
