package vistas;

import java.applet.AudioClip;

import javax.swing.JApplet;

public class Sonidos extends Thread {

	private AudioClip sonidoBoton,sonidoBotonViajar,sonidoBotonFiltrar;
	
	public Sonidos(){
		crearSonidos();
	}

	private void crearSonidos(){
	
		sonidoBoton = JApplet.newAudioClip(VentanaPrincipal.class.getResource("/vistas/sonidos/sonidoBoton.wav"));
		sonidoBotonViajar = JApplet.newAudioClip(VentanaPrincipal.class.getResource("/vistas/sonidos/sonidoBotonViajar.wav"));
		sonidoBotonFiltrar = JApplet.newAudioClip(VentanaPrincipal.class.getResource("/vistas/sonidos/sonidoBotonFiltrar.wav"));
	}

	public void reproducirSonidoBoton(){
		sonidoBoton.play();
	}

	public void reproducirSonidoBotonViajar(){
		sonidoBotonViajar.play();
	}
	public void reproducirSonidoBotonFiltrar(){
		sonidoBotonFiltrar.play();
	}
}
