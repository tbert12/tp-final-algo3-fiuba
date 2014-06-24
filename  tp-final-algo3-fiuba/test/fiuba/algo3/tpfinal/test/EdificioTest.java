package fiuba.algo3.tpfinal.test;

import modelo.Edificio;
import modelo.Policia;
import modelo.Tiempo;

import org.junit.Assert;
import org.junit.Test;

public class EdificioTest {
	
	private String pista = "Esto es una pista de prueba.";
	private String nombre = "Biblioteca";
	private Edificio UnEdificio = new Edificio(nombre,pista);
	
	@Test
	public void testPistaEdificioEsCorrecta() {
		Policia UnPolicia = new Policia("Juan", 0);
		Tiempo unTiempo = new Tiempo(50);
		UnPolicia.setTiempo(unTiempo);
		
		Assert.assertEquals(UnEdificio.visitar(UnPolicia),pista);
		
	}
	@Test
	public void testNombreEdificioEsCorrect0() {
		
		Assert.assertEquals(UnEdificio.getNombre(),nombre);
		
	}
}
