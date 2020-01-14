package src.test.java;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class empBinarySearchTest {

	@Test
	void test() {
		src.main.java.gr.aueb.dmst.erp.Employee.Employees.clear(); // used because in maven the elements of the list stay
		new src.main.java.gr.aueb.dmst.erp.Employee("Ivan", "Zaytsev", "Athlete", "Petreasismydaddy", 12000, null);
		new src.main.java.gr.aueb.dmst.erp.Employee("Ivan", "Zaytsev", "Athlete", "Petreasismydaddy", 12000, null);

		int output = src.main.java.gr.aueb.dmst.erp.Employee.empBinarySearch(1);
		assertEquals(1, output);
	}

}