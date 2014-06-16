package fiuba.algo3.tpfinal.test;

import static org.junit.Assert.*;
import modelo.BaseDeDatos;
import modelo.Ladron;
import modelo.ObjetoRobado;
import modelo.Partida;
import modelo.Policia;

import org.junit.Test;

public class PartidaTest {
	private Policia UnPolicia;
	private BaseDeDatos UnaBaseDeDatos;
	private Ladron UnLadron;
	private ObjetoRobado UnObjeto;
	
	private Partida UnaPartida = new Partida(UnPolicia,UnLadron,UnaBaseDeDatos,UnObjeto);
	
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
