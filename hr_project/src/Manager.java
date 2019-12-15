import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Manager extends Employee { // responsible for the Employees who have him as Manager
	
	//private int employee_Id, extraHoursMonth = 0 /* need to be set to 0 every month */;
	//private int monthPayment;
	//private Calendar[][]thisWeekShift = new Calendar[7][8];
	//private boolean checkedIn = false; // true when the employee has checked in but hasn't checked out
	//private Calendar lastChecked = Calendar.getInstance();
	
	public Manager(String firstname, String surname, String position, String password, int salary, Manager manager) {
		super(firstname, surname, position, password, salary, manager);
	} 
	
	public Manager (Employee employee) {//for the promotion at the Hr Director class
		super(employee);
	}
	
	@Override
	public String toString() {
		return "Manager [firstname=" + getFirstname() + ", surname=" + getSurname() + ", position=" + getPosition() + ", employee_Id="
				+ getEmployee_Id() + ", salary=" + getSalary()  + "]";
	}
	
	public void getMenu() {
		Scanner sc = new Scanner(System.in);
		boolean menuflag = true;
		System.out.println("Welcome!");
		do {
			System.out.println(" MANAGER MENU \n------------- \n Select: \n1)Check in.\n2)Check out. \n3)Day off request. \n4)Inbox. \n5)Show shift of the week. "
					+ "\n6)View Employees. \n7)Show check in status of Employees. \n8)Change an Employee's shift. \n9)Set  extra hours for an Employee."
					+ " \n10)Edit an Employee's payment. \n11)Edit an Employee's fields. \n12)Log out.");
			boolean flag = false;
			int selection = 0;
			do {
				if (!sc.hasNextInt()) {
					System.out.println("Please insert an integer 1 - 12");
					flag = true;
					sc.next();
	
				} else {
					selection = sc.nextInt();
					if (selection > 12 || selection < 1) {
						flag = true;
						System.out.println("input an integer [1,12]");
					} else {
						flag = false;
						sc.nextLine();
					}
				}
			} while (flag);
			if (selection == 1) {
				if (!this.isCheckedIn()) { 
					this.setLastChecked(Calendar.getInstance());
					this.setCheckedIn(true);
					System.out.println("Check in successful!");
				}else {
					System.out.println("Already checked in.");
				}
			} else if (selection == 2) {
				if (this.isCheckedIn()) {
					this.setLastChecked(Calendar.getInstance());
					this.setCheckedIn(false); 
					System.out.println("Check out successful!");
				}else {
					System.out.println("Already checked out.");
				}
			} else if (selection == 3) {// you can request for free day only in the current week
				Calendar freeRequest = Calendar.getInstance();
				freeRequest = enterWeekDay();
				if (freeRequest.get(Calendar.YEAR) == 1990) {
					//SHOW THE MENU AGAIN
				}else {
					System.out.println("Day off request succesfully sent to Manager.");
					//inboxrelated
					//can be added an option to send a message with the request
				}
			
			}else if (selection == 4) {
				//call inbox
	
			}else if (selection == 5){
				printShift(this.getThisWeekShift());
			}else if (selection == 6) {
				boolean onefound = false;
				for (Employee a : super.Employees) {
					if (this.equals(a.getManager())) {
						System.out.println(a.toString());
						onefound = true;
					}
				}
				if (!onefound) {
					System.out.println("No employees found.");
				}
			}else if (selection == 7) {
				boolean onefound = false;
				String status;
				for (Employee a : super.Employees) { 
					if (this.equals(a.getManager())) {
						onefound = true;
						if (a.getLastChecked().get(Calendar.YEAR) != 1990) {
							if (a.isCheckedIn()) {
								status = "Checked in";
							}else {
								status = "Checked out";
							}
							String timeChecked = String.format("%02d:%02d", a.getLastChecked().get(Calendar.HOUR_OF_DAY), a.getLastChecked().get(Calendar.MINUTE));
							String dayChecked = String.format("%d/%d", a.getLastChecked().get(Calendar.DAY_OF_MONTH), a.getLastChecked().get(Calendar.MONTH) + 1);
							System.out.println("Id: " + a.getEmployee_Id() + " " + a.getFirstname() + " " +  a.getSurname() + " status: " + status + " at " + timeChecked + " of "  + dayChecked);
						}else { //if a.getLastChecked().get(Calendar.YEAR) == 1990 the Employee has never checked in
							System.out.println("Id: " + a.getEmployee_Id() + " " + a.getFirstname() + " " +  a.getSurname() + " status: Checked out");
						}
					}
				}
				if (!onefound) {
					System.out.println("No employees found.");
				}
			}else if (selection == 8) {
				boolean shiftflag = false;
				do {
					int posInEmployees = enterEmpId(); 
					if (posInEmployees != -1) {
						boolean flag2;
						do {
							flag2 = false;
							System.out.println(Employee.Employees.get(posInEmployees).toString());
							printShift(Employee.Employees.get(posInEmployees).getThisWeekShift());
							boolean flag3;
							int dayInt = -1;
							do {	
								flag3 = false;
								System.out.println("Insert the day of the week, press Enter to choose a different Id \n"
										+ "or type \"exit\" to return to the basic menu:");
								String dayOfWeek = sc.nextLine();
								dayOfWeek = dayOfWeek.toLowerCase();
								switch (dayOfWeek ) {
								case "":
									shiftflag = true;
									break;
								case "monday":
									dayInt = 0;
									break;
								case "tuesday":
									dayInt = 1;
									break;
								case "wednesday":
									dayInt = 2;
									break;
								case "thursday":
									dayInt = 3;
									break;
								case "friday":
									dayInt = 4;
									break;
								case "saturday":
									dayInt = 5;
									break;
								case "sunday":
									dayInt = 6;
									break;
								case "exit":
									menuflag = true;
								default:
									System.out.println("That is not a valid input.");
									flag3 = true;
								}
							}while (flag3);
							if(!shiftflag && dayInt !=-1) {
								String[] shiftStr = new String[8];
								shiftStr = Employee.Employees.get(posInEmployees).getShiftStr();
								boolean flag4;
								do {	
									flag4 = false;
									System.out.println("Insert the new shift for the day or type \"back\" to go back.");
									String inputShift = sc.nextLine();
									if (inputShift.toLowerCase().equals("back")) {
										flag2 = true;
										break;
									}else {
										if (dayInt == 0) { // monday is both at positions 0 and 7
											shiftStr[0] = inputShift;
											shiftStr[7] = inputShift;
										}else {
											shiftStr[dayInt] = inputShift;
											}
										try {
											Employee.Employees.get(posInEmployees).setThisWeekShift(Shift.createShift(shiftStr));
											//if it moves on the input is correct as creatShift method throws Exception for wrong input
											Employee.Employees.get(posInEmployees).setShiftStr(shiftStr);
											System.out.println("The change has been made.");
										}catch (Exception e) {
											System.out.println("Invalid input");
											flag4 = true;
										}
									}
								}while(flag4);
							}
							
						}while(flag2);
					}else {
						menuflag = true;
					}
					
				}while (shiftflag);
			
			}else  if (selection == 9) {
				//related to inbox 		
			}else if (selection == 10) {
				int posInEmployees = enterEmpId();
				if (posInEmployees != -1) {
					boolean flag1 = false;
					int sel = 0;
					System.out.println("Select \n1)Edit salary. \n2)Edit payment for the current month. \n3)Return to central menu.");
					do {
						if (!sc.hasNextInt()) {
							System.out.println("Please insert a number, 1 or 2 or 3.");
							flag1 = true;
							sc.next();
	
						} else {
							sel = sc.nextInt();
							if (sel > 3 || sel < 1) {
								flag1 = true;
								System.out.println("Please insert 1 or 2 or 3.");
							} else {
								flag1 = false;
								sc.nextLine();
							}
						}
	
					} while (flag1);
					switch (sel) {
					
					case 1:
						System.out.println("The current salary is " + super.Employees.get(posInEmployees).getSalary() + ".");
						String selected;
						int selectedSalary = -1;
						boolean flag2 = false;
						do {
							System.out.println("Set the new salary or press Enter to return to the basic Menu:");
							selected = sc.nextLine();
							if (selected.equals("")) {
								flag2 = false;
								break;

							} else {
								try {
									selectedSalary = Integer.parseInt(selected);
									if (selectedSalary < 0) {
										flag2 = true;
										System.out.println("Input an integer greater than zero.");
									}else if(selectedSalary == super.Employees.get(posInEmployees).getSalary()) {
										flag2 = true;
										System.out.println("Insert a salary different than the previous one.");
									}else {
										flag2 = false;
									}
								}catch(Exception b) {
									flag2 = true;
									System.out.println("Please insert an Integer.");
								}
							}	
						} while (flag2);
						if (selectedSalary != -1) {
							System.out.println("The new salary is going to be set to " + selectedSalary + ".\nAre you sure you want to make that change?");
							boolean flag3 = false;
							do {
								System.out.println("yes/no");
								String verify = sc.nextLine();
								if (verify.toLowerCase().equals("yes")) {
									super.Employees.get(posInEmployees).setSalary(selectedSalary);
									System.out.println("The change has been made.");
									flag3 = false;
								}else if (verify.toLowerCase().equals("no")){
									System.out.println("Change cancelled");
									flag3 = false;
								}else {
									flag3 = true;
								}
							}while(flag3);
						}
						break;
					case 2:
						System.out.println("The current payment is " + super.Employees.get(posInEmployees).getMonthPayment() + ".");
						String selected2;
						int selectedPayment = -1;
						boolean flag4 = false;
						do {
							System.out.println("Set the new payment or press Enter to return to the basic Menu:");
							selected2 = sc.nextLine();
							if (selected2.equals("")) {
								flag4 = false;
								break;

							} else {
								try {
									selectedPayment = Integer.parseInt(selected2);
									if (selectedPayment < 0) {
										flag4 = true;
										System.out.println("Input an integer greater than zero.");
									}else if(selectedPayment == super.Employees.get(posInEmployees).getMonthPayment()) {
										flag4 = true;
										System.out.println("Insert a payment different than the previous one.");
									}else {
										flag4 = false;
									}
								}catch(Exception b) {
									flag4 = true;
									System.out.println("Please insert an Integer.");
								}
							}	
						} while (flag4);
						if (selectedPayment != -1) {
							System.out.println("The new payment is going to be set to " + selectedPayment + ".\nAre you sure you want to make that change?");
							boolean flag5 = false;
							do {
								System.out.println("yes/no");
								String verify = sc.nextLine();
								if (verify.toLowerCase().equals("yes")) {
									super.Employees.get(posInEmployees).setSalary(selectedPayment);
									System.out.println("The change has been made.");
									flag5 = false;
								}else if (verify.toLowerCase().equals("no")){
									System.out.println("Change cancelled");
									flag5 = false;
								}else {
									flag5 = true;
								}
							}while(flag5);
						}
						break;
					case 3:
						menuflag = true;
						break;
					}	
				}else {
					menuflag = true;
				}
			}else if(selection == 11) {
				int posInEmployees = enterEmpId();
				if (posInEmployees != -1) {
					System.out.println(super.Employees.get(posInEmployees).toString());
					boolean flag1 = false;
					int sel = 0;
					
					System.out.println("Select \n1)Change position. \n2)Change manager. \n3)Return to central Menu.");
	
					do {
						if (!sc.hasNextInt()) {
							System.out.println("Please insert a number, 1 or 2 or 3.");
							flag1 = true;
							sc.next();
	
						} else {
							sel = sc.nextInt();
							if (sel > 3 || sel < 1) {
								flag1 = true;
								System.out.println("Please insert 1 or 2 or 3.");
							} else {
								flag1 = false;
								sc.nextLine();
							}
						}
						switch (sel) {
						case 1:
							System.out.println("The current position is " + super.Employees.get(posInEmployees).getPosition());
							System.out.println("Set the new position or press Enter to return to central Menu:");
							String pos;
							pos = sc.nextLine();
							if (pos.equals("")) {
								menuflag = true;
							}else {
								System.out.println("The new position is going to be set to: " + pos + ". \nAre you sure you want to make that change?");
								boolean flag2 = false;
								do {
									System.out.println("yes/no");
									String verify = sc.nextLine();
									if (verify.toLowerCase().equals("yes")) {
										super.Employees.get(posInEmployees).setPosition(pos);
										System.out.println("The change has been made.");
										flag2 = false;
									}else if (verify.toLowerCase().equals("no")){
										System.out.println("Change cancelled.");
										flag2 = false;
									}else {
										flag2 = true;
									}
								}while(flag2);
							}
							break;
						case 2: 
							System.out.println("The current manager is " + super.Employees.get(posInEmployees).getManager().getFirstname() +
									" " + super.Employees.get(posInEmployees).getManager().getSurname() +".");
							String selected;
							int selectedId, posInEmpOfManager = -1;
							boolean flag3 = false;
							do {	
								System.out.println("Enter the id of the new Manager or press Enter to return to central Menu.");
								selected = sc.nextLine();
								if (selected.equals("")) {
									flag3 = false;
									posInEmpOfManager = -1;
								}else {
									try {
										selectedId = Integer.parseInt(selected);
										posInEmpOfManager = binarySearch(selectedId);
									
										if (posInEmpOfManager != -1) {
											if (selectedId != this.getEmployee_Id()
													&& selectedId != super.Employees.get(posInEmployees)
															.getEmployee_Id()
													&& super.Employees.get(posInEmpOfManager) instanceof Manager) { // checks that
																									// the new
																									// Manager is
																									// not the same
																									// with the
																									// current or
																									// with the
																									// employee whom
																									// field we want
																									// to edit and
																									// that the new
																									// Manager is a
																									// Manager and
																									// not a basic
																									// Employee
												flag3 = false;
											} else {
												posInEmpOfManager = -1;
												flag3 = true;
												System.out.println("You are not allowed to do that.");
											}
										}else {
											posInEmpOfManager = -1;
											flag3 = true;
											System.out.println("That is not a valid id.");
										}
									}catch (Exception d) {
										posInEmpOfManager = -1;
										System.out.println("Please insert an Integer.");
										flag3 = true;
									}
								}
						
							if (posInEmpOfManager != -1) {	
								Manager newManager =(Manager) super.Employees.get(posInEmpOfManager);
								System.out.println("The new Manager is going to be: " + newManager.getFirstname() + " " + newManager.getSurname() + "." + "\nAre you sure you want to make that change?");
								boolean flag10 = false;
								do {
									System.out.println("yes/no");
									String verify = sc.nextLine();
									if (verify.toLowerCase().equals("yes")) {
										super.Employees.get(posInEmployees).setManager(newManager);
										System.out.println("The change has been made.");
										flag10 = false;
									}else if (verify.toLowerCase().equals("no")){
										System.out.println("Change cancelled.");
										flag10 = false;
									}else {
										flag10 = true;
									}
									}while(flag10);
							}
							}while(flag3);
							break;
						case 3:
							menuflag = true;
							break;
						}
						
					} while (flag1);
					
				}else {
					menuflag = true;
				}
			}else if(selection == 12) {
				menuflag = false;
			}
		}while (menuflag);
	}
	public int enterEmpId() {
		Scanner sc = new Scanner(System.in);
		String selected;
		int selectedId, requested = -1;
		boolean flag1 = false;
		do {
			System.out.println("Enter the Employee's id or press Enter to return to central Menu.");
			selected = sc.nextLine();
			if (selected.equals("")) {
				flag1 = false;
				break;

			} else {
				try {
					selectedId = Integer.parseInt(selected);
					int posInEmployees = binarySearch(selectedId);
					if (posInEmployees != -1) {
						if (this.equals(super.Employees.get(posInEmployees).getManager())) {
							requested = posInEmployees;
							flag1 = false;
							break;
						} else {
							flag1 = true;
							System.out.println("You are not allowed to do that.");
						}
					} else {
						flag1 = true;
						System.out.println("That is not a valid Id.");
					}
				} catch (Exception b) {
					flag1 = true;
					System.out.println("Please insert an Integer.");
				}
			}
		} while (flag1);
		return requested;
	}

	public static int binarySearch(int x) {// x the id you want to find in the Employees and returns the position in
											// Employees
		int l = 0, r = Employee.Employees.size() - 1;
		while (l <= r) {
			int m = l + (r - l) / 2;
			if (Employee.Employees.get(m).getEmployee_Id() == x)
				return m;

			if (Employee.Employees.get(m).getEmployee_Id() < x)
				l = m + 1;
			else
				r = m - 1;
		}
		return -1;
	}

	public static int whereIsManager(int id) {// if the inserted id is a valid Manager id, the method returns the position in Employees, else returns -1.
		for (int i = 0; i < Employee.Employees.size(); i ++) {
			if (Employee.Employees.get(i).getEmployee_Id() == id) {
				if (Employee.Employees.get(i) instanceof Manager) {
					return i;
				} else {
					break;
				}

			}
		}
		return -1;
	}

}
