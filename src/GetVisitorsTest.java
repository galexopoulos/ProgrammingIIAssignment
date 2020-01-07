
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

class GetVisitorsTest {

	@Test
	void test() {
		Room r1 = new Room(1, 2, 50);
		Room r2 = new Room(1, 2, 50);
		Date checkIn1 = new Date();
		Date checkOut1 = new Date();
		Booking b1 = new Booking(checkIn1, checkOut1, 1, true);
		b1.setCheckedIn(true);
		Date checkIn2 = new Date();
		Date checkOut2 = new Date();
		Booking b2 = new Booking(checkIn2, checkOut2, 2, false);
		b2.setCheckedIn(true);
		int[] output = Booking.getVisitors();
		int[] test = { 4, 2 };
		assertThat(output, is(new int[] { 4, 2 }));
	}

}