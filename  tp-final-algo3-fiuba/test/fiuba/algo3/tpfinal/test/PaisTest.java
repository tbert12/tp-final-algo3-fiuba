package fiuba.algo3.tpfinal.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import modelo.Edificio;
import modelo.Pais;

import org.junit.Test;

public class PaisTest {

	private Edificio biblioteca = new Edificio("biblioteca","biblioteca pista");
	private Edificio puerto = new Edificio("puerto","puerto pista");
	private Edificio fiuba = new Edificio("fiuba", "fiuba pista");
	
	private Edificio[] edificios = {biblioteca,puerto,fiuba};
	private String nombre = "Argentina";
	
	private Pais UnPais = new Pais(nombre,edificios);
	
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
		HashMap<String,Integer> Distancias = new HashMap<String,Integer>();
		Distancias.put("ARG", 1);
		Distancias.put("ING", 2);
		Distancias.put("USA", 3);
		UnPais.setDistancias(Distancias);
		
		assertEquals(UnPais.distanciaAPais("ARG"),1);
		assertEquals(UnPais.distanciaAPais("ING"),2);
		assertEquals(UnPais.distanciaAPais("USA"),3);
	}

	@Test
	public void testInformacionDelPaisEsCorrecta(){
		String Info = "Este pais blablabla, blablabla y aun mas blablabla";
		UnPais.setInformacion(Info);
		
		assertEquals(UnPais.getInformacion(),Info);
		assertNotEquals(UnPais.getInformacion(),Info + "Algo Mas");
	}
}



