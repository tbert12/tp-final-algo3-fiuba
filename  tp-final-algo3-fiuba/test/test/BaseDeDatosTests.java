package test;

import java.util.ArrayList;

import modelo.BaseDeDatos;
import modelo.Ladron;
import modelo.Pais;
import modelo.Edificio;
import modelo.Trayectoria;
import modelo.caracteristicas.*;

import org.junit.Assert;
import org.junit.Test;

public class BaseDeDatosTests {

	Ladron LadronUno = CrearLadron("Sofia",Sexo.FEMENINO,Hobby.MUSICA,Cabello.CASTANIO,Senia.TATUAJE,Vehiculo.DESCAPOTABLE);
	Ladron LadronDos = CrearLadron("Marcos",Sexo.MASCULINO,Hobby.TENNIS,Cabello.RUBIO,Senia.COJERA,Vehiculo.MOTO);
	Ladron LadronTres = CrearLadron("Lucia",Sexo.FEMENINO,Hobby.NATACION,Cabello.NEGRO,Senia.ANILLO,Vehiculo.MOTO);
	Ladron LadronCuatro = CrearLadron("Tomas",Sexo.MASCULINO,Hobby.MUSICA,Cabello.ROJO,Senia.ANILLO,Vehiculo.DEPORTIVO);
	
	Pais Argentina = crearPais("Argentina");
	Pais Cuba = crearPais("Cuba");
	Pais Argelia = crearPais("Argelia");
	Pais Alemania = crearPais("Alemania");
	Pais Rusia = crearPais("Rusia");
	Pais Peru = crearPais("Peru");
	
	ArrayList<Ladron> listLadrones = new ArrayList<Ladron>();
	ArrayList<Pais> listPaises = new ArrayList<Pais>();
	
	
	public Ladron CrearLadron(String Nombre,Sexo Sexo,Hobby Hobby,Cabello Cabello,Senia Senia,Vehiculo Vehiculo){
		Caracteristicas caracteristicas = new Caracteristicas(Sexo,Hobby,Cabello,Senia,Vehiculo);
		Ladron LadronNuevo = new Ladron(Nombre,caracteristicas);
		return LadronNuevo;
	}
	
	public Pais crearPais(String Nombre){
		Edificio[] Edificios = {new Edificio("Lugarde"+Nombre)};
		Pais unPais = new Pais(Nombre,Edificios,null);
		return unPais;
	}
	
	public BaseDeDatos CrearBase(){
		
		listLadrones.add(LadronUno);
		listLadrones.add(LadronDos);
		listLadrones.add(LadronTres);
		listLadrones.add(LadronCuatro);
		listPaises.add(Argentina);
		listPaises.add(Cuba);
		listPaises.add(Argelia);
		listPaises.add(Alemania);
		listPaises.add(Rusia);
		listPaises.add(Peru);
		BaseDeDatos unaBase = new BaseDeDatos(listLadrones,listPaises);
		return unaBase;
	}
	
	@Test
	public void PruebaLadronSoloFiltraLosMasculinos(){
		BaseDeDatos Base = CrearBase();

		Caracteristicas CaracteristicasPedidias = new Caracteristicas(Sexo.MASCULINO,null,null,null,null);
		
		ArrayList<Ladron> Sospechosos = Base.filtarPorCaracteristicas(CaracteristicasPedidias);
		
		ArrayList<Ladron> SospechososEsperados = new ArrayList<Ladron>();
		//Agrego los ladrones que son Masculinos
		SospechososEsperados.add(LadronDos);
		SospechososEsperados.add(LadronCuatro);
		
		Assert.assertEquals(Sospechosos, SospechososEsperados);
	}
	
	@Test
	public void PruebaSoloFiltraLosLadronesQueTienenComoVehiculoMoto(){
		BaseDeDatos Base = CrearBase();
		Caracteristicas CaracteristicasPedidias = new Caracteristicas(null,null,null,null,Vehiculo.MOTO);
		ArrayList<Ladron> Sospechosos = Base.filtarPorCaracteristicas(CaracteristicasPedidias);
		
		ArrayList<Ladron> SospechososEsperados = new ArrayList<Ladron>();
		//Agrego los ladrones que su vehiculo es una Moto
		SospechososEsperados.add(LadronDos);
		SospechososEsperados.add(LadronTres);
		
		Assert.assertEquals(Sospechosos, SospechososEsperados);
	}
	
