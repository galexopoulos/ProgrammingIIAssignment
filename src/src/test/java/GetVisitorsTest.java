package src.test.java;

import java.util.Date;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GetVisitorsTest {

	@Test
	void test() {
		src.main.java.gr.aueb.dmst.erp.Room r1 = new src.main.java.gr.aueb.dmst.erp.Room(1, 2, 50);
		src.main.java.gr.aueb.dmst.erp.Room r2 = new src.main.java.gr.aueb.dmst.erp.Room(1, 2, 50);
		Date checkIn1 = new Date();
		Date checkOut1 = new Date();
		src.main.java.gr.aueb.dmst.erp.Booking b1 = new src.main.java.gr.aueb.dmst.erp.Booking(checkIn1, checkOut1, 1,
				true);
		b1.setCheckedIn(true);
		Date checkIn2 = new Date();
		Date checkOut2 = new Date();
		src.main.java.gr.aueb.dmst.erp.Booking b2 = new src.main.java.gr.aueb.dmst.erp.Booking(checkIn2, checkOut2, 2,
				false);
		b2.setCheckedIn(true);
		int[] output = src.main.java.gr.aueb.dmst.erp.Booking.getVisitors();
		int[] test = { 4, 2 };
		assertArrayEquals(test, output);
	}

}