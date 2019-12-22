import java.util.*;

public class Hr_surface {
	
	public static void toRun(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Hr part!");
		//if (main.firstTime){ 	firstTime a boolean variable from main that shows if it
								// is the first time that the client enters Hr part
		
		//BEGINNING OF IF
		System.out.println("At the beginning is necessary to set up the hr data. \n");
		boolean directornotset;
		Hr_Director hrDirector = new Hr_Director("", "", "Hr Director", "", -1, null);
		do {
			directornotset = false;
			System.out.println("Hr Director's set up. \n");
			String firstname, surname, password = "";
			double salary = 0;
			boolean flag1;
			do {
				flag1 = false;
				System.out.print("Insert Hr Director's first name:");
				firstname = sc.nextLine();
				boolean flag2;
				do {
					flag2 = false;
					System.out.print("Insert Hr Director's surname or press Enter to go back:");
					surname = sc.nextLine();
					if (surname.equals("")) {
						flag1 = true;
						continue;
					}
					boolean flag3;
					do {
						flag3 = false;
						System.out.print("Insert Hr Director's password or press Enter to go back:");
						password = sc.nextLine();
						if (password.equals("")) {
							flag2 = true;
							continue;
						}
						boolean flag4;
						do {
							flag4 = false;
							System.out.print("Insert Hr Director's salary or press Enter to go back:");
							String selected = sc.nextLine();
							if (selected.equals("")) {
								flag3 = true;
								break;
		  
							} else { 
								try {
									salary = Double.parseDouble(selected);
									if (salary < 0) {
										flag4 = true;
										System.out.println("Insert a non negative salary.");
									}else if (!Employee.checkDecimalsSalary(salary)) {
										flag4 = true;
										System.out.println("Insert a number with 2 or less decimals.");
									}
								}catch(NumberFormatException b) {
									flag4 = true;
									System.out.println("Please insert a number.");
								}
							}
						}while(flag4);
					}while(flag3);
				}while(flag2);
			}while(flag1);
			System.out.println("Hr Director's data: \nfirst name: " + firstname + "\nsurname: " + surname + "\npassword: " + password + "\nsalary: " + salary
					+ "\nDo you want to save?");
			flag1 = false;
			do {
				System.out.println("yes/no");
				String verify = sc.nextLine();
				if (verify.toLowerCase().equals("yes")) {
					hrDirector.setFirstname(firstname);
					hrDirector.setSurname(surname);
					hrDirector.setPassword(password);
					hrDirector.setSalary(salary);
					System.out.println("Succesfully saved. \n" + hrDirector.toString());
					flag1 = false;
				}else if (verify.toLowerCase().equals("no")){
					System.out.println("Please insert the fields again.");
					flag1 = false;
					directornotset = true;
				}else {
					flag1 = true;
				}
			}while(flag1);
		}while(directornotset);
		
		System.out.println("Managers' set up. \n");
		boolean moremanagers;
		do {
			moremanagers = false;
			Manager manager = new Manager("" ,"" ,"" , "", -1, null);
				String firstname, surname, position = "", password = "", managerName = "";
				int mngrId, mngrPosition = -1;
				double salary = 0;
				boolean flag1;
				do {
					flag1 = false;
					System.out.print("Insert Manager's first name:");
					firstname = sc.nextLine();
					boolean flag2;
					do {
						flag2 = false;
						System.out.print("Insert Manager's surname or press Enter to go back:");
						surname = sc.nextLine();
						if (surname.equals("")) {
							flag1 = true;
							continue;
						}
						boolean flag3;
						do {
							flag3 = false;
							System.out.print("Insert Manager's position or press Enter to go back:");
							position = sc.nextLine();
							if (position.equals("")) {
								flag2 = true;
								continue;
							}
							boolean flag4;
							do {
								flag4 = false;
								System.out.print("Insert Manager's password or press Enter to go back:");
								password = sc.nextLine();
								if (password.equals("")) {
									flag3 = true;
									continue;
								}
								boolean flag5;
								do {
									flag5 = false;
									System.out.print("Insert Manager's salary or press Enter to go back:");
									String selected = sc.nextLine();
									if (selected.equals("")) {
										flag4 = true;
										break;
				
									} else {
										try {
											salary = Double.parseDouble(selected);
											if (salary < 0) {
												flag5 = true;
												System.out.println("Insert a non negative salary.");
												continue;
											}else if (!Employee.checkDecimalsSalary(salary)) {
												flag5 = true;
												System.out.println("Insert a number with 2 or less decimals.");
												continue;
											}
										}catch(NumberFormatException b) {
											flag5 = true;
											System.out.println("Please insert a number.");
											continue;
										}
									}
									boolean flag6;
									do {
										flag6 = false;
										System.out.print("Insert Manager's id of the Manager, insert -1 if the Manager doesn't have a Manager \n"
												+ "or press Enter to go back:");
										selected = sc.nextLine();
										if (selected.equals("")) {
											flag5 = true;
											break;
					
										} else {
											try {
												mngrId = Integer.parseInt(selected);
												mngrPosition = Manager.whereIsManager(mngrId);
												if (mngrId != -1 && mngrPosition == -1) {
													flag6 = true;
													System.out.println("That is not a valid Manager id.");
													continue;
												}else if (mngrId == -1) {
													managerName = "no Manager";
												}else {
													managerName = Employee.Employees.get(mngrPosition).getFirstname();
													managerName += " " + Employee.Employees.get(mngrPosition).getSurname();
												}
											}catch(NumberFormatException b) {
												flag6 = true;
												System.out.println("Please insert an Integer.");
												continue;
											}
										}
										boolean flag7;
										do {
											flag7 = false;
											String [] shiftStr = Shift.insertShiftStr();
											if (shiftStr[0].equals("no shift")){
												flag6 = true;
												break;
											}
											try {
												manager.setThisWeekShift(Shift.createShift(shiftStr));								
											}catch (ShiftException e) {
												System.err.println("Mistake with the inserted shift. " + e);
												flag7 = true;
												continue;
											}
											System.out.println("Manager's shift:");
											Employee.printShift(manager.getThisWeekShift());
											System.out.println("Do you want to save the shift for the Manager?");
											boolean flag8;
											do {
												flag8 = false;
												System.out.println("yes/no");
												String verify = sc.nextLine();
												if (verify.toLowerCase().equals("yes")) {
													System.out.println("Succesfully saved. ");
												}else if (verify.toLowerCase().equals("no")){
													System.out.println("Shift has not been saved, please insert a new shift.");
													flag7 = true;
												}else {
													flag8 = true;
												}
											}while(flag8);
										}while(flag7);
									}while(flag6);
								}while(flag5);
							}while(flag4);
						}while(flag3);
					}while(flag2);
				}while(flag1);
				System.out.println("Manager's data: \nfirst name: " + firstname + "\nsurname: " + surname + "\nposition: " + position +
						"\npassword: " + password + "\nsalary: " + salary + "\nmanager name :" + managerName
						+ "\nDo you want to save?");
				flag1 = false;
				do {
					System.out.println("yes/no");
					String verify = sc.nextLine();
					if (verify.toLowerCase().equals("yes")) {
						manager.setFirstname(firstname);
						manager.setSurname(surname);
						manager.setPosition(position);
						manager.setPassword(password);
						manager.setSalary(salary);
						if(mngrPosition != -1) {
							manager.setManager((Manager) Employee.Employees.get(mngrPosition));
						}
						System.out.println("Succesfully saved. \n" + manager.toString());
						flag1 = false;
					}else if (verify.toLowerCase().equals("no")){
						System.out.println("Manager has not been saved.");
						Employee.removeLastEmployee();
						flag1 = false;
					}else {
						flag1 = true;
					}
				}while(flag1);
				System.out.println("Do you want to add more Managers?");
				flag1 = false;
				do {
					System.out.println("yes/no");
					String verify = sc.nextLine();
					if (verify.toLowerCase().equals("yes")) {
						moremanagers = true;
						flag1 = false;
					}else if (verify.toLowerCase().equals("no")){
						//moremanagers is false
						System.out.println("Managers' insertion completed.");
						flag1 = false;
					}else {
						flag1 = true;
					}
				}while(flag1);
		}while(moremanagers);
		
		System.out.println("Managers \n--------");
		int firstemppos;
		for(int i = 1; i < Employee.Employees.size(); i++) {
			System.out.println(Employee.Employees.get(i).toString());
		}
		firstemppos = Employee.Employees.size();
		System.out.println("\nEmployees' set up. \n");
		boolean moreemployees;
		do {
			moreemployees = false;
			Employee employee = new Employee("" ,"" ,"" , "", -1, null);
			String firstname, surname, position = "", password = "", managerName = "";
			int mngrId, mngrPosition = -1;
			double salary = 0;
			boolean flag1;
			do {
				flag1 = false;
				System.out.print("Insert Employee's first name:");
				firstname = sc.nextLine();
				boolean flag2;
				do {
					flag2 = false;
					System.out.print("Insert Employee's surname or press Enter to go back:");
					surname = sc.nextLine();
					if (surname.equals("")) {
						flag1 = true;
						continue;
					}
					boolean flag3;
					do {
						flag3 = false;
						System.out.print("Insert Employee's position or press Enter to go back:");
						position = sc.nextLine();
						if (position.equals("")) {
							flag2 = true;
							continue;
						}
						boolean flag4;
						do {
							flag4 = false;
							System.out.print("Insert Employee's password or press Enter to go back:");
							password = sc.nextLine();
							if (password.equals("")) {
								flag3 = true;
								continue;
							}
							boolean flag5;
							do {
								flag5 = false;
								System.out.print("Insert Employee's salary or press Enter to go back:");
								String selected = sc.nextLine();
								if (selected.equals("")) {
									flag4 = true;
									break;
				
								} else {
									try {
										salary = Double.parseDouble(selected);
										if (salary < 0) {
											flag5 = true;
											System.out.println("Insert a non negative salary.");
											continue;
										}else if (!Employee.checkDecimalsSalary(salary)) {
											flag5 = true;
											System.out.println("Insert a number with 2 or less decimals.");
											continue;
										}
									}catch(NumberFormatException b) {
										flag5 = true;
										System.out.println("Please insert a number.");
										continue;
									}
								}
								boolean flag6;
								do {
									flag6 = false;
									System.out.print("Insert Manager's id of the Employee \n"
											+ "or press Enter to go back:");
									selected = sc.nextLine();
									if (selected.equals("")) {
										flag5 = true;
										break;
					
									} else {
										try {
											mngrId = Integer.parseInt(selected);
											mngrPosition = Manager.whereIsManager(mngrId);
											if (mngrPosition == -1) {
												flag6 = true;
												System.out.println("That is not a valid Manager id.");
												continue;
											}else {
												managerName = Employee.Employees.get(mngrPosition).getFirstname();
												managerName += " " + Employee.Employees.get(mngrPosition).getSurname();
											}
										}catch(NumberFormatException b) {
											flag6 = true;
											System.out.println("Please insert an Integer.");
											continue;
										}
									}
									boolean flag7;
									do {
										flag7 = false;
										String [] shiftStr = Shift.insertShiftStr();
										if (shiftStr[0].equals("no shift")){
											flag6 = true;
											break;
										}
										try {
											employee.setThisWeekShift(Shift.createShift(shiftStr));								
										}catch (ShiftException e) {
											System.err.println("Mistake with the inserted shift. " + e);
											flag7 = true;
											continue;
										}
										System.out.println("Manager's shift:");
										Employee.printShift(employee.getThisWeekShift());
										System.out.println("Do you want to save the shift for the Employee?");
										boolean flag8;
										do {
											flag8 = false;
											System.out.println("yes/no");
											String verify = sc.nextLine();
											if (verify.toLowerCase().equals("yes")) {
												System.out.println("Succesfully saved. ");
											}else if (verify.toLowerCase().equals("no")){
												System.out.println("Shift has not been saved, please insert a new shift.");
												flag7 = true;
											}else {
												flag8 = true;
											}
										}while(flag8);
									}while(flag7);
								}while(flag6);
							}while(flag5);
						}while(flag4);
					}while(flag3);
				}while(flag2);
			}while(flag1);
			System.out.println("Employee's data: \nfirst name: " + firstname + "\nsurname: " + surname + "\nposition: " + position +
					"\npassword: " + password + "\nsalary: " + salary + "\nmanager name :" + managerName
					+ "\nDo you want to save?");
			flag1 = false;
			do {
				System.out.println("yes/no");
				String verify = sc.nextLine();
				if (verify.toLowerCase().equals("yes")) {
					employee.setFirstname(firstname);
					employee.setSurname(surname);
					employee.setPosition(position);
					employee.setPassword(password);
					employee.setSalary(salary);
					employee.setManager((Manager) Employee.Employees.get(mngrPosition));
					System.out.println("Succesfully saved. \n" + employee.toString());
					flag1 = false;
				}else if (verify.toLowerCase().equals("no")){
					System.out.println("Employee has not been saved.");
					Employee.removeLastEmployee();
					flag1 = false;
				}else {
					flag1 = true;
				}
			}while(flag1);
			System.out.println("Do you want to add more Employees?");
			flag1 = false;
			do {
				System.out.println("yes/no");
				String verify = sc.nextLine();
				if (verify.toLowerCase().equals("yes")) {
					moreemployees = true;
					flag1 = false;
				}else if (verify.toLowerCase().equals("no")){
					//moreemployees is false
					System.out.println("Employees' insertion completed.");
						flag1 = false;
					}else {
					flag1 = true;
				}
			}while(flag1);
		}while(moreemployees);
		
		System.out.println("Employees \n--------");
		for(int i = firstemppos; i < Employee.Employees.size(); i++) {
			System.out.println(Employee.Employees.get(i).toString());
		}
		//END OF IF
		//}
	
		
		boolean stayAtHR;
		do {
			stayAtHR = true;	
			System.out.println("Press Enter to login or Space to return to basic menu.");
			String selection = sc.nextLine();
			if (selection.equals("")){ 
				System.out.println("LOGIN \n------");
				boolean  wrongInput;
				int id = -1;
				do {
					 wrongInput = false; 
					 try {
						System.out.print("Id:");
						id = sc.nextInt();
						sc.nextLine();
					 }catch (InputMismatchException ime) {
						 System.out.println("Invalid input.");
						 wrongInput = true;	
						 sc.nextLine();
					 }
				}while (wrongInput);			
				System.out.print("Password:");
				String password = sc.nextLine();
				int posInEmployees = Employee.login(id, password);
				if (posInEmployees != -1) {
					Employee.Employees.get(posInEmployees).getMenu();
				}else {
					System.out.println("Wrong id or password.");
				}	
			}else if(selection.equals(" ")) {
				stayAtHR = false;
			}
		}while(stayAtHR);
	}

	public static void toBeDoneEveryWeek() {
		for (Employee a : Employee.Employees) {
			try {
				a.setThisWeekShift(Shift.createShift(a.getShiftStr()));// has not a purpose at this project because of
																		// the difficulties at the check but it could be
																		// used in real circumstances
				a.setWresyperergasias_evdomadiaiws(0);
			} catch (ShiftException e) {
				// check has been done it will never reach here, if there was an error with the
				// input Exception would have been caught and handled before
			}
		}
	}

	public static void toBeDoneEveryMonth() {
		double totalSalaryOutcome = 0;
		for (Employee a : Employee.Employees) {
			totalSalaryOutcome += a.getMonthPayment();
			a.setMonthPayment(a.getSalary());
		}
		//SEND totalSalaryOutcome to Finance and Reporting
	}
}
