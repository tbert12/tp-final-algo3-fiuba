package fiuba.algo3.tpfinal.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;

import modelo.Coordenadas;
import modelo.Edificio;
import modelo.Pais;

import org.junit.Test;

public class PaisTest {

	private Edificio biblioteca = new Edificio("biblioteca","biblioteca pista");
	private Edificio puerto = new Edificio("puerto","puerto pista");
	private Edificio fiuba = new Edificio("fiuba", "fiuba pista");
	
	private Edificio[] edificios = {biblioteca,puerto,fiuba};
	private String nombre = "Argentina";
	
	private Coordenadas coordenadasDelPais = new Coordenadas(2,2);
	private Pais UnPais = new Pais(nombre,edificios,coordenadasDelPais);
	
	@Test
	public void testElNombreDelPaisEsCorrecto() {
		
		assertEquals( UnPais.getNombre(), nombre );
	}	
	
	@Test
	public void testLosNombresDeLosEdificiosSonCorrectos(){
		
		ArrayList<Edificio> ListaEdificios = UnPais.getEdificios();
		
		Iterator<Edificio> Iterador = ListaEdificios.iterator();
		int i = 0;
		while( Iterador.hasNext() ){
			Edificio NombreActual = Iterador.next();
			assertEquals( NombreActual, edificios[i]);
			i++;
		}
	}
	
	@Test
	public void testLasDistanciasSonCorrectas(){
		Coordenadas otrascoordenadasDelPais = new Coordenadas(0,0);
		Pais otroPais = new Pais(nombre,edificios,otrascoordenadasDelPais);
		int distanciaEsperada = (int) (Math.sqrt(8)*111);
		assertEquals(UnPais.distanciaAPais(otroPais),distanciaEsperada,0);
	}

	@Test
	public void testInformacionDelPaisEsCorrecta(){
		String Info = "Este pais blablabla, blablabla y aun mas blablabla";
		UnPais.setInformacion(Info);
		
		assertEquals(UnPais.getInformacion(),Info);
		assertNotEquals(UnPais.getInformacion(),Info + "Algo Mas");
	}
}



