package vistas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


import modelo.PartidaObservable;

public class VistaPartida extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	protected PartidaObservable partida;
	private JPanel PanelGeneral;
	private String RutaImagenPais;
	private String HorasTiempo;
	private String NombrePaisActual;

	
	public VistaPartida(PartidaObservable partida) {
			this.partida = partida;
			this.partida.addObserver(this);
			
			HorasTiempo = "Lunes, 12:00hs";
			NombrePaisActual = "Buenos Aires";
			
			crearVentana();
	}


	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void mostrarVentana(){
		setVisible(true);
	}
	
	private void crearVentana(){
		setVisible(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Tomi\\Dropbox\\Java\\Fiuba-Algo3-Tp2\\src\\vistas\\imagenes\\icono.png"));
		setResizable(false);
		setTitle("Carmen Sandiego");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 642, 513);
		PanelGeneral = new JPanel();
		PanelGeneral.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(PanelGeneral);
		PanelGeneral.setLayout(null);
		
		JLabel ImagenPais = new JLabel("");
		ImagenPais.setIcon(new ImageIcon("/vistas/imagenes/paises/"+ this.NombrePaisActual +".jpg"));
		ImagenPais.setBounds(10, 92, 274, 376);
		PanelGeneral.add(ImagenPais);
		
		JLabel CiudadActual = new JLabel(this.NombrePaisActual);
		CiudadActual.setHorizontalAlignment(SwingConstants.CENTER);
		CiudadActual.setFont(new Font("Stencil", Font.PLAIN, 20));
		CiudadActual.setForeground(new Color(255, 255, 255));
		CiudadActual.setBounds(9, 18, 275, 25);
		PanelGeneral.add(CiudadActual);
		
		JLabel Tiempo = new JLabel(this.HorasTiempo);
		Tiempo.setHorizontalAlignment(SwingConstants.CENTER);
		Tiempo.setForeground(new Color(255, 255, 255));
		Tiempo.setFont(new Font("LCD", Font.PLAIN, 18));
		Tiempo.setBounds(19, 42, 255, 25);
		PanelGeneral.add(Tiempo);
		
		JLabel LabelInformacion = new JLabel("Aca es el lugar donde va a\r\nentrar la descripcion del \r\npais y las pistas de cada \r\nedificio. \r\nEntran 27/30 char por linea");
		LabelInformacion.setVerticalTextPosition(SwingConstants.TOP);
		LabelInformacion.setVerticalAlignment(SwingConstants.TOP);
		LabelInformacion.setToolTipText("");
		LabelInformacion.setHorizontalAlignment(SwingConstants.TRAILING);
		LabelInformacion.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 15));
		LabelInformacion.setForeground(new Color(255, 255, 255));
		LabelInformacion.setBounds(310, 24, 307, 213);
		PanelGeneral.add(LabelInformacion);
		
		JButton BotonInvestigar = new JButton("Viajar");
		BotonInvestigar.setFocusPainted(false);
		BotonInvestigar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BotonInvestigar.setBorderPainted(false);
		BotonInvestigar.setRolloverSelectedIcon(new ImageIcon("C:\\Users\\Tomi\\Dropbox\\TP2 (final) - Algoritmos III\\Imagenes\\Botones\\BotonApretado.png"));
		BotonInvestigar.setMargin(new Insets(3, 28, 0, 10));
		BotonInvestigar.setIcon(new ImageIcon("C:\\Users\\Tomi\\Dropbox\\TP2 (final) - Algoritmos III\\Imagenes\\Botones\\BotonNormal.png"));
		BotonInvestigar.setSelectedIcon(new ImageIcon("C:\\Users\\Tomi\\Dropbox\\TP2 (final) - Algoritmos III\\Imagenes\\Botones\\BotonConMouseArriba.png"));
		BotonInvestigar.setRolloverIcon(new ImageIcon("C:\\Users\\Tomi\\Dropbox\\TP2 (final) - Algoritmos III\\Imagenes\\Botones\\BotonConMouseArriba.png"));
		BotonInvestigar.setBounds(428, 403, 80, 77);
		PanelGeneral.add(BotonInvestigar);
		
		JButton BotonViajar = new JButton("Edificios");
		BotonViajar.setFocusPainted(false);
		BotonViajar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BotonViajar.setBorderPainted(false);
		BotonViajar.setRolloverSelectedIcon(new ImageIcon("C:\\Users\\Tomi\\Dropbox\\TP2 (final) - Algoritmos III\\Imagenes\\Botones\\BotonApretado.png"));
		BotonViajar.setMargin(new Insets(3, 28, 0, 10));
		BotonViajar.setIcon(new ImageIcon("C:\\Users\\Tomi\\Dropbox\\TP2 (final) - Algoritmos III\\Imagenes\\Botones\\BotonNormal.png"));
		BotonViajar.setSelectedIcon(new ImageIcon("C:\\Users\\Tomi\\Dropbox\\TP2 (final) - Algoritmos III\\Imagenes\\Botones\\BotonConMouseArriba.png"));
		BotonViajar.setRolloverIcon(new ImageIcon("C:\\Users\\Tomi\\Dropbox\\TP2 (final) - Algoritmos III\\Imagenes\\Botones\\BotonConMouseArriba.png"));
		BotonViajar.setBounds(555, 403, 80, 77);
		PanelGeneral.add(BotonViajar);
		
		JButton BotonFiltrar = new JButton("Filtrar");
		BotonFiltrar.setFocusPainted(false);
		BotonFiltrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BotonFiltrar.setBorderPainted(false);
		BotonFiltrar.setRolloverSelectedIcon(new ImageIcon("C:\\Users\\Tomi\\Dropbox\\TP2 (final) - Algoritmos III\\Imagenes\\Botones\\BotonApretado.png"));
		BotonFiltrar.setMargin(new Insets(3, 28, 0, 10));
		BotonFiltrar.setIcon(new ImageIcon("C:\\Users\\Tomi\\Dropbox\\TP2 (final) - Algoritmos III\\Imagenes\\Botones\\BotonNormal.png"));
		BotonFiltrar.setSelectedIcon(new ImageIcon("C:\\Users\\Tomi\\Dropbox\\TP2 (final) - Algoritmos III\\Imagenes\\Botones\\BotonConMouseArriba.png"));
		BotonFiltrar.setRolloverIcon(new ImageIcon("C:\\Users\\Tomi\\Dropbox\\TP2 (final) - Algoritmos III\\Imagenes\\Botones\\BotonConMouseArriba.png"));
		BotonFiltrar.setBounds(298, 403, 80, 77);
		PanelGeneral.add(BotonFiltrar);
		
		JLabel FondoConImagen = new JLabel("");
		FondoConImagen.setForeground(new Color(255, 255, 255));
		FondoConImagen.setIcon(new ImageIcon("/vistas/imagenes/Fondo.png"));
		FondoConImagen.setBounds(0, 0, 640, 480);
		PanelGeneral.add(FondoConImagen);
	}
}
