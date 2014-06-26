package modelo;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import vistas.VentanaPrincipal;

public class Juego {
	
	PartidaObservable partida;
	CarmenSanDiego carmen;
	VentanaPrincipal ventanaPrincipal;
	
	public Juego(){
		
		ventanaPrincipal = new VentanaPrincipal();
		
		try {
			carmen = new CarmenSanDiego();
		} catch (ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| InstantiationException | ParserConfigurationException
				| SAXException | IOException e) {
			
			ventanaPrincipal.mostrarError("No se puede iniciar el juego");
		}
		
		
		
	}
	
	public void iniciar(){
		ventanaPrincipal.mostrarVentana();
	}
	
}
