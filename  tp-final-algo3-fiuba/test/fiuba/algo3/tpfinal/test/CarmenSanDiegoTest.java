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
		File archivo = new File("RegistroPolicias.xml");
		if (archivo.exists()) {
			archivo.delete();
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
