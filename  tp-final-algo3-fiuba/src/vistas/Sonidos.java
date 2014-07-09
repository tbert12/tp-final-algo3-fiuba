package vistas;

import java.applet.AudioClip;

import javax.swing.JApplet;

public class Sonidos {

	private static Sonidos instancia;
	private AudioClip sonidoBoton,sonidoBotonViajar,sonidoBotonFiltrar,
					sonidoBotonVisitar,sonidoBienvenida,sonidoExitoso,
					sonidoFallido,sonidoHeridaCuchillo,sonidoHeridaArma,sonidoReloj;
	
	private Sonidos(){
		crearSonidos();
	}
	
	public static Sonidos ObtenerSonidos(){
		if(instancia == null){
			instancia = new Sonidos();
		}
		return instancia;
	}

	private void crearSonidos(){
	
		sonidoBoton = JApplet.newAudioClip(getClass().getResource("/vistas/sonidos/sonidoBoton.wav"));
		sonidoBotonViajar = JApplet.newAudioClip(getClass().getResource("/vistas/sonidos/sonidoBotonViajar.wav"));
		sonidoBotonFiltrar = JApplet.newAudioClip(getClass().getResource("/vistas/sonidos/sonidoBotonFiltrar.wav"));
		sonidoBotonVisitar = JApplet.newAudioClip(getClass().getResource("/vistas/sonidos/sonidoBotonVisitar.wav"));
		sonidoBienvenida = JApplet.newAudioClip(getClass().getResource("/vistas/sonidos/sonidoBienvenida.wav"));
		sonidoExitoso = JApplet.newAudioClip(getClass().getResource("/vistas/sonidos/sonidoExitoso.wav"));
		sonidoFallido = JApplet.newAudioClip(getClass().getResource("/vistas/sonidos/sonidoFallido.wav"));
		sonidoHeridaCuchillo = JApplet.newAudioClip(getClass().getResource("/vistas/sonidos/sonidoHeridaCuchillo.wav"));
		sonidoHeridaArma = JApplet.newAudioClip(getClass().getResource("/vistas/sonidos/sonidoHeridaArma.wav"));
		sonidoReloj = JApplet.newAudioClip(getClass().getResource("/vistas/sonidos/sonidoReloj.wav"));
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
	
	public void reproducirSonidoReloj() {
		sonidoReloj.play();
	}
}

