package fiuba.algo3.tpfinal.test;

import static org.junit.Assert.*;
import modelo.Coordenadas;

import org.junit.Test;

public class CoordenadaTest {

	private static final double DELTA = 1e-15;
	
	@Test
	public void CoordenadasObtengoLatitudLongitudQueEspero() {
		Coordenadas unaCoordenada = new Coordenadas(4.3,-6.8);
		double LatitudEsperada = 4.3;
		double LongitudEsperada = -6.8;
		assertEquals(unaCoordenada.getLatitud(),LatitudEsperada,DELTA);
		assertEquals(unaCoordenada.getLongitud(), LongitudEsperada,DELTA);
	}
	
	@Test
	public void DistanciaEntreCoordenadas() {
		Coordenadas unaCoordenada = new Coordenadas(4.3,-6.8);
		Coordenadas otrasCoordenada = new Coordenadas(9,33);
		int distanciaQueEspero = (int)(Math.sqrt( Math.pow(4.3-9, 2)+Math.pow(-6.8 - 33, 2))*111);
		assertEquals(unaCoordenada.DistanciaA(otrasCoordenada),distanciaQueEspero,DELTA);
	}

}
