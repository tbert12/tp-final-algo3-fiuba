package vistas.paneles;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JPanel;

import modelo.Pais;
import modelo.PartidaObservable;

public class PanelViajar extends PanelTresBotones{
	
	private ArrayList<Pais> Paises;
	
	public PanelViajar(JPanel panel, PartidaObservable partida) {
		super(panel, partida);
	}
	
	protected void InsertarNombreEnBotones(){
		Paises = partida.getPaisesAViajar();
		Iterator<Pais> iterador = Paises.iterator();
		Pais PaisUno = iterador.next();
		this.BotonUno.setText(PaisUno.getNombre());
		Pais PaisDos = iterador.next();
		this.BotonDos.setText(PaisDos.getNombre());
		Pais PaisTres = iterador.next();
		this.BotonTres.setText(PaisTres.getNombre());
	}

}
