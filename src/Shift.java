import java.util.Calendar;
import java.util.Scanner;


/**
 * The class is used to create an Employee's timetable of the type Calendar,
 *  using String input that will be checked.
 * @author Georgios Sideris
 *
 */
public class Shift {

	/**
	 * Uses a String input of a daily timetable 
	 * and creates an array of type int variables exported from the input.
	 * *max 4 different time periods.
	 * @param day_schedule  String input of a daily timetable 
	 * 						with "," between time periods,
	 * 						"-" between chronical points,
	 *					 	":" between hours and minutes,
	 * 						for example "9:00-17:00,18:00-20:00" 
	 * @return int[4][4] Array (int_Arrival_hours, int_Arrival_mins, int_Departure_hours, int_Departure_mins) 
	 * *when the procceding is over sets the leftover indexes = -1.
	 * @throws ShiftException
	 */
	public static int[][] arr_Dep_Times_int(String day_schedule) throws ShiftException { //public only for the junit

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

		try {
			String[] timePeriods = new String[4];
			String[] timePeriods_fromStr = day_schedule.split(",");
			if (day_schedule.length() > 0) {
				if (day_schedule.charAt(0) == '-') { // checks if the string starts with '-', it will help for overnight
														// shifts
					day_schedule = "clear:clear" + day_schedule;
					starthyphen = true;

				}

				if (day_schedule.charAt(day_schedule.length() - 1) == '-') { // checks if the string ends with '-', it
																				// will help for overnight shifts
					day_schedule = day_schedule + "clear:clear";
					endhyphen = true;
				}

				timePeriods_fromStr = day_schedule.split(",");

				for (int i = 0; i < timePeriods_fromStr.length; i++) {
					timePeriods[i] = timePeriods_fromStr[i];
				}
				for (int i = timePeriods_fromStr.length; i < 4; i++) {
					timePeriods[i] = "empty";
				}
			} else {
				for (int i = 0; i < 4; i++) {
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
					} else {
						str_Arrival_hours[i] = Times[0].substring(0, Times[0].indexOf(":"));
						str_Arrival_mins[i] = Times[0].substring(Times[0].indexOf(":") + 1, Times[0].length());
					}
					if (endhyphen == true && i == timePeriods_fromStr.length - 1) {
						str_Departure_hours[i] = "-1";
						str_Departure_mins[i] = "-1";
					} else {
						str_Departure_hours[i] = Times[1].substring(0, Times[1].indexOf(":"));
						str_Departure_mins[i] = Times[1].substring(Times[1].indexOf(":") + 1, Times[1].length());
					}
				}
			}
		} catch (ArrayIndexOutOfBoundsException aioobe) {
			throw new ShiftException("Misused characters, possibly out of bounds input.");
		} catch (StringIndexOutOfBoundsException sioobe) {
			throw new ShiftException("Misused characters, possibly out of bounds input.");
		}

		for (int i = 0; i < 4; i++) {
			int x;
			try {
				x = Integer.parseInt(str_Arrival_hours[i]);
			} catch (NumberFormatException nfe) {
				throw new ShiftException("Unparsable inserted value.");
			}

			if (x >= 0 && x <= 23 || x == -1) {// 0 <= x <= 23 because x represents hours here, -1 positions needed
												// in order to check that there are no back to back arrivals or
												// departures
				int_Arrival_hours[i] = x;
			} else {
				throw new ShiftException("Out of bounds number.");
			}

			if (str_Arrival_mins[i].length() != 2) {// if the string that represents mins has not two position exactly
													// then it throws Exception and does not move on
				throw new ShiftException("Problem with characters after \":\".");
			}

			try {
				x = Integer.parseInt(str_Arrival_mins[i]);
			} catch (NumberFormatException nfe) {
				throw new ShiftException("Unparsable inserted value.");
			}

			if (x >= 0 && x <= 59 || x == -1) {// 0 <= x <= 59 because x represents minutes here, -1 positions needed in
												// order
				// to check that there are no back to back arrivals or departures
				int_Arrival_mins[i] = x;
			} else {
				throw new ShiftException("Out of bounds number.");
			}

			try {
				x = Integer.parseInt(str_Departure_hours[i]);
			} catch (NumberFormatException nfe) {
				throw new ShiftException("Unparsable inserted value.");
			}

			if (x >= 0 && x <= 23 || x == -1) {// 0 <= x <= 23 because x represents hours here, -1 positions needed in
												// order to
				// check that there are no back to back arrivals or departures
				int_Departure_hours[i] = x;
			} else {
				throw new ShiftException("Out of bounds number.");
			}

			if (str_Departure_mins[i].length() != 2) {// if the string that represents mins has not two position exactly
														// then it throws Exception and does not move on
				throw new ShiftException("Problem with characters after \":\".");
			}

			try {
				x = Integer.parseInt(str_Departure_mins[i]);
			} catch (NumberFormatException nfe) {
				throw new ShiftException("Unparsable inserted value.");
			}

			if (x >= 0 && x <= 59 || x == -1) {// 0 <= x <= 59 because x represents minutes here, -1 positions needed in
												// order
				// to check that there are no back to back arrivals or departures
				int_Departure_mins[i] = x;
			} else {
				throw new ShiftException("Out of bounds number.");
			}

		}
		arrH_arrM_depH_depM[0] = int_Arrival_hours;
		arrH_arrM_depH_depM[1] = int_Arrival_mins;
		arrH_arrM_depH_depM[2] = int_Departure_hours;
		arrH_arrM_depH_depM[3] = int_Departure_mins;

