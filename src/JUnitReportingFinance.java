package main.java.erp.dmst.aueb.gr;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;

class JUnitReportingFinance {

	// Vgenei toso megali logo tou string pou kanei output
	// gia na litougrgisei xriastikan diorthoseis se 2 function apo Inventory 
	
	@Test
	public void test() {
		ReportingFinance a = new ReportingFinance(4500,5000,500);

        double electricity = 4500;
		double waterSupply = 5000;
		double phone_internetSupply = 500;
		double[] fixInv = Inventory.getInvFixFin();
		double[] urgInv = Inventory.getInvUrgFin();
		double sumFixInv = 0;
		double sumUrgInv = 0;
		double wages = 0;

		Date date = new Date(System.currentTimeMillis());
		Calendar cal = Calendar
				.getInstance(TimeZone.getTimeZone("Europe/Athens"));
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		fixInv = Inventory.getInvFixFin();
		urgInv = Inventory.getInvUrgFin();
		try {
			for (int i = 0; i < fixInv.length; i++) {
				sumFixInv += fixInv[i];
			}
			for (int i = 0; i < urgInv.length; i++) {
				sumUrgInv += urgInv[i];
			}
		} catch (Exception w) {
			System.out.println("Error");
		}
		
		double totalexpenses = waterSupply + electricity + phone_internetSupply
				+ wages + sumFixInv + sumUrgInv;
		String actual = "Total expenses of" + "February" + " of " + year
				+ "are " + totalexpenses + " " + "\n Water Supply bill:  "
				+ waterSupply + "\n Electrisity bill:   " + electricity
				+ "\n Telecommunications: " + phone_internetSupply
				+ "\n Employees payments: " + wages + "\n Inventory expenses: "
				+ sumFixInv + sumUrgInv
				+ "\n -------------------------------------------";
		//System.out.println(result + "result"); 
		//System.out.println(actual + "actual"); 
		
    
	}


	static  void loadobjects() {
		ReportingFinance f = new ReportingFinance(4500,5000,500); // orizoume tous logarismous tou mina
		ReportingFinance f2 = new ReportingFinance(5509, 5222, 433);
		ReportingFinance f3 = new ReportingFinance(7509, 5232, 443);
		ReportingFinance f4 = new ReportingFinance(4655, 4553, 434);
		ShareHolders sh1 = new ShareHolders("alexis", "alexis12345");
	    ShareHolders sh2 = new ShareHolders("olympiakos","eisaistomialokatimagiko7");
	    ShareHolders sh3 = new ShareHolders("username", "passss12345");		
	}
}
