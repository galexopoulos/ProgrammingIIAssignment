

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class empBinarySearchTest {

	@Test
	void test() {
		new gr.aueb.dmst.erp.Employee("Ivan", "Zaytsev", "Athlete", "Petreasismydaddy", 12000, null);
		new gr.aueb.dmst.erp.Employee("Ivan", "Zaytsev", "Athlete", "Petreasismydaddy", 12000, null);

		int output = gr.aueb.dmst.erp.Employee.empBinarySearch(1);
		assertEquals(1, output);
	}

}