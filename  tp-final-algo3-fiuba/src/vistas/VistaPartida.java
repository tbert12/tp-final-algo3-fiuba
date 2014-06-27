package vistas;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import modelo.PartidaObservable;

public class VistaPartida extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	protected PartidaObservable partida;

	
	public VistaPartida(PartidaObservable partida) {
			this.partida = partida;
			this.partida.addObserver(this);
	}


	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
