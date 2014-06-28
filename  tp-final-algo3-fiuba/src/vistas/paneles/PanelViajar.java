package vistas.paneles;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import modelo.Pais;
import modelo.PartidaObservable;

public class PanelViajar implements Observer{
	JPanel PanelGeneral;
	JLayeredPane Panel;
	JButton BotonPais1,BotonPais2,BotonPais3;
	PartidaObservable partida;
	ArrayList<Pais> Paises;
	
	
	public PanelViajar(JPanel panel,PartidaObservable partida){
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
		this.BotonPais1 = new JButton();
		BotonPais1.setBounds(10, 11, 287, 30);
		Panel.add(BotonPais1);
		
		this.BotonPais2 = new JButton();
		BotonPais2.setBounds(10, 49, 287, 32);
		Panel.add(BotonPais2);
		
		this.BotonPais3 = new JButton();
		BotonPais3.setBounds(10, 90, 287, 32);
		Panel.add(BotonPais3);
		
	}
	
	private void InsertarNombreEnBotones(){
		Paises = partida.getPaisesAViajar();
		Iterator<Pais> iterador = Paises.iterator();
		Pais PaisUno = iterador.next();
		this.BotonPais1.setText(PaisUno.getNombre());
		Pais PaisDos = iterador.next();
		this.BotonPais2.setText(PaisDos.getNombre());
		Pais PaisTres = iterador.next();
		this.BotonPais3.setText(PaisTres.getNombre());
	}
	
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
