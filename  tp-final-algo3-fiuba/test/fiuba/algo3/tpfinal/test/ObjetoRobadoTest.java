package fiuba.algo3.tpfinal.test;

import static org.junit.Assert.*;
import modelo.ObjetoRobado;

import org.junit.Test;

public class ObjetoRobadoTest {
	
	private String nombre = "La Giaconda";
	private String valor = "muy valioso";
	private ObjetoRobado UnObjeto = new ObjetoRobado(nombre,valor);
	
	
	@Test
	public void ValorEsCorrecto() {
		assertEquals(UnObjeto.getValor(),valor);
	}

	@Test
	public void ValorNoEsCorrecto() {
		assertNotEquals(UnObjeto.getValor(),"valioso");
	}
	
	@Test
	public void NombreEsCorrecto() {
		assertEquals(UnObjeto.getNombre(),nombre);
	}

	@Test
	public void NombreNoEsCorrecto() {
		assertNotEquals(UnObjeto.getNombre(),"La ultima cena");
	}

}
