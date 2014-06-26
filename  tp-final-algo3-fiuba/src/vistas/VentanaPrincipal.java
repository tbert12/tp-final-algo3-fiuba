package vistas;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import modelo.Juego;
import control.ControladorBotonEmpezar;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textoNombreUsuario;
	private Juego unJuego;


	public VentanaPrincipal(Juego unJuego) {
		
		this.unJuego = unJuego;
		
		setVisible(false);
		setTitle("Carmen Sandiego");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		
		textoNombreUsuario = new JTextField();
		textoNombreUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		textoNombreUsuario.setFont(new Font("Virtual DJ", Font.PLAIN, 18));
		textoNombreUsuario.setColumns(10);
		
		JButton btnJugar = new JButton("Jugar");
		btnJugar.addActionListener(new ControladorBotonEmpezar(this,this.unJuego) );
		btnJugar.setBackground(Color.DARK_GRAY);
		btnJugar.setForeground(Color.RED);
		btnJugar.setVerticalAlignment(SwingConstants.BOTTOM);
		btnJugar.setFont(new Font("Virtual DJ", Font.BOLD, 23));
		
		JLabel label = new JLabel("Carmen Sandiego");
		label.setHorizontalTextPosition(SwingConstants.LEADING);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.RED);
		label.setFont(new Font("Virtual DJ", Font.BOLD, 35));
		
		JLabel lblIconocs = new JLabel("IconoCS");
		lblIconocs.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/vistas/imagenes/carmen_san_diego_1.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(193)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnJugar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(textoNombreUsuario, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE))
					.addContainerGap(175, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(219, Short.MAX_VALUE)
					.addComponent(lblIconocs, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
					.addGap(201))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(label, GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblIconocs)
					.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
					.addComponent(textoNombreUsuario, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnJugar, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}

	public void mostrarVentana(){
		setVisible(true);
	}
	
	public String getTexto(){
		return textoNombreUsuario.getText();
	}

	public void mostrarErrorFinal(String string) {
		JOptionPane.showMessageDialog(this,string,"ERROR:",JOptionPane.ERROR_MESSAGE);
		System.exit(0);
	}
	public void mostrarError(String string) {
		JOptionPane.showMessageDialog(this,string,"ERROR:",JOptionPane.ERROR_MESSAGE);
	}
}
