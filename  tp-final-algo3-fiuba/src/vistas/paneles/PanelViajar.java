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
	private ArrayList<BotonParaPais> listaBotones;
	
	public PanelViajar(JPanel panel, PartidaObservable partida) {
		super(panel, partida);
	}
	
	protected void crearBotones(){
		
		listaBotones = new ArrayList<BotonParaPais>();
		
		BotonParaPais botonUno = new BotonParaPais();
		botonUno.addActionListener(new ControladorViajarAPais(partida, botonUno));
		botonUno.setBounds(10, 11, 287, 30);
		botonUno.setFont(new Font(fuenteDeBotones, Font.PLAIN, 18));
		listaBotones.add(botonUno);
		Panel.add(botonUno);
		
		BotonParaPais botonDos = new BotonParaPais();
		botonDos.addActionListener(new ControladorViajarAPais(partida, botonDos));
		botonDos.setBounds(10, 49, 287, 32);
		botonDos.setFont(new Font(fuenteDeBotones, Font.PLAIN, 18));
		listaBotones.add(botonDos);
		Panel.add(botonDos);
		
		BotonParaPais botonTres = new BotonParaPais();
		botonTres.addActionListener(new ControladorViajarAPais(partida, botonTres));
		botonTres.setBounds(10, 90, 287, 32);
		botonTres.setFont(new Font(fuenteDeBotones, Font.PLAIN, 18));
		listaBotones.add(botonTres);
		Panel.add(botonTres);

	}
	
	protected void actualizarBotones(){
		Paises = partida.getPaisesAViajar();
		
		Iterator<Pais> iteradorPais = Paises.iterator();
		Iterator<BotonParaPais> iteradorBotones = listaBotones.iterator();
		
		while(iteradorBotones.hasNext() && iteradorPais.hasNext()){
			Pais unPais = iteradorPais.next();
			BotonParaPais unBoton = iteradorBotones.next();
			
			unBoton.setText(unPais.getNombre());
			unBoton.setPais(unPais);
		}
	
	}

}
