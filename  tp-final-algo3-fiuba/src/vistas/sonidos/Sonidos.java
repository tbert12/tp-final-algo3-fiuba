package vistas.sonidos;

import java.applet.AudioClip;
import java.util.HashMap;
import javax.swing.JApplet;

public class Sonidos {

	private static Sonidos instancia;
	private HashMap<Audios,AudioClip> mapaSonidos;
	private Sonidos(){
		mapaSonidos = new HashMap<Audios,AudioClip>();
		crearSonidos();
	}
	
	public static Sonidos ObtenerSonidos(){
		if(instancia == null){
			instancia = new Sonidos();
		}
		return instancia;
	}

	private void crearSonidos(){

		mapaSonidos.put(Audios.COMUN, JApplet.newAudioClip(getClass().getResource("/vistas/sonidos/audios/sonidoBoton.wav")));
		mapaSonidos.put(Audios.VIAJAR, JApplet.newAudioClip(getClass().getResource("/vistas/sonidos/audios/sonidoBotonViajar.wav")));
		mapaSonidos.put(Audios.FILTRAR, JApplet.newAudioClip(getClass().getResource("/vistas/sonidos/audios/sonidoBotonFiltrar.wav")));
		mapaSonidos.put(Audios.VISITAR, JApplet.newAudioClip(getClass().getResource("/vistas/sonidos/audios/sonidoBotonVisitar.wav")));
		mapaSonidos.put(Audios.BIENVENIDA, JApplet.newAudioClip(getClass().getResource("/vistas/sonidos/audios/sonidoBienvenida.wav")));
		mapaSonidos.put(Audios.EXITOSO, JApplet.newAudioClip(getClass().getResource("/vistas/sonidos/audios/sonidoExitoso.wav")));
		mapaSonidos.put(Audios.FALLIDO, JApplet.newAudioClip(getClass().getResource("/vistas/sonidos/audios/sonidoFallido.wav")));
		mapaSonidos.put(Audios.CUCHILLO, JApplet.newAudioClip(getClass().getResource("/vistas/sonidos/audios/sonidoHeridaCuchillo.wav")));
		mapaSonidos.put(Audios.ARMA, JApplet.newAudioClip(getClass().getResource("/vistas/sonidos/audios/sonidoHeridaArma.wav")));
		mapaSonidos.put(Audios.RELOJ, JApplet.newAudioClip(getClass().getResource("/vistas/sonidos/audios/sonidoReloj.wav")));
		
	}

	public void reproducirSonido(Audios tipoSonido){
		
		AudioClip unSonido = mapaSonidos.get(tipoSonido); 
		unSonido.play();
	}
}

