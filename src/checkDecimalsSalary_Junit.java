
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class checkDecimalsSalary_Junit {

	@Test
	void test() {
		boolean flag = Employee.checkDecimalsSalary(12.34);
		assertEquals(flag, true);
		boolean flag2 = Employee.checkDecimalsSalary(123.79);
		assertEquals(flag2, true);
		boolean flag3 = Employee.checkDecimalsSalary(1245.68795849);
		assertEquals(flag3, false);
	}

}
