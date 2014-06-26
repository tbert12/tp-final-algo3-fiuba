package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.CarmenSanDiego;
import vistas.VentanaPrincipal;

public class ControladorBotonEmpezar implements ActionListener {
		private VentanaPrincipal vista;
		//private String nombreJugador;
		private CarmenSanDiego juego;
		
		public ControladorBotonEmpezar(VentanaPrincipal vista, String nombre) {
			this.vista = vista;
			try{
				juego = new CarmenSanDiego();
			}
			catch (Exception e){
				//mostrar error en pantalla.
			}
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//juego.iniciarPartida(nombreJugador);
			//Enviar Partida a Partida Observable
			
		}
}
