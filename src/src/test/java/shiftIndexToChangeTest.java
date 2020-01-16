package src.test.java;


import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;

import org.junit.jupiter.api.Test;

class shiftIndexToChangeTest {

	@Test
	void test() {
		String[] shift = {"19:00-22:00", "19:00-22:00", "19:00-22:00", "19:00-22:00", "19:00-22:00", "19:00-22:00", "19:00-22:00",
				"19:00-22:00"};
		Calendar[][] shiftcal = { null, null, null, null, null, null, null };
		try {
			shiftcal = src.main.java.gr.aueb.dmst.erp.Shift.createShift(shift);
		} catch (src.main.java.gr.aueb.dmst.erp.ShiftException a) {
			System.err.println("wrong1 " + a);
		}
		Calendar day = Calendar.getInstance();
		int i = day.get(Calendar.DAY_OF_WEEK) - 1;
		int output = src.main.java.gr.aueb.dmst.erp.Manager.shiftIndexToChange(3, shiftcal[i]); 
		//it is -1 because it overpasses midnight, we don't do a check with outcome different than -1 because
		//the method is related to the time the program runs and we want to run it any time
		assertEquals(-1, output);
	}

}
