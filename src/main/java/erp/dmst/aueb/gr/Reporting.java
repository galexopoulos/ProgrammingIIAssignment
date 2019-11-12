package main.java.erp.dmst.aueb.gr;
import java.util.Scanner;

public class Reporting {

	public static void Menu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("---REPORTING MENU---"
							+"/n  1. HR"
							+"/n  2. FINANCE"
							+"/n  3. INVENTORY"
							+"/n  4. CRM ");
		boolean confirm = true ;
		do {
			try {
				int dep = sc.nextInt();
				switch(dep) {
					case 1:
						if (averageWage() == 0 ) {
							System.out.println("No employees available");
						}//getAverageSex();
						break;
					case 2:  ReportingFinance.getMenu();
						break;
					case 3:
							break;
					case 4: break;
				}
				confirm = false;
			}catch(Exception e) {
				System.err.println("Do you mean: /n 1 or 2 or 3 or 4");
			}
		}while(confirm);
	}

	public static double averageWage() {
		int S = 0;
		for (ReportingHR i : ReportingHR.hr) {
			S += i.getWage();
		}
		if (!(ReportingHR.hr.isEmpty())){
			return S/ReportingHR.hr.size();
		}else return 0;
	}

	public static void getAverageSex() {
		int sM = 0;
		int sW = 0;
		
		for (ReportingHR i : ReportingHR.hr) {
			if(i.getSex() == 0) {//MEN
				sM ++;
			}else sW++; //Women
		}
		
		if (!(ReportingHR.hr.isEmpty())){
			double perMen = (double) sM/ReportingHR.hr.size() * 100;
			double perWomen = (double) sW/ReportingHR.hr.size() * 100;
			System.out.printf("The percentage of Woman is %f and of men is %f",perMen,perWomen);
		}else System.out.println("No employees available.");
	}

	//public static void get


}
