package vistas.paneles;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import modelo.PartidaObservable;
import modelo.Edificio;

public class PanelEdificios extends PanelTresBotones{
	
	ArrayList<Edificio> EdificiosAMostrar;

	public PanelEdificios(JPanel panel, PartidaObservable partida) {
		super(panel, partida);
	}

	@Override
	protected void InsertarNombreEnBotones() {
		EdificiosAMostrar = partida.getEdificiosAVisitar();
		Iterator<Edificio> iterador = EdificiosAMostrar.iterator();
		Edificio EdificioUno = iterador.next();
		this.BotonUno.setText(EdificioUno.getNombre());
		Edificio EdificioDos = iterador.next();
		this.BotonDos.setText(EdificioDos.getNombre());
		Edificio EdificioTres = iterador.next();
		this.BotonTres.setText(EdificioTres.getNombre());
	}
		
}

