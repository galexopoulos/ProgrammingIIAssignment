import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Booking {
	static Scanner sc = new Scanner(System.in);
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	private Date checkIn;
	private Date checkOut;
	private int nights;
	private static int counter = 0;
	private int bookingCode;
	private boolean buffet;
	private int roomNumber;
	private double extraExpenses;
	private double check;
	private boolean checkedIn;
	private boolean checkedOut;
	private static ArrayList<ArrayList<Booking>> bookings = new ArrayList<ArrayList<Booking>>();
	protected static double getChecks = 0;

	public Booking(Date checkIn, Date checkOut, int roomNumber, boolean buffet) {
		this.buffet = buffet;
		this.checkedIn = false;
		this.checkedOut = false;
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.nights = ((Math.abs((int) (checkIn.getTime() - checkOut.getTime()))) / (1000 * 60 * 60 * 24)) + 1; //count nights for a new booking
		bookingCode = ++counter; //booking codes start from 1 and add up when a new room is added
		this.extraExpenses = 0;
		this.check = computeCheck(buffet);
		getChecks += this.check;
		setBookings(); //add the booking to the list 
	}

	public double computeCheck(boolean buffet) { //calculate the booking's check, bearing in mind buffet's charge (12)
		double check = 0;
		for (Room a : Room.getRooms()) {
			if (a.getRoomNumber() == roomNumber) {
				if (buffet) {
					check = (nights * a.getPricePerNight()) + (nights * 12 );
				} else {
					check = nights * a.getPricePerNight();
					break;
				}
			}
		}
		return check;
	}

	public void increaseCheck(double x) {
		extraExpenses += x;
		check += x;
		getChecks +=x;
	}

	public void setBookings() { // add the booking to the list in the right row depending on room's number
		bookings.get(roomNumber - 1).add(this);
	}
	
	public static int[] getVisitors() {
		int sum[] = { 0, 0 };
		for (Room room : Room.getRooms()) {
			for (Booking booking : bookings.get(room.getRoomNumber() - 1)) {
				if (booking.checkedIn) {
					sum[0] += Room.getRooms().get(booking.roomNumber).getCapacity();
					if (booking.buffet) {
						sum[1] += Room.getRooms().get(booking.roomNumber).getCapacity();
					}
					break;
				}
			}
		}
		return sum;
	}

	public static Room Availability(int capacity, Date checkIn, Date checkOut) {
		Room ret = null;
		for (;;) {
			int i = 0;
			boolean f1 = false;
			int r[] = new int[Room.getRooms().size()];
			for (Room room : Room.getRooms()) {
				if (room.getCapacity() == capacity) { //check for every room
					boolean f2 = true;
					for (Booking booking : bookings.get(i)) { // check for every room's booking
						if (!((checkIn.before(booking.checkIn) && checkOut.before(booking.checkIn))
								|| (checkIn.after(booking.checkOut) && checkOut.after(booking.checkOut)))) { // check if the check in and check out is not affected by an other booking and reverse the condition and as a result check which rooms don't suit
							f2 = false;
							break;
						}
					}
					if (f2) { // print available rooms
						System.out.println("Room's number : " + Room.getRooms().get(i).getRoomNumber() + "\tFloor :"
								+ Room.getRooms().get(i).getFloor() + "\tPrice per night :"
								+ Room.getRooms().get(i).getPricePerNight() + "�");
						f1 = true; // at least one room available
					}
					if (f2) {
						r[i] = 1; // if the room is available put 1 in the room's number row of the table
					}
				}
				i++;
			}
			if (f1) { 
				int chosen = 0;
				boolean f3;
				do {
					f3 = false;
					System.out.println("Choose the room's number you want to book or press 0 to cancel the procedure : ");
					try {
						chosen = sc.nextInt();
					} catch (InputMismatchException e) {
						System.out.println(ANSI_RED + "Insert an Integer!" + ANSI_RESET);
						sc.nextLine();
						continue;
					}
					if (chosen > 0 && chosen <= r.length) {
						for (int j = 0; j < r.length; j++) { // for every room
							if ((j + 1 == chosen) && (r[j] == 1)) { // check if  the chosen room is not one of the printed rooms
								f3 = true;
								break;
							}
							if (j + 1 == r.length) { // if the loop hasn't been broken this room is  not available 
								System.out.println(ANSI_RED + "This room is not compatible to your requirements!" + ANSI_RESET);
							}
						}
					} else if (chosen == 0) { // cancel the procedure if you choose 0
						f3 = true;
					} else  { // room number out of bounds
						System.out.println(ANSI_RED + "This room does not exists!" + ANSI_RESET);
					}
				} while (f3 == false);
				if (chosen != 0) {
					ret = Room.getRooms().get(chosen - 1);
				} else {
					System.out.println("No booking created!\n");
				}
			} else { // no rooms were available 
				System.out.println("No rooms available for these dates!\n");
			}
			break;
		}
		return ret; // return the room to be booked
	}

	public static void getMenu() {
		for (;;) {
			int choose1 = 0;
			do {
				System.out.println("Booking's Menu :");
				System.out.println("1. Search for availability and create a booking");
				System.out.println("2. Check In procedure");
				System.out.println("3. Check Out procedure");
				System.out.println("4. Cancel a booking");
				System.out.println("5. Search for bookings ");
				System.out.print("Selection : ");
				try {
					choose1 = sc.nextInt();
				} catch (InputMismatchException e) {
					System.out.println(ANSI_RED + "Insert an Integer!" + ANSI_RESET);
					sc.nextLine();
					continue;
				}
				if (choose1 != 1 && choose1 != 2 && choose1 != 3 && choose1 != 4 && choose1 != 5) {
					System.out.println(ANSI_RED + "Insert 1 or 2 or 3 or 4 or 5! " + ANSI_RESET);
				}
			} while (choose1 != 1 && choose1 != 2 && choose1 != 3 && choose1 != 4 && choose1 != 5); // check menu input 
			System.out.println();
			switch (choose1) {
			case 1:
				boolean f = false;
				int capacity = -1;
				int counter1 = 0;
				do {
					if (counter1 > 0) {
						System.out.println(ANSI_RED + "No existing rooms with this capacity!" + ANSI_RESET);
					}
					try {
						System.out.println("Insert room's capacity (press 0 to cancel the procedure) : ");
						capacity = sc.nextInt();
					} catch (InputMismatchException e) {
						System.out.println(ANSI_RED + "Insert an Integer!" + ANSI_RESET);
						sc.nextLine();
						counter1 = 0;
						continue;
					}
					counter1++;
				} while (capacity < 0 || capacity > 4 || capacity == 1); //check capacity input or 0 to break
				if (capacity == 0) {
					break;
				}
				sc.nextLine();
				int day1 = 0;
				int month1 = 0;
				int year1 = 0;
				Date checkIn = null;
				Date today;
				Calendar Cal1;
				int counter2 = 0;
				boolean breaks = false;
				do {
					if (counter2 > 0) { // check if there was a fail attempt for check in date
						System.out.println(ANSI_RED + "Wrong Check In Date!" + ANSI_RESET);
					}
					System.out.println("Insert Check In date (press 0 to cancel the procedure) ,");
					do {
						try {
							System.out.println("Day : ");
							day1 = sc.nextInt();
						} catch (InputMismatchException e) {
							System.out.println(ANSI_RED + "Insert an Integer!" + ANSI_RESET);
							sc.nextLine();
							continue;
						}
						f = true;
					} while (f == false); // check day input or 0 to break
					if (day1 == 0) {
						breaks = true;
						break;
					}
					f = false;
					do {
						try {
							System.out.println("Month : ");
							month1 = sc.nextInt();
						} catch (InputMismatchException e) {
							System.out.println(ANSI_RED + "Insert an Integer!" + ANSI_RESET);
							sc.nextLine();
							continue;
						}
						f = true;
					} while (f == false); // check month input or 0 to break
					if (month1 == 0) {
						breaks = true;
						break;
					}
					f = false;
					do {
						try {
							System.out.println("Year : ");
							year1 = sc.nextInt();
						} catch (InputMismatchException e) {
							System.out.println(ANSI_RED + "Insert an Integer!" + ANSI_RESET);
							sc.nextLine();
							continue;
						}
						f = true;
					} while (f == false); // check year input or 0 to break
					if (year1 == 0) {
						breaks = true;
						break;
					}
					Cal1 = Calendar.getInstance();
					// set the date to calendar
					Cal1.set(Calendar.YEAR, year1);
					Cal1.set(Calendar.MONTH, month1 - 1);
					Cal1.set(Calendar.DAY_OF_MONTH, day1);
					Cal1.set(Calendar.HOUR_OF_DAY, 12);
					Cal1.set(Calendar.MINUTE, 0);
					Cal1.set(Calendar.SECOND, 0);
					Cal1.set(Calendar.MILLISECOND, 0);
					today = new Date(); //set today's date
					checkIn = Cal1.getTime(); // set check in date from calendar to date 
					Calendar Caltoday = Calendar.getInstance();
					Calendar CalcheckIn = Calendar.getInstance();
					// check if the check in is for today to accept the date
					CalcheckIn.setTime(checkIn);
					if ((Caltoday.get(Calendar.DAY_OF_MONTH) == CalcheckIn.get(Calendar.DAY_OF_MONTH))
							&& (Caltoday.get(Calendar.MONTH) == CalcheckIn.get(Calendar.MONTH))
							&& (Caltoday.get(Calendar.YEAR) == CalcheckIn.get(Calendar.YEAR))) {
						checkIn = Caltoday.getTime(); //set check in same as the today date to be accepted
						break;
					}
					counter2++;
				} while (checkIn.before(today)); // check the check in date to be after today 
				if (breaks) {
					break;
				}
				int day2 = 0;
				int month2 = 0;
				int year2 = 0;
				Date checkOut = null;
				Calendar Cal2;
				counter2 = 0;
				do {
					if (counter2 > 0) { // check if there was a fail attempt for check out date
						System.out.println(ANSI_RED + "Wrong Check Out Date!" + ANSI_RESET);
					}
					f = false;
					System.out.println("Insert Check Out date (press 0 to cancel the procedure) ,");
					do {
						try {
							System.out.println("Day : ");
							day2 = sc.nextInt();
						} catch (InputMismatchException e) {
							System.out.println(ANSI_RED + "Insert an Integer!" + ANSI_RESET);
							sc.nextLine();
							continue;
						}
						f = true;
					} while (f == false); // check day input or 0 to break
					if (day2 == 0) {
						breaks = true;
						break;
					}
					f = false;
					do {
						try {
							System.out.println("Month : ");
							month2 = sc.nextInt();
						} catch (InputMismatchException e) {
							System.out.println(ANSI_RED + "Insert an Integer!" + ANSI_RESET);
							sc.nextLine();
							continue;
						}
						f = true;
					} while (f == false); // check month input or 0 to break
					if (month2 == 0) {
						breaks = true;
						break;
					}
					f = false;
					do {
						try {
							System.out.println("Year : ");
							year2 = sc.nextInt();
						} catch (InputMismatchException e) {
							System.out.println(ANSI_RED + "Insert an Integer!" + ANSI_RESET);
							sc.nextLine();
							continue;
						}
						f = true;
					} while (f == false); // check year input or 0 to break
					if (year2 == 0) {
						breaks = true;
						break;
					}
					// set the date to calendar
					Cal2 = Calendar.getInstance();
					Cal2.set(Calendar.YEAR, year2);
					Cal2.set(Calendar.MONTH, month2 - 1);
					Cal2.set(Calendar.DAY_OF_MONTH, day2);
					Cal2.set(Calendar.HOUR_OF_DAY, 11);
					Cal2.set(Calendar.MINUTE, 0);
					Cal2.set(Calendar.SECOND, 0);
					Cal2.set(Calendar.MILLISECOND, 0);
					checkOut = Cal2.getTime();
					counter2++;
				} while (checkIn.after(checkOut)); // check the check out date to be after check in 

				if (breaks) { // check if procedure is canceled in order to break
					break;
				}
				Room roomForBook = Availability(capacity, checkIn, checkOut); //check for availability
				if (roomForBook == null) { // if there was no room to be booked
					break;
				} else {
					sc.nextLine();
					boolean buffet;
					int counter3 = 0;
					do {
						if (counter3 > 0) { // check if there was a fail attempt for buffet answer
							System.out.println(ANSI_RED + "Only valid answers are 'YES' and 'NO'!" + ANSI_RESET);
						}
						System.out.println("If buffet is included to the "
								+ "booking type 'YES' if it's not type 'NO' (Extra 12 � charge per day) : ");
						String answer = sc.nextLine();
						if (answer.toLowerCase().equals("yes")) {
							buffet = true;
							break;
						} else if (answer.toLowerCase().equals("no")) {
							buffet = false;
							break;
						}
						counter3++;
					} while (true); // check answers to be yes or no
					System.out.println("Fullname : ");
					String fullname = sc.nextLine();
					System.out.println("Phone number : ");
					long PhoneNumber = sc.nextLong();
					sc.nextLine();
					System.out.println("Email : ");
					String email = sc.nextLine();
					boolean sure;
					int counter14 = 0;
					do {
						if (counter14 > 0) { // check if there was a fail attempt for sure answer
							System.out.println(ANSI_RED + "Only valid answers are 'YES' and 'NO'!" + ANSI_RESET);
						}
						System.out.println("Are you sure you want to create the booking for : " + checkIn + " and " + checkOut + ". Type 'YES' or 'NO' : ");
						String answer = sc.nextLine();
						if (answer.toLowerCase().equals("yes")) {
							sure = true;
							break;
						} else if (answer.toLowerCase().equals("no")) {
							sure = false;
							break;
						}
						counter14++;
					} while (true); // check answers to be yes or no
					if (sure) {
					Booking a = new Booking(checkIn, checkOut, roomForBook.getRoomNumber(), buffet); // create the booking
					System.out.println("The booking with code : " + a.bookingCode + " in the room no." + a.roomNumber + " and check : " + a.check + "�" 
							+ "\n has been completed successfully!\n ");
					} else {
						System.out.println("No booking created!\n");
					}
					break;
				}
			case 2:
				int roomIn = -1;
				int counter4 = 0;
				do {
					if (counter4 > 0) { // check if there was a fail attempt for room number
						System.out.println(ANSI_RED + "No room with this number, try again!" + ANSI_RESET);
					}
					System.out.println("Insert the number of Checking In room (press 0 to cancel the procedure) : ");
					try {
						roomIn = sc.nextInt();
					} catch (InputMismatchException e) {
						System.out.println(ANSI_RED + "Insert an Integer!" + ANSI_RESET);
						sc.nextLine();
						counter4 = 0;
						continue;
					}
					counter4++;
				} while (roomIn < 0 || roomIn > Room.getRooms().size()); // check room's number not to be out of bounds or 0 to break
				if (roomIn == 0) {
					break;
				}
				//set dates to calendar
				Calendar today1 = Calendar.getInstance();
				Calendar checkInd = Calendar.getInstance();
				Booking b1 = null;
				boolean already = false;
				for (Booking book : bookings.get(roomIn - 1)) { // for the chosen room
					checkInd.setTime(book.checkIn);
					if ((today1.get(Calendar.DAY_OF_MONTH) == checkInd.get(Calendar.DAY_OF_MONTH))
							&& (today1.get(Calendar.MONTH) == checkInd.get(Calendar.MONTH))
							&& (today1.get(Calendar.YEAR) == checkInd.get(Calendar.YEAR))
							&& (today1.get(Calendar.HOUR) >= checkInd.get(Calendar.HOUR))) { //check if its is the time for check in
						if (book.checkedIn == false) { // check if this booking has already checked in
							book.checkedIn = true;
							b1 = book;
							break;
						} else {
							already = true;
							break;
						}
					}
				}
				if (already == true) {
					System.out.println("This room has already Checked In!\n");
				} else if (b1 == null) {
					System.out.println("It is not the time or day for this room to Check In!\n");
				} else {
					b1.checkIn = today1.getTime();
					System.out.println("Check In for Room no : " + b1.roomNumber + ", with Booking Code : "
							+ b1.bookingCode + ", completed succesfully at : " + b1.checkIn + "\n");
				}
				break;
			case 3:
				int roomOut = -1;
				int counter5 = 0;
				do {
					if (counter5 > 0) {
						System.out.println(ANSI_RED + "No room with this number, try again!" + ANSI_RESET);
					}
					System.out.println("Insert the number of Checking Out room (press 0 to cancel the procedure) :  ");
					try {
						roomOut = sc.nextInt();
					} catch (InputMismatchException e) {
						System.out.println(ANSI_RED + "Insert an Integer!" + ANSI_RESET);
						sc.nextLine();
						counter5 = 0;
						continue;
					}
					counter5++;
				} while (roomOut < 0 || roomOut > Room.getRooms().size());
				if (roomOut == 0) {
					break;
				}
				Calendar today2 = Calendar.getInstance();
				Calendar checkOutd = Calendar.getInstance();
				Booking b2 = null;
				boolean already2 = false;
				for (Booking book : bookings.get(roomOut - 1)) {
					checkOutd.setTime(book.checkOut);
					if ((today2.get(Calendar.DAY_OF_MONTH) == checkOutd.get(Calendar.DAY_OF_MONTH))
							&& (today2.get(Calendar.MONTH) == checkOutd.get(Calendar.MONTH))
							&& (today2.get(Calendar.YEAR) == checkOutd.get(Calendar.YEAR))) {
						if (book.checkedOut == false) {
							book.checkedOut = true;
							b2 = book;
							break;
						} else {
							already2 = true;
							break;
						}
					}
				}
				if (already2 == true) {
					System.out.println("This room has already Checked Out!\n");
				} else if (b2 == null) {
					System.out.println("This room is not Checking Out today!\n");
				} else {
					b2.checkOut = today2.getTime();
					bookings.get(roomOut - 1).remove(b2);
					System.out.println("Room : " + roomOut + ",with Booking Code : " + b2.bookingCode
							+ ", Checked Out with final check : " + b2.check + "�" + ", at :" + b2.checkOut + "\n");
				}
				break;
			case 4:
				int bookCode1 = -1;
				do {
					System.out.println("Insert the code of the booking you want to cancel : (press 0 to cancel the procedure) ");
					try {
						bookCode1 = sc.nextInt();
					} catch (InputMismatchException e) {
						System.out.println(ANSI_RED + "Insert an Integer!" + ANSI_RESET);
						sc.nextLine();
						continue;
					}
				} while (bookCode1 < 0);
				if (bookCode1 == 0) {
					break;
				}
				int counter13 = 0;
				boolean f4 = false;
				for (Room room : Room.getRooms()) {
					for (Booking booking : bookings.get(counter13)) {
						if (booking.bookingCode == bookCode1) {
							f4 = true;
							if (booking.checkedIn == false) {
							System.out.println("Booking with code : " + booking.bookingCode + " in the room no." + booking.roomNumber + " and check : 0�" + " has been canceled!\n");
							bookings.get(counter13).remove(booking);
							} else {
								Date canceledCheckOut = new Date();
								booking.nights = ((Math.abs((int) (booking.checkIn.getTime() - canceledCheckOut.getTime()))) / (1000 * 60 * 60 * 24)) + 1;
								booking.check = booking.computeCheck(booking.buffet);
								System.out.println("Booking with code : " + booking.bookingCode + " in the room no." + booking.roomNumber + " and check : " + booking.check + "�" + " has been canceled!\n");
								bookings.get(counter13).remove(booking);
							}
							break;
						}
					}
					if (f4) {
						break;
					}
					counter13++;
				}
				if (f4 == false) {
					System.out.println("No booking with this code!\n");
				}
				break;
			case 5:
				for (;;) {
					boolean f3 = false;
					int choose2 = 0;
					do {
						System.out.println("Search for bookings :");
						System.out.println("1. See all ongoing bookings");
						System.out.println("2. Search by room number");
						System.out.println("3. Search by booking code");
						System.out.println("4. See all the bookings");
						System.out.println("5. Go back");
						System.out.print("Selection : ");
						try {
							choose2 = sc.nextInt();
						} catch (InputMismatchException e) {
							System.out.println(ANSI_RED + "Insert an Integer!" + ANSI_RESET);
							sc.nextLine();
							continue;
						}
						if (choose2 != 1 && choose2 != 2 && choose2 != 3 && choose2 != 4 && choose2 != 5) {
							System.out.println(ANSI_RED + "Insert 1 or 2 or 3 or 4 or 5 " + ANSI_RESET);
						}
					} while (choose2 != 1 && choose2 != 2 && choose2 != 3 && choose2 != 4 && choose2 != 5);
					System.out.println();
					switch (choose2) {
					case 1:
						int counter6 = 0;
						int counter7 = 0;
						for (Room room : Room.getRooms()) {
							for (Booking booking : bookings.get(counter7)) {
								if (booking.checkedIn) {
									counter6++;
									System.out.println("Room : " + room.getRoomNumber());
									System.out.println("Booking code : " + booking.bookingCode + "\tCheck In date : "
											+ booking.checkIn + ", (checked in : " + booking.checkedIn + ")"
											+ "\tCheck Out date : " + booking.checkOut + ", (checked out : "
											+ booking.checkedOut + ")" + "\tBuffet : " + booking.buffet + "\nCheck : "
											+ booking.check + "�" + "\t Extra expenses : " + booking.extraExpenses
											+ "�\n");
									break;
								}
							}
							counter7++;
						}
						if (counter6 == 0) {
							System.out.println("No ongoing bookings!\n");
						}
						break;
					case 2:
						int counter8 = 0;
						int roomNum = -1;
						do {
							if (counter8 > 0) {
								System.out.println(ANSI_RED + "No room with this number, try again!" + ANSI_RESET);
							}
							System.out.println("Insert the room's number (press 0 to cancel the procedure) :  ");
							try {
								roomNum = sc.nextInt();
							} catch (InputMismatchException e) {
								System.out.println(ANSI_RED + "Insert an Integer!" + ANSI_RESET);
								sc.nextLine();
								counter8 = 0;
								continue;
							}
							counter8++;
						} while (roomNum < 0 || roomNum > Room.getRooms().size());
						if (roomNum == 0) {
							break;
						}
						int counter9 = 0;
						System.out.println("Room : " + Room.getRooms().get(roomNum - 1).getRoomNumber());
						for (Booking booking : bookings.get(roomNum - 1)) {
							counter9++;
							System.out.println("Booking code : " + booking.bookingCode + "\tCheck In date : "
									+ booking.checkIn + ", (checked in : " + booking.checkedIn + ")"
									+ "\tCheck Out date : " + booking.checkOut + ", (checked out : "
									+ booking.checkedOut + ")" + "\tBuffet : " + booking.buffet + "\nCheck : "
									+ booking.check + "�" + "\t Extra expenses : " + booking.extraExpenses + "�\n");

						}
						if (counter9 > 0) {
							System.out.println("Total bookings for Room no."
									+ Room.getRooms().get(roomNum - 1).getRoomNumber() + " : " + counter8 + "\n");
						} else {
							System.out.println("No bookings for Room no."
									+ Room.getRooms().get(roomNum - 1).getRoomNumber() + "\n");
						}
						break;
					case 3:
						int bookCode2;
						do {
							System.out.println("Insert the booking's code (press 0 to cancel the procedure) :  ");
							try {
								bookCode2 = sc.nextInt();
							} catch (InputMismatchException e) {
								System.out.println(ANSI_RED + "Insert an Integer!" + ANSI_RESET);
								sc.nextLine();
								continue;
							}
							break;
						} while (true);
						if (bookCode2 == 0) {
							break;
						}
						int counter10 = 0;
						boolean f2 = false;
						for (Room room : Room.getRooms()) {
							for (Booking booking : bookings.get(counter10)) {
								if (booking.bookingCode == bookCode2) {
									f2 = true;
									System.out.println("Booking code : " + booking.bookingCode);
									System.out.println("Room : " + room.getRoomNumber() + "\tCheck In date : "
											+ booking.checkIn + ", (checked in : " + booking.checkedIn + ")"
											+ "\tCheck Out date : " + booking.checkOut + ", (checked out : "
											+ booking.checkedOut + ")" + "\tBuffet : " + booking.buffet + "\nCheck : "
											+ booking.check + "�" + "\t Extra expenses : " + booking.extraExpenses
											+ "�\n");
									break;
								}
							}
							if (f2) {
								break;
							}
							counter10++;
						}
						if (f2 == false) {
							System.out.println("No booking with this code!\n");
						}
						break;
					case 4:
						int counter12 = 0;
						for (Room room : Room.getRooms()) {
							int counter11 = 0;
							System.out.println("Room : " + room.getRoomNumber());
							for (Booking booking : bookings.get(counter12)) {
								counter11++;
								System.out.println("Booking code : " + booking.bookingCode + "\tCheck In date : "
										+ booking.checkIn + ", (checked in : " + booking.checkedIn + ")"
										+ "\tCheck Out date : " + booking.checkOut + ", (checked out : "
										+ booking.checkedOut + ")" + "\tBuffet : " + booking.buffet + "\nCheck : "
										+ booking.check + "�" + "\t Extra expenses : " + booking.extraExpenses + "�\n");

							}
							if (counter11 > 0) {
								System.out.println("Total bookings for Room no." + room.getRoomNumber() + " : "
										+ counter11 + "\n");
							} else {
								System.out.println("No bookings for Room no." + room.getRoomNumber() + "\n");
							}
							counter12++;
						}
						break;
					case 5:
						f3 = true;
						break;
					}
					if (f3) {
						break;
					}
				}
			}
			continue;
		}
	}

	public static ArrayList<ArrayList<Booking>> getBookings() {
		return bookings;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public Date getCheckIn() {
		return checkIn;
	}

}