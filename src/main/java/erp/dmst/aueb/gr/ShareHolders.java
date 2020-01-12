package main.java.erp.dmst.aueb.gr;

/**
 * 
 * @author Ioannis Alexios Perakis
 * ShareHolder class aims to provide dividends to each shareholder. It is called by the ReportingFinance class by 
 * pressing 3. The first menu is a declaration menu only to check if the individual who called this class wants to 
 * proceed to the dividends and shareholders and it is not mistakenly called. By pressing 1 Username and password
 * checks follows up and if are correct a method display the earings/dividends.
 * There is an ArrayList with every shareholde of the hotel with usernames and passwords.
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.TimeZone;
import java.util.stream.Collectors;

public class ShareHolders {
	
	private static int counter = 1;
	private final int id;
	private String username;
	private String pswrd;
	private double dividend = 0;
	
	public static ArrayList<ShareHolders> shareholders = new ArrayList<ShareHolders>();
	
	public ShareHolders(String username, String pswrd) { //Prepei na bei sthn main create user
		this.username = username;
		this.pswrd = pswrd;
		id = counter;
		counter++;
		dividend = ReportingFinance.dividends();
		shareholders.add(this);
		
	}
	
	/**
	 * This method gives access to the shareholders to their dividends of each month. Every Shareholder have his password
	 * and username. There is a check process to declare if the password and the username is correct, if so this method calls 
	 * toString() to display the earnings. 
	 */
	
	public static void getShareHoldersMenu() {
		boolean flag = true;
		Scanner sc = new Scanner(System.in);
		do {
			try {
				boolean flag2 = getMenuflag();
				if(flag2) {
					
					System.out.println("Please insert Username: ");
					String usnm = sc.next();
					System.out.println("Please insert password: ");
					String pass = sc.next();
					int j = 0;
					for(ShareHolders i : shareholders) {
						if(i.getUsername().equals(usnm) && i.getPswrd().equals(pass)) {
							System.out.println("Username & password are correct.");				
							System.out.println(i.toString());
							flag = false;
							j = 1;
						}
					}
					if (j == 0) {
						System.out.println("Please insert valid password and/or username");
					}
				}else {
					flag = false;
					ReportingFinance.getMenu();
				}
			}catch(InputMismatchException e){
		        System.out.println("Input exception. Try again.");
			}catch(Exception e){
		        System.out.println("Error try again.");
		    }
		}while(flag);
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
		return "ShareHolder id: " + id + " and username: " + username 
				+"\nDividend of " + getCurrentMonth(getDate(1) - 1) + "," + getDate(2)
				+" is " + dividend + "\n";
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
	
	/**
	 * This method 
	 */
	
	public static boolean getMenuflag() {
		Scanner sc = new Scanner(System.in);
		System.out.println("-----------Share Holders menu-----------"
				+ "\nTo proceed press: 1."
				+ "\nExit and return to Finance menu press: 0.");
		int ans = 0;
		int epilogh = 0;
		boolean flag2 = false;
		do {
			if (!sc.hasNextInt()) {
				System.out.println("Input an integer [0,1]");
				flag2 = true; 
				sc.next();

			} else {
				epilogh = sc.nextInt();
				if (epilogh > 1 || epilogh < 0) {
					flag2 = true;
					System.out.println("Input an integer [0,1]");
				} else {
					if (epilogh == 1) {
						ans = 1;
						flag2 = false;
					}else {
						ans = 0;
						flag2 = false;
					}
				sc.nextLine();
				}
			}
		} while (flag2);
		
		if(ans == 1) {
			return true;
		}else {
			return false;
		}
		
	}
	
}

