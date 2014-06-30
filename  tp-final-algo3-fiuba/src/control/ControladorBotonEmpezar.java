package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.sun.xml.internal.ws.util.StringUtils;

import vistas.VentanaPrincipal;

public class ControladorBotonEmpezar implements ActionListener {
		private VentanaPrincipal vista;
		private Juego juego;
		
		public ControladorBotonEmpezar(VentanaPrincipal vista,Juego juego) {
			this.vista = vista;
			this.juego = juego;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			String NombreUsuario = vista.getTexto();
			
			
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
