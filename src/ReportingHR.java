import java.util.ArrayList;

public class ReportingHR {

	private double wage;
	private String name;
	private String surname;
	private int id;
	private String SSN;
	private int VATR;
	private int startingDate;
	private String email;
	private String poss;
	private int sex; //0 man 1 woman
	static ArrayList<ReportingHR> hr = new ArrayList<ReportingHR>(); //Stoixia ipalilon sideri

	public ReportingHR(double wage, String name, String surname, int id, String sSN, int vATR, int startingDate,
			String email, String poss,int sex ) {
		this.wage = wage;
		this.name = name;
		this.surname = surname;
		this.id = id;
		SSN = sSN;
		VATR = vATR;
		this.startingDate = startingDate;
		this.email = email;
		this.poss = poss;
		this.sex = sex;
		hr.add(this);

	}

	public int getSex() {
		return sex;
	}

	public double getWage() {
		return wage;
	}

	public void setWage(double wage) {
		this.wage = wage;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSSN() {
		return SSN;
	}

	public void setSSN(String sSN) {
		SSN = sSN;
	}

	public int getVATR() {
		return VATR;
	}

	public void setVATR(int vATR) {
		VATR = vATR;
	}

	public int getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(int startingDate) {
		this.startingDate = startingDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPoss() {
		return poss;
	}

	public void setPoss(String poss) {
		this.poss = poss;
	}

}
