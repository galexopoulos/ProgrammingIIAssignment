package src.test.java;


import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import src.main.java.gr.aueb.dmst.erp.*;


class whereIsManagerTest {

	@Test
	void test() {
		new src.main.java.gr.aueb.dmst.erp.Manager("Ivan", "Zaytsev", "Athlete", "Petreasismydaddy", 12000, null);
		new src.main.java.gr.aueb.dmst.erp.Employee("Ivan", "Zaytsev", "Athlete", "Petreasismydaddy", 12000, null);
		new src.main.java.gr.aueb.dmst.erp.Employee("Ivan", "Zaytsev", "Athlete", "Petreasismydaddy", 12000, null);
		new src.main.java.gr.aueb.dmst.erp.Employee("Ivan", "Zaytsev", "Athlete", "Petreasismydaddy", 12000, null);
		new src.main.java.gr.aueb.dmst.erp.Manager("Ivan", "Zaytsev", "Athlete", "Petreasismydaddy", 12000, null);
		int output = src.main.java.gr.aueb.dmst.erp.Manager.whereIsManager(4);
		assertEquals(4, output);
	}

}

