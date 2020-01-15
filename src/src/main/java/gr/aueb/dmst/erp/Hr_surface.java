package src.main.java.gr.aueb.dmst.erp;
import java.util.Scanner;
import java.util.Calendar;
import java.util.InputMismatchException;

/**
 * The class that contains the methods the will be directly called by the main class.
 * 
 * @author Nilolaos Antonopoulos, Georgios Sideris
 */

public class Hr_surface {
	
	/** 
	 * The basic Hr method
	 * Contains the first initializations of the Hr, possibly won't be used at this project though
	 * Contains the logging in procedure that will call the proper getMenu method
	 */
	 
	public static void toRun(){
		Scanner sc = new Scanner(System.in);
		//if (main.firstTime){ 	firstTime a boolean variable from main that shows if it
								// is the first time that the client enters Hr part
		
		
/*
	//	if (mainClass.IsHrCalledOnce()) {
		System.out.println("At the beginning is necessary to set up the hr data. \n");
		boolean directornotset;
		Hr_Director hrDirector = new Hr_Director("", "", "", -1);
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
										System.out.print(
												"Insert Manager's id of the Manager or press Enter to go back:");
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
												} else {
													managerName = Employee.Employees.get(mngrPosition)
															.getFirstname();
													managerName += " "
															+ Employee.Employees.get(mngrPosition).getSurname();
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
													manager.setShiftStr(shiftStr);
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
						manager.setManager((Manager) Employee.Employees.get(mngrPosition));
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
											}else if (mngrPosition == 0) {//hr director is manager only for managers
														flag6 = true;
														System.out.println("You are not allowed to do that.");
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
												employee.setShiftStr(shiftStr);
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
		//}

	*/	
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
					System.out.println("Wrong Id or Password.");
				}	
			}else if(selection.equals(" ")) {
				stayAtHR = false;
			}
		}while(stayAtHR);
	}

	/**
	 * The method called from main for the weekly procedures.
	 */
	public static void toBeDoneEveryWeek() {
		for (Employee a : Employee.Employees) {
			try {
				a.setThisWeekShift(Shift.createShift(a.getShiftStr()));
				a.setWresyperergasias_evdomadiaiws(0);
			} catch (ShiftException e) {
				// check has been done it will never reach here, if there was an error with the
				// input Exception would have been caught and handled before
			}
		}
	}

	/**
	 * The method called from main for the monthly procedures.
	 */
	public static void toBeDoneEveryMonth() {
		double totalSalaryOutcome = 0;
		for (Employee a : Employee.Employees) {
			totalSalaryOutcome += a.getMonthPayment();
			a.setMonthPayment(a.getSalary());
		}
	}
	/**
	 * Creates the objects
	 */
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
		Kobe.setShiftStr(shift);
		Kobe.setThisWeekShift(shiftcal);
		Petreas.setShiftStr(shift);
		Petreas.setThisWeekShift(shiftcal);
		Dumbledore.setShiftStr(shift);
		Dumbledore.setThisWeekShift(shiftcal);
		Leo.setShiftStr(shift);
		Leo.setThisWeekShift(shiftcal);
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
		Rick.setShiftStr(shift2);
		Rick.setThisWeekShift(shiftcal);
		NF.setShiftStr(shift2);
		NF.setThisWeekShift(shiftcal);
		Ron.setShiftStr(shift2);
		Ron.setThisWeekShift(shiftcal);
		Lionel.setShiftStr(shift2);
		Lionel.setThisWeekShift(shiftcal);
		String[] shift3 = { "7:00-14:00", "7:00-14:00", "7:00-14:00", "7:00-14:00", "7:00-14:00", "-", "-",
		"7:00-14:00" };
		try {
			shiftcal = Shift.createShift(shift3);
		} catch (ShiftException a) {
			System.out.println("wrong3 " + a);
		}
		Cristiano.setShiftStr(shift3);
		Cristiano.setThisWeekShift(shiftcal);
		Margaret.setShiftStr(shift3);
		Margaret.setThisWeekShift(shiftcal);
		Angela.setShiftStr(shift3);
		Angela.setThisWeekShift(shiftcal);
		Agatha.setShiftStr(shift3);
		Agatha.setThisWeekShift(shiftcal);
		
				

	}
}
