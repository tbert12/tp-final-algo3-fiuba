package vistas.paneles;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import control.ControladorBotonBuscar;
import vistas.VistaPartida;
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
		MenuCaracteristicas.setBounds(310, 249, 307, 135);
		this.PanelGeneral.add(MenuCaracteristicas);
		MenuCaracteristicas.setVisible(false);
		darFormaAVentana();
	}	
	
	
	private JLabel crearLabel(String stringCaracteristica){
		JLabel Label = new JLabel(stringCaracteristica + ":");
		MenuCaracteristicas.add(Label);
		Label.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 15));
		Label.setForeground(new Color(255, 255, 255));
		return Label;
	}
	
	private Choice crearChoice(String[] options){
		Choice choice = new Choice();
		choice.setBackground(SystemColor.desktop);
		choice.setForeground(SystemColor.text);
		choice.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 12));
		for (int i=0;i < options.length;i++){
			choice.add(options[i]);
		}
		choice.select(0);
		MenuCaracteristicas.add(choice);
		return choice;
	}
	
	private void darFormaAVentana(){ 
		MenuCaracteristicas.setLayout(null);
		
		JLabel Separador = new JLabel("----------------------------------------------------------------------------");
		Separador.setForeground(new Color(255, 255, 255));
		Separador.setBounds(0, 0, 305, 8);
		MenuCaracteristicas.add(Separador);
		
		
		JLabel LabelSexo = crearLabel("Sexo");
		String[] OpcionesSexo = {"N/A","MASCULINO","FEMENINO"};
		choiceSexo = crearChoice(OpcionesSexo);
		LabelSexo.setBounds(0, 13, 90, 15);
		choiceSexo.setBounds(90, 10, 120, 18);
		
		JLabel LabelHobby = crearLabel("Hobby");
		String[] OpcionesHobby = {"N/A","TENNIS","MUSICA","ALPINISMO","PARACAIDISMO","NATACION","CROQUET"};
		choiceHobby = crearChoice(OpcionesHobby);
		LabelHobby.setBounds(0, 38, 90, 15);
		choiceHobby.setBounds(90, 35, 120, 18);
		
		JLabel LabelCabello = crearLabel("Cabello");
		String[] OpcionesCabello = {"N/A","CASTANIO","RUBIO","ROJO","NEGRO"};
		choiceCabello = crearChoice(OpcionesCabello);
		LabelCabello.setBounds(0, 63, 90, 15);
		choiceCabello.setBounds(90, 60, 120, 18);
		
		JLabel LabelSenia = crearLabel("Seña");
		String[] OpcionesSenia = {"N/A","COJERA","ANILLO","TATUAJE","CICATRIZ","JOYAS"};
		choiceSenia = crearChoice(OpcionesSenia);
		LabelSenia.setBounds(0,88,90,15);
		choiceSenia.setBounds(90, 85, 120, 18);
		
		JLabel LabelVehiculo = crearLabel("Vehiculo");
		String[] OpcionesVehiculos = {"N/A","DESCAPOTABLE","LIMUSINA","DEPORTIVO","MOTO"};
		choiceVehiculo = crearChoice(OpcionesVehiculos);
		LabelVehiculo.setBounds(0,113,90,15);
		choiceVehiculo.setBounds(90, 110, 120, 18);
		
		JButton BotonBuscar = new JButton();
		BotonBuscar.setFocusPainted(false);
		BotonBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BotonBuscar.setBorderPainted(false);
		BotonBuscar.setIcon(new ImageIcon(VistaPartida.class.getResource("/vistas/imagenes/botones/BotonOKNormal.png")));
		BotonBuscar.setRolloverIcon(new ImageIcon(VistaPartida.class.getResource("/vistas/imagenes/botones/BotonOKApretado.png")));
		BotonBuscar.setBounds(225, 55, 81, 46);
		BotonBuscar.addActionListener(new ControladorBotonBuscar(partida,this));
		MenuCaracteristicas.add(BotonBuscar);
		
	}
	
	public void mostrarPanel(){
		MenuCaracteristicas.setVisible(true);
	}
	public void ocultarPanel(){
		MenuCaracteristicas.setVisible(false);
	}


	public String obtenerSexoSeleccionado() {
		String sexoSeleccionado = choiceSexo.getSelectedItem();
		if (sexoSeleccionado == "N/A") return null;
		return sexoSeleccionado;
	}


	public String obtenerHobbySeleccionado() {
		String hobbySeleccionado = choiceHobby.getSelectedItem();
		if (hobbySeleccionado == "N/A") return null;
		return hobbySeleccionado;
	}


	public String obtenerCabelloSeleccionado() {
		String cabelloSeleccionado = choiceCabello.getSelectedItem();
		if (cabelloSeleccionado == "N/A") return null;
		return cabelloSeleccionado;
	}


	public String obtenerSeniaSeleccionado() {
		String seniaSeleccionado = choiceSenia.getSelectedItem();
		if (seniaSeleccionado == "N/A") return null;
		return seniaSeleccionado;
	}
	
	public String obtenerVehiculoSeleccionado() {
		String vehiculoSeleccionado = choiceVehiculo.getSelectedItem();
		if (vehiculoSeleccionado == "N/A") return null;
		return vehiculoSeleccionado;
	}
	

}
