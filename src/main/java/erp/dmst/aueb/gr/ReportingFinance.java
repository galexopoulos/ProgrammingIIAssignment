package main.java.erp.dmst.aueb.gr;

import java.util.Scanner;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;

public class ReportingFinance { //This class must be called once a month.
	
		private static double electricity; //logariasmos DEH
		private static double waterSupply ; //logarismos EYDAP
		private static double phone_internetSupply; //logariasmos tilefonou kai internet
		private static double wages = 0;
		private static double cashAvailableAfterTaxes; //after taxes and Dividends
		private static double cashAvailableBeforeTaxes;
		private static double loan = 0;
		private static final double RATE = 0.3; //shareholders rate of payment 
		private static int months = 0;
		private static double [] fixInv;
		private static double [] urgInv;
		private static final double TAXRATE = 0.23;
		
		public static double [] ProfLosBase = new double[12];
		
		public ReportingFinance(double electricity, double waterSupply, double phone_internetSupply) {
			super();
			ReportingFinance.electricity = electricity;
			ReportingFinance.waterSupply = waterSupply;
			ReportingFinance.phone_internetSupply = phone_internetSupply;
			months++;
		}
		
		public static void setYearAndBase() {
			for(;;) {
				if (months == 11) {
					months = 0;
					cashAvailableBeforeTaxes = 0;
					cashAvailableAfterTaxes = 0;
					for (int i = 0; i < ProfLosBase.length; i++) {
						ProfLosBase[i] = 0;
					}
				}
			}
		}
		
		public static void getMenu() throws InputMismatchException, Exception{
			Scanner sc = new Scanner(System.in);
			System.out.println("------------------ MENU FINANCE ---- " + getDate() +" --------"
					+ "\n 1. Proceeds - Εxpenses - Profits - Losses" 
					+ "\n 2. Loans "
					+ "\n 3. TAX liabilities "
					+ "\n 4. Connect to Investments menu. Username and password required."
					+ "\n 5. Create new Investor."
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
						 System.out.println("This months income is:" + getProceeds());
						 getExpenses();
						 if (cashAvailableBeforeTaxes >= 0 ) {
							 System.out.println("Profits are: " + cashAvailableBeforeTaxes);
						 }else {
							 System.out.println("Losses are: " + cashAvailableBeforeTaxes);
						 }
						 System.out.println("To continue press one of the suggested numbers.");
					}else if(ans == 2) {
						 if (loan > 0 ) {
							 System.out.println("Current loans are: " + loan);
						 }else {
							 System.out.println("There are no Loans to show"); 
						 }
						 System.out.println("To continue press one of the suggested numbers.");
					}else if (ans == 3) {
						 taxLiabilities();
						 System.out.println("To continue press one of the suggested numbers.");
					}else if(ans == 4) {
						ShareHolders.getShareHoldersMenu(); 
						System.out.println("To continue press one of the suggested numbers.");
					}else if(ans == 5) {
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
		
		public static double getProceeds() { //theloume kapos na tsekaroume oti allazei o minas gia na midenizonte ta miniea esoda
			if(Booking.getChecks > 0) {
				return Booking.getChecks;
			}else return 0;
		}
		
		public static String getExpenses() { //This method is to used once a month
			electricity = 0; //every month are 0
			waterSupply = 0;
			phone_internetSupply = 0;
			
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
				System.out.println("Error");
			}
			double totalexpenses = waterSupply + electricity + phone_internetSupply + wages + sumFixInv + sumUrgInv;
			return "Total expenses of" + getCurrentMonth(month) + " of "+ year
					+ "are " + totalexpenses + " "
							+ "\n Water Supply bill:  " + waterSupply 
							+ "\n Electrisity bill:   " + electricity 
							+ "\n Telecommunications: " + phone_internetSupply
							+ "\n Employees payments: " + wages
							+ "\n Inventory expenses: " + sumFixInv + sumUrgInv
							+ "\n -------------------------------------------";  //needs more data.
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
			double sumOfFixedExpenses = waterSupply + phone_internetSupply + electricity;// + George's Markou Buffet expenses - pending 
			double sumOfIncome = getProceeds(); 
			for(ReportingHR i : ReportingHR.hr) {
				wages += i.getWage();
			}
			cashAvailableBeforeTaxes =  sumOfIncome - sumOfFixedExpenses  - wages;
			ProfLosBase [months] = cashAvailableBeforeTaxes;
		}
		
		public static String getDate() {
			SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy, HH:mm:ss");
			Date date = new Date(System.currentTimeMillis());
			return formatter.format(date);
		}
		
		public static double dividends() { //this divides the profits to the shareholders depending on the rate 
			if(cashAvailableAfterTaxes > 0 ) {						 
				double totalDiv = cashAvailableAfterTaxes * RATE;
				double monthlyDividends = totalDiv / ShareHolders.shareholders.size() ;
				return monthlyDividends;
			}else {
				return 0;
			}
		}
		
		public static void taxLiabilities() {
			try {
				if(cashAvailableBeforeTaxes > 0 ) {
					Date date = new Date(System.currentTimeMillis());
					Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Athens"));
					cal.setTime(date);
					int month = cal.get(Calendar.MONTH);
					cashAvailableAfterTaxes = cashAvailableBeforeTaxes - cashAvailableBeforeTaxes * TAXRATE;
					System.out.println("Hotel's TAX liablities of " + month + "are: " + cashAvailableBeforeTaxes * TAXRATE);
				}else {
					System.out.println("All Tax liabilities are sutisfied.");
				}
			}catch(Exception e){
				System.err.println("Error.");
			}
		}
		
		public static void setloans(double loan, double payment) {
			ReportingFinance.loan = loan;
			int currentmonth = 0;
			if(currentmonth < months) {
				ReportingFinance.loan -= payment;
			}
			currentmonth++;
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
