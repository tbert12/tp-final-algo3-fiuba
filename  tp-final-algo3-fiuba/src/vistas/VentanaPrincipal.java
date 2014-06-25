package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import java.awt.TextArea;
import javax.swing.JTextPane;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setContentPane(contentPane);
		
		JButton btnJugar = new JButton("Jugar");
		btnJugar.setBackground(Color.DARK_GRAY);
		btnJugar.setForeground(Color.RED);
		btnJugar.setVerticalAlignment(SwingConstants.BOTTOM);
		btnJugar.setFont(new Font("Virtual DJ", Font.BOLD, 23));
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Virtual DJ", Font.PLAIN, 18));
		textField.setColumns(10);
		
		JTextPane txtpnCarmenSandiego = new JTextPane();
		txtpnCarmenSandiego.setFont(new Font("Virtual DJ", Font.PLAIN, 35));
		txtpnCarmenSandiego.setForeground(Color.RED);
		txtpnCarmenSandiego.setBackground(Color.BLACK);
		txtpnCarmenSandiego.setText("Carmen Sandiego");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(218)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnJugar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
						.addComponent(textField, Alignment.LEADING))
					.addGap(196))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(88, Short.MAX_VALUE)
					.addComponent(txtpnCarmenSandiego, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(71))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(26)
					.addComponent(txtpnCarmenSandiego, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 266, Short.MAX_VALUE)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnJugar, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
