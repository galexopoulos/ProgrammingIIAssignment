package main.java.erp.dmst.aueb.gr;
import java.util.Scanner;

public class ReportingFinance {
	
		private final double electricity = 4000; //logariasmos DEH
		private final double waterSupply = 4500; //logarismos EYDAP
		private final double phone_internetSupply = 650; //logariasmos tilefonou kai internet
		
		public static void getMenu() {
			Scanner sc = new Scanner(System.in);
			System.out.println("--MENU FINANCE--"
					+ "/n 1. Proceeds - Εxpenses - Profits - Losses"
					+ "/n 2. Loans "
					+ "/n 3. TAX liabilities ");
			
			boolean confirm = true;
			do {
				try {
					int ans = sc.nextInt();
					switch(ans) {
						case 1:
					}
				confirm = false;
					
				}catch(Exception e) {
					System.err.println("Wrong input, please try again.");
				}
			}while(confirm);
			
		}
		
		public double getProceeds() {
			// Booking.getchecks
		}
		
		public double getExpenses() {
			//
		}
		
		public double getProfit_losses() {
			//
		}

}
