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

import org.junit.Test;

public class PartidaTest {
	private Policia UnPolicia;
	private BaseDeDatos UnaBaseDeDatos;
	private Ladron UnLadron;
	private ObjetoRobado UnObjeto;
	private Partida UnaPartida;
	private Pais[] Paises;
	
	private void CrearDatos(){
		UnPolicia = new Policia("Gorgori",0);
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
		Edificio[] Edificios = {new Edificio("Lugarde"+Nombre,"Pistade"+Nombre)};
		Pais unPais = new Pais(Nombre,Edificios);
		return unPais;
	}
	
	@Test
	public void PartidaNoEstaTerminada(){
		CrearDatos();
		assertFalse(UnaPartida.SeTerminoLaPartida());
	}
	
}
