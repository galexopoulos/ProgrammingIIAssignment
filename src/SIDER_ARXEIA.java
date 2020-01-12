import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class SIDER_ARXEIA implements Serializable {
	public static void grapsimo_Employees() {
		try {

			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("C:\\SIDER\\sider.ser"));
			out.writeObject(Employee.Employees);
			out.close();
			out.close();
		} catch (Exception i) {
			i.printStackTrace();
		}

	}

	public static void parsimo_Employees() {
		try {
			FileInputStream fis = new FileInputStream("C:\\SIDER\\sider.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);

			Employee.Employees = (ArrayList) ois.readObject();

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
		for (int i = 0; i < Employee.Employees.size(); i++) {
			System.out.println(Employee.Employees.get(i));
		}
	}
}
