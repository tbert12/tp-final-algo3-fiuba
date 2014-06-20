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
	
	
	
	

	@Test
	public void TestDeJuegoCaso1() throws ErrorElPaisNoEsta {
	
		Edificio Bolsa = new Edificio("Bolsa","�Le dicen que ten�a el cabello casta�o�");
		Edificio Banco = new Edificio("Banco","�Cambio dinero a libras esterlinas�");
		Edificio Aeropuerto = new Edificio("Aeropuerto", "�El avi�n ten�a colores rojo, blanco y azul, llevaba raqueta de tenis�");
		Edificio[] edificios = {Bolsa,Banco,Aeropuerto};
		String nombreArg = "Buenos Aires";
		Pais Argentina = new Pais(nombreArg,edificios);
		
		Edificio Hotel = new Edificio("Hotel",", estaba mejorando su ingl�s americano");
		Edificio BancoLDN = new Edificio("Banco","�compr� d�lares y llevaba un tatuaje�");
		Edificio Muelle = new Edificio("Muelle","Fue en un crucero que ten�a una bandera con estrellas");
		Edificio[] edificiosLDN = {Hotel,BancoLDN,Muelle};
		String nombreIng = "London";
		Pais Inglaterra = new Pais(nombreIng,edificiosLDN);
		
		Edificio AeropuertoNY = new Edificio("Aeropuerto",", estaba mejorando su ingl�s americano");
		Edificio[] edificiosNY = {AeropuertoNY,Banco,Muelle};
		String nombreUsa = "New York";
		Pais Usa = new Pais(nombreUsa,edificiosNY);
		
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
		assertEquals(PistaBolsa,"�Le dicen que ten�a el cabello casta�o�");
		
		assertEquals(ListaDeEdificios.get(1),"Banco");
		String PistaBanco = unaPartida.MostrarPistaDeEdificio(ListaDeEdificios.get(1));
		assertEquals(PistaBanco,"�Cambio dinero a libras esterlinas�");
		
		assertEquals(ListaDeEdificios.get(2),"Aeropuerto");
		String PistaAeropuerto = unaPartida.MostrarPistaDeEdificio(ListaDeEdificios.get(2));
		assertEquals(PistaAeropuerto,"�El avi�n ten�a colores rojo, blanco y azul, llevaba raqueta de tenis�");
	
        
		unaPartida.ViajarHacia("London"); 
        
		assertEquals(unPolicia.getPais(),Inglaterra);
	
		ListaDeEdificios = unaPartida.NombresDeEdificiosAMostrar();
		
		assertEquals(ListaDeEdificios.get(0),"Hotel");
		
		String PistaHotel = unaPartida.MostrarPistaDeEdificio(ListaDeEdificios.get(0));
		assertEquals(PistaHotel,Hotel.getPista());
		
		assertEquals(ListaDeEdificios.get(1),"Banco");
		String PistaBancoLondres = unaPartida.MostrarPistaDeEdificio(ListaDeEdificios.get(1));
		assertEquals(PistaBancoLondres,"�compr� d�lares y llevaba un tatuaje�");
		
		
		assertEquals(ListaDeEdificios.get(2),"Muelle");
		String PistaMuelle = unaPartida.MostrarPistaDeEdificio(ListaDeEdificios.get(2));
		assertEquals(PistaMuelle,Muelle.getPista());
	
		//filtra ladron con coincidencias.
		unaPartida.FiltrarLadron(null,null,Cabello[0],Senia[2],Vehiculo[0]);
	
		unaPartida.ViajarHacia(Usa.getNombre());
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
	
	@Test
	public void TestDeJuegoCaso2() throws ErrorElPaisNoEsta {
		
		Edificio Museo = new Edificio("Museo","Era alto y de contextura delgada");
		Edificio Aeropuerto = new Edificio("Aeropuerto","se mov�a en auto con bandera roja y blanca");
		Edificio[] edificiosMex = {Museo,Aeropuerto};
		String nombreMEX = "Veracruz";
		Pais Mexico = new Pais(nombreMEX,edificiosMex);
		
		Edificio Banco = new Edificio("Banco","Nunca vi a esa persona");
		Edificio Muelle = new Edificio("Muelle","Por ac� pasa mucha gente");
		Edificio[] edificiosItalia = {Banco,Muelle};
		String nombreITA = "Milan";
		Pais Italia = new Pais(nombreITA,edificiosItalia);
		
		Edificio MuseoAUS = new Edificio("Museo","No conosco nadie as�");
		Edificio HotelAUS = new Edificio("Hotel","Por aca nada, paisanito");
		Edificio[] edificiosAUS = {MuseoAUS,HotelAUS};
		String nombreAUS = "Sidney";
		Pais Australia = new Pais(nombreAUS,edificiosAUS);
		
		Pais USA = new Pais("USA",edificiosAUS);
		
		Edificio BolsaARG = new Edificio("Bolsa","�Le dicen que ten�a el cabello casta�o�");
		Edificio BancoARG = new Edificio("Banco","�Cambio dinero a libras esterlinas�");
		Edificio AeropuertoARG = new Edificio("Aeropuerto", "�El avi�n ten�a colores rojo, blanco y azul, llevaba raqueta de tenis�");
		Edificio[] edificiosArgentina = {BolsaARG,BancoARG,AeropuertoARG};
		String nombreArg = "Buenos Aires";
		Pais Argentina = new Pais(nombreArg,edificiosArgentina);
		
		Edificio HotelLND = new Edificio("Hotel",", estaba mejorando su ingl�s americano");
		Edificio BancoLDN = new Edificio("Banco","�compr� d�lares y llevaba un tatuaje�");
		Edificio MuelleLND = new Edificio("Muelle","Fue en un crucero que ten�a una bandera con estrellas");
		Edificio[] edificiosLDN = {HotelLND,BancoLDN,MuelleLND};
		String nombreIng = "London";
		Pais Inglaterra = new Pais(nombreIng,edificiosLDN);
		
		Caracteristicas CaracteristicasDelLadron = new Caracteristicas(Sexo[0],Hobbie[1],Cabello[1],Senia[2],Vehiculo[3]);
		unaBase = new BaseDeDatos();
		unObjeto = new ObjetoRobado("Joya","Valioso");
		
		
		unLadron = new Ladron("Nihuel",CaracteristicasDelLadron);
		ArrayList<Pais> PaisesDeLadron = new ArrayList<Pais>();
		PaisesDeLadron.add(Mexico);
		PaisesDeLadron.add(Argentina);
		PaisesDeLadron.add(Inglaterra);
		
		Caracteristicas CaracteristicasSimilares = new Caracteristicas(Sexo[0],Hobbie[1],Cabello[1],Senia[2],Vehiculo[2]);
		Ladron unLadronSimilCaracteristicas = new Ladron("Willy",CaracteristicasSimilares);
		
		Caracteristicas CaracteristicasNadaQueVer = new Caracteristicas(Sexo[1],Hobbie[2],Cabello[1],Senia[0],Vehiculo[0]);
		Ladron unLadronNadaQueVer = new Ladron("Larry",CaracteristicasNadaQueVer);
		
		Trayectoria trayecto = new Trayectoria(PaisesDeLadron);
		unLadron.addTrayectoria(trayecto);
		
		unPolicia = new Policia("Pedraza",0);
		Tiempo UnTiempo = new Tiempo(147);
		unPolicia.setTiempo(UnTiempo);
		
		unaBase.addPais(Argentina);
		unaBase.addPais(Inglaterra);
		unaBase.addPais(Mexico);
		unaBase.addPais(Australia);
		unaBase.addPais(Italia);
		unaBase.addPais(USA);
		
		unaBase.addSospechoso(unLadron);
		unaBase.addSospechoso(unLadronSimilCaracteristicas);
		unaBase.addSospechoso(unLadronNadaQueVer);
		
		int DistanciaMEXaITA = 9600;
		int DistanciaITAaAUS = 16500;
		int DistanciaAUSaUSA = 16000;
				
		HashMap<String,Integer> DistanciaDesdeMexico = new HashMap<String,Integer>();
		DistanciaDesdeMexico.put(nombreITA,DistanciaMEXaITA);
		Mexico.setDistancias(DistanciaDesdeMexico);

		HashMap<String,Integer> DistanciaDesdeITA = new HashMap<String,Integer>();
		DistanciaDesdeITA.put(nombreAUS,DistanciaITAaAUS);
		DistanciaDesdeITA.put(Mexico.getNombre(),DistanciaMEXaITA);
		Italia.setDistancias(DistanciaDesdeITA);
		
		HashMap<String,Integer> DistanciaDesdeAUS = new HashMap<String,Integer>();
		DistanciaDesdeAUS.put(USA.getNombre(),DistanciaAUSaUSA);
		DistanciaDesdeAUS.put(Italia.getNombre(),DistanciaITAaAUS);
		Australia.setDistancias(DistanciaDesdeAUS);
		
		unaPartida = new Partida(unPolicia, unLadron, unaBase, unObjeto);
		
		assertEquals(unPolicia.getPais(),Mexico);
		
		//El Jugador malgasta el tiempo viajando
		unaPartida.MostrarPistaDeEdificio(Museo.getNombre());
		unaPartida.MostrarPistaDeEdificio(Aeropuerto.getNombre());
		
		unaPartida.ViajarHacia(Italia.getNombre());
		unaPartida.MostrarPistaDeEdificio(Banco.getNombre());
		unaPartida.MostrarPistaDeEdificio(Muelle.getNombre());
		
		unaPartida.ViajarHacia(Australia.getNombre());
		unaPartida.MostrarPistaDeEdificio(MuseoAUS.getNombre());
		unaPartida.MostrarPistaDeEdificio(HotelAUS.getNombre());
		
		unaPartida.ViajarHacia(Italia.getNombre());
		
		unaPartida.ViajarHacia(Mexico.getNombre());
		
		unaPartida.ViajarHacia(Italia.getNombre());
		
		unaPartida.ViajarHacia(Australia.getNombre());
		
		unaPartida.ViajarHacia(USA.getNombre());
		
		assertTrue(unPolicia.TiempoAgotado());
		
	}

}


