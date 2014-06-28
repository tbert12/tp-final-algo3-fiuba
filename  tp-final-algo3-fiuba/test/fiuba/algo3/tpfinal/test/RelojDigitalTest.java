package fiuba.algo3.tpfinal.test;

import static org.junit.Assert.*;

import org.junit.Test;

import vistas.relojdigital.RelojDigital;

public class RelojDigitalTest {

	@Test
	public void testEsLunesALas7() {
		RelojDigital reloj = new RelojDigital();
		assertEquals(reloj.HoraDigital(154),"Lunes, 7:00 Hs.");
	}
	@Test
	public void testPasaunDia(){
		RelojDigital reloj = new RelojDigital();
		assertEquals(reloj.HoraDigital(153),"Lunes, 8:00 Hs.");
		assertEquals(reloj.HoraDigital(153-24),"Martes, 8:00 Hs.");
		assertEquals(reloj.HoraDigital(153-24-5),"Martes, 13:00 Hs.");
	}

}
