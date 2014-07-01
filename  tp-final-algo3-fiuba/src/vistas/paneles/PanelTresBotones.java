package vistas.paneles;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import vistas.Sonidos;
import modelo.PartidaObservable;

public abstract class PanelTresBotones implements Observer{
	protected JPanel PanelGeneral;
	protected JLayeredPane Panel;
	protected PartidaObservable partida;
	protected String FuenteDeBotones = "Stencil";
	protected Sonidos sonidos;
	
	public PanelTresBotones(JPanel panel,PartidaObservable partida,Sonidos sonidos){
		this.sonidos = sonidos;
		this.PanelGeneral = panel;
		Panel = new JLayeredPane();
		this.partida = partida;
		partida.addObserver(this);
		darFormaAPanel();
		crearBotones();
		actualizarBotones();
		
	}
	
	private void darFormaAPanel(){
		Panel.setBounds(310, 248, 307, 133);
		PanelGeneral.add(Panel);
		Panel.setVisible(false);
	}
	
	protected abstract void actualizarBotones();
	
	protected abstract void crearBotones();
	
	@Override
	public void update(Observable arg0, Object arg1) {
		actualizarBotones();
	}
	
	public void mostrarPanel(){
		Panel.setVisible(true);
	}
	public void ocultarPanel(){
		Panel.setVisible(false);
	}

}
