
import java.util.*;

public class check_main {

	public static void main(String args[]) {
		/*
		 * Scanner sc = new Scanner(System.in); String daySchedule = "3:00-4:00";
		 * String[] a = new String[8];
		 * 
		 * a[6]="20:00-"; a[7]="-3:00";
		 * 
		 * Calendar[][] weekShift = new Calendar[7][8]; try { weekShift =
		 * Shift.createShift(a); }catch (Exception e) {
		 * System.err.println("mistake with the shift"); } Employee d =new Employee("b",
		 * "b", "b", "c", 10,null); d.setThisWeekShift(weekShift);
		 * System.out.println(d.getThisWeekShift()[0][0].get(Calendar.YEAR));
		 * Employee.printShift(d.getThisWeekShift()); Calendar dailyTimes[] = new
		 * Calendar[8]; //shows the arrivals and departures of the employee in one day
		 * int dayCounter = 0;// shows how many indexes of the array dailyTimes are
		 * filled boolean checkedIn = false; //true when the employee has checked in but
		 * hasn't checked out boolean flag=false; int selection = 0; Manager ab = new
		 * Manager("a", "a", "a", "b", 10, null); Manager ac = new Manager("c", "c",
		 * "c", "d", 10, null); Employee c =new Employee("b", "b", "b", "c", 10, ab);
		 * Hr_Director boss = new Hr_Director("boss", "no1","a","b",10,null); c = new
		 * Manager(c); System.out.println(c instanceof Manager); boss.getMenu();
		 */
		/* 0 */ Hr_Director Kobe = new Hr_Director("Kobe", "Bryant", "123456", 213141);
		/* 1 */ Manager Petreas = new Manager("Petreas", "Giorgos", "Athlete", "DaddyofIvan", 19000, Kobe);
		/* 2 */ Manager Barney = new Manager("Barney", "Stinson", "P.L.E.A.S.E.", "robin", 1434133, Kobe);
		/* 3 */ Employee Ivan = new Employee("Ivan", "Zaytsev", "Athlete", "Petreasismydaddy", 12000, Petreas);
		/* 4 */ Employee Rick = new Employee("Rick", "Sanchez", "scientist", "Wubalubadubdub", 2, Barney);
		/* 5 */ Employee Rachel = new Employee("Rachel", "Green", "waitress", "LaPooh", 980, Barney);
		/* 6 */ Employee NF = new Employee("Nate", "Feuerstein", "GOAT", "paidmydues", 3034308, Barney);

		String[] shift = { "9:00-17:00", "1:00-9:00", "-", "7:00-14:00", "19:00-", "-1:00,12:00-13:00", "-",
				"11:00-17:00" };
		Calendar[][] shiftcal = { null, null, null, null, null, null, null };
		try {
			shiftcal = Shift.createShift(shift);
		} catch (ShiftException a) {
			System.out.println("wrong1 " + a);
		}
		Barney.setShiftStr(shift);
		Barney.setThisWeekShift(shiftcal);
		Petreas.setShiftStr(shift);
		Petreas.setThisWeekShift(shiftcal);
		String[] shift2 = { "12:00-17:00", "16:00-", "-8:00", "12:00-20:00", "7:00-14:00", "9:01-17:00",
				"23:00-23:30,23:31-23:45,23:46-23:47,23:48-23:49", "12:00-13:00" };
		try {
			shiftcal = Shift.createShift(shift2);
		} catch (ShiftException a) {
			System.out.println("wrong2 " + a);
		}
		Rachel.setShiftStr(shift2);
		Rachel.setThisWeekShift(shiftcal);
		Ivan.setShiftStr(shift2);
		Ivan.setThisWeekShift(shiftcal);
		String[] shift3 = { "7:00-14:00", "7:00-14:00", "7:00-14:00", "7:00-14:00", "7:00-14:00", "-", "-",
				"7:00-14:00" };
		Calendar cal = Calendar.getInstance();
		int weekday = cal.get(Calendar.DAY_OF_WEEK);
		if (weekday != Calendar.MONDAY) {
			int days = (Calendar.SATURDAY - weekday + 2) % 7 - 7;
			cal.add(Calendar.DAY_OF_YEAR, days);
		}
		// System.out.println(cal.get(Calendar.DAY_OF_MONTH));
		// System.out.println(Rachel.getThisWeekShift()[5][1].get(Calendar.DAY_OF_MONTH));
		try {
			shiftcal = Shift.createShift(shift3);
		} catch (ShiftException a) {
			System.out.println("wrong3 " + a);
		}
		NF.setShiftStr(shift3);
		NF.setThisWeekShift(shiftcal);
		Rick.setShiftStr(shift3);
		Rick.setThisWeekShift(shiftcal);
		Hr_surface.toRun();
	}

	public static void loadobjects() {
		Hr_Director Kobe = new Hr_Director("Kobe", "Bryant", "123456", 2131);
		Manager Petreas = new Manager("Petreas", "Giorgos", "Athlete", "DaddyofIvan", 19000, Kobe);
		Manager Barney = new Manager("Barney", "Stinson", "P.L.E.A.S.E.", "robin", 13413, Kobe);
		Manager Dumbledore = new Manager("Albus", "Percival Wulfric Brian Dumbledore", "Principal", "oldman", 2200,
				Kobe);
		Manager Leo = new Manager("Leonardo", "Da vinci", "Architect", "monalisa", 1200, Kobe);
		Employee Ivan = new Employee("Ivan", "Zaytsev", "Athlete", "Petreasismydaddy", 1200, Petreas);
		Employee Rick = new Employee("Rick", "Sanchez", "scientist", "Wubalubadubdub", 1700, Barney);
		Employee Rachel = new Employee("Rachel", "Green", "waitress", "LaPooh", 1980, Barney);
		Employee NF = new Employee("Nate", "Feuerstein", "GOAT", "paidmydues", 1710, Barney);
		Employee Ron = new Employee("Ron", "Wesley", "Wizard", "orangehair", 1600, Barney);
		Employee Lionel = new Employee("Lionel", "Messi", "Athlete", "goat", 1750, Dumbledore);
		Employee Cristiano = new Employee("Cristiano", "Ronaldo", "Athlete", "beyondmessi", 1749, Dumbledore);
		Employee Margaret = new Employee("Margaret", "Thatcher", "Queen", "obey", 1250, Dumbledore);
		Employee Angela = new Employee("Angela", "Merkel", "chancellor", "ichheissemerkel", 1200, Dumbledore);
		Employee Agatha = new Employee("Agatha", "Christie", "Author", "missmarple", 1200, Petreas);

	}
}