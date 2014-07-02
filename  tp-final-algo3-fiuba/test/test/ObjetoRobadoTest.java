package test;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import modelo.ObjetoRobado;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class ObjetoRobadoTest {
	
	private String nombre = "La Giaconda";
	private String valor = "muy valioso";
	private ObjetoRobado UnObjeto = new ObjetoRobado(nombre,valor);
	
	
	@Test
	public void ValorEsCorrecto() {
		Assert.assertEquals(UnObjeto.getValor(),valor);
	}

	@Test
	public void ValorNoEsCorrecto() {
		Assert.assertFalse(UnObjeto.getValor().equals("valioso"));
	}
	
	@Test
	public void NombreEsCorrecto() {
		Assert.assertEquals(UnObjeto.getNombre(),nombre);
	}

	@Test
	public void NombreNoEsCorrecto() {
		Assert.assertFalse(UnObjeto.getNombre().equals("La ultima cena"));
	}
	@Test
	public void testSerializarYCargarObjeto() throws ParserConfigurationException{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.newDocument();
		Element elementoObjeto = UnObjeto.serializar(doc);
		doc.appendChild(elementoObjeto);
		Assert.assertNotNull(elementoObjeto);
		Node nodoALevantar = doc.getFirstChild();
		ObjetoRobado objetoCargado = ObjetoRobado.hidratar(nodoALevantar);
		Assert.assertTrue(objetoCargado.equals(UnObjeto));
		
	}

}
