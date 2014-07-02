package test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import modelo.Edificio;
import modelo.Policia;
import modelo.heridas.HeridaArmaDeFuego;
import modelo.heridas.HeridaCuchillo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class EdificioTest {
	
	private String pista = "Esto es una pista de prueba.";
	private String nombre = "Biblioteca";
	private Edificio UnEdificio = new Edificio(nombre);
	private Policia unPolicia= new Policia("Pepe",0);
	@After
	public void after() {
		File archivo = new File("pruebaEdificio.xml");
		if (archivo.exists()) {
			archivo.delete();
		}
	}
	@Test
	public void testPistaEdificioEsCorrecta() {
		Policia UnPolicia = new Policia("Juan", 0);
		UnEdificio.setPista(pista);
		Assert.assertEquals(UnEdificio.visitar(UnPolicia),pista);
		
	}
	@Test
	public void testNombreEdificioEsCorrect0() {
		Assert.assertEquals(UnEdificio.getNombre(),nombre);
		
	}
	@Test
	public void testEdificioTieneHeridaCuchillo(){
		UnEdificio.setHerida(new HeridaCuchillo("Esto Es una prueba"));
		int TiempoInicial = unPolicia.getTiempo();
		UnEdificio.visitar(unPolicia);
		int TiempoFinal = unPolicia.getTiempo();
		Assert.assertTrue(TiempoInicial > TiempoFinal);
		
	}
	@Test
	public void testHeridaCuchilloRestaMenosQueHeridaArma(){
		Edificio otroEdificio = new Edificio("Pizzeria");
		otroEdificio.setPista("Esta es otra pista de prueba");
		otroEdificio.setHerida(new HeridaArmaDeFuego("Herido por un disparo con arma de fuegos"));
		Policia otroPolicia = new Policia("Seba",0);
		UnEdificio.visitar(unPolicia);
		otroEdificio.visitar(otroPolicia);
		Assert.assertTrue(unPolicia.getTiempo() > otroPolicia.getTiempo());
	}
	@Test
	public void testPersistenciaUnEdificioSinHerida() throws ParserConfigurationException, TransformerException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.newDocument();
		Element edificioSerializado = UnEdificio.serializar(doc);
		Assert.assertNotNull(edificioSerializado);
		doc.appendChild(edificioSerializado);
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		File archivoDestino = new File("pruebaEdificio.xml");
		StreamResult result = new StreamResult(archivoDestino);
		transformer.transform(source, result);
		
		File archivo = new File("pruebaEdificio.xml");
		Assert.assertTrue(archivo.exists());


		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		doc = dBuilder.parse(archivo);
		doc.getDocumentElement().normalize();
		Node elementoACargar = doc.getElementsByTagName("Edificio").item(0);
		Edificio edificioCargado = Edificio.hidratar(elementoACargar);
		Assert.assertTrue(edificioCargado.equals(UnEdificio));
		
	}
	@Test
	public void testPersistenciaUnEdificioConHeridaCuchillo() throws ParserConfigurationException, TransformerException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.newDocument();
		UnEdificio.setHerida(new HeridaCuchillo("Herida con arma blanca"));
		Element edificioSerializado = UnEdificio.serializar(doc);
		Assert.assertNotNull(edificioSerializado);
		doc.appendChild(edificioSerializado);
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		File archivoDestino = new File("pruebaEdificio.xml");
		StreamResult result = new StreamResult(archivoDestino);
		transformer.transform(source, result);
		
		File archivo = new File("pruebaEdificio.xml");
		Assert.assertTrue(archivo.exists());


		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		doc = dBuilder.parse(archivo);
		doc.getDocumentElement().normalize();
		Node elementoACargar = doc.getElementsByTagName("Edificio").item(0);
		Edificio edificioCargado = Edificio.hidratar(elementoACargar);
		Assert.assertTrue(edificioCargado.equals(UnEdificio));
	}
	public void testPersistenciaDosEdificiosConHeridaYUnoConLadron() throws ParserConfigurationException, TransformerException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.newDocument();
		Edificio otroEdificio = new Edificio("Biblioteca");
		otroEdificio.setPista("Esta es otra pista de prueba");
		otroEdificio.setHerida(new HeridaArmaDeFuego("Herido por un disparo con arma de fuegos"));
		UnEdificio.setHerida(new HeridaCuchillo("Herida con arma blanca"));
		Element edificioSerializado = UnEdificio.serializar(doc);
		Element otroEdificioSerializado = otroEdificio.serializar(doc);
		Assert.assertNotNull(edificioSerializado);
		doc.appendChild(edificioSerializado);
		doc.getFirstChild().appendChild(otroEdificioSerializado);
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		File archivoDestino = new File("pruebaEdificio.xml");
		StreamResult result = new StreamResult(archivoDestino);
		transformer.transform(source, result);
		
		File archivo = new File("pruebaEdificio.xml");
		Assert.assertTrue(archivo.exists());


		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		doc = dBuilder.parse(archivo);
		doc.getDocumentElement().normalize();
		Node elementoACargar = doc.getElementsByTagName("Edificio").item(0);
		Node segundoElementoACargar = doc.getElementsByTagName("Edificio").item(1);
		Edificio edificioCargado = Edificio.hidratar(elementoACargar);
		Edificio otroEdificioCargado = Edificio.hidratar(segundoElementoACargar);
		Assert.assertTrue(edificioCargado.equals(UnEdificio));
		Assert.assertTrue(otroEdificioCargado.equals(otroEdificio));
	}
}
