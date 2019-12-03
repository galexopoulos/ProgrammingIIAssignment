package main.java.erp.dmst.aueb.gr;
import java.util.ArrayList;
import java.util.Date;

public class ReportingClients {

	private String name;
	private String surname;
	private boolean loyalty;
	private String SSN;
	private int satisfuction; //apo 1 eos 5
	private int counter_st = 0;
	private int arrival; //! months? !
	private String email;
	private int know;//pos mathane to ksenodoxio, 0 gia 
	private double payment;
	private int family_alone_friends;
	private int checkin;
	private int checkout;
	private int reasonfortravel; /**answers :
								  * 0 gia epagelmatiko
								  * 1 gia pleasure 
								  * 2 family
								  * 3 tourismos
								  * 4 group trips 
								  **/

	static ArrayList<ReportingClients> coustomerBase = new ArrayList<ReportingClients>();

	public ReportingClients(String name, String surname, boolean loyalty, String sSN, int satisfuction, int arrival,
			String email, int know, double payment, int family, int checkin, int checkout,int reasonfortravel) {
		super();
		this.name = name;
		this.surname = surname;
		this.loyalty = loyalty;
		SSN = sSN;
		this.satisfuction = satisfuction;
		this.arrival = arrival;
		this.email = email;
		this.know = know;
		this.payment = payment;
		this.family_alone_friends = family;
		this.checkin = checkin;
		this.checkout = checkout;
		this.reasonfortravel = reasonfortravel;
		counter_st++;
		coustomerBase.add(this);
	} //COMMENTS! EINAI MOGLIDES

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

	public boolean isLoyalty() {
		return loyalty;
	}

	public void setLoyalty(boolean loyalty) {
		this.loyalty = loyalty;
	}

	public String getSSN() {
		return SSN;
	}

	public void setSSN(String sSN) {
		SSN = sSN;
	}

	public int getSatisfuction() {
		return satisfuction;
	}

	public void setSatisfuction(int satisfuction) {
		this.satisfuction = satisfuction;
	}

	public int getArrival() {
		return arrival;
	}

	public void setArrival(int arrival) {
		this.arrival = arrival;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getKnow() {
		return know;
	}

	public void setKnow(int know) {
		this.know = know;
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
			for(ReportingClients i : ReportingClients.coustomerBase) {
				s += i.getSatisfuction();
			}
			System.out.println("Average Satisfaction:"+ s/ReportingClients.coustomerBase.size());
		}catch(Exception e) {
			System.out.println("No clients available.");
		}
	}
	
	public static void averageDaysOfStay() {
		int s = 0 ;
		try {
			for(ReportingClients i : ReportingClients.coustomerBase) {
				s += i.getCheckout() - i.getCheckin();
			}
			System.out.println("Average days of stay:"+ s /ReportingClients.coustomerBase.size());
		}catch(Exception e) {
			System.out.println("No clients available.");
		}
	}
	
	public static void averagePayment() {
		int s = 0;
		try {
			for(ReportingClients i : ReportingClients.coustomerBase) {
				s += i.getPayment();
			}
			System.out.println("Average of payments is:"+ s /ReportingClients.coustomerBase.size());
		}catch(Exception e) {
			System.out.println("No payments available.");
		}
	}
	
	public static void percentageAlone_Family_Friends() {
		int s1 = 0;// sum of clients that came alone
		int s2 = 0;// sum of clients that came with their family
		int s3 = 0;//sum of clients that came with friends
		try {
			for(ReportingClients i : ReportingClients.coustomerBase) {
				if (i.family_alone_friends == 1) {
					s1++;
				}else if (i.family_alone_friends == 2) {
					s2++;
				}else {
					s3++;
				}
			}
			System.out.printf("Average of clients that came alone is: %f /n "
					+ "Average of clients that came with their family is: %f /n"
					+ "Average of clients that came with their friends is: %f ", s1/ (s1+s2+s3), s2/ (s1+s2+s3), s3/ (s1+s2+s3) );
		}catch(Exception e) {
			System.out.println("No clients available.");
		}
	}
}