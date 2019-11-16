import java.util.*;
import java.text.*;
import java.lang.*;

public class Shift {

	Calendar[][] nextWeekShift = new Calendar[8][8];
	private String[] schedule = new String[7]; // the class is used to create an Employee's timetable of the type
												// Calendar, using String input that will be checked




	
	public static int[][] arr_Dep_Times_int(String day_schedule)  throws Exception{ // uses a String     
															// input of a daily
															// timetable
															// with "," between time periods, "-"
															// between chronical points, ":" between
															// hours and minutes, for example
															// "9:00-17:00,18:00-20:00" and creates an int[4][8] Array
															// (int_Arrival_hours, int_Arrival_mins,
															// int_Departure_hours, int_Departure_mins)
															// max 4 different time periods

		String[] str_Arrival_hours = new String[4];
		String[] str_Arrival_mins = new String[4];
		String[] str_Departure_hours = new String[4];
		String[] str_Departure_mins = new String[4];
		int[] int_Arrival_hours = new int[4];
		int[] int_Arrival_mins = new int[4];
		int[] int_Departure_hours = new int[4];
		int[] int_Departure_mins = new int[4];
		int[][] arrH_arrM_depH_depM = new int[4][4];
		boolean starthyphen = false, endhyphen = false;

		String[] timePeriods = new String[4];
		String[] timePeriods_fromStr = day_schedule.split(",");
		if (day_schedule.length() > 0) {
			if (day_schedule.charAt(0) == '-') { //checks if the string starts with '-', it will help for overnight shifts
				day_schedule = "clear:clear" + day_schedule;
				starthyphen = true;
				
			}
			
			if (day_schedule.charAt(day_schedule.length() - 1) == '-') { // checks if the string ends with '-', it will help for overnight shifts
				day_schedule = day_schedule + "clear:clear";
				endhyphen = true;
			}
		
			timePeriods_fromStr = day_schedule.split(",");
			
			for (int i = 0; i< timePeriods_fromStr.length; i++) {
				timePeriods[i] = timePeriods_fromStr[i];
			}
			for (int i= timePeriods_fromStr.length; i<4; i++) {
				timePeriods[i] = "empty";
			}
		}else {
			for (int i = 0; i<4; i++) {
				timePeriods[i] = "empty";
			}
		}
		for (int i = 0; i < 4; i++) {
			if (timePeriods[i].equals("empty")) {
				str_Arrival_hours[i] = "-1";
				str_Arrival_mins[i] = "-1";
				str_Departure_hours[i] = "-1";
				str_Departure_mins[i] = "-1";
			} else {
				String[] Times = timePeriods[i].split("-", 2); 
				if (starthyphen == true && i == 0) {
					str_Arrival_hours[i] = "-1";
					str_Arrival_mins[i] = "-1";
				}else {
					str_Arrival_hours[i] = Times[0].substring(0, Times[0].indexOf(":"));
					str_Arrival_mins[i] = Times[0].substring(Times[0].indexOf(":") + 1, Times[0].length());
				}
				if (endhyphen == true && i == timePeriods_fromStr.length-1) {
					str_Departure_hours[i] = "-1";
					str_Departure_mins[i] = "-1";
				}else {
					str_Departure_hours[i] = Times[1].substring(0, Times[1].indexOf(":"));
					str_Departure_mins[i] = Times[1].substring(Times[1].indexOf(":") + 1, Times[1].length());
				}
			}
		}
		

		for (int i = 0; i < 4; i++) {
			int x;

			x = Integer.parseInt(str_Arrival_hours[i]);


			if (x >= 0 && x <= 23 || x == -1) {// 0 <= x <= 23 because x represents hours here, -1 positions needed
													// in order to check that there are no back to back arrivals or
													// departures
				int_Arrival_hours[i] = x;
			}else {
				throw new Exception(); 				
			}
			
			if (str_Arrival_mins[i].length() != 2) {//if the string that represents mins has not two position exactly then it throws Exception and does not move on
				throw new Exception(); 	
			}
			
			x = Integer.parseInt(str_Arrival_mins[i]);
			if (x >= 0 && x <= 59 || x == -1) {// 0 <= x <= 59 because x represents minutes here, -1 positions needed in order
										// to check that there are no back to back arrivals or departures
				int_Arrival_mins[i] = x;
			} else {
				throw new Exception();     
			}

			x = Integer.parseInt(str_Departure_hours[i]);

			if (x >= 0 && x <= 23 || x == -1) {// 0 <= x <= 23 because x represents hours here, -1 positions needed in order to
									// check that there are no back to back arrivals or departures
				int_Departure_hours[i] = x;
			} else {
				throw new Exception();                
			}
			
			if (str_Departure_mins[i].length() != 2) {//if the string that represents mins has not two position exactly then it throws Exception and does not move on
				throw new Exception();               
			}
			
			x = Integer.parseInt(str_Departure_mins[i]);

			if (x >= 0 && x <= 59 || x == -1) {// 0 <= x <= 59 because x represents minutes here, -1 positions needed in order
										// to check that there are no back to back arrivals or departures
				int_Departure_mins[i] = x;
			} else {
				throw new Exception();                     
			}
			
		}
		arrH_arrM_depH_depM[0] = int_Arrival_hours;
		arrH_arrM_depH_depM[1] = int_Arrival_mins;
		arrH_arrM_depH_depM[2] = int_Departure_hours;
		arrH_arrM_depH_depM[3] = int_Departure_mins;

		return arrH_arrM_depH_depM;
	}

