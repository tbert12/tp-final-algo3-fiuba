package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vistas.VentanaPrincipal;

public class ControladorBotonEmpezar implements ActionListener {
		private VentanaPrincipal vista;
		private String nombreJugador;
		
		public ControladorBotonEmpezar(VentanaPrincipal vista, String nombre) {
			this.vista = vista;
			this.nombreJugador = nombre;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//buscar en base de datos el jugador y empezar la partida, si no esta crear uno nuevo
			
		}
}
