package fiuba.algo3.tpfinal.test;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import modelo.Caracteristicas;
import modelo.Ladron;
import modelo.Policia;
import modelo.RangoInvestigador;
import modelo.RangoSargento;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


public class LadronTest {
	private String nombreArchivo = "pruebaLadron.xml";
	private String[] Sexo = {"Masculino","Femenino"};
	private String[] Hobbie = {"Tennis","Musica","Alpinismo","Paracaidismo","Natacion","Croquet"};
	private String[] Cabello = {"Castanio","Rubio","Rojo","Negro"};
	private String[] Senia = {"Cojera","Anillo","Tatuaje","Cicatriz","Joyas"};
	private String[] Vehiculo = {"Descapotable","Limusina","Deportivo","Moto"};
	
	@After
	public void after() {
		File archivo = new File(this.nombreArchivo);
		if (archivo.exists()) {
			archivo.delete();
		}
	}
	
	@Test
	public void LadronEsFemenino() {
		Caracteristicas CaracteristicasDelLadron = new Caracteristicas(Sexo[1],Hobbie[0],Cabello[3],Senia[1],Vehiculo[0]);
		Ladron ladron = new Ladron("Menem",CaracteristicasDelLadron);
		Assert.assertEquals(ladron.Sexo(),Sexo[1]);
		Assert.assertEquals(ladron.Hobby(), Hobbie[0]);
		Assert.assertEquals(ladron.Cabello(),Cabello[3]);
		Assert.assertEquals(ladron.Senia(),Senia[1]);
		Assert.assertEquals(ladron.Vehiculo(),Vehiculo[0]);
	}

	@Test
	public void LadronNoEsFemenino() {
		Caracteristicas CaracteristicasDelLadron = new Caracteristicas(Sexo[1],Hobbie[0],Cabello[3],Senia[1],Vehiculo[0]);
		Ladron ladron = new Ladron("Menem",CaracteristicasDelLadron);
		Assert.assertNotEquals(ladron.Sexo(),Sexo[0]);
	}

	@Test
	public void TestOrdenDeArresto(){
		Caracteristicas CaracteristicasDelLadron = new Caracteristicas(Sexo[1],Hobbie[0],Cabello[3],Senia[1],Vehiculo[0]);
		Ladron ladron = new Ladron("Menem",CaracteristicasDelLadron);
		
		Assert.assertFalse(ladron.TieneOrdenDeArresto());
		
		ladron.EmitirOrdenDeArresto();
		
		Assert.assertTrue(ladron.TieneOrdenDeArresto());
	}
	@Test
	public void PersistenciaFuncionaCon2Ladrones() throws ParserConfigurationException, TransformerException, SAXException, IOException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.newDocument();
		Caracteristicas caracteristicasLadronUno = new Caracteristicas(Sexo[1],Hobbie[0],Cabello[3],Senia[1],Vehiculo[0]);
		Caracteristicas caracteristicasLadronDos = new Caracteristicas (Sexo[0],Hobbie[2],Cabello[1],Senia[3],Vehiculo[2]);
		Ladron unLadron = new Ladron("Tomy",caracteristicasLadronUno);
		Ladron otroLadron = new Ladron("Facu",caracteristicasLadronDos);
		Element LadronSerializado = unLadron.serializarLadron(doc);
		Element otroLadronSerializado = otroLadron.serializarLadron(doc);
		Assert.assertNotNull(LadronSerializado);
		doc.appendChild(LadronSerializado);
		doc.getFirstChild().appendChild(otroLadronSerializado);
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		File archivoDestino = new File(this.nombreArchivo);
		StreamResult result = new StreamResult(archivoDestino);
		transformer.transform(source, result);
		
		File archivo = new File(this.nombreArchivo);
		Assert.assertTrue(archivo.exists());


		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		doc = dBuilder.parse(archivo);
		doc.getDocumentElement().normalize();
		
		Ladron LadronCargado = Ladron.ladronHidratar(doc,0);
		Assert.assertNotNull(LadronCargado);
		Assert.assertEquals(LadronCargado.getNombre(),unLadron.getNombre());
		Assert.assertTrue(LadronCargado.CompararCaracteristicas(caracteristicasLadronUno) );
		Ladron LadronCargado2 = Ladron.ladronHidratar(doc,1);
		Assert.assertNotNull(LadronCargado2);
		Assert.assertEquals(LadronCargado2.getNombre(),otroLadron.getNombre());
		Assert.assertTrue(LadronCargado2.CompararCaracteristicas(caracteristicasLadronDos) );
	}

}
