package vistas;

import java.applet.AudioClip;

import javax.swing.JApplet;

public class Sonidos extends Thread {

	private AudioClip sonidoBotonJugar,sonidoBotonViajar,sonidoBotonFiltrar;
	
	public Sonidos(){
		crearSonidos();
	}

	private void crearSonidos(){
	
		sonidoBotonJugar = JApplet.newAudioClip(VentanaPrincipal.class.getResource("/vistas/sonidos/sonidoBotonJugar.wav"));
		sonidoBotonViajar = JApplet.newAudioClip(VentanaPrincipal.class.getResource("/vistas/sonidos/sonidoBotonViajar.wav"));
		sonidoBotonFiltrar = JApplet.newAudioClip(VentanaPrincipal.class.getResource("/vistas/sonidos/sonidoBotonFiltrar.wav"));
	}

	public void reproducirSonidoBotonJugar(){
		sonidoBotonJugar.play();
		//while (sonidoBotonJugar.);
	}

	public void reproducirSonidoBotonViajar(){
		sonidoBotonViajar.play();
	}
	public void reproducirSonidoBotonFiltrar(){
		sonidoBotonFiltrar.play();
	}
}
