import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Manager extends Employee { // responsible for the Employees who have him as Manager except their basic
										// shifts
	private int employee_Id, extraHoursMonth = 0 /* need to be set to 0 every month */;
	private int monthPayment;
	private Calendar[][] weekShift = new Calendar[8][8];
	private Calendar dailyTimes[] = new Calendar[8]; // shows the arrivals and departures of the employee in one day
	private int dayCounter = 0;// shows how many indexes of the array dailyTimes are filled
	private boolean checkedIn = false; // true when the employee has checked in but hasn't checked out

	public Manager(String firstname, String surname, String position, String password, int salary, Manager manager) {
		super(firstname, surname, position, password, salary, manager);
	} 
	
	public Manager (Employee employee) {//for the promotion at the Hr Director class
		super(employee);
	}
	
	public void getMenu() {
		Scanner sc = new Scanner(System.in);
		boolean menuflag = true;//must add a log out option that makes menuflag false
		System.out.println("Welcome!");
		do {
			System.out.println(" MANAGER MENU \n------------- \n Select: \n1)Check in.\n2)Check out. \n3)Day off request. \n4)Inbox. \n5)Show shift of the week. "
					+ "\n6)View Employees. \n7)Show check in status of Employees.\n8)Set  extra hours for an Employee. \n9)Edit an Employee's payment. \n10)Edit an Employee's fields.");
			boolean flag = false;
			int selection = 0;
			do {
				if (!sc.hasNextInt()) {
					System.out.println("Please insert an integer 1 - 10");
					flag = true;
					sc.next();
	
				} else {
					selection = sc.nextInt();
					if (selection > 10 || selection < 1) {
						flag = true;
						System.out.println("input an integer [1,10]");
					} else {
						flag = false;
						sc.nextLine();
					}
				}
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
				printShift(this);
			}else if (selection == 6) {
				boolean onefound = false;
				for (Employee a : super.Employees) { // BINARY SEARCH MAYBE?
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
				for (Employee a : super.Employees) { // BINARY SEARCH MAYBE?
					if (this.equals(a.getManager())) {
						onefound = true;
						if (a.isCheckedIn()) {
							status = "Checked in";
						}else {
							status = "Checked out";
						}
						System.out.println("Id:" + a.getEmployee_Id() + a.getFirstname() + a.getSurname() + "status:" + status);
					}
				}
				if (!onefound) {
					System.out.println("No employees found.");
				}
			}else if (selection == 8) {
				//related to inbox 
				
			}else if (selection == 9) {
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
						System.out.println("The current salary is " + super.Employees.get(posInEmployees).getSalary());
						System.out.println("Set the new salary:");
						boolean flag2 = false;
						int newSalary = super.Employees.get(posInEmployees).getSalary();
						do {
							if (!sc.hasNextInt()) {
								System.out.println("Please insert an integer.");
								flag2 = true;
								sc.next();
		
							} else {
								newSalary = sc.nextInt();
								if (newSalary < 0) {
									flag2 = true;
									System.out.println("Input an integer greater than zero.");
								}else if(newSalary == super.Employees.get(posInEmployees).getSalary()) {
									System.out.println("Insert a salary different than the previous one.");
								}else {
									flag2 = false;
									sc.nextLine();
								}
							}
							
						} while (flag2);
						System.out.println("The new salary is going to be set to" + newSalary + "/nare you sure you want to make that change?");
						boolean flag3 = false;
						do {
							System.out.println("yes/no");
							String verify = sc.nextLine();
							if (verify.toLowerCase().equals("yes")) {
								super.Employees.get(posInEmployees).setSalary(newSalary);
								System.out.println("The change has been made.");
								flag3 = false;
							}else if (verify.toLowerCase().equals("no")){
								System.out.println("Change cancelled");
								flag3 = false;
							}else {
								flag3 = true;
							}
						}while(flag3);
						break;
					
					case 2:
						System.out.println("The current payment is " + super.Employees.get(posInEmployees).getMonthPayment());
						System.out.println("Set the new payment:");
						boolean flag4 = false;
						int newPayment = super.Employees.get(posInEmployees).getMonthPayment();
						do {
							if (!sc.hasNextInt()) {
								System.out.println("Please insert an integer.");
								flag4 = true;
								sc.next();
		
							} else {
								newPayment = sc.nextInt();
								if (newPayment < 0) {
									flag4 = true;
									System.out.println("Input an integer greater than zero.");
								}else if(newPayment == super.Employees.get(posInEmployees).getMonthPayment()) {
									System.out.println("Insert a payment different than the previous one.");
								}else {
									flag4 = false;
									sc.nextLine();
								}
							}
							
						} while (flag4);
						System.out.println("The new payment is going to be set to" + newPayment + "." + "\nAre you sure you want to make that change?");
						boolean flag5 = false;
						do {
							System.out.println("yes/no");
							String verify = sc.nextLine();
							if (verify.toLowerCase().equals("yes")) {
								super.Employees.get(posInEmployees).setMonthPayment(newPayment);
								System.out.println("The change have been made.");
								flag5 = false;
							}else if (verify.toLowerCase().equals("no")){
								System.out.println("Change cancelled");
								flag5 = false;
							}else {
								flag5 = true;
							}
						}while(flag5);
						break;
					
					case 3:
						menuflag = true;
						break;
					}	
				}else {
					menuflag = true;
				}
			}else if(selection == 10) {
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
							int selectedId, posInEmployees2 = -1;
							boolean flag3 = false;
							do {	
								System.out.println("Enter the id of the new Manager or press Enter to return to central Menu.");
								selected = sc.nextLine();
								if (selected.equals("")) {
									flag3 = false;
									posInEmployees2 = -1;
								}else {
									try {
										selectedId = Integer.parseInt(selected);
											boolean found = false;
											for (int i = 0; i < super.Employees.size(); i++) { // BINARY SEARCH CAN BE USED HERE
												if (super.Employees.get(i).getEmployee_Id() == selectedId) {	
													found = true;
													if (selectedId != this.employee_Id && super.Employees.get(i) instanceof Manager) { //checks that the new Manager is not the same with the current and that the new Manager is a Manager and not a basic Employee
														posInEmployees2 = i;
														flag3 = false;
														break;
													}else {
														flag3 = true;
														System.out.println("You are not allowed to do that.");
													}
												}
												
											}
											if (!found) {
												flag3 = true;
												System.out.println("That is not a valid id.");
											}
									}catch (Exception d) {
										System.out.println("Please insert an Integer.");
										flag3 = true;
									}
								}
						
							if (posInEmployees2 != -1) {	
								Manager newManager =(Manager) super.Employees.get(posInEmployees2);
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
					boolean found = false;
					for (int i = 0; i < super.Employees.size(); i++) { //BINARY SEARCH CAN BE USED HERE
						if (super.Employees.get(i).getEmployee_Id() == selectedId) {
							found = true;
							if (this.equals(super.Employees.get(i).getManager())) {
								requested = i;
								flag1 = false;
								break;
							}else {
								flag1 = true;
								System.out.println("You are not allowed to do that.");
								break;
							}
						}
						
					}
					if (!found) {
						flag1 = true;
						System.out.println("That is not a valid Id.");
					}
				}catch(Exception b) {
					flag1 = true;
					System.out.println("Please insert an Integer.");
				}
			}	
		} while (flag1);
		return requested;
	}
	
}
