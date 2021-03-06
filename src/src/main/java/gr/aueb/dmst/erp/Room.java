package src.main.java.gr.aueb.dmst.erp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class implements the rooms of the hotel.
 * 
 * @author Nikolas Moatsos
 */
public class Room implements Serializable {
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
		getRooms().add(this); // add a new room to the list
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
				System.out.println("------------------- ROOMS MENU ---- " + getDate() + " --------------");
				System.out.println("1) See the rooms");
				System.out.println("2) Exit");
				System.out.println("------------------- CHOOSE A NUMBER BETWEEN 1 AND 2 -------------------");
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
			switch (choose) {
			case 1:
				for (Room room : getRooms()) { // print for every room
					System.out.println("Room no." + room.roomNumber + "\n" + "floor : " + room.floor + "\tcapacity : "
							+ room.capacity + "\tprice per night : " + room.pricePerNight + "$\n");
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

	@Override
	public String toString() {
		return "Room [floor=" + floor + ", capacity=" + capacity + ", pricePerNight=" + pricePerNight + ", roomNumber="
				+ roomNumber + "]";
	}

	public static void setRooms(ArrayList<Room> rooms) {
		Room.rooms = rooms;
	}

	public static String getDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy, HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		return formatter.format(date);
	}

	public static int getCounter() {
		return counter;
	}

	public static void setCounter(int counter) {
		Room.counter = counter;
	}

	public static void grapsimocounter(int x) throws IOException {
		Scanner scanner = new Scanner(System.in);
		FileWriter outFile = new FileWriter("Roomcounter.txt", true);
		PrintWriter out = new PrintWriter(outFile);
		out.println(x);
		out.close();
		outFile.close();
	}

	/** Method used for getting the variable add from files. */
	public static int parsimocounter() {
		int[] tall = new int[2];
		int i = 0;
		File file = new File("Roomcounter.txt");

		try {
			Scanner s1 = new Scanner(file);
			while (s1.hasNextInt()) {
				tall[i] = s1.nextInt();
			}
			return tall[i];

		} catch (FileNotFoundException e) {
			System.out.println("Exception");
		}
		return 0;
	}

}