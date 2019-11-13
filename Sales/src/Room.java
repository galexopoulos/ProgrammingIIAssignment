import java.util.ArrayList;

public class Room {
	private int floor;
	private int capacity;
	private double pricePerNight;
	private static int counter = 0;
	private int roomNumber;
	//private String[] extras;
	private static ArrayList<Room> rooms = new ArrayList<Room>();

	public Room(int floor, int capacity, double pricePerNight) {
		this.floor = floor;
		this.capacity = capacity;
		this.pricePerNight = pricePerNight;
		//this.extras = extras;
		this.roomNumber = ++counter;
		rooms.add(this);
		Booking.getBookings().add(new ArrayList());
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
