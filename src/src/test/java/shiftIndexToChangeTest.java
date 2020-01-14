package src.test.java;


import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;

import org.junit.jupiter.api.Test;

class shiftIndexToChangeTest {

	@Test
	void test() {
		String[] shift = { "9:00-17:00", "1:00-9:00", "10:00-17:00", "7:00-14:00", "19:00-", "-1:00,12:00-13:00", "-",
		"11:00-17:00" };
		Calendar[][] shiftcal = { null, null, null, null, null, null, null };
		try {
			shiftcal = src.main.java.gr.aueb.dmst.erp.Shift.createShift(shift);
		} catch (src.main.java.gr.aueb.dmst.erp.ShiftException a) {
			System.err.println("wrong1 " + a);
		}
		
		int output = src.main.java.gr.aueb.dmst.erp.Manager.shiftIndexToChange(1, shiftcal[2]); 
		assertEquals(1, output);
	}

}
