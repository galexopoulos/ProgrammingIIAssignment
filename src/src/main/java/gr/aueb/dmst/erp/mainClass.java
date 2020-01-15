package src.main.java.gr.aueb.dmst.erp;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
/**
 * The main class of our program,
 * contains the main method.
 * @author Nikolaos Antonopoulos, Georgios Sideris
 *
 */
public class mainClass {
	static boolean flag1=true;
	///** variable that shows if the hr menu has been called once WON'T be used at the presentation */
	//private static boolean hrCalledOnce = false;
	/**
	 * main method, calls the starting menu
	 * also contains some code related to threads, that won't be used at the presentation
	 * @param args
	 */
	
	
	public static void main(String[] args) {
		
		
		/*
		 * Thread tWeek = new Thread(new weekThread()); Thread tMonth = new Thread(new
		 * monthThread()); tWeek.start(); tMonth.start();
		 */
		try{
			SIDER_ARXEIA.parsimo_Employees();
			Employee.Employees.get(0);
			Arxeia_markou.parsimo_Inventory();
			MO_arxeia.parsimo_Booking();
			MO_arxeia.parsimo_Room();
			Employee.setAdd(Employee.parsimo());
			Arxeia_perakis.parsimo_reporting();
			while (flag1) {
						InputMenu();
			}
		}catch (IndexOutOfBoundsException npe) {
			Reporting.loadobjects();
			Inventory.loadobjects();
			SalesMenu.Sales_LoadObjects();
			Hr_surface.loadobjects();
	
			SIDER_ARXEIA.grapsimo_Employees();
			Arxeia_perakis.grapsimo_reporting();
			MO_arxeia.grapsimo_Booking();
			MO_arxeia.grapsimo_Room();
			Arxeia_markou.grapsimo_Inventory();
			try{
				Employee.grapsimo(Employee.getAdd());
			}catch (IOException ioe) {
				System.out.println ("Error with the files");
			}
			while (flag1) {
				InputMenu();
			}
		}


	}
	
	///** Getter of the variable hrCalledOnce, WON'T be used at the presentation */
	//public static boolean isHrCalledOnce() {
	//	return hrCalledOnce;
	//}

	///** Setter of the variable hrCalledOnce, WON'T be used at the presentation */
	//public static void setHrCalledOnce(boolean hrCalledOnce) {
	//	mainClass.hrCalledOnce = hrCalledOnce;
	//}

	/** user chooses one of the options from the starting menu */
		public static void InputMenu() {
		Scanner in = new Scanner(System.in);
		display_menu();
		int epilogh = 0;
		boolean flag2 = false;
		do {
			if (!in.hasNextInt()) {
				System.out.println("input an integer [1,8]");
				flag2 = true;
				in.next();

			} else {
				epilogh = in.nextInt();
				if (epilogh > 8 || epilogh < 1) {
					flag2 = true;
					System.out.println("input an integer [1,8]");
				} else {
					flag2 = false;
				}
				in.nextLine();
			}
		} while (flag2);
		switch (epilogh) {
		case 1:
			System.out.println("WELCOME TO HR DEPARTMENT!");
			Hr_surface.toRun();
			//setHrCalledOnce(true);
			break;

		case 2:
			System.out.println("WELCOME TO SALES DEPARTMENT!");
			SalesMenu.getMenu();	
			break;

		case 3:
			System.out.println("WELCOME TO FINANCE AND REPORTING DEPARTMENT!");
			Reporting.Menu();
			break;
		case 4:
			System.out.println("WELCOME TO INVENTORY MANAGMENT DEPARTMENT!");
			Inventory.invMenu();
			break;
		case 5:
			System.out.println("Execute the weekly actions");// Happens every Monday
			toBeDoneEveryWeek();
			break;
		case 6:
			System.out.println("Execute the monthly actions");// Happens every month
			toBeDoneEveryMonth();
			break;
		case 7:
			System.out.println("Goodbye");
			//perasma arxeia
			Arxeia_perakis.grapsimo_reporting();
			SIDER_ARXEIA.grapsimo_Employees();
			Arxeia_markou.grapsimo_Inventory();
			MO_arxeia.grapsimo_Booking();
			MO_arxeia.grapsimo_Room();
			try{
				Employee.grapsimo(Employee.getAdd());
			}catch (IOException ioe){
				System.out.println("Error with the files.");
			}
			flag1 = false;
			break;
		default:
			System.err.println("Unrecognized option");
			break;
		}
	}

	
	/** shows the starting menu */
	public static void display_menu() {
		System.out.println(
				"--------------------- HOTEL ERP ----------------------"
				+ "\n1) HR DEPARTMENT \n2) SALES DEPARTMENT \n3) FINANCE AND REPORTING DEPARTMENT \n4) INVENTORY MANAGMENT DEPARTMENT \n"
				+ "5) EXECUTE THE WEEKLY ACTIONS \n6) EXECUTE THE MONTHLY ACTIONS \n7) QUIT THE PROGRAM");
		System.out.print("-----------CHOOSE A NUMBER BETWEEN 1 AND 7------------ \n");
	}


	
	/** contains the actions we want to do at the start of every month */
	public static void toBeDoneEveryMonth() {
		Hr_surface.toBeDoneEveryMonth();
		Reporting.toBeDoneEveryMonth();
	}

	
	/** contains the actions we want to do at the start of every week (Monday) */
	public static void toBeDoneEveryWeek() {
		Hr_surface.toBeDoneEveryWeek();
	}
}