package main.java.erp.dmst.aueb.gr;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class ShareHolders {
	
	private static int counter = 1;
	private final int id;
	private String username;
	private String pswrd;
	private double dividend = 0;
	public static ArrayList<ShareHolders> shareholders = new ArrayList<ShareHolders>();
	
	public ShareHolders(String username, String pswrd) {
		this.username = username;
		this.pswrd = pswrd;
		id = counter;
		counter++;
		dividend = ReportingFinance.dividends();
		shareholders.add(this);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPswrd() {
		return pswrd;
	}

	public void setPswrd(String pswrd) {
		this.pswrd = pswrd;
	}

	@Override
	public String toString() {
		return "ShareHolder id = " + id + " and username = " + username 
				+"\n Dividend of " + getCurrentMonth(getDate(1) + 1) + "," + getDate(2)
				+" is " + dividend;
	}
	
	public static int getDate(int i) {
		Date date = new Date(System.currentTimeMillis());
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Athens"));
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		if (i == 1) {
			return month;
		}else return year;
	} 

	public static String getCurrentMonth(int minas) {
		String [] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		return months[minas + 1]; 
	}
}
