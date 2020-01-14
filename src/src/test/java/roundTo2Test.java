package src.test.java;

import static org.junit.jupiter.api.Assertions.*;
import java.lang.*;
import org.junit.jupiter.api.Test;

class roundTo2Test {

	@Test
	void test() {
		double output = src.main.java.gr.aueb.dmst.erp.Hr_Director.roundTo2(20.123);
		assertEquals(20.12, output);
		output = src.main.java.gr.aueb.dmst.erp.Hr_Director.roundTo2(20.129);
		assertEquals(20.13, output);
	}
	
	@Test
	void test1() {
		double output = src.main.java.gr.aueb.dmst.erp.Hr_Director.roundTo2(20.129);
		assertEquals(20.13, output);
	}
	
	@Test
	void test2() {
		double output = src.main.java.gr.aueb.dmst.erp.Hr_Director.roundTo2(20);
		assertEquals(20, output);
	}

}
