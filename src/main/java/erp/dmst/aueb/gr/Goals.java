package main.java.erp.dmst.aueb.gr;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * @author Ioannis Alexios Perakis, Aggeliki Nina Kafouni
 *With the use of this class the user can compare profits among the past 3 years. Moreover, user can set each 
 *year's goals and by pressing 3 can compare current year's profits and the goal of the month that have been chosen.
 */

public class Goals {
	
	static double [] goals = new double [12]; 
	static double [][] years = new double [4][12];
	
	public static void menu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("------------------- GOALS MENU ----- " + getDate() + " ------------"
				+ "\n1) Compare the current year with the results from 3 years ago"
				+ "\n2) Compare the current year with the results from 2 years ago"
				+ "\n3) Compare the current year with the previous year"
				+ "\n4) Compare the current year with the current year's goals"
				+ "\n5) Exit");
		System.out.println("------------------- CHOOSE A NUMBER BETWEEN 1 AND 5 --------------------");
		
		int ans ;
		boolean flag = true;
		do {
			try {
				ans = sc.nextInt();
				if(ans == 5){
					System.out.println("Reconnecting you to Finance main menu...");
					ReportingFinance.getMenu();
				}else if(ans == 1 || ans == 2 || ans == 3 || ans == 4){
					System.out.println("Please type the month (number 1-12) you want to compare: ");
					
					int ans2;
					boolean flag2 = true;
					do {
							if (!sc.hasNextInt()) {
								System.out.println("Please input an integer [1,12]");
								flag2 = true;
								sc.next();
							}else {
								ans2 = sc.nextInt();
								if (ans2 >= 1 && ans2 <= 12) {
									flag2 = false;
									comparison(ans,ans2);
								}else {
									System.out.println("Please input an integer [1,12]");
								}
							}
					}while(flag2);
					flag = false;
				}else {
					System.out.println("Please insert a valid answer.");
				}
			}catch(Exception e) {
				System.out.println("Wrong input, please try again.");
				System.out.println();
				menu();
			}
		}while(flag);
		menu();
	}
	
	public static void comparison(int ans, int month) { // ans einai to year pou thelei na sigrinei
		
		try {
			if(ans == 1) {
				if (ReportingFinance.ProfLosBase[month - 1] > ReportingFinance.ProfLosPrev3[month - 1] ) {
					System.out.println("Profits of current year are More than 3 years ago by: " + 
							(ReportingFinance.ProfLosBase[month - 1] - ReportingFinance.ProfLosPrev3[month - 1]));
					System.out.println();
				}else {
					System.out.println("Profits of current year are Less than 3 years ago by: " + 
							(ReportingFinance.ProfLosBase[month - 1] - ReportingFinance.ProfLosPrev3[month - 1]));
					System.out.println();
				}
			}else if (ans == 2) {
				if (ReportingFinance.ProfLosBase[month - 1] > ReportingFinance.ProfLosPrev2[month - 1] ) {
					System.out.println("Profits of current year are More than 2 years ago by: " + 
							(ReportingFinance.ProfLosBase[month - 1] - ReportingFinance.ProfLosPrev2[month - 1]));
					System.out.println();
				}else {
					System.out.println("Profits of current year are Less than 2 years ago by: " + 
							(ReportingFinance.ProfLosBase[month - 1] - ReportingFinance.ProfLosPrev2[month - 1]));
					System.out.println();
				}
			}else if (ans == 3) {
				if ( ReportingFinance.ProfLosBase[month - 1] > ReportingFinance.ProfLosPrev[month - 1]) {
					System.out.println("Profits of current year are More than 1 years ago by: " + 
							(ReportingFinance.ProfLosBase[month - 1] - ReportingFinance.ProfLosPrev[month - 1]));
					System.out.println();
				}else {
					System.out.println("Profits of current year are Less than 1 years ago by: " + 
							(ReportingFinance.ProfLosBase[month - 1] - ReportingFinance.ProfLosPrev[month - 1]));
					System.out.println();
				}
			}else if (ans == 4) {
				System.out.println("Currents profits: " + ReportingFinance.ProfLosBase[month - 1] + "\nGoal: " + 
									goals[month - 1]);
				System.out.println();
			}
		}catch(IndexOutOfBoundsException e) {
			System.out.println("ERROR.");
		}catch(Exception e) {
			System.out.println("RunTimeError.");
		}
		
	}
	
	/**
	 * 
	 *This method loads up the goals from the user for every month of the year.
 	 *
	 */
	
	public static void loadgoals(double ja, double feb, double mar, double ap, double may,
			double june, double july, double au, double se, double oc,
			double no, double dec) { 
		goals[0] = ja; //IANOUARIOS
		goals[1] = feb; //FEVROUARIOS
		goals[2] = mar; //MARTIOS
		goals[3] = ap; //APRILIOS
		goals[4] = may; //MAIOS
		goals[5] = june; //IOUNIOS
		goals[6] = july; //IOULIOS
		goals[7] = au; //AVGOUSTOS
		goals[8] = se; //SEPTEMVRIOS
		goals[9] = oc; //OKTOVRIOS
		goals[10] = no; //NOEMVRIOS
		goals[11] = dec; //DEKEMVRIOS
		
	}
	public static String getDate() {
		SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy, HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		return formatter.format(date);
	}
}
