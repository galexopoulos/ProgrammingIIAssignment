package src.main.java.gr.aueb.dmst.erp;
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
		Scanner sc = new Scanner(System.in);
		boolean confirm;
		do {
		System.out.println("---------------REPORTING MENU---- " + getDate() + " -----------"
							+"\n  1. HR"
							+"\n  2. FINANCIAL AND INVESTMENTS SERVICES"
							+"\n  3. CLIENTS DATA "
							+"\n  0. EXIT"
				+ "\n------------------------------------------------------------------");
			confirm = true;
			int dep = readInteger();
			if (dep == 1) {
				System.out.println();
				System.out.println("-------------------- Welcome to HR reporting!-------------------");
				System.out.println();
				if (Employee.Employees.isEmpty()) {
					System.out.println("NO employees available");
				} else {
					System.out.println("The Average wage is:" + averageWage());
					System.out.println();
					Distribution_Stars.getDistributionOfWages();
					System.out.println();
					System.out.println("If you want to have a brief of every employee's salary, Press 1"
							+ "\nTo return to main menu press 0 ");
					int chosen = 0;
					boolean flag2 = false;
					do {
						chosen = readInteger();
						if (chosen > 1 || chosen < 0) {
							flag2 = true;
							System.out.println("Input an integer [0,1]");
						} else {
							if (chosen == 1) {
								for (Employee i : Employee.Employees) {
									System.out.println("Employee's name: " + i.getFirstname() + ", Employee's id: "
											+ i.getEmployee_Id() + ", Employee's salary: " + i.getSalary());
								}
								System.out.println();
								System.out.println("Reconnecting you to the main menu");
								confirm = true;
								break;
							} else {
								System.out.println("Reconnecting you to the main menu");
								confirm = true;
								break;
							}

						}
					} while (flag2);
				}
			} else if (dep == 2) {
				try {
					ReportingFinance.getMenu();
				} catch (Exception e) {

				}
				System.out.println("To continue press one of the suggested numbers of the homepage.");
			} else if (dep == 3) {
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
				/*
				 * }else if (dep == 4) { System.out.println("Please insert electricity bill: ");
				 * double el = 0; double wa = 0; double ph = 0; boolean flag2 = false; do { if
				 * (!sc.hasNextInt()) { System.out.println("Input an integer "); flag2 = true;
				 * sc.next();
				 * 
				 * } else { el = sc.nextInt(); flag2 = false; } sc.nextLine(); } while (flag2);
				 * System.out.println("Please insert water supply bill: "); do { if
				 * (!sc.hasNextInt()) { System.out.println("Input an integer "); flag2 = true;
				 * sc.next();
				 * 
				 * } else { wa = sc.nextInt(); flag2 = false; } sc.nextLine(); } while (flag2);
				 * System.out.println("Please insert phone and internetSupply bill: "); do { if
				 * (!sc.hasNextInt()) { System.out.println("Input an integer "); flag2 = true;
				 * sc.next();
				 * 
				 * } else { ph = sc.nextInt(); flag2 = false; } sc.nextLine(); } while (flag2);
				 * new ReportingFinance(el,wa,ph);
				 * System.out.println("---------------------------------"); System.out.
				 * println("To continue press one of the suggested numbers of the homepage.");
				 */
			} else if (dep == 0) {
				confirm = false;
			} else if (dep > 3 || dep < 0) {
				System.err.println("Do you mean: \n 0, 1, 2, 3 ?");
			}
		} while (confirm);
	}
	
	public static String getDate() {
		SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy, HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		return formatter.format(date);
	}
	
	
	public static double averageWage() {
		double S = 0;
		for (Employee i : Employee.Employees) {
			S += i.getSalary();
		}
		if (!( Employee.Employees.isEmpty())){
			return S / Employee.Employees.size();
		}else { 
			return 800000;
		}
	}
	
	private static int readInteger() {
		Scanner sc = new Scanner(System.in);
		boolean inputFlag;
		int x = -1;
		do {
			inputFlag = false;
			String epilogh = sc.nextLine();

			try {
				x = Integer.parseInt(epilogh);
			} catch (NumberFormatException b) {
				inputFlag = true;
				System.out.println("Please insert an Integer.");
			}
		} while (inputFlag);
		return x;	
	}
	
	public static void toBeDoneEveryMonth() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("-----UPDATE BILLS----- \nPlease insert electricity bill: ");
		double el = 0;
		double wa = 0;
		double ph = 0;
		boolean flag2, inputFlag;

		do {
			inputFlag = false;
			String epilogh = sc.nextLine();

			try {
				el = Integer.parseInt(epilogh);
			} catch (NumberFormatException b) {
				inputFlag = true;
				System.out.println("Please insert an Integer.");
			}
		} while (inputFlag);
		System.out.println("Please insert water supply bill: ");
		do {
			inputFlag = false;
			String epilogh = sc.nextLine();

			try {
				wa = Integer.parseInt(epilogh);
			} catch (NumberFormatException b) {
				inputFlag = true;
				System.out.println("Please insert an Integer.");
			}
		} while (inputFlag);
		System.out.println("Please insert phone and internetSupply bill: ");
		do {
			inputFlag = false;
			String epilogh = sc.nextLine();

			try {
				ph = Integer.parseInt(epilogh);
			} catch (NumberFormatException b) {
				inputFlag = true;
				System.out.println("Please insert an Integer.");
			}
		} while (inputFlag);
		new ReportingFinance(el,wa,ph);
		System.out.println("---------------------------------");
	}
	//temporary 
	static void loadobjects() {
		
		ReportingFinance f = new ReportingFinance(41,51,51); // orizoume tous logarismous tou mina
		
		//(onoma , epitheto , satisfaction ( 0 - 5) , payment , familyalone (0 - 3) , daysofstey , reasonoftravel ( 0 - 4) , email , know)
		
		ReportingClients c1 = new ReportingClients("alexis", "Perakis", 5, 230, 2, 5, 1, "abc@gmail.com", 2);
		ReportingClients c2 = new ReportingClients("Giorgos", "Alexopoulos", 5, 150, 1, 3, 2, "abc1@gmail.com", 2);
		ReportingClients c3 = new ReportingClients("Dimitris", "gianakopoulos", 5, 210, 3, 5, 0, "dpg7000@gmail.com", 2);
		ReportingClients c4 = new ReportingClients("Takis", "Tsoukalas", 4, 70, 1, 4, 4, "takaros@gmail.com", 2);
		ReportingClients c5 = new ReportingClients("Vaggelis", "Marinakis", 4, 90, 2, 5, 0, "Vagelas7@gmail.com", 2);
		ReportingClients c6 = new ReportingClients("Stefania", "Terzopoulou", 4, 85, 3, 6, 0, "stefterzo@gmail.com", 2);
		ReportingClients c7 = new ReportingClients("Christina", "Maragou", 3, 530, 3, 2, 3, "chrismar@gmail.com", 2);
		ReportingClients c8 = new ReportingClients("Elena", "kouts", 3, 400, 3, 2, 2, "kouts@gmail.com", 2);
		ReportingClients c9 = new ReportingClients("Katerina", "Patera", 3, 350, 3, 3, 3, "katpat@gmail.com", 2);
		ReportingClients c10 = new ReportingClients("Marina", "Pepelasi",3, 125, 2, 4, 3, "mar@gmail.com", 2);
		ReportingClients c11 = new ReportingClients("Aggelina", "Kafouni",5, 100, 1, 2, 3, "aggee@gmail.com", 2);
		ReportingClients c12 = new ReportingClients("Eleni", "Vasilopoulou",5, 550, 1, 5, 1, "elenivas@gmail.com", 2);
		ReportingClients c13 = new ReportingClients("Katerina", "Tzima",5, 630, 2, 6, 4, "kat@gmail.com", 2);
		ReportingClients c14 = new ReportingClients("Panagiotis", "Aggelopoulos",5, 540, 1, 3, 4, "Olympiacos7@gmail.com", 2);
		ReportingClients c15 = new ReportingClients("Giorgos", "Aggelopoulos", 5, 130, 3, 5, 1,"hrilikosMagas7@gmail.com", 2);
		
		
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