		return arrH_arrM_depH_depM;
	}

	/**
	 * Find when the monday is in the current week.
	 * @return a Calendar variable of the Monday in the current week.
	 */
	public static Calendar getCurrentMonday() { 
		Calendar cal = Calendar.getInstance();
		int weekday = cal.get(Calendar.DAY_OF_WEEK);
		if (weekday != Calendar.MONDAY) {
			int days = (Calendar.SATURDAY - weekday + 2) % 7 - 7;
			cal.add(Calendar.DAY_OF_YEAR, days);
		}
		cal.set(Calendar.HOUR_OF_DAY, 00);
		cal.set(Calendar.MINUTE, 00);
		cal.set(Calendar.SECOND, 00);
		return cal;
	}

	
	/**
	 * Creates the shift of the day cal.
	 * @param arrH_arrM_depH_depM the array the arr_Dep_Times_int method created.
	 * @param cal, Calendar type variable of the day of which we want to create the shift
	 * @return two Arrays in an Array of type Calendar (cal_Arrival, cal_Departure) (two dimensional Array)
	 * that contain when an Employee arrives and leaves.
	 * *sets the YEAR 1990 when the corresponding index of the input's array is -1.
	 */
	private static Calendar[][] arr_Dep_Times_Cal(int[][] arrH_arrM_depH_depM, Calendar cal) {
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

	/**
	 * Connects the Arrival and Departure times at the right order.
	 * @param cal the array that the arr_Dep_Times_Cal mehtod created
	 * @return the timetable of a day (Calendar array)
	 */
	private static Calendar[] connect_Arr_Dep(Calendar[][] cal) { // connects the Arrival and Departure times at the
																	// right order of the array that the
																	// arr_Dep_Times_Cal created
		Calendar[] day_Schedule = new Calendar[8];
		for (int i = 0; i < 4; i++) {
			day_Schedule[2 * i] = cal[0][i];
			day_Schedule[2 * i + 1] = cal[1][i];
		}
		return day_Schedule;

	}

	/**
	 * Connects the indexes of 8 daily timetables (first and last the same (Monday) as Sunday -> Monday -> Tuesday)) at one array
	 * @param calSchedule Calendar [8][8]
	 * @return one array after connected the arrays that consist the Calendar[] input, used at checkWeekSchedule method
	 */
	private static Calendar[] week_Schedule(Calendar[][] calSchedule) {

		Calendar[] fullWeek = new Calendar[64];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				fullWeek[i * 8 + j] = calSchedule[i][j];
			}
		}
		return fullWeek;

	}

	/**
	 * Checks that there are no back to back arrivals or departures
	 * and that the fullWeek indexes are at the right order 
	 * else it throws ShiftException.
	 * @param fullWeek the array created from the week_Schedule method 
	 * @throws ShiftException
	 */
	private static void checkWeekSchedule(Calendar[] fullWeek) throws ShiftException {
		int y1990inrow = 0;

		for (int i = 0; i < fullWeek.length; i++) {
			
			if (i != 0 || i != fullWeek.length -1) {// checks that there are no back to back arrivals or departures, there must be an even number of indexes with YEAR = 1990 in the row, otherwise there is a problem with the input (we don't check the first and last indexes as the first was checked the previous week and the last will be checked the next)
				if (fullWeek[i].get(Calendar.YEAR) == 1990) {
					y1990inrow++;
				}else if (y1990inrow != 0){
					if (y1990inrow % 2 != 0) {
						throw new ShiftException("Arrival - Departure's order exception.");
					}else {
						y1990inrow = 0;
					}
				}
			}

			if (fullWeek[i].get(Calendar.YEAR) != 1990 && fullWeek[i + 1].get(Calendar.YEAR) != 1990)
				if (fullWeek[i].after(fullWeek[i + 1])) { // checks that the indexes are at the right order, if not
															// then there is a mistake at the input given, we don't need
															// to check indexes with YEAR != 1990 but separated by
															// indexes with YEAR = 1990 because these indexes are from
															// diffent days and that means that their order is correct

					throw new ShiftException("Chronical order exception.");
				}
		}
		
		

	}
	
	/**
	 * Throws ShiftException if a day's shift has more than 8 hours.
	 * @param dayShift, a shift of a day
	 * @throws ShiftException
	 */

	private static void check8Hours(Calendar[] dayShift) throws ShiftException { 
		final long millisOf8Hours = 8 * 60 * 60 * 1000;
		long millisTheDay = 0, startInMillis, endInMillis;
		for (int i = 0; i < 7; i = i + 2) {
			if (dayShift[i].get(Calendar.YEAR) != 1990) {
				if ( dayShift[i + 1].get(Calendar.YEAR) != 1990) {// input like 12:00-16:00
					startInMillis =  dayShift[i].getTimeInMillis();
					endInMillis =  dayShift[i + 1].getTimeInMillis();
					millisTheDay =+ endInMillis - startInMillis - 1000;//-1000 because of some minor differences
				} else {// input like 12:00-
					Calendar endOfDay = Calendar.getInstance();
					endOfDay.set(Calendar.YEAR, dayShift[i].get(Calendar.YEAR));
					endOfDay.set(Calendar.MONTH, dayShift[i].get(Calendar.MONTH));
					endOfDay.set(Calendar.DAY_OF_MONTH, dayShift[i].get(Calendar.DAY_OF_MONTH));
					endOfDay.set(Calendar.HOUR_OF_DAY, 23);
					endOfDay.set(Calendar.MINUTE, 59);
					endOfDay.set(Calendar.SECOND, 0);
					endOfDay.set(Calendar.MILLISECOND, 0);
					startInMillis =  dayShift[i].getTimeInMillis();
					endInMillis = endOfDay.getTimeInMillis();
					millisTheDay =+ endInMillis - startInMillis; 
					break;
				}
			} else {
				if ( dayShift[i + 1].get(Calendar.YEAR) != 1990) {// input like -16:00
					Calendar startOfDay = Calendar.getInstance();
					startOfDay.set(Calendar.YEAR, dayShift[i + 1].get(Calendar.YEAR));
					startOfDay.set(Calendar.MONTH, dayShift[i + 1].get(Calendar.MONTH));
					startOfDay.set(Calendar.DAY_OF_MONTH, dayShift[i + 1].get(Calendar.DAY_OF_MONTH));
					startOfDay.set(Calendar.HOUR_OF_DAY, 0);
					startOfDay.set(Calendar.MINUTE, 0);
					startOfDay.set(Calendar.SECOND, 59);
					startOfDay.set(Calendar.MILLISECOND, 999);
					startInMillis = startOfDay.getTimeInMillis();
					endInMillis =  dayShift[i + 1].getTimeInMillis();
					millisTheDay =+ endInMillis - startInMillis ;
				} else {
					break;
				}
			}
		}
		if (millisTheDay >= millisOf8Hours) {
			throw new ShiftException("More than 8 hours a day.");
		}
	}

