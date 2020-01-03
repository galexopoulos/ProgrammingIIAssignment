package main.java.erp.dmst.aueb.gr;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Reporting {

	public static void Menu()  {
		Scanner sc = new Scanner(System.in);
		System.out.println("---------------REPORTING MENU---- " + getDate() + " -----------"
							+"\n  1. HR"
							+"\n  2. FINANCIAL AND INVESTMENTS SERVICES"
							+"\n  3. CLIENTS DATA "
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
					System.out.println("To continue press one of the suggested numbers.");
					System.out.println("-----------------------");
				}else if(dep == 2) {
					try {
						ReportingFinance.getMenu();
					}catch(Exception e) {
						
					}
					System.out.println("To continue press one of the suggested numbers.");
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
				}else if(dep == 0) {
					confirm = false;
				}else if(dep > 3 || dep < 0) {
					System.err.println("Do you mean: \n 1 or 2 or 3");
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
}
