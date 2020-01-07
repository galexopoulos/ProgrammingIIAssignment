package main.java.erp.dmst.aueb.gr;
import java.util.Date;
import java.util.Scanner;

public class Reporting {

	public static void Menu() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("---REPORTING MENU---"
							+"\n  1. HR"
							+"\n  2. FINANCE"
							+"\n  3. INVENTORY"
							+"\n  4. CRM ");
		boolean confirm = true ;
		do {
			try {
				int dep = sc.nextInt();
				switch(dep) {
					case 1:
						//if (averageWage() == 0 ) {
						//	System.out.println("No employees available");
						//}//getAverageSex();
						break;
					case 2:  ReportingFinance.getMenu();
						break;
					case 3:
							break;
					case 4: break;
				}
				confirm = false;
			}catch(Exception e) {
				System.err.println("Do you mean: \n 1 or 2 or 3 or 4");
			}
		}while(confirm);
	}
}
