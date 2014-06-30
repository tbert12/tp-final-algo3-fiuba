package fiuba.algo3.tpfinal.test;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.junit.After;
import org.junit.Test;
import org.junit.Assert;
import org.xml.sax.SAXException;

import modelo.CarmenSanDiego;
import modelo.Coordenadas;
import modelo.Edificio;
import modelo.Ladron;
import modelo.Pais;
import modelo.Partida;
import modelo.Policia;
import modelo.Trayectoria;
import modelo.caracteristicas.Cabello;
import modelo.caracteristicas.Caracteristicas;
import modelo.caracteristicas.Hobby;
import modelo.caracteristicas.Senia;
import modelo.caracteristicas.Sexo;
import modelo.caracteristicas.Vehiculo;
import modelo.excepcion.ErrorAlCargarDatos;
import modelo.excepcion.ErrorNoSeEncontroLadron;
import modelo.excepcion.ErrorNoSeEncontroPais;
import modelo.excepcion.ErrorObjetoNoEncontrado;


public class CarmenSanDiegoTest {
	@After
	public void after() {
		File archivo1 = new File("pruebaXMLPolicia.xml");
		if (archivo1.exists()) {
			archivo1.delete();
		}
		File archivo2 = new File("prueba2XMLLadrones.xml");
		if (archivo2.exists()){
			archivo2.delete();
		}
		File archivo3 = new File("pruebaLadronesXML.xml");
		if (archivo3.exists()){
			archivo3.delete();
		}
		File archivo4 = new File("pruebaPoliciasXML.xml");
		if (archivo4.exists()) {
			archivo4.delete();
		}
		File archivo5 = new File("pruebaLadron.xml");
		if (archivo5.exists()) {
			archivo5.delete();
		}
	}

	@Test
	public void CarmenCargaPolicias() throws ErrorAlCargarDatos{
		CarmenSanDiego Carmen = new CarmenSanDiego();
		Carmen.limpiarTodoslosDatos();
		Policia unPolicia = new Policia("Facu",5);
		Carmen.agregarPolicia(unPolicia);
		Policia otroPolicia = new Policia("Tomy",10);
		Carmen.agregarPolicia(otroPolicia);
		Assert.assertTrue(Carmen.policiaEstaEnJuego(unPolicia));
		Assert.assertTrue(Carmen.policiaEstaEnJuego(otroPolicia));
	}
	@Test
	public void CarmenCargaLadrones() throws ErrorAlCargarDatos{
		CarmenSanDiego Carmen = new CarmenSanDiego();
		Carmen.limpiarTodoslosDatos();
		Ladron LadronUno = new Ladron("Tito",null);
		Ladron LadronDos = new Ladron("Jose",null);
		Carmen.agregarLadron(LadronUno);
		Carmen.agregarLadron(LadronDos);
		Assert.assertTrue(Carmen.ladronEstaEnJuego(LadronUno.getNombre()));
		Assert.assertTrue(Carmen.ladronEstaEnJuego(LadronDos.getNombre()));
	}
	
