package vistas;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
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
import vistas.relojdigital.HiloDelReloj;
import vistas.relojdigital.RelojDigital;
import modelo.PartidaObservable;
import control.ControladorBotonFiltrar;
import control.ControladorBotonViajar;
import control.ControladorBotonInvestigar;
import control.Juego;

public class VistaPartida extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	
	private PartidaObservable partida;
	
	private JPanel panelGeneral;
	private Juego juego;
	
	private PanelViajar panelMenuParaViajar;
	private PanelEdificios panelMenuEdificios;
	private PanelCaracteristicas panelMenuCaracteristicas;
	private Sonidos sonidos;
	private JLabel textoInterpool,ciudadActual,tiempo,imagenPais,textoPista,informacionPais;
	private final String rutaImagenPais = "/vistas/imagenes/paises/";
	private String nombrePaisActual;
	private RelojDigital reloj;
	private Timer timer;

	
	public VistaPartida(PartidaObservable partida,Juego juego) {
		this.juego = juego;	
		this.sonidos = Sonidos.ObtenerSonidos();
		this.partida = partida;
		this.partida.addObserver(this);  
		panelGeneral = new JPanel();
		panelMenuParaViajar = new PanelViajar(panelGeneral,partida);
		panelMenuEdificios = new PanelEdificios(panelGeneral,partida);
		panelMenuCaracteristicas = new PanelCaracteristicas(panelGeneral,partida);
		crearVentana();
		nombrePaisActual = partida.getPaisActual();
		updateCiudadActual();
		updateImagenPais();
		mensajeDeBienvenida();
		reloj = new RelojDigital();
		updateHora();
		timer = new Timer(200, new HiloDelReloj(reloj,tiempo));
		timer.start();
	}


	private void mensajeDeBienvenida(){
		String nombreDelPolicia = partida.getNombredelPolicia();
		String rangoDelPolicia = partida.getRangodelPolicia();
		String ObjetoRobado = partida.getObjetoRobado();
		String PaisActual = partida.getPaisActual();
		informacionPais.setText("<html>Hola, has sido identificado(a) como: " + nombreDelPolicia + 
				                "<br><br> Tu rango actual es: " + rangoDelPolicia + ".</html>");
		textoInterpool.setText("<html>+++++++++++++NOTICIAS+++++++++++++++<br> "+ 
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
			sonidos.pausa();
			sonidos.reproducirSonidoFallido();
			
			mostrarMensaje("Tiempo agotado, el ladron se escapo.", "Mensaje Interpool");
			
			partida.finalizarPartida();
			//le aviso a la ventana principal que termine
			juego.cerrarPartida();
		}
		
		if (partida.partidaFinalizada()){
			sonidos.pausa();
			if (partida.ladronArrestado())sonidos.reproducirSonidoExitoso(); 
			else sonidos.reproducirSonidoFallido();
			
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
		nombrePaisActual = partida.getPaisActual();
		ciudadActual.setText(nombrePaisActual);
	}
	
	private void updateHora() { 
		reloj.ActualizarHora(partida.getTiempoRestante());
	}
	private void updateImagenPais(){
		
		URL imagen = VentanaPrincipal.class.getResource(this.rutaImagenPais + this.nombrePaisActual +".jpg");
		
		if (imagen != null){
			imagenPais.setIcon(new ImageIcon(VentanaPrincipal.class.getResource(this.rutaImagenPais + this.nombrePaisActual +".jpg")));
		}
		
	}
	
	private void updateTextos(){
		String pistadeEdificio = partida.getPistaActual() ;
		ArrayList<String> NombreLadronesFiltrados = partida.nombreDeSospechosos();
		if (pistadeEdificio == null && NombreLadronesFiltrados == null){
			textoInterpool.setVisible(false);
			textoPista.setVisible(false);
		}
		else if (NombreLadronesFiltrados == null){
			textoInterpool.setVisible(false);
			String Mensaje = "Pista: <br>";
			if (pistadeEdificio.equals("HeridaArmaDeFuego")){
				Mensaje = "Cuidado: <br>";
				pistadeEdificio = "Has sido herido por un arma de Fuego";
				sonidos.reproducirSonidoHeridaArma();
			}
			if (pistadeEdificio.equals("HeridaCuchillo")){
				Mensaje = "Cuidado: <br>";
				pistadeEdificio = "Has sido herido por un arma blanca";
				sonidos.reproducirSonidoHeridaCuchillo();
			}
			textoPista.setText("<html>" + Mensaje + pistadeEdificio + "</html>");
			textoPista.setVisible(true);
		}
		else if(pistadeEdificio == null){
			textoPista.setVisible(false);
			textoInterpool.setText("<html>" + generarMensajedeInterpool(NombreLadronesFiltrados) +"</html>");
			textoInterpool.setVisible(true);
		}
		
		String InfoPais = partida.getInformacionPaisActual();
		if (InfoPais != null){
			informacionPais.setText("<html>" + InfoPais + "</html>");
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
		panelGeneral.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelGeneral);
		setLocationRelativeTo(null);
		panelGeneral.setLayout(null);
		
		imagenPais = new JLabel("");
		imagenPais.setBackground(Color.BLACK);
		imagenPais.setBounds(10, 212, 274, 256);
		panelGeneral.add(imagenPais);
		
		informacionPais = new JLabel("");
		informacionPais.setForeground(Color.WHITE);
		informacionPais.setFont(new Font("Verdana", Font.PLAIN, 10));
		informacionPais.setBackground(Color.BLACK);
		informacionPais.setVerticalTextPosition(SwingConstants.TOP);
		informacionPais.setVerticalAlignment(SwingConstants.TOP);
		informacionPais.setBounds(10, 92, 274, 112);
		panelGeneral.add(informacionPais);
		
		ciudadActual = new JLabel();
		ciudadActual.setHorizontalAlignment(SwingConstants.CENTER);
		ciudadActual.setFont(new Font("Stencil", Font.PLAIN, 20));
		ciudadActual.setForeground(new Color(255, 255, 255));
		ciudadActual.setBounds(9, 18, 275, 25);
		panelGeneral.add(ciudadActual);
		
		tiempo = new JLabel();
		tiempo.setHorizontalAlignment(SwingConstants.CENTER);
		tiempo.setForeground(new Color(255, 255, 255));
		tiempo.setFont(new Font("LCD", Font.PLAIN, 18));
		tiempo.setBounds(19, 42, 255, 25);
		panelGeneral.add(tiempo);
		
		textoPista = new JLabel();
		textoPista.setVerticalTextPosition(SwingConstants.TOP);
		textoPista.setVerticalAlignment(SwingConstants.TOP);
		textoPista.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 15));
		textoPista.setForeground(new Color(255, 255, 255));
		textoPista.setBounds(310, 24, 307, 214);
		panelGeneral.add(textoPista);
		
		textoInterpool = new JLabel();
		textoInterpool.setVerticalTextPosition(SwingConstants.TOP);
		textoInterpool.setVerticalAlignment(SwingConstants.TOP);
		textoInterpool.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 15));
		textoInterpool.setForeground(new Color(255, 255, 255));
		textoInterpool.setBounds(310, 24, 307, 214);
		panelGeneral.add(textoInterpool);
		
		JButton BotonInvestigar = new JButton("Edificios");
		BotonInvestigar.setFocusPainted(false);
		BotonInvestigar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BotonInvestigar.setBorderPainted(false);
		BotonInvestigar.setMargin(new Insets(3, 28, 0, 10));
		BotonInvestigar.setIcon(new ImageIcon(VistaPartida.class.getResource("/vistas/imagenes/botones/BotonInvestigar.png")));
		BotonInvestigar.setRolloverIcon(new ImageIcon(VistaPartida.class.getResource("/vistas/imagenes/botones/BotonInvestigarApretado.png")));
		BotonInvestigar.setBounds(428, 403, 80, 77);
		BotonInvestigar.addActionListener(new ControladorBotonInvestigar(this));
		panelGeneral.add(BotonInvestigar);
		
		JButton BotonViajar = new JButton("Viajar");
		BotonViajar.setFocusPainted(false);
		BotonViajar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BotonViajar.setBorderPainted(false);
		BotonViajar.setMargin(new Insets(3, 28, 0, 10));
		BotonViajar.setIcon(new ImageIcon(VistaPartida.class.getResource("/vistas/imagenes/botones/BotonViajarNormal.png")));
		BotonViajar.setRolloverIcon(new ImageIcon(VistaPartida.class.getResource("/vistas/imagenes/botones/BotonViajarApretado.png")));
		BotonViajar.setBounds(555, 403, 80, 77);
		BotonViajar.addActionListener(new ControladorBotonViajar(this));
		panelGeneral.add(BotonViajar);
		
		JButton BotonFiltrar = new JButton("Filtrar");
		BotonFiltrar.setFocusPainted(false);
		BotonFiltrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BotonFiltrar.setBorderPainted(false);
		BotonFiltrar.setMargin(new Insets(3, 28, 0, 10));
		BotonFiltrar.setIcon(new ImageIcon(VistaPartida.class.getResource("/vistas/imagenes/botones/BotonFiltrarNormal.png")));
		BotonFiltrar.addActionListener(new ControladorBotonFiltrar(this));
		BotonFiltrar.setRolloverIcon(new ImageIcon(VistaPartida.class.getResource("/vistas/imagenes/botones/BotonFiltrarApretado.png")));
		BotonFiltrar.setBounds(298, 403, 80, 77);
		panelGeneral.add(BotonFiltrar);
		
		JLabel FondoConImagen = new JLabel("");
		FondoConImagen.setForeground(new Color(255, 255, 255));
		FondoConImagen.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/vistas/imagenes/Fondo.png")));
		FondoConImagen.setBounds(0, 0, 640, 480);
		panelGeneral.add(FondoConImagen);
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