	@Test
	public void PruebaSeEncuentraSoloUnLadronPorFiltrarMasDeUnaCaracteristica(){
		BaseDeDatos Base = CrearBase();

		Caracteristicas CaracteristicasPedidias = new Caracteristicas(null,null,Cabello.NEGRO,null,Vehiculo.MOTO);
		
		ArrayList<Ladron> Sospechosos = Base.filtarPorCaracteristicas(CaracteristicasPedidias);
		
		ArrayList<Ladron> SospechososEsperados = new ArrayList<Ladron>();
		//Agrego el Ladron es coincidente
		SospechososEsperados.add(LadronTres);
		
		Assert.assertEquals(Sospechosos, SospechososEsperados);
	}
	
	@Test
	public void PruebaLasCaracteristicasNoCoincidenConNingunLadron(){
		BaseDeDatos Base = CrearBase();

		Caracteristicas CaracteristicasPedidias = new Caracteristicas(null,null,Cabello.NEGRO,Senia.COJERA,Vehiculo.MOTO);
		
		ArrayList<Ladron> Sospechosos = Base.filtarPorCaracteristicas(CaracteristicasPedidias);
		
		ArrayList<Ladron> SospechososEsperados = new ArrayList<Ladron>();
		//No Agrego ningun ladron
		Assert.assertEquals(Sospechosos, SospechososEsperados);
	}
	
	@Test
	public void PruebaPidoDestinos(){
		ArrayList<Pais> PaisesDelTrayecto = new ArrayList<Pais>();
		PaisesDelTrayecto.add(Argentina);
		PaisesDelTrayecto.add(Cuba);
		PaisesDelTrayecto.add(Argelia);
		PaisesDelTrayecto.add(Alemania);
		
		Trayectoria trayecto = new Trayectoria(PaisesDelTrayecto);
		LadronUno.addTrayectoria(trayecto);
		BaseDeDatos Base = CrearBase();
		ArrayList<Pais> PaisesDestino = Base.posiblesPaisesAViajar(LadronUno, Argentina);
		
		//Se espera un pais de la trayectoria y otros dos que no esten.
		Assert.assertTrue( PaisesDestino.contains(Cuba) );
		Assert.assertTrue( PaisesDestino.contains(Peru) );
		Assert.assertTrue( PaisesDestino.contains(Rusia) );
		
		Assert.assertFalse( PaisesDestino.contains(Argentina) );
		Assert.assertFalse( PaisesDestino.contains(Argelia) );
		Assert.assertFalse( PaisesDestino.contains(Alemania) );

	}
	
	@Test
	public void PruebaPidoDestinosCasoPaisEquivocadoVuelveAlAnteriorDondeHabiaEncontradoPistas(){
		ArrayList<Pais> PaisesDelTrayecto = new ArrayList<Pais>();
		PaisesDelTrayecto.add(Argentina);
		PaisesDelTrayecto.add(Cuba);
		PaisesDelTrayecto.add(Argelia);
		PaisesDelTrayecto.add(Alemania);
		
		Trayectoria trayecto = new Trayectoria(PaisesDelTrayecto);
		LadronUno.addTrayectoria(trayecto);
		BaseDeDatos Base = CrearBase();
		Pais Brasil = crearPais("Brasil");
		listPaises.add(Brasil);
		
		ArrayList<Pais> PaisesDestino = Base.posiblesPaisesAViajar(LadronUno, Argentina);
		Assert.assertTrue (PaisesDestino.contains(Cuba) );
		
		//Ahora El Ladron Esta en Cuba, pero se viajo a Brasil (Por Error)
		PaisesDestino = Base.posiblesPaisesAViajar(LadronUno, Brasil);
		
		Assert.assertTrue(PaisesDestino.size() == 3);
		
		Assert.assertFalse ( PaisesDestino.contains(Brasil) );
		Assert.assertFalse (PaisesDestino.contains(Cuba) );
		Assert.assertFalse( PaisesDestino.contains(Argelia) );
		Assert.assertFalse( PaisesDestino.contains(Alemania) );
		
		//Espero la posibilidad de Volver a Argentina pero no puedo ir a Cuba
		Assert.assertTrue( PaisesDestino.contains(Argentina) );
		Assert.assertTrue( PaisesDestino.contains(Peru) );
		Assert.assertTrue( PaisesDestino.contains(Rusia) );
		
		//Voy a Argentina, tendria que tener la posibilidad de ir a Cuba y 2 mas
		PaisesDestino = Base.posiblesPaisesAViajar(LadronUno, Argentina);
		Assert.assertFalse( PaisesDestino.contains(Argentina) );
		Assert.assertTrue(PaisesDestino.contains(Cuba) );
		
		
	}
}
