package fiuba.algo3.tpfinal.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import modelo.BaseDeDatos;
import modelo.Edificio;
import modelo.Ladron;
import modelo.ObjetoRobado;
import modelo.Pais;
import modelo.Partida;
import modelo.Policia;
import modelo.Tiempo;
import modelo.Trayectoria;
import modelo.caracteristicas.*;
import modelo.excepcion.ErrorElPaisNoEsta;

import org.junit.Test;

public class PartidaJuegoTest {
	private Partida unaPartida;
	private Policia unPolicia;
	private Ladron unLadron;
	BaseDeDatos unaBase;
	private ObjetoRobado unObjeto;
	
	@Test
	public void TestDeJuegoCaso1() throws ErrorElPaisNoEsta {
	
		Edificio Bolsa = new Edificio("Bolsa","“Le dicen que tenía el cabello castaño”");
		Edificio Banco = new Edificio("Banco","“Cambio dinero a libras esterlinas”");
		Edificio Aeropuerto = new Edificio("Aeropuerto", "“El avión tenía colores rojo, blanco y azul, llevaba raqueta de tenis”");
		Edificio[] edificios = {Bolsa,Banco,Aeropuerto};
		String nombreArg = "Buenos Aires";
		Pais Argentina = new Pais(nombreArg,edificios);
		
		Edificio Hotel = new Edificio("Hotel",", estaba mejorando su inglés americano");
		Edificio BancoLDN = new Edificio("Banco","“compró dólares y llevaba un tatuaje”");
		Edificio Muelle = new Edificio("Muelle","Fue en un crucero que tenía una bandera con estrellas");
		Edificio[] edificiosLDN = {Hotel,BancoLDN,Muelle};
		String nombreIng = "London";
		Pais Inglaterra = new Pais(nombreIng,edificiosLDN);
		
		Edificio AeropuertoNY = new Edificio("Aeropuerto","Algo huele mal");
		AeropuertoNY.setLadron();
		Edificio[] edificiosNY = {AeropuertoNY};
		String nombreUsa = "New York";
		Pais Usa = new Pais(nombreUsa,edificiosNY);
		
		Caracteristicas CaracteristicasDelLadron = new Caracteristicas(Sexo.FEMENINO,Hobby.TENNIS,Cabello.CASTANIO,Senia.TATUAJE,Vehiculo.DESCAPOTABLE);
		unaBase = new BaseDeDatos();
		ArrayList<Edificio> ListaDeEdificios;
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
		
		assertEquals(unaPartida.nombreObjetoRobado(),"Bandera Antigua");
		assertEquals(unaPartida.valorObjetoRobado(),"Poco Valioso");
		
		assertEquals(unPolicia.getPais(),Argentina);
		
		ListaDeEdificios = unaPartida.edificiosAMostrar();
		
		assertEquals(ListaDeEdificios.get(0),Bolsa);
		
		String PistaBolsa = unaPartida.visitarEdificio(ListaDeEdificios.get(0));
		assertEquals(PistaBolsa,Bolsa.visitar(unPolicia));
		
		assertEquals(ListaDeEdificios.get(1),Banco);
		String PistaBanco = unaPartida.visitarEdificio(ListaDeEdificios.get(1));
		assertEquals(PistaBanco,Banco.visitar(unPolicia));
		
		assertEquals(ListaDeEdificios.get(2),Aeropuerto);
		String PistaAeropuerto = unaPartida.visitarEdificio(ListaDeEdificios.get(2));
		assertEquals(PistaAeropuerto,Aeropuerto.visitar(unPolicia));
	
        
		unaPartida.viajarHacia(Inglaterra); 
        
		assertEquals(unPolicia.getPais(),Inglaterra);
	
		ListaDeEdificios = unaPartida.edificiosAMostrar();
		
		assertEquals(ListaDeEdificios.get(0),Hotel);
		
		String PistaHotel = unaPartida.visitarEdificio(ListaDeEdificios.get(0));
		assertEquals(PistaHotel,Hotel.visitar(unPolicia));
		
		assertEquals(ListaDeEdificios.get(1),BancoLDN);
		String PistaBancoLondres = unaPartida.visitarEdificio(ListaDeEdificios.get(1));
		assertEquals(PistaBancoLondres,BancoLDN.visitar(unPolicia));
		
		
		assertEquals(ListaDeEdificios.get(2),Muelle);
		String PistaMuelle = unaPartida.visitarEdificio(ListaDeEdificios.get(2));
		assertEquals(PistaMuelle,Muelle.visitar(unPolicia));

		//filtra ladron con coincidencias.
		unaPartida.filtrarLadron(null,null,Cabello.CASTANIO,Senia.TATUAJE,Vehiculo.DESCAPOTABLE);
	
		unaPartida.viajarHacia(Usa);
		assertEquals(unPolicia.getPais(),Usa);
		
		ListaDeEdificios = unaPartida.edificiosAMostrar();
		//El policia esta en el mismo pais que el ladron, deberia encontrarlo
		
		//si la orden de arresto se emitio el ladron debe ser atrapado
		assertEquals(unaPartida.visitarEdificio(ListaDeEdificios.get(0)),"Ladron Atrapado");
		assertTrue(unaPartida.seTerminoLaPartida());
		}
	
