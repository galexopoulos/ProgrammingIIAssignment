package src.test.java;


import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

import src.main.java.gr.aueb.dmst.erp.Booking;
import src.main.java.gr.aueb.dmst.erp.Room;

class ComputeCheckTest {

	@Test
	void test1() {
		Room r = new Room(1, 2, 50);
		Calendar Cal = Calendar.getInstance();
		Cal.set(Calendar.YEAR, 2020);
		Cal.set(Calendar.MONTH, 2 - 1);
		Cal.set(Calendar.DAY_OF_MONTH, 24);
		Cal.set(Calendar.HOUR_OF_DAY, 12);
		Cal.set(Calendar.MINUTE, 0);
		Cal.set(Calendar.SECOND, 0);
		Cal.set(Calendar.MILLISECOND, 0);
		Date checkIn = Cal.getTime();
		Cal = Calendar.getInstance();
		Cal.set(Calendar.YEAR, 2020);
		Cal.set(Calendar.MONTH, 2 - 1);
		Cal.set(Calendar.DAY_OF_MONTH, 25);
		Cal.set(Calendar.HOUR_OF_DAY, 11);
		Cal.set(Calendar.MINUTE, 0);
		Cal.set(Calendar.SECOND, 0);
		Cal.set(Calendar.MILLISECOND, 0);
		Date checkOut = Cal.getTime();
		Booking b1 = new Booking(checkIn, checkOut, 1, true);
		double output1 = b1.computeCheck(true);
		assertEquals(62, output1);
	}

	@Test
	void test2() {
		Room r = new Room(1, 2, 50);
		Calendar Cal = Calendar.getInstance();
		Cal.set(Calendar.YEAR, 2020);
		Cal.set(Calendar.MONTH, 2 - 1);
		Cal.set(Calendar.DAY_OF_MONTH, 26);
		Cal.set(Calendar.HOUR_OF_DAY, 12);
		Cal.set(Calendar.MINUTE, 0);
		Cal.set(Calendar.SECOND, 0);
		Cal.set(Calendar.MILLISECOND, 0);
		Date checkIn = Cal.getTime();
		Cal = Calendar.getInstance();
		Cal.set(Calendar.YEAR, 2020);
		Cal.set(Calendar.MONTH, 2 - 1);
		Cal.set(Calendar.DAY_OF_MONTH, 28);
		Cal.set(Calendar.HOUR_OF_DAY, 11);
		Cal.set(Calendar.MINUTE, 0);
		Cal.set(Calendar.SECOND, 0);
		Cal.set(Calendar.MILLISECOND, 0);
		Date checkOut = Cal.getTime();
		Booking b2 = new Booking(checkIn, checkOut, 2, false);
		double output2 = b2.computeCheck(false);
		assertEquals(100, output2);
	}
}