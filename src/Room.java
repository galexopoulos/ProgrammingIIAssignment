
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class implements the rooms of the hotel.
 * 
 * @author Nikolas Moatsos
 */
public class Room {
	/** Scanner used for input. */
	static Scanner sc = new Scanner(System.in);
	/** Floor number of the room. */
	private int floor;
	/** Room's capacity. */
	private int capacity;
	/** Room's price per night. */
	private double pricePerNight;
	/** Static counter for the rooms numbers. */
	private static int counter = 0;
	/** Room's number. */
	private int roomNumber;
	/** Static array list for every room. */
	private static ArrayList<Room> rooms = new ArrayList<Room>();

	/**
	 * This constructs rooms with specified capacity, floor, price per night, room
	 * number and adds them to an array list.
	 * 
	 * @param floor         The floor number of the room
	 * @param capacity      The maximum capacity of the room
	 * @param pricePerNight The cost of staying for one night in this room
	 */
	public Room(int floor, int capacity, double pricePerNight) {
		this.floor = floor;
		this.capacity = capacity;
		this.pricePerNight = pricePerNight;
		this.roomNumber = ++counter; // room numbers start from 1 and add up when a new room is added
		rooms.add(this); // add a new room to the list
		Booking.getBookings().add(new ArrayList<Booking>()); // add a new row in the bookings list for every new room
	}

	/**
	 * This prints the room's menu and then navigates the user to the
	 * functionalities offered.
	 */
	public static void getMenu() {
		boolean goBack = false;
		for (;;) {
			int choose = 0;
			do {
				System.out.println("Room's Menu :");
				System.out.println("1. See the rooms");
				System.out.println("2. Go Back");
				System.out.print("Selection : ");
				try {
					choose = sc.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("Insert an Integer!");
					sc.nextLine();
					continue;
				}
				if (choose != 1 && choose != 2) {
					System.out.println("Insert 1 or 2!");
				}
			} while (choose != 1 && choose != 2); // check selection input
			System.out.println();
			switch (choose) {
			case 1:
				for (Room room : rooms) { // print for every room
					System.out.println("Room no." + room.roomNumber + "\n" + "floor : " + room.floor + "\tcapacity : "
							+ room.capacity + "\tprice per night : " + room.pricePerNight + "€\n");
				}
				break;
			case 2:
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
	 * This returns the room's floor.
	 * 
	 * @return floor number
	 */
	public int getFloor() {
		return floor;
	}

	/**
	 * This returns the room's number.
	 * 
	 * @return room number
	 */
	public int getRoomNumber() {
		return roomNumber;
	}

	/**
	 * This returns the room's price per night.
	 * 
	 * @return room's price per night
	 */
	public double getPricePerNight() {
		return pricePerNight;
	}

	/**
	 * This returns the rooms list.
	 * 
	 * @return room array list
	 */
	public static ArrayList<Room> getRooms() {
		return rooms;
	}

	/**
	 * This returns the room's capacity
	 * 
	 * @return room's capacity
	 */
	public int getCapacity() {
		return capacity;
	}

}
