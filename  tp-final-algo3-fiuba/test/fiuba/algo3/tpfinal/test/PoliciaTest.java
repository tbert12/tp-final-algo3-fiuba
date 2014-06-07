package fiuba.algo3.tpfinal.test;

import modelo.Policia;
import modelo.RangoDetective;
import modelo.RangoInvestigador;
import modelo.RangoNovato;
import modelo.RangoSargento;

import org.junit.Assert;
import org.junit.Test;

public class PoliciaTest {

	@Test
	public void PoliciaNuevoEsNovato() {
		
		Policia Jugador = new Policia();
		Assert.assertTrue(Jugador.getRango() instanceof RangoNovato);
	}
	
	@Test
	public void PoliciaTieneSeisArrestosCambiodeRango(){
		Policia Jugador = new Policia();
		for (int i=0;i<5;i++){
			Jugador.AddArresto();
		}
		Assert.assertTrue(Jugador.getRango() instanceof RangoDetective);
		for (int i=0;i<5;i++){
			Jugador.AddArresto();
		}
		Assert.assertTrue(Jugador.getRango() instanceof RangoInvestigador);
		for (int i=0;i<10;i++){
			Jugador.AddArresto();
		}
		Assert.assertTrue(Jugador.getRango() instanceof RangoSargento);
	}
}
