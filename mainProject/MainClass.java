
public class MainClass {
	public static void main(String args[]) {	
		/*
		 * Thread tWeek = new Thread(new weekThread()); Thread tMonth = new Thread(new
		 * monthThread()); tWeek.start(); tMonth.start();
		 */
		while (true) {
					InputMenu();
		}
	}
	
	public static void InputMenu() {
		Scanner in = new Scanner(System.in);
		display_Menu();
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
			toRun();
			break;

		case 2:
			System.out.println("WELCOME TO SALES DEPARTMENT");
			question();
			break;

		case 3:
			System.out.println("WELCOME TO FINANCE AND REPORTING DEPARTMENT");
			Reporting.Menu();
			break;
		case 4:
			System.out.println("WELCOME TO INVENTORY MANAGMENT DEPARTMENT");
			question();
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

	public void display_menu() {
		System.out.println(
				"1) HR DEPARTMENT \n2) SALES DEPARTMENT \n3) FINANCE AND REPORTING DEPARTMENT \n4) INVENTORY MANAGMENT DEPARTMENT \n5) CRM DEPARTMENT\n");
		System.out.print("-----------CHOOSE A NUMBER BETWEEN 1 AND 7------------ \n");
	}

	public static void question() {

		System.out.println("Πατήστε 0 αν θελετε να γυρίσετε πίσω");
		System.out.println("Για να συνεχίσετε πατήστε Enter.");

		Scanner q = new Scanner(System.in);

		int i = q.nextInt();

	}

	public void toBeDoneEveryMonth() {
		Hr_surface.toBeDoneEveryMonth();
	}

	public void toBeDoneEveryWeek() {
		Hr_surface.toBeDoneEveryWeek();
	}
}
