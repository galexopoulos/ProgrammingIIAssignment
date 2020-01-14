package src.test.java;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class checkDecimalsSalaryTest {

	@Test
	void test() {
		boolean flag = src.main.java.gr.aueb.dmst.erp.Employee.checkDecimalsSalary(12.34);
		assertEquals(flag, true);
		boolean flag2 = src.main.java.gr.aueb.dmst.erp.Employee.checkDecimalsSalary(123.79);
		assertEquals(flag2, true);
		boolean flag3 = src.main.java.gr.aueb.dmst.erp.Employee.checkDecimalsSalary(1245.68795849);
		assertEquals(flag3, false);
	}

}
