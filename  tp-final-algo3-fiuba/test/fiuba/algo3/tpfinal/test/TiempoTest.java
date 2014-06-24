package fiuba.algo3.tpfinal.test;

import static org.junit.Assert.*;
import modelo.Tiempo;

import org.junit.Test;

public class TiempoTest {
	
	private int CantidadHoras = 154;
	private Tiempo UnTiempo = new Tiempo(CantidadHoras);

	@Test
	public void ReducirHoras() {
		int HorasAReducir = 8;
		UnTiempo.reducirHoras(HorasAReducir);
		assertEquals(UnTiempo.getHoras(),CantidadHoras - HorasAReducir);
	}
	@Test
	public void TiempoNoEstaAgotado() {
		int HorasAReducir = 24;
		UnTiempo.reducirHoras(HorasAReducir);
		assertFalse(UnTiempo.tiempoAgotado());
	}
	@Test
	public void TiempoEstaAgotado() {
		int HorasAReducir = 154;
		UnTiempo.reducirHoras(HorasAReducir);
		assertTrue(UnTiempo.tiempoAgotado());
		
		UnTiempo.reducirHoras(HorasAReducir);
		assertTrue(UnTiempo.tiempoAgotado());
	}
}
