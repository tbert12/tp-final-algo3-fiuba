package vistas.paneles;

import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import modelo.PartidaObservable;

public abstract class PanelTresBotones implements Observer{
	protected JPanel PanelGeneral;
	protected JLayeredPane Panel;
	protected JButton BotonUno,BotonDos,BotonTres;
	
	protected PartidaObservable partida;
	
	private String FuenteDeBotones = "Stencil";
	
	
	public PanelTresBotones(JPanel panel,PartidaObservable partida){
		this.PanelGeneral = panel;
		Panel = new JLayeredPane();
		this.partida = partida;
		partida.addObserver(this);
		darFormaAPanel();
		InsertarNombreEnBotones();
		
	}
	
	private void darFormaAPanel(){
		Panel.setBounds(310, 248, 307, 133);
		PanelGeneral.add(Panel);
		Panel.setVisible(false);
		//PonerControladorACadaUno
		this.BotonUno = new JButton();
		BotonUno.setBounds(10, 11, 287, 30);
		BotonUno.setFont(new Font(FuenteDeBotones, Font.PLAIN, 18));
		Panel.add(BotonUno);
		
		this.BotonDos = new JButton();
		BotonDos.setBounds(10, 49, 287, 32);
		BotonDos.setFont(new Font(FuenteDeBotones, Font.PLAIN, 18));
		Panel.add(BotonDos);
		
		this.BotonTres = new JButton();
		BotonTres.setBounds(10, 90, 287, 32);
		BotonTres.setFont(new Font(FuenteDeBotones, Font.PLAIN, 18));
		Panel.add(BotonTres);
		
	}
	
	protected abstract void InsertarNombreEnBotones();
	
	@Override
	public void update(Observable arg0, Object arg1) {
		InsertarNombreEnBotones();
	}
	
	public void mostrarPanel(){
		Panel.setVisible(true);
	}
	public void ocultarPanel(){
		Panel.setVisible(false);
	}

}
