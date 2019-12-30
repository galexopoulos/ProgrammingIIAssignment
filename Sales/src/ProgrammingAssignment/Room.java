package ProgrammingAssignment;

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
					System.out.println(ANSI_RED + "Insert an Integer!" + ANSI_RESET);
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
