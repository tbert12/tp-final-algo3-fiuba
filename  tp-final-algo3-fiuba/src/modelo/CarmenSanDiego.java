package modelo;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;





public  class CarmenSanDiego {
	private static String nombreArchivoPolicias = "RegistroPolicias.xml";
	private static String nombreArchivoLadrones = "RegistroLadrones.xml";
	private static List<Policia> liststadoPolicias = new ArrayList<Policia>();
	private static List<Ladron> listadoLadrones = new ArrayList<Ladron>();
	private Partida unaPartida;
	
	public  CarmenSanDiego() throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, ParserConfigurationException, SAXException, IOException{
		levantarPoliciasDelXML();
		levantarLadronesDelXML();
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
	liststadoPolicias = levantarAlgoDelXML(nombreArchivoPolicias, Policia.class,liststadoPolicias);
	
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
		DOMSource source = new DOMSource(doc);
		File archivoDestino = new File(nombreArchivo);
		StreamResult result = new StreamResult(archivoDestino);
		transformer.transform(source, result);
	}
	public void bajarPoliciasAXML() throws ParserConfigurationException, TransformerException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		bajarObjetoAXML(nombreArchivoPolicias, liststadoPolicias, Policia.class);
		}
	public void bajarLadronesAXML() throws ParserConfigurationException, TransformerException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		bajarObjetoAXML(nombreArchivoLadrones, listadoLadrones,Ladron.class);
	}

	public void agregarPolicia(Policia unPolicia){
		liststadoPolicias.add(unPolicia);
	}
	public void agregarLadron(Ladron unLadron){
		listadoLadrones.add(unLadron);
	}
	public Boolean ladronEstaEnJuego(Ladron unLadron){
		return listadoLadrones.contains(unLadron);
	}
	public Boolean policiaEstaEnJuego(Policia unPolicia){
		return liststadoPolicias.contains(unPolicia);
	}
	
	private Policia VerSiJugadorYaJugo(String unNombre){
		Policia unPolicia = new Policia(unNombre, 0);//Lo creo de antemano para poder hacer el .equals()
		for (Policia policia: liststadoPolicias){
			if (unPolicia.equals(policia)){
				unPolicia = policia;
				return unPolicia;//Devuelvo el policia que ya estaba en el listado
			}
		}
		liststadoPolicias.add(unPolicia);//En todo caso, agrego el policia nuevo
		return unPolicia;
	}
	public Policia IniciarJugador(String unNombre){
		return VerSiJugadorYaJugo(unNombre);
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

	public void iniciarPartida(String string) {
		// TODO Auto-generated method stub
		
	}
	
}

