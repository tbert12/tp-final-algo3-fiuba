package fiuba.algo3.tpfinal.test;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.junit.After;
import org.junit.Test;
import org.junit.Assert;
import org.xml.sax.SAXException;

import modelo.*;


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
	public void CarmenCargaPolicias(){
		CarmenSanDiego Carmen = new CarmenSanDiego();
		Policia unPolicia = new Policia("Facu",5);
		Carmen.AgregarPolicia(unPolicia);
		Policia otroPolicia = new Policia("Tomy",10);
		Carmen.AgregarPolicia(otroPolicia);
		Assert.assertTrue(Carmen.PoliciaEstaEnJuego(unPolicia));
		Assert.assertTrue(Carmen.PoliciaEstaEnJuego(otroPolicia));
	}
	@Test
	public void CarmenCargaLadrones(){
		CarmenSanDiego Carmen = new CarmenSanDiego();
		Ladron LadronUno = new Ladron("Tito",null);
		Ladron LadronDos = new Ladron("Jose",null);
		Carmen.AgregarLadron(LadronUno);
		Carmen.AgregarLadron(LadronDos);
		Assert.assertTrue(Carmen.LadronEstaEnJuego(LadronUno));
		Assert.assertTrue(Carmen.LadronEstaEnJuego(LadronDos));
	}
	
	@Test
	public void CarmenCreaXMLConPolicias() throws ParserConfigurationException, TransformerException{
		CarmenSanDiego Carmen = new CarmenSanDiego();
		Policia unPolicia = new Policia("Facu",5);
		Carmen.AgregarPolicia(unPolicia);
		Policia otroPolicia = new Policia("Tomy",10);
		Carmen.AgregarPolicia(otroPolicia);
		Carmen.BajarPoliciasAXML();
		Assert.assertTrue(Carmen.ArchivoPoliciasExiste());
	}
	@Test
	public void CarmenCreaXMLConLadrones() throws ParserConfigurationException, TransformerException{
		CarmenSanDiego Carmen = new CarmenSanDiego();
		Caracteristicas caracteristicas1=new Caracteristicas(null,null,null,null,null);
		Caracteristicas caracteristicas2=new Caracteristicas(null,null,null,null,null);
		Ladron LadronUno = new Ladron("Tito",caracteristicas1);
		Ladron LadronDos = new Ladron("Jose",caracteristicas2);
		Carmen.AgregarLadron(LadronUno);
		Carmen.AgregarLadron(LadronDos);
		Carmen.BajarLadronesAXML();
		Assert.assertTrue(Carmen.ArchivoLadronesExiste());
	
	}
	@Test
	public void CarmenLevantaXMLConLadrones() throws ParserConfigurationException, TransformerException, SAXException, IOException{
		CarmenSanDiego Carmen = new CarmenSanDiego();
		Caracteristicas caracteristicas1=new Caracteristicas(null,null,null,null,null);
		Caracteristicas caracteristicas2=new Caracteristicas(null,null,null,null,null);
		Ladron LadronUno = new Ladron("Tito",caracteristicas1);
		Ladron LadronDos = new Ladron("Jose",caracteristicas2);
		Carmen.AgregarLadron(LadronUno);
		Carmen.AgregarLadron(LadronDos);
		Carmen.BajarLadronesAXML();
		Carmen.LevantarLadronesDelXML();
		Assert.assertTrue(Carmen.LadronEstaEnJuego(LadronUno));
		Assert.assertTrue(Carmen.LadronEstaEnJuego(LadronDos));
	}
	@Test
	public void CarmenLevantaXMLConPolicias() throws ParserConfigurationException, TransformerException, SAXException, IOException{
		CarmenSanDiego Carmen = new CarmenSanDiego();
		Policia unPolicia = new Policia("Facu",5);
		Carmen.AgregarPolicia(unPolicia);
		Policia otroPolicia = new Policia("Tomy",10);
		Carmen.AgregarPolicia(otroPolicia);
		Carmen.BajarPoliciasAXML();
		Carmen.LevantarPoliciasDelXML();
		Assert.assertTrue(Carmen.PoliciaEstaEnJuego(unPolicia));
		Assert.assertTrue(Carmen.PoliciaEstaEnJuego(otroPolicia));
	}

}
