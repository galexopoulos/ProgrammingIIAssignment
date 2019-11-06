import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Booking {
	static Scanner sc = new Scanner(System.in);
	private Date checkIn;
	private Date checkOut;
	private int nights;
	private static int counter = 0;
	private int bookingCode;
//	private int typeOfBooking;
	private int roomNumber;
	private double extraExpenses;
	private double check;
	public static double getChecks = 0;
	private static ArrayList<ArrayList<Booking>> bookings = new ArrayList<ArrayList<Booking>>();

	public Booking(int year1, int month1, int day1, int year2, int month2, int day2, int roomNumber) {
//		this.typeOfBooking = typeOfBooking;
		this.roomNumber = roomNumber;
		this.check = computeCheck();
		Calendar Cal1 = Calendar.getInstance();
		Cal1.set(Calendar.YEAR, year1);
		Cal1.set(Calendar.MONTH, month1);
		Cal1.set(Calendar.DAY_OF_MONTH, day1);
		this.checkIn = Cal1.getTime();
		Calendar Cal2 = Calendar.getInstance();
		Cal2.set(Calendar.YEAR, year2);
		Cal2.set(Calendar.MONTH, month2);
		Cal2.set(Calendar.DAY_OF_MONTH, day2);
		this.checkOut = Cal2.getTime();
		this.nights = ((Math.abs((int) (checkIn.getTime() - checkOut.getTime()))) / (1000 * 60 * 60 * 24));
		bookingCode = counter++;
		this.extraExpenses = 0;
		setBookings();
	}

	public double computeCheck() {
		double check = 0;
		for (Room a : Room.getRooms()) {
			if (a.getRoomNumber() == roomNumber) {
				check = nights * a.getPricePerNight();
				break;
			}
		}
		getChecks += check;
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
			for (ArrayList<Booking> book : bookings) {
				if (Room.getRooms().get(i).getCapacity() == capacity) {
					boolean f2 = true;
					for (Booking b : book) {
						if ((checkIn.before(b.checkIn) && checkOut.before(b.checkIn))
								|| (checkIn.after(b.checkOut) && checkOut.after(b.checkOut))) {
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
					i++;
				}
			}
			if (f1) {
				System.out.println("Choose the room's number you want or press 0 to cancel the procedure : ");
				int x = sc.nextInt();
				if (x != 0) {
					ret = Room.getRooms().get(x - 1);
				}
			} else {
				System.out.println("No rooms available for these dates");
			}
			break;
		}
		return ret;
	}

	public static void getMenu() {
		for (;;) {
			
			System.out.println("Booking's Menu :");
			System.out.println("1. Search for availability and create a booking");
			System.out.println("2. Search for booking's information");
			System.out.println("3. CheckOut procedure");
			int choose = sc.nextInt();
			
			switch (choose) {
			case 1:
				
				System.out.println("Insert room's capacity : ");
				int capacity = sc.nextInt();
				System.out.println("Insert Check In date,");
				System.out.println("Day : ");
				int day1 = sc.nextInt();
				System.out.println("Month : ");
				int month1 = sc.nextInt();
				System.out.println("Year : ");
				int year1 = sc.nextInt();
				Calendar Cal1 = Calendar.getInstance();
				Cal1.set(Calendar.YEAR, year1);
				Cal1.set(Calendar.MONTH, month1);
				Cal1.set(Calendar.DAY_OF_MONTH, day1);
				Date checkIn = Cal1.getTime();
				System.out.println("Insert Check Out date,");
				System.out.println("Day : ");
				int day2 = sc.nextInt();
				System.out.println("Month : ");
				int month2 = sc.nextInt();
				System.out.println("Year : ");
				int year2 = sc.nextInt();
				Calendar Cal2 = Calendar.getInstance();
				Cal1.set(Calendar.YEAR, year2);
				Cal1.set(Calendar.MONTH, month2);
				Cal1.set(Calendar.DAY_OF_MONTH, day2);
				Date checkOut = Cal1.getTime();
				Room roomForBook = Availability(capacity, checkIn, checkOut);
				
				if (roomForBook == null) {
					break;
				} else {
					System.out.println("Fullname : ");
					String fullname = sc.nextLine();
					System.out.println("Phone number : ");
					long PhoneNumber = sc.nextInt();
					System.out.println("Email : ");
					String email = sc.nextLine();
					new Booking(year1, month1, day1, year2, month2, day2, roomForBook.getRoomNumber());
					System.out.println("The booking have been completed successfully ");
					break;
				}
			case 2:
				
				System.out.println("Insert room's number : ");
				int num = sc.nextInt();
				Date today1 = new Date();
				Booking b1 = null;
				for (Booking book : bookings.get(num - 1)) {
					if ((today1.after(book.checkIn)) && (today1.before(book.checkOut))) {
						b1 = book;
						break;
					}
				}
				if (b1 != null) {
					System.out.println("Room : " + num + "/nCheck In date : " + b1.checkIn + "/nCheck Out date : "
							+ b1.checkOut + "/nBooking code : " + b1.bookingCode + "/nCheck : " + b1.check + "�");
				} else {
					System.out.println("The room is not booked right now");
				}
			case 3:
				
				System.out.println("Insert the number of Checking Out room : ");
				int roomOut = sc.nextInt();
				Date today2 = new Date();
				Booking b2 = null ;
				for (Booking book : bookings.get(roomOut - 1)) {
					if (today2.equals(book.checkOut)) {
						b2 = book;
						break;
					}
				}
				if ( b2 == null) {
					System.out.println("This room is not Checking Out today!");
				} else {
					bookings.get(roomOut-1).remove(b2);
				}
				break;
			}
		}
	}

	public static ArrayList<ArrayList<Booking>> getBookings() {
		return bookings;
	}
}
