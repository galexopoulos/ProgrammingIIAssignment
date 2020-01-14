

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class loginTest {

	@Test
	void test() {
		new gr.aueb.dmst.erp.Employee("Ivan", "Zaytsev", "Athlete", "Petreasismydaddy", 12000, null);
		int output = gr.aueb.dmst.erp.Employee.login(0, "Petreasismydaddy");
		assertEquals(0, output);
	}

	@Test
	void test1() {
		new gr.aueb.dmst.erp.Employee("Ivan", "Zaytsev", "Athlete", "Petreasismydaddy", 12000, null);
		int output = gr.aueb.dmst.erp.Employee.login(0, "Petsreasismydaddy");
		assertEquals(-1, output);
	}

}
