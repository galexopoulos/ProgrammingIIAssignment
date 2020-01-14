package src.test.java;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class loginTest {

	@Test
	void test() {
		src.main.java.gr.aueb.dmst.erp.Employee.Employees.clear(); // used because in maven the elements of the list stay
		new src.main.java.gr.aueb.dmst.erp.Employee("Ivan", "Zaytsev", "Athlete", "Petreasismydaddy", 12000, null);
		int output = src.main.java.gr.aueb.dmst.erp.Employee.login(0, "Petreasismydaddy");
		assertEquals(0, output);
	}

	@Test
	void test1() {
		src.main.java.gr.aueb.dmst.erp.Employee.Employees.clear(); // used because in maven the elements of the list stay
		new src.main.java.gr.aueb.dmst.erp.Employee("Ivan", "Zaytsev", "Athlete", "Petreasismydaddy", 12000, null);
		int output = src.main.java.gr.aueb.dmst.erp.Employee.login(0, "Petsreasismydaddy");
		assertEquals(-1, output);
	}

}
