package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vistas.paneles.PanelCaracteristicas;
import modelo.PartidaObservable;
import modelo.caracteristicas.*;

public class ControladorBotonBuscar implements ActionListener{
	private PartidaObservable partida;
	PanelCaracteristicas panel;
	
	public ControladorBotonBuscar(PartidaObservable partida,PanelCaracteristicas panel){
		this.partida = partida;
		this.panel = panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Sexo unSexo = Sexo.fromString(panel.obtenerSexoSeleccionado());
		Hobby unHobby = Hobby.fromString(panel.obtenerHobbySeleccionado());
		Cabello unCabello = Cabello.fromString(panel.obtenerCabelloSeleccionado());
		Senia unaSenia = Senia.fromString(panel.obtenerSeniaSeleccionado());
		Vehiculo unVehiculo = Vehiculo.fromString(panel.obtenerVehiculoSeleccionado());
		partida.filtrarLadron(unSexo, unHobby, unCabello, unaSenia, unVehiculo);
	}

}
