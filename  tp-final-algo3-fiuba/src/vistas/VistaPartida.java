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

import vistas.paneles.PanelViajar;
import vistas.paneles.PanelEdificios;
import vistas.paneles.PanelCaracteristicas;
import vistas.relojdigital.RelojDigital;
import modelo.PartidaObservable;
import control.ControladorBotonViajar;
import control.ControladorBotonInvestigar;

public class VistaPartida extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	
	private PartidaObservable partida;
	
	private JPanel PanelGeneral;
	
	private PanelViajar panelMenuParaViajar;
	private PanelEdificios panelMenuEdificios;
	private PanelCaracteristicas panelMenuCaracteristicas;
	
	private String RutaImagenPais = "/vistas/imagenes/paises/";
	private String HorasTiempo;
	private String NombrePaisActual;
	private RelojDigital Reloj = new RelojDigital();

	
	public VistaPartida(PartidaObservable partida) {
			this.partida = partida;
			this.partida.addObserver(this);
			PanelGeneral = new JPanel();
			HorasTiempo = Reloj.HoraDigital(partida.getTiempoRestante());
			NombrePaisActual = partida.getPaisActual();
			
			panelMenuParaViajar = new PanelViajar(PanelGeneral,partida);
			panelMenuEdificios = new PanelEdificios(PanelGeneral,partida);
			panelMenuCaracteristicas = new PanelCaracteristicas(PanelGeneral,partida);
			
			crearVentana();
	}


	@Override
	public void update(Observable arg0, Object arg1) {
		HorasTiempo = Reloj.HoraDigital(partida.getTiempoRestante()); //pasar el entero a forma correcta
		NombrePaisActual = partida.getPaisActual();
	
	}
	
	public void mostrarVentana(){
		setVisible(true);
	}
	
	private void crearVentana(){
		setVisible(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VistaPartida.class.getResource("/vistas/imagenes/icono.png")));
		setResizable(false);
		setTitle("Carmen Sandiego");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 642, 513);
		PanelGeneral.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(PanelGeneral);
		setLocationRelativeTo(null);
		PanelGeneral.setLayout(null);
		
		JLabel ImagenPais = new JLabel("");
		ImagenPais.setIcon(new ImageIcon(VentanaPrincipal.class.getResource(this.RutaImagenPais + this.NombrePaisActual +".jpg")));
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
		
		JLabel LabelInformacion = new JLabel("<html> INFORMACION PARA MOSTRAR:\r\n - Pistas.\r\n -Posibles Ladrones.\r\n -Orden de Arresto Emitida.\r\n -Info del Pais.\r\n -Si gano o Perdio.\r\nEntrar 384 chars</html>");
		LabelInformacion.setVerticalTextPosition(SwingConstants.TOP);
		LabelInformacion.setVerticalAlignment(SwingConstants.TOP);
		LabelInformacion.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 15));
		LabelInformacion.setForeground(new Color(255, 255, 255));
		LabelInformacion.setBounds(310, 24, 307, 214);
		PanelGeneral.add(LabelInformacion);
		
		JButton BotonInvestigar = new JButton("Edificios");
		BotonInvestigar.setFocusPainted(false);
		BotonInvestigar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BotonInvestigar.setBorderPainted(false);
		BotonInvestigar.setMargin(new Insets(3, 28, 0, 10));
		BotonInvestigar.setIcon(new ImageIcon(VistaPartida.class.getResource("/vistas/imagenes/botones/BotonInvestigar.png")));
		BotonInvestigar.setRolloverIcon(new ImageIcon(VistaPartida.class.getResource("/vistas/imagenes/botones/BotonInvestigarApretado.png")));
		BotonInvestigar.setBounds(428, 403, 80, 77);
		BotonInvestigar.addActionListener(new ControladorBotonInvestigar(this,this.partida));
		PanelGeneral.add(BotonInvestigar);
		
		JButton BotonViajar = new JButton("Viajar");
		BotonViajar.setFocusPainted(false);
		BotonViajar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BotonViajar.setBorderPainted(false);
		BotonViajar.setMargin(new Insets(3, 28, 0, 10));
		BotonViajar.setIcon(new ImageIcon(VistaPartida.class.getResource("/vistas/imagenes/botones/BotonViajarNormal.png")));
		BotonViajar.setRolloverIcon(new ImageIcon(VistaPartida.class.getResource("/vistas/imagenes/botones/BotonViajarApretado.png")));
		BotonViajar.setBounds(555, 403, 80, 77);
		BotonViajar.addActionListener(new ControladorBotonViajar(this));
		PanelGeneral.add(BotonViajar);
		
		JButton BotonFiltrar = new JButton("Filtrar");
		BotonFiltrar.setFocusPainted(false);
		BotonFiltrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BotonFiltrar.setBorderPainted(false);
		BotonFiltrar.setMargin(new Insets(3, 28, 0, 10));
		BotonFiltrar.setIcon(new ImageIcon(VistaPartida.class.getResource("/vistas/imagenes/botones/BotonFiltrarNormal.png")));
		BotonFiltrar.setRolloverIcon(new ImageIcon(VistaPartida.class.getResource("/vistas/imagenes/botones/BotonFiltrarApretado.png")));
		BotonFiltrar.setBounds(298, 403, 80, 77);
		PanelGeneral.add(BotonFiltrar);
		
		JLabel FondoConImagen = new JLabel("");
		FondoConImagen.setForeground(new Color(255, 255, 255));
		FondoConImagen.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/vistas/imagenes/Fondo.png")));
		FondoConImagen.setBounds(0, 0, 640, 480);
		PanelGeneral.add(FondoConImagen);
	}


	public void MostrarPaisesParaViajar() {
		panelMenuParaViajar.mostrarPanel();	
		panelMenuEdificios.ocultarPanel();
		panelMenuCaracteristicas.ocultarPanel();
	}
	
	public void MostrarEdificiosParaVisitar(){
		panelMenuParaViajar.ocultarPanel();
		panelMenuEdificios.mostrarPanel();
		panelMenuCaracteristicas.ocultarPanel();
	}
	public void MostrarCaracteristicasParaFiltrar(){
		panelMenuParaViajar.ocultarPanel();
		panelMenuEdificios.ocultarPanel();
		panelMenuCaracteristicas.mostrarPanel();
	}
}
