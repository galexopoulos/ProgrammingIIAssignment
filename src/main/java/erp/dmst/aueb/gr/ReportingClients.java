package main.java.erp.dmst.aueb.gr;
import java.util.ArrayList;
import java.util.Date;

public class ReportingClients {

	private String name;
	private String surname;
	private boolean loyalty;
	private String SSN;
	private int satisfuction;
	private int arrival; //! months? !
	private String email;
	private int know;//pos mathane to ksenodoxio
	private double payment;
	private int family;
	private Date checkin;
	private Date checkout;


	static ArrayList<ReportingClients> coustomerBase = new ArrayList<ReportingClients>();

	public ReportingClients(String name, String surname, boolean loyalty, String sSN, int satisfuction, int arrival,
			String email, int know, double payment, int family, Date checkin, Date checkout) {
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
		this.family = family;
		this.checkin = checkin;
		this.checkout = checkout;
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
		return family;
	}

	public void setFamily(int family) {
		this.family = family;
	}

	public static ArrayList<ReportingClients> getCoustomerBase() {
		return coustomerBase;
	}

	public Date getCheckin() {
		return checkin;
	}

	public Date getCheckout() {
		return checkout;
	}

}
