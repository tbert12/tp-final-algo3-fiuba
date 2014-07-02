package vistas.paneles;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import control.ControladorViajarAPais;
import vistas.botones.BotonParaPais;
import modelo.Pais;
import modelo.PartidaObservable;

public class PanelViajar extends PanelTresBotones{
	
	private ArrayList<Pais> Paises;
	private BotonParaPais botonUno,botonDos,botonTres;
	
	public PanelViajar(JPanel panel, PartidaObservable partida) {
		super(panel, partida);
	}
	
	protected void crearBotones(){
	
		this.botonUno = new BotonParaPais();
		botonUno.addActionListener(new ControladorViajarAPais(partida, botonUno));
		botonUno.setBounds(10, 11, 287, 30);
		botonUno.setFont(new Font(fuenteDeBotones, Font.PLAIN, 18));
		Panel.add(botonUno);
		
		this.botonDos = new BotonParaPais();
		botonDos.addActionListener(new ControladorViajarAPais(partida, botonDos));
		botonDos.setBounds(10, 49, 287, 32);
		botonDos.setFont(new Font(fuenteDeBotones, Font.PLAIN, 18));
		Panel.add(botonDos);
		
		this.botonTres = new BotonParaPais();
		botonTres.addActionListener(new ControladorViajarAPais(partida, botonTres));
		botonTres.setBounds(10, 90, 287, 32);
		botonTres.setFont(new Font(fuenteDeBotones, Font.PLAIN, 18));
		Panel.add(botonTres);

	}
	
	protected void actualizarBotones(){
		Paises = partida.getPaisesAViajar();
		Iterator<Pais> iterador = Paises.iterator();
		Pais PaisUno = iterador.next();
		this.botonUno.setText(PaisUno.getNombre());
		this.botonUno.setPais(PaisUno);
		Pais PaisDos = iterador.next();
		this.botonDos.setText(PaisDos.getNombre());
		this.botonDos.setPais(PaisDos);
		Pais PaisTres = iterador.next();
		this.botonTres.setText(PaisTres.getNombre());
		this.botonTres.setPais(PaisTres);
	}

}
