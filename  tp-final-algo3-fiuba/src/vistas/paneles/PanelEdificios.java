package vistas.paneles;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JPanel;
import vistas.botones.BotonParaEdificio;
import modelo.PartidaObservable;
import modelo.Edificio;

public class PanelEdificios extends PanelTresBotones{
	
	private ArrayList<Edificio> EdificiosAMostrar;
	private BotonParaEdificio BotonUno,BotonDos,BotonTres;

	public PanelEdificios(JPanel panel, PartidaObservable partida) {
		super(panel, partida);
	}

	protected void crearBotones(){
		//PonerControladorACadaUno
		this.BotonUno = new BotonParaEdificio();
		BotonUno.setBounds(10, 11, 287, 30);
		BotonUno.setFont(new Font(FuenteDeBotones, Font.PLAIN, 18));
		Panel.add(BotonUno);
		
		this.BotonDos = new BotonParaEdificio();
		BotonDos.setBounds(10, 49, 287, 32);
		BotonDos.setFont(new Font(FuenteDeBotones, Font.PLAIN, 18));
		Panel.add(BotonDos);
		
		this.BotonTres = new BotonParaEdificio();
		BotonTres.setBounds(10, 90, 287, 32);
		BotonTres.setFont(new Font(FuenteDeBotones, Font.PLAIN, 18));
		Panel.add(BotonTres);

	}
	
	protected void actualizarBotones(){
		EdificiosAMostrar = partida.getEdificiosAVisitar();
		
		Iterator<Edificio> iterador = EdificiosAMostrar.iterator();
		Edificio EdificioUno = iterador.next();
		this.BotonUno.setText(EdificioUno.getNombre());
		this.BotonUno.setEdificio(EdificioUno);
		Edificio EdificioDos = iterador.next();
		this.BotonDos.setText(EdificioDos.getNombre());
		this.BotonDos.setEdificio(EdificioDos);
		Edificio EdificioTres = iterador.next();
		this.BotonTres.setText(EdificioTres.getNombre());
		this.BotonTres.setEdificio(EdificioTres);
	}
		
}

