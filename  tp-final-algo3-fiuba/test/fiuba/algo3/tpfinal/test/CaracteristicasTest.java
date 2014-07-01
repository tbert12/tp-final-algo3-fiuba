package fiuba.algo3.tpfinal.test;


import modelo.caracteristicas.*;

import org.junit.Assert;
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
		Assert.assertEquals(UnaCaracteristica.getSexo(),UnSexo);
	}
	@Test
	public void HobbyEsCorrecto() {
		Assert.assertEquals(UnaCaracteristica.getHobby(),UnHobby);
	}
	@Test
	public void CabelloEsCorrecto() {
		Assert.assertEquals(UnaCaracteristica.getCabello(),UnCabello);
	}
	@Test
	public void SeniaEsCorrecto() {
		Assert.assertEquals(UnaCaracteristica.getSenia(),UnaSenia);
	}
	@Test
	public void VehiculoEsCorrecto() {
		Assert.assertEquals(UnaCaracteristica.getVehiculo(),UnVehiculo);
	}
	
	@Test
	public void SexoNoEsCorrecto() {
		Assert.assertNotEquals(UnaCaracteristica.getSexo(),Sexo.FEMENINO);
	}
	@Test
	public void HobbyNoEsCorrecto() {
		Assert.assertNotEquals(UnaCaracteristica.getHobby(),Hobby.ALPINISMO);
	}
	@Test
	public void CabelloNoEsCorrecto() {
		Assert.assertNotEquals(UnaCaracteristica.getCabello(),Cabello.ROJO);
	}
	@Test
	public void SeniaNoEsCorrecto() {
		Assert.assertNotEquals(UnaCaracteristica.getSenia(),Senia.JOYAS);
	}
	@Test
	public void VehiculoNoEsCorrecto() {
		Assert.assertNotEquals(UnaCaracteristica.getVehiculo(),Vehiculo.LIMUSINA);
	}
}
