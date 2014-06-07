package fiuba.algo3.tpfinal.test;

import modelo.Ladron;

import org.junit.Assert;
import org.junit.Test;


public class LadronTest {
	
	private String[] Sexo = {"Masculino","Femenino"};
	private String[] Hobbie = {"Tennis","Musica","Alpinismo","Paracaidismo","Natacion","Croquet"};
	private String[] Cabello = {"Castanio","Rubio","Rojo","Negro"};
	private String[] Senia = {"Cojera","Anillo","Tatuaje","Cicatriz","Joyas"};
	private String[] Vehiculo = {"Descapotable","Limusina","Deportivo","Moto"};
	
	@Test
	public void LadronEsFemenino() {
		Ladron ladron = new Ladron(Sexo[1],Hobbie[0],Cabello[3],Senia[1],Vehiculo[0]);
		Assert.assertEquals(ladron.Sexo(),Sexo[1]);
		Assert.assertEquals(ladron.Hobby(), Hobbie[0]);
		Assert.assertEquals(ladron.Cabello(),Cabello[3]);
		Assert.assertEquals(ladron.Senia(),Senia[1]);
		Assert.assertEquals(ladron.Vehiculo(),Vehiculo[0]);
	}

	@Test
	public void LadronNoEsFemenino() {
		Ladron ladron = new Ladron("Masculino","","","","");
		Assert.assertNotEquals(ladron.Sexo(),Sexo[1]);
	}

}
