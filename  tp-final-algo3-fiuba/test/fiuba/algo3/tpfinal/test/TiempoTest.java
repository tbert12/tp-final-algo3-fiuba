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
		UnTiempo.ReducirHoras(HorasAReducir);
		assertEquals(UnTiempo.Horas(),CantidadHoras - HorasAReducir);
	}
	@Test
	public void TiempoNoEstaAgotado() {
		int HorasAReducir = 24;
		UnTiempo.ReducirHoras(HorasAReducir);
		assertFalse(UnTiempo.TiempoAgotado());
	}
	@Test
	public void TiempoEstaAgotado() {
		int HorasAReducir = 154;
		UnTiempo.ReducirHoras(HorasAReducir);
		assertTrue(UnTiempo.TiempoAgotado());
		
		UnTiempo.ReducirHoras(HorasAReducir);
		assertTrue(UnTiempo.TiempoAgotado());
	}
}
