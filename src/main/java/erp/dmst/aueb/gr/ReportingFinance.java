package main.java.erp.dmst.aueb.gr;

import java.util.Scanner;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;

/**
 * 
 * @author Ioannis Alexis perakis & Aggeliki Nina Kafouni 
 *
 */

public class ReportingFinance { //This class must be called once a month.
	
		private static double electricity; //logariasmos DEH
		private static double waterSupply ; //logarismos EYDAP
		private static double phone_internetSupply; //logariasmos tilefonou kai internet
		private static double wages = 0;
		private static double cashAvailableAfterTaxes; //after taxes and Dividends
		private static double cashAvailableBeforeTaxes;
		private static int months = -1;
		private static double totalexpenses ;
		private static int years = 0;
		
		private static final double TAXRATE = 0.23;
		private static final double RATE = 0.3; //shareholders rate of payment 
		
		private static double [] fixInv;
		private static double [] urgInv;
		protected static double [] ProfLosBase = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; //new double[12];
		protected static double [] ProfLosPrev = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; //new double[12]; //pinakas me ta kerdoi zimies tou proigoumenou xronou
		protected static double [] ProfLosPrev2 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; //new double[12]; //pinakas me  ta kerdoi zimies prin apo dio xronia
		protected static double [] ProfLosPrev3 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; //new double[12];  // pinakas me  ta kerdoi zimies prin apo 3 xronia
		
		public ReportingFinance(double electricity, double waterSupply, double phone_internetSupply) {
			super();
			months++;
			ReportingFinance.electricity = electricity;
			ReportingFinance.waterSupply = waterSupply;
			ReportingFinance.phone_internetSupply = phone_internetSupply;
		}
		
		public static void setYearAndBase() {
			for(;;) {
				//if (years == 0 && months == 0) {
				//	for (int i = 0; i <= 11; i++) {
				//		ProfLosBase[i] = 0;
				//		ProfLosPrev[i] = 0;
				//		ProfLosPrev2[i] = 0;
				//		ProfLosPrev2[i] = 0;
				//	}
				//	years ++;
				//}
				if(months == 0) { //ianouarios
					cashAvailableAfterTaxes = 0;
					cashAvailableBeforeTaxes = 0;
				}else if (months == 1) { //fevrouarios
					cashAvailableAfterTaxes = 0;
					cashAvailableBeforeTaxes = 0;
				}else if (months == 2) {//martios
					cashAvailableAfterTaxes = 0;
					cashAvailableBeforeTaxes = 0;
				}else if (months == 3) {//aprilios
					cashAvailableAfterTaxes = 0;
					cashAvailableBeforeTaxes = 0;
				}else if (months == 4) {//maios
					cashAvailableAfterTaxes = 0;
					cashAvailableBeforeTaxes = 0;
				}else if (months == 5) {//iounios
					cashAvailableAfterTaxes = 0;
					cashAvailableBeforeTaxes = 0;
				}else if (months == 6) {//iouliios
					cashAvailableAfterTaxes = 0;
					cashAvailableBeforeTaxes = 0;
				}else if (months == 7) {//avgoustos
					cashAvailableAfterTaxes = 0;
					cashAvailableBeforeTaxes = 0;
				}else if (months == 8) {//septemvrios
					cashAvailableAfterTaxes = 0;
					cashAvailableBeforeTaxes = 0;
				}else if (months == 9) {//oktomvrios
					cashAvailableAfterTaxes = 0;
					cashAvailableBeforeTaxes = 0;
				}else if (months == 10) {//noemvrios
					cashAvailableAfterTaxes = 0;
					cashAvailableBeforeTaxes = 0;
				}else if (months == 11) {//dekemvrios
					cashAvailableAfterTaxes = 0;
					cashAvailableBeforeTaxes = 0;
				}else if (months == 12) {
					years ++;
					months = 0;
					cashAvailableAfterTaxes = 0;
					cashAvailableBeforeTaxes = 0;
					ProfLosPrev3 = ProfLosPrev2;
					ProfLosPrev2 = ProfLosPrev;
					ProfLosPrev = ProfLosBase;
					
					for (int i = 0; i <= 11; i++) {
						ProfLosBase[i] = 0;
					}
				}
			}
			
		}
		
		public static void getMenu() {
			Scanner sc = new Scanner(System.in);
			System.out.println("------------------ MENU FINANCE ---- " + getDate() +" --------"
					+ "\n 1. Proceeds - Εxpenses - Profits - Losses" 
					+ "\n 2. TAX liabilities "
					+ "\n 3. Connect to Investments menu. Username and password required."
					+ "\n 4. Create new Investor."
					+ "\n PRESS 0 TO EXIT."
					+ "\n ------------------------------------------------------------------"); 
			int ans = 0;
			boolean flag = true;
			do {
				try {
					ans = sc.nextInt();
					if (ans == 0) {
						flag = false;
					}else if(ans == 1){
						 profit_Losses();
						 System.out.println("This months income is:" + getProceeds());
						 getExpenses();
						 if (cashAvailableBeforeTaxes >= 0 ) {
							 System.out.println("Profits are: " + ProfLosBase[months]); 
						 }else {
							 System.out.println("Losses are: " + ProfLosBase[months]); 
						 }
						 System.out.println("To continue press one of the suggested numbers.");
					}else if (ans == 2) {
						 taxLiabilities();
						 System.out.println("To continue press one of the suggested numbers.");
					}else if(ans == 3) {
						ShareHolders.getShareHoldersMenu(); 
						System.out.println("To continue press one of the suggested numbers.");
					}else if(ans == 4) {
						setNewInvestor();
						getMenu();
						System.out.println("To continue press one of the suggested numbers.");
					}
					if(ans > 5 || ans < 0) {
						System.err.println("Wrong input, please try again.");
						System.out.println("To continue press one of the suggested numbers.");
					}
				}catch(InputMismatchException e) {
					System.out.println("Wrong input, please try again.");
					System.out.println("Reconnecting to homepage...");
					ReportingFinance.getMenu();
				}catch(Exception e) {
					System.out.println("Something went wrong.");
					System.out.println("Reconnecting to homepage...");
					ReportingFinance.getMenu();
				}
			}while(flag);
			Reporting.Menu();
		}
		
