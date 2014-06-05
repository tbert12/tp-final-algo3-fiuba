package fiuba.algo3.Test;

import junit.framework.Assert;
import org.junit.Test;

public class TestUnitariosLadron {

	@Test
	public void LadronPoseeCaracterista(){
        Ladron ladron = Ladron new();
        ladron.sexo = "masculino";
		Assert.assertEquals(ladron.sexo,"masculino" );

	}

}