/**
 * Using all the other methods of the class creates the shift of the week
 * according to the inputted String[], if there is a problem with the input
 * it throws ShiftException.
 * @param strSchedule, an array of Strings containing the shift we want to create
 * @return the shift of the week as a Calendar[7][8]
 * @throws ShiftException
 */
	public static Calendar[][] createShift(String[] strSchedule) throws ShiftException {
		Calendar[][] dayScheduleNextWeek8 = new Calendar[8][8];
		Calendar[][] dayScheduleNextWeek = new Calendar[7][8];
		if (strSchedule.length != 8) {
			throw new ShiftException("Wrong length of input.");
		}
		int[][] arrH_arrM_depH_depM = new int[4][4];
		Calendar[][] arr_depTimesCal = new Calendar[2][4];
		Calendar dayToChange = getCurrentMonday();
		Calendar[] daySchedule = new Calendar[8];
		Calendar[] weekSchedule = new Calendar[64];
		for (int i = 0; i < 8; i++) {
			arrH_arrM_depH_depM = arr_Dep_Times_int(strSchedule[i]);
			arr_depTimesCal = arr_Dep_Times_Cal(arrH_arrM_depH_depM, dayToChange);
			dayToChange.add(Calendar.DAY_OF_YEAR, 1);
			daySchedule = connect_Arr_Dep(arr_depTimesCal);
			dayScheduleNextWeek8[i] = daySchedule;
		}

		weekSchedule = week_Schedule(dayScheduleNextWeek8);
		checkWeekSchedule(weekSchedule);// executes the check and throws ShiftException if there is a mistake

		for (int i = 0; i < 7; i++) {
			check8Hours(dayScheduleNextWeek8[i]);// executes the check and throws ShiftException if there is a mistake
			dayScheduleNextWeek[i] = dayScheduleNextWeek8[i];

		}

		return dayScheduleNextWeek;
	}

	/**
	 * Contains all the procedure with the messages to insert a shift.
	 * @return the inserted shift as String[], to be checked or "no shift" at the first index of the table if the shift wasn't inserted.
	 */
	public static String[] insertShiftStr() {
		Scanner sc = new Scanner(System.in);
		String shiftStr[] = new String[8];
		for (int i = 0; i < 8; i++) {
			shiftStr[i] = "-";
		}
		System.out.println("Insert the shift.");
		boolean flag1;
		do {
			flag1 = false;
			System.out.println("Insert Monday's shift or press Enter to go back:");
			String inserted;
			inserted = sc.nextLine();
			if (inserted.equals("")) {
				shiftStr[0] = "no shift";
				return shiftStr;
			} else {
				shiftStr[0] = inserted;
			}
			boolean flag2;
			do {
				flag2 = false;
				System.out.println("Insert Tuesday's shift or press Enter to go back:");
				inserted = sc.nextLine();
				if (inserted.equals("")) {
					flag1 = true;
					break;
				} else {
					shiftStr[1] = inserted;
				}
				boolean flag3;
				do {
					flag3 = false;
					System.out.println("Insert Wednesday's shift or press Enter to go back:");
					inserted = sc.nextLine();
					if (inserted.equals("")) {
						flag2 = true;
						break;
					} else {
						shiftStr[2] = inserted;
					}
					boolean flag4;
					do {
						flag4 = false;
						System.out.println("Insert Thursday's shift or press Enter to go back:");
						inserted = sc.nextLine();
						if (inserted.equals("")) {
							flag3 = true;
							break;
						} else {
							shiftStr[3] = inserted;
						}
						boolean flag5;
						do {
							flag5 = false;
							System.out.println("Insert Friday's shift or press Enter to go back:");
							inserted = sc.nextLine();
							if (inserted.equals("")) {
								flag4 = true;
								break;
							} else {
								shiftStr[4] = inserted;
							}
							boolean flag6;
							do {
								flag6 = false;
								System.out.println("Insert Saturday's shift or press Enter to go back:");
								inserted = sc.nextLine();
								if (inserted.equals("")) {
									flag5 = true;
									break;
								} else {
									shiftStr[5] = inserted;
								}
								System.out.println("Insert Sunday's shift or press Enter to go back:");
								inserted = sc.nextLine();
								if (inserted.equals("")) {
									flag6 = true;
									break;
								} else {
									shiftStr[6] = inserted;
								}
							} while (flag6);
						} while (flag5);
					} while (flag4);
				} while (flag3);

			} while (flag2);

		} while (flag1);
		return shiftStr;
	}
}
