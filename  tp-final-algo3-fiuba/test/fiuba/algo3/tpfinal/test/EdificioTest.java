package fiuba.algo3.tpfinal.test;

import modelo.Edificio;

import org.junit.Assert;
import org.junit.Test;

public class EdificioTest {
	
	private String pista = "Esto es una pista de prueba.";
	private String nombre = "Biblioteca";
	private Edificio UnEdificio = new Edificio(nombre,pista);
	
	@Test
	public void testPistaEdificioEsCorrecta() {
		
		Assert.assertEquals(UnEdificio.getPista(),pista);
		
	}
	
	public void testNombreEdificioEsCorrect0() {
		
		Assert.assertEquals(UnEdificio.getNombre(),nombre);
		
	}
}