	public static Calendar getNextMonday() { // returns when the next monday is (Calendar type)
		Calendar cal = Calendar.getInstance();
		int weekday = cal.get(Calendar.DAY_OF_WEEK);
		if (weekday != Calendar.MONDAY) {
			int days = (Calendar.SATURDAY - weekday + 2) % 7;
			cal.add(Calendar.DAY_OF_YEAR, days);
		} else {
			cal.add(Calendar.DAY_OF_YEAR, 7);
		}
		cal.set(Calendar.HOUR_OF_DAY, 00);
		cal.set(Calendar.MINUTE, 00);
		cal.set(Calendar.SECOND, 00);
		return cal;
	}

	public static Calendar[][] arr_Dep_Times_Cal(int[][] arrH_arrM_depH_depM, Calendar cal, int daysFromMonday) { // cal when
																											// the next
																											// monday
																											// is, uses
																											// the list
																											// the
																											// arr_Dep_Times_int
																											// method
																											// created
																											// to create
																											// two Arrays
																											// (in an
																											// Array)
																											// of type
																											// Calendar
																											// (cal_Arrival,
																											// cal_Departure)
																											// that
																											// contain
																											// when an
																											// Employee
																											// arrives
																											// and
																											// leaves

		cal.add(Calendar.DAY_OF_YEAR, daysFromMonday);
		Calendar[][] calArrDep = new Calendar[2][4];
		for (int i = 0; i < 4; i++) { // arrH_arrM_depH_depM[0][] = int_Arrival_hours[]
			calArrDep[0][i] = Calendar.getInstance();// arrivals
			calArrDep[1][i] = Calendar.getInstance();// departures
			
			calArrDep[0][i].set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
			if (arrH_arrM_depH_depM[0][i] == -1) {
				calArrDep[0][i].set(Calendar.YEAR, 1990);
			} else {
				calArrDep[0][i].set(Calendar.HOUR_OF_DAY, arrH_arrM_depH_depM[0][i]);
				calArrDep[0][i].set(Calendar.MINUTE, arrH_arrM_depH_depM[1][i]); // arrH_arrM_depH_depM.get(1) =
																					// int_Arrival_mins
			}

			calArrDep[1][i].set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
			if (arrH_arrM_depH_depM[2][i] == -1) { // arrH_arrM_depH_depM[2][] = int_Departure_hours[]
				calArrDep[1][i].set(Calendar.YEAR, 1990);
			} else {
				calArrDep[1][i].set(Calendar.HOUR_OF_DAY, arrH_arrM_depH_depM[2][i]);
				calArrDep[1][i].set(Calendar.MINUTE, arrH_arrM_depH_depM[3][i]); // arrH_arrM_depH_depM.get(3)=
																					// int_Departure_mins
			}
		}
		return calArrDep;

	}

