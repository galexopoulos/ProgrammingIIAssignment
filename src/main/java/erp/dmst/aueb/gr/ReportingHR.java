package main.java.erp.dmst.aueb.gr;
import java.util.ArrayList;

public class ReportingHR {

	private double wage;
	private String name;
	private String surname;
	static ArrayList<ReportingHR> hr = new ArrayList<ReportingHR>(); //Stoixia ipalilon sideri

	public ReportingHR(double wage, String name, String surname) {
		this.wage = wage;
		this.name = name;
		this.surname = surname;
		hr.add(this);
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
	
	public ArrayList<ReportingHR> getHrList(){
		return hr;
	}
	
	public static double averageWage() {
		int S = 0;
		for (ReportingHR i : ReportingHR.hr) {
			S += i.getWage();
		}
		if (!(ReportingHR.hr.isEmpty())){
			return S/ReportingHR.hr.size();
		}else return 0;
	}
	
}
