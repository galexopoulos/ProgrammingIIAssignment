package src.test.java;

import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GetVisitorsTest {

	@Test
	void test() {
		src.main.java.gr.aueb.dmst.erp.Room r1 = new src.main.java.gr.aueb.dmst.erp.Room(1, 2, 50);
		src.main.java.gr.aueb.dmst.erp.Room r2 = new src.main.java.gr.aueb.dmst.erp.Room(1, 2, 50);
		Calendar Cal = Calendar.getInstance();
		Cal.set(Calendar.YEAR, 2020);
		Cal.set(Calendar.MONTH, 1 - 1);
		Cal.set(Calendar.DAY_OF_MONTH, 13);
		Cal.set(Calendar.HOUR_OF_DAY, 12);
		Cal.set(Calendar.MINUTE, 0);
		Cal.set(Calendar.SECOND, 0);
		Cal.set(Calendar.MILLISECOND, 0);
		Date checkIn1 = Cal.getTime();
		Cal = Calendar.getInstance();
		Cal.set(Calendar.YEAR, 2020);
		Cal.set(Calendar.MONTH, 1 - 1);
		Cal.set(Calendar.DAY_OF_MONTH, 17);
		Cal.set(Calendar.HOUR_OF_DAY, 11);
		Cal.set(Calendar.MINUTE, 0);
		Cal.set(Calendar.SECOND, 0);
		Cal.set(Calendar.MILLISECOND, 0);
		Date checkOut1 = Cal.getTime();
		src.main.java.gr.aueb.dmst.erp.Booking b4 = new src.main.java.gr.aueb.dmst.erp.Booking(checkIn1, checkOut1, 1,
				true);
		b4.setCheckedIn(true);
		Cal = Calendar.getInstance();
		Cal.set(Calendar.YEAR, 2020);
		Cal.set(Calendar.MONTH, 1 - 1);
		Cal.set(Calendar.DAY_OF_MONTH, 14);
		Cal.set(Calendar.HOUR_OF_DAY, 12);
		Cal.set(Calendar.MINUTE, 0);
		Cal.set(Calendar.SECOND, 0);
		Cal.set(Calendar.MILLISECOND, 0);
		Date checkIn2 = Cal.getTime();
		Cal = Calendar.getInstance();
		Cal.set(Calendar.YEAR, 2020);
		Cal.set(Calendar.MONTH, 1 - 1);
		Cal.set(Calendar.DAY_OF_MONTH, 18);
		Cal.set(Calendar.HOUR_OF_DAY, 11);
		Cal.set(Calendar.MINUTE, 0);
		Cal.set(Calendar.SECOND, 0);
		Cal.set(Calendar.MILLISECOND, 0);
		Date checkOut2 = Cal.getTime();
		src.main.java.gr.aueb.dmst.erp.Booking b3 = new src.main.java.gr.aueb.dmst.erp.Booking(checkIn2, checkOut2, 2,
				false);
		b3.setCheckedIn(true);
		int[] output = src.main.java.gr.aueb.dmst.erp.Booking.getVisitors();
		int[] test = { 4, 2 };
		assertArrayEquals(test, output);
	}

}