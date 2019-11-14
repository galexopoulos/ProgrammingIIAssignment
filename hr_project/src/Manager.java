import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Manager extends Employee{
	private int employee_Id, extraHoursMonth = 0 /*need to be set to 0 every month*/;
	private double monthSalary;
	private Calendar[][]weekShift = new Calendar[8][8];
	private static int add = 0;
	static ArrayList<Employee> Employees = new ArrayList<Employee>();
	private Calendar dailyTimes[] = new Calendar[8]; // shows the arrivals and departures of the employee in one day
	private int dayCounter = 0;// shows how many indexes of the array dailyTimes are filled
	private boolean checkedIn = false; // true when the employee has checked in but hasn't checked out

	public Manager (String firstname, String surname, String position, String password, double salary,
			Manager employer) {
		super(firstname, surname, position, password, salary, employer);
	} 
	
	public void getMenu() {
		Scanner sc = new Scanner(System.in);
		boolean flag = false;
		int selection = 0;

		System.out.println(
				"Welcome! \nSelect: \n1)Check in.\n2)Check out. \n3)Day off request. \n4)Inbox. \n5)Show shift of the week. "
				+ "\n6)View Employees. \n7)Set  extra hours for an Employee. \n8)Edit an Employee's payment. \n9)Edit an Employee's fields");
		do {
			if (!sc.hasNextInt()) {
				System.out.println("Please insert an integer 1 - 9");
				flag = true;
				sc.next();

			} else {
				selection = sc.nextInt();
				if (selection > 9 || selection < 1) {
					flag = true;
					System.out.println("input an integer [1,9]");
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
					flag2 = true;
				}
			} while (flag2);
		
		}else if (selection == 4) {
			//call inbox

		}else if (selection == 5){
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
			
		}else if (selection == 6) {
			for (Employee a : Employees) {
				if (this.equals(a.getManager())) {
					a.toString();
				}
			}
		}else if (selection == 7) {
			//related to inbox 
			
		}else if (selection == 8) {
			int posInEmployees = enterEmpId();
			if (posInEmployees != -1) {
				boolean flag9 = false;
				int sel = 0;
				System.out.println("Do you want to edit \n1)Salary. \n2)Payment for the current month?");
				do {
					if (!sc.hasNextInt()) {
						System.out.println("Please insert a number, 1 or 2.");
						flag9 = true;
						sc.next();

					} else {
						sel = sc.nextInt();
						if (sel > 2 || sel < 1) {
							flag9 = true;
							System.out.println("Please insert 1 or 2.");
						} else {
							flag9 = false;
							sc.nextLine();
						}
					}

				} while (flag9);
				switch (sel) {
				case 1:
					System.out.println("The current salary is " + Employees.get(posInEmployees).getSalary());
					System.out.println("Set the new salary:");
					boolean flag7 = false;
					int newSalary = Employees.get(posInEmployees).getSalary();
					do {
						if (!sc.hasNextInt()) {
							System.out.println("Please insert an integer.");
							flag7 = true;
							sc.next();
	
						} else {
							newSalary = sc.nextInt();
							if (newSalary < 0) {
								flag7 = true;
								System.out.println("Input an integer greater than zero.");
							}else if(newSalary == Employees.get(posInEmployees).getSalary()) {
								System.out.println("Insert a salary different than the previous one.");
							}else {
								flag7 = false;
								sc.nextLine();
							}
						}
						
					} while (flag7);
					System.out.println("The new salary is going to be set to" + newSalary + "/nare you sure you want to make that change?");
					boolean flag8 = false;
					do {
						System.out.println("yes/no");
						String verify = sc.nextLine();
						if (verify.toLowerCase().equals("yes")) {
							Employees.get(posInEmployees).setSalary(newSalary);
							System.out.println("The change have been made.");
							flag8 = false;
						}else if (verify.toLowerCase().equals("no")){
							System.out.println("Change cancelled");
							flag8 = false;
						}else {
							flag8 = true;
						}
					}while(flag8);
					break;
				case 2:
					System.out.println("The current payment is " + Employees.get(posInEmployees).getMonthPayment());
					System.out.println("Set the new payment:");
					boolean flag1 = false;
					int newPayment = Employees.get(posInEmployees).getMonthPayment();
					do {
						if (!sc.hasNextInt()) {
							System.out.println("Please insert an integer.");
							flag1 = true;
							sc.next();
	
						} else {
							newPayment = sc.nextInt();
							if (newPayment < 0) {
								flag1 = true;
								System.out.println("Input an integer greater than zero.");
							}else if(newPayment == Employees.get(posInEmployees).getMonthPayment()) {
								System.out.println("Insert a payment different than the previous one.");
							}else {
								flag1 = false;
								sc.nextLine();
							}
						}
						
					} while (flag1);
					System.out.println("The new payment is going to be set to" + newPayment + "/nare you sure you want to make that change?");
					boolean flag10 = false;
					do {
						System.out.println("yes/no");
						String verify = sc.nextLine();
						if (verify.toLowerCase().equals("yes")) {
							Employees.get(posInEmployees).setMonthPayment(newPayment);
							System.out.println("The change have been made.");
							flag10 = false;
						}else if (verify.toLowerCase().equals("no")){
							System.out.println("Change cancelled");
							flag10 = false;
						}else {
							flag10 = true;
						}
					}while(flag10);
					break;
				}
			}else {
				//RETURN TO MENU
			}
		}else if(selection == 9) {
			int posInEmployees = enterEmpId();
			if (posInEmployees != -1) {
				System.out.println(Employees.get(posInEmployees).toString());
				boolean flag9 = false;
				int sel = 0;
				
				System.out.println("Which field do you want to change? \n1)position \n2)manager");

				do {
					if (!sc.hasNextInt()) {
						System.out.println("Please insert a number, 1 or 2.");
						flag9 = true;
						sc.next();

					} else {
						sel = sc.nextInt();
						if (sel > 4 || sel < 1) {
							flag9 = true;
							System.out.println("Please insert 1 or 2.");
						} else {
							flag9 = false;
							sc.nextLine();
						}
					}
					switch (sel) {
					case 1:
						System.out.println("The current position is " + Employees.get(posInEmployees).getPosition());
						System.out.println("Set the new position:");
						String pos;
						pos = sc.nextLine();
						System.out.println("The new position is going to be set to" + pos + "/nare you sure you want to make that change?");
						boolean flag8 = false;
						do {
							System.out.println("yes/no");
							String verify = sc.nextLine();
							if (verify.toLowerCase().equals("yes")) {
								Employees.get(posInEmployees).setPosition(pos);
								System.out.println("The change have been made.");
								flag8 = false;
							}else if (verify.toLowerCase().equals("no")){
								System.out.println("Change cancelled");
								flag8 = false;
							}else {
								flag8 = true;
							}
						}while(flag8);
						break;
					case 2: 
						System.out.println("The current manager is " + Employees.get(posInEmployees).getManager().getFirstname() + Employees.get(posInEmployees).getManager().getSurname());
						System.out.println("Enter the id of the new Manager.");
						int selectedId, posInEmployees2 = -1;
						boolean flag6 = false;
						
						do {
							if (!sc.hasNextInt()) {
								System.out.println("Please insert an integer.");
								flag6 = true;
								sc.next();

							} else {
								selectedId = sc.nextInt();
								for (int i = 0; i < Employees.size(); i++) {
									if (Employees.get(i).getEmployee_Id() == selectedId) {	
										if (selectedId != this.employee_Id && Employees.get(i) instanceof Manager) { //checks that the new Manager is not the same with the current and that the new Manager is a Manager and not a basic Employee
											posInEmployees2 = i;
											flag6 = false;
											break;
										}else {
											flag6 = true;
											System.out.println("You are not allowed to do that.");
											break;
										}
									}else {
											flag6 = true;
											System.out.println("That is not a valid id.");
									}
									
								}
							}	
						} while (flag6);
						Manager newManager =(Manager) Employees.get(posInEmployees2);
						System.out.println("The new Manager is going to be " + newManager.getFirstname() + newManager.getSurname() + "/nare you sure you want to make that change?");
						boolean flag10 = false;
						do {
							System.out.println("yes/no");
							String verify = sc.nextLine();
							if (verify.toLowerCase().equals("yes")) {
								Employees.get(posInEmployees).setManager(newManager);
								System.out.println("The change have been made.");
								flag10 = false;
							}else if (verify.toLowerCase().equals("no")){
								System.out.println("Change cancelled");
								flag10 = false;
							}else {
								flag10 = true;
							}
						}while(flag10);
						break;
					}

				} while (flag9);
				
			}else {
				//RETURN TO MENU
			}
		}
	}
	
	public int enterEmpId() {//I HAVE TO ADD AN EXIT OPTION
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Employee's id.");
		int selectedId;
		int requested = -1;
		boolean flag6 = false;
		do {
			if (!sc.hasNextInt()) {
				System.out.println("Please insert an integer.");
				flag6 = true;
				sc.next();

			} else {
				selectedId = sc.nextInt();
				for (int i = 0; i < Employees.size(); i++) {
					if (Employees.get(i).getEmployee_Id() == selectedId) {	
						if (this.equals(Employees.get(i).getManager())) {
							requested = i;
							flag6 = false;
							break;
						}else {
							flag6 = true;
							System.out.println("You are not allowed to do that.");
						}
					}else {
							flag6 = true;
							System.out.println("That is not a valid id.");
							break;
					}
					
				}
			}	
		} while (flag6);
		return requested;
	}
	
}
