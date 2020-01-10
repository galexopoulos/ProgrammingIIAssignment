package main.java.erp.dmst.aueb.gr;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * @author Ioannis Alexios Perakis
 * 
 * This class works as a play maker of the whole Reporting and finance department. Its only purpose is to call methods 
 * from other classes to display intel. After calling Menu(): By pressing 1 the class displays HR analytics by pressing
 * 2 connects to finance menu (ReportingFinance.java) by pressing 3 displays customers analytics and by pressing 0 
 * returns to the home page.
 *
 */

public class Reporting {

	public static void Menu()  {
		loadobjects();
		Scanner sc = new Scanner(System.in);
		System.out.println("---------------REPORTING MENU---- " + getDate() + " -----------"
							+"\n  1. HR"
							+"\n  2. FINANCIAL AND INVESTMENTS SERVICES"
							+"\n  3. CLIENTS DATA "
							+"\n  4. ADD FINANCIAL MONTHLY PAYMENTS "
							+"\n  0. EXIT"
							+"\n------------------------------------------------------------------");
		boolean confirm = true ;
		do {
			try {
				int dep = sc.nextInt();
				if(dep == 1) {
					System.out.println();
					System.out.println("-------------------- Welcome to HR reporting!-------------------");
					System.out.println();
					if (ReportingHR.hr.isEmpty()) {
						System.out.println("NO employees available");
					}else {
						System.out.println("The Average wage is:" + ReportingHR.averageWage());
						System.out.println();
						Distribution_Stars.getDistributionOfWages();
						System.out.println();
					}
					System.out.println("To continue press one of the suggested numbers of the homepage.");
					System.out.println("-----------------------");
				}else if(dep == 2) {
					try {
						ReportingFinance.getMenu();
					}catch(Exception e) {
						
					}
					System.out.println("To continue press one of the suggested numbers of the homepage.");
				}else if(dep == 3) {
					System.out.println();
					System.out.println("-------------- Welcome to CLIENTS DATA reporting! ----------------");
					System.out.println();
					ReportingClients.averageSatisfaction();
					Distribution_Stars.getDistributionOfSatisfaction();
					System.out.println();
					ReportingClients.averageDaysOfStay();
					Distribution_Stars.getDistributionOfDays();
					System.out.println();
					ReportingClients.averagePayment();
					System.out.println();
					ReportingClients.percentageAlone_Family_Friends();
					System.out.println();
					Distribution_Stars.getDistributionOfReasons();
					System.out.println("---------------------------------");
					System.out.println("To continue press one of the suggested numbers of the homepage.");
				}else if (dep == 4) {
					System.out.println("Please insert electricity bill: ");
					double el = sc.nextDouble();
					System.out.println("Please insert water supply bill: ");
					double wa = sc.nextDouble();
					System.out.println("Please insert phone and internetSupply bill: ");
					double ph = sc.nextDouble();
					new ReportingFinance(el,wa,ph);
					System.out.println("---------------------------------");
					System.out.println("To continue press one of the suggested numbers of the homepage.");
				}else if(dep == 0) {
					mainClass.InputMenu();
				}else if(dep > 4 || dep < 0) {
					System.err.println("Do you mean: \n 1, 2, 3 ?");
				}
			}catch(InputMismatchException e) {
				System.out.println("Something went wrong, please try again.");
				System.out.println("Reconnecting to homepage...");
				Reporting.Menu();
			}
		}while(confirm);
	}
	
	public static String getDate() {
		SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy, HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		return formatter.format(date);
	}
	
