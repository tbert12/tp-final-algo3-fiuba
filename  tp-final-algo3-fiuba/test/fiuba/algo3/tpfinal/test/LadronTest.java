package fiuba.algo3.tpfinal.test;

import org.junit.Assert;
import org.junit.Test;
import fiuba.algo3.tpfinal.clases.Ladron;


public class LadronTest {

	@Test
	public void LadronEsFemenino() {
		Ladron ladron = new Ladron("femenino","","","","");
		Assert.assertEquals(ladron.Sexo(),"femenino");
	}

}
