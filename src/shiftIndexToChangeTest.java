

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
			shiftcal = gr.aueb.dmst.erp.Shift.createShift(shift);
		} catch (gr.aueb.dmst.erp.ShiftException a) {
			System.err.println("wrong1 " + a);
		}
		
		int output = gr.aueb.dmst.erp.Manager.shiftIndexToChange(1, shiftcal[2]); //depends on the day it is today
		assertEquals(1, output);
	}

}