	public static Calendar[] connect_Arr_Dep(Calendar[][] cal) { // connects the Arrival and Departure times at the right order of the array that the arr_Dep_Times_Cal created
		Calendar[] day_Schedule = new Calendar[8];
		for (int i = 0; i < 4; i++) {
			day_Schedule[2*i] = cal[0][i];
			day_Schedule[2*i + 1] = cal[1][i];
		}
		return day_Schedule;

	}

	public static Calendar[] week_Schedule(Calendar[][] calSchedule) {// connects the indexes of an Array Calendar[][] at an
																// Array Calendar[]

		Calendar[] fullWeek = new Calendar[64];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				fullWeek[i * 8 + j] = calSchedule[i][j];
			}
		}
		return fullWeek;

	}

	public static boolean Check_week_Schedule(Calendar[] fullWeek) {// checks that there are no back to back arrivals
																// or departures and that the fullWeek indexes
																// are at the right order
		boolean everythingOk = true;
		int y1990inrow = 0;
		for (int i = 1; i < fullWeek.length - 1; i++) {// checks that there are no back to back arrivals or departures,
														// there must be an evem number of indexes with YEAR = 1990 in
														// the row, otherwise there is a problem with the input (we
														// don't check the first and last indexes as the first was
														// checked the previous week and the last will be checked the
														// next)
			if (fullWeek[i].get(Calendar.YEAR) == 1990) {
				y1990inrow++;
			}else if (y1990inrow != 0){
				if (y1990inrow % 2 != 0) {
					everythingOk = false;
					break;
				}else {
					y1990inrow = 0;
				}
			}
			

		}

		for (int i = 0; i < fullWeek.length; i++) {

			if (fullWeek[i].get(Calendar.YEAR) != 1990 && fullWeek[i + 1].get(Calendar.YEAR) != 1990)
				if (fullWeek[i].after(fullWeek[i + 1])) { // checks that the indexes are at the right order, if not
															// then there is a mistake at the input given, we don't need
															// to check indexes with YEAR != 1990 but separated by
															// indexes with YEAR = 1990 because these indexes are from
															// diffent days and that means that their order is correct

					everythingOk = false;
					break;
				}
		}

		return everythingOk;
	}

	public static Calendar[][] createShift(String[] strSchedule) throws Exception{
		Calendar[][] dayScheduleNextWeek = new Calendar[8][8];
			if (strSchedule.length != 8) {
				throw new Exception();
			}
			int[][] arrH_arrM_depH_depM = new int[4][4];
			Calendar[][] arr_depTimesCal = new Calendar[2][4];
			Calendar nextMonday = getNextMonday();
			Calendar[] daySchedule = new Calendar[8];
			Calendar[] weekSchedule = new Calendar[64];
			for (int i = 0; i < 8; i++) { 
				
				arrH_arrM_depH_depM = arr_Dep_Times_int(strSchedule[i]);
				arr_depTimesCal = arr_Dep_Times_Cal(arrH_arrM_depH_depM, nextMonday, i - 1);
				daySchedule = connect_Arr_Dep(arr_depTimesCal);
				dayScheduleNextWeek[i] = daySchedule;
			}
	
			weekSchedule = week_Schedule(dayScheduleNextWeek); 
			if (!Check_week_Schedule(weekSchedule)) {
				throw new Exception ();                 
			}
		return dayScheduleNextWeek;
	}
}
