import java.util.*;
import java.time.*;
public class check_main {

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);

		String daySchedule = "12:00-11:00,13:00-16:00,17:00-18:00";
		String[] a = new String[8];
		for (int i = 0; i < 6; i++) {
			a[i] = daySchedule;
		}
		
		 a[6]="20:00-";
		 a[7]="-8:00";
		 Calendar[][] weekShift = new Calendar[8][8];
		 weekShift = Shift.connectsAllMethods(a);
		Calendar dailyTimes[] = new Calendar[8]; //shows the arrivals and departures of the employee in one day
		int dayCounter = 0;// shows how many indexes of the array dailyTimes are filled
		boolean checkedIn = false; //true when the employee has checked in but hasn't checked out
	    boolean flag=false;
	    int selection = 0;



			System.out.println(
					"Welcome! \nSelect: \n1)Check in.\n2)Check out. \n3)Day off request. \n4)Inbox. \n5)Show shift of the week.");
			do {
				if (!sc.hasNextInt()) {
					System.out.println("Please insert 1 or 2 or 3 or 4 or 5");
					flag = true;
					sc.next();

				} else {
					selection = sc.nextInt();
					if (selection > 5 || selection < 1) {
						flag = true;
						System.out.println("input an integer [1,5]");
					} else {
						flag = false;
						sc.nextLine();
					}
				}
				// sc.nextLine(); //checkneeded
			} while (flag);
			if (selection == 1) {
				Calendar arrTime = Calendar.getInstance();
				dailyTimes[dayCounter] = arrTime;
				checkedIn = true;
				dayCounter++;
				System.out.println("Check in successful!");
			} else if (selection == 2) {
				Calendar depTime = Calendar.getInstance();
				dailyTimes[dayCounter] = depTime;
				checkedIn = false;
				dayCounter++;
				System.out.println("Check out successful!");
			} else if (selection == 3) {// you can request for free day only in the current week
				Calendar current = Calendar.getInstance();
				current.add(Calendar.SECOND, -1); // in order to be able to request for free day the day we request
				Calendar lastDay = Calendar.getInstance();
				Calendar freeRequest = Calendar.getInstance();
				int day = 0, month = 0, year = 0;
				boolean flag2 = false;
				do {
					System.out.println("Insert the day of the month.");
					boolean flag3 = false;
					do {
						if (!sc.hasNextInt()) {
							System.err.println("Insert an integer:");
							flag3 = true;
							sc.next();

						} else {
							day = sc.nextInt();
							if (day > 31 || day < 1) {
								flag3 = true;
								System.err.println("Insert an integer [1,31]:");
							} else {
								flag3 = false;
								// flag3=true; dont know if a flag is needed here
							}
						}
						sc.nextLine();
					} while (flag3);
					System.out.println("Insert the month.");
					boolean flag4 = false;
					do {
						if (!sc.hasNextInt()) {
							System.err.println("Insert an integer:");
							flag4 = true;
							sc.next();

						} else {
							month = sc.nextInt();
							if (month > 12 || month < 1) {
								flag4 = true;
								System.err.println("Insert an integer [1,12]:");
							} else {
								flag4 = false;
								// flag3=true; dont know if a flag is needed here
							}
						}
						sc.nextLine();
					} while (flag4);
					System.out.println("Insert the year.");
					boolean flag5 = false;
					do {
						if (!sc.hasNextInt()) {
							System.err.println("Insert an integer:");
							flag5 = true;
							sc.next();

						} else {
							year = sc.nextInt();
							if (year != current.get(Calendar.YEAR) && year != current.get(Calendar.YEAR) + 1) {
								flag5 = true;
								System.err.println("Insert an integer [" + current.get(Calendar.YEAR) + ","
										+ (current.get(Calendar.YEAR) + 1) + "]:");
							} else {
								flag5 = false;
								// flag3=true; dont know if a flag is needed here
							}
						}
						sc.nextLine();
					} while (flag5);
					try {
						LocalDate.of(year, month, day);
					} catch (Exception e) { // checks that the date is valid, for example day == 31, month == 2 is invalid
						System.out.println("Invalid date");
						flag2 = true;
						continue;
					}
					freeRequest.set(Calendar.DAY_OF_MONTH, day);
					freeRequest.set(Calendar.MONTH, month - 1);
					freeRequest.set(Calendar.YEAR, year);
					lastDay = Shift.getNextMonday();
					if (current.after(freeRequest) || freeRequest.after(lastDay)) {
						System.out.println("Insert a date of the current week.");
						System.out.println(freeRequest.getTime());
						flag2 = true;
					}
				} while (flag2);
			
			}else if (selection == 4) {
				//call inbox

			}else {
				for(int i = 1; i<8; i++) {
					String dayName;
					switch (i) {
					case 1:
						dayName = "Monday";
						break;
					case 2:
						dayName = "Tuesday";
						break;
					case 3:
						dayName = "Wednesday";
						break;
					case 4:
						dayName = "Thursday";
						break;
					case 5:
						dayName = "Friday";
						break;
					case 6:
						dayName = "Saturday";
						break;
					default:
						dayName = "Sunday";
						break;
					}
					System.out.println(dayName);
					String time;
					for (int j = 0; j < 8; j = j + 2) {
						if (weekShift[i][j].get(Calendar.YEAR) != 1990) {
							time = String.format("%02d:%02d", weekShift[i][j].get(Calendar.HOUR_OF_DAY), weekShift[i][j].get(Calendar.MINUTE));
							System.out.print (time + " - ");
							if (weekShift[i][j + 1].get(Calendar.YEAR) != 1990) {
								time = String.format("%02d:%02d", weekShift[i][j + 1].get(Calendar.HOUR_OF_DAY), weekShift[i][j + 1].get(Calendar.MINUTE));
								System.out.println(time);
							}else {
								System.out.println();
							}
						}else {
							if (weekShift[i][j + 1].get(Calendar.YEAR) != 1990) {
								time = String.format("%02d:%02d", weekShift[i][j + 1].get(Calendar.HOUR_OF_DAY), weekShift[i][j + 1].get(Calendar.MINUTE));
								System.out.println("- " + time);
							}else if (j == 0){// for an empty day
								System.out.println("-");
							}
						}
					}
					
				 }
				
			}
			
		}

	
	}

	