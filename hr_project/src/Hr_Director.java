import java.util.*;
public class Hr_Director extends Manager{ // is resposible for all the Managers (even if they have another Manager) and the basic shifts of all the Employees
	private String firstname, surname, position , password;
	private int employee_Id, extraHoursMonth = 0 /*need to be set to 0 every month*/, salary, monthPayment;
	
	public Hr_Director(String firstname, String surname, String position, String password, int salary,
			Manager manager) {// position will be set to Hr Director and Manager to null
		super(firstname, surname, position, password, salary, manager);
		this.employee_Id = 0;
	}
	
	public void getMenu() {
		Scanner sc = new Scanner(System.in);
		boolean menuflag = true;//must add a log out option that makes menuflag false
		System.out.println("Welcome!");
		do {
			System.out.println(" HR DIRECT0R MENU \n------------- \n Select: \n1)Inbox. \n2)View Managers. \n3)Show check in status of Managers. \n"
					+ "4)Set extra hours for a Manager. \n5)Edit a Manager's payment. \n6)Edit a Manager's fields. \n7)Edit an Employer's shift. "
					+ "\n8)Hire a new member. \n9)Remove a member. \n10)Promote to Manager. ");
			boolean flag = false;
			int selection = 0;
			do { 
				if (!sc.hasNextInt()) {
					System.out.println("Please insert an integer 1 - 9");
					flag = true;
					sc.next();
	
				} else {
					selection = sc.nextInt();
					if (selection > 8 || selection < 1) {
						flag = true;
						System.out.println("input an integer [1,9]");
					} else {
						flag = false;
						sc.nextLine();
					}
				}
			} while (flag);
			if (selection == 1) {
				//INB0X RELATED
			}else if (selection == 2){
				boolean onefound = false;
				for (Employee a : Employee.Employees) { // BINARY SEARCH MAYBE?
					if (a instanceof Manager) {
						onefound = true;
						System.out.println(a.toString());
					}
				}
				if (!onefound) {
					System.out.println("No Managers found.");
				}
			}else if (selection == 3) {
				boolean onefound = false;
				String status;
				for (Employee a : Employee.Employees) { // BINARY SEARCH MAYBE?
					if (a instanceof Manager) {
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
					System.out.println("No Managers found.");
				}
			}else if (selection == 4) {
				//related to inbox
			}else if (selection == 5) {
				int posInEmployees = enterManagerId();
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
						System.out.println("The current salary is " + Employee.Employees.get(posInEmployees).getSalary());
						System.out.println("Set the new salary:");
						boolean flag2 = false;
						int newSalary = Employee.Employees.get(posInEmployees).getSalary();
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
								}else if(newSalary == Employee.Employees.get(posInEmployees).getSalary()) {
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
								Employee.Employees.get(posInEmployees).setSalary(newSalary);
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
						System.out.println("The current payment is " + Employee.Employees.get(posInEmployees).getMonthPayment());
						System.out.println("Set the new payment:");
						boolean flag4 = false;
						int newPayment = Employee.Employees.get(posInEmployees).getMonthPayment();
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
								}else if(newPayment == Employee.Employees.get(posInEmployees).getMonthPayment()) {
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
								Employee.Employees.get(posInEmployees).setMonthPayment(newPayment);
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
			}else if (selection == 6) {
				int posInEmployees = enterManagerId();
				if (posInEmployees != -1) {
					System.out.println(Employee.Employees.get(posInEmployees).toString());
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
							System.out.println("The current position is " + Employee.Employees.get(posInEmployees).getPosition());
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
										Employee.Employees.get(posInEmployees).setPosition(pos);
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
							System.out.println("The current manager is " + Employee.Employees.get(posInEmployees).getManager().getFirstname() +
									" " + Employee.Employees.get(posInEmployees).getManager().getSurname() +".");
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
											for (int i = 0; i < Employee.Employees.size(); i++) { // BINARY SEARCH CAN BE USED HERE
												if (Employee.Employees.get(i).getEmployee_Id() == selectedId) {	
													found = true;
													if (selectedId != this.employee_Id && Employee.Employees.get(i) instanceof Manager) { //checks that the new Manager is not the same with the current and that the new Manager is a Manager and not a basic Employee
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
								Manager newManager =(Manager) Employee.Employees.get(posInEmployees2);
								System.out.println("The new Manager is going to be: " + newManager.getFirstname() + " " + newManager.getSurname() + "." + "\nAre you sure you want to make that change?");
								boolean flag10 = false;
								do {
									System.out.println("yes/no");
									String verify = sc.nextLine();
									if (verify.toLowerCase().equals("yes")) {
										Employee.Employees.get(posInEmployees).setManager(newManager);
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
			}else if(selection == 7) {
				boolean shiftflag = false;
				do {
					String selected;
					int selectedId, posInEmployees = -1;
					boolean flag1 = false;
					do {
						System.out.println("Enter the Employee's id or press Enter to return to central Menu.");
						selected = sc.nextLine();
						if (selected.equals("")) {
							shiftflag = false;
							flag1 = false;
							break;
	
						} else {
							try {
								selectedId = Integer.parseInt(selected);
								boolean found = false;
								for (int i = 0; i < Employee.Employees.size(); i++) { //BINARY SEARCH CAN BE USED HERE
									if (Employee.Employees.get(i).getEmployee_Id() == selectedId) {
										flag1 = false;
										found = true;
										posInEmployees = i;
										break;
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
					if (posInEmployees != -1) {
						boolean flag2;
						do {
							flag2 = false;
							System.out.println(Employee.Employees.get(posInEmployees).toString());
							printShift(Employee.Employees.get(posInEmployees));
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
									dayInt = 1;
									break;
								case "tuesday":
									dayInt = 2;
									break;
								case "wednesday":
									dayInt = 3;
									break;
								case "thursday":
									dayInt = 4;
									break;
								case "friday":
									dayInt = 5;
									break;
								case "saturday":
									dayInt = 6;
									break;
								case "sunday":
									dayInt = 7;
									break;
								case "exit":
									menuflag = true;
								default:
									System.out.println("That is not a valid input.");
									flag3 = true;
								}
							}while (flag3);
							if(shiftflag == false && dayInt !=-1) {
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
										if (dayInt == 7) { // sunday is both at positions 0 and 7
											shiftStr[0] = inputShift;
											shiftStr[7] = inputShift;
										}else {
											shiftStr[dayInt] = inputShift;
											}
										try {
											Shift.createShift(shiftStr);
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
			}else if(selection == 8) {
				//IT WILL BE ADDED LATER
			}else if(selection == 9) {
				String selected;
				int selectedId, posInEmployees = -1;
				boolean flag1;
				do {
					flag1 = false;
					System.out.println("Enter the Employee's id or press Enter to return to central Menu.");
					selected = sc.nextLine();
					if (selected.equals("")) {
						menuflag = true;
						break;
					} else {
						try {
							selectedId = Integer.parseInt(selected);
							boolean found = false;
							for (int i = 0; i < Employee.Employees.size(); i++) { //BINARY SEARCH CAN BE USED HERE
								if (Employee.Employees.get(i).getEmployee_Id() == selectedId) {
									found = true;
									posInEmployees = i;
									break;
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
				if (posInEmployees != -1) {
					System.out.println(Employee.Employees.get(posInEmployees).toString());
					System.out.println("You are going to remove " + Employee.Employees.get(posInEmployees).getFirstname() 
							+ " " + Employee.Employees.get(posInEmployees).getSurname() + ". \nAre you sure?");
					boolean flag2 = false;
					do {
						System.out.println("yes/no");
						String verify = sc.nextLine();
						if (verify.toLowerCase().equals("yes")) {
							Employee.Employees.remove(posInEmployees);
							System.out.println("The removal has been made.");
							flag2 = false;
						}else if (verify.toLowerCase().equals("no")){
							System.out.println("Removal cancelled.");
							flag2 = false;
						}else {
							flag2 = true;
						}
					}while(flag2);
				}
			}else if(selection == 10) {
				String selected;
				int selectedId, posInEmployees = -1;
				boolean flag1;
				do {
					flag1 = false;
					System.out.println("Enter the Employee's id or press Enter to return to central Menu.");
					selected = sc.nextLine();
					if (selected.equals("")) {
						menuflag = true;
						break;
					} else {
						try {
							selectedId = Integer.parseInt(selected);
							boolean found = false;
							for (int i = 0; i < Employee.Employees.size(); i++) { //BINARY SEARCH CAN BE USED HERE
								if (Employee.Employees.get(i).getEmployee_Id() == selectedId) {
									found = true;
									if (Employee.Employees.get(i) instanceof Manager == false)	{ //if it isn't instanceof Manager it is a basic Employee
										posInEmployees = i;
									}else {
										System.out.println("Already a Manager.");
									}
									break;
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
				if (posInEmployees != -1) {
					Manager a = new Manager(Employee.Employees.get(posInEmployees));
					Employee.Employees.set(posInEmployees, a);
				}
			}
		}while(menuflag);
	}
	
	
	public int enterManagerId() {
		Scanner sc = new Scanner(System.in);
		String selected;
		int selectedId, requested = -1;
		boolean flag1 = false;
		do {
			System.out.println("Enter the Manager's id or press Enter to return to central Menu.");
			selected = sc.nextLine();
			if (selected.equals("")) {
				flag1 = false;
				break;

			} else {
				try {
					selectedId = Integer.parseInt(selected);
					boolean found = false;
					for (int i = 0; i < Employee.Employees.size(); i++) { //BINARY SEARCH CAN BE USED HERE
						if (Employee.Employees.get(i).getEmployee_Id() == selectedId) {
							found = true;
							if (Employee.Employees.get(i) instanceof Manager) {
								requested = i;
								flag1 = false;
								break;
							}else {
								flag1 = true;
								System.out.println("That Id doesn't refer to a Manager.");
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
