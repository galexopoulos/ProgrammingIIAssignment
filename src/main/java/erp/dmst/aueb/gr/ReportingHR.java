package main.java.erp.dmst.aueb.gr;
import java.util.ArrayList;

public class ReportingHR {

	private double wage;
	private String name;
	private String surname;
	private int sex; //0 man 1 woman
	private int age;
	static ArrayList<ReportingHR> hr = new ArrayList<ReportingHR>(); //Stoixia ipalilon sideri

	public ReportingHR(double wage, String name, String surname,int sex, int age ) {
		this.wage = wage;
		this.name = name;
		this.surname = surname;
		this.sex = sex;
		this.age = age;
		hr.add(this);
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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

	public static void getAverageSex() {
		int sM = 0;
		int sW = 0;
		try {
			for (ReportingHR i : ReportingHR.hr) {
				if(i.getSex() == 0) {//MEN
					sM ++;
				}else sW++; //Women
			}
			
			double perMen = (double) sM/ReportingHR.hr.size() * 100;
			double perWomen = (double) sW/ReportingHR.hr.size() * 100;
			System.out.printf("The percentage of Woman is %f and of men is %f",perMen,perWomen);
		}catch(Exception e) {
			System.out.println("No employees available.");
		}
	}

	public static void getAvaAge() {
		int s = 0;
		try {
			for(ReportingHR i : ReportingHR.hr) {
				s = i.getAge();
			}
			System.out.println("The average age of the hotel's employees is: " + s / ReportingHR.hr.size());
		}catch(Exception e) {
			System.out.println("No employees available.");
		}
	}
	
}
