import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Booking {
	static Scanner sc = new Scanner(System.in);
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

	public Booking(Date checkIn, Date checkOut, int roomNumber, boolean buffet) {
		this.buffet = buffet;
		this.checkedIn = false;
		this.checkedOut = false;
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.nights = ((Math.abs((int) (checkIn.getTime() - checkOut.getTime()))) / (1000 * 60 * 60 * 24)) + 1;
		bookingCode = ++counter;
		this.extraExpenses = 0;
		this.check = computeCheck(buffet);
		setBookings();
	}

	public double computeCheck(boolean buffet) {
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

	public void increaseCheck(double x) {
		extraExpenses += x;
		check += x;
	}

	public void setBookings() {
		bookings.get(roomNumber - 1).add(this);
	}

	public static Room Availability(int capacity, Date checkIn, Date checkOut) {
		Room ret = null;
		for (;;) {
			int i = 0;
			boolean f1 = false;
			int r[] = new int[Room.getRooms().size()];
			for (Room room : Room.getRooms()) {
				if (room.getCapacity() == capacity) {
					boolean f2 = true;
					for (Booking booking : bookings.get(i)) {
						if (!((checkIn.before(booking.checkIn) && checkOut.before(booking.checkIn))
								|| (checkIn.after(booking.checkOut) && checkOut.after(booking.checkOut)))) {
							f2 = false;
							break;
						}
					}
					if (f2) {
						System.out.println("Room's number : " + Room.getRooms().get(i).getRoomNumber() + "\tFloor :"
								+ Room.getRooms().get(i).getFloor() + "\tPrice per night :"
								+ Room.getRooms().get(i).getPricePerNight());
						f1 = true;
					}
					if (f2) {
						r[i] = 1;
					}
				}
				i++;
			}
			if (f1) {
				int x = 0;
				boolean f3;
				do {
					f3 = false;
					System.out
							.println("Choose the room's number you want to book or press 0 to cancel the procedure : ");
					try {
						x = sc.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("Insert Integer");
						sc.nextLine();
						continue;
					}
					if (x > 0 && x <= r.length) {
						for (int j = 0; j < r.length; j++) {
							if ((j + 1 == x) && (r[j] == 1)) {
								f3 = true;
								break;
							}
							if (j + 1 == r.length) {
								System.out.println("This room is not compatible to your requirements");
							}
						}
					} else if (x == 0) {
						f3 = true;
					} else if (x > r.length) {
						System.out.println("This room does not exists");
					}
				} while (f3 == false);
				if (x != 0) {
					ret = Room.getRooms().get(x - 1);
				} else {
					System.out.println("No booking created!");
				}
			} else {
				System.out.println("No rooms available for these dates!");
			}
			break;
		}
		return ret;
	}

	public static void getMenu() {
		for (;;) {
			int choose = 0;
			do {
				System.out.println("Booking's Menu :");
				System.out.println("1. Search for availability and create a booking");
				System.out.println("2. Check In procedure");
				System.out.println("3. Check Out procedure");
				System.out.println("4. See all bookings ");
				try {
					choose = sc.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("Insert an Integer");
					sc.nextLine();
					continue;
				}
				if (choose != 1 && choose != 2 && choose != 3 && choose != 4) {
					System.out.println("Insert 1 or 2 or 3 or 4 ");
				}
			} while (choose != 1 && choose != 2 && choose != 3 && choose != 4);
			switch (choose) {
			case 1:
				boolean f = false;
				int capacity = -1;
				int m = 0;
				do {
					if (m > 0) {
						System.out.println("No existing rooms with this capacity!");
					}
					try {
						System.out.println("Insert room's capacity (press 0 to cancel the procedure) : ");
						capacity = sc.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("Insert an Integer");
						sc.nextLine();
						continue;
					}
					m++;
				} while (capacity < 0 || capacity > 4 || capacity == 1);
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
				int i = 0;
				boolean breaks = false;
				do {
					if (i > 0) {
						System.out.println("Wrong Check In Date!");
					}
					System.out.println("Insert Check In date (press 0 to cancel the procedure) ,");
					do {
						try {
							System.out.println("Day : ");
							day1 = sc.nextInt();
						} catch (InputMismatchException e) {
							System.out.println("Insert an Integer");
							sc.nextLine();
							continue;
						}
						f = true;
					} while (f == false);
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
							System.out.println("Insert an Integer");
							sc.nextLine();
							continue;
						}
						f = true;
					} while (f == false);
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
							System.out.println("Insert an Integer");
							sc.nextLine();
							continue;
						}
						f = true;
					} while (f == false);
					if (year1 == 0) {
						breaks = true;
						break;
					}
					Cal1 = Calendar.getInstance();
					Cal1.set(Calendar.YEAR, year1);
					Cal1.set(Calendar.MONTH, month1 - 1);
					Cal1.set(Calendar.DAY_OF_MONTH, day1);
					Cal1.set(Calendar.HOUR_OF_DAY, 12);
					Cal1.set(Calendar.MINUTE, 0);
					Cal1.set(Calendar.SECOND, 0);
					Cal1.set(Calendar.MILLISECOND, 0);
					today = new Date();
					checkIn = Cal1.getTime();
					Calendar Caltoday = Calendar.getInstance();
					Calendar CalcheckIn = Calendar.getInstance();
					CalcheckIn.setTime(checkIn);
					if ((Caltoday.get(Calendar.DAY_OF_MONTH) == CalcheckIn.get(Calendar.DAY_OF_MONTH))
							&& (Caltoday.get(Calendar.MONTH) == CalcheckIn.get(Calendar.MONTH))
							&& (Caltoday.get(Calendar.YEAR) == CalcheckIn.get(Calendar.YEAR))) {
						checkIn = Caltoday.getTime();
						break;
					}
					i++;
				} while (checkIn.before(today));
				if (breaks) {
					break;
				}
				int day2 = 0;
				int month2 = 0;
				int year2 = 0;
				Date checkOut = null;
				Calendar Cal2;
				i = 0;
				do {
					if (i > 0) {
						System.out.println("Wrong Check Out Date!");
					}
					f = false;
					System.out.println("Insert Check Out date (press 0 to cancel the procedure) ,");
					do {
						try {
							System.out.println("Day : ");
							day2 = sc.nextInt();
						} catch (InputMismatchException e) {
							System.out.println("Insert an Integer");
							sc.nextLine();
							continue;
						}
						f = true;
					} while (f == false);
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
							System.out.println("Insert an Integer");
							sc.nextLine();
							continue;
						}
						f = true;
					} while (f == false);
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
							System.out.println("Insert an Integer");
							sc.nextLine();
							continue;
						}
						f = true;
					} while (f == false);
					if (year2 == 0) {
						breaks = true;
						break;
					}
					Cal2 = Calendar.getInstance();
					Cal2.set(Calendar.YEAR, year2);
					Cal2.set(Calendar.MONTH, month2 - 1);
					Cal2.set(Calendar.DAY_OF_MONTH, day2);
					Cal2.set(Calendar.HOUR_OF_DAY, 11);
					Cal2.set(Calendar.MINUTE, 0);
					Cal2.set(Calendar.SECOND, 0);
					Cal2.set(Calendar.MILLISECOND, 0);
					checkOut = Cal2.getTime();
					i++;
				} while (checkIn.after(checkOut));

				if (breaks) {
					break;
				}
				Room roomForBook = Availability(capacity, checkIn, checkOut);
				if (roomForBook == null) {
					break;
				} else {
					sc.nextLine();
					boolean buffet;
					int n = 0;
					do {
						if (n > 0) {
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
						n++;
					} while (true);
					System.out.println("Fullname : ");
					String fullname = sc.nextLine();
					System.out.println("Phone number : ");
					long PhoneNumber = sc.nextLong();
					sc.nextLine();
					System.out.println("Email : ");
					String email = sc.nextLine();
					new Booking(checkIn, checkOut, roomForBook.getRoomNumber(), buffet);
					System.out.println("The booking have been completed successfully ");
					break;
				}
			case 2:
				int roomIn = -1;
				int l = 0;
				do {
					if (l > 0) {
						System.out.println("No room with this number, try again!");
					}
					System.out.println("Insert the number of Checking In room (press 0 to cancel the procedure) : ");
					try {
						roomIn = sc.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("Insert an Integer");
						sc.nextLine();
						continue;
					}
					l++;
				} while (roomIn < 0 || roomIn > Room.getRooms().size());
				if (roomIn == 0) {
					break;
				}
				Calendar today1 = Calendar.getInstance();
				Calendar checkInd = Calendar.getInstance();
				Booking b1 = null;
				boolean already = false;
				for (Booking book : bookings.get(roomIn - 1)) {
					checkInd.setTime(book.checkIn);
					if ((today1.get(Calendar.DAY_OF_MONTH) == checkInd.get(Calendar.DAY_OF_MONTH))
							&& (today1.get(Calendar.MONTH) == checkInd.get(Calendar.MONTH))
							&& (today1.get(Calendar.YEAR) == checkInd.get(Calendar.YEAR))
							&& (today1.get(Calendar.HOUR) >= checkInd.get(Calendar.HOUR))) {
						if (book.checkedIn == false) {
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
					System.out.println("This room has already Checked In!");
				} else if (b1 == null) {
					System.out.println("It is not the time or day for this room to Check In!");
				} else {
					b1.checkIn = today1.getTime();
					System.out.println("Check In completed succesfully at : " + b1.checkIn);
				}

				break;
			case 3:
				int roomOut = -1;
				int k = 0;
				do {
					if (k > 0) {
						System.out.println("No room with this number, try again!");
					}
					System.out.println("Insert the number of Checking Out room (press 0 to cancel the procedure) :  ");
					try {
						roomOut = sc.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("Insert an Integer");
						sc.nextLine();
						continue;
					}
					k++;
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
					System.out.println("This room has already Checked Out!");
				} else if (b2 == null) {
					System.out.println("This room is not Checking Out today!");
				} else {
					b2.checkOut = today2.getTime();
					bookings.get(roomOut - 1).remove(b2);
					System.out.println("Room :" + roomOut + ", Checked Out with final check : " + b2.check + "€"
							+ ", at :" + b2.checkOut);
				}
				break;
			case 4:
				int j = 0;
				for (Room room : Room.getRooms()) {
					int s = 0;
					System.out.println("Room : " + room.getRoomNumber());
					for (Booking booking : bookings.get(j)) {
						s++;
						System.out.println("Booking code : " + booking.bookingCode + "\tCheck In date : "
								+ booking.checkIn + "\tCheck Out date : " + booking.checkOut + "\tCheck : "
								+ booking.check + "€" + "\t Extra expenses : " + booking.extraExpenses + "€");
					}
					if (s > 0) {
						System.out.println("Total bookings for Room no." + room.getRoomNumber() + " : " + s + "\n");
					} else {
						System.out.println("No bookings for Room no." + room.getRoomNumber() + "\n");
					}
					j++;
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
