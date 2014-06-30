package modelo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import modelo.excepcion.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public  class CarmenSanDiego {
	private static String nombreArchivoPolicias = "RegistroPolicias.xml";
	private static String nombreArchivoLadrones = "RegistroLadrones.xml";
	private static String nombreArchivoPaises = "RegistroPaises.xml";
	private static List<Policia> listadoPolicias = new ArrayList<Policia>();
	private static List<Ladron> listadoLadrones = new ArrayList<Ladron>();
	private static List<Pais> listadoPaises = new ArrayList<Pais>();
	private Partida unaPartida;
	
	public  CarmenSanDiego() throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, ParserConfigurationException, SAXException, IOException{
		levantarPoliciasDelXML();
		levantarLadronesDelXML();
		levantarPaisesDelXML();
		}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private <T> List<T> levantarAlgoDelXML(String nombreArchivo,Class clase,List<T> listado) throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
		File archivo = new File(nombreArchivo);
		if (archivo.exists()){
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			doc = dBuilder.parse(archivo);
			doc.getDocumentElement().normalize();
			Method metodoHidratar = clase.getDeclaredMethod("hidratar",org.w3c.dom.Node.class);
			NodeList nodos = doc.getElementsByTagName(clase.getName());
			for (int i = 0;i < nodos.getLength();i++){
				Class<T> x =(Class<T>) metodoHidratar.invoke(nodos.item(i));
				listado.add((T) x);
			}
		}
		return listado;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private <T> void bajarObjetoAXML(String nombreArchivo,List<T> listado,Class clase) throws ParserConfigurationException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, TransformerException{
		Document doc = crearDoc();
		Method metodoSerializarMethod = clase.getDeclaredMethod("serializar",Document.class);
		for(T objeto: listado){
			Element objetoSerializado = (Element) metodoSerializarMethod.invoke(objeto, doc);
			if (doc.hasChildNodes()==false){
				doc.appendChild(objetoSerializado);
			}
			else{
				doc.getFirstChild().appendChild(objetoSerializado);
			}
		transformarYEscribirADisco(nombreArchivo, doc);
		}
	}
	public void levantarPoliciasDelXML() throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
		 levantarAlgoDelXML(nombreArchivoPolicias, Policia.class,listadoPolicias);
	
	}
	public void levantarPaisesDelXML() throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, ParserConfigurationException, SAXException, IOException{
		levantarAlgoDelXML(nombreArchivoPaises, Pais.class, listadoPaises);
	}
	
	public void levantarLadronesDelXML() throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
		 levantarAlgoDelXML(nombreArchivoLadrones, Ladron.class, listadoLadrones);
	}

	private Document crearDoc() throws ParserConfigurationException{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.newDocument();
		return doc;
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
	public void bajarPoliciasAXML() throws ParserConfigurationException, TransformerException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		bajarObjetoAXML(nombreArchivoPolicias, listadoPolicias, Policia.class);
		}
	public void bajarLadronesAXML() throws ParserConfigurationException, TransformerException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		bajarObjetoAXML(nombreArchivoLadrones, listadoLadrones,Ladron.class);
	}
	
	public void agregarPolicia(Policia unPolicia){
		listadoPolicias.add(unPolicia);
	}
	public void agregarLadron(Ladron unLadron){
		listadoLadrones.add(unLadron);
	}
	public Boolean ladronEstaEnJuego(Ladron unLadron){
		return listadoLadrones.contains(unLadron);
	}
	public Boolean policiaEstaEnJuego(Policia unPolicia){
		return listadoPolicias.contains(unPolicia);
	}
	
	private Policia verSiJugadorYaJugo(String unNombre){
		Policia unPolicia = new Policia(unNombre, 0);//Lo creo de antemano para poder hacer el .equals()
		for (Policia policia: listadoPolicias){
			if (unPolicia.equals(policia)){
				unPolicia = policia;
				return unPolicia;//Devuelvo el policia que ya estaba en el listado
			}
		}
		listadoPolicias.add(unPolicia);//En todo caso, agrego el policia nuevo
		return unPolicia;
	}
	public Policia iniciarJugador(String unNombre){
		return verSiJugadorYaJugo(unNombre);
	}
	private Boolean archivoExiste(String nombreArchivo){
		File archivo = new File(nombreArchivo);
		if (archivo.exists()){
			return true;
		}
		return false;
	}
	public Boolean archivoPoliciasExiste(){
		return archivoExiste(nombreArchivoPolicias);
	}
	public Boolean archivoLadronesExiste(){
		return archivoExiste(nombreArchivoLadrones);
	}

	public Partida getPartida(){
		return this.unaPartida;
	}
	//Este metodo se va a ir, no se encariñen
   private void generarXMLpaises() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParserConfigurationException, TransformerException{
	   Edificio biblioteca = new Edificio("biblioteca");
		Edificio puerto = new Edificio("puerto");
		Edificio fiuba = new Edificio("fiuba");
		Edificio[] edificios = {biblioteca,puerto,fiuba};
		Pais Argentina = new Pais("Argentina",edificios,new Coordenadas(1, 1));
		Pais Brasil = new Pais("Brasil",edificios,new Coordenadas(1, 3));
		ArrayList<Pais> ListaPaises = new ArrayList<Pais>();
		ListaPaises.add(Argentina);
		ListaPaises.add(Brasil);
		listadoPaises=ListaPaises;
		bajarObjetoAXML(nombreArchivoPaises, ListaPaises, Pais.class);
   }
	public void generarPartidaXML(String nombreArchivo) throws ParserConfigurationException, TransformerException{
		
		Document doc = crearDoc();
		Element elementoPartidas = doc.createElement("Partidas");
		doc.appendChild(elementoPartidas);
		transformarYEscribirADisco(nombreArchivo, doc);
		
	}
	public Ladron buscarLadronPorString(String nombreLadron) throws ErrorNoSeEncontroLadron{
		for (Ladron ladron: listadoLadrones){
			if(nombreLadron.equals(ladron.getNombre())){
				Ladron ladronADevolver = ladron;
				return ladronADevolver;
			}
		}
		throw new ErrorNoSeEncontroLadron();
	}
	private Pais buscarPaisPorString(String nombrePais) throws ErrorNoSeEncontroPais{
		for (Pais pais:listadoPaises){
			if(nombrePais.equals(pais.getNombre())){
				Pais paisADevolver = pais;
				return paisADevolver;
			}
		}
		throw new ErrorNoSeEncontroPais();
	}
	public void agregarPartidasAlXML(String nombreArchivo,HashMap<String,String[]> PaisesConPistas, String[] Paises, String nombreLadron,String nombreObjetoRobado,String valorObjeto) throws ParserConfigurationException, SAXException, IOException, TransformerException{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.newDocument();
		doc = dBuilder.parse(nombreArchivo);
		Element elementoPartidas = (Element)doc.getElementsByTagName("Partidas").item(0);
		Element elementoUnaPartida = doc.createElement("Partida");
		Element elementoPistas = doc.createElement("Pistas");	
		elementoPartidas.appendChild(elementoUnaPartida);
		for (int i = 0; i<Paises.length;i++){
			String[] pistasdeEdificios = PaisesConPistas.get(Paises[i]);
			Element elementoPistasPais = doc.createElement("PistasPais");
			elementoPistasPais.setAttribute("NombrePais",Paises[i]);
			for(int j = 0;j < pistasdeEdificios.length;j++){
				elementoPistasPais.setAttribute("pista"+j, pistasdeEdificios[j]);
			}
			
			elementoPistas.appendChild(elementoPistasPais);
			elementoUnaPartida.appendChild(elementoPistas);
		}
		
		elementoUnaPartida.setAttribute("NombreLadron",nombreLadron);
		elementoUnaPartida.setAttribute("NombreObjeto",nombreObjetoRobado);
		elementoUnaPartida.setAttribute("ValorObjeto", valorObjeto);
		
		transformarYEscribirADisco(nombreArchivo, doc);
	}
	public void iniciarPartida(String nombreUsuario) throws ParserConfigurationException, SAXException, IOException, ErrorNoSeEncontroLadron, ErrorNoSeEncontroPais, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, TransformerException{
		generarXMLpaises();
		Policia elPolicia = iniciarJugador(nombreUsuario);
		String rangoPoliciaString = elPolicia.toStringRango();
		File archivoPartida = new File("Partidas"+rangoPoliciaString+".xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.newDocument();
		doc = dBuilder.parse(archivoPartida);
		doc.getDocumentElement().normalize();
		
		NodeList nodosPartida = doc.getElementsByTagName("Partida");
		
		Random random = new Random();
		Node nodoALevantar = nodosPartida.item(random.nextInt(nodosPartida.getLength()));
		Element elementoPartida = (Element)nodoALevantar;
		ArrayList<Pais> paisesParaTrayectoria = new ArrayList<Pais>();
		for (int i = 0; i<doc.getElementsByTagName("PistasPais").getLength();i++){
			
			Element elementoPista = (Element)doc.getElementsByTagName("PistasPais").item(i);
			Pais paisParaAgregar = buscarPaisPorString(elementoPista.getAttribute("NombrePais"));
			
			paisesParaTrayectoria.add(paisParaAgregar);
			ArrayList<String> pistasDelPais = new ArrayList<String>();
			for (int j = 0 ; j<3 ; j++){
				String pista =elementoPista.getAttribute("Pista"+j);
				if (pista.equals("HeridaCuchillo") || pista.equals("HeridaArmaDeFuego")){
					Class<?> ClaseHerida = Class.forName(pista);
					Constructor<?> ConstructorHerida = ClaseHerida.getConstructor(String.class);
					Object unaHeridaObject = ConstructorHerida.newInstance(pista);
					paisParaAgregar.getEdificios().get(j).setHerida((modelo.heridas.Herida)ClaseHerida.cast(unaHeridaObject));
				}
				pistasDelPais.add(pista);
			}
			agregarPistaAEdificios(paisParaAgregar.getEdificios(),pistasDelPais);
		}
		
	
		
		String nombreLadron = elementoPartida.getAttribute("NombreLadron");
		Ladron ladronAPasar = buscarLadronPorString(nombreLadron);
		ladronAPasar.addTrayectoria(new Trayectoria(paisesParaTrayectoria));
		ObjetoRobado ObjetoASetear = new ObjetoRobado(elementoPartida.getAttribute("NombreObjeto"),elementoPartida.getAttribute("ValorObjeto"));
		Policia unPolicia = iniciarJugador(nombreUsuario);
		BaseDeDatos baseAPasar = new BaseDeDatos((ArrayList<Ladron>)listadoLadrones,(ArrayList<Pais>)listadoPaises);
		unaPartida= new Partida(unPolicia,ladronAPasar,baseAPasar,ObjetoASetear);
	}

	private void agregarPistaAEdificios(ArrayList<Edificio> edificios,ArrayList<String> pistas){
		for (int i = 0; i< edificios.size();i++){
			edificios.get(i).setPista(pistas.get(i));
		}
	}
	
	
	public void levantarTodosLosDatos() throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, ParserConfigurationException, SAXException, IOException {
		levantarLadronesDelXML();
		levantarPaisesDelXML();
		levantarPoliciasDelXML();
		
	}
	
}

