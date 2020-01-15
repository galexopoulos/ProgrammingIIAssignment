package src.main.java.gr.aueb.dmst.erp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Arxeia_perakis {
	public static void grapsimo_reporting() {
		// fixed.Inventory
		try {

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("customer.ser"));
			out.writeObject(ReportingClients.coustomerBase);
			out.close();
			out.close();
		} catch (Exception i) {
			i.printStackTrace();
		}
		// urgent.Inventory

		try {

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("shareholders.ser"));
			out.writeObject(ShareHolders.shareholders);
			out.close();
			out.close();
		} catch (Exception i) {
			i.printStackTrace();
		}
	}

	public static void parsimo_reporting() {
		try {
			FileInputStream fis = new FileInputStream("customer.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			ReportingClients.setCoustomerBase((ArrayList) ois.readObject()); // Inventory.setFixedInventory((ArrayList)
																				// ois.readObject());
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
		try {
			FileInputStream fis = new FileInputStream("shareholders.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			ShareHolders.setShareholders((ArrayList) ois.readObject());
			// Inventory.setFixedInventory((ArrayList) ois.readObject());
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

	public static void Emfanish() {
		for (int i = 0; i < ReportingClients.coustomerBase.size(); i++) {
			System.out.println(ReportingClients.coustomerBase.get(i));
		}
	}

	public static void Emfanish1() {
		for (int i = 0; i < ShareHolders.shareholders.size(); i++) {
			System.out.println(ShareHolders.shareholders.get(i));
		}
	}

}