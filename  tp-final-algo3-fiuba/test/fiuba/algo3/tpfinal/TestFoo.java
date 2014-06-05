package fiuba.algo3.tpfinal;

import fiuba.algo3.cla.Foo;
import org.junit.Assert;
import org.junit.Test;

public class TestFoo {

	@Test
	public void testA() {
		  Foo foo = new Foo();
	      String result = foo.doFoo();
	      Assert.assertEquals("Foo", result);
	}
}
