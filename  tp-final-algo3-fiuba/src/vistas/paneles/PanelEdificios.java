package vistas.paneles;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JPanel;
import control.ControladorVisitarEdificio;
import vistas.botones.BotonParaEdificio;
import modelo.PartidaObservable;
import modelo.Edificio;

public class PanelEdificios extends PanelTresBotones{
	
	private ArrayList<Edificio> edificiosAMostrar;
	private BotonParaEdificio botonUno,botonDos,botonTres;

	public PanelEdificios(JPanel panel, PartidaObservable partida) {
		super(panel, partida);
		
	}

	protected void crearBotones(){
		
		this.botonUno = new BotonParaEdificio();
		botonUno.addActionListener(new ControladorVisitarEdificio(partida, botonUno));
		botonUno.setBounds(10, 11, 287, 30);
		botonUno.setFont(new Font(fuenteDeBotones, Font.PLAIN, 18));
		Panel.add(botonUno);
		
		this.botonDos = new BotonParaEdificio();
		botonDos.addActionListener(new ControladorVisitarEdificio(partida, botonDos));
		botonDos.setBounds(10, 49, 287, 32);
		botonDos.setFont(new Font(fuenteDeBotones, Font.PLAIN, 18));
		Panel.add(botonDos);
		
		this.botonTres = new BotonParaEdificio();
		botonTres.addActionListener(new ControladorVisitarEdificio(partida, botonTres));
		botonTres.setBounds(10, 90, 287, 32);
		botonTres.setFont(new Font(fuenteDeBotones, Font.PLAIN, 18));
		Panel.add(botonTres);

	}
	
	protected void actualizarBotones(){
		edificiosAMostrar = partida.getEdificiosAVisitar();
		
		Iterator<Edificio> iterador = edificiosAMostrar.iterator();
		Edificio EdificioUno = iterador.next();
		this.botonUno.setText(EdificioUno.getNombre());
		this.botonUno.setEdificio(EdificioUno);
		Edificio EdificioDos = iterador.next();
		this.botonDos.setText(EdificioDos.getNombre());
		this.botonDos.setEdificio(EdificioDos);
		Edificio EdificioTres = iterador.next();
		this.botonTres.setText(EdificioTres.getNombre());
		this.botonTres.setEdificio(EdificioTres);
	}
		
}

