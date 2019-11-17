import java.util.*;
import java.time.*;
public class check_main {

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);

		String daySchedule = "12:01-12:53,13:00-16:00,17:00-18:00";
		String[] a = new String[8];
		for (int i = 0; i < 6; i++) {
			a[i] = daySchedule;
		}
		
		 a[6]="20:00-";
		 a[7]="-3:00";
		 Calendar[][] weekShift = new Calendar[8][8];
		 try {
			 weekShift = Shift.createShift(a);
		}catch (Exception e) {
			System.err.println("mistake with the shift");
		}
		Calendar dailyTimes[] = new Calendar[8]; //shows the arrivals and departures of the employee in one day
		int dayCounter = 0;// shows how many indexes of the array dailyTimes are filled
		boolean checkedIn = false; //true when the employee has checked in but hasn't checked out
	    boolean flag=false;
	    int selection = 0;
	    Manager ab = new Manager("a", "a", "a", "b", 10,	null);
	    Manager ac = new Manager("c", "c", "c", "d", 10,	null);
	    Employee c =new Employee("b", "b", "b", "c", 10,	ab);
	    Hr_Director boss = new Hr_Director("boss", "no1","a","b",10,null);
	    c = new Manager(c);
	    System.out.println(c instanceof Manager);
	    boss.getMenu();
	    

	
	}
}
	