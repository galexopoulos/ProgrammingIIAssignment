
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";

	/**
	 * This initializes Room and Booking objects.
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
		Cal.set(Calendar.YEAR, 2019);
		Cal.set(Calendar.MONTH, 12 - 1);
		Cal.set(Calendar.DAY_OF_MONTH, 24);
		Cal.set(Calendar.HOUR_OF_DAY, 12);
		Cal.set(Calendar.MINUTE, 0);
		Cal.set(Calendar.SECOND, 0);
		Cal.set(Calendar.MILLISECOND, 0);
		Date checkIn = Cal.getTime();
		Cal = Calendar.getInstance();
		Cal.set(Calendar.YEAR, 2019);
		Cal.set(Calendar.MONTH, 12 - 1);
		Cal.set(Calendar.DAY_OF_MONTH, 26);
		Cal.set(Calendar.HOUR_OF_DAY, 11);
		Cal.set(Calendar.MINUTE, 0);
		Cal.set(Calendar.SECOND, 0);
		Cal.set(Calendar.MILLISECOND, 0);
		Date checkOut = Cal.getTime();
		Booking a = new Booking(checkIn, checkOut, 2, true);
		a.setCheckedIn(true);

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
		Cal.set(Calendar.MONTH, 1 - 1);
		Cal.set(Calendar.DAY_OF_MONTH, 20);
		Cal.set(Calendar.HOUR_OF_DAY, 12);
		Cal.set(Calendar.MINUTE, 0);
		Cal.set(Calendar.SECOND, 0);
		Cal.set(Calendar.MILLISECOND, 0);
		checkIn = Cal.getTime();
		Cal.set(Calendar.YEAR, 2020);
		Cal.set(Calendar.MONTH, 1 - 1);
		Cal.set(Calendar.DAY_OF_MONTH, 25);
		Cal.set(Calendar.HOUR_OF_DAY, 11);
		Cal.set(Calendar.MINUTE, 0);
		Cal.set(Calendar.SECOND, 0);
		Cal.set(Calendar.MILLISECOND, 0);
		checkOut = Cal.getTime();
		a = new Booking(checkIn, checkOut, 2, true);
		// a.setCheckedIn(true);

		Cal.set(Calendar.YEAR, 2020);
		Cal.set(Calendar.MONTH, 1 - 1);
		Cal.set(Calendar.DAY_OF_MONTH, 22);
		Cal.set(Calendar.HOUR_OF_DAY, 12);
		Cal.set(Calendar.MINUTE, 0);
		Cal.set(Calendar.SECOND, 0);
		Cal.set(Calendar.MILLISECOND, 0);
		checkIn = Cal.getTime();
		Cal.set(Calendar.YEAR, 2020);
		Cal.set(Calendar.MONTH, 1 - 1);
		Cal.set(Calendar.DAY_OF_MONTH, 24);
		Cal.set(Calendar.HOUR_OF_DAY, 11);
		Cal.set(Calendar.MINUTE, 0);
		Cal.set(Calendar.SECOND, 0);
		Cal.set(Calendar.MILLISECOND, 0);
		checkOut = Cal.getTime();
		a = new Booking(checkIn, checkOut, 7, true);
		// a.setCheckedIn(true);

		Cal.set(Calendar.YEAR, 2020);
		Cal.set(Calendar.MONTH, 1 - 1);
		Cal.set(Calendar.DAY_OF_MONTH, 6);
		Cal.set(Calendar.HOUR_OF_DAY, 12);
		Cal.set(Calendar.MINUTE, 0);
		Cal.set(Calendar.SECOND, 0);
		Cal.set(Calendar.MILLISECOND, 0);
		checkIn = Cal.getTime();
		Cal.set(Calendar.YEAR, 2020);
		Cal.set(Calendar.MONTH, 1 - 1);
		Cal.set(Calendar.DAY_OF_MONTH, 11);
		Cal.set(Calendar.HOUR_OF_DAY, 11);
		Cal.set(Calendar.MINUTE, 0);
		Cal.set(Calendar.SECOND, 0);
		Cal.set(Calendar.MILLISECOND, 0);
		checkOut = Cal.getTime();
		a = new Booking(checkIn, checkOut, 1, true);
		a.setCheckedIn(true);

		Cal.set(Calendar.YEAR, 2020);
		Cal.set(Calendar.MONTH, 1 - 1);
		Cal.set(Calendar.DAY_OF_MONTH, 6);
		Cal.set(Calendar.HOUR_OF_DAY, 12);
		Cal.set(Calendar.MINUTE, 0);
		Cal.set(Calendar.SECOND, 0);
		Cal.set(Calendar.MILLISECOND, 0);
		checkIn = Cal.getTime();
		Cal.set(Calendar.YEAR, 2020);
		Cal.set(Calendar.MONTH, 1 - 1);
		Cal.set(Calendar.DAY_OF_MONTH, 9);
		Cal.set(Calendar.HOUR_OF_DAY, 11);
		Cal.set(Calendar.MINUTE, 0);
		Cal.set(Calendar.SECOND, 0);
		Cal.set(Calendar.MILLISECOND, 0);
		checkOut = Cal.getTime();
		a = new Booking(checkIn, checkOut, 3, true);
		a.setCheckedIn(true);

		Cal.set(Calendar.YEAR, 2020);
		Cal.set(Calendar.MONTH, 1 - 1);
		Cal.set(Calendar.DAY_OF_MONTH, 6);
		Cal.set(Calendar.HOUR_OF_DAY, 12);
		Cal.set(Calendar.MINUTE, 0);
		Cal.set(Calendar.SECOND, 0);
		Cal.set(Calendar.MILLISECOND, 0);
		checkIn = Cal.getTime();
		Cal.set(Calendar.YEAR, 2020);
		Cal.set(Calendar.MONTH, 1 - 1);
		Cal.set(Calendar.DAY_OF_MONTH, 9);
		Cal.set(Calendar.HOUR_OF_DAY, 11);
		Cal.set(Calendar.MINUTE, 0);
		Cal.set(Calendar.SECOND, 0);
		Cal.set(Calendar.MILLISECOND, 0);
		checkOut = Cal.getTime();
		a = new Booking(checkIn, checkOut, 12, true);
		// a.setCheckedIn(true);
	}

	public static void getMenu() {
		Sales_LoadObjects();
		boolean goBack = false;
		for (;;) {
			int choose = 0;
			do {
				System.out.println("Sales Menu :");
				System.out.println("1. Bookings");
				System.out.println("2. Rooms");
				System.out.println("3. Go Back");
				System.out.print("Selection : ");
				try {
					choose = sc.nextInt();
				} catch (InputMismatchException e) {
					System.out.println(ANSI_RED + "Insert an Integer!" + ANSI_RESET);
					sc.nextLine();
					continue;
				}
				if (choose != 1 && choose != 2 && choose != 3) {
					System.out.println(ANSI_RED + "Insert 1 or 2 or 3! " + ANSI_RESET);
				}
			} while (choose != 1 && choose != 2 && choose != 3); // check menu input
			System.out.println();
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
}

