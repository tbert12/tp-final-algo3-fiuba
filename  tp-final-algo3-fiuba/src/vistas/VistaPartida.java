package vistas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import vistas.paneles.PanelViajar;
import vistas.paneles.PanelEdificios;
import vistas.paneles.PanelCaracteristicas;
import vistas.relojdigital.RelojDigital;
import modelo.PartidaObservable;
import control.ControladorBotonCaracteristica;
import control.ControladorBotonViajar;
import control.ControladorBotonInvestigar;

public class VistaPartida extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	
	private PartidaObservable partida;
	
	private JPanel PanelGeneral;
	
	private PanelViajar panelMenuParaViajar;
	private PanelEdificios panelMenuEdificios;
	private PanelCaracteristicas panelMenuCaracteristicas;
	
	private JLabel CiudadActual,Tiempo,ImagenPais,TextoPista,InformacionPais;
	
	private String RutaImagenPais = "/vistas/imagenes/paises/";
	private String HorasTiempo;
	private String NombrePaisActual;
	private RelojDigital Reloj = new RelojDigital();

	
	public VistaPartida(PartidaObservable partida) {
			this.partida = partida;
			this.partida.addObserver(this);
			PanelGeneral = new JPanel();
			panelMenuParaViajar = new PanelViajar(PanelGeneral,partida);
			panelMenuEdificios = new PanelEdificios(PanelGeneral,partida);
			panelMenuCaracteristicas = new PanelCaracteristicas(PanelGeneral,partida);
			crearVentana();
			updateHora();
			NombrePaisActual = partida.getPaisActual();
			updateCiudadActual();
			updateImagenPais();
			updateTextos();
		
			
	}


	@Override
	public void update(Observable arg0, Object arg1) {
		if (partida.partidaFinalizada()){
			mostrarMensaje(partida.getPistaActual(), "Mensaje InterPol:");
		}
		
		updateHora();
		updateCiudadActual();
		updateImagenPais();
		updateTextos();
		panelMenuParaViajar.ocultarPanel();
		panelMenuEdificios.ocultarPanel();
		panelMenuCaracteristicas.ocultarPanel();
	
	}
	
	private void updateCiudadActual(){
		NombrePaisActual = partida.getPaisActual();
		CiudadActual.setText(NombrePaisActual);
	}
	
	private void updateHora(){
		HorasTiempo = Reloj.HoraDigital(partida.getTiempoRestante());
		Tiempo.setText(HorasTiempo);
	}
	private void updateImagenPais(){
		
		URL imagen = VentanaPrincipal.class.getResource(this.RutaImagenPais + this.NombrePaisActual +".jpg");
		
		if (imagen != null){
			ImagenPais.setIcon(new ImageIcon(VentanaPrincipal.class.getResource(this.RutaImagenPais + this.NombrePaisActual +".jpg")));
		}
		
	}
	
	private void updateTextos(){
		String nuevapista = partida.getPistaActual() ;
		if (nuevapista != null){
			TextoPista.setText("<html> Pista: <br>" + nuevapista +"</html>");
		}
		else{
			TextoPista.setText("<html>Informacion general.<br>");
		}
		
		String InfoPais = partida.getInformacionPaisActual();
		if (InfoPais != null){
			InformacionPais.setText("<html> Informacion :<br>" + InfoPais +"</html>");
		}
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
		
		ImagenPais = new JLabel("");
		ImagenPais.setBackground(Color.BLACK);
		ImagenPais.setBounds(10, 212, 274, 256);
		PanelGeneral.add(ImagenPais);
		
		InformacionPais = new JLabel("");
		InformacionPais.setForeground(Color.WHITE);
		InformacionPais.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 15));
		InformacionPais.setBackground(Color.BLACK);
		InformacionPais.setVerticalTextPosition(SwingConstants.TOP);
		InformacionPais.setVerticalAlignment(SwingConstants.TOP);
		InformacionPais.setBounds(10, 92, 274, 112);
		PanelGeneral.add(InformacionPais);
		
		CiudadActual = new JLabel();
		CiudadActual.setHorizontalAlignment(SwingConstants.CENTER);
		CiudadActual.setFont(new Font("Stencil", Font.PLAIN, 20));
		CiudadActual.setForeground(new Color(255, 255, 255));
		CiudadActual.setBounds(9, 18, 275, 25);
		PanelGeneral.add(CiudadActual);
		
		Tiempo = new JLabel();
		Tiempo.setHorizontalAlignment(SwingConstants.CENTER);
		Tiempo.setForeground(new Color(255, 255, 255));
		Tiempo.setFont(new Font("LCD", Font.PLAIN, 18));
		Tiempo.setBounds(19, 42, 255, 25);
		PanelGeneral.add(Tiempo);
		
		TextoPista = new JLabel("<html> INFORMACION PARA MOSTRAR:\r\n - Pistas.\r\n -Posibles Ladrones.\r\n -Orden de Arresto Emitida.\r\n -Info del Pais.\r\n -Si gano o Perdio.\r\nEntrar 384 chars</html>");
		TextoPista.setVerticalTextPosition(SwingConstants.TOP);
		TextoPista.setVerticalAlignment(SwingConstants.TOP);
		TextoPista.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 15));
		TextoPista.setForeground(new Color(255, 255, 255));
		TextoPista.setBounds(310, 24, 307, 214);
		PanelGeneral.add(TextoPista);
		
		JButton BotonInvestigar = new JButton("Edificios");
		BotonInvestigar.setFocusPainted(false);
		BotonInvestigar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BotonInvestigar.setBorderPainted(false);
		BotonInvestigar.setMargin(new Insets(3, 28, 0, 10));
		BotonInvestigar.setIcon(new ImageIcon(VistaPartida.class.getResource("/vistas/imagenes/botones/BotonInvestigar.png")));
		BotonInvestigar.setRolloverIcon(new ImageIcon(VistaPartida.class.getResource("/vistas/imagenes/botones/BotonInvestigarApretado.png")));
		BotonInvestigar.setBounds(428, 403, 80, 77);
		BotonInvestigar.addActionListener(new ControladorBotonInvestigar(this));
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
		BotonFiltrar.addActionListener(new ControladorBotonCaracteristica(this));
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
	
	private void mostrarMensaje(String string,String titulo) {
		JOptionPane.showMessageDialog(this,string,titulo,JOptionPane.INFORMATION_MESSAGE);
	}
}
