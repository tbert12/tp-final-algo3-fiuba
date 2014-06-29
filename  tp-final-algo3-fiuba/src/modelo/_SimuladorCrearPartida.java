package modelo;

import java.util.ArrayList;

import modelo.caracteristicas.Cabello;
import modelo.caracteristicas.Caracteristicas;
import modelo.caracteristicas.Hobby;
import modelo.caracteristicas.Senia;
import modelo.caracteristicas.Sexo;
import modelo.caracteristicas.Vehiculo;

public class _SimuladorCrearPartida {

	public Partida crearPartida() {
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
		
		//Creo la Base de datos
		BaseDeDatos basedeDatos = new BaseDeDatos();
		//Le seteto los datos a la base
		//Creo Ladrones
		Caracteristicas caracteristicasNick = new Caracteristicas(Sexo.MASCULINO,Hobby.ALPINISMO,Cabello.NEGRO,Senia.ANILLO,Vehiculo.MOTO);
		Ladron ladronNick = new Ladron("Nick Brunch",caracteristicasNick);
		basedeDatos.addSospechoso(ladronNick);
		Caracteristicas caracteristicasLen = new Caracteristicas(Sexo.MASCULINO,Hobby.ALPINISMO,Cabello.ROJO,Senia.TATUAJE,Vehiculo.DESCAPOTABLE);
		Ladron ladronLen = new Ladron("Len Bulk",caracteristicasLen);
		basedeDatos.addSospechoso(ladronLen);
		Caracteristicas caracteristicasThor = new Caracteristicas(Sexo.MASCULINO,Hobby.CROQUET,Cabello.RUBIO,Senia.TATUAJE,Vehiculo.LIMUSINA);
		Ladron ladronThor = new Ladron("Ihor Ihorovitch",caracteristicasThor);
		basedeDatos.addSospechoso(ladronThor);
		Caracteristicas caracteristicasFast = new Caracteristicas(Sexo.MASCULINO,Hobby.CROQUET,Cabello.NEGRO,Senia.JOYAS,Vehiculo.DESCAPOTABLE);
		Ladron ladronFast = new Ladron("Fast Eddie B.",caracteristicasFast);
		basedeDatos.addSospechoso(ladronFast);
		Caracteristicas caracteristicasScar = new Caracteristicas(Sexo.MASCULINO,Hobby.CROQUET,Cabello.ROJO,Senia.ANILLO,Vehiculo.LIMUSINA);
		Ladron ladronScar = new Ladron("Scar Graynolt",caracteristicasScar);
		basedeDatos.addSospechoso(ladronScar);
		Caracteristicas caracteristicasMerey = new Caracteristicas(Sexo.FEMENINO,Hobby.ALPINISMO,Cabello.CASTANIO,Senia.JOYAS,Vehiculo.LIMUSINA);
		Ladron ladronMerey = new Ladron("Merey Laroc",caracteristicasMerey);
		basedeDatos.addSospechoso(ladronMerey);
		Caracteristicas caracteristicasLady = new Caracteristicas(Sexo.FEMENINO,Hobby.TENNIS,Cabello.ROJO,Senia.ANILLO,Vehiculo.DESCAPOTABLE);
		Ladron ladronLady = new Ladron("Lady Agatha",caracteristicasLady);
		basedeDatos.addSospechoso(ladronLady);
		Caracteristicas caracteristicasKatherine = new Caracteristicas(Sexo.FEMENINO,Hobby.ALPINISMO,Cabello.CASTANIO,Senia.TATUAJE,Vehiculo.MOTO);
		Ladron ladronKatherine = new Ladron("Katherine Drib",caracteristicasKatherine);
		basedeDatos.addSospechoso(ladronKatherine);
		Caracteristicas caracteristicasDazzle = new Caracteristicas(Sexo.FEMENINO,Hobby.TENNIS,Cabello.RUBIO,Senia.TATUAJE,Vehiculo.LIMUSINA);
		Ladron ladronDazzle = new Ladron("Dazzle Annie",caracteristicasDazzle);
		basedeDatos.addSospechoso(ladronDazzle);
		Caracteristicas caracteristicasCarmen = new Caracteristicas(Sexo.FEMENINO,Hobby.TENNIS,Cabello.CASTANIO,Senia.JOYAS,Vehiculo.DESCAPOTABLE);
		Ladron ladronCarmen = new Ladron("Carmen Sandiego",caracteristicasCarmen);
		basedeDatos.addSospechoso(ladronCarmen);
		
		
		
		//Creo Paises
		Coordenadas CoordArgelia = new Coordenadas(28.033886,1.659626);
		Edificio MuseoArgelia = new Edificio("Biblioteca","No eh visto nada");
		Edificio PuertoArgelia = new Edificio("Puerto","No vi nada, Anda mucha gente por estos lugares");
		Edificio CasaDeGobiernoArgelia = new Edificio("Casa de Gobierno","Pues aqui no hay nada");
		Edificio[] EdificiosArgelia = {MuseoArgelia,PuertoArgelia,CasaDeGobiernoArgelia}; 
		Pais Argelia = new Pais("Argel",EdificiosArgelia,CoordArgelia);
		Argelia.setInformacion("Informacion sobre Argelia");
		basedeDatos.addPais(Argelia);
		
		Coordenadas CoordArgentina = new Coordenadas(-34.608418,-58.373161);
		Edificio AeropuertoArgentina = new Edificio("Aeropuerto","Andaba en un coche que tenia una bandera con una hoja");
		Edificio BancoArgentina = new Edificio("Banco","Dijo que viajaba hacia el norte, hacia donde hace frio");
		Edificio BibliotecaArgentina = new Edificio("Biblioteca","Tenia lindas joyas la dama");
		Edificio[] EdificiosArgentina = {AeropuertoArgentina,BancoArgentina,BibliotecaArgentina}; 
		Pais Argentina = new Pais("Buenos Aires",EdificiosArgentina,CoordArgentina);
		Argentina.setInformacion("Argentina, un pais con buena gente");
		basedeDatos.addPais(Argentina);
		
		Coordenadas CoordAlemania = new Coordenadas(51.165691,10.451526);
		Edificio MuseoAlemania = new Edificio("Museo","No he visto nada por estos lados");
		Edificio AeropuertoAlemania = new Edificio("Aeropuerto","Veo mucha gente todos los dias es probable que no la haya visto");
		Edificio BolsaAlemania = new Edificio("Bolsa","Si la he visto no me he dado cuenta");
		Edificio[] EdificiosAlemania = {MuseoAlemania,AeropuertoAlemania,BolsaAlemania}; 
		Pais Alemania = new Pais("Berlin",EdificiosAlemania,CoordAlemania);
		Alemania.setInformacion("Informacion sobre Alemania");
		basedeDatos.addPais(Alemania);
		
		Coordenadas CoordUsa = new Coordenadas(40.714268,-74.005974);
		Edificio CasadeGobiernoUsa = new Edificio("Casa de Gobierno","No he visto nada sospechoso");
		Edificio BibliotecaUsa = new Edificio("Biblioteca","Aqui no vas a encontrar lo que buscas");
		Edificio BancoUsa = new Edificio("Bolsa","Disculpe, No tengo informacion para ofrecerle");
		Edificio[] EdificiosUsa = {CasadeGobiernoUsa,BibliotecaUsa,BancoUsa}; 
		Pais Usa = new Pais("New York",EdificiosUsa,CoordUsa);
		Usa.setInformacion("Informacion de Estados Unidos");
		basedeDatos.addPais(Usa);
		
		Coordenadas CoordParaguay = new Coordenadas(-23.442503,-58.443832);
		Edificio MuseoParaguay = new Edificio("Museo","No he visto nada");
		Edificio AeropuertoParaguay = new Edificio("Aeropuerto","Si la he visto no me he dado cuenta");
		Edificio BolsaParaguay = new Edificio("Bolsa","Veo pasar mucha gente para ver algo");
		Edificio[] EdificiosParaguay = {MuseoParaguay,AeropuertoParaguay,BolsaParaguay}; 
		Pais Paraguay = new Pais("Concepcion",EdificiosParaguay,CoordParaguay);
		Paraguay.setInformacion("Informacion de Paraguay");
		basedeDatos.addPais(Paraguay);
		
		Coordenadas CoordCanada = new Coordenadas(45.423492,-75.697929);
		Edificio PuertoCanada = new Edificio("Puerto","Dijo que se dirijiria a un pais comunista");
		Edificio BibliotecaCanada = new Edificio("Biblioteca","Jugamos un partido de tennis y luego menciono algo de un pais con muchos habitantes");
		Edificio MuseoCanada = new Edificio("Museo","La bandera del avion que tomo es de color rojo");
		Edificio[] EdificiosCanada = {PuertoCanada,BibliotecaCanada,MuseoCanada}; 
		Pais Canada = new Pais("Ottawa",EdificiosCanada,CoordCanada);
		Canada.setInformacion("Informacion de Canada");
		basedeDatos.addPais(Canada);
		
		Coordenadas CoordChina = new Coordenadas(31.230707,121.472916);
		Edificio PuertoChina = new Edificio("Puerto","Me pregunto en que pais podia conocer a los kanguros");
		Edificio CasadeGobiernoChina = new Edificio("Casa de Gobierno","Se movia en un auto descapotable");
		Edificio BolsaChina = new Edificio("Bolsa","Creo que tomo un vuelo hacia oceania");
		Edificio[] EdificiosChina = {PuertoChina,CasadeGobiernoChina,BolsaChina}; 
		Pais China = new Pais("Shanghai",EdificiosChina,CoordChina);
		China.setInformacion("Informacion de China");
		basedeDatos.addPais(China);
		
		Coordenadas CoordEspania = new Coordenadas(40.416691,-3.700345);
		Edificio MuseoEspania = new Edificio("Museo","No eh visto nada estos dias");
		Edificio BancoEspania = new Edificio("Banco","Disculpe, no tengo informacion para ofrecerle");
		Edificio CasadeGobiernoEspania = new Edificio("Casa de Gobierno","Si el ladron ha estado aqui no lo he visto");
		Edificio[] EdificiosEspania = {MuseoEspania,BancoEspania,CasadeGobiernoEspania}; 
		Pais Espania = new Pais("Madrid",EdificiosEspania,CoordEspania);
		Espania.setInformacion("Informacion de España");
		basedeDatos.addPais(Espania);
		
		Coordenadas CoordAustralia = new Coordenadas(-33.867138,151.207108);
		Edificio PuertoAustralia = new Edificio("Puerto","Veo algo sospechoso por aqui");
		Edificio BibliotecaAustralia = new Edificio("Biblioteca","");
		BibliotecaAustralia.setLadron();
		Edificio BolsaAustralia = new Edificio("Bolsa","Estos dias han pasado cosas extrañas");
		Edificio[] EdificiosAustralia = {PuertoAustralia,BibliotecaAustralia,BolsaAustralia}; 
		Pais Australia = new Pais("Sydney",EdificiosAustralia,CoordAustralia);
		Australia.setInformacion("Informacion de Australia");
		basedeDatos.addPais(Australia);
		
		Coordenadas CoordFrancia = new Coordenadas(41.895466,12.482324);
		Edificio MuseoFrancia = new Edificio("Museo","Creo que aqui no ha pasado nada");
		Edificio BancoFrancia = new Edificio("Banco","Si veo algo estare en contacto contigo");
		Edificio AeropuertoFrancia = new Edificio("Aeropuerto","Mucha gente pasa por aqui, es dificil que vea al ladron");
		Edificio[] EdificiosFrancia = {MuseoFrancia,BancoFrancia,AeropuertoFrancia}; 
		Pais Francia = new Pais("Roma",EdificiosFrancia,CoordFrancia);
		Francia.setInformacion("Informacion de Francia");
		basedeDatos.addPais(Francia);
		
		//Asigno La trayectoria del Ladron
		ArrayList<Pais> PaisesDeLaTrayectoria = new ArrayList<Pais>();
		PaisesDeLaTrayectoria.add(Argentina);
		PaisesDeLaTrayectoria.add(Canada);
		PaisesDeLaTrayectoria.add(China);
		PaisesDeLaTrayectoria.add(Australia);
		Trayectoria trayectoriaDelLadron = new Trayectoria(PaisesDeLaTrayectoria);
		
		ladronCarmen.addTrayectoria(trayectoriaDelLadron);
		
		//Creo Policia
		Policia unPolicia = new Policia("PoliciaTest",0);
		//Creo Objeto
		ObjetoRobado unObjetoRobado = new ObjetoRobado("Reservas del Banco","Valioso");
		
		
		Partida unaPartida = new Partida(unPolicia,ladronCarmen,basedeDatos,unObjetoRobado);
		
		return unaPartida;
	}

}
