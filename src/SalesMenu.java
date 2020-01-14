
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * This class implements the main surface of the sales department.
 * 
 * @author Nikolas Moatsos
 */

public class SalesMenu implements Serializable {
	/**
	 * Scanner to get input.
	 */
	static Scanner sc = new Scanner(System.in);

/**
 * This initializes Rooms and Bookings.
 */
	public static void Sales_LoadObjects() {

		new Room(1, 2, 50);
		new Room(1, 2, 50);
		new Room(1, 2, 50);
		new Room(1, 2, 50);
		new Room(1, 2, 50);
		new Room(1, 2, 50);
		new Room(1, 2, 50);
		new Room(1, 2, 50);
		new Room(1, 2, 50);
		new Room(1, 2, 50);
		new Room(2, 3, 70);
		new Room(2, 3, 70);
		new Room(2, 3, 70);
		new Room(2, 3, 70);
		new Room(2, 3, 70);
		new Room(2, 3, 70);
		new Room(2, 3, 70);
		new Room(2, 3, 70);
		new Room(2, 3, 70);
		new Room(2, 3, 70);
		new Room(3, 4, 100);
		new Room(3, 4, 100);
		new Room(3, 4, 100);
		new Room(3, 4, 100);
		new Room(3, 5, 150);
		new Room(3, 5, 150);
		new Room(3, 6, 200);

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
		Cal.set(Calendar.DAY_OF_MONTH, 26);
		Cal.set(Calendar.HOUR_OF_DAY, 11);
		Cal.set(Calendar.MINUTE, 0);
		Cal.set(Calendar.SECOND, 0);
		Cal.set(Calendar.MILLISECOND, 0);
		Date checkOut = Cal.getTime();
		Booking a = new Booking(checkIn, checkOut, 2, true);
	//	a.setCheckedIn(true);

		Cal.set(Calendar.YEAR, 2020);
		Cal.set(Calendar.MONTH, 2 - 1);
		Cal.set(Calendar.DAY_OF_MONTH, 20);
		Cal.set(Calendar.HOUR_OF_DAY, 12);
		Cal.set(Calendar.MINUTE, 0);
		Cal.set(Calendar.SECOND, 0);
		Cal.set(Calendar.MILLISECOND, 0);
		checkIn = Cal.getTime();
		Cal.set(Calendar.YEAR, 2020);
		Cal.set(Calendar.MONTH, 2 - 1);
		Cal.set(Calendar.DAY_OF_MONTH, 25);
		Cal.set(Calendar.HOUR_OF_DAY, 11);
		Cal.set(Calendar.MINUTE, 0);
		Cal.set(Calendar.SECOND, 0);
		Cal.set(Calendar.MILLISECOND, 0);
		checkOut = Cal.getTime();
		a = new Booking(checkIn, checkOut, 3, true);
		// a.setCheckedIn(true);

		Cal.set(Calendar.YEAR, 2020);
		Cal.set(Calendar.MONTH, 2 - 1);
		Cal.set(Calendar.DAY_OF_MONTH, 19);
		Cal.set(Calendar.HOUR_OF_DAY, 12);
		Cal.set(Calendar.MINUTE, 0);
		Cal.set(Calendar.SECOND, 0);
		Cal.set(Calendar.MILLISECOND, 0);
		checkIn = Cal.getTime();
		Cal.set(Calendar.YEAR, 2020);
		Cal.set(Calendar.MONTH, 2 - 1);
		Cal.set(Calendar.DAY_OF_MONTH, 23);
		Cal.set(Calendar.HOUR_OF_DAY, 11);
		Cal.set(Calendar.MINUTE, 0);
		Cal.set(Calendar.SECOND, 0);
		Cal.set(Calendar.MILLISECOND, 0);
		checkOut = Cal.getTime();
		a = new Booking(checkIn, checkOut, 5, true);
		// a.setCheckedIn(true);

		Cal.set(Calendar.YEAR, 2020);
		Cal.set(Calendar.MONTH, 3 - 1);
		Cal.set(Calendar.DAY_OF_MONTH, 20);
		Cal.set(Calendar.HOUR_OF_DAY, 12);
		Cal.set(Calendar.MINUTE, 0);
		Cal.set(Calendar.SECOND, 0);
		Cal.set(Calendar.MILLISECOND, 0);
		checkIn = Cal.getTime();
		Cal.set(Calendar.YEAR, 2020);
		Cal.set(Calendar.MONTH, 3 - 1);
		Cal.set(Calendar.DAY_OF_MONTH, 25);
		Cal.set(Calendar.HOUR_OF_DAY, 11);
		Cal.set(Calendar.MINUTE, 0);
		Cal.set(Calendar.SECOND, 0);
		Cal.set(Calendar.MILLISECOND, 0);
		checkOut = Cal.getTime();
		a = new Booking(checkIn, checkOut, 2, true);
		// a.setCheckedIn(true);

		Cal.set(Calendar.YEAR, 2020);
		Cal.set(Calendar.MONTH, 3 - 1);
		Cal.set(Calendar.DAY_OF_MONTH, 22);
		Cal.set(Calendar.HOUR_OF_DAY, 12);
		Cal.set(Calendar.MINUTE, 0);
		Cal.set(Calendar.SECOND, 0);
		Cal.set(Calendar.MILLISECOND, 0);
		checkIn = Cal.getTime();
		Cal.set(Calendar.YEAR, 2020);
		Cal.set(Calendar.MONTH, 3 - 1);
		Cal.set(Calendar.DAY_OF_MONTH, 24);
		Cal.set(Calendar.HOUR_OF_DAY, 11);
		Cal.set(Calendar.MINUTE, 0);
		Cal.set(Calendar.SECOND, 0);
		Cal.set(Calendar.MILLISECOND, 0);
		checkOut = Cal.getTime();
		a = new Booking(checkIn, checkOut, 7, true);
		// a.setCheckedIn(true);
		Cal.set(Calendar.YEAR, 2020);
		Cal.set(Calendar.MONTH, 5 - 1);
		Cal.set(Calendar.DAY_OF_MONTH, 22);
		Cal.set(Calendar.HOUR_OF_DAY, 12);
		Cal.set(Calendar.MINUTE, 0);
		Cal.set(Calendar.SECOND, 0);
		Cal.set(Calendar.MILLISECOND, 0);
		checkIn = Cal.getTime();
		Cal.set(Calendar.YEAR, 2020);
		Cal.set(Calendar.MONTH, 5 - 1);
		Cal.set(Calendar.DAY_OF_MONTH, 24);
		Cal.set(Calendar.HOUR_OF_DAY, 11);
		Cal.set(Calendar.MINUTE, 0);
		Cal.set(Calendar.SECOND, 0);
		Cal.set(Calendar.MILLISECOND, 0);
		checkOut = Cal.getTime();
		a = new Booking(checkIn, checkOut, 24, true);
		// a.setCheckedIn(true);

		Cal = Calendar.getInstance();
		Cal.set(Calendar.YEAR, 2020);
		Cal.set(Calendar.MONTH, Cal.get(Calendar.MONTH));
		Cal.set(Calendar.DAY_OF_MONTH, Cal.get(Calendar.DAY_OF_MONTH) - 1);
		Cal.set(Calendar.HOUR_OF_DAY, 12);
		Cal.set(Calendar.MINUTE, 0);
		Cal.set(Calendar.SECOND, 0);
		Cal.set(Calendar.MILLISECOND, 0);
		checkIn = Cal.getTime();
		Cal = Calendar.getInstance();
		Cal.set(Calendar.YEAR, 2020);
		Cal.set(Calendar.MONTH,Cal.get(Calendar.MONTH));
		Cal.set(Calendar.DAY_OF_MONTH,Cal.get(Calendar.DAY_OF_MONTH) + 1);
		Cal.set(Calendar.HOUR_OF_DAY, 11);
		Cal.set(Calendar.MINUTE, 0);
		Cal.set(Calendar.SECOND, 0);
		Cal.set(Calendar.MILLISECOND, 0);
		checkOut = Cal.getTime();
		a = new Booking(checkIn, checkOut, 1, true);
		a.setCheckedIn(true);

		Cal = Calendar.getInstance();
		Cal.set(Calendar.YEAR, 2020);
		Cal.set(Calendar.MONTH, Cal.get(Calendar.MONTH));
		Cal.set(Calendar.DAY_OF_MONTH, Cal.get(Calendar.DAY_OF_MONTH) - 3);
		Cal.set(Calendar.HOUR_OF_DAY, 12);
		Cal.set(Calendar.MINUTE, 0);
		Cal.set(Calendar.SECOND, 0);
		Cal.set(Calendar.MILLISECOND, 0);
		checkIn = Cal.getTime();
		Cal = Calendar.getInstance();
		Cal.set(Calendar.YEAR, 2020);
		Cal.set(Calendar.MONTH, Cal.get(Calendar.MONTH));
		Cal.set(Calendar.DAY_OF_MONTH,  Cal.get(Calendar.DAY_OF_MONTH));
		Cal.set(Calendar.HOUR_OF_DAY, 11);
		Cal.set(Calendar.MINUTE, 0);
		Cal.set(Calendar.SECOND, 0);
		Cal.set(Calendar.MILLISECOND, 0);
		checkOut = Cal.getTime();
		a = new Booking(checkIn, checkOut, 3, true);
		a.setCheckedIn(true);

		Cal = Calendar.getInstance();
		Cal.set(Calendar.YEAR, 2020);
		Cal.set(Calendar.MONTH, Cal.get(Calendar.MONTH));
		Cal.set(Calendar.DAY_OF_MONTH,Cal.get(Calendar.DAY_OF_MONTH) - 1);
		Cal.set(Calendar.HOUR_OF_DAY, 12);
		Cal.set(Calendar.MINUTE, 0);
		Cal.set(Calendar.SECOND, 0);
		Cal.set(Calendar.MILLISECOND, 0);
		checkIn = Cal.getTime();
		Cal = Calendar.getInstance();
		Cal.set(Calendar.YEAR, 2020);
		Cal.set(Calendar.MONTH, Cal.get(Calendar.MONTH));
		Cal.set(Calendar.DAY_OF_MONTH, Cal.get(Calendar.DAY_OF_MONTH));
		Cal.set(Calendar.HOUR_OF_DAY, 11);
		Cal.set(Calendar.MINUTE, 0);
		Cal.set(Calendar.SECOND, 0);
		Cal.set(Calendar.MILLISECOND, 0);
		checkOut = Cal.getTime();
		a = new Booking(checkIn, checkOut, 12, true);
		 a.setCheckedIn(true);
	}
/**
 * This method displays the main menu of Sales department.
 */
	public static void getMenu() {
		 Sales_LoadObjects();
		boolean goBack = false;
		for (;;) {
			int choose = 0;
			do {
				System.out.println("------------------ SALES MENU ---- " + getDate() + " ---------------");
				System.out.println("1) Bookings");
				System.out.println("2) Rooms");
				System.out.println("3) Exit");
				System.out.println("------------------ CHOOSE A NUMBER BETWEEN 1 AND 3 --------------------");
				try {
					choose = sc.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("Insert an Integer!");
					sc.nextLine();
					continue;
				}
				if (choose != 1 && choose != 2 && choose != 3) {
					System.out.println("Insert 1 or 2 or 3! ");
				}
			} while (choose != 1 && choose != 2 && choose != 3); // check menu input
			switch (choose) {
			case 1:
				Booking.getMenu();
				break;
			case 2:
				Room.getMenu();
				break;
			case 3:
				goBack = true;
				break;
			}
			if (goBack) {
				break;
			}
			continue;
		}
	}

	public static String getDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy, HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		return formatter.format(date);
	}
}
