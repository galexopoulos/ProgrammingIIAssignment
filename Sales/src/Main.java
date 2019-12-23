import java.util.Calendar;
import java.util.Date;

public class Main {

public static void Sales_LoadObjects() {
	
	new Room(1,2,50);
	new Room(1,2,50);
	new Room(1,2,50);
	new Room(1,2,50);
	new Room(1,2,50);
	new Room(1,2,50);
	new Room(1,2,50);
	new Room(1,2,50);
	new Room(1,2,50);
	new Room(1,2,50);
	new Room(2,3,70);
	new Room(2,3,70);
	new Room(2,3,70);
	new Room(2,3,70);
	new Room(2,3,70);
	new Room(2,3,70);
	new Room(2,3,70);
	new Room(2,3,70);
	new Room(2,3,70);
	new Room(2,3,70);
	new Room(3,4,100);
	new Room(3,4,100);
	new Room(3,4,100);
	new Room(3,4,100);
	new Room(3,5,150);
	new Room(3,5,150);
	new Room(3,6,200);
	/*Calendar Cal = Calendar.getInstance();
	Cal.set(Calendar.YEAR, 2020);
	Cal.set(Calendar.MONTH, month1 - 1);
	Cal.set(Calendar.DAY_OF_MONTH, day1);
	Cal.set(Calendar.HOUR_OF_DAY, 12);
	Cal.set(Calendar.MINUTE, 0);
	Cal.set(Calendar.SECOND, 0);
	Cal.set(Calendar.MILLISECOND, 0);
	Date checkIn = Cal.getTime();*/
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sales_LoadObjects();
		Booking.getMenu();
	}

}
