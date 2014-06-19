package fiuba.algo3.tpfinal.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import modelo.BaseDeDatos;
import modelo.Caracteristicas;
import modelo.Edificio;
import modelo.Ladron;
import modelo.ObjetoRobado;
import modelo.Pais;
import modelo.Partida;
import modelo.Policia;
import modelo.Tiempo;
import modelo.Trayectoria;
import modelo.excepcion.ErrorEdificioNoEstaEnPais;

import org.junit.Test;

public class PartidaTest {
	private Policia UnPolicia;
	private Tiempo UnTiempo;
	private BaseDeDatos UnaBaseDeDatos;
	private Ladron UnLadron;
	private ObjetoRobado UnObjeto;
	private Partida UnaPartida;
	private Pais[] Paises;
	
	private void CrearDatos(){
		UnPolicia = new Policia("Gorgori",0);
		UnTiempo = new Tiempo(200);
		UnPolicia.setTiempo(UnTiempo);
		UnaBaseDeDatos = new BaseDeDatos();
		UnObjeto = new ObjetoRobado("Pintura","muy valioso");
		
		Caracteristicas caracteristicas = new Caracteristicas("masculino","tennis","rojo","tatuaje","limusina");
		UnLadron = new Ladron("Pancu",caracteristicas);
		Caracteristicas caracteristicas2 = new Caracteristicas("masculino","tennis","rubio","tatuaje","limusina");
		Ladron OtroLadron = new Ladron("Fernando",caracteristicas2);
		
		Pais Argentina = crearPais("Argentina");
		Pais Cuba = crearPais("Cuba");
		Pais Argelia = crearPais("Argelia");
		Pais Alemania = crearPais("Alemania");
		Pais Rusia = crearPais("Rusia");
		Pais Peru = crearPais("Peru");
		
		Pais[] LosPaises = {Argentina,Cuba,Argelia,Alemania,Rusia,Peru};
		Paises = LosPaises;
		
		ArrayList<Pais> PaisesDelTrayecto = new ArrayList<Pais>();
		PaisesDelTrayecto.add(Argentina);
		PaisesDelTrayecto.add(Cuba);
		PaisesDelTrayecto.add(Argelia);
		PaisesDelTrayecto.add(Alemania);
		
		Trayectoria trayecto = new Trayectoria(PaisesDelTrayecto);
		UnLadron.addTrayectoria(trayecto);
		
		UnPolicia.setPaisActual(Argentina);
		
		UnaBaseDeDatos.addPais(Argentina);
		UnaBaseDeDatos.addPais(Cuba);
		UnaBaseDeDatos.addPais(Argelia);
		UnaBaseDeDatos.addPais(Alemania);
		UnaBaseDeDatos.addPais(Rusia);
		UnaBaseDeDatos.addPais(Peru);
		
		UnaBaseDeDatos.addSospechoso(UnLadron);
		UnaBaseDeDatos.addSospechoso(OtroLadron);
		
		
		UnaPartida = new Partida(UnPolicia,UnLadron,UnaBaseDeDatos,UnObjeto);
	}
	
	private Pais crearPais(String Nombre){
		Edificio[] Edificios = {new Edificio("Lugarde"+Nombre,"Pistade"+Nombre),new Edificio("2Lugarde"+Nombre,"2Pistade"+Nombre)};
		Pais unPais = new Pais(Nombre,Edificios);
		return unPais;
	}
	
	@Test
	public void PartidaNoEstaTerminada(){
		CrearDatos();
		assertFalse(UnaPartida.SeTerminoLaPartida());
	}
	
	@Test
	public void ReducirHorasAlPolicaNoDuerme(){
		//Si reduzco menos de 15 horas el policia no tiene que dormir
		CrearDatos();
		int HorasAReducir = 12;
		int HorasIniciales = UnTiempo.Horas();
		UnaPartida.ReducirHorasalPolicia(HorasAReducir);
		
		assertEquals(HorasIniciales - HorasAReducir,UnTiempo.Horas() );
	}
	
	@Test
	public void ReducirHorasAlPolicaSIDuerme(){
		//Si reduzco mas de 15 horas el policia tiene que dormir
		CrearDatos();
		int HorasADormir = 8;
		int HorasAReducir = 18;
		int HorasIniciales = UnTiempo.Horas();
		UnaPartida.ReducirHorasalPolicia(HorasAReducir);
		
		assertEquals(HorasIniciales - (HorasAReducir + HorasADormir) ,UnTiempo.Horas() );
	}
	
	@Test
	public void NombreYValorObjetoRobado(){
		CrearDatos();
		assertEquals(UnObjeto.getNombre(),UnaPartida.NombreObjetoRobado());
		assertEquals(UnObjeto.getValor(),UnaPartida.ValorObjetoRobado());
	}
	
	@Test
	public void NombresPosiblesEdificiosAMostrar(){
		CrearDatos();
		ArrayList<String> PosiblesEdificios = UnaPartida.NombresDeEdificiosAMostrar();
		
		Pais Argentina = Paises[0];
		
		String NombreEdificio1 = (Argentina.getNombresDeEdificios()).get(0);
		String NombreEdificio2 = (Argentina.getNombresDeEdificios()).get(1);
		
		assertTrue(PosiblesEdificios.contains(NombreEdificio1));
		assertTrue(PosiblesEdificios.contains(NombreEdificio2));
	}
	
	@Test
	public void MostrarPistaDeEdificio() throws ErrorEdificioNoEstaEnPais{
		CrearDatos();
		Pais Argentina = Paises[0];
		
		String NombreEdificio = (Argentina.getNombresDeEdificios()).get(0);
		
		String UnaPista = UnaPartida.MostrarPistaDeEdificio(NombreEdificio);
		
		assertEquals(UnaPista, Argentina.getPistaDeEdificio(NombreEdificio));
		
	}
}
