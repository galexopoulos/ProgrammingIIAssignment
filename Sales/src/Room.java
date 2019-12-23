import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Room {
	static Scanner sc = new Scanner(System.in);
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	private int floor;
	private int capacity;
	private double pricePerNight;
	private static int counter = 0;
	private int roomNumber;
	private static ArrayList<Room> rooms = new ArrayList<Room>();

	public Room(int floor, int capacity, double pricePerNight) {
		this.floor = floor;
		this.capacity = capacity;
		this.pricePerNight = pricePerNight;
		this.roomNumber = ++counter; // room numbers start from 1 and add up when a new room is added
		rooms.add(this); // add a new room to the list
		Booking.getBookings().add(new ArrayList<Booking>()); // add a new row in the bookings list for every new room
	}

	public static void getMenu() {
		for (;;) {
			int choose = 0;
			do {
				System.out.println("Room's Menu :");
				System.out.println("1. See the rooms");
				try {
					choose = sc.nextInt();
				} catch (InputMismatchException e) {
					System.out.println(ANSI_RED + "Insert an Integer!" + ANSI_RESET);
					sc.nextLine();
					continue;
				}
				if (choose != 1) {
					System.out.println("Insert 1!");
				}
			} while (choose != 1);
			switch (choose) {
			case 1:
				for (Room room : rooms) {
					System.out.println("Room no." + room.roomNumber + "\n" + "floor : " + room.floor + "\tcapacity : "
							+ room.capacity + "\tprice per night : " + room.pricePerNight + "€\n");
				}
				break;
			}
			continue;
		}

	}

	public int getFloor() {
		return floor;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public double getPricePerNight() {
		return pricePerNight;
	}

	public static ArrayList<Room> getRooms() {
		return rooms;
	}

	public int getCapacity() {
		return capacity;
	}

}
