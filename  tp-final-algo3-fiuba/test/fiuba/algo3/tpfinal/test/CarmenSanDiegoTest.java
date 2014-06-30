package fiuba.algo3.tpfinal.test;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.junit.After;
import org.junit.Test;
import org.junit.Assert;
import org.xml.sax.SAXException;







import modelo.*;
import modelo.caracteristicas.Cabello;
import modelo.caracteristicas.Caracteristicas;
import modelo.caracteristicas.Hobby;
import modelo.caracteristicas.Senia;
import modelo.caracteristicas.Sexo;
import modelo.caracteristicas.Vehiculo;
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
	}

	@Test
	public void CarmenCargaPolicias() throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, ParserConfigurationException, SAXException, IOException, TransformerException{
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
	public void CarmenCargaLadrones() throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, ParserConfigurationException, SAXException, IOException{
		CarmenSanDiego Carmen = new CarmenSanDiego();
		Carmen.limpiarTodoslosDatos();
		Ladron LadronUno = new Ladron("Tito",null);
		Ladron LadronDos = new Ladron("Jose",null);
		Carmen.agregarLadron(LadronUno);
		Carmen.agregarLadron(LadronDos);
		Assert.assertTrue(Carmen.ladronEstaEnJuego(LadronUno));
		Assert.assertTrue(Carmen.ladronEstaEnJuego(LadronDos));
	}
	
	@Test
	public void CarmenCreaXMLConPolicias() throws ParserConfigurationException, TransformerException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, InstantiationException, SAXException, IOException{
		CarmenSanDiego Carmen = new CarmenSanDiego();
		Carmen.limpiarTodoslosDatos();
		Policia unPolicia = new Policia("Facu",4);
		Carmen.agregarPolicia(unPolicia);
		Policia otroPolicia = new Policia("Tomy",10);
		Carmen.agregarPolicia(otroPolicia);
		Carmen.bajarPoliciasAXML("pruebaXMLPolicia.xml");
		Assert.assertTrue(Carmen.archivoPoliciasExiste());
	}
	@Test
	public void CarmenCreaXMLConLadrones() throws ParserConfigurationException, TransformerException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, InstantiationException, SAXException, IOException{
		CarmenSanDiego Carmen = new CarmenSanDiego();
		Carmen.limpiarTodoslosDatos();
		Caracteristicas caracteristicas1=new Caracteristicas(null,null,null,null,null);
		Caracteristicas caracteristicas2=new Caracteristicas(Sexo.MASCULINO,Hobby.ALPINISMO,Cabello.CASTANIO,Senia.JOYAS,Vehiculo.DESCAPOTABLE);
		Ladron LadronUno = new Ladron("Tito",caracteristicas1);
		Ladron LadronDos = new Ladron("Jose",caracteristicas2);
		Carmen.agregarLadron(LadronUno);
		Carmen.agregarLadron(LadronDos);
		Carmen.bajarLadronesAXML("prueba2XMLLadrones.xml");
		Assert.assertTrue(Carmen.archivoLadronesExiste());
	
	}
	@Test
	public void CarmenLevantaXMLConLadrones() throws ParserConfigurationException, TransformerException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
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
		Carmen.bajarLadronesAXML("pruebaLadronesXML.xml");
		Carmen.levantarLadronesDelXML("pruebaLadronesXML.xml");
		Assert.assertTrue(Carmen.ladronEstaEnJuego(LadronUno));
		Assert.assertTrue(Carmen.ladronEstaEnJuego(LadronDos));
	}
	@Test
	public void CarmenLevantaXMLConPolicias() throws ParserConfigurationException, TransformerException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
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
	public void CarmenIniciaJugadorQueNoExiste() throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, ParserConfigurationException, SAXException, IOException, TransformerException{
		CarmenSanDiego Carmen = new CarmenSanDiego();
		Carmen.limpiarTodoslosDatos();
		Policia policiaBase = new Policia("El Tito",0);
		Assert.assertFalse(Carmen.policiaEstaEnJuego(policiaBase));
		Policia policiaAIniciar = Carmen.iniciarJugador("El Tito");
		Assert.assertTrue(policiaAIniciar.equals(policiaBase));
		Assert.assertTrue(Carmen.policiaEstaEnJuego(policiaBase));
}
	@Test
	public void CarmenIniciaJugadorQueExiste() throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, ParserConfigurationException, SAXException, IOException, TransformerException{
		CarmenSanDiego Carmen = new CarmenSanDiego();
		Carmen.limpiarTodoslosDatos();
		Policia policiaBase = new Policia("El Tito",0);
		Carmen.agregarPolicia(policiaBase);
		Assert.assertTrue(Carmen.policiaEstaEnJuego(policiaBase));
		Policia policiaAIniciar = Carmen.iniciarJugador("El Tito");
		Assert.assertTrue(policiaAIniciar.equals(policiaBase));
	}
	@Test
	public void CarmenArrancaPartida() throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, ParserConfigurationException, SAXException, IOException, TransformerException, ErrorNoSeEncontroLadron, ErrorNoSeEncontroPais, ErrorObjetoNoEncontrado{
		CarmenSanDiego Carmen = new CarmenSanDiego();
		Carmen.limpiarTodoslosDatos();
		Policia unPolicia = new Policia("Facu",4);
		Carmen.agregarPolicia(unPolicia);
		Policia otroPolicia = new Policia("Tomy",10);
		Carmen.agregarPolicia(otroPolicia);
		Caracteristicas caracteristicas1=new Caracteristicas(Sexo.FEMENINO,Hobby.CROQUET,null,null,null);
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
		HashMap<String,String[]> paisesPistas = new HashMap<String,String[]>();
		String[] pistas = {"Pista 1","Pista2","HeridaCuchillo"};
		String[] otrasPistas = {"Pista4","Pista5","Pista6"};
		paisesPistas.put("Argentina",pistas );
		paisesPistas.put("Brasil",otrasPistas);
		String[] Paises = {"Argentina","Brasil"};
		Carmen.generarPartidaXML("PartidasNovato.xml");
		Carmen.agregarPartidasAlXML("PartidasNovato.xml",paisesPistas, Paises, "Jose","Pipa","Normal");
		Carmen.iniciarPartida("Facu");
		Assert.assertTrue(Carmen.getPartida().nombreObjetoRobado().equals("Pipa"));
	}
}