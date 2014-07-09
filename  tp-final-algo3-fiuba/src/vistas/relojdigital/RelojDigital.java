package vistas.relojdigital;

import vistas.sonidos.Audios;
import vistas.sonidos.Sonidos;

public class RelojDigital {
	private String[] Dias = {"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
	private int indiceDia = 0;
	private int tiempoTranscurridoDelDia = 7;
	private Sonidos sonidos;
	public int tiempoInicial = 154;
	public int tiempoTemporal = tiempoInicial;
	
	public RelojDigital() {
		this.sonidos = Sonidos.ObtenerSonidos();
	}
	
	public void ActualizarHora(int tiempo){
		this.tiempoInicial = tiempo;
		if (this.tiempoInicial < 0) this.tiempoInicial = 0;
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
	
	public String AvanzarUnaHora() {
		if (tiempoInicial == tiempoTemporal)return obtenerHoraDigitalDespuesde(0);
		tiempoTemporal--;
		sonidos.reproducirSonido(Audios.RELOJ);
		return obtenerHoraDigitalDespuesde(1);
	}

	public String ObtenerHoraDigital() {
		return obtenerHoraDigitalDespuesde(0);
	}
}
