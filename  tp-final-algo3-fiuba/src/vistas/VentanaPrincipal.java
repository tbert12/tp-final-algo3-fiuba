package vistas;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import control.ControladorBotonJugar;
import control.Juego;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel MenuInicial;
	private JTextField textoNombreUsuario;
	private Juego unJuego;
	
	public VentanaPrincipal(Juego unJuego) {
		this.unJuego = unJuego;
		crearVentana();
		
	}

	public void mostrarVentana(){
		setVisible(true);
	}
	
	public void ocultarVentana(){
		setVisible(false);
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
	
	private void crearVentana(){
		setVisible(false);
		setTitle("Carmen SanDiego");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/vistas/imagenes/icono.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		MenuInicial = new JPanel();
		MenuInicial.setBackground(Color.BLACK);
		MenuInicial.setForeground(Color.BLACK);
		MenuInicial.setBorder(null);
		setContentPane(MenuInicial);
		setLocationRelativeTo(null);
		
		//controlador
		final ControladorBotonJugar control = new ControladorBotonJugar(this,this.unJuego);
		
		textoNombreUsuario = new JTextField();
		textoNombreUsuario.setBounds(193, 353, 244, 33);
		textoNombreUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		textoNombreUsuario.setFont(new Font("Stencil", Font.PLAIN, 18));
		textoNombreUsuario.setColumns(10);
		textoNombreUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
                	control.actionPerformed(null);
                }
             }
          });
		
		JButton BotonJugar = new JButton("Jugar");
		BotonJugar.setBounds(193, 404, 244, 37);
		BotonJugar.addActionListener( control );
		BotonJugar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent e) {
               control.actionPerformed(null);
            }
         });
		BotonJugar.setBackground(Color.DARK_GRAY);
		BotonJugar.setForeground(Color.RED);
		BotonJugar.setVerticalAlignment(SwingConstants.BOTTOM);
		BotonJugar.setFont(new Font("Stencil", Font.PLAIN, 23));
		
		JLabel Titulo = new JLabel("Carmen Sandiego");
		Titulo.setBounds(10, 11, 614, 107);
		Titulo.setHorizontalTextPosition(SwingConstants.LEADING);
		Titulo.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo.setForeground(Color.RED);
		Titulo.setFont(new Font("Stencil", Font.PLAIN, 50));
		
		JLabel IconoImagen = new JLabel("IconoCS");
		IconoImagen.setBounds(229, 124, 192, 200);
		IconoImagen.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/vistas/imagenes/LogoInicio.png")));
		MenuInicial.setLayout(null);
		MenuInicial.add(BotonJugar);
		MenuInicial.add(textoNombreUsuario);
		MenuInicial.add(IconoImagen);
		MenuInicial.add(Titulo);
		
	}

}
