package vistas.paneles;

import java.awt.Choice;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import modelo.PartidaObservable;

public class PanelCaracteristicas {
	private JPanel PanelGeneral;
	JLayeredPane MenuCaracteristicas;
	PartidaObservable partida;
	Choice choiceSexo,choiceHobby,choiceCabello,choiceSenia,choiceVehiculo;
	
	
	
	public PanelCaracteristicas(JPanel PanelGeneral,PartidaObservable partida){
		this.partida = partida;
		this.PanelGeneral = PanelGeneral;
		MenuCaracteristicas = new JLayeredPane();
		MenuCaracteristicas.setBounds(310, 249, 307, 129);
		this.PanelGeneral.add(MenuCaracteristicas);
		MenuCaracteristicas.setVisible(false);
		darFormaAVentana();
	}	
	
	private Choice crearChoice(String[] options){
		Choice choice = new Choice();
		choice.setBackground(SystemColor.desktop);
		choice.setForeground(SystemColor.text);
		choice.setFont(new Font("Arial", Font.PLAIN, 14));
		for (int i=0;i < options.length;i++){
			choice.add(options[i]);
		}
		choice.select(0);
		MenuCaracteristicas.add(choice);
		return choice;
	}
	
	private void darFormaAVentana(){ 
		MenuCaracteristicas.setLayout(null);
		
		String[] OpcionesSexo = {"N/A","Masculino","Femenino"};
		choiceSexo = crearChoice(OpcionesSexo);
		choiceSexo.setBounds(10, 10, 112, 20);
		String[] OpcionesHobby = {"N/A"};
		choiceHobby = crearChoice(OpcionesHobby);
		choiceHobby.setBounds(10, 10, 112, 50);
		
	}
	
	public void mostrarPanel(){
		MenuCaracteristicas.setVisible(true);
	}
	public void ocultarPanel(){
		MenuCaracteristicas.setVisible(false);
	}
	
	

}
