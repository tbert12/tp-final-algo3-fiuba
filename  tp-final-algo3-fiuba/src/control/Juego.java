package control;


import modelo.CarmenSanDiego;
import modelo.Partida;
import modelo.PartidaObservable;
import modelo.excepcion.ErrorAlCargarDatos;
import vistas.Sonidos;
import vistas.VentanaPrincipal;
import vistas.VistaPartida;




public class Juego{
	
	CarmenSanDiego carmen;
	VentanaPrincipal ventanaPrincipal;
	VistaPartida vistaPartida;
	Sonidos sonidos;
	
	public Juego(){
		sonidos = new Sonidos();
		ventanaPrincipal = new VentanaPrincipal(this,this.sonidos);
		
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
		Partida partida; 
		try {
			partida = carmen.iniciarPartida(string);
			PartidaObservable partidaObservable = new PartidaObservable(partida);
			vistaPartida = new VistaPartida(partidaObservable,this,this.sonidos);
		} catch (ErrorAlCargarDatos e) {

			ventanaPrincipal.mostrarErrorFinal("No se puede iniciar el juego. Contactate con el administrador del sistemas.");
		}
		
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