	@Test
	public void TestDeJuegoCaso2() throws ErrorElPaisNoEsta {
		
		Edificio Museo = new Edificio("Museo","Era alto y de contextura delgada");
		Edificio Aeropuerto = new Edificio("Aeropuerto","se movía en auto con bandera roja y blanca");
		Edificio[] edificiosMex = {Museo,Aeropuerto};
		String nombreMEX = "Veracruz";
		Pais Mexico = new Pais(nombreMEX,edificiosMex);
		
		Edificio Banco = new Edificio("Banco","Nunca vi a esa persona");
		Edificio Muelle = new Edificio("Muelle","Por acá pasa mucha gente");
		Edificio[] edificiosItalia = {Banco,Muelle};
		String nombreITA = "Milan";
		Pais Italia = new Pais(nombreITA,edificiosItalia);
		
		Edificio MuseoAUS = new Edificio("Museo","No conosco nadie así");
		Edificio HotelAUS = new Edificio("Hotel","Por aca nada, paisanito");
		Edificio[] edificiosAUS = {MuseoAUS,HotelAUS};
		String nombreAUS = "Sidney";
		Pais Australia = new Pais(nombreAUS,edificiosAUS);
		
		Pais USA = new Pais("USA",edificiosAUS);
		
		Edificio BolsaARG = new Edificio("Bolsa","“Le dicen que tenía el cabello castaño”");
		Edificio BancoARG = new Edificio("Banco","“Cambio dinero a libras esterlinas”");
		Edificio AeropuertoARG = new Edificio("Aeropuerto", "“El avión tenía colores rojo, blanco y azul, llevaba raqueta de tenis”");
		Edificio[] edificiosArgentina = {BolsaARG,BancoARG,AeropuertoARG};
		String nombreArg = "Buenos Aires";
		Pais Argentina = new Pais(nombreArg,edificiosArgentina);
		
		Edificio HotelLND = new Edificio("Hotel",", estaba mejorando su inglés americano");
		Edificio BancoLDN = new Edificio("Banco","“compró dólares y llevaba un tatuaje”");
		Edificio MuelleLND = new Edificio("Muelle","Fue en un crucero que tenía una bandera con estrellas");
		Edificio[] edificiosLDN = {HotelLND,BancoLDN,MuelleLND};
		String nombreIng = "London";
		Pais Inglaterra = new Pais(nombreIng,edificiosLDN);

		Caracteristicas CaracteristicasDelLadron = new Caracteristicas(Sexo.MASCULINO,Hobby.MUSICA,Cabello.RUBIO,Senia.TATUAJE,Vehiculo.MOTO);
		unaBase = new BaseDeDatos();
		unObjeto = new ObjetoRobado("Joya","Valioso");
		
		
		unLadron = new Ladron("Nihuel",CaracteristicasDelLadron);
		ArrayList<Pais> PaisesDeLadron = new ArrayList<Pais>();
		PaisesDeLadron.add(Mexico);
		PaisesDeLadron.add(Argentina);
		PaisesDeLadron.add(Inglaterra);

		Caracteristicas CaracteristicasSimilares = new Caracteristicas(Sexo.MASCULINO,Hobby.MUSICA,Cabello.RUBIO,Senia.TATUAJE,Vehiculo.DEPORTIVO);
		Ladron unLadronSimilCaracteristicas = new Ladron("Willy",CaracteristicasSimilares);

		Caracteristicas CaracteristicasNadaQueVer = new Caracteristicas(Sexo.FEMENINO,Hobby.ALPINISMO,Cabello.RUBIO,Senia.COJERA,Vehiculo.DESCAPOTABLE);
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
		unaPartida.visitarEdificio(Museo);
		unaPartida.visitarEdificio(Aeropuerto);
		
		unaPartida.viajarHacia(Italia);
		unaPartida.visitarEdificio(Banco);
		unaPartida.visitarEdificio(Muelle);
		
		unaPartida.viajarHacia(Australia);
		unaPartida.visitarEdificio(MuseoAUS);
		unaPartida.visitarEdificio(HotelAUS);
		
		unaPartida.viajarHacia(Italia);
		
		unaPartida.viajarHacia(Mexico);
		
		unaPartida.viajarHacia(Italia);
		
		unaPartida.viajarHacia(Australia);
		
		unaPartida.viajarHacia(USA);
		
		assertTrue(unPolicia.tiempoAgotado());
		
	}

}


