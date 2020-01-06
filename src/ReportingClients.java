package main.java.erp.dmst.aueb.gr;

/**
 * @author Ioannis Alexios Perakis,
 * This class offers an amount of analytics taken from customers data such as average payments, average days of stay,
 * in which conditions did the customer visit the hotel ect. Necessity  for Reporting.java 
 */

import java.util.ArrayList;

public class ReportingClients { 

	private String name;
	private String surname;
	private int satisfuction; // apo 0 eos 5
	private double payment;
	private int family_alone_friends;
	private int checkin;
	private int checkout;
	private int reasonfortravel;
	/**
	 * answers : 0 gia epagelmatiko 1 gia pleasure 2 family 3 tourismos 4 group
	 * trips
	 **/

	static ArrayList<ReportingClients> coustomerBase = new ArrayList<ReportingClients>();

	public ReportingClients(String name, String surname, int satisfuction,
			double payment, int family, int checkin, int checkout,
			int reasonfortravel) { // main payment + na vro tropo na kano int to
									// check in kai to check out
		super();
		this.name = name;
		this.surname = surname;
		this.satisfuction = satisfuction;
		this.payment = payment;
		this.family_alone_friends = family;
		this.checkin = checkin;
		this.checkout = checkout;
		this.reasonfortravel = reasonfortravel;
		coustomerBase.add(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getSatisfuction() {
		return satisfuction;
	}

	public void setSatisfuction(int satisfuction) {
		this.satisfuction = satisfuction;
	}

	public double getPayment() {
		return payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

	public int getFamily() {
		return family_alone_friends;
	}

	public void setFamily(int family) {
		this.family_alone_friends = family;
	}

	public static ArrayList<ReportingClients> getCoustomerBase() {
		return coustomerBase;
	}

	public int getCheckin() {
		return checkin;
	}

	public int getCheckout() {
		return checkout;
	}

	public int getReasonfortravel() {
		return reasonfortravel;
	}

	public void setReasonfortravel(int reasonfortravel) {
		this.reasonfortravel = reasonfortravel;
	}

	public static void averageSatisfaction() {
		int s = 0;
		try {
			for (ReportingClients i : ReportingClients.coustomerBase) {
				s += i.getSatisfuction();
			}
			System.out.println("Average Satisfaction:"
					+ s / ReportingClients.coustomerBase.size());
		} catch (Exception e) {
			System.out.println("No clients available.");
		}
	}

	public static void averageDaysOfStay() {
		int s = 0;
		try {
			for (ReportingClients i : ReportingClients.coustomerBase) {
				s += i.getCheckout() - i.getCheckin();
			}
			System.out.println("Average days of stay:"
					+ s / ReportingClients.coustomerBase.size());
		} catch (Exception e) {
			System.out.println("No clients available.");
		}
	}

	public static void averagePayment() {
		int s = 0;
		try {
			for (ReportingClients i : ReportingClients.coustomerBase) {
				s += i.getPayment();
			}
			System.out.println("Average of payments is:"
					+ s / ReportingClients.coustomerBase.size());
		} catch (Exception e) {
			System.out.println("No payments available.");
		}
	}

	public static void percentageAlone_Family_Friends() {
		int s1 = 0;// sum of clients that came alone
		int s2 = 0;// sum of clients that came with their family
		int s3 = 0;// sum of clients that came with friends
		try {
			for (ReportingClients i : ReportingClients.coustomerBase) {
				if (i.family_alone_friends == 1) {
					s1++;
				} else if (i.family_alone_friends == 2) {
					s2++;
				} else {
					s3++;
				}
			}
			System.out.printf("Average of clients that came alone is: %f /n "
					+ "Average of clients that came with their family is: %f /n"
					+ "Average of clients that came with their friends is: %f ",
					s1 / (s1 + s2 + s3), s2 / (s1 + s2 + s3),
					s3 / (s1 + s2 + s3));
		} catch (Exception e) {
			System.out.println("No clients available.");
		}
	}
}