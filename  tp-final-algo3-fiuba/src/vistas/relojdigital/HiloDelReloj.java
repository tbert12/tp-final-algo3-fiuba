package vistas.relojdigital;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class HiloDelReloj implements ActionListener {
	private RelojDigital relojDigital;
	private JLabel labelDondeseMuestraHora;
	
	public HiloDelReloj(RelojDigital reloj,JLabel label){
		relojDigital = reloj;
		labelDondeseMuestraHora = label;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		labelDondeseMuestraHora.setText(relojDigital.AvanzarUnaHora());
	}

}
