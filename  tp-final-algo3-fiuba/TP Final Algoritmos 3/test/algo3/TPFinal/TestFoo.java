import static org.junit.Assert.*;

import org.junit.Test;


public class TestFoo {

	@Test
	public void test() {
		int x = 5;
		int y = 4;
		assertEquals("x*y da 20",20,x*y);
	}

}
