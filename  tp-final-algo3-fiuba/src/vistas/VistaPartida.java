package vistas;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import vistas.paneles.PanelViajar;
import vistas.paneles.PanelEdificios;
import vistas.paneles.PanelCaracteristicas;
import vistas.relojdigital.RelojDigital;
import modelo.PartidaObservable;
import control.ControladorBotonFiltrar;
import control.ControladorBotonViajar;
import control.ControladorBotonInvestigar;
import control.Juego;

public class VistaPartida extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	
	private PartidaObservable partida;
	
	private JPanel PanelGeneral;
	private Juego juego;
	
	private PanelViajar panelMenuParaViajar;
	private PanelEdificios panelMenuEdificios;
	private PanelCaracteristicas panelMenuCaracteristicas;
	private Sonidos sonidos;
	private JLabel TextoInterpool,CiudadActual,Tiempo,ImagenPais,TextoPista,InformacionPais;
	private String RutaImagenPais = "/vistas/imagenes/paises/";
	private String HorasTiempo;
	private String NombrePaisActual;
	private RelojDigital Reloj;
	private Timer timer;

	
	public VistaPartida(PartidaObservable partida,Juego juego,Sonidos sonidos) {
		this.juego = juego;	
		this.sonidos = sonidos;
		this.partida = partida;
		this.partida.addObserver(this);  
		PanelGeneral = new JPanel();
		panelMenuParaViajar = new PanelViajar(PanelGeneral,partida);
		panelMenuEdificios = new PanelEdificios(PanelGeneral,partida);
		panelMenuCaracteristicas = new PanelCaracteristicas(PanelGeneral,partida);
		crearVentana();
		NombrePaisActual = partida.getPaisActual();
		updateCiudadActual();
		updateImagenPais();
		mensajeDeBienvenida();
		Reloj = new RelojDigital();
		updateHora();
		Tiempo.setText(Reloj.ObtenerHoraDigital());
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				HorasTiempo = Reloj.AvanzarUnaHora();
				Tiempo.setText(HorasTiempo);
			}
		});
		timer.start();
	}


	private void mensajeDeBienvenida(){
		String nombreDelPolicia = partida.getNombredelPolicia();
		String rangoDelPolicia = partida.getRangodelPolicia();
		String ObjetoRobado = partida.getObjetoRobado();
		String PaisActual = partida.getPaisActual();
		InformacionPais.setText("<html>Hola, has sido identificado(a) como: " + nombreDelPolicia + 
				                "<br><br> Tu rango actual es: " + rangoDelPolicia + ".</html>");
		TextoInterpool.setText("<html>+++++++++++++NOTICIAS+++++++++++++++<br> "+ 
				                "Tesoro nacional robado en " + PaisActual + 
				                ".<br><br> El botin ha sido identificado como: " + ObjetoRobado + 
				                ".<br>Tu mision:Persigue al ladron desde " + PaisActual +
								" hasta atraparlo. Tienes tiempo hasta el "
								+ "Domingo a las 17:00hs. <br><br> ¡Suerte!"  );
		
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		updateHora();
		if (partida.tiempoAgotado()){
			mostrarMensaje("Tiempo agotado, el ladron se escapo.", "Mensaje Interpool");
			partida.finalizarPartida();
			//le aviso a la ventana principal que termine
			juego.cerrarPartida();
		}
		
		if (partida.partidaFinalizada()){
			mostrarMensaje(partida.getPistaActual(), "Mensaje Interpool");
			partida.finalizarPartida();
			//le aviso a la ventana principal que termine
			juego.cerrarPartida();
		}
		
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
	
	private void updateHora() { 
		Reloj.ActualizarHora(partida.getTiempoRestante());
	}
	private void updateImagenPais(){
		
		URL imagen = VentanaPrincipal.class.getResource(this.RutaImagenPais + this.NombrePaisActual +".jpg");
		
		if (imagen != null){
			ImagenPais.setIcon(new ImageIcon(VentanaPrincipal.class.getResource(this.RutaImagenPais + this.NombrePaisActual +".jpg")));
		}
		
	}
	
	private void updateTextos(){
		String pistadeEdificio = partida.getPistaActual() ;
		ArrayList<String> NombreLadronesFiltrados = partida.nombreDeSospechosos();
		if (pistadeEdificio == null && NombreLadronesFiltrados == null){
			TextoInterpool.setVisible(false);
			TextoPista.setVisible(false);
		}
		else if (NombreLadronesFiltrados == null){
			TextoInterpool.setVisible(false);
			String Mensaje = "Pista: <br>";
			if (pistadeEdificio.equals("HeridaArmaDeFuego")){
				Mensaje = "Cuidado: <br>";
				pistadeEdificio = "Has sido herido por un arma de Fuego";
			}
			if (pistadeEdificio.equals("HeridaCuchillo")){
				Mensaje = "Cuidado: <br>";
				pistadeEdificio = "Has sido herido por un arma blanca";
			}
			TextoPista.setText("<html>" + Mensaje + pistadeEdificio + "</html>");
			TextoPista.setVisible(true);
		}
		else if(pistadeEdificio == null){
			TextoPista.setVisible(false);
			TextoInterpool.setText("<html>" + generarMensajedeInterpool(NombreLadronesFiltrados) +"</html>");
			TextoInterpool.setVisible(true);
		}
		
		String InfoPais = partida.getInformacionPaisActual();
		if (InfoPais != null){
			InformacionPais.setText("<html>" + InfoPais + "</html>");
		}
	}
	
	private String generarMensajedeInterpool(ArrayList<String> Nombres) {
		String mensaje = "Mensaje de la Interpool: <br>";
		if (Nombres.size() == 0) mensaje = mensaje +  "No se han encontrado coincidencias con ningun sospechoso";
		else if (Nombres.size() == 1) mensaje = mensaje +  "Orden de arresto emitida para: <br>";
		else mensaje = mensaje + "Se encontraron " + Integer.toString(Nombres.size()) + " coincidencias"; 
		
		Iterator<String> iteradorNombres = Nombres.iterator();
		while (iteradorNombres.hasNext()){
			mensaje = mensaje + " -" + iteradorNombres.next() + "<br>";
		}
		return mensaje;
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
		InformacionPais.setFont(new Font("Verdana", Font.PLAIN, 10));
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
		
		TextoPista = new JLabel();
		TextoPista.setVerticalTextPosition(SwingConstants.TOP);
		TextoPista.setVerticalAlignment(SwingConstants.TOP);
		TextoPista.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 15));
		TextoPista.setForeground(new Color(255, 255, 255));
		TextoPista.setBounds(310, 24, 307, 214);
		PanelGeneral.add(TextoPista);
		
		TextoInterpool = new JLabel();
		TextoInterpool.setVerticalTextPosition(SwingConstants.TOP);
		TextoInterpool.setVerticalAlignment(SwingConstants.TOP);
		TextoInterpool.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 15));
		TextoInterpool.setForeground(new Color(255, 255, 255));
		TextoInterpool.setBounds(310, 24, 307, 214);
		PanelGeneral.add(TextoInterpool);
		
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
		BotonViajar.addActionListener(new ControladorBotonViajar(this,this.sonidos));
		PanelGeneral.add(BotonViajar);
		
		JButton BotonFiltrar = new JButton("Filtrar");
		BotonFiltrar.setFocusPainted(false);
		BotonFiltrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BotonFiltrar.setBorderPainted(false);
		BotonFiltrar.setMargin(new Insets(3, 28, 0, 10));
		BotonFiltrar.setIcon(new ImageIcon(VistaPartida.class.getResource("/vistas/imagenes/botones/BotonFiltrarNormal.png")));
		BotonFiltrar.addActionListener(new ControladorBotonFiltrar(this,this.sonidos));
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
	public void cerrar() {
		this.dispose();	
	}


	public void ocultarVentana() {
		setVisible(false);
	}
	
}
