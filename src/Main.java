
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main implements Serializable {
	static Scanner sc = new Scanner(System.in);

	/**
	 * This initializes Room and Booking objects.
	 */
	public static void main(String[] args) {
// MHNYMA PROS MO: THA TREKSEIS 2 FORES TO PROGRAMMA, MIA VAZONTAS SXOLIA TIS 4 TELEYTAIES GRAMMES KAI MIA VAZONTAS SXOLIA TIS 3 PRWTES GIA NA DOUME AN DOULEUOUN TA KWLOARXEIA
		Sales_LoadObjects();
	/*	MO_arxeia.grapsimo_Booking();
		MO_arxeia.grapsimo_Room();
		MO_arxeia.parsimo_Booking();
		MO_arxeia.parsimo_Room();
		MO_arxeia.Emfanish_Booking();
		MO_arxeia.Emfanish_Room(); */
		Main.getMenu();
	}

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
		SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy, HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		return formatter.format(date);
	}
}
