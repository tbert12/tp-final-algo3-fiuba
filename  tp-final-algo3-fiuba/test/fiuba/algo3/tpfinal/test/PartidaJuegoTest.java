package fiuba.algo3.tpfinal.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import modelo.BaseDeDatos;
import modelo.Caracteristicas;
import modelo.Edificio;
import modelo.Ladron;
import modelo.ObjetoRobado;
import modelo.Pais;
import modelo.Partida;
import modelo.Policia;

import org.junit.Test;

public class PartidaJuegoTest {
	private Partida unaPartida;
	private Policia unPolicia;
	private Ladron unLadron;
	BaseDeDatos unaBase;
	
	
	private String[] Sexo = {"Masculino","Femenino"};
	private String[] Hobbie = {"Tennis","Musica","Alpinismo","Paracaidismo","Natacion","Croquet"};
	private String[] Cabello = {"Castanio","Rubio","Rojo","Negro"};
	private String[] Senia = {"Cojera","Anillo","Tatuaje","Cicatriz","Joyas"};
	private String[] Vehiculo = {"Descapotable","Limusina","Deportivo","Moto"};
	private ObjetoRobado unObjeto;
	
	
	private Edificio Bolsa = new Edificio("bolsa","“Le dicen que tenía el cabello castaño”");
	private Edificio Banco = new Edificio("Banco","“Cambio dinero a libras esterlinas”");
	private Edificio Aeropuerto = new Edificio("AeroPuerto", "“El avión tenía colores rojo, blanco y azul, llevaba raqueta de tenis”");
	private Edificio[] edificios = {Bolsa,Banco,Aeropuerto};
	private String nombre = "Buenos Aires";
	private Pais Argentina = new Pais(nombre,edificios);
	
	private Edificio Hotel = new Edificio("Hotel",", estaba mejorando su inglés americano");
	private Edificio BancoLDN = new Edificio("Banco","“compró dólares y llevaba un tatuaje”");
	private Edificio Muelle = new Edificio("Muelle","Fue en un crucero que tenía una bandera con estrellas");
	private Edificio[] edificiosLDN = {Hotel,BancoLDN,Muelle};
	private String nombreing = "London";
	private Pais Inglaterra = new Pais(nombreing,edificiosLDN);
	
	private Edificio AeropuertoNY = new Edificio("Aeropuerto",", estaba mejorando su inglés americano");
	private Edificio[] edificiosNY = {AeropuertoNY,Banco,Muelle};
	private String nombreUsa = "New York";
	private Pais Usa = new Pais(nombreUsa,edificiosNY);
	

	@Test
	public void TestDeJuegoCaso1 () {
		
		Caracteristicas CaracteristicasDelLadron = new Caracteristicas(Sexo[1],Hobbie[0],Cabello[0],Senia[2],Vehiculo[0]);
		unaBase = new BaseDeDatos();
		ArrayList<String> ListaDeEdificios;
		unObjeto = new ObjetoRobado("Bandera Antigua","Poco Valioso");
		unLadron = new Ladron("Roberto",CaracteristicasDelLadron);
		unPolicia = new Policia("Tylen Perez",0);
		unaBase.addPais(Argentina);
		unaBase.addPais(Inglaterra);
		unaBase.addPais(Usa);
		unaBase.addSospechoso(unLadron);
		
		
		unaPartida = new Partida(unPolicia, unLadron, unaBase, unObjeto);
		
		assertEquals(unaPartida.NombreObjetoRobado(),"Bandera Antigua");
		assertEquals(unaPartida.ValorObjetoRobado(),"Poco Valioso");
		
		
		unaPartida.ViajarHacia("Buenos Aires"); 
		
		assertEquals(unPolicia.getPais(),Argentina);
		
		ListaDeEdificios = unaPartida.NombresDeEdificiosAMostrar();
		
		assertEquals(ListaDeEdificios.get(0),"Bolsa");
		
		String PistaBolsa = unaPartida.MostrarPistaDeEdificio(ListaDeEdificios.get(0));
		assertEquals(PistaBolsa,"“Le dicen que tenía el cabello castaño”");
		
		assertEquals(ListaDeEdificios.get(1),"Banco");
		String PistaBanco = unaPartida.MostrarPistaDeEdificio(ListaDeEdificios.get(1));
		assertEquals(PistaBanco,"“Cambio dinero a libras esterlinas”");
		
		assertEquals(ListaDeEdificios.get(2),"Aeropuerto");
		String PistaAeropuerto = unaPartida.MostrarPistaDeEdificio(ListaDeEdificios.get(2));
		assertEquals(PistaAeropuerto,"“El avión tenía colores rojo, blanco y azul, llevaba raqueta de tenis”");
	
        unaPartida.ViajarHacia("London"); 
		
		assertEquals(unPolicia.getPais(),Inglaterra);
	
		ListaDeEdificios = unaPartida.NombresDeEdificiosAMostrar();
		
		assertEquals(ListaDeEdificios.get(0),"Hotel");
		
		String PistaHotel = unaPartida.MostrarPistaDeEdificio(ListaDeEdificios.get(0));
		assertEquals(PistaHotel,"”, estaba mejorando su inglés americano”.");
		
		assertEquals(ListaDeEdificios.get(1),"Banco");
		String PistaBancoLondres = unaPartida.MostrarPistaDeEdificio(ListaDeEdificios.get(1));
		assertEquals(PistaBancoLondres,"“compró dólares y llevaba un tatuaje”");
		
		
		assertEquals(ListaDeEdificios.get(2),"Muelle");
		String PistaMuelle = unaPartida.MostrarPistaDeEdificio(ListaDeEdificios.get(2));
		assertEquals(PistaMuelle,"“ Fue en un crucero que tenía una bandera con estrellas”");
	
		unaPartida.FiltrarLadron(null, null, "castaño", "tatuaje", "tenis");
		
		unaPartida.ViajarHacia("New york");
		
		assertEquals(unPolicia.getPais(),Usa);
		
		ListaDeEdificios = unaPartida.NombresDeEdificiosAMostrar();
		//aca deberia finalizar la partida
		unaPartida.MostrarPistaDeEdificio(ListaDeEdificios.get(0));
		
		}
	}
