package gr.aueb.dmst.erp;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class MO_arxeia implements Serializable {
	public static void grapsimo_Room() {
		try {

			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("C:\\Mo\\room.ser"));
			out.writeObject(Room.getRooms());
			out.close();
			out.close();
		} catch (Exception i) {	
			i.printStackTrace();
		}
	}
	public static void parsimo_Room() {
		try {
			FileInputStream fis = new FileInputStream("C:\\Mo\\room.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Room.setRooms((ArrayList) ois.readObject());

			ois.close();
			fis.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Class not found");
			c.printStackTrace();
			return;
		}
	}
	public static void grapsimo_Booking() {
		try {

			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("C:\\Mo\\bookings.ser"));
			out.writeObject(Booking.getBookings());
			out.close();
			out.close();
		} catch (Exception i) {
			i.printStackTrace();
		}
	}
	public static void parsimo_Booking() {
		try {
			FileInputStream fis = new FileInputStream("C:\\Mo\\bookings.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);

			Booking.setBookings((ArrayList<ArrayList<Booking>>) ois.readObject());

			ois.close();
			fis.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Class not found");
			c.printStackTrace();
			return;
		}
	}
	public static void Emfanish_Booking() {
		for (int i = 0; i < Booking.getBookings().size(); i++) {
			System.out.println(Booking.getBookings().get(i));
		} 
	}
	public static void Emfanish_Room() {
		for (int i = 0; i < Room.getRooms().size(); i++) {
			System.out.println(Room.getRooms().get(i));
		} 
}
		

	}


