package vistas.relojdigital;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class HiloDelReloj implements ActionListener {
	private RelojDigital relojDigital;
	private JLabel label;
	
	public HiloDelReloj(RelojDigital reloj,JLabel label){
		relojDigital = reloj;
		this.label = label;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		label.setText(relojDigital.avanzarUnaHora());
	}

}
