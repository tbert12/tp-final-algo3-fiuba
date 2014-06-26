package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vistas.VentanaPrincipal;

public class ControladorBotonEmpezar implements ActionListener {
		private VentanaPrincipal vista;
		//private String nombreJugador;
		
		public ControladorBotonEmpezar(VentanaPrincipal vista) {
			this.vista = vista;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			String NombreUsuario = vista.getTexto();
			//Enviar Partida a Partida Observable
			
		}
}
