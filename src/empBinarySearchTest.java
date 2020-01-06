
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class empBinarySearchTest {

	@Test
	void test() {
		new Employee("Ivan", "Zaytsev", "Athlete", "Petreasismydaddy", 12000, null);
		new Employee("Ivan", "Zaytsev", "Athlete", "Petreasismydaddy", 12000, null);

		int output = Employee.empBinarySearch(1);
		assertEquals(1, output);
	}

}