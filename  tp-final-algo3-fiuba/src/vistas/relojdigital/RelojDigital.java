package vistas.relojdigital;

import javax.swing.JLabel;
import javax.swing.Timer;

import vistas.sonidos.Audios;
import vistas.sonidos.Sonidos;

public class RelojDigital {
	private String[] Dias = {"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
	private int indiceDia = 0;
	private int tiempoTranscurridoDelDia = 7;
	private Sonidos sonidos;
	private int tiempoInicial = 154;
	private int tiempoTemporal = tiempoInicial;
	private Timer timer;
	
	public RelojDigital(JLabel label) {
		this.sonidos = Sonidos.ObtenerSonidos();
		this.timer = new Timer(200, new HiloDelReloj(this,label));
	}
	
	public void actualizarHora(int tiempo){
		this.tiempoInicial = tiempo;
		if (this.tiempoInicial < 0) this.tiempoInicial = 0;
		timer.start();
	}

	private String obtenerHoraDigitalDespuesde(int cantHoras){
		if (cantHoras + this.tiempoTranscurridoDelDia > 24){
			indiceDia++;
			if (indiceDia > 6) indiceDia = 0;
			this.tiempoTranscurridoDelDia = cantHoras + this.tiempoTranscurridoDelDia - 24;
		}
		else {
			this.tiempoTranscurridoDelDia = cantHoras + this.tiempoTranscurridoDelDia;
		}
		String tiempoDigital = Dias[indiceDia] + ", " + Integer.toString(this.tiempoTranscurridoDelDia) + ":00 Hs.";
		return tiempoDigital;
	}
	
	public String avanzarUnaHora() {
		if (tiempoInicial == tiempoTemporal){
			timer.stop();
			return obtenerHoraDigital();
		}
		tiempoTemporal--;
		sonidos.reproducirSonido(Audios.RELOJ);
		return obtenerHoraDigitalDespuesde(1);
	}

	public String obtenerHoraDigital() {
		return obtenerHoraDigitalDespuesde(0);
	}
}
