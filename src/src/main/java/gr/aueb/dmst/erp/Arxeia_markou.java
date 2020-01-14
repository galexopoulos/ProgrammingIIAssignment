package src.main.java.gr.aueb.dmst.erp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Arxeia_markou {
	public static void grapsimo_Inventory() {
		// fixed.Inventory
		try {

			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("Inventory1.ser"));
			out.writeObject(Inventory.fixedInventory);
			out.close();
			out.close();
		} catch (Exception i) {
			i.printStackTrace();
		}
		// urgent.Inventory

		try {

			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("Inventory2.ser"));
			out.writeObject(Inventory.urgentInventory);
			out.close();
			out.close();
		} catch (Exception i) {
			i.printStackTrace();
		}
	}

	public static void parsimo_Inventory() {
		try {
			FileInputStream fis = new FileInputStream("Inventory1.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Inventory.setFixedInventory((ArrayList) ois.readObject());
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
		try {
			FileInputStream fis = new FileInputStream("Inventory2.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Inventory.setUrgentInventory((ArrayList) ois.readObject());
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
		for (int i = 0; i < Inventory.getFixedInventory().size(); i++) {
			System.out.println(Inventory.getFixedInventory().get(i));
		}
	}

}