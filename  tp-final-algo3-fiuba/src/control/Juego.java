package control;


import modelo.CarmenSanDiego;
import modelo.Partida;
import modelo.PartidaObservable;
import modelo.excepcion.ErrorAlCargarDatos;
import vistas.VentanaPrincipal;
import vistas.VistaPartida;




public class Juego{
	
	CarmenSanDiego carmen;
	VentanaPrincipal ventanaPrincipal;
	PartidaObservable partidaObservable;
	VistaPartida vistaPartida;
	
	public Juego(){
		
		ventanaPrincipal = new VentanaPrincipal(this);
		
		try {
			carmen = new CarmenSanDiego();
		} catch (ErrorAlCargarDatos e) {
			ventanaPrincipal.mostrarErrorFinal("No se puede iniciar el juego");
		}
	
	}
	
	public void iniciar(){
		ventanaPrincipal.mostrarVentana();
	}
	
	public void iniciarPartida(String string){
		
		try {
			Partida partida = carmen.iniciarPartida(string);
		} catch (ErrorAlCargarDatos e) {

			ventanaPrincipal.mostrarErrorFinal("No se puede iniciar el juego. Contactate con el administrador del sistemas." + e.toString());
		}
		
		
		partidaObservable = new PartidaObservable(partida);
		vistaPartida = new VistaPartida(partidaObservable,this);
		
		ventanaPrincipal.ocultarVentana();
		vistaPartida.mostrarVentana();
	}

	public void cerrarPartida() {
		vistaPartida.ocultarVentana();
		ventanaPrincipal.mostrarVentana();
		try {
			carmen.almacenarDatos();
		} catch (ErrorAlCargarDatos e) {
			ventanaPrincipal.mostrarErrorFinal("No se pueden guardar los avances realizados." );
		}	
		
		vistaPartida.cerrar();
		
		
	}

}
