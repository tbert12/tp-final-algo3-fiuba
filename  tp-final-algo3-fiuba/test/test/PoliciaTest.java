package test;

import static org.junit.Assert.assertEquals;
import modelo.Ladron;
import modelo.Policia;
import modelo.caracteristicas.Caracteristicas;
import modelo.rangos.RangoDetective;
import modelo.rangos.RangoInvestigador;
import modelo.rangos.RangoNovato;
import modelo.rangos.RangoSargento;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
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

import org.junit.After;
import org.junit.Before;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
public class PoliciaTest {
	private String nombreArchivo;
	private Ladron unLadron = new Ladron("Jonh", new Caracteristicas(null, null, null, null, null)); 
	@Before
	public void before() {
		this.nombreArchivo = java.util.UUID.randomUUID().toString() + ".xml";
	}
	
	@After
	public void after() {
		File archivo = new File(this.nombreArchivo);
		if (archivo.exists()) {
			archivo.delete();
		}
	}

	
	public void SumarArrestos(int Cantidad,Policia Jugador){
		for (int i = 0; i<Cantidad; i++){
			Jugador.arrestarSospechoso(unLadron);
		}
	}

	@Test
	public void PoliciaNuevoEsNovato() {
		
		Policia Jugador = new Policia("Pepe",0);
		Assert.assertTrue(Jugador.getRango() instanceof RangoNovato);
	}
	
	@Test
	public void PoliciaCambiadeRangoAlSumarArrestos(){
		Policia Jugador = new Policia("Pepe",0);
		Jugador.setSospechoso(unLadron);
		
		this.SumarArrestos(5,Jugador);
		Assert.assertTrue(Jugador.getRango() instanceof RangoDetective);

		this.SumarArrestos(5,Jugador);
		Assert.assertTrue(Jugador.getRango() instanceof RangoInvestigador);
		
		this.SumarArrestos(10,Jugador);
		Assert.assertTrue(Jugador.getRango() instanceof RangoSargento);
	}
	
	@Test
	public void PoliciaEsSargento(){
		Policia unPolicia = new Policia("Bufarreti", 20);
		unPolicia.setSospechoso(unLadron);
		unPolicia.arrestarSospechoso(unLadron);
		Assert.assertTrue(unPolicia.getRango() instanceof RangoSargento);
	}
	
	@Test
	public void ReducirHorasAlPolicaNoDuerme(){
		//Si reduzco menos de 15 horas el policia no tiene que dormir
		Policia UnPolicia = new Policia("Pedraza",0);
		int HorasAReducir = 12;
		int HorasIniciales = UnPolicia.getTiempo();
		UnPolicia.reducirHorasPorViaje(HorasAReducir);
		
		assertEquals(HorasIniciales - HorasAReducir,UnPolicia.getTiempo() );
	}
	
	@Test
	public void ReducirHorasAlPolicaDuerme(){
		//Si reduzco mas de 15 horas el policia tiene que dormir
		int HorasPorDormir = 8;
		Policia UnPolicia = new Policia("Pedraza",0);
		int HorasAReducir = 18;
		int HorasIniciales = UnPolicia.getTiempo();
		UnPolicia.reducirHorasPorViaje(HorasAReducir);
		
		assertEquals(HorasIniciales - HorasAReducir - HorasPorDormir,UnPolicia.getTiempo() );
	}
	
	@Test
	public void PoliciaReducirHorasPorViaje(){
		Policia UnPolicia = new Policia("Pedraza",0);
		
		int TiempoAntesDeDormir = UnPolicia.getTiempo();
		int HorasAReducir = 8;
		
		UnPolicia.reducirHorasPorViaje(HorasAReducir);;
		
		Assert.assertEquals(TiempoAntesDeDormir - HorasAReducir ,UnPolicia.getTiempo());
	
	}
	
