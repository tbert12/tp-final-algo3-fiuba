package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.sun.xml.internal.ws.util.StringUtils;

import vistas.Sonidos;
import vistas.VentanaPrincipal;

public class ControladorBotonJugar implements ActionListener {
		private VentanaPrincipal vista;
		private Juego juego;
		private Sonidos sonidos;
		
		public ControladorBotonJugar(VentanaPrincipal vista,Juego juego,Sonidos sonidos) {
			this.vista = vista;
			this.juego = juego;
			this.sonidos = sonidos;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String NombreUsuario = vista.getTexto();
			sonidos.reproducirSonidoBoton();
			
			if (NombreUsuario == null || NombreUsuario.equals("") ){
				vista.mostrarError("Ingrese un nombre valido.");
			}
			else{
				NombreUsuario = amoldarNombre(NombreUsuario);
				juego.iniciarPartida(NombreUsuario);
			}
		}
		
		private String amoldarNombre(String nombre) {
			String nombreCorrecto;
			nombre = StringUtils.capitalize(nombre);
			nombreCorrecto = nombre.trim(); 
			
			return nombreCorrecto;
		}

}
