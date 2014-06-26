package fiuba.algo3.tpfinal.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import modelo.BaseDeDatos;
import modelo.Coordenadas;
import modelo.Edificio;
import modelo.Ladron;
import modelo.ObjetoRobado;
import modelo.Pais;
import modelo.Partida;
import modelo.Policia;
import modelo.Tiempo;
import modelo.Trayectoria;
import modelo.caracteristicas.Cabello;
import modelo.caracteristicas.Caracteristicas;
import modelo.caracteristicas.Hobby;
import modelo.caracteristicas.Senia;
import modelo.caracteristicas.Sexo;
import modelo.caracteristicas.Vehiculo;
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
		
		Caracteristicas caracteristicas = new Caracteristicas(Sexo.MASCULINO,Hobby.TENNIS,Cabello.ROJO,Senia.TATUAJE,Vehiculo.LIMUSINA);
		UnLadron = new Ladron("Pancu",caracteristicas);
		Caracteristicas caracteristicas2 = new Caracteristicas(Sexo.MASCULINO,Hobby.TENNIS,Cabello.RUBIO,Senia.TATUAJE,Vehiculo.LIMUSINA);
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
		Coordenadas coordenadaPais = new Coordenadas(0,0);
		Pais unPais = new Pais(Nombre,Edificios,coordenadaPais);
		return unPais;
	}
	
	@Test
	public void PartidaNoEstaTerminada(){
		CrearDatos();
		assertFalse(UnaPartida.seTerminoLaPartida());
	}
	
	@Test
	public void NombreYValorObjetoRobado(){
		CrearDatos();
		assertEquals(UnObjeto.getNombre(),UnaPartida.nombreObjetoRobado());
		assertEquals(UnObjeto.getValor(),UnaPartida.valorObjetoRobado());
	}
	
	@Test
	public void PosiblesEdificiosAMostrar(){
		CrearDatos();
		ArrayList<Edificio> PosiblesEdificios = UnaPartida.edificiosAMostrar();
		
		Pais Argentina = Paises[0];
		
		Edificio Edificio1 = (Argentina.getEdificios()).get(0);
		Edificio Edificio2 = (Argentina.getEdificios()).get(1);
		
		assertTrue(PosiblesEdificios.contains(Edificio1));
		assertTrue(PosiblesEdificios.contains(Edificio2));
	}
	
	@Test
	public void MostrarPistaDeEdificio() throws ErrorEdificioNoEstaEnPais{
		CrearDatos();
		Pais Argentina = Paises[0];
		
		Edificio UnEdificio = (Argentina.getEdificios()).get(0);
		
		String UnaPista = UnaPartida.visitarEdificio(UnEdificio);
		
		assertEquals(UnaPista, UnEdificio.visitar(UnPolicia));
		
	}
}
