package fiuba.algo3.tpfinal.test;


	
	import modelo.Pais;
import modelo.Trayectoria;
import modelo.excepcion.ErrorNoHayMasPaisesParaAvanzar;
import modelo.excepcion.ErrorNoHayPais;
import static org.junit.Assert.*;

import org.junit.Test;

import static org.mockito.Mockito.*;

	
	@SuppressWarnings("unused")
	public class TrayectoriaTest {
		
		
		@Test(expected = ErrorNoHayMasPaisesParaAvanzar.class)
		public void TrayectoriaPruebaAvanzarExcepcion() throws ErrorNoHayMasPaisesParaAvanzar,ErrorNoHayPais{
			Pais Argentina = new Pais("ARGENTINA",null);
			Pais pais[] = {Argentina};
			Trayectoria Trayectoria = new Trayectoria(pais);
			assertEquals(0, Trayectoria.avanzar());
			
		}
		
		@Test(expected = ErrorNoHayPais.class)
		public void TrayectoriaPruebasPaisActualYFinal() throws ErrorNoHayPais{
			Pais pais[] = {};
			assertEquals(0, new Trayectoria(pais));
			
		}
			
		@Test
		public void TrayectoriaAvanzar() throws ErrorNoHayMasPaisesParaAvanzar, ErrorNoHayPais{
			Pais Argentina = new Pais("ARGENTINA",null);
			Pais Brasil = new Pais("Brasil",null);
			Pais pais[]={Argentina,Brasil};
			Trayectoria Trayectoria = new Trayectoria(pais);
			assertEquals(Brasil, Trayectoria.avanzar());
		}
		
		@Test
		public void TrayectoPaisActualYFinal() throws ErrorNoHayPais, ErrorNoHayMasPaisesParaAvanzar{
			Pais Argentina = new Pais("ARGENTINA",null);
			Pais pais[]= {Argentina};
			Trayectoria Trayectoria = new Trayectoria(pais);
			assertEquals(Argentina, Trayectoria.paisActual());
			assertEquals(Argentina, Trayectoria.paisFinal());
		}
	}
			/*
	 @Test
		public void TrayecteriaExcepciones() throws ErrorNoHayMasPaisesParaAvanzar, ErrorNoHayPais{
			Trayectoria mockTrayec;
			Trayectoria mockTrayec2;
			//mockTrayec = mock(Trayectoria.class);
			//mockTrayec = new Trayectoria(any(Pais[].class));
			
			//when(mockTrayec.avanzar()).thenThrow(new ErrorNoHayMasPaisesParaAvanzar());
			//doThrow(new ErrorNoHayPais()).when(mockTrayec.paisActual());
			//doThrow(new ErrorNoHayPais()).when(mockTrayec.paisFinal());
			
			//Pais pais[] = any(Pais.class)};
			mockTrayec2 = mock(Trayectoria.class);
			Pais[] Argentina ={new Pais("Argentina",null)};
			mockTrayec2 = new Trayectoria(Argentina);
			
			
			when(mockTrayec2.paisActual()).thenReturn(Argentina[0])
			
			/*
			 * when(mockTrayec2.avanzar()).thenReturn(any(Pais.class));
			 when(mockTrayec2.paisActual()).thenReturn(any(Pais.class));
			when(mockTrayec2.paisFinal()).thenReturn(any(Pais.class));
			 */
			
		
		/*
		
		
		when(mockTrayec.paisActual()).thenThrow(new ErrorNoHayPais());
			//when(mockTrayec.paisFinal()).thenThrow(new ErrorNoHayPais());
			
			//doThrow(new ErrorNoHayMasPaisesParaAvanzar()).when(mockTrayec.avanzar());
			//doThrow(new ErrorNoHayPais()).when(mockTrayec.paisActual());
			//doThrow(new ErrorNoHayPais()).when(mockTrayec.paisFinal());
			mockTrayec = (Trayectoria.class);
			(mockTrayec.avanzar()).thenThrow(new ErrorNoHayPais());
			
		*/


