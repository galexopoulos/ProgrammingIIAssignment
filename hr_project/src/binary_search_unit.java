import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class binary_search_unit {

	@Test
	void test() {
		/*3*/	Employee Ivan = new Employee("Ivan", "Zaytsev", "Athlete", "Petreasismydaddy", 12000, null);
		/*3*/	Employee ron = new Employee("Ivan", "Zaytsev", "Athlete", "Petreasismydaddy", 12000, null);

		int output = Employee.binarySearch(1);
		assertEquals(1, output);
	}

}
