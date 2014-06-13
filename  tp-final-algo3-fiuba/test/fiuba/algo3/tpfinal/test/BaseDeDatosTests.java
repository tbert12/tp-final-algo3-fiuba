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
	
	public Ladron CrearLadron(String Nombre,String Sexo,String Hobby,String Cabello,String Senia,String Vehiculo){
		Caracteristicas caracteristicas = new Caracteristicas(Sexo,Hobby,Cabello,Senia,Vehiculo);
		Ladron LadronNuevo = new Ladron(Nombre,caracteristicas);
		return LadronNuevo;
	}
	
	@Test
	public void PruebaLadronSoloFiltraLosMasculinos(){
		Ladron LadronUno = CrearLadron("Sofia",Sexo[1],Hobby[1],Cabello[0],Senia[2],Vehiculo[0]);
		Ladron LadronDos = CrearLadron("Marcos",Sexo[0],Hobby[0],Cabello[1],Senia[0],Vehiculo[3]);
		Ladron LadronTres = CrearLadron("Lucia",Sexo[1],Hobby[4],Cabello[3],Senia[1],Vehiculo[3]);
		Ladron LadronCuatro = CrearLadron("Tomas",Sexo[0],Hobby[1],Cabello[2],Senia[1],Vehiculo[2]);
		
		BaseDeDatos Cuartel = new BaseDeDatos();
		Cuartel.addSospechoso(LadronUno);
		Cuartel.addSospechoso(LadronDos);
		Cuartel.addSospechoso(LadronTres);
		Cuartel.addSospechoso(LadronCuatro);
		
		Caracteristicas CaracteristicasPedidias = new Caracteristicas(Sexo[0],null,null,null,null);
		
		ArrayList<Ladron> Sospechosos = Cuartel.FiltarPorCaracteristicas(CaracteristicasPedidias);
		
		ArrayList<Ladron> SospechososEsperados = new ArrayList<Ladron>();
		SospechososEsperados.add(LadronDos);
		SospechososEsperados.add(LadronCuatro);
		
		Assert.assertEquals(Sospechosos, SospechososEsperados);
		
		
	}

}
