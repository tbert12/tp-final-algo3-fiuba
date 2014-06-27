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
		 -Alemania   Alemania      (51.165691,10.451526)     Museo,Aeropuerto,Bolsa
		 -Usa        New York      (40.714268,-74.005974)    Casa de Gobierno,Biblioteca,Banco
		 -Paraguay   Concepcion    (-23.442503,-58.443832)   Museo,Aeropuerto,Bolsa
		 -Canada     Ottawa        (45.423492,-75.697929)    Puerto,Biblioteca,Museo
		 -China      Shanghai      (31.230707,121.472916)    Puerto,Casa de Gobierno,Bolsa
		 -Espania    Madrid        (40.416691,-3.700345)     Museo,Banco,Casa de Gobierno
		 -Australia  Sydney        (-33.867138,151.207108)   Puerto,Biblioteca,Bolsa
		 -Francia    Roma          (41.895466,12.482324)     Museo,Banco,Aeropuerto
		Ladrones 
		 -Nick Brunch (Masculino,Alpinismo,Negro,Anillo,Moto)
		 -Len Bulk (Masculino,Alpinismo,Rojo,Tatuaje,Descapotable)
		 -Ihor Ihorovitch (Masculino,Croquet,Rubio,Tatuaje,Limusina)
		 -Fast Eddie B. (Masculino,Croquet,Negro,Joyas,Convertible)
		 -Scar Graynolt (Masculino,Croquet,Rojo,Anillo,Limusina)
		 -Merey Laroc (Femenino,Alpinismo,Marron,Joyas,Limusina)
		 -Lady Agatha (Femenino,Tennis,Rojo,Anillo,Convertible)
		 -Katherine Drib (Femenino,Alpinismo,Marron,Tatuaje,Moto)
		 -Dazzle Annie (Femenino,Tennis,Rubio,Tatuaje,Limusina)
		 -Carmen Sandiego (Femenino,Tennis,Marron,Joyas,Convertible)
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
		Edificio MuseoArgelia = new Edificio("Biblioteca","No vi nada");
		Edificio PuertoArgelia = new Edificio("Puerto","No vi nada, Mucha gente");
		Edificio CasaDeGobiernoArgelia = new Edificio("Casa de Gobierno","Pues aqui no hay nada");
		Edificio[] EdificiosArgelia = {MuseoArgelia,PuertoArgelia,CasaDeGobiernoArgelia}; 
		Pais Argelia = new Pais("Argel",EdificiosArgelia,CoordArgelia);
		basedeDatos.addPais(Argelia);
		
		Coordenadas CoordArgentina = new Coordenadas(-34.608418,-58.373161);
		Edificio AeropuertoArgentina = new Edificio("Aeropuerto","mmm...Creo que se fue a Canada");
		Edificio BancoArgentina = new Edificio("Banco","El nombre de pais a donde se dirigia empieza con Ca y termina con Nada");
		Edificio BibliotecaArgentina = new Edificio("Biblioteca","Las pista estan en otro lado");
		Edificio[] EdificiosArgentina = {AeropuertoArgentina,BancoArgentina,BibliotecaArgentina}; 
		Pais Argentina = new Pais("Buenos Aires",EdificiosArgentina,CoordArgentina);
		basedeDatos.addPais(Argentina);
		
		Coordenadas CoordAlemania = new Coordenadas(51.165691,10.451526);
		Edificio MuseoAlemania = new Edificio("Museo","Ohhh Birrreeen bireen");
		Edificio AeropuertoAlemania = new Edificio("Aeropuerto","Lobittteeen y Caperusitenn");
		Edificio BolsaAlemania = new Edificio("Bolsa","Iaaaa noo estaba acaaa");
		Edificio[] EdificiosAlemania = {MuseoAlemania,AeropuertoAlemania,BolsaAlemania}; 
		Pais Alemania = new Pais("Alemania",EdificiosAlemania,CoordAlemania);
		basedeDatos.addPais(Alemania);
		
		Coordenadas CoordUsa = new Coordenadas(40.714268,-74.005974);
		Edificio CasadeGobiernoUsa = new Edificio("Casa de Gobierno","Nooou nothing");
		Edificio BibliotecaUsa = new Edificio("Biblioteca","Calafa fa fa faa");
		Edificio BancoUsa = new Edificio("Bolsa","pa pa pa pa paaaaChiuuuu Peniaaa");
		Edificio[] EdificiosUsa = {CasadeGobiernoUsa,BibliotecaUsa,BancoUsa}; 
		Pais Usa = new Pais("New York",EdificiosUsa,CoordUsa);
		basedeDatos.addPais(Usa);
		
		Coordenadas CoordParaguay = new Coordenadas(-23.442503,-58.443832);
		Edificio MuseoParaguay = new Edificio("Museo","Nada");
		Edificio AeropuertoParaguay = new Edificio("Aeropuerto","No vi nada");
		Edificio BolsaParaguay = new Edificio("Bolsa","Muchisima gente para ver algo");
		Edificio[] EdificiosParaguay = {MuseoParaguay,AeropuertoParaguay,BolsaParaguay}; 
		Pais Paraguay = new Pais("Concepcion",EdificiosParaguay,CoordParaguay);
		basedeDatos.addPais(Paraguay);
		
		Coordenadas CoordCanada = new Coordenadas(45.423492,-75.697929);
		Edificio PuertoCanada = new Edificio("Puerto","Cleo que se fue a Chinaaa uoocho");
		Edificio BibliotecaCanada = new Edificio("Biblioteca","Se achino los ojos y se las tomo");
		Edificio MuseoCanada = new Edificio("Museo","Me jugo al tennis la de pelo castanio");
		Edificio[] EdificiosCanada = {PuertoCanada,BibliotecaCanada,MuseoCanada}; 
		Pais Canada = new Pais("Ottawa",EdificiosCanada,CoordCanada);
		basedeDatos.addPais(Canada);
		
		Coordenadas CoordChina = new Coordenadas(31.230707,121.472916);
		Edificio PuertoChina = new Edificio("Puerto","Se fue en canguro");
		Edificio CasadeGobiernoChina = new Edificio("Casa de Gobierno","Alto Descapotable tenia");
		Edificio BolsaChina = new Edificio("Bolsa","Lena de Jolas la tlia, se flue a Austlalia");
		Edificio[] EdificiosChina = {PuertoChina,CasadeGobiernoChina,BolsaChina}; 
		Pais China = new Pais("Shanghai",EdificiosChina,CoordChina);
		basedeDatos.addPais(China);
		
		Coordenadas CoordEspania = new Coordenadas(40.416691,-3.700345);
		Edificio MuseoEspania = new Edificio("Museo","Puesh nada conio");
		Edificio BancoEspania = new Edificio("Banco","No eh viyto a nadie");
		Edificio CasadeGobiernoEspania = new Edificio("Casa de Gobierno","Meyi meyii meyii gooool, no vi nada");
		Edificio[] EdificiosEspania = {MuseoEspania,BancoEspania,CasadeGobiernoEspania}; 
		Pais Espania = new Pais("Madrid",EdificiosEspania,CoordEspania);
		basedeDatos.addPais(Espania);
		
		Coordenadas CoordAustralia = new Coordenadas(-33.867138,151.207108);
		Edificio PuertoAustralia = new Edificio("Puerto,","Hay Algo raro");
		Edificio BibliotecaAustralia = new Edificio("Biblioteca","Ofaaaa");
		BibliotecaAustralia.setLadron();
		Edificio BolsaAustralia = new Edificio("Bolsa","En este edifico no esta, pero esta en el Pais");
		Edificio[] EdificiosAustralia = {PuertoAustralia,BibliotecaAustralia,BolsaAustralia}; 
		Pais Australia = new Pais("Sydney",EdificiosAustralia,CoordAustralia);
		basedeDatos.addPais(Australia);
		
		Coordenadas CoordFrancia = new Coordenadas(41.895466,12.482324);
		Edificio MuseoFrancia = new Edificio("Museo","No hay Nada aqui");
		Edificio BancoFrancia = new Edificio("Banco","No tenemos nada");
		Edificio AeropuertoFrancia = new Edificio("Aeropuerto","No eh visto nada, ni me banie");
		Edificio[] EdificiosFrancia = {MuseoFrancia,BancoFrancia,AeropuertoFrancia}; 
		Pais Francia = new Pais("Roma",EdificiosFrancia,CoordFrancia);
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
		Policia unPolicia = new Policia("Javier",0);
		Tiempo unTiempo = new Tiempo(147);
		unPolicia.setTiempo(unTiempo);
		//Creo Objeto
		ObjetoRobado unObjetoRobado = new ObjetoRobado("Reservas del Banco","Valioso");
		
		
		Partida unaPartida = new Partida(unPolicia,ladronCarmen,basedeDatos,unObjetoRobado);
		
		return unaPartida;
	}

}
