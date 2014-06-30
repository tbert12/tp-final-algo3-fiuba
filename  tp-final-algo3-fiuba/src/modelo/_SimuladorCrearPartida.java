package modelo;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import modelo.caracteristicas.Cabello;
import modelo.caracteristicas.Caracteristicas;
import modelo.caracteristicas.Hobby;
import modelo.caracteristicas.Senia;
import modelo.caracteristicas.Sexo;
import modelo.caracteristicas.Vehiculo;
import modelo.heridas.HeridaArmaDeFuego;
import modelo.heridas.HeridaCuchillo;

public class _SimuladorCrearPartida {
	private ArrayList<Pais> Paises;
	private ArrayList<Ladron> Ladrones;
	
	public Partida crearPartida(String nombreDelPolicia) {
		/* DATOS DE PARTIDA
		Paises
		 -Argelia    Argel         (28.033886,1.659626)      Museo,Puerto,Casa de Gobierno
		 -Argentina  Buenos Aires  (-34.608418,-58.373161)   Aeropuerto,Banco,Biblioteca
		 -Alemania   Amsterdam      (51.165691,10.451526)     Museo,Aeropuerto,Bolsa
		 -Usa        New York      (40.714268,-74.005974)    Casa de Gobierno,Biblioteca,Banco
		 -Paraguay   Concepcion    (-23.442503,-58.443832)   Museo,Aeropuerto,Bolsa
		 -Canada     Ottawa        (45.423492,-75.697929)    Puerto,Biblioteca,Museo
		 -China      Shanghai      (31.230707,121.472916)    Puerto,Casa de Gobierno,Bolsa
		 -Espania    Madrid        (40.416691,-3.700345)     Museo,Banco,Casa de Gobierno
		 -Australia  Sydney        (-33.867138,151.207108)   Puerto,Biblioteca,Bolsa
		 -Francia    Roma          (41.895466,12.482324)     Museo,Banco,Aeropuerto
		Ladrones 
		 -Nick Brunch     (Masculino,Alpinismo,Negro,Anillo,Moto)
		 -Len Bulk        (Masculino,Alpinismo,Rojo,Tatuaje,Descapotable)
		 -Ihor Ihorovitch (Masculino,Croquet  ,Rubio,Tatuaje,Limusina)
		 -Fast Eddie B.   (Masculino,Croquet  ,Negro,Joyas,Convertible)
		 -Scar Graynolt   (Masculino,Croquet  ,Rojo,Anillo,Limusina)
		 -Merey Laroc     (Femenino ,Alpinismo,Marron,Joyas,Limusina)
		 -Lady Agatha     (Femenino ,Tennis   ,Rojo,Anillo,Convertible)
		 -Katherine Drib  (Femenino ,Alpinismo,Marron,Tatuaje,Moto)
		 -Dazzle Annie    (Femenino ,Tennis   ,Rubio,Tatuaje,Limusina)
		 -Carmen Sandiego (Femenino ,Tennis   ,Marron,Joyas,Convertible)
		Ladron 
		 -Carmen Sandiego
		Trayectoria del Ladron
		 -[Argentina,Canada,China,Australia]
		Objeto 
		 -Reservas del Banco, Valioso
		Policia
		 -Javi,0 Arrestos
		 
		 ------>FALTA PROBAR HERIDAS<-------
		*/
		
		ArrayList<Ladron> listLadrones = new ArrayList<Ladron>();
		ArrayList<Pais> listPaises = new ArrayList<Pais>();
		
		//Le seteto los datos a la base
		//Creo Ladrones
		Caracteristicas caracteristicasNick = new Caracteristicas(Sexo.MASCULINO,Hobby.ALPINISMO,Cabello.NEGRO,Senia.ANILLO,Vehiculo.MOTO);
		Ladron ladronNick = new Ladron("Nick Brunch",caracteristicasNick);
		listLadrones.add(ladronNick);
		Caracteristicas caracteristicasLen = new Caracteristicas(Sexo.MASCULINO,Hobby.ALPINISMO,Cabello.ROJO,Senia.TATUAJE,Vehiculo.DESCAPOTABLE);
		Ladron ladronLen = new Ladron("Len Bulk",caracteristicasLen);
		listLadrones.add(ladronLen);
		Caracteristicas caracteristicasThor = new Caracteristicas(Sexo.MASCULINO,Hobby.CROQUET,Cabello.RUBIO,Senia.TATUAJE,Vehiculo.LIMUSINA);
		Ladron ladronThor = new Ladron("Ihor Ihorovitch",caracteristicasThor);
		listLadrones.add(ladronThor);
		Caracteristicas caracteristicasFast = new Caracteristicas(Sexo.MASCULINO,Hobby.CROQUET,Cabello.NEGRO,Senia.JOYAS,Vehiculo.DESCAPOTABLE);
		Ladron ladronFast = new Ladron("Fast Eddie B.",caracteristicasFast);
		listLadrones.add(ladronFast);
		Caracteristicas caracteristicasScar = new Caracteristicas(Sexo.MASCULINO,Hobby.CROQUET,Cabello.ROJO,Senia.ANILLO,Vehiculo.LIMUSINA);
		Ladron ladronScar = new Ladron("Scar Graynolt",caracteristicasScar);
		listLadrones.add(ladronScar);
		Caracteristicas caracteristicasMerey = new Caracteristicas(Sexo.FEMENINO,Hobby.ALPINISMO,Cabello.CASTANIO,Senia.JOYAS,Vehiculo.LIMUSINA);
		Ladron ladronMerey = new Ladron("Merey Laroc",caracteristicasMerey);
		listLadrones.add(ladronMerey);
		Caracteristicas caracteristicasLady = new Caracteristicas(Sexo.FEMENINO,Hobby.TENNIS,Cabello.ROJO,Senia.ANILLO,Vehiculo.DESCAPOTABLE);
		Ladron ladronLady = new Ladron("Lady Agatha",caracteristicasLady);
		listLadrones.add(ladronLady);
		Caracteristicas caracteristicasKatherine = new Caracteristicas(Sexo.FEMENINO,Hobby.ALPINISMO,Cabello.CASTANIO,Senia.TATUAJE,Vehiculo.MOTO);
		Ladron ladronKatherine = new Ladron("Katherine Drib",caracteristicasKatherine);
		listLadrones.add(ladronKatherine);
		Caracteristicas caracteristicasDazzle = new Caracteristicas(Sexo.FEMENINO,Hobby.TENNIS,Cabello.RUBIO,Senia.TATUAJE,Vehiculo.LIMUSINA);
		Ladron ladronDazzle = new Ladron("Dazzle Annie",caracteristicasDazzle);
		listLadrones.add(ladronDazzle);
		Caracteristicas caracteristicasCarmen = new Caracteristicas(Sexo.FEMENINO,Hobby.TENNIS,Cabello.CASTANIO,Senia.JOYAS,Vehiculo.DESCAPOTABLE);
		Ladron ladronCarmen = new Ladron("Carmen Sandiego",caracteristicasCarmen);
		listLadrones.add(ladronCarmen);
		
		
		
		//Creo Paises
		Coordenadas CoordArgelia = new Coordenadas(28.033886,1.659626);
		Edificio MuseoArgelia = new Edificio("Biblioteca");
		//MuseoArgelia.setPista("No eh visto nada");
		Edificio PuertoArgelia = new Edificio("Puerto");
		//PuertoArgelia.setPista("No vi nada, Anda mucha gente por estos lugares");
		Edificio CasaDeGobiernoArgelia = new Edificio("Casa de Gobierno");
		//CasaDeGobiernoArgelia.setPista("Pues aqui no hay nada");
		Edificio[] EdificiosArgelia = {MuseoArgelia,PuertoArgelia,CasaDeGobiernoArgelia}; 
		Pais Argelia = new Pais("Argel",EdificiosArgelia,CoordArgelia);
		Argelia.setInformacion("Informacion sobre Argelia");
		listPaises.add(Argelia);
		
		Coordenadas CoordArgentina = new Coordenadas(-34.608418,-58.373161);
		Edificio AeropuertoArgentina = new Edificio("Aeropuerto");
		AeropuertoArgentina.setPista("Andaba en un coche que tenia una bandera con una hoja");
		Edificio BancoArgentina = new Edificio("Banco");
		BancoArgentina.setPista("Dijo que viajaba hacia el norte, hacia donde hace frio");
		Edificio BibliotecaArgentina = new Edificio("Biblioteca");
		BibliotecaArgentina.setPista("Tenia lindas joyas la dama");
		Edificio[] EdificiosArgentina = {AeropuertoArgentina,BancoArgentina,BibliotecaArgentina}; 
		Pais Argentina = new Pais("Buenos Aires",EdificiosArgentina,CoordArgentina);
		Argentina.setInformacion("Argentina, un pais con buena gente");
		listPaises.add(Argentina);
		
		Coordenadas CoordAlemania = new Coordenadas(51.165691,10.451526);
		Edificio MuseoAlemania = new Edificio("Museo");
		//MuseoAlemania.setPista("No he visto nada por estos lados");
		Edificio AeropuertoAlemania = new Edificio("Aeropuerto");
		//AeropuertoAlemania.setPista("Veo mucha gente todos los dias es probable que no la haya visto");
		Edificio BolsaAlemania = new Edificio("Bolsa");
		//BolsaAlemania.setPista("Si la he visto no me he dado cuenta");
		Edificio[] EdificiosAlemania = {MuseoAlemania,AeropuertoAlemania,BolsaAlemania}; 
		Pais Alemania = new Pais("Berlin",EdificiosAlemania,CoordAlemania);
		Alemania.setInformacion("Informacion sobre Alemania");
		listPaises.add(Alemania);
		
		Coordenadas CoordUsa = new Coordenadas(40.714268,-74.005974);
		Edificio CasadeGobiernoUsa = new Edificio("Casa de Gobierno");
		//CasadeGobiernoUsa.setPista("No he visto nada sospechoso");
		Edificio BibliotecaUsa = new Edificio("Biblioteca");
		//BibliotecaUsa.setPista("Aqui no vas a encontrar lo que buscas");
		Edificio BancoUsa = new Edificio("Bolsa");
		//BancoUsa.setPista("Disculpe, No tengo informacion para ofrecerle");
		Edificio[] EdificiosUsa = {CasadeGobiernoUsa,BibliotecaUsa,BancoUsa}; 
		Pais Usa = new Pais("New York",EdificiosUsa,CoordUsa);
		Usa.setInformacion("Informacion de Estados Unidos");
		listPaises.add(Usa);
		
		Coordenadas CoordParaguay = new Coordenadas(-23.442503,-58.443832);
		Edificio MuseoParaguay = new Edificio("Museo");
		//MuseoParaguay.setPista("No he visto nada");
		Edificio AeropuertoParaguay = new Edificio("Aeropuerto");
		//AeropuertoParaguay.setPista("Si la he visto no me he dado cuenta");
		Edificio BolsaParaguay = new Edificio("Bolsa");
		//BolsaParaguay.setPista("Veo pasar mucha gente para ver algo");
		Edificio[] EdificiosParaguay = {MuseoParaguay,AeropuertoParaguay,BolsaParaguay}; 
		Pais Paraguay = new Pais("Concepcion",EdificiosParaguay,CoordParaguay);
		Paraguay.setInformacion("Informacion de Paraguay");
		listPaises.add(Paraguay);
		
		Coordenadas CoordCanada = new Coordenadas(45.423492,-75.697929);
		Edificio PuertoCanada = new Edificio("Puerto");
		PuertoCanada.setPista("Dijo que se dirijiria a un pais comunista");
		Edificio BibliotecaCanada = new Edificio("Biblioteca");
		BibliotecaCanada.setPista("Jugamos un partido de tennis y luego menciono algo de un pais con muchos habitantes");
		Edificio MuseoCanada = new Edificio("Museo");
		MuseoCanada.setPista("La bandera del avion que tomo es de color rojo");
		Edificio[] EdificiosCanada = {PuertoCanada,BibliotecaCanada,MuseoCanada}; 
		Pais Canada = new Pais("Ottawa",EdificiosCanada,CoordCanada);
		Canada.setInformacion("Informacion de Canada");
		listPaises.add(Canada);
		
		Coordenadas CoordChina = new Coordenadas(31.230707,121.472916);
		Edificio PuertoChina = new Edificio("Puerto");
		PuertoChina.setPista("Me pregunto en que pais podia conocer a los kanguros");
		Edificio CasadeGobiernoChina = new Edificio("Casa de Gobierno");
		CasadeGobiernoChina.setPista("Se movia en un auto descapotable");
		Edificio BolsaChina = new Edificio("Bolsa");
		BolsaChina.setPista("Creo que tomo un vuelo hacia oceania");
		Edificio[] EdificiosChina = {PuertoChina,CasadeGobiernoChina,BolsaChina}; 
		Pais China = new Pais("Shanghai",EdificiosChina,CoordChina);
		China.setInformacion("Informacion de China");
		listPaises.add(China);
		
		Coordenadas CoordEspania = new Coordenadas(40.416691,-3.700345);
		Edificio MuseoEspania = new Edificio("Museo");
		//MuseoEspania.setPista("No eh visto nada estos dias");
		Edificio BancoEspania = new Edificio("Banco");
		//BancoEspania.setPista("Disculpe, no tengo informacion para ofrecerle");
		Edificio CasadeGobiernoEspania = new Edificio("Casa de Gobierno");
		//CasadeGobiernoEspania.setPista("Si el ladron ha estado aqui no lo he visto");
		Edificio[] EdificiosEspania = {MuseoEspania,BancoEspania,CasadeGobiernoEspania}; 
		Pais Espania = new Pais("Madrid",EdificiosEspania,CoordEspania);
		Espania.setInformacion("Informacion de España");
		listPaises.add(Espania);
		
		Coordenadas CoordAustralia = new Coordenadas(-33.867138,151.207108);
		Edificio PuertoAustralia = new Edificio("Puerto");
		PuertoAustralia.setPista("Veo algo sospechoso por aqui");
		HeridaCuchillo UnaHerida = new HeridaCuchillo("HERIDA DE CUCHILLO");
		PuertoAustralia.setHerida(UnaHerida);
		Edificio BibliotecaAustralia = new Edificio("Biblioteca");
		BibliotecaAustralia.setPista("Algo huele mal");
		//BibliotecaAustralia.setLadron();
		Edificio BolsaAustralia = new Edificio("Bolsa");
		HeridaArmaDeFuego OtraHerida = new HeridaArmaDeFuego("HERIDA DE PISTOLA");
		BolsaAustralia.setHerida(OtraHerida);
		BolsaAustralia.setPista("Estos dias han pasado cosas extrañas");
		Edificio[] EdificiosAustralia = {PuertoAustralia,BibliotecaAustralia,BolsaAustralia}; 
		Pais Australia = new Pais("Sydney",EdificiosAustralia,CoordAustralia);
		Australia.setInformacion("Informacion de Australia");
		listPaises.add(Australia);
		
		Coordenadas CoordFrancia = new Coordenadas(41.895466,12.482324);
		Edificio MuseoFrancia = new Edificio("Museo");
		//MuseoFrancia.setPista("Creo que aqui no ha pasado nada");
		Edificio BancoFrancia = new Edificio("Banco");
		//BancoFrancia.setPista("Si veo algo estare en contacto contigo");
		Edificio AeropuertoFrancia = new Edificio("Aeropuerto");
		//AeropuertoFrancia.setPista("Mucha gente pasa por aqui, es dificil que vea al ladron");
		Edificio[] EdificiosFrancia = {MuseoFrancia,BancoFrancia,AeropuertoFrancia}; 
		Pais Francia = new Pais("Roma",EdificiosFrancia,CoordFrancia);
		Francia.setInformacion("Informacion de Francia");
		listPaises.add(Francia);
		
		//Asigno La trayectoria del Ladron
		ArrayList<Pais> PaisesDeLaTrayectoria = new ArrayList<Pais>();
		PaisesDeLaTrayectoria.add(Argentina);
		PaisesDeLaTrayectoria.add(Canada);
		PaisesDeLaTrayectoria.add(China);
		PaisesDeLaTrayectoria.add(Australia);
		Trayectoria trayectoriaDelLadron = new Trayectoria(PaisesDeLaTrayectoria);
		
		ladronCarmen.addTrayectoria(trayectoriaDelLadron);
		
		//Creo la Base de datos
		BaseDeDatos basedeDatos = new BaseDeDatos(listLadrones,listPaises);
		
		//Creo Policia
		Policia unPolicia = new Policia(nombreDelPolicia,4);
		//Creo Objeto
		ObjetoRobado unObjetoRobado = new ObjetoRobado("Reservas del Banco","Valioso");
		
		
		Partida unaPartida = new Partida(unPolicia,ladronCarmen,basedeDatos,unObjetoRobado);
		Paises = listPaises;
		Ladrones = listLadrones;
		try {
			serializar();
		} catch (ParserConfigurationException e) {
			System.out.print("Algo Salio mal Uno");
			e.printStackTrace();
		} catch (TransformerException e) {
			System.out.print("Algo Salio mal Dos");
			e.printStackTrace();
		}
		return unaPartida;
	}

	public void serializar() throws ParserConfigurationException, TransformerException{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.newDocument();
		Element elementoPais = doc.createElement("PaisesyLadrones");
		for (Pais pais: Paises){
			elementoPais.appendChild(pais.serializar(doc));
		}
		for (Ladron ladron: Ladrones){
			elementoPais.appendChild(ladron.serializar(doc));
		}
		doc.appendChild(elementoPais);
		transformarYEscribirADisco("PaisesYLadrones.xml",doc);
	}
	private void transformarYEscribirADisco(String nombreArchivo, Document doc) throws TransformerException{
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","3");
		DOMSource source = new DOMSource(doc);
		File archivoDestino = new File(nombreArchivo);
		StreamResult result = new StreamResult(archivoDestino);
		transformer.transform(source, result);
	}

}
