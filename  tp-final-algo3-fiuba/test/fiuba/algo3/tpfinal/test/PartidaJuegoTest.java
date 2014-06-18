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
	private String edificioAux;
	private Partida unaPartida;
	private Policia unPolicia;
	private Ladron unLadron;
	private String nombreArchivo = "pruebaLadron.xml";
	private String[] Sexo = {"Masculino","Femenino"};
	private String[] Hobbie = {"Tennis","Musica","Alpinismo","Paracaidismo","Natacion","Croquet"};
	private String[] Cabello = {"Castanio","Rubio","Rojo","Negro"};
	private String[] Senia = {"Cojera","Anillo","Tatuaje","Cicatriz","Joyas"};
	private String[] Vehiculo = {"Descapotable","Limusina","Deportivo","Moto"};
	private ObjetoRobado unObjeto;
	private Edificio Bolsa = new Edificio("bolsa","bolsa pista");
	private Edificio Banco = new Edificio("Banco","puerto pista");
	private Edificio Aeropuerto = new Edificio("AeroPuerto", "fiuba pista");
	private Edificio[] edificios = {Bolsa,Banco,Aeropuerto};
	private String nombre = "Argentina";
	private Pais UnPais = new Pais(nombre,edificios);
	Pais Argentina =new Pais("BuenosAires",edificios);
	Pais London =new Pais("London",edificios);
	Pais Argelia =new Pais("Argelia",edificios);
	Pais Alemania =new Pais("Alemania",edificios);
	Pais Rusia =new Pais("Rusia",edificios);
	Pais Peru =new Pais("Peru",edificios);
	
	/* Caso 1: Lo atrapa
	-Empieza en Bs As.
	-Visita Bolsa
	Pista: “Le dicen que tenía el cabello castaño”.
	-Visita Banco
	Pista: “Cambio dinero a libras esterlinas”.
	-Visita aeropuerto,
	Pista: “El avión tenía colores rojo, blanco y azul, llevaba raqueta de tenis”.
	-Viajo a London.
	-Recibe herida de arma de fuego.
	-Visita hotel
	Pista: ”, estaba mejorando su inglés americano”.
	-Visita banco
	            	Pista: “compró dólares y llevaba un tatuaje”.
	-Visita muelle
	            	Pista: “ Fue en un crucero que tenía una bandera con estrellas”.
	-Emitir orden de arresto: Cabello castaño, senia tatuaje, hobby tenis.
	            	Orden de arresto emitida para “Tylen Perez”.
	-Viajo a New York.
	-Visita aeropuerto
	            	Encontró al ladrón, policía lo atrapó

	
	*/
	@Test
	public void TestDeJuego() {
		
		Caracteristicas CaracteristicasDelLadron = new Caracteristicas(Sexo[1],Hobbie[0],Cabello[3],Senia[1],Vehiculo[0]);
		BaseDeDatos unaBase = new BaseDeDatos();
		ArrayList<String> ListaDeEdificios;
		unObjeto = new ObjetoRobado("La Gioconda","Poco Valiso");
		unLadron = new Ladron("Roberto",CaracteristicasDelLadron);
		unPolicia = new Policia("Juan",0);
		unaBase.addPais(Argentina);
		unaBase.addPais(London);
		unaBase.addSospechoso(unLadron);
		
		
		unaPartida = new Partida(unPolicia, unLadron, unaBase, unObjeto);
		unaPartida.ViajarHacia("Buenos Aires"); 
		
		assertEquals(unPolicia.getPais(),Argentina);
		
		ListaDeEdificios = unaPartida.NombresDeEdificiosAMostrar();
		
		assertEquals(ListaDeEdificios.get(0),"Bolsa");
		
		String Pista = unaPartida.MostrarPistaDeEdificio(ListaDeEdificios.get(0));
		assertEquals(Pista,"“Le dicen que tenía el cabello castaño”");
		
		String Pista2 = unaPartida.MostrarPistaDeEdificio(ListaDeEdificios.get(1));
		assertEquals(Pista2,"“Cambio dinero a libras esterlinas”");
		
		String Pista3 = unaPartida.MostrarPistaDeEdificio(ListaDeEdificios.get(2));
		assertEquals(Pista3,"“El avión tenía colores rojo, blanco y azul, llevaba raqueta de tenis”");
	
        unaPartida.ViajarHacia("London"); 
		
		assertEquals(unPolicia.getPais(),London);
	
		ListaDeEdificios = unaPartida.NombresDeEdificiosAMostrar();
		
		assertEquals(ListaDeEdificios.get(0),"Hotel");
		
		String Pista = unaPartida.MostrarPistaDeEdificio(ListaDeEdificios.get(0));
		assertEquals(Pista,"”, estaba mejorando su inglés americano”.");
		
		String Pista2 = unaPartida.MostrarPistaDeEdificio(ListaDeEdificios.get(1));
		assertEquals(Pista2,"“compró dólares y llevaba un tatuaje”");
		
		String Pista3 = unaPartida.MostrarPistaDeEdificio(ListaDeEdificios.get(2));
		assertEquals(Pista3,"“ Fue en un crucero que tenía una bandera con estrellas”");
	
		unaPartida.FiltrarLadron(unSexo, unHobby, "castaño", "tatuaje", "tenis");
		/*
		-Visita hotel
		Pista: ”, estaba mejorando su inglés americano”.
		-Visita banco
		            	Pista: “compró dólares y llevaba un tatuaje”.
		-Visita muelle
		            	Pista: “ Fue en un crucero que tenía una bandera con estrellas”.
		-Emitir orden de arresto: Cabello castaño, senia tatuaje, hobby tenis.
		            	Orden de arresto emitida para “Tylen Perez”.
	
	*/
	}
		
	
	
	

		
		
		
	}

}
