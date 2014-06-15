package fiuba.algo3.tpfinal.test;

import static org.junit.Assert.*;
import modelo.Caracteristicas;

import org.junit.Test;

public class CaracteristicasTest {
	private String Sexo = "masculino";
	private String Hobby = "tenis";
	private String Cabello = "rubio";
	private String Senia = "tatuaje";
	private String Vehiculo = "descapotable";
	private Caracteristicas UnaCaracteristica = new Caracteristicas(Sexo,Hobby,Cabello,Senia,Vehiculo);

	@Test
	public void SexoEsCorrecto() {
		assertEquals(UnaCaracteristica.getSexo(),Sexo);
	}
	@Test
	public void HobbyEsCorrecto() {
		assertEquals(UnaCaracteristica.getHobby(),Hobby);
	}
	@Test
	public void CabelloEsCorrecto() {
		assertEquals(UnaCaracteristica.getCabello(),Cabello);
	}
	@Test
	public void SeniaEsCorrecto() {
		assertEquals(UnaCaracteristica.getSenia(),Senia);
	}
	@Test
	public void VehiculoEsCorrecto() {
		assertEquals(UnaCaracteristica.getVehiculo(),Vehiculo);
	}
}
