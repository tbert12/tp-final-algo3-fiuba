package fiuba.algo3.tpfinal.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import modelo.BaseDeDatos;
import modelo.Edificio;
import modelo.Ladron;
import modelo.ObjetoRobado;
import modelo.Pais;
import modelo.Partida;
import modelo.Policia;

import org.junit.Test;

public class PartidaTest {
	private Policia UnPolicia;
	private BaseDeDatos UnaBaseDeDatos;
	private Ladron UnLadron;
	private ObjetoRobado UnObjeto;
	
	private Partida UnaPartida = new Partida(UnPolicia,UnLadron,UnaBaseDeDatos,UnObjeto);
	private Edificio biblioteca = new Edificio("biblioteca","biblioteca pista");
	private Edificio puerto = new Edificio("puerto","puerto pista");
	private Edificio fiuba = new Edificio("fiuba", "fiuba pista");
	private Edificio[] edificios = {biblioteca,puerto,fiuba};
	
	public ArrayList<String> NombresDeEdificiosAMostrar(){
		Pais PaisActual = UnPolicia.getPais();
		
		return PaisActual.getNombresDeEdificios();
	}
	private Pais pais = new Pais("Argentina",edificios);
	@Test
	public void TestObtenerNombresDeEdificios() {
		assertEquals("fiuba", pais.getNombresDeEdificios().get(2));
		assertEquals("puerto", pais.getNombresDeEdificios().get(1));
		assertEquals("biblioteca", pais.getNombresDeEdificios().get(0));
	}
	
	


}
