package fiuba.algo3.tpfinal.test;

import static org.junit.Assert.*;

import org.junit.Test;

import vistas.Sonidos;
import vistas.relojdigital.RelojDigital;

public class RelojDigitalTest {

	@Test
	public void testEsLunesALas7() {
		Sonidos sonido = new Sonidos();
		RelojDigital reloj = new RelojDigital(sonido);
		assertEquals(reloj.ObtenerHoraDigital(),"Lunes, 7:00 Hs.");
	}
	@Test
	public void testPasaunDia(){
		Sonidos sonido = new Sonidos();
		RelojDigital reloj = new RelojDigital(sonido);
		reloj.ActualizarHora(153);
		reloj.AvanzarUnaHora();
		assertEquals(reloj.AvanzarUnaHora(),"Lunes, 8:00 Hs.");
	}

}
