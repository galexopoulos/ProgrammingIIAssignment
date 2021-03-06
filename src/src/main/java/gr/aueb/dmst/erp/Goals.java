package src.main.java.gr.aueb.dmst.erp;
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
		boolean flag;
		do {
				flag = false;
			System.out.println("--------------------------------- GOALS MENU ---------------------------------------"
					+ "\n If you want to compare current year with the results from 3 years ago press: 0 "
					+ "\n If you want to compare current year with the results from 2 years ago press: 1 "
					+ "\n If you want to compare current year with the previous year press:            2 "
					+ "\n If you want to compare current year with current year's goals press:         3 "
					+ "\n If you want to exit press:                                                  -1 ");
			
			int ans ;
			try {
				ans = sc.nextInt();
				if(ans == -1){
					System.out.println("Reconnecting you to Finance main menu...");
				}else if(ans == 0 || ans == 1 || ans == 2 || ans == 3){
					flag = true;
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
				}else {
					System.err.println("Please insert a valid answer.");
				}
			}catch(Exception e) {
				System.err.println("Wrong input, please try again.");
				System.out.println();
				flag = true;
				sc.nextLine();
			}
		}while(flag);
	}
	
	public static void comparison(int ans, int month) { // ans einai to year pou thelei na sigrinei
		
		try {
			if(ans == 0) {
				if (ReportingFinance.ProfLosBase[month] > ReportingFinance.ProfLosPrev3[month] ) {
					System.out.println("Profits of current year are More than 3 years ago by: " + 
							(ReportingFinance.ProfLosBase[month] - ReportingFinance.ProfLosPrev3[month]));
					System.out.println();
				}else {
					System.out.println("Profits of current year are Less than 3 years ago by: " + 
							(ReportingFinance.ProfLosBase[month] - ReportingFinance.ProfLosPrev3[month]));
					System.out.println();
				}
			}else if (ans == 1) {
				if (ReportingFinance.ProfLosBase[month] > ReportingFinance.ProfLosPrev2[month] ) {
					System.out.println("Profits of current year are More than 2 years ago by: " + 
							(ReportingFinance.ProfLosBase[month] - ReportingFinance.ProfLosPrev2[month]));
					System.out.println();
				}else {
					System.out.println("Profits of current year are Less than 2 years ago by: " + 
							(ReportingFinance.ProfLosBase[month] - ReportingFinance.ProfLosPrev2[month]));
					System.out.println();
				}
			}else if (ans == 2) {
				if ( ReportingFinance.ProfLosBase[month] > ReportingFinance.ProfLosPrev[month]) {
					System.out.println("Profits of current year are More than 1 years ago by: " + 
							(ReportingFinance.ProfLosBase[month] - ReportingFinance.ProfLosPrev[month]));
					System.out.println();
				}else {
					System.out.println("Profits of current year are Less than 1 years ago by: " + 
							(ReportingFinance.ProfLosBase[month] - ReportingFinance.ProfLosPrev[month]));
					System.out.println();
				}
			}else if (ans == 3) {
				System.out.println("Currents profits: " + ReportingFinance.ProfLosBase[month] + "\nGoal: " + 
									goals[month]);
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
}

