package src.main.java.gr.aueb.dmst.erp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountCustomers {
	/**
	 * This class Creates Accounts for every customer that needs one. Necessity for
	 * Customer Entry.
	 * 
	 * @author Georgios Alkis Alexopoulos,
	 */
	private Membership membershipCode;
	private String username;
	private String password;
	private double totalpayment;
	private static int counteracc = -1;
	private String id;

	static ArrayList<AccountCustomers> accCustomerBase = new ArrayList<AccountCustomers>();

	public AccountCustomers(String username, String password, double totalpayment, String id,
			Membership membershipCode) {
		this.username = username;
		this.password = password;
		this.totalpayment = totalpayment;
		this.id = id;
		this.membershipCode = membershipCode;
		accCustomerBase.add(this);
		counteracc++;

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getTotalPayment() {
		return totalpayment;
	}

	public void setPayment(double totalpayment) {
		this.totalpayment = totalpayment;

	}

	public static void setCounteracc(int counteracc) {
		AccountCustomers.counteracc = counteracc;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Membership getMembershipCode() {
		return membershipCode;
	}

	public void setMembershipCode(Membership membershipCode) {
		this.membershipCode = membershipCode;
	}

	public static int getCounteracc() {
		return counteracc;
	}

	public static void grapsimoAccountCustomer(int x) throws IOException {
		Scanner scanner = new Scanner(System.in);
		FileWriter outFile = new FileWriter("AccountCustomer.txt", true);
		PrintWriter out = new PrintWriter(outFile);
		out.println(x);
		out.close();
		outFile.close();
	}

	/** Method used for getting the variable add from files. */
	public static int parsimoAccountCustomer() {
		int[] tall = new int[2];
		int i = 0;
		File file = new File("AccountCustomer.txt");

		try {
			Scanner s1 = new Scanner(file);
			while (s1.hasNextInt()) {
				tall[i] = s1.nextInt();
			}
			return tall[i];

		} catch (FileNotFoundException e) {
			System.out.println("Exception");
		}
		return 0;
	}
}