	@Test
	public void CarmenCreaXMLConPolicias() throws ParserConfigurationException, TransformerException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, InstantiationException, SAXException, IOException, ErrorAlCargarDatos{
		CarmenSanDiego Carmen = new CarmenSanDiego();
		Carmen.limpiarTodoslosDatos();
		Policia unPolicia = new Policia("Facu",4);
		Carmen.agregarPolicia(unPolicia);
		Policia otroPolicia = new Policia("Tomy",10);
		Carmen.agregarPolicia(otroPolicia);
		Carmen.almacenarDatos();
		Assert.assertTrue(Carmen.archivoPoliciasExiste());
	}
	@Test
	public void CarmenLevantaXMLConLadrones() throws ErrorAlCargarDatos, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, ParserConfigurationException, SAXException, IOException{
		CarmenSanDiego Carmen = new CarmenSanDiego();
		Carmen.limpiarTodoslosDatos();
		Caracteristicas caracteristicas1=new Caracteristicas(null,null,null,null,null);
		Caracteristicas caracteristicas2=new Caracteristicas(Sexo.MASCULINO,Hobby.ALPINISMO,Cabello.CASTANIO,Senia.JOYAS,Vehiculo.DESCAPOTABLE);
		Ladron LadronUno = new Ladron("Tito",caracteristicas1);
		Ladron LadronDos = new Ladron("Jose",caracteristicas2);
		Edificio biblioteca = new Edificio("biblioteca");
		Edificio puerto = new Edificio("puerto");
		Edificio fiuba = new Edificio("fiuba");
		Edificio[] edificios = {biblioteca,puerto,fiuba};
		Pais Argentina = new Pais("Argentina",edificios,new Coordenadas(1, 1));
		Pais Brasil = new Pais("Brasil",edificios,new Coordenadas(1, 3));
		ArrayList<Pais> ListaPaises = new ArrayList<Pais>();
		ListaPaises.add(Argentina);
		ListaPaises.add(Brasil);
		LadronDos.addTrayectoria(new Trayectoria(ListaPaises));
		Carmen.agregarLadron(LadronUno);
		Carmen.agregarLadron(LadronDos);
		Carmen.levantarLadronesDelXML("pruebaLadronesXML.xml");
		Assert.assertTrue(Carmen.ladronEstaEnJuego(LadronUno.getNombre()));
		Assert.assertTrue(Carmen.ladronEstaEnJuego(LadronDos.getNombre()));
	}
	@Test
	public void CarmenLevantaXMLConPolicias() throws ParserConfigurationException, TransformerException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, ErrorAlCargarDatos{
		CarmenSanDiego Carmen = new CarmenSanDiego();
		Carmen.limpiarTodoslosDatos();
		Policia unPolicia = new Policia("Facu",4);
		Carmen.agregarPolicia(unPolicia);
		Policia otroPolicia = new Policia("Tomy",10);
		Carmen.agregarPolicia(otroPolicia);
		Carmen.levantarPoliciasDelXML("pruebaPoliciasXML.xml");
		Assert.assertTrue(Carmen.policiaEstaEnJuego(unPolicia));
		Assert.assertTrue(Carmen.policiaEstaEnJuego(otroPolicia));
	
	}
	
	@Test
	public void CarmenIniciaJugadorQueNoExiste() throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, ParserConfigurationException, SAXException, IOException, TransformerException, ErrorAlCargarDatos{
		CarmenSanDiego Carmen = new CarmenSanDiego();
		Carmen.limpiarTodoslosDatos();
		Policia policiaBase = new Policia("El Tito",0);
		Assert.assertFalse(Carmen.policiaEstaEnJuego(policiaBase));
		Policia policiaAIniciar = Carmen.iniciarJugador("El Tito");
		Assert.assertTrue(policiaAIniciar.equals(policiaBase));
		Assert.assertTrue(Carmen.policiaEstaEnJuego(policiaBase));
}
	@Test
	public void CarmenIniciaJugadorQueExiste() throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, ParserConfigurationException, SAXException, IOException, TransformerException, ErrorAlCargarDatos{
		CarmenSanDiego Carmen = new CarmenSanDiego();
		Carmen.limpiarTodoslosDatos();
		Policia policiaBase = new Policia("El Tito",0);
		Carmen.agregarPolicia(policiaBase);
		Assert.assertTrue(Carmen.policiaEstaEnJuego(policiaBase));
		Policia policiaAIniciar = Carmen.iniciarJugador("El Tito");
		Assert.assertTrue(policiaAIniciar.equals(policiaBase));
	}
	@Test
	public void CarmenArrancaPartida() throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, ParserConfigurationException, SAXException, IOException, TransformerException, ErrorNoSeEncontroLadron, ErrorNoSeEncontroPais, ErrorObjetoNoEncontrado, ErrorAlCargarDatos{
		CarmenSanDiego Carmen = new CarmenSanDiego();
		Carmen.limpiarTodoslosDatos();
		Carmen.levantarTodosLosDatos();
		Caracteristicas caracteristicas1=new Caracteristicas(null,null,null,null,null);
		Ladron unLadron = new Ladron("Nick Brunch",caracteristicas1);
		Carmen.agregarLadron(unLadron);
		Assert.assertTrue(Carmen.ladronEstaEnJuego(unLadron.getNombre()));
		Policia unPolicia = new Policia("Facu",4);
		Carmen.agregarPolicia(unPolicia);
		Policia otroPolicia = new Policia("Tomy",10);
		Carmen.agregarPolicia(otroPolicia);
		Partida unaPartida=Carmen.iniciarPartida("Facu");
	}
}