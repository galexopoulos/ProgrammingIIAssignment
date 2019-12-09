import java.util.ArrayList;

public class Room {
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
		rooms.add(this); //add a new room to the list
		Booking.getBookings().add(new ArrayList<Booking>()); // add a new row in the bookings list for every new room
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
