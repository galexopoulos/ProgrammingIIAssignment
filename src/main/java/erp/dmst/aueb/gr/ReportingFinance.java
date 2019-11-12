package main.java.erp.dmst.aueb.gr;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportingFinance {
	
		private double electricity; //logariasmos DEH
		private double waterSupply ; //logarismos EYDAP
		private double phone_internetSupply; //logariasmos tilefonou kai internet
		
		public ReportingFinance(double electricity, double waterSupply, double phone_internetSupply) {
			super();
			this.electricity = electricity;
			this.waterSupply = waterSupply;
			this.phone_internetSupply = phone_internetSupply;
		}

		public static void getMenu() {
			Scanner sc = new Scanner(System.in);
			System.out.println("--MENU FINANCE--" + getDate()
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
		
		public static double getProceeds() {
			if(Booking.getChecks > 0) {
				return Booking.getChecks;
			}else return 0;
		}
		
		public double getElectricity() {
			return electricity;
		}

		public void setElectricity(double electricity) {
			this.electricity = electricity;
		}

		public double getWaterSupply() {
			return waterSupply;
		}

		public void setWaterSupply(double waterSupply) {
			this.waterSupply = waterSupply;
		}

		public double getPhone_internetSupply() {
			return phone_internetSupply;
		}

		public void setPhone_internetSupply(double phone_internetSupply) {
			this.phone_internetSupply = phone_internetSupply;
		}
		
		public double getProfit_losses() {
			double sumOfFixesExpenses = waterSupply + phone_internetSupply + electricity;
			//Pending HR wages.
			double sumOfIncome = getProceeds(); // + George's Markou Buffet expenses - pending 
			return sumOfFixesExpenses - sumOfIncome;
		}
		
		public static String getDate() {
			SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy, HH:mm:ss");
			Date date = new Date(System.currentTimeMillis());
			return formatter.format(date);
		}

}
