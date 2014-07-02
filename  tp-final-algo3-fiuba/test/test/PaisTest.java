package test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import modelo.Coordenadas;
import modelo.Edificio;
import modelo.Pais;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class PaisTest {

	private Edificio biblioteca = new Edificio("biblioteca");
	private Edificio puerto = new Edificio("puerto");
	private Edificio fiuba = new Edificio("fiuba");
	
	private Edificio[] edificios = {biblioteca,puerto,fiuba};
	private String nombre = "Argentina";
	
	private Coordenadas coordenadasDelPais = new Coordenadas(2,2);
	private Pais UnPais = new Pais(nombre,edificios,coordenadasDelPais);
	@After
	public void after() {
		File archivo = new File("pruebaPaises.xml");
		if (archivo.exists()) {
			archivo.delete();
		}
	}
	
	@Test
	public void testElNombreDelPaisEsCorrecto() {
		
		Assert.assertEquals( UnPais.getNombre(), nombre );
	}	
	
	@Test
	public void testCopiarPaisNoTieneMismaReferencia(){
	Pais paisCopia = UnPais.copiar();
	Assert.assertFalse(paisCopia == UnPais);
	}
	@Test
	public void testLosNombresDeLosEdificiosSonCorrectos(){
		
		ArrayList<Edificio> ListaEdificios = UnPais.getEdificios();
		
		Iterator<Edificio> Iterador = ListaEdificios.iterator();
		int i = 0;
		while( Iterador.hasNext() ){
			Edificio NombreActual = Iterador.next();
			Assert.assertEquals( NombreActual, edificios[i]);
			i++;
		}
	}
	
	@Test
	public void testLasDistanciasSonCorrectas(){
		Coordenadas otrascoordenadasDelPais = new Coordenadas(0,0);
		Pais otroPais = new Pais(nombre,edificios,otrascoordenadasDelPais);
		int distanciaEsperada = 506;
		Assert.assertEquals(UnPais.distanciaAPais(otroPais),distanciaEsperada,0);
	}

	@Test
	public void testInformacionDelPaisEsCorrecta(){
		String Info = "Informacion del pais";
		UnPais.setInformacion(Info);
		
		Assert.assertEquals(UnPais.getInformacion(),Info);
		Assert.assertFalse(UnPais.getInformacion().equals(Info + "Algo Mas"));
	}
	@Test
	public void testPersistenciaUnPais() throws ParserConfigurationException, TransformerException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		UnPais.setInformacion("Informacion del Pais");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.newDocument();
		Element elementoSerializado =UnPais.serializar(doc);
		Assert.assertNotNull(elementoSerializado);
		doc.appendChild(elementoSerializado);
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		File archivoDestino = new File("pruebaPaises.xml");
		StreamResult result = new StreamResult(archivoDestino);
		transformer.transform(source, result);
		File archivo = new File("pruebaPaises.xml");
		Assert.assertTrue(archivo.exists());
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		doc = dBuilder.parse(archivo);
		doc.getDocumentElement().normalize();
		Node nodoPais = doc.getElementsByTagName("Pais").item(0);
		Pais unPaisCargado = Pais.hidratar(nodoPais);
		Assert.assertTrue(UnPais.equals(unPaisCargado));
		
	}
}



