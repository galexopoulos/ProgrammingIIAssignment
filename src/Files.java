import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Files {
	
	private double checksFromRooms;
	private String nameOfEmployee;
	private int EmployeeID;
	private double wage;

	public Files(double checks) throws IOException {
		
		FileWriter outFile = new FileWriter("Revenue.txt", true);
		PrintWriter out = new PrintWriter(outFile);
		
	}
	
	public Files(String name, int id, double wage) throws IOException {
		FileWriter outFile2 = new FileWriter("Employeeswages.txt", true);
		PrintWriter out2 = new PrintWriter(outFile2);
		
		for(;;) {
			//introex1
		}
	}
}
