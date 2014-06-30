package vistas.relojdigital;

public class RelojDigital {
	private String[] Dias = {"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
	private int indiceDia = 0;
	private int TiempoTranscurridoDelDia = 7;
	
	public int TiempoInicial = 154;
	
	public String HoraDigital(int tiempo){
		int tiempoASumar = this.TiempoInicial - tiempo;
		this.TiempoInicial = tiempo;
		return obtenerHoraDigitalDespuesde(tiempoASumar);
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
	
	public int tiempoQueSeRestaraCon(int tiempo) {
		return this.TiempoInicial - tiempo;
	}

	public String AvanzarUnaHora() {
		return obtenerHoraDigitalDespuesde(1);
	}
}
