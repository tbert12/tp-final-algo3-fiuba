package fiuba.algo3.tpfinal.test;


	
	import java.util.ArrayList;

import modelo.Pais;
import modelo.Trayectoria;
import modelo.excepcion.ErrorNoHayMasPaisesParaAvanzar;
import static org.junit.Assert.*;

import org.junit.Test;

import static org.mockito.Mockito.*;

	
	@SuppressWarnings("unused")
	public class TrayectoriaTest {
		private Pais Argentina = new Pais("ARGENTINA",null);
		private Pais Brasil = new Pais("Brasil",null);
		
		@Test(expected = ErrorNoHayMasPaisesParaAvanzar.class)
		public void TrayectoriaPruebaAvanzarExcepcion() throws ErrorNoHayMasPaisesParaAvanzar{
			ArrayList<Pais> ListaPaises = new ArrayList<Pais>();
			ListaPaises.add(Argentina);
			Trayectoria UnaTrayectoria = new Trayectoria(ListaPaises);
			
			assertEquals(UnaTrayectoria.avanzar(),ErrorNoHayMasPaisesParaAvanzar.class);
			
		}
			
		@Test
		public void TrayectoriaAvanzar() throws ErrorNoHayMasPaisesParaAvanzar{
			ArrayList<Pais> ListaPaises = new ArrayList<Pais>();
			ListaPaises.add(Argentina);
			ListaPaises.add(Brasil);
			Trayectoria UnaTrayectoria = new Trayectoria(ListaPaises);
			
			assertEquals(UnaTrayectoria.avanzar(),Brasil);
		}
		
		@Test
		public void TrayectoPaisActualYFinal() throws ErrorNoHayMasPaisesParaAvanzar{
			ArrayList<Pais> ListaPaises = new ArrayList<Pais>();
			ListaPaises.add(Argentina);
			Trayectoria UnaTrayectoria = new Trayectoria(ListaPaises);
			assertEquals(Argentina, UnaTrayectoria.paisActual());
			assertEquals(Argentina, UnaTrayectoria.paisFinal());
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


