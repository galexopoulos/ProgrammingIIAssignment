package src.test.java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GetVisitorsTest {

	@Test
	void test() {
		int[] output = src.main.java.gr.aueb.dmst.erp.Booking.getVisitors();
		int[] test = { 4, 2 };
		assertArrayEquals(test, output);
	}

}