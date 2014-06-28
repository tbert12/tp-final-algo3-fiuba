package vistas.relojdigital;

public class RelojDigital {
	private String[] Dias = {"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
	private int indiceDia = 0;
	private int TiempoTranscurridoDelDia = 7;
	
	public int TiempoInicial = 154;
	
	public String HoraDigital(int tiempo){
		int tiempoASumar = this.TiempoInicial - tiempo;
		this.TiempoInicial = tiempo;
		if (tiempoASumar + this.TiempoTranscurridoDelDia > 24){
			indiceDia++;
			if (indiceDia > 6) indiceDia = 0;
			this.TiempoTranscurridoDelDia = tiempoASumar + this.TiempoTranscurridoDelDia - 24;
		}
		else {
			this.TiempoTranscurridoDelDia = tiempoASumar + this.TiempoTranscurridoDelDia;
		}
		String tiempoDigital = Dias[indiceDia] + ", " + Integer.toString(this.TiempoTranscurridoDelDia) + ":00 Hs.";
		return tiempoDigital;
	}
}
