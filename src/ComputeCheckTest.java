

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

class ComputeCheckTest {

	@Test
	void test1() {
		gr.aueb.dmst.erp.Room r = new gr.aueb.dmst.erp.Room(1, 2, 50);
		Date checkIn1 = new Date();
		Date checkOut1 = new Date();
		gr.aueb.dmst.erp.Booking b1 = new gr.aueb.dmst.erp.Booking(checkIn1, checkOut1, 1, true);
		double output1 = b1.computeCheck(true);
		assertEquals(62, output1);
	}

	@Test
	void test2() {
		gr.aueb.dmst.erp.Room r = new gr.aueb.dmst.erp.Room(1, 2, 50);
		Date checkIn2 = new Date();
		Date checkOut2 = new Date();
		gr.aueb.dmst.erp.Booking b2 = new gr.aueb.dmst.erp.Booking(checkIn2, checkOut2, 1, false);
		double output2 = b2.computeCheck(false);
		assertEquals(50, output2);
	}
}