		public static double getProceeds() { 
			if(Booking.getChecks > 0) {
				return Booking.getChecks;
			}else return 0;
		}
		
		public static void getExpenses() { //This method is to used once per month
			
			Date date = new Date(System.currentTimeMillis());
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Athens"));
			cal.setTime(date);
			int month = cal.get(Calendar.MONTH);
			int year = cal.get(Calendar.YEAR);
			
			fixInv = Inventory.getInvFixFin();
			urgInv = Inventory.getInvUrgFin();
			double sumFixInv = 0;
			double sumUrgInv = 0;
			try {	
				for (int i = 0; i < fixInv.length; i++) {
					sumFixInv += fixInv[i];
				}
				for (int i = 0; i < urgInv.length; i++ ) {
					sumUrgInv += urgInv[i]; 
				}
			}catch(Exception w) {
				System.out.println("Error -getExpenses()");
			}
			totalexpenses = waterSupply + electricity + phone_internetSupply + wages + sumFixInv + sumUrgInv;
			System.out.println("Total expenses of " + getCurrentMonth(month -1 ) + " " + year
					+ " are " + totalexpenses + " "
							+ "\n Water Supply bill:  " + waterSupply 
							+ "\n Electrisity bill:   " + electricity 
							+ "\n Telecommunications: " + phone_internetSupply
							+ "\n Employees payments: " + wages
							+ "\n Inventory expenses: " + sumFixInv + sumUrgInv
							+ "\n -------------------------------------------");  
		}
		
		public static String getCurrentMonth(int minas) {
			String [] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
			return months[minas + 1]; 
		}
		
		public double getElectricity() {
			return electricity;
		}

		public void setElectricity(double electricity) {
			ReportingFinance.electricity = electricity;
		}

		public double getWaterSupply() {
			return waterSupply;
		}

		public void setWaterSupply(double waterSupply) {
			ReportingFinance.waterSupply = waterSupply;
		}

		public double getPhone_internetSupply() {
			return phone_internetSupply;
		}

		public void setPhone_internetSupply(double phone_internetSupply) {
			ReportingFinance.phone_internetSupply = phone_internetSupply;
		}
		
		public static void profit_Losses() {
			double sumOfExpenses = waterSupply + phone_internetSupply + electricity + totalexpenses;// + George's Markou Buffet expenses - pending 
			double sumOfIncome = getProceeds(); 
			for(ReportingHR i : ReportingHR.hr) {
				wages += i.getWage();
			}
			cashAvailableBeforeTaxes =  sumOfIncome - sumOfExpenses  - wages;
			cashAvailableAfterTaxes = cashAvailableBeforeTaxes - cashAvailableBeforeTaxes * TAXRATE;
			ProfLosBase[months] = cashAvailableAfterTaxes ;
		}
		
		public static String getDate() {
			SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy, HH:mm:ss");
			Date date = new Date(System.currentTimeMillis());
			return formatter.format(date);
		}
		
		public static double dividends() { //this divides the profits to the shareholders depending on the rate 
			if(cashAvailableAfterTaxes > 1000 ) {						 
				double totalDiv = cashAvailableAfterTaxes * RATE;
				double monthlyDividends = totalDiv / ShareHolders.shareholders.size() ;
				return monthlyDividends;
			}else {
				return 0;
			}
		}
		
		public static void taxLiabilities() {
			try {
				if(cashAvailableBeforeTaxes > 0  ) {
					Date date = new Date(System.currentTimeMillis());
					Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Athens"));
					cal.setTime(date);
					int month = cal.get(Calendar.MONTH);
					System.out.println("Hotel's TAX liablities of " + month + " are: " + cashAvailableBeforeTaxes * TAXRATE);
				}else {
					System.out.println("All Tax liabilities are sutisfied.");
				}
			}catch(Exception e){
				System.err.println("Error.");
			}
		}
		
		public static void setNewInvestor() {
			Scanner sc = new Scanner(System.in);
			for(;;) {
				System.out.println("Insert a username of your choice.");
				String usr = sc.nextLine();
				System.out.println("Insert a password of your choice.");
				String pass = sc.nextLine();
				System.out.println("Please reinsert your password.");
				String passvalid = sc.nextLine();
				if(pass.equals(passvalid)) {
					new ShareHolders(usr,pass);
					System.out.println("New investor stored succesful!"
							+ "Reconnecting to Finance menu... ");
					break;
				}
			}
		}
		
}
