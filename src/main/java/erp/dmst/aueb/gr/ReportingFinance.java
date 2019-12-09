package main.java.erp.dmst.aueb.gr;

import java.util.Scanner;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ReportingFinance { //This class must be called once a month.
	
		private static double electricity; //logariasmos DEH
		private static double waterSupply ; //logarismos EYDAP
		private static double phone_internetSupply; //logariasmos tilefonou kai internet
		private static double wages = 0;
		private static double cashAvailableAfterTaxes; //after taxes and Dividends
		private static double cashAvailableBeforeTaxes;
		private static double loan = 0;
		private static int rate; //shareholders rate of payment 
		private static int months = 1;
		
		//minieos pinakas me kerdi
		
		public ReportingFinance(double electricity, double waterSupply, double phone_internetSupply,
				int numberOfShareHolders, int rate	) {
			super();
			++months;
			ReportingFinance.electricity = electricity;
			ReportingFinance.waterSupply = waterSupply;
			ReportingFinance.phone_internetSupply = phone_internetSupply;
		}
		
		public static void getMenu() {
			Scanner sc = new Scanner(System.in);
			System.out.println("------------------ MENU FINANCE ---- " + getDate() +" --------"
					+ "\n 1. Proceeds - Εxpenses - Profits - Losses" 
					+ "\n 2. Loans "
					+ "\n 3. TAX liabilities "
					+ "\n 4. Connect to Investments menu. Username and password is needed.");
			
			boolean confirm = true;
			do {
				try {
					int ans = sc.nextInt();
					switch(ans) {
						case 1: // expenses and income  after taxes and dividends
					}
					
				confirm = false;
				}catch(Exception e) {
					System.err.println("Wrong input, please try again.");
				}
			}while(confirm);
			
		}
		
		public static double getProceeds() { //theloume kapos na tsekaroume oti allazei o minas gia na midenizonte ta miniea esoda
			if(Booking.getChecks > 0) {
				return Booking.getChecks;
			}else return 0;
		}
		
		public static String getExpenses() {
			double totalexpenses = waterSupply + electricity + phone_internetSupply + wages ;// + George's Markou Buffet expenses - pending 
			Date date = new Date(System.currentTimeMillis());
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Athens"));
			cal.setTime(date);
			int month = cal.get(Calendar.MONTH);
			int year = cal.get(Calendar.YEAR);
			return "Total expenses of" + getCurrentMonth(month) + " of "+ year
					+ "are " + totalexpenses + " "
							+ "\n Water Supply bill:  "+ waterSupply 
							+ "\n Electrisity bill:   "+ electricity 
							+ "\n Telecommunications: "+ phone_internetSupply
							+ "\n Employees payments: "+ wages
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
		}
		
		public static String getDate() {
			SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy, HH:mm:ss");
			Date date = new Date(System.currentTimeMillis());
			return formatter.format(date);
		}
		
		public static double dividends() { //this divides the profits to the shareholders depending on the rate 
			if(cashAvailableBeforeTaxes > 0 ) {						 
				double totalDiv = cashAvailableAfterTaxes * rate;
				double monthlyDividends = totalDiv / ShareHolders.shareholders.size() ;
				return monthlyDividends;
			}else {
				return 0;
			}
		}
		
		public static void setTaxLiabilities(int taxRate) {
			try {
				if(cashAvailableBeforeTaxes > 0 ) {
					Date date = new Date(System.currentTimeMillis());
					Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Athens"));
					cal.setTime(date);
					int month = cal.get(Calendar.MONTH);
					cashAvailableAfterTaxes = cashAvailableBeforeTaxes - cashAvailableBeforeTaxes * taxRate;
					System.out.println("Hotel's TAX liablities of " + month + "are: " + cashAvailableBeforeTaxes * taxRate );
				}
			}catch(Exception e){
				System.err.println();
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
		
		public static void distribution() {
			//pending
		}
		/** 
		* complete menu -pending
		* try catch for new methods
		* method to 0 income every month
		* */
}
