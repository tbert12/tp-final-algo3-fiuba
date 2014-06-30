package vistas.relojdigital;

public class RelojDigital {
	private String[] Dias = {"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
	private int indiceDia = 0;
	private int TiempoTranscurridoDelDia = 7;
	
	public int TiempoInicial = 154;
	public int TiempoTemporal = TiempoInicial;
	
	public void ActualizarHora(int tiempo){
		this.TiempoInicial = tiempo;
	}

	private String obtenerHoraDigitalDespuesde(int cantHoras){
		if (cantHoras + this.TiempoTranscurridoDelDia > 24){
			indiceDia++;
			if (indiceDia > 6) indiceDia = 0;
			this.TiempoTranscurridoDelDia = cantHoras + this.TiempoTranscurridoDelDia - 24;
		}
		else {
			this.TiempoTranscurridoDelDia = cantHoras + this.TiempoTranscurridoDelDia;
		}
		String tiempoDigital = Dias[indiceDia] + ", " + Integer.toString(this.TiempoTranscurridoDelDia) + ":00 Hs.";
		return tiempoDigital;
	}
	
	public String AvanzarUnaHora() {
		if (TiempoInicial == TiempoTemporal)return obtenerHoraDigitalDespuesde(0);
		TiempoTemporal--;
		return obtenerHoraDigitalDespuesde(1);
	}

	public String ObtenerHoraDigital() {
		return obtenerHoraDigitalDespuesde(0);
	}
}
