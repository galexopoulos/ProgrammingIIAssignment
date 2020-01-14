package gr.aueb.dmst.erp;

/**
 * @author Ioannis Alexios Perakis,
 * This class offers an amount of analytics taken from customers data such as average payments, average days of stay,
 * in which conditions did the customer visit the hotel ect. Necessity for Reporting.java
 */

import java.util.ArrayList;

public class ReportingClients {

	private String name;
	private String surname;
	private int satisfuction; // apo 0 eos 5
	private double payment;
	private int family_alone_friends;
    private int daysofstay;
	private int reasonfortravel;
	private String email;
	private int know;
	private static int counter_st = 0;

	/**
	 * answers : 0 gia epagelmatiko 1 gia pleasure 2 family 3 tourismos 4 group
	 * trips
	 */

	static ArrayList<ReportingClients> coustomerBase = new ArrayList<ReportingClients>();

	public ReportingClients(String name, String surname, int satisfuction, double payment, int family, int daysofstay,
			int reasonfortravel, String email, int know) {
		this.name = name;
		this.surname = surname;
		this.satisfuction = satisfuction;
		this.payment = payment;
		this.family_alone_friends = family;
        this.daysofstay = daysofstay;
		this.reasonfortravel = reasonfortravel;
		this.email = email;
		this.know = know;
		coustomerBase.add(this);
        counter_st++;
	}

	public void setDaysofstay(int daysofstay) {
		this.daysofstay = daysofstay;
	}

	public static int getCounter_st(){
		return counter_st;
		}
	public int getKnow() {
		return know;
	}
	public void setKnow(int know) {
		this.know = know;
	}
    public String getEmail() {
    	return email;
    }
    public void setEmail(String email) {
    	this.email = email;
    }
    public int getDaysofstay() {
    	return daysofstay;
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

	public int getReasonfortravel() {
		return reasonfortravel;
	}

	public void setReasonfortravel(int reasonfortravel) {
		this.reasonfortravel = reasonfortravel;
	}

	/**
	 * This method is called from Reporting.java by pressing 3
	 */
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
				s += i.getDaysofstay();
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
		double s1 = 0;// sum of clients that came alone
		double s2 = 0;// sum of clients that came with their family
		double s3 = 0;// sum of clients that came with friends
		try {
			for (ReportingClients i : ReportingClients.coustomerBase) {
				if (i.getFamily() == 1) {
					s1++;
				} else if (i.getFamily() == 2) {
					s2++;
				} else {
					s3++;
				}
			}
			System.out.printf("Average of clients that came alone is: %f \n"
					+ "Average of clients that came with their family is: %f \n"
					+ "Average of clients that came with their friends is: %f ",
					s1 / (s1 + s2 + s3), s2 / (s1 + s2 + s3),
					s3 / (s1 + s2 + s3));
			System.out.println();
		} catch (Exception e) {
			System.out.println("No clients available.");
		}
	}
}
