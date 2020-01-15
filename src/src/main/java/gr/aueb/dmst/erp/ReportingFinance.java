package src.main.java.gr.aueb.dmst.erp;

import java.util.Scanner;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;

/**
 * 
 * @author Ioannis Alexios Perakis & Aggeliki Nina Kafouni 
 * 
 * This class offers to the user the benefit to have a briefing of 1. Income, expenses, profit, losses by pressing 1
 * at the menu of this class 2.Be notified of any tax liabilities by pressing 2 at the main menu of this class 3. Log
 * into shareholders class for the dividends if occur and last but not least 4. Sing up as a new investor by pressing
 * 4 at the main menu of this class.
 *
 */

public class ReportingFinance { //This class must be called once a month.
		/**
		 * electricity, watersupply, phone_internetSupply and wages are expenses that are computed below.
		 */
		private static double electricity; //logariasmos DEH
		private static double waterSupply ; //logarismos EYDAP
		private static double phone_internetSupply; //logariasmos tilefonou kai internet
		private static double wages = 0; //misthoi
		private static double cashAvailableAfterTaxes; //after taxes and Dividends
		private static double cashAvailableBeforeTaxes;
		private static int months = -1;
		private static double totalexpenses ;
		
		private static final double TAXRATE = 0.23;
		private static final double RATE = 0.25; //shareholders rate of payment 
		/**
		 * fixInv and urgInv are expenses from Inventory.java and Supplier.java by Giorgos Markou
		 */
		private static double [] fixInv;
		private static double [] urgInv;
		protected static double [] ProfLosBase = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; 
		protected static double [] ProfLosPrev = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; //pinakas me ta kerdoi zimies tou proigoumenou xronou
		protected static double [] ProfLosPrev2 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};//pinakas me  ta kerdoi zimies prin apo dio xronia
		protected static double [] ProfLosPrev3 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};// pinakas me  ta kerdoi zimies prin apo 3 xronia
		
		public ReportingFinance(double electricity, double waterSupply, double phone_internetSupply) {
			super();
			months++;
			ReportingFinance.electricity = electricity;
			ReportingFinance.waterSupply = waterSupply;
			ReportingFinance.phone_internetSupply = phone_internetSupply;
		}
		
		/**
		 * Purpose of this method:
		 * Cash available before and after taxes must be 0 every month to start  counting the earning from the begging.
		 * So do the arrays of the profits of the year (and the ones before of the current year)  but after 12 months. 
		 * 
		 */
		
		public static void setYearAndBase() {
			for(;;) {
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
		
		/**
		 * Displays the main menu of this class.
		 */
		
		public static void getMenu() {
			Scanner sc = new Scanner(System.in);
			boolean flag = true;
			do {
			System.out.println("------------------ MENU FINANCE ---- " + getDate() +" --------"
					+ "\n 1. Proceeds - Εxpenses - Profits - Losses" 
					+ "\n 2. TAX liabilities "
					+ "\n 3. Connect to Investments menu. Username and password required."
					+ "\n 4. Create new Investor."
					+ "\n 5. Connect to Goals Department."
					+ "\n 6. To set goals."
					+ "\n PRESS 0 TO EXIT."
					+ "\n ------------------------------------------------------------------"); 
			
			int ans = 0;
				try {
					ans = sc.nextInt();
					if (ans == 0) {
						flag = false;
					}else if(ans == 1){
						 profit_Losses();
						 System.out.println("This months income is: " + getProceeds());
						 getExpenses();
						 if (cashAvailableBeforeTaxes >= 0 ) {
							 System.out.println("Profits are: " + ProfLosBase[months]); 
						 }else {
							 System.out.println("Losses are: " + ProfLosBase[months]); 
						 }
						 System.out.println("To continue press one of the suggested numbers.");
						 getMenu();
					}else if (ans == 2) {
						 taxLiabilities();
						 System.out.println("To continue press one of the suggested numbers.");
						 getMenu();
					}else if(ans == 3) {
						ShareHolders.getShareHoldersMenu(); 
						System.out.println("To continue press one of the suggested numbers.");
						getMenu();
					}else if(ans == 4) {
						setNewInvestor();
						System.out.println("To continue press one of the suggested numbers.");
						getMenu();
					}else if (ans == 5){
						Goals.menu();
					}else if(ans == 6 ) {
						int ja, feb, mar, ap, may, june, july, au, se, oc, no, dec;
						try {
							System.out.println("Set January Goal:");
							ja = sc.nextInt();
							System.out.println("Set February Goal:");
							feb = sc.nextInt();
							System.out.println("Set March Goal:");
							mar = sc.nextInt();
							System.out.println("Set April Goal:");
							ap = sc.nextInt();
							System.out.println("Set May Goal:");
							may = sc.nextInt();
							System.out.println("Set June Goal:");
							june = sc.nextInt();
							System.out.println("Set July Goal:");
							july = sc.nextInt();
							System.out.println("Set August Goal:");
							au = sc.nextInt();
							System.out.println("Set September Goal:");
							se = sc.nextInt();
							System.out.println("Set October Goal:");
							oc = sc.nextInt();
							System.out.println("Set November Goal:");
							no = sc.nextInt();
							System.out.println("Set December Goal:");
							dec = sc.nextInt();
							Goals.loadgoals(ja, feb, mar, ap, may, june, july, au, se, oc, no, dec);
						} catch (InputMismatchException e) {
							System.out.println();
						}
					}
					if(ans > 6 || ans < 0) {
						System.err.println("Wrong input, please try again.");
						System.out.println("To continue press one of the suggested numbers.");
					}
				}catch(InputMismatchException e) {
					System.out.println("Wrong input, please try again.");
					System.out.println("Reconnecting to homepage...");
					ReportingFinance.getMenu();
				}//catch(Exception e) {
					//System.out.println("Something went wrong.");
					//System.out.println("Reconnecting to homepage...");
					//ReportingFinance.getMenu();
				//}
			}while(flag);
		}
		
		/**
		 * 
		 * Connects and gets the income of the hotel from the bookings via Booking.java and Room.java
		 */
		
		public static double getProceeds() { 
			if(Booking.getChecks > 0) {
				return Booking.getChecks;
			}else return 0;
		}
		
		/**
		 * 
		 * Sums up the expenses of:
		 * 1)Connects and gets the expenses of the hotel via Inventory.java and Supplier.java
		 * 2)As well as the expenses of the hotel written above.
		 */
		
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
		
		/**
		 * Computation of the profits/losses
		 */
		
		public static void profit_Losses() {
			double sumOfExpenses = waterSupply + phone_internetSupply + electricity + totalexpenses;
			double sumOfIncome = getProceeds(); 
			for(Employee i : Employee.Employees) {
				wages += i.getSalary();
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
		
		/**
		 * Sets and makes new Investor object.
		 */
		
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
