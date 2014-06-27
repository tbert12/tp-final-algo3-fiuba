package modelo;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import vistas.VentanaPrincipal;
import vistas.VistaPartida;




public class Juego {
	
	CarmenSanDiego carmen;
	VentanaPrincipal ventanaPrincipal;
	PartidaObservable partidaObservable;
	VistaPartida vistaPartida;
	
	public Juego(){
		
		ventanaPrincipal = new VentanaPrincipal(this);
		
		try {
			carmen = new CarmenSanDiego();
		} catch (ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| InstantiationException | ParserConfigurationException
				| SAXException | IOException e) {
			
			ventanaPrincipal.mostrarErrorFinal("No se puede iniciar el juego");
		}
	
	}
	
	public void iniciar(){
		ventanaPrincipal.mostrarVentana();
	}
	
	public void iniciarPartida(String string){
		
		//carmen.iniciarPartida(string);
		//Partida partida = carmen.getPartida();
		
		//" BORRAR DE ACA -> "/
		_SimuladorCrearPartida Simulador = new _SimuladorCrearPartida();
		Partida partida = Simulador.crearPartida();
		//" <- HASTA	ACA"/	
		
		partidaObservable = new PartidaObservable(partida);
		vistaPartida = new VistaPartida(partidaObservable);
				
		ventanaPrincipal.ocultarVentana();
		vistaPartida.mostrarVentana();
	}
	
}
