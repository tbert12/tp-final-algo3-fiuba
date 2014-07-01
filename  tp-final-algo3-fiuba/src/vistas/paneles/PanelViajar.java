package vistas.paneles;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import control.ControladorViajarAPais;
import vistas.Sonidos;
import vistas.botones.BotonParaPais;
import modelo.Pais;
import modelo.PartidaObservable;

public class PanelViajar extends PanelTresBotones{
	
	private ArrayList<Pais> Paises;
	private BotonParaPais BotonUno,BotonDos,BotonTres;
	
	public PanelViajar(JPanel panel, PartidaObservable partida, Sonidos sonidos) {
		super(panel, partida,sonidos);
	}
	
	protected void crearBotones(){
	
		this.BotonUno = new BotonParaPais();
		BotonUno.addActionListener(new ControladorViajarAPais(partida, BotonUno,this.sonidos));
		BotonUno.setBounds(10, 11, 287, 30);
		BotonUno.setFont(new Font(FuenteDeBotones, Font.PLAIN, 18));
		Panel.add(BotonUno);
		
		this.BotonDos = new BotonParaPais();
		BotonDos.addActionListener(new ControladorViajarAPais(partida, BotonDos,this.sonidos));
		BotonDos.setBounds(10, 49, 287, 32);
		BotonDos.setFont(new Font(FuenteDeBotones, Font.PLAIN, 18));
		Panel.add(BotonDos);
		
		this.BotonTres = new BotonParaPais();
		BotonTres.addActionListener(new ControladorViajarAPais(partida, BotonTres,this.sonidos));
		BotonTres.setBounds(10, 90, 287, 32);
		BotonTres.setFont(new Font(FuenteDeBotones, Font.PLAIN, 18));
		Panel.add(BotonTres);

	}
	
	protected void actualizarBotones(){
		Paises = partida.getPaisesAViajar();
		Iterator<Pais> iterador = Paises.iterator();
		Pais PaisUno = iterador.next();
		this.BotonUno.setText(PaisUno.getNombre());
		this.BotonUno.setPais(PaisUno);
		Pais PaisDos = iterador.next();
		this.BotonDos.setText(PaisDos.getNombre());
		this.BotonDos.setPais(PaisDos);
		Pais PaisTres = iterador.next();
		this.BotonTres.setText(PaisTres.getNombre());
		this.BotonTres.setPais(PaisTres);
	}

}
