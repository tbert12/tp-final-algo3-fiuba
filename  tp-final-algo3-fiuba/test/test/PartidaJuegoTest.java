package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import modelo.BaseDeDatos;
import modelo.Coordenadas;
import modelo.Edificio;
import modelo.Ladron;
import modelo.ObjetoRobado;
import modelo.Pais;
import modelo.Partida;
import modelo.Policia;
import modelo.Trayectoria;
import modelo.caracteristicas.*;

import org.junit.Test;

public class PartidaJuegoTest {
	private Partida unaPartida;
	private Policia unPolicia;
	private Ladron unLadron;
	BaseDeDatos unaBase;
	private ObjetoRobado unObjeto;
	
	@Test
	public void TestDeJuegoCaso1() {
		Caracteristicas CaracteristicasDelLadron = new Caracteristicas(Sexo.FEMENINO,Hobby.TENNIS,Cabello.NEGRO,Senia.ANILLO,Vehiculo.DESCAPOTABLE);
		Ladron ladron = new Ladron("NombredeLadron",CaracteristicasDelLadron);
		Edificio Bolsa = new Edificio("Bolsa");
		Edificio Banco = new Edificio("Banco");
		Edificio Aeropuerto = new Edificio("Aeropuerto");
		Edificio[] edificios = {Bolsa,Banco,Aeropuerto};
		String nombreArg = "Buenos Aires";
		Coordenadas coordenadasBuenosAires = new Coordenadas(-34.608418,-58.373161);
		Pais Argentina = new Pais(nombreArg,edificios,coordenadasBuenosAires);
		
		Edificio Hotel = new Edificio("Hotel");
		Edificio BancoLDN = new Edificio("Banco");
		Edificio Muelle = new Edificio("Muelle");
		Edificio[] edificiosLDN = {Hotel,BancoLDN,Muelle};
		String nombreIng = "London";
		Coordenadas coordenadasLondres = new Coordenadas(51.500153,-0.126236);
		Pais Inglaterra = new Pais(nombreIng,edificiosLDN,coordenadasLondres);
		
		Edificio AeropuertoNY = new Edificio("Aeropuerto");
		AeropuertoNY.setLadron(ladron);
		Edificio[] edificiosNY = {AeropuertoNY};
		String nombreUsa = "New York";
		Coordenadas coordenadasNY = new Coordenadas(40.714268,-74.005974);
		Pais Usa = new Pais(nombreUsa,edificiosNY,coordenadasNY);
		
		
		
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
		
		ArrayList<Ladron> listLadrones = new ArrayList<Ladron>();
		ArrayList<Pais> listPaises = new ArrayList<Pais>();
		
		listPaises.add(Argentina);
		listPaises.add(Inglaterra);
		listPaises.add(Usa);
		listLadrones.add(unLadron);
		
		
		unaBase = new BaseDeDatos(listLadrones,listPaises);
		
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
		ArrayList<Edificio> EdificiosDondePuedeEstarElLadron = unaPartida.edificiosAMostrar();
		for (Edificio edificio: EdificiosDondePuedeEstarElLadron){
			unaPartida.visitarEdificio(edificio);
		}
		assertTrue(unaPartida.seTerminoLaPartida());
		}
	
	@Test
	public void TestDeJuegoCaso2() {
		
		Edificio Museo = new Edificio("Museo");
		Museo.setPista("Era alto y de contextura delgada");
		Edificio Aeropuerto = new Edificio("Aeropuerto");
		Aeropuerto.setPista("se movía en auto con bandera roja y blanca");
		Edificio[] edificiosMex = {Museo,Aeropuerto};
		String nombreMEX = "Veracruz";
		Coordenadas coordenadasMex = new Coordenadas(19.427050,-99.127571);
		Pais Mexico = new Pais(nombreMEX,edificiosMex,coordenadasMex);
		
		Edificio Banco = new Edificio("Banco");
		Banco.setPista("Nunca vi a esa persona");
		Edificio Muelle = new Edificio("Muelle");
		Muelle.setPista("Por acá pasa mucha gente");
		Edificio[] edificiosItalia = {Banco,Muelle};
		String nombreITA = "Milan";
		Coordenadas coordenadasItalia = new Coordenadas(41.871941,12.567380);
		Pais Italia = new Pais(nombreITA,edificiosItalia,coordenadasItalia);
		
		Edificio MuseoAUS = new Edificio("Museo");
		MuseoAUS.setPista("No conosco nadie así");
		Edificio HotelAUS = new Edificio("Hotel");
		HotelAUS.setPista("Por aca nada, paisanito");
		Edificio[] edificiosAUS = {MuseoAUS,HotelAUS};
		String nombreAUS = "Sidney";
		Coordenadas coordenadasSidney = new Coordenadas(-33.867138,151.207108);
		Pais Australia = new Pais(nombreAUS,edificiosAUS,coordenadasSidney);
		
		Coordenadas coordenadasNY = new Coordenadas(40.714268,-74.005974);
		Pais USA = new Pais("USA",edificiosAUS,coordenadasNY);
		
		Edificio BolsaARG = new Edificio("Bolsa");
		BolsaARG.setPista("Le dicen que tenía el cabello castaño");
		Edificio BancoARG = new Edificio("Banco");
		BancoARG.setPista("Cambio dinero a libras esterlinas");
		Edificio AeropuertoARG = new Edificio("Aeropuerto");
		AeropuertoARG.setPista("El avión tenía colores rojo, blanco y azul, llevaba raqueta de tenis");
		Edificio[] edificiosArgentina = {BolsaARG,BancoARG,AeropuertoARG};
		String nombreArg = "Buenos Aires";
		Coordenadas coordenadasBuenosAires = new Coordenadas(-34.608418,-58.373161);
		Pais Argentina = new Pais(nombreArg,edificiosArgentina,coordenadasBuenosAires);
		
		Edificio HotelLND = new Edificio("Hotel");
		HotelLND.setPista("estaba mejorando su inglés americano");
		Edificio BancoLDN = new Edificio("Banco");
		BancoLDN.setPista("estaba mejorando su inglés americano");
		Edificio MuelleLND = new Edificio("Muelle");
		MuelleLND.setPista("Fue en un crucero que tenía una bandera con estrellas");
		Edificio[] edificiosLDN = {HotelLND,BancoLDN,MuelleLND};
		String nombreIng = "London";
		Coordenadas coordenadasLondres = new Coordenadas(51.500153,-0.126236);
		Pais Inglaterra = new Pais(nombreIng,edificiosLDN,coordenadasLondres);

		Caracteristicas CaracteristicasDelLadron = new Caracteristicas(Sexo.MASCULINO,Hobby.MUSICA,Cabello.RUBIO,Senia.TATUAJE,Vehiculo.MOTO);
		
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
		
		ArrayList<Ladron> listLadrones = new ArrayList<Ladron>();
		ArrayList<Pais> listPaises = new ArrayList<Pais>();
		
		listPaises.add(Argentina);
		listPaises.add(Inglaterra);
		listPaises.add(Mexico);
		listPaises.add(Australia);
		listPaises.add(Italia);
		listPaises.add(USA);
		
		listLadrones.add(unLadron);
		listLadrones.add(unLadronSimilCaracteristicas);
		listLadrones.add(unLadronNadaQueVer);
		
		unaBase = new BaseDeDatos(listLadrones,listPaises);
		
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