	@Test
	public void ReducirHorasAlPolicaPorFiltracion(){
		Policia UnPolicia = new Policia("Pedraza",0);
		int HorasPoliciaIniciales = UnPolicia.getTiempo();
		int HorasAReducir = 3;
		UnPolicia.reducirHorasPorFiltracion();
		
		assertEquals(HorasPoliciaIniciales - HorasAReducir,UnPolicia.getTiempo() );
	}
	
	@Test
	public void ReducirHorasAlPolicaPorHeridaCuchillo(){
		Policia UnPolicia = new Policia("Pedraza",0);
		int HorasPoliciaIniciales = UnPolicia.getTiempo();
		int HorasAReducir = 2;
		UnPolicia.reducirHorasPorHeridaCuchillo();
		
		assertEquals(HorasPoliciaIniciales - HorasAReducir,UnPolicia.getTiempo() );
	}

	@Test
	public void ReducirHorasAlPolicaPorHeridaArma(){
		Policia UnPolicia = new Policia("Pedraza",0);
		int HorasPoliciaIniciales = UnPolicia.getTiempo();
		int HorasAReducir = 4;
		UnPolicia.reducirHorasPorHeridaArmaDeFuego();
		
		assertEquals(HorasPoliciaIniciales - HorasAReducir,UnPolicia.getTiempo() );
	}
	
	
	@Test
	public void PoliciaNoCambiadeRangoConCuatroArrestos(){
		Policia Jugador = new Policia("Pepe",0);
		this.SumarArrestos(4,Jugador);
		Assert.assertFalse(Jugador.getRango() instanceof RangoDetective);
	}
	
	@Test 
	public void PoliciaVijaMasRapidoSiCambiaDeRango(){
		Policia Jugador = new Policia("Pepe",0);
		Jugador.setSospechoso(unLadron);
		//Novato Viaja a 900 Km/h
		int KilometrosAViajar = 5000;
		
		int HorasDeViajeEsperadasNovato = 5000/900;
		int HorasDeViajeNovato = Jugador.costoDeViaje(KilometrosAViajar);
		Assert.assertEquals(HorasDeViajeNovato, HorasDeViajeEsperadasNovato);
		
		//RangoDetective viaja a 1100
		this.SumarArrestos(5,Jugador);
		int HorasDeViajeEsperadasDetective = 5000/1100;
		int HorasDeViajeDetective = Jugador.costoDeViaje(KilometrosAViajar);
		Assert.assertTrue(HorasDeViajeDetective < HorasDeViajeNovato);
		Assert.assertEquals(HorasDeViajeDetective, HorasDeViajeEsperadasDetective);
	}
	@Test
	public void PersistenciaFuncionaCon2Policias() throws ParserConfigurationException, TransformerException, SAXException, IOException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.newDocument();
		
		Policia unPolicia = new Policia("Juan",15);
		Policia otroPolicia = new Policia("Pepe",35);
		Element otroPoliciaSerializado = otroPolicia.serializar(doc);
		Element policiaSerializado = unPolicia.serializar(doc);
		Assert.assertNotNull(policiaSerializado);
		doc.appendChild(policiaSerializado);
		doc.getFirstChild().appendChild(otroPoliciaSerializado);
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
		
		NodeList nodosPolicia = doc.getElementsByTagName("Policia");
		List <Policia> listado= new ArrayList<Policia>();
		for  (int i=0;i<nodosPolicia.getLength();i++){
			Node nodoHijo = nodosPolicia.item(i);
			Policia policiaCargado = Policia.hidratar(nodoHijo);
			listado.add(policiaCargado);
		}
		Assert.assertNotNull(listado.get(1));
		Assert.assertEquals(listado.get(1).getNombre(),otroPolicia.getNombre());
		Assert.assertTrue(listado.get(1).getRango() instanceof RangoSargento);
		Assert.assertNotNull(listado.get(0));
		Assert.assertEquals(listado.get(0).getNombre(),unPolicia.getNombre());
		Assert.assertTrue(listado.get(0).getRango() instanceof RangoInvestigador);
	}

	
}
