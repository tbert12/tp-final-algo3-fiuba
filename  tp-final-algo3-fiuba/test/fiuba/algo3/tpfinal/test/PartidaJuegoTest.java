package fiuba.algo3.tpfinal.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import modelo.BaseDeDatos;
import modelo.Caracteristicas;
import modelo.Edificio;
import modelo.Ladron;
import modelo.ObjetoRobado;
import modelo.Pais;
import modelo.Partida;
import modelo.Policia;
import modelo.Tiempo;
import modelo.Trayectoria;
import modelo.excepcion.ErrorElPaisNoEsta;

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
	
	
	private Edificio Bolsa = new Edificio("Bolsa","“Le dicen que tenía el cabello castaño”");
	private Edificio Banco = new Edificio("Banco","“Cambio dinero a libras esterlinas”");
	private Edificio Aeropuerto = new Edificio("Aeropuerto", "“El avión tenía colores rojo, blanco y azul, llevaba raqueta de tenis”");
	private Edificio[] edificios = {Bolsa,Banco,Aeropuerto};
	private String nombreArg = "Buenos Aires";
	private Pais Argentina = new Pais(nombreArg,edificios);
	
	private Edificio Hotel = new Edificio("Hotel",", estaba mejorando su inglés americano");
	private Edificio BancoLDN = new Edificio("Banco","“compró dólares y llevaba un tatuaje”");
	private Edificio Muelle = new Edificio("Muelle","Fue en un crucero que tenía una bandera con estrellas");
	private Edificio[] edificiosLDN = {Hotel,BancoLDN,Muelle};
	private String nombreIng = "London";
	private Pais Inglaterra = new Pais(nombreIng,edificiosLDN);
	
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
		ArrayList<Pais> PaisesDeLadron = new ArrayList<Pais>();
		PaisesDeLadron.add(Argentina);
		PaisesDeLadron.add(Inglaterra);
		PaisesDeLadron.add(Usa);
		
		Trayectoria trayecto = new Trayectoria(PaisesDeLadron);
		unLadron.addTrayectoria(trayecto);
		
		unPolicia = new Policia("Tylen Perez",0);
		Tiempo UnTiempo = new Tiempo(154);
		unPolicia.setTiempo(UnTiempo);
		
		unaBase.addPais(Argentina);
		unaBase.addPais(Inglaterra);
		unaBase.addPais(Usa);
		unaBase.addSospechoso(unLadron);
		
		int DistanciaARGaING = 11128;
		int DistanciaARGaUSA = 8526;
		int DistanciaINGaUSA = 5570;
				
		HashMap<String,Integer> DistanciaDesdeArgentina = new HashMap<String,Integer>();
		DistanciaDesdeArgentina.put(nombreIng,DistanciaARGaING);
		DistanciaDesdeArgentina.put(nombreUsa,DistanciaARGaUSA);
		Argentina.setDistancias(DistanciaDesdeArgentina);

		HashMap<String,Integer> DistanciaDesdeUsa = new HashMap<String,Integer>();
		DistanciaDesdeUsa.put(nombreIng,DistanciaINGaUSA);
		DistanciaDesdeUsa.put(nombreArg,DistanciaARGaUSA);
		Usa.setDistancias(DistanciaDesdeUsa);
		
		HashMap<String,Integer> DistanciaDesdeIng = new HashMap<String,Integer>();
		DistanciaDesdeIng.put(nombreArg,DistanciaARGaING);
		DistanciaDesdeIng.put(nombreUsa,DistanciaINGaUSA);
		Inglaterra.setDistancias(DistanciaDesdeIng);
		
		unaPartida = new Partida(unPolicia, unLadron, unaBase, unObjeto);
		
		assertEquals(unaPartida.NombreObjetoRobado(),"Bandera Antigua");
		assertEquals(unaPartida.ValorObjetoRobado(),"Poco Valioso");
		
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
	
        try{
			unaPartida.ViajarHacia("London"); 
		}
		catch(ErrorElPaisNoEsta e){	
		}
        
		assertEquals(unPolicia.getPais(),Inglaterra);
	
		ListaDeEdificios = unaPartida.NombresDeEdificiosAMostrar();
		
		assertEquals(ListaDeEdificios.get(0),"Hotel");
		
		String PistaHotel = unaPartida.MostrarPistaDeEdificio(ListaDeEdificios.get(0));
		assertEquals(PistaHotel,Hotel.getPista());
		
		assertEquals(ListaDeEdificios.get(1),"Banco");
		String PistaBancoLondres = unaPartida.MostrarPistaDeEdificio(ListaDeEdificios.get(1));
		assertEquals(PistaBancoLondres,"“compró dólares y llevaba un tatuaje”");
		
		
		assertEquals(ListaDeEdificios.get(2),"Muelle");
		String PistaMuelle = unaPartida.MostrarPistaDeEdificio(ListaDeEdificios.get(2));
		assertEquals(PistaMuelle,Muelle.getPista());
	
		//filtra ladron con coincidencias.
		unaPartida.FiltrarLadron(null,null,Cabello[0],Senia[2],Vehiculo[0]);

		try{	
			unaPartida.ViajarHacia(Usa.getNombre());
		}
		catch(ErrorElPaisNoEsta e){	
		}
		assertEquals(unPolicia.getPais(),Usa);
		
		ListaDeEdificios = unaPartida.NombresDeEdificiosAMostrar();
		//El policia esta en el mismo pais que el ladron, deberia encontrarlo
		
		//pasa por el primer edificio
		unaPartida.MostrarPistaDeEdificio(ListaDeEdificios.get(0));
		unaPartida.MostrarPistaDeEdificio(ListaDeEdificios.get(1));
		
		//si la orden de arresto se emitio el ladron debe ser atrapado
		assertEquals(unaPartida.MostrarPistaDeEdificio(ListaDeEdificios.get(2)),"Atrapado");
		assertTrue(unaPartida.SeTerminoLaPartida());
		}
	}
