

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class checkDecimalsSalaryTest {

	@Test
	void test() {
		boolean flag = gr.aueb.dmst.erp.Employee.checkDecimalsSalary(12.34);
		assertEquals(flag, true);
		boolean flag2 = gr.aueb.dmst.erp.Employee.checkDecimalsSalary(123.79);
		assertEquals(flag2, true);
		boolean flag3 = gr.aueb.dmst.erp.Employee.checkDecimalsSalary(1245.68795849);
		assertEquals(flag3, false);
	}

}
