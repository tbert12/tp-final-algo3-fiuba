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
	private ArrayList<BotonParaEdificio> listaBotones; 

	public PanelEdificios(JPanel panel, PartidaObservable partida) {
		super(panel, partida);
		
	}

	protected void crearBotones(){
		
		listaBotones = new ArrayList<BotonParaEdificio>();
		int cantidadDeBotones = 3;
		int cordenadaHorizontal = 10;
		
		for(int i=0; i < cantidadDeBotones; i++){
			
			BotonParaEdificio unBoton = new BotonParaEdificio();
			unBoton.addActionListener(new ControladorVisitarEdificio(partida, unBoton));
			unBoton.setBounds(10,cordenadaHorizontal, 287, 32);
			unBoton.setFont(new Font(fuenteDeBotones, Font.PLAIN, 18));
			listaBotones.add(unBoton);
			Panel.add(unBoton);
			cordenadaHorizontal += 39;
			
		}
		/*
		BotonParaEdificio botonUno = new BotonParaEdificio();
		botonUno.addActionListener(new ControladorVisitarEdificio(partida, botonUno));
		botonUno.setBounds(10, 10, 287, 32);
		botonUno.setFont(new Font(fuenteDeBotones, Font.PLAIN, 18));
		listaBotones.add(botonUno);
		Panel.add(botonUno);
		
		BotonParaEdificio botonDos = new BotonParaEdificio();
		botonDos.addActionListener(new ControladorVisitarEdificio(partida, botonDos));
		botonDos.setBounds(10, 50, 287, 32);
		botonDos.setFont(new Font(fuenteDeBotones, Font.PLAIN, 18));
		listaBotones.add(botonDos);
		Panel.add(botonDos);
		
		BotonParaEdificio botonTres = new BotonParaEdificio();
		botonTres.addActionListener(new ControladorVisitarEdificio(partida, botonTres));
		botonTres.setBounds(10, 90, 287, 32);
		botonTres.setFont(new Font(fuenteDeBotones, Font.PLAIN, 18));
		listaBotones.add(botonTres);
		Panel.add(botonTres);
		*/

	}
	
	protected void actualizarBotones(){
		edificiosAMostrar = partida.getEdificiosAVisitar();
		
		Iterator<Edificio> iteradorEdificios = edificiosAMostrar.iterator();
		Iterator<BotonParaEdificio> iteradorBotones = listaBotones.iterator();
		
		while(iteradorBotones.hasNext() && iteradorEdificios.hasNext()){
			Edificio unEdificio = iteradorEdificios.next();
			BotonParaEdificio unBoton = iteradorBotones.next();
			
			unBoton.setText(unEdificio.getNombre());
			unBoton.setEdificio(unEdificio);
			
		}

	}
		
}

