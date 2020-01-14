package gr.aueb.dmst.erp;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * This class implements a booking.
 * 
 * @author Nikolas Moatsos
 */
public class Booking implements Serializable {
	/** Scanner used for input. */
	static Scanner sc = new Scanner(System.in);
	/** Check in date. */
	private Date checkIn;
	/** Check out date. */
	private Date checkOut;
	/** nights at the hotel. */
	private int nights;
	/** Static counter for the bookings codes. */
	private static int counter = 0;
	/** Booking's code. */
	private int bookingCode;
	/** Buffet offered or not boolean. */
	private boolean buffet;
	/** Room's number. */
	private int roomNumber;
	/** Room's extra expenses. */
	private double extraExpenses;
	/** Room's check. */
	private double check;
	/** Checked in or not boolean. */
	private boolean checkedIn;
	/** Checked out or not boolean. */
	private boolean checkedOut;
	/** Static array list with all the bookings. */
	private static ArrayList<ArrayList<Booking>> bookings = new ArrayList<ArrayList<Booking>>();
	/** Static total check for every booking. */
	protected static double getChecks = 0;

	/**
	 * This constructs bookings specified by check in, check out, room number,
	 * buffet, check, nights, extra expenses, booking code and add them to an array
	 * list.
	 * 
	 * @param checkIn    check in date
	 * @param checkOut   check out date
	 * @param roomNumber booking's room number
	 * @param buffet     if the booking offers buffet to the clients
	 */
	public Booking(Date checkIn, Date checkOut, int roomNumber, boolean buffet) {
		this.buffet = buffet;
		this.checkedIn = false;
		this.checkedOut = false;
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		long diff = checkOut.getTime() - checkIn.getTime();
		this.nights = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS); // count
																				// nights
																				// for a
																				// new
																				// booking
		bookingCode = ++counter; // booking codes start from 1 and add up when a new room is added
		this.extraExpenses = 0;
		this.check = computeCheck(buffet);
		getChecks += this.check;
		setBookings(); // add the booking to the list
	}

	/**
	 * This computes the check of the booking and returns the result.
	 * 
	 * @param buffet if the booking offers buffet to the clients
	 * @return final check
	 */
	public double computeCheck(boolean buffet) { // calculate the booking's check, bearing in mind buffet's charge (12)
		double check = 0;
		for (Room a : Room.getRooms()) {
			if (a.getRoomNumber() == roomNumber) {
				if (buffet) {
					check = (nights * a.getPricePerNight()) + (nights * 12);
				} else {
					check = nights * a.getPricePerNight();
					break;
				}
			}
		}
		return check;
	}

	/**
	 * This increases a booking's check during the stay.
	 * 
	 * @param x amount to add
	 */
	public void increaseCheck(double x) {
		extraExpenses += x;
		check += x;
		getChecks += x;
	}

	/**
	 * This sets the booking to the right place in the booking's array list.
	 */
	public void setBookings() { // add the booking to the list in the right row depending on room's number
		bookings.get(roomNumber - 1).add(this);
	}

	public static void setBookings(ArrayList<ArrayList<Booking>> bookings) {
		Booking.bookings = bookings;
	}

	/**
	 * This calculates the number of visitors in the hotel this moment and
	 * categorizes them to with buffet and without buffet.
	 * 
	 * @return array with total clients at the hotel and clients with buffet at the
	 *         hotel
	 */
	public static int[] getVisitors() {
		int sum[] = { 0, 0 };
		for (Room room : Room.getRooms()) {
			for (Booking booking : bookings.get(room.getRoomNumber() - 1)) {
				if (booking.checkedIn) {
					sum[0] += Room.getRooms().get(booking.roomNumber - 1).getCapacity();
					if (booking.buffet) {
						sum[1] += Room.getRooms().get(booking.roomNumber - 1).getCapacity();
					}
				}
				break;
			}
		}
		return sum;
	}

	@Override
	public String toString() {
		return "Booking [checkIn=" + checkIn + ", checkOut=" + checkOut + ", nights=" + nights + ", bookingCode="
				+ bookingCode + ", buffet=" + buffet + ", roomNumber=" + roomNumber + ", extraExpenses=" + extraExpenses
				+ ", check=" + check + ", checkedIn=" + checkedIn + ", checkedOut=" + checkedOut + "]";
	}

	/**
	 * This prints the available rooms and then returns the chosen room to book.
	 * 
	 * @param capacity room's capacity
	 * @param checkIn  check in date
	 * @param checkOut check out date
	 * @return room to book
	 */
	public static Room Availability(int capacity, Date checkIn, Date checkOut) {
		Room ret = null;
		for (;;) {
			int i = 0;
			boolean f1 = false;
			int r[] = new int[Room.getRooms().size()];
			for (Room room : Room.getRooms()) {
				if (room.getCapacity() == capacity) { // check for every room
					boolean f2 = true;
					for (Booking booking : bookings.get(i)) { // check for every room's booking
						if (!((checkIn.before(booking.checkIn) && checkOut.before(booking.checkIn))
								|| (checkIn.after(booking.checkOut) && checkOut.after(booking.checkOut)))) { // check if
																												// the
																												// check
																												// in
																												// and
																												// check
																												// out
																												// is
																												// not
																												// affected
																												// by an
																												// other
																												// booking
																												// and
																												// reverse
																												// the
																												// condition
																												// and
																												// as a
																												// result
																												// check
																												// which
																												// rooms
																												// don't
																												// suit
							f2 = false;
							break;
						}
					}
					if (f2) { // print available rooms
						System.out.println("Room's number : " + Room.getRooms().get(i).getRoomNumber() + "\tFloor :"
								+ Room.getRooms().get(i).getFloor() + "\tPrice per night :"
								+ Room.getRooms().get(i).getPricePerNight() + "€");
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
					System.out
							.println("Choose the room's number you want to book or press 0 to cancel the procedure : ");
					try {
						chosen = sc.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("Insert an Integer!");
						sc.nextLine();
						continue;
					}
					if (chosen > 0 && chosen <= r.length) {
						for (int j = 0; j < r.length; j++) { // for every room
							if ((j + 1 == chosen) && (r[j] == 1)) { // check if the chosen room is not one of the
																	// printed rooms
								f3 = true;
								break;
							}
							if (j + 1 == r.length) { // if the loop hasn't been broken this room is not available
								System.out.println("This room is not compatible to your requirements!");
							}
						}
					} else if (chosen == 0) { // cancel the procedure if you choose 0
						f3 = true;
					} else { // room number out of bounds
						System.out.println("This room does not exists!");
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

	/**
	 * This prints the booking's menu and then navigates the user to the
	 * functionalities offered.
	 */
	public static void getMenu() {
		boolean goBack = false;
		for (;;) {
			int choose1 = 0;
			do {
				System.out.println("---------------- BOOKINGS MENU ---- " + getDate() + " --------------");
				System.out.println("1) Search for availability and create a booking");
				System.out.println("2) Check In procedure");
				System.out.println("3) Check Out procedure");
				System.out.println("4) Cancel a booking");
				System.out.println("5) Search for bookings ");
				System.out.println("6) Exit");
				System.out.println("------------------- CHOOSE A NUMBER BETWEEN 1 AND 6 --------------------");
				try {
					choose1 = sc.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("Insert an Integer!");
					sc.nextLine();
					continue;
				}
				if (choose1 != 1 && choose1 != 2 && choose1 != 3 && choose1 != 4 && choose1 != 5 && choose1 != 6) {
					System.out.println("Insert 1 or 2 or 3 or 4 or 5 or 6! ");
				}
			} while (choose1 != 1 && choose1 != 2 && choose1 != 3 && choose1 != 4 && choose1 != 5 && choose1 != 6); // check
																													// menu
																													// input
			switch (choose1) {
			case 1:
				boolean f = false;
				int capacity = -1;
				int counter1 = 0;
				do {
					if (counter1 > 0) {
						System.out.println("No existing rooms with this capacity!");
					}
					try {
						System.out.println("Insert room's capacity (press 0 to cancel the procedure) : ");
						capacity = sc.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("Insert an Integer!");
						sc.nextLine();
						counter1 = 0;
						continue;
					}
					counter1++;
				} while (capacity < 0 || capacity > 6 || capacity == 1); // check capacity input or 0 to break
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
						System.out.println("Wrong Check In Date!");
					}
					System.out.println("Insert Check In date (press 0 to cancel the procedure) ,");
					do {
						try {
							System.out.println("Day : ");
							day1 = sc.nextInt();
						} catch (InputMismatchException e) {
							System.out.println("Insert an Integer!");
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
							System.out.println("Insert an Integer!");
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
							System.out.println("Insert an Integer!");
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
					today = new Date(); // set today's date
					checkIn = Cal1.getTime(); // set check in date from calendar to date
					Calendar Caltoday = Calendar.getInstance();
					Calendar CalcheckIn = Calendar.getInstance();
					// check if the check in is for today to accept the date
					CalcheckIn.setTime(checkIn);
					if ((Caltoday.get(Calendar.DAY_OF_MONTH) == CalcheckIn.get(Calendar.DAY_OF_MONTH))
							&& (Caltoday.get(Calendar.MONTH) == CalcheckIn.get(Calendar.MONTH))
							&& (Caltoday.get(Calendar.YEAR) == CalcheckIn.get(Calendar.YEAR))) {
						checkIn = Caltoday.getTime(); // set check in same as the today date to be accepted
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
						System.out.println("Wrong Check Out Date!");
					}
					f = false;
					System.out.println("Insert Check Out date (press 0 to cancel the procedure) ,");
					do {
						try {
							System.out.println("Day : ");
							day2 = sc.nextInt();
						} catch (InputMismatchException e) {
							System.out.println("Insert an Integer!");
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
							System.out.println("Insert an Integer!");
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
							System.out.println("Insert an Integer!");
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
				Room roomForBook = Availability(capacity, checkIn, checkOut); // check for availability
				if (roomForBook == null) { // if there was no room to be booked
					break;
				} else {
					sc.nextLine();
					boolean buffet;
					int counter3 = 0;
					do {
						if (counter3 > 0) { // check if there was a fail attempt for buffet answer
							System.out.println("Only valid answers are 'YES' and 'NO'!");
						}
						System.out.println("If buffet is included to the "
								+ "booking type 'YES' if it's not type 'NO' (Extra 12 € charge per day) : ");
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
					boolean sure;
					int counter14 = 0;
					do {
						if (counter14 > 0) { // check if there was a fail attempt for sure answer
							System.out.println("Only valid answers are 'YES' and 'NO'!");
						}
						System.out.println("Are you sure you want to create the booking for : " + checkIn + " and "
								+ checkOut + ". Type 'YES' or 'NO' : ");
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
						Booking a = new Booking(checkIn, checkOut, roomForBook.getRoomNumber(), buffet); // create the
																											// booking
						// a.check = CustomerEntry.(a.nights,a.check);
						System.out
								.println("The booking with code : " + a.bookingCode + " in the room no." + a.roomNumber
										+ " and check : " + a.check + "€" + "\n has been completed successfully!\n ");
					} else {
						System.out.println("No booking created!\n");
					}
					break;
				}
			case 2:
				int codeIn = -1;
				int counter4 = 0;
				do {
					if (counter4 > 0) { // check if there was a fail attempt for booking code
						System.out.println("Insert a code > 0 !");
					}
					System.out
							.println("Insert the code of the Checking In booking (press 0 to cancel the procedure) : ");
					try {
						codeIn = sc.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("Insert an Integer!");
						sc.nextLine();
						counter4 = 0;
						continue;
					}
					counter4++;
				} while (codeIn < 0); // check if the code is not < 0
				if (codeIn == 0) {
					break;
				}
				// set dates to calendar
				Calendar today1 = Calendar.getInstance();
				Calendar checkInd = Calendar.getInstance();
				Booking b1 = null;
				boolean already = false;
				boolean found = false;
				for (Room room : Room.getRooms()) {
					for (Booking book : bookings.get(room.getRoomNumber() - 1)) { // for every booking
						if (book.bookingCode == codeIn) { // check if it the same code
							checkInd.setTime(book.checkIn);
							if (((today1.get(Calendar.DAY_OF_MONTH) == checkInd.get(Calendar.DAY_OF_MONTH))
									&& (today1.get(Calendar.MONTH) == checkInd.get(Calendar.MONTH))
									&& (today1.get(Calendar.YEAR) == checkInd.get(Calendar.YEAR))
									&& (today1.get(Calendar.HOUR) == checkInd.get(Calendar.HOUR)))
									|| today1.getTime().after(book.checkIn)) { // check if its is the time
								// for check in
								if (book.checkedIn == false) { // check if this booking has already checked in
									book.checkedIn = true;
									b1 = book;
								} else {
									already = true;
								}
							}
							found = true;
							break;
						}
						if (found) {
							break;
						}
					}
				}
				if (already == true) {
					System.out.println("This booking has already Checked In!\n");
				} else if (found == false) {
					System.out.println("No booking with this code!\n");
				} else if (b1 == null) {
					System.out.println("It is not yet time or day for this booking to Check In!\n");
				} else {
					b1.checkIn = today1.getTime();
					System.out.println("Check In for Room no : " + b1.roomNumber + ", with Booking Code : "
							+ b1.bookingCode + ", completed succesfully at : " + b1.checkIn + "\n"); // complete check
																										// in
				}
				break;
			case 3:
				int codeOut = -1;
				int counter5 = 0;
				do {
					if (counter5 > 0) {
						System.out.println("Insert a code > 0 !");
					}
					System.out.println(
							"Insert the code of the Checking Out booking (press 0 to cancel the procedure) :  ");
					try {
						codeOut = sc.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("Insert an Integer!");
						sc.nextLine();
						counter5 = 0;
						continue;
					}
					counter5++;
				} while (codeOut < 0); // check room's number not to be out of
										// bounds or 0 to break
				if (codeOut == 0) {
					break;
				}
				// set dates to calendar
				Calendar today2 = Calendar.getInstance();
				Calendar checkOutd = Calendar.getInstance();
				Booking b2 = null;
				boolean already2 = false;
				boolean found2 = false;
				int roomNumFound = 0;
				for (Room room : Room.getRooms()) {
					for (Booking book : bookings.get(room.getRoomNumber() - 1)) { // for every room
						if (book.bookingCode == codeOut) { // check if the code is right
							checkOutd.setTime(book.checkOut);
							if ((today2.get(Calendar.DAY_OF_MONTH) == checkOutd.get(Calendar.DAY_OF_MONTH))
									&& (today2.get(Calendar.MONTH) == checkOutd.get(Calendar.MONTH))
									&& (today2.get(Calendar.YEAR) == checkOutd.get(Calendar.YEAR))) { // check if the
																										// date
																										// is
																										// the right to
																										// check
																										// out
								if (book.checkedOut == false) { // check if this booking has already checked out
									book.checkedOut = true;
									b2 = book;
								} else {
									already2 = true;
								}
							} else if (today2.getTime().after(book.checkOut)) { // check if the date is
																				// after the scheduled
																				// and increase check
								double oldCheck = book.check;
								Date laterCheckOut = new Date();
								long diff = laterCheckOut.getTime() - book.checkIn.getTime();
								book.nights = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
								book.check = book.computeCheck(book.buffet);
								getChecks -= oldCheck - book.check;
								if (book.checkedOut == false) { // check if this booking has already checked out
									book.checkedOut = true;
									b2 = book;
								} else {
									already2 = true;
								}
							}
							found2 = true;
							roomNumFound = room.getRoomNumber();
							break;
						}
						if (found2) {
							break;
						}
					}
				}
				if (already2 == true) {
					System.out.println("This booking has already Checked Out!\n");
				} else if (found2 == false) {
					System.out.println("No booking with this code!\n");
				} else if (b2 == null) {
					System.out.println("It is not yet time or day for this booking to Check Out!\n");
				} else {
					b2.checkOut = today2.getTime();
					bookings.get(roomNumFound - 1).remove(b2);
					System.out.println("Room : " + b2.roomNumber + ", with Booking Code : " + b2.bookingCode
							+ ", Checked Out with final check : " + b2.check + "€" + ", at :" + b2.checkOut + "\n");
				}
				break;
			case 4:
				int bookCode1 = -1;
				int counter14 = 0;
				do {
					if (counter14 > 0) {
						System.out.println("Wrong booking code!");
					}
					System.out.println(
							"Insert the code of the booking you want to cancel (press 0 to cancel the procedure) : ");
					try {
						bookCode1 = sc.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("Insert an Integer!");
						sc.nextLine();
						counter14 = 0;
						continue;
					}
					counter14++;
				} while (bookCode1 < 0); // check to insert a valid booking code > 0
				if (bookCode1 == 0) {
					break;
				}
				boolean f4 = false;
				boolean after = false;
				for (Room room : Room.getRooms()) { // check for every room
					for (Booking booking : bookings.get(room.getRoomNumber() - 1)) {
						if (booking.bookingCode == bookCode1) { // if its the right booking code
							f4 = true;
							if (booking.checkedIn == false) { // if the room has not checked in
								getChecks -= booking.check; // reduce the general earnings
								System.out.println("Booking with code : " + booking.bookingCode + " in the room no."
										+ booking.roomNumber + " and check : 0€" + " has been canceled!\n");
								bookings.get(room.getRoomNumber() - 1).remove(booking); // remove the booking
							} else { // if the room has checked in
								// calculate the reduced check
								double oldCheck = booking.check;
								Date canceledCheckOut = new Date();
								if (canceledCheckOut.before(booking.checkOut)) { // check if it can be canceled
									long diff = canceledCheckOut.getTime() - booking.checkIn.getTime();
									booking.nights = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;
									booking.check = booking.computeCheck(booking.buffet);
									getChecks -= oldCheck - booking.check;
									System.out.println("Booking with code : " + booking.bookingCode + " in the room no."
											+ booking.roomNumber + " and check : " + booking.check + "€"
											+ " has been canceled and ready to Check Out!\n");
									booking.checkOut = canceledCheckOut; // change checkOut;
								} else {
									after = true;
								}
							}
							break;
						}
					}
					if (f4) {
						break;
					}
				}
				if (after) {
					System.out.println(
							"This booking cannot be canceled because Check Out Date has passed.\nProceed to Check Out!");
				} else if (f4 == false) {
					System.out.println("No booking with this code!\n"); // no booking found with this code
				}
				break;
			case 5:
				boolean f3 = false;
				for (;;) {
					int choose2 = 0;
					do {
						System.out.println("-------------- SEARCH FOR BOOKINGS ---- " + getDate() + " -----------");
						System.out.println("1) See all ongoing bookings");
						System.out.println("2) Search by room number");
						System.out.println("3) Search by booking code");
						System.out.println("4) See all the bookings");
						System.out.println("5) Exit");
						System.out.println("------------------- CHOOSE A NUMBER BETWEEN 1 AND 5 --------------------");
						try {
							choose2 = sc.nextInt();
						} catch (InputMismatchException e) {
							System.out.println("Insert an Integer!");
							sc.nextLine();
							continue;
						}
						if (choose2 != 1 && choose2 != 2 && choose2 != 3 && choose2 != 4 && choose2 != 5) {
							System.out.println("Insert 1 or 2 or 3 or 4 or 5 ");
						}
					} while (choose2 != 1 && choose2 != 2 && choose2 != 3 && choose2 != 4 && choose2 != 5); // check
																											// selection
																											// input
					switch (choose2) {
					case 1:
						int counter6 = 0;
						int counter7 = 0;
						for (Room room : Room.getRooms()) {
							for (Booking booking : bookings.get(counter7)) {
								if (booking.checkedIn) { // check for ongoing bookings with the boolean checked in
									counter6++;
									System.out.println("Room : " + room.getRoomNumber());
									System.out.println("Booking code : " + booking.bookingCode + "\tCheck In date : "
											+ booking.checkIn + ", (checked in : " + booking.checkedIn + ")"
											+ "\tCheck Out date : " + booking.checkOut + ", (checked out : "
											+ booking.checkedOut + ")" + "\tBuffet : " + booking.buffet + "\nCheck : "
											+ booking.check + "€" + "\t Extra expenses : " + booking.extraExpenses
											+ "€\n");
									break;
								}
							}
							counter7++;
						}
						if (counter6 == 0) { // check if there were no ongoing bookings
							System.out.println("No ongoing bookings!\n");
						}
						break;
					case 2:
						int counter8 = 0;
						int roomNum = -1;
						do {
							if (counter8 > 0) {
								System.out.println("No room with this number, try again!");
							}
							System.out.println("Insert the room's number (press 0 to cancel the procedure) :  ");
							try {
								roomNum = sc.nextInt();
							} catch (InputMismatchException e) {
								System.out.println("Insert an Integer!");
								sc.nextLine();
								counter8 = 0;
								continue;
							}
							counter8++;
						} while (roomNum < 0 || roomNum > Room.getRooms().size()); // check room's number not to be out
																					// of bounds or 0 to break
						if (roomNum == 0) {
							break;
						}
						int counter9 = 0;
						System.out.println("Room : " + Room.getRooms().get(roomNum - 1).getRoomNumber());
						for (Booking booking : bookings.get(roomNum - 1)) { // for the chosen room
							counter9++;
							System.out.println("Booking code : " + booking.bookingCode + "\tCheck In date : "
									+ booking.checkIn + ", (checked in : " + booking.checkedIn + ")"
									+ "\tCheck Out date : " + booking.checkOut + ", (checked out : "
									+ booking.checkedOut + ")" + "\tBuffet : " + booking.buffet + "\nCheck : "
									+ booking.check + "€" + "\t Extra expenses : " + booking.extraExpenses + "€\n");

						}
						if (counter9 > 0) { // print the sum of the bookings
							System.out.println("Total bookings for Room no."
									+ Room.getRooms().get(roomNum - 1).getRoomNumber() + " : " + counter8 + "\n");
						} else {
							System.out.println("No bookings for Room no."
									+ Room.getRooms().get(roomNum - 1).getRoomNumber() + "\n");
						}
						break;
					case 3:
						int bookCode2 = -1;
						int counter13 = 0;
						do {
							if (counter13 > 0) {
								System.out.println("Wrong booking code!");
							}
							System.out.println("Insert the booking's code (press 0 to cancel the procedure) :  ");
							try {
								bookCode2 = sc.nextInt();
							} catch (InputMismatchException e) {
								System.out.println("Insert an Integer!");
								sc.nextLine();
								counter13 = 0;
								continue;
							}
							counter13++;
						} while (bookCode2 < 0); // check booking code input
						if (bookCode2 == 0) {
							break;
						}
						int counter10 = 0;
						boolean f2 = false;
						for (Room room : Room.getRooms()) { // for every room
							for (Booking booking : bookings.get(counter10)) {
								if (booking.bookingCode == bookCode2) { // check if its the right code
									f2 = true;
									System.out.println("Booking code : " + booking.bookingCode);
									System.out.println("Room : " + room.getRoomNumber() + "\tCheck In date : "
											+ booking.checkIn + ", (checked in : " + booking.checkedIn + ")"
											+ "\tCheck Out date : " + booking.checkOut + ", (checked out : "
											+ booking.checkedOut + ")" + "\tBuffet : " + booking.buffet + "\nCheck : "
											+ booking.check + "€" + "\t Extra expenses : " + booking.extraExpenses
											+ "€\n");
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
						for (Room room : Room.getRooms()) { // for every room
							int counter11 = 0;
							System.out.println("Room : " + room.getRoomNumber());
							for (Booking booking : bookings.get(counter12)) { // for every booking
								counter11++;
								System.out.println("Booking code : " + booking.bookingCode + "\tCheck In date : "
										+ booking.checkIn + ", (checked in : " + booking.checkedIn + ")"
										+ "\tCheck Out date : " + booking.checkOut + ", (checked out : "
										+ booking.checkedOut + ")" + "\tBuffet : " + booking.buffet + "\nCheck : "
										+ booking.check + "€" + "\t Extra expenses : " + booking.extraExpenses + "€\n");

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
					continue;
				}
				break;
			case 6:
				goBack = true;
				break;
			}
			if (goBack) {
				break;
			}
			continue;
		}
	}

	/**
	 * This sets the check in boolean parameter to true or false when its needed.
	 * 
	 * @param checkedIn check in situation
	 */
	public void setCheckedIn(boolean checkedIn) {
		this.checkedIn = checkedIn;
	}

	/**
	 * This returns the bookings list.
	 * 
	 * @return booking array list
	 */
	public static ArrayList<ArrayList<Booking>> getBookings() {
		return bookings;
	}

	/**
	 * This return the check out date.
	 * 
	 * @return check out date
	 */
	public Date getCheckOut() {
		return checkOut;
	}

	/**
	 * This returns the check in date.
	 * 
	 * @return check in date
	 */
	public Date getCheckIn() {
		return checkIn;
	}

	public static String getDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy, HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		return formatter.format(date);
	}

}
