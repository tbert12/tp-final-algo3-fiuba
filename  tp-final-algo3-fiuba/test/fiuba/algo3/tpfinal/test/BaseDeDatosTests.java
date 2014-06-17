package fiuba.algo3.tpfinal.test;

import java.util.ArrayList;

import modelo.BaseDeDatos;
import modelo.Caracteristicas;
import modelo.Ladron;
import modelo.Pais;
import modelo.Edificio;
import modelo.Trayectoria;
import modelo.excepcion.ErrorNoHayPais;

import org.junit.Assert;
import org.junit.Test;

public class BaseDeDatosTests {
	
	private String[] Sexo = {"Masculino","Femenino"};
	private String[] Hobby = {"Tennis","Musica","Alpinismo","Paracaidismo","Natacion","Croquet"};
	private String[] Cabello = {"Castanio","Rubio","Rojo","Negro"};
	private String[] Senia = {"Cojera","Anillo","Tatuaje","Cicatriz","Joyas"};
	private String[] Vehiculo = {"Descapotable","Limusina","Deportivo","Moto"};
	
	Ladron LadronUno = CrearLadron("Sofia",Sexo[1],Hobby[1],Cabello[0],Senia[2],Vehiculo[0]);
	Ladron LadronDos = CrearLadron("Marcos",Sexo[0],Hobby[0],Cabello[1],Senia[0],Vehiculo[3]);
	Ladron LadronTres = CrearLadron("Lucia",Sexo[1],Hobby[4],Cabello[3],Senia[1],Vehiculo[3]);
	Ladron LadronCuatro = CrearLadron("Tomas",Sexo[0],Hobby[1],Cabello[2],Senia[1],Vehiculo[2]);
	
	Pais Argentina = crearPais("Argentina");
	Pais Cuba = crearPais("Cuba");
	Pais Argelia = crearPais("Argelia");
	Pais Alemania = crearPais("Alemania");
	
	
	public Ladron CrearLadron(String Nombre,String Sexo,String Hobby,String Cabello,String Senia,String Vehiculo){
		Caracteristicas caracteristicas = new Caracteristicas(Sexo,Hobby,Cabello,Senia,Vehiculo);
		Ladron LadronNuevo = new Ladron(Nombre,caracteristicas);
		return LadronNuevo;
	}
	
	public Pais crearPais(String Nombre){
		Edificio[] Edificios = {new Edificio("Lugarde"+Nombre,"Pistade"+Nombre)};
		Pais unPais = new Pais(Nombre,Edificios);
		return unPais;
	}
	
	public BaseDeDatos CrearBase(){
		BaseDeDatos unaBase = new BaseDeDatos();
		unaBase.addSospechoso(LadronUno);
		unaBase.addSospechoso(LadronDos);
		unaBase.addSospechoso(LadronTres);
		unaBase.addSospechoso(LadronCuatro);
		unaBase.addPais(Argentina);
		unaBase.addPais(Cuba);
		unaBase.addPais(Argelia);
		unaBase.addPais(Alemania);
		return unaBase;
	}
	
	@Test
	public void PruebaLadronSoloFiltraLosMasculinos(){
		BaseDeDatos Base = CrearBase();
		
		Caracteristicas CaracteristicasPedidias = new Caracteristicas(Sexo[0],null,null,null,null);
		
		ArrayList<Ladron> Sospechosos = Base.FiltarPorCaracteristicas(CaracteristicasPedidias);
		
		ArrayList<Ladron> SospechososEsperados = new ArrayList<Ladron>();
		//Agrego los ladrones que son Masculinos
		SospechososEsperados.add(LadronDos);
		SospechososEsperados.add(LadronCuatro);
		
		Assert.assertEquals(Sospechosos, SospechososEsperados);
	}
	
	@Test
	public void PruebaSoloFiltraLosLadronesQueTienenComoVehiculoMoto(){
		BaseDeDatos Base = CrearBase();
		Caracteristicas CaracteristicasPedidias = new Caracteristicas(null,null,null,null,Vehiculo[3]);
		
		ArrayList<Ladron> Sospechosos = Base.FiltarPorCaracteristicas(CaracteristicasPedidias);
		
		ArrayList<Ladron> SospechososEsperados = new ArrayList<Ladron>();
		//Agrego los ladrones que su vehiculo es una Moto
		SospechososEsperados.add(LadronDos);
		SospechososEsperados.add(LadronTres);
		
		Assert.assertEquals(Sospechosos, SospechososEsperados);
	}
	
	@Test
	public void PruebaSeEncuentraSoloUnLadronPorFiltrarMasDeUnaCaracteristica(){
		BaseDeDatos Base = CrearBase();
		Caracteristicas CaracteristicasPedidias = new Caracteristicas(null,null,Cabello[3],null,Vehiculo[3]);
		
		ArrayList<Ladron> Sospechosos = Base.FiltarPorCaracteristicas(CaracteristicasPedidias);
		
		ArrayList<Ladron> SospechososEsperados = new ArrayList<Ladron>();
		//Agrego el Ladron es coincidente
		SospechososEsperados.add(LadronTres);
		
		Assert.assertEquals(Sospechosos, SospechososEsperados);
	}
	
	@Test
	public void PruebaLasCaracteristicasNoCoincidenConNingunLadron(){
		BaseDeDatos Base = CrearBase();
		Caracteristicas CaracteristicasPedidias = new Caracteristicas(null,null,Cabello[3],Senia[0],Vehiculo[3]);
		
		ArrayList<Ladron> Sospechosos = Base.FiltarPorCaracteristicas(CaracteristicasPedidias);
		
		ArrayList<Ladron> SospechososEsperados = new ArrayList<Ladron>();
		//No Agrego ningun ladron
		Assert.assertEquals(Sospechosos, SospechososEsperados);
	}
	
	@Test
	public void PruebaAgregoPais(){
		BaseDeDatos Base = CrearBase();
		
		ArrayList<Pais> PaisesdeBase = Base.ObtenerPaises();
		
		ArrayList<Pais> PaisesEsperados = new ArrayList<Pais>();
		PaisesEsperados.add(Argentina);
		PaisesEsperados.add(Cuba);
		PaisesEsperados.add(Argelia);
		PaisesEsperados.add(Alemania);
		
		Assert.assertEquals(PaisesdeBase,PaisesEsperados);		
	}
	
	@Test
	public void PruebaPidoDestinos() throws ErrorNoHayPais{
		Pais[] PaisesDelTrayecto = {Argentina,Cuba,Argelia,Alemania};
		Trayectoria trayecto = new Trayectoria(PaisesDelTrayecto);
		LadronUno.addTrayectoria(trayecto);
		BaseDeDatos Base = CrearBase();
		ArrayList<Pais> PaisesDestino = Base.PosiblesPaisesAViajar(LadronUno, Argentina);
		
		Assert.assertTrue( PaisesDestino.contains(Cuba) );
		Assert.assertFalse( PaisesDestino.contains(Argelia) );
		Assert.assertFalse( PaisesDestino.contains(Alemania) );
		
	}
}
