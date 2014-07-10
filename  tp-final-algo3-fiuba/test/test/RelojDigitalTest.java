package test;

import static org.junit.Assert.*;

import javax.swing.JLabel;

import org.junit.Test;

import vistas.relojdigital.RelojDigital;

public class RelojDigitalTest {

	@Test
	public void testEsLunesALas7() {
		RelojDigital reloj = new RelojDigital(new JLabel());
		assertEquals(reloj.obtenerHoraDigital(),"Lunes, 7:00 Hs.");
	}
	@Test
	public void testPasaunDia(){
		RelojDigital reloj = new RelojDigital(new JLabel());
		reloj.actualizarHora(153);
		reloj.avanzarUnaHora();
		assertEquals(reloj.avanzarUnaHora(),"Lunes, 8:00 Hs.");
	}

}
