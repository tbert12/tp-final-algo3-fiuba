package fiuba.algo3.tpfinal.test;

import modelo.Ladron;

import org.junit.Assert;
import org.junit.Test;


public class LadronTest {

	@Test
	public void LadronEsFemenino() {
		Ladron ladron = new Ladron("femenino","","","","");
		Assert.assertEquals(ladron.Sexo(),"femenino");
	}

	@Test
	public void LadronNoEsFemenino() {
		Ladron ladron = new Ladron("masculino","","","","");
		Assert.assertNotEquals(ladron.Sexo(),"femenino");
	}
}
