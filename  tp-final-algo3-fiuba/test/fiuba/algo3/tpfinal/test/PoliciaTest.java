package fiuba.algo3.tpfinal.test;

import modelo.Policia;
import modelo.RangoDetective;
import modelo.RangoInvestigador;
import modelo.RangoNovato;
import modelo.RangoSargento;

import org.junit.Assert;
import org.junit.Test;

public class PoliciaTest {

	
	public void SumarArrestos(int Cantidad,Policia Jugador){
		for (int i = 0; i<Cantidad; i++){
			Jugador.AddArresto();
		}
	}

	@Test
	public void PoliciaNuevoEsNovato() {
		
		Policia Jugador = new Policia();
		Assert.assertTrue(Jugador.getRango() instanceof RangoNovato);
	}
	
	@Test
	public void PoliciaCambiadeRangoAlSumarArrestos(){
		Policia Jugador = new Policia();
		
		this.SumarArrestos(5,Jugador);
		Assert.assertTrue(Jugador.getRango() instanceof RangoDetective);

		this.SumarArrestos(5,Jugador);
		Assert.assertTrue(Jugador.getRango() instanceof RangoInvestigador);
		
		this.SumarArrestos(10,Jugador);
		Assert.assertTrue(Jugador.getRango() instanceof RangoSargento);
	}
	
	@Test
	public void PoliciaNoCambiadeRangoConCuatroArrestos(){
		Policia Jugador = new Policia();
		for (int i=0;i<4;i++){
			Jugador.AddArresto();
		}
		Assert.assertFalse(Jugador.getRango() instanceof RangoDetective);
	}
	
	@Test 
	public void PoliciaVijaMasRapidoSiCambiaDeRango(){
		Policia Jugador = new Policia();
		//Novato Viaja a 900 Km/h
		int KilometrosAViajar = 5000;
		
		int HorasDeViajeEsperadasNovato = 5000/900;
		int HorasDeViajeNovato = Jugador.CostoDeViaje(KilometrosAViajar);
		Assert.assertEquals(HorasDeViajeNovato, HorasDeViajeEsperadasNovato);
		
		//RangoDetective viaja a 1100
		this.SumarArrestos(5,Jugador);
		int HorasDeViajeEsperadasDetective = 5000/1100;
		int HorasDeViajeDetective = Jugador.CostoDeViaje(KilometrosAViajar);
		Assert.assertTrue(HorasDeViajeDetective < HorasDeViajeNovato);
		Assert.assertEquals(HorasDeViajeDetective, HorasDeViajeEsperadasDetective);
	}
}
