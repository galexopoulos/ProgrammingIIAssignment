import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class whereIsManagerTest {

	@Test
	void test() {
		new Manager("Ivan", "Zaytsev", "Athlete", "Petreasismydaddy", 12000, null);
		int output = Manager.whereIsManager(0);
		assertEquals(0, output);
	}

}
