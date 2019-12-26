import java.util.Scanner;
/**
 * The main class of our program,
 * contains the main method.
 * @author Nikolaos Antonopoulos, Georgios Sideris
 *
 */
public class mainClass {
	
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
		while (true) {
					InputMenu();
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
				System.out.println("input an integer [1,7]");
				flag2 = true;
				in.next();

			} else {
				epilogh = in.nextInt();
				if (epilogh > 7 || epilogh < 1) {
					flag2 = true;
					System.out.println("input an integer [1,7]");
				} else {
					flag2 = false;
				}
				in.nextLine();
			}
		} while (flag2);
		switch (epilogh) {
		case 1:
			System.out.println("WELCOME TO HR DEPARTMENT");
			Hr_surface.toRun();
			//setHrCalledOnce(true);
			break;

		case 2:
			System.out.println("WELCOME TO SALES DEPARTMENT");
			Booking.getMenu();
			break;

		case 3:
			System.out.println("WELCOME TO FINANCE AND REPORTING DEPARTMENT");
			Reporting.Menu();
			break;
		case 4:
			System.out.println("WELCOME TO INVENTORY MANAGMENT DEPARTMENT");
			Inventory.invMenu();
			break;
		case 5:
			System.out.println("WELCOME TO CRM DEPARTMENT");
			question();
			break;
		case 6:
			System.out.println("Execute the weekly actions");// Happens every Monday
			toBeDoneEveryWeek();
			break;
		case 7:
			System.out.println("Execute the monthly actions");// Happens every month
			toBeDoneEveryMonth();
			break;
		default:
			System.err.println("Unrecognized option");
			break;
		}
	}

	
	/** shows the starting menu */
	public static void display_menu() {
		System.out.println(
				"1) HR DEPARTMENT \n2) SALES DEPARTMENT \n3) FINANCE AND REPORTING DEPARTMENT \n4) INVENTORY MANAGMENT DEPARTMENT \n5) CRM DEPARTMENT\n");
		System.out.print("-----------CHOOSE A NUMBER BETWEEN 1 AND 7------------ \n");
	}


	
	/** contains the actions we want to do at the start of every month */
	public static void toBeDoneEveryMonth() {
		Hr_surface.toBeDoneEveryMonth();
	}

	
	/** contains the actions we want to do at the start of every week (Monday) */
	public static void toBeDoneEveryWeek() {
		Hr_surface.toBeDoneEveryWeek();
	}
}


}
