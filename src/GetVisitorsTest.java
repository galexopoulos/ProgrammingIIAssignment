

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

class GetVisitorsTest {

	@Test
	void test() {
		gr.aueb.dmst.erp.Room r1 = new gr.aueb.dmst.erp.Room(1, 2, 50);
		gr.aueb.dmst.erp.Room r2 = new gr.aueb.dmst.erp.Room(1, 2, 50);
		Date checkIn1 = new Date();
		Date checkOut1 = new Date();
		gr.aueb.dmst.erp.Booking b1 = new gr.aueb.dmst.erp.Booking(checkIn1, checkOut1, 1, true);
		b1.setCheckedIn(true);
		Date checkIn2 = new Date();
		Date checkOut2 = new Date();
		gr.aueb.dmst.erp.Booking b2 = new gr.aueb.dmst.erp.Booking(checkIn2, checkOut2, 2, false);
		b2.setCheckedIn(true);
		int[] output = gr.aueb.dmst.erp.Booking.getVisitors();
		int[] test = { 4, 2 };
		assertThat(output, is(new int[] { 4, 2 }));
	}

}