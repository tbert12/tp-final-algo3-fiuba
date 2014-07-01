package fiuba.algo3.tpfinal.test;

import static org.junit.Assert.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import modelo.Coordenadas;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class CoordenadaTest {

	//Margen de error (Lo agrandamos porque son distancias de miles de kilometros)
	private static final double DELTA = 20;
	
	@Test
	public void CoordenadasObtengoLatitudLongitudQueEspero() {
		Coordenadas unaCoordenada = new Coordenadas(4.3,-6.8);
		double LatitudEsperada = 4.3;
		double LongitudEsperada = -6.8;
		assertEquals(unaCoordenada.getLatitud(),LatitudEsperada,DELTA);
		assertEquals(unaCoordenada.getLongitud(), LongitudEsperada,DELTA);
	}
	
	@Test
	public void DistanciaEntreCoordenadas() {
		Coordenadas unaCoordenada = new Coordenadas(-34.608418,-58.373161);
		Coordenadas otrasCoordenada = new Coordenadas(41.871941,12.567380);
		//Destancia esperada de la web
		//Buenos Aires(-34.608418,-58.373161)
		//   Italy (41.871941,12.567380)
		double distanciaQueEspero = 17940;
		assertEquals(unaCoordenada.DistanciaA(otrasCoordenada),distanciaQueEspero,DELTA);
	}
	@Test
	public void testPersistenciaCoordenadas() throws ParserConfigurationException{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.newDocument();
		Coordenadas unasCoordenadas = new Coordenadas(5,2);
		Element elementoCoordenadas = (Element)unasCoordenadas.serializar(doc);
		doc.appendChild(elementoCoordenadas);
		Node elementoACargar=doc.getFirstChild();
		Coordenadas coordenadasCargadas = Coordenadas.hidratar(elementoACargar);
		Assert.assertTrue(coordenadasCargadas.equals(unasCoordenadas));
		
	}

}
