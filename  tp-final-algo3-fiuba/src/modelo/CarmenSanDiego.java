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
	private static List<Policia> ListadoPolicias = new ArrayList<Policia>();
	private static List<Ladron> ListadoLadrones = new ArrayList<Ladron>();
	
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
	ListadoPolicias = levantarAlgoDelXML(nombreArchivoPolicias, Policia.class,ListadoPolicias);
	
	}
	
	public void levantarLadronesDelXML() throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
		levantarAlgoDelXML(nombreArchivoLadrones, Ladron.class, ListadoLadrones);
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
		bajarObjetoAXML(nombreArchivoPolicias, ListadoPolicias, Policia.class);
		}
	public void bajarLadronesAXML() throws ParserConfigurationException, TransformerException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		bajarObjetoAXML(nombreArchivoLadrones, ListadoLadrones,Ladron.class);
	}

	public void agregarPolicia(Policia unPolicia){
		ListadoPolicias.add(unPolicia);
	}
	public void agregarLadron(Ladron unLadron){
		ListadoLadrones.add(unLadron);
	}
	public Boolean ladronEstaEnJuego(Ladron unLadron){
		return ListadoLadrones.contains(unLadron);
	}
	public Boolean policiaEstaEnJuego(Policia unPolicia){
		return ListadoPolicias.contains(unPolicia);
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

	
	}

