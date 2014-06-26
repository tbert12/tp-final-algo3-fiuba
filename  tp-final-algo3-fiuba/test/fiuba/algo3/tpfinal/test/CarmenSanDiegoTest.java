package fiuba.algo3.tpfinal.test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.junit.After;
import org.junit.Test;
import org.junit.Assert;
import org.xml.sax.SAXException;

import modelo.*;
import modelo.caracteristicas.Caracteristicas;


public class CarmenSanDiegoTest {
	@After
	public void after() {
		File archivo1 = new File("RegistroPolicias.xml");
		if (archivo1.exists()) {
			archivo1.delete();
		}
		File archivo2 = new File("RegistroLadrones.xml");
		if (archivo2.exists()){
			archivo2.delete();
		}
	}
	@Test
	public void CarmenCargaPolicias() throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, ParserConfigurationException, SAXException, IOException{
		CarmenSanDiego Carmen = new CarmenSanDiego();
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
		Policia unPolicia = new Policia("Facu",5);
		Carmen.agregarPolicia(unPolicia);
		Policia otroPolicia = new Policia("Tomy",10);
		Carmen.agregarPolicia(otroPolicia);
		Carmen.bajarPoliciasAXML();
		Assert.assertTrue(Carmen.archivoPoliciasExiste());
	}
	@Test
	public void CarmenCreaXMLConLadrones() throws ParserConfigurationException, TransformerException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, InstantiationException, SAXException, IOException{
		CarmenSanDiego Carmen = new CarmenSanDiego();
		Caracteristicas caracteristicas1=new Caracteristicas(null,null,null,null,null);
		Caracteristicas caracteristicas2=new Caracteristicas(null,null,null,null,null);
		Ladron LadronUno = new Ladron("Tito",caracteristicas1);
		Ladron LadronDos = new Ladron("Jose",caracteristicas2);
		Carmen.agregarLadron(LadronUno);
		Carmen.agregarLadron(LadronDos);
		Carmen.bajarLadronesAXML();
		Assert.assertTrue(Carmen.archivoLadronesExiste());
	
	}
	@Test
	public void CarmenLevantaXMLConLadrones() throws ParserConfigurationException, TransformerException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
		CarmenSanDiego Carmen = new CarmenSanDiego();
		Caracteristicas caracteristicas1=new Caracteristicas(null,null,null,null,null);
		Caracteristicas caracteristicas2=new Caracteristicas(null,null,null,null,null);
		Ladron LadronUno = new Ladron("Tito",caracteristicas1);
		Ladron LadronDos = new Ladron("Jose",caracteristicas2);
		Carmen.agregarLadron(LadronUno);
		Carmen.agregarLadron(LadronDos);
		Carmen.bajarLadronesAXML();
		Carmen.levantarLadronesDelXML();
		Assert.assertTrue(Carmen.ladronEstaEnJuego(LadronUno));
		Assert.assertTrue(Carmen.ladronEstaEnJuego(LadronDos));
	}
	@Test
	public void CarmenLevantaXMLConPolicias() throws ParserConfigurationException, TransformerException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
		CarmenSanDiego Carmen = new CarmenSanDiego();
		Policia unPolicia = new Policia("Facu",5);
		Carmen.agregarPolicia(unPolicia);
		Policia otroPolicia = new Policia("Tomy",10);
		Carmen.agregarPolicia(otroPolicia);
		Carmen.bajarPoliciasAXML();
		Carmen.levantarPoliciasDelXML();
		Assert.assertTrue(Carmen.policiaEstaEnJuego(unPolicia));
		Assert.assertTrue(Carmen.policiaEstaEnJuego(otroPolicia));
	
	}
	
@Test
public void CarmenIniciaJugadorQueNoExiste() throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, ParserConfigurationException, SAXException, IOException{
	CarmenSanDiego Carmen = new CarmenSanDiego();
	Policia policiaBase = new Policia("El Tito",0);
	Assert.assertFalse(Carmen.policiaEstaEnJuego(policiaBase));
	Policia policiaAIniciar = Carmen.iniciarJugador("El Tito");
	Assert.assertTrue(policiaAIniciar.equals(policiaBase));
}
}