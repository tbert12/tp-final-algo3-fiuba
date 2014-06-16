package fiuba.algo3.tpfinal.test;

import static org.junit.Assert.*;
import modelo.ObjetoRobado;

import org.junit.Test;

public class ObjetoRobadoTest {
	private String valor = "muy valioso";
	private ObjetoRobado UnObjeto = new ObjetoRobado(valor);
	
	
	@Test
	public void ValorEsCorrecto() {
		assertEquals(UnObjeto.getValor(),valor);
	}

	@Test
	public void ValorNoEsCorrecto() {
		assertNotEquals(UnObjeto.getValor(),"valioso");
	}

}
