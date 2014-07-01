package vistas;

import java.applet.AudioClip;

import javax.swing.JApplet;

public class Sonidos extends Thread {

	private AudioClip sonidoBoton,sonidoBotonViajar,sonidoBotonFiltrar,
					sonidoBotonVisitar,sonidoBienvenida,sonidoExitoso,
					sonidoFallido,sonidoHeridaCuchillo,sonidoHeridaArma;
	
	public Sonidos(){
		crearSonidos();
	}

	private void crearSonidos(){
	
		sonidoBoton = JApplet.newAudioClip(VentanaPrincipal.class.getResource("/vistas/sonidos/sonidoBoton.wav"));
		sonidoBotonViajar = JApplet.newAudioClip(VentanaPrincipal.class.getResource("/vistas/sonidos/sonidoBotonViajar.wav"));
		sonidoBotonFiltrar = JApplet.newAudioClip(VentanaPrincipal.class.getResource("/vistas/sonidos/sonidoBotonFiltrar.wav"));
		sonidoBotonVisitar = JApplet.newAudioClip(VentanaPrincipal.class.getResource("/vistas/sonidos/sonidoBotonVisitar.wav"));
		sonidoBienvenida = JApplet.newAudioClip(VentanaPrincipal.class.getResource("/vistas/sonidos/sonidoBienvenida.wav"));
		sonidoExitoso = JApplet.newAudioClip(VentanaPrincipal.class.getResource("/vistas/sonidos/sonidoExitoso.wav"));
		sonidoFallido = JApplet.newAudioClip(VentanaPrincipal.class.getResource("/vistas/sonidos/sonidoFallido.wav"));
		sonidoHeridaCuchillo = JApplet.newAudioClip(VentanaPrincipal.class.getResource("/vistas/sonidos/sonidoHeridaCuchillo.wav"));
		sonidoHeridaArma = JApplet.newAudioClip(VentanaPrincipal.class.getResource("/vistas/sonidos/sonidoHeridaArma.wav"));
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
	public void reproducirSonidoBotonVisitarEdificio(){
		sonidoBotonVisitar.play();
	}
	public void reproducirSonidoBienvenida(){
		sonidoBienvenida.play();
	}
	public void reproducirSonidoExitoso(){
		sonidoExitoso.play();
	}
	public void reproducirSonidoFallido(){
		sonidoFallido.play();
	}
	public void reproducirSonidoHeridaCuchillo(){
		sonidoHeridaCuchillo.play();
	}
	public void reproducirSonidoHeridaArma(){
		sonidoHeridaArma.play();
	}
}
