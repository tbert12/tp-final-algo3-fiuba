package fiuba.algo3.tpfinal.test;

import java.util.ArrayList;

import modelo.BaseDeDatos;
import modelo.Caracteristicas;
import modelo.Ladron;

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
	
	public Ladron CrearLadron(String Nombre,String Sexo,String Hobby,String Cabello,String Senia,String Vehiculo){
		Caracteristicas caracteristicas = new Caracteristicas(Sexo,Hobby,Cabello,Senia,Vehiculo);
		Ladron LadronNuevo = new Ladron(Nombre,caracteristicas);
		return LadronNuevo;
	}
	
	public BaseDeDatos CrearCuartel(){
		BaseDeDatos Cuartel = new BaseDeDatos();
		Cuartel.addSospechoso(LadronUno);
		Cuartel.addSospechoso(LadronDos);
		Cuartel.addSospechoso(LadronTres);
		Cuartel.addSospechoso(LadronCuatro);
		return Cuartel;
	}
	
	@Test
	public void PruebaLadronSoloFiltraLosMasculinos(){
		BaseDeDatos Cuartel = CrearCuartel();
		
		Caracteristicas CaracteristicasPedidias = new Caracteristicas(Sexo[0],null,null,null,null);
		
		ArrayList<Ladron> Sospechosos = Cuartel.FiltarPorCaracteristicas(CaracteristicasPedidias);
		
		ArrayList<Ladron> SospechososEsperados = new ArrayList<Ladron>();
		//Agrego los ladrones que son Masculinos
		SospechososEsperados.add(LadronDos);
		SospechososEsperados.add(LadronCuatro);
		
		Assert.assertEquals(Sospechosos, SospechososEsperados);
	}
	
	@Test
	public void PruebaSoloFiltraLosLadronesQueTienenComoVehiculoMoto(){
		BaseDeDatos Cuartel = CrearCuartel();
		Caracteristicas CaracteristicasPedidias = new Caracteristicas(null,null,null,null,Vehiculo[3]);
		
		ArrayList<Ladron> Sospechosos = Cuartel.FiltarPorCaracteristicas(CaracteristicasPedidias);
		
		ArrayList<Ladron> SospechososEsperados = new ArrayList<Ladron>();
		//Agrego los ladrones que su vehiculo es una Moto
		SospechososEsperados.add(LadronDos);
		SospechososEsperados.add(LadronTres);
		
		Assert.assertEquals(Sospechosos, SospechososEsperados);
	}
	
	@Test
	public void PruebaSeEncuentraSoloUnLadronPorFiltrarMasDeUnaCaracteristica(){
		BaseDeDatos Cuartel = CrearCuartel();
		Caracteristicas CaracteristicasPedidias = new Caracteristicas(null,null,Cabello[3],null,Vehiculo[3]);
		
		ArrayList<Ladron> Sospechosos = Cuartel.FiltarPorCaracteristicas(CaracteristicasPedidias);
		
		ArrayList<Ladron> SospechososEsperados = new ArrayList<Ladron>();
		//Agrego el Ladron es coincidente
		SospechososEsperados.add(LadronTres);
		
		Assert.assertEquals(Sospechosos, SospechososEsperados);
	}
	
	@Test
	public void PruebaLasCaracteristicasNoCoincidenConNingunLadron(){
		BaseDeDatos Cuartel = CrearCuartel();
		Caracteristicas CaracteristicasPedidias = new Caracteristicas(null,null,Cabello[3],Senia[0],Vehiculo[3]);
		
		ArrayList<Ladron> Sospechosos = Cuartel.FiltarPorCaracteristicas(CaracteristicasPedidias);
		
		ArrayList<Ladron> SospechososEsperados = new ArrayList<Ladron>();
		//No Agrego ningun ladron
		Assert.assertEquals(Sospechosos, SospechososEsperados);
	}

}
