package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.commons.lang3.StringUtils;

import vistas.sonidos.Audios;
import vistas.sonidos.Sonidos;
import vistas.VentanaPrincipal;

public class ControladorBotonJugar implements ActionListener {
		private VentanaPrincipal vista;
		private Juego juego;
		private Sonidos sonidos;
		
		public ControladorBotonJugar(VentanaPrincipal vista,Juego juego) {
			this.vista = vista;
			this.juego = juego;
			this.sonidos = Sonidos.ObtenerSonidos();;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String NombreUsuario = vista.getTexto();
			sonidos.reproducirSonido(Audios.COMUN);
			
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
