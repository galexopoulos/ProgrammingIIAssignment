package src.test.java;


import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class Shiftarr_Dep_Times_intTest {

	@Test
	void test() {
		int[][] output = {{ -1, -1, -1, -1}, { -1, -1, -1, -1}, {-1, -1, -1, -1}, {-1, -1, -1, -1}};
		try {
			output = src.main.java.gr.aueb.dmst.erp.Shift.arr_Dep_Times_int("12:00-18:00,20:00-23:00");
		}catch (src.main.java.gr.aueb.dmst.erp.ShiftException a) {
			System.out.println("Shift Exception here");
		}
		int[][] expected = {{ 12, 20, -1, -1}, { 0, 0, -1, -1}, {18, 23, -1, -1}, {0, 0, -1, -1}};
		assertArrayEquals(expected, output);
	}

}
