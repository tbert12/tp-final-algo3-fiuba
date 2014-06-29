package fiuba.algo3.tpfinal.test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import modelo.Ladron;
import modelo.caracteristicas.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class LadronTest {
	
	private String nombreArchivo = "pruebaLadron.xml";

	@After
	public void after() {
		File archivo = new File(this.nombreArchivo);
		if (archivo.exists()) {
			archivo.delete();
		}
	}
	
	@Test
	public void LadronNombreEsCorrecto() {
		Caracteristicas CaracteristicasDelLadron = new Caracteristicas(Sexo.FEMENINO,Hobby.TENNIS,Cabello.NEGRO,Senia.ANILLO,Vehiculo.DESCAPOTABLE);
		Ladron ladron = new Ladron("Menem",CaracteristicasDelLadron);
		Assert.assertEquals(ladron.getNombre(),"Menem");	
	}
	
	@Test
	public void LadronNombreNoEsCorrecto() {
		Caracteristicas CaracteristicasDelLadron = new Caracteristicas(Sexo.FEMENINO,Hobby.TENNIS,Cabello.NEGRO,Senia.ANILLO,Vehiculo.DESCAPOTABLE);
		Ladron ladron = new Ladron("Menem",CaracteristicasDelLadron);
		Assert.assertNotEquals(ladron.getNombre(),"Carlitos Menem");	
	}
	
	@Test
	public void LadronCaracteristicasSonCorrectas() {
		Caracteristicas CaracteristicasDelLadron = new Caracteristicas(Sexo.FEMENINO,Hobby.TENNIS,Cabello.NEGRO,Senia.ANILLO,Vehiculo.DESCAPOTABLE);
		Ladron ladron = new Ladron("Menem",CaracteristicasDelLadron);
		Assert.assertEquals(ladron.getSexo(),Sexo.FEMENINO);
		Assert.assertEquals(ladron.getHobby(), Hobby.TENNIS);
		Assert.assertEquals(ladron.getCabello(),Cabello.NEGRO);
		Assert.assertEquals(ladron.getSenia(),Senia.ANILLO);
		Assert.assertEquals(ladron.getVehiculo(),Vehiculo.DESCAPOTABLE);
	}

	@Test
	public void LadronCaracteristicasNoSonCorrectas() {
		Caracteristicas CaracteristicasDelLadron = new Caracteristicas(Sexo.FEMENINO,Hobby.TENNIS,Cabello.NEGRO,Senia.ANILLO,Vehiculo.DESCAPOTABLE);
		Ladron ladron = new Ladron("Menem",CaracteristicasDelLadron);
		Assert.assertNotEquals(ladron.getSexo(),Sexo.MASCULINO);
		Assert.assertNotEquals(ladron.getHobby(), Hobby.ALPINISMO);
		Assert.assertNotEquals(ladron.getCabello(),Cabello.ROJO);
		Assert.assertNotEquals(ladron.getSenia(),Senia.JOYAS);
		Assert.assertNotEquals(ladron.getVehiculo(),Vehiculo.LIMUSINA);
	}
	
	@Test
	public void LadronCompararCaracteristicasEsTrue() {
		Caracteristicas CaracteristicasDelLadron = new Caracteristicas(Sexo.FEMENINO,Hobby.TENNIS,Cabello.NEGRO,Senia.ANILLO,Vehiculo.DESCAPOTABLE);
		Ladron ladron = new Ladron("Menem",CaracteristicasDelLadron);
		Assert.assertTrue(ladron.compararCaracteristicas(CaracteristicasDelLadron));
	}
	
	@Test
	public void LadronCompararCaracteristicasEsFalse() {
		Caracteristicas CaracteristicasDelLadron = new Caracteristicas(Sexo.FEMENINO,Hobby.TENNIS,Cabello.NEGRO,Senia.ANILLO,Vehiculo.DESCAPOTABLE);
		Caracteristicas OtrasCaracteristicasDelLadron = new Caracteristicas(Sexo.MASCULINO,Hobby.TENNIS,Cabello.NEGRO,Senia.ANILLO,Vehiculo.DESCAPOTABLE);
		Ladron ladron = new Ladron("Menem",CaracteristicasDelLadron);
		Assert.assertFalse(ladron.compararCaracteristicas(OtrasCaracteristicasDelLadron));
	}
 	
	@Test
	public void PersistenciaFuncionaCon2Ladrones() throws ParserConfigurationException, TransformerException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.newDocument();
		Caracteristicas caracteristicasLadronUno = new Caracteristicas(Sexo.FEMENINO,Hobby.TENNIS,Cabello.NEGRO,Senia.ANILLO,Vehiculo.DESCAPOTABLE);
		Caracteristicas caracteristicasLadronDos = new Caracteristicas (Sexo.MASCULINO,Hobby.ALPINISMO,Cabello.RUBIO,Senia.CICATRIZ,Vehiculo.DEPORTIVO);
		Ladron unLadron = new Ladron("Tomy",caracteristicasLadronUno);
		Ladron otroLadron = new Ladron("Facu",caracteristicasLadronDos);
		Element LadronSerializado = unLadron.serializar(doc);
		Element otroLadronSerializado = otroLadron.serializar(doc);
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
		NodeList nodosLadron = doc.getElementsByTagName("Ladron");
		List <Ladron> listado= new ArrayList<Ladron>();
		for  (int i=0;i<nodosLadron.getLength();i++){
			Node nodoHijo = nodosLadron.item(i);
			Ladron ladronCargado = Ladron.hidratar(nodoHijo);
			listado.add(ladronCargado);
		}
		
		Assert.assertNotNull(listado.get(0));
		Assert.assertEquals(listado.get(0).getNombre(),unLadron.getNombre());
		Assert.assertTrue(listado.get(0).compararCaracteristicas(caracteristicasLadronUno) );
	
		Assert.assertNotNull(listado.get(1));
		Assert.assertEquals(listado.get(1).getNombre(),otroLadron.getNombre());
		Assert.assertTrue(listado.get(1).compararCaracteristicas(caracteristicasLadronDos) );
	}

}