	static void loadobjects() {
		ReportingHR r1 = new ReportingHR(1200,"alexis","perakis");
		ReportingHR r2 = new ReportingHR(1200,"nikos","papadopoulos");
		ReportingHR r3 = new ReportingHR(1200,"giorgos","moatsos");
		ReportingHR r4 = new ReportingHR(1200,"babis","markou");
		ReportingHR r5 = new ReportingHR(1150,"dimitris","tsoutrelis");
		ReportingHR r6 = new ReportingHR(1150,"alexandros","sideris");
		ReportingHR r7 = new ReportingHR(2000,"niki","pavlopoulou");
		ReportingHR r8 = new ReportingHR(2000,"sofia","kafouni");
		ReportingHR r9 = new ReportingHR(2100,"stefania","terzopoulou");
		ReportingHR r10 = new ReportingHR(780,"katerina","patera");
		
		ReportingFinance f = new ReportingFinance(41,51,51); // orizoume tous logarismous tou mina
		
		
		ReportingClients c1 = new ReportingClients("alexis","perakis",5,340,0,17,19,2);
		ReportingClients c2 = new ReportingClients("nikos","antonopoulos",3,50,2,11,12,0);
		ReportingClients c3 = new ReportingClients("dimitris","dimitropoulos",5,540,2,7,11,2);
		ReportingClients c4 = new ReportingClients("notis","aggelos",5,240,2,23,27,1);
		ReportingClients c5 = new ReportingClients("christina","kardami",5,3300,3,20,29,1);
		ReportingClients c6 = new ReportingClients("kostantina","epithetopoulou",4,300,1,15,17,1);
		ReportingClients c7 = new ReportingClients("onomas","spetsiopoulos",4,290,0,17,19,2);
		ReportingClients c8 = new ReportingClients("dimitris","gianakopoulos",5,470,4,17,19,0);
		ReportingClients c9 = new ReportingClients("christina","katsaba",5,335,2,8,14,0);
		ReportingClients c10 = new ReportingClients("takis","tsoukalas",5,535,2,8,14,0);
		ReportingClients c11 = new ReportingClients("Vaggelis","Marinakis",5,475,2,8,14,0);
		ReportingClients c12 = new ReportingClients("Dimitris","Giannakopoulos",5,455,5,8,14,0);
		
		ShareHolders sh1 = new ShareHolders("alexis","alexis12345");
		ShareHolders sh2 = new ShareHolders("olympiakos","eisaistomialokatimagiko7");
		ShareHolders sh3 = new ShareHolders("username","passss12345");
		
		new Room(1,2,50);
		new Room(1,2,50);
		new Room(1,2,50);
		new Room(1,2,50);
		new Room(1,2,50);
		new Room(1,2,50);
		new Room(1,2,50);
		new Room(1,2,50);
		new Room(1,2,50);
		new Room(1,2,50);
		new Room(2,3,70);
		new Room(2,3,70);
		new Room(2,3,70);
		new Room(2,3,70);
		new Room(2,3,70);
		new Room(2,3,70);
		new Room(2,3,70);
		new Room(2,3,70);
		new Room(2,3,70);
		new Room(2,3,70);
		new Room(3,4,100);
		new Room(3,4,100);
		new Room(3,4,100);
		new Room(3,4,100);
		new Room(3,5,150);
		new Room(3,5,150);
		new Room(3,6,200);
		
		Date d1 = new Date();
		Date d2 = new Date();
		Date d3 = new Date();
		Date d4 = new Date();
		Date d5 = new Date();
		Date d6 = new Date();
		new Booking(d1,d2,2,true);
		new Booking(d3,d4,2,true);
		new Booking(d5,d6,2,true);
		
		Supplier s1 = new Supplier("s1", 1, "@1");
		Supplier s2 = new Supplier("s2", 2, "@2");
		Supplier s3 = new Supplier("s3", 3, "@3");
		Supplier s4 = new Supplier("s4", 4, "@4");
		Supplier s5 = new Supplier("s5", 5, "@5");
		Inventory i1 = new Inventory("z1", 100, 5, 70, s1, "Fixed", 0);
		Inventory i2 = new Inventory("z2", 150, 25, 20, s2, "Fixed", 0);
		Inventory i3 = new Inventory("z3", 200, 50, 20, s3, "Fixed", 0);
		Inventory i4 = new Inventory("z4", 120, 10, 10, s4, "Fixed", 0);
		Inventory i5 = new Inventory("z5", 125, 20, 30, s5, "Fixed", 0);
		Inventory iu1 = new Inventory("u1", 100, 5, 70, s1, "Urgent", 0);
		Inventory iu2 = new Inventory("u2", 150, 25, 20, s2, "Urgent", 0);
		Inventory iu3 = new Inventory("u3", 200, 50, 20, s3, "Urgent", 0);
		Inventory iu4 = new Inventory("u4", 120, 10, 10, s4, "Urgent", 0);
		Inventory iu5 = new Inventory("u5", 125, 20, 30, s5, "Urgent", 0);

		
	}
}
