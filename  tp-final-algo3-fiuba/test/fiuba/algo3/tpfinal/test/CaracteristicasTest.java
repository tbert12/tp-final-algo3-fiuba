package fiuba.algo3.tpfinal.test;

import static org.junit.Assert.*;
import modelo.caracteristicas.*;

import org.junit.Test;

public class CaracteristicasTest {
	private Sexo UnSexo = Sexo.MASCULINO;
	private Hobby UnHobby = Hobby.TENNIS;
	private Cabello UnCabello = Cabello.RUBIO;
	private Senia UnaSenia = Senia.TATUAJE;
	private Vehiculo UnVehiculo = Vehiculo.DESCAPOTABLE;
	private Caracteristicas UnaCaracteristica = new Caracteristicas(UnSexo,UnHobby,UnCabello,UnaSenia,UnVehiculo);

	@Test
	public void SexoEsCorrecto() {
		assertEquals(UnaCaracteristica.getSexo(),UnSexo);
	}
	@Test
	public void HobbyEsCorrecto() {
		assertEquals(UnaCaracteristica.getHobby(),UnHobby);
	}
	@Test
	public void CabelloEsCorrecto() {
		assertEquals(UnaCaracteristica.getCabello(),UnCabello);
	}
	@Test
	public void SeniaEsCorrecto() {
		assertEquals(UnaCaracteristica.getSenia(),UnaSenia);
	}
	@Test
	public void VehiculoEsCorrecto() {
		assertEquals(UnaCaracteristica.getVehiculo(),UnVehiculo);
	}
	
	@Test
	public void SexoNoEsCorrecto() {
		assertNotEquals(UnaCaracteristica.getSexo(),Sexo.FEMENINO);
	}
	@Test
	public void HobbyNoEsCorrecto() {
		assertNotEquals(UnaCaracteristica.getHobby(),Hobby.ALPINISMO);
	}
	@Test
	public void CabelloNoEsCorrecto() {
		assertNotEquals(UnaCaracteristica.getCabello(),Cabello.ROJO);
	}
	@Test
	public void SeniaNoEsCorrecto() {
		assertNotEquals(UnaCaracteristica.getSenia(),Senia.JOYAS);
	}
	@Test
	public void VehiculoNoEsCorrecto() {
		assertNotEquals(UnaCaracteristica.getVehiculo(),Vehiculo.LIMUSINA);
	}
}
