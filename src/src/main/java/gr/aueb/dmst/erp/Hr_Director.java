package src.main.java.gr.aueb.dmst.erp;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * The class that refers to the Hr Director Extends the Manager class and has
 * not any extra fields. Every Manager is responsible for the Employees who have
 * him as Manager. Hr Director is responsible for all the Managers (even if they
 * have another Manager).
 * 
 * @author Nilolaos Antonopoulos, Georgios Sideris
 *
 */
public class Hr_Director extends Manager {

	/**
	 * Constructor of Hr_Director with arguments firstname, surname, password,
	 * salary position set to "Hr Director" and Manager to null
	 * 
	 * @param firstname
	 * @param surname
	 * @param password
	 * @param salary
	 */
	public Hr_Director(String firstname, String surname, String password, int salary) {
		super(firstname, surname, password, salary);
		this.setPosition("Hr director");
		this.setManager(null);
	}

	/**
	 * The method that prints the basic characteristics of the Hr_Director, except
	 * of manager.
	 */
	@Override
	public String toString() {
		return "Hr Director [firstname=" + getFirstname() + ", surname=" + getSurname() + ", position=" + getPosition()
				+ ", employee_Id=" + getEmployee_Id() + ", salary=" + getSalary() + "]";
	}

	/** The method of the menu of the Hr_Director. */
	public void getMenu() {
		Scanner sc = new Scanner(System.in);
		boolean menuflag = true;
		System.out.println("Welcome!");
		do {
			System.out.println("---------------- HR DIRECTOR MENU ---- " + getDate() + " ---------------" + "\n1) Inbox"
					+ "\n2) View Managers" + "\n3) Show check in status of Managers"
					+ "\n4) Set extra hours for a Manager " + "\n5) Edit a Manager's payment"
					+ "\n6) Edit a Manager's fields" + "\n7) Edit an Employee's shift" + "\n8) Hire a new member"
					+ "\n9) Remove a member" + "\n10) Promote to Manager" + "\n11) Log out"
					+ "\n------------------- CHOOSE A NUMBER BETWEEN 1 AND 11 ---------------------");
			boolean flag = false;
			int selection = 0;
			do {
				if (!sc.hasNextInt()) {
					System.out.println("Please insert an integer 1 - 11");
					flag = true;
					sc.next();

				} else {
					selection = sc.nextInt();
					if (selection > 11 || selection < 1) {
						flag = true;
						System.out.println("input an integer [1,11]");
					} else {
						flag = false;
						sc.nextLine();
					}
				}
			} while (flag);
			if (selection == 1) {
				int epilogh = 0;
				boolean flag3 = false;
				do {
					System.out.println("---------------- INBOX ---- " + getDate() + " --------------" + "\n1) Send Mail"
							+ "\n2) View Mails" + "\n3) Exit"
							+ "\n--------------- CHOOSE A NUMBER BETWEEN 1 AND 3 ---------------");
					if (!sc.hasNextInt()) {
						System.out.println("Insert 1 or 2 or 3");
						flag3 = true;
						sc.next();

					} else {
						epilogh = sc.nextInt();
						if (epilogh > 3 || epilogh < 1) {
							flag3 = true;
							System.out.println("Input an integer [1,3]");
						} else {
							flag3 = false;
						}
						sc.nextLine();
					}
				} while (flag3);
				if (epilogh == 1) {
					mhnyma();
				} else if (epilogh == 2) {
					for (int i = 0; i < Employee.Employees.get(this.getEmployee_Id()).getMaxmail(); i++) {
						System.out.println(Employee.Employees.get(this.getEmployee_Id()).newmail[i]);
					}
					if (Employee.Employees.get(this.getEmployee_Id()).getMaxmail() == 0) {
						System.out.println("You've got no mails :(");
					}
				} else {

				}
			} else if (selection == 2) {
				showManagers();
			} else if (selection == 3) {
				showCheckInStatusManagers();
			} else if (selection == 4) {
				Yperoria();
			} else if (selection == 5) {
				int posInEmployees = enterManagerId();

				if (posInEmployees != -1) {
					System.out.println(Employee.Employees.get(posInEmployees).toString());
					boolean flag1 = false;
					int sel = 0;
					System.out.println("------------- EDIT MANAGER'S PAYMENT ---- " + getDate() + " -------------"
							+ "\n1) Edit salary" + "\n2) Edit payment for the current month" + "\n3) Exit"
							+ "\n--------------------- CHOOSE A NUMBER BETWEEN 1 AND 3 ---------------------");
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
						System.out.println(
								"The current salary is " + Employee.Employees.get(posInEmployees).getSalary() + ".");
						String selected;
						double selectedSalary = -1;
						boolean flag2 = false;
						do {
							System.out.println("Set the new salary or press Enter to return to the basic Menu:");
							selected = sc.nextLine();
							if (selected.equals("")) {
								flag2 = false;
								break;

							} else {
								try {
									selectedSalary = Double.parseDouble(selected);
									if (selectedSalary < 0) {
										flag2 = true;
										System.out.println("Insert a non negative salary.");
									} else if (selectedSalary == Employee.Employees.get(posInEmployees).getSalary()) {
										flag2 = true;
										System.out.println("Insert a salary different than the previous one.");
									} else if (!checkDecimalsSalary(selectedSalary)) {
										flag2 = true;
										System.out.println("Insert a number with 2 or less decimals.");
										continue;
									} else {
										flag2 = false;
									}
								} catch (NumberFormatException b) {
									flag2 = true;
									System.out.println("Please insert a number.");
								}
							}
						} while (flag2);
						if (selectedSalary != -1) {
							System.out.println("The new salary is going to be set to " + selectedSalary
									+ ".\nAre you sure you want to make that change?");
							boolean flag3 = false;
							do {
								System.out.println("yes/no");
								String verify = sc.nextLine();
								if (verify.toLowerCase().equals("yes")) {
									Employee.Employees.get(posInEmployees).setSalary(selectedSalary);
									System.out.println("The change has been made.");
									flag3 = false;
								} else if (verify.toLowerCase().equals("no")) {
									System.out.println("Change cancelled");
									flag3 = false;
								} else {
									flag3 = true;
								}
							} while (flag3);
						}
						break;

					case 2:
						System.out.println("The current payment is "
								+ Employee.Employees.get(posInEmployees).getMonthPayment() + ".");
						String selected2;
						double selectedPayment = -1;
						boolean flag4 = false;
						do {
							System.out.println("Set the new payment or press Enter to return to the basic Menu:");
							selected2 = sc.nextLine();
							if (selected2.equals("")) {
								flag4 = false;
								break;

							} else {
								try {
									selectedPayment = Double.parseDouble(selected2);
									if (selectedPayment < 0) {
										flag4 = true;
										System.out.println("Insert a number greater than zero.");
									} else if (selectedPayment == Employee.Employees.get(posInEmployees)
											.getMonthPayment()) {
										flag4 = true;
										System.out.println("Insert a payment different than the previous one.");
									} else if (!checkDecimalsSalary(selectedPayment)) {
										flag4 = true;
										System.out.println("Insert a number with 2 or less decimals.");
										continue;
									} else {
										flag4 = false;
									}
								} catch (NumberFormatException b) {
									flag4 = true;
									System.out.println("Please insert a number.");
								}
							}
						} while (flag4);
						if (selectedPayment != -1) {
							System.out.println("The new payment is going to be set to " + selectedPayment
									+ ".\nAre you sure you want to make that change?");
							boolean flag5 = false;
							do {
								System.out.println("yes/no");
								String verify = sc.nextLine();
								if (verify.toLowerCase().equals("yes")) {
									Employee.Employees.get(posInEmployees).setMonthPayment(selectedPayment);
									System.out.println("The change has been made.");
									flag5 = false;
								} else if (verify.toLowerCase().equals("no")) {
									System.out.println("Change cancelled.");
									flag5 = false;
								} else {
									flag5 = true;
								}
							} while (flag5);
						}
						break;
					case 3:
						menuflag = true;
						break;
					}
				} else {
					menuflag = true;
				}
			} else if (selection == 6) {
				int posInEmployees = enterManagerId();
				if (posInEmployees != -1) {
					System.out.println(Employee.Employees.get(posInEmployees).toString());
					boolean flag1 = false;
					int sel = 0;

					System.out.println("------------- EDIT MANAGER'S FIELDS ---- " + getDate() + " --------------"
							+ "\n1) Change position" + "\n2) Change manager" + "\n3) Exit"
							+ "\n---------------------- CHOOSE A NUMBER BETWEEN 1 AND 3 ---------------------");

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
							System.out.println(
									"The current position is " + Employee.Employees.get(posInEmployees).getPosition());
							System.out.println("Set the new position or press Enter to return to central Menu:");
							String pos;
							pos = sc.nextLine();
							if (pos.equals("")) {
								menuflag = true;
							} else {
								System.out.println("The new position is going to be set to: " + pos
										+ ". \nAre you sure you want to make that change?");
								boolean flag2 = false;
								do {
									System.out.println("yes/no");
									String verify = sc.nextLine();
									if (verify.toLowerCase().equals("yes")) {
										Employee.Employees.get(posInEmployees).setPosition(pos);
										System.out.println("The change has been made.");
										flag2 = false;
									} else if (verify.toLowerCase().equals("no")) {
										System.out.println("Change cancelled.");
										flag2 = false;
									} else {
										flag2 = true;
									}
								} while (flag2);
							}
							break;
						case 2: // every Employee except the Hr_Director has a Manager
							System.out.println("The current manager is "
									+ Employee.Employees.get(posInEmployees).getManager().getFirstname() + " "
									+ Employee.Employees.get(posInEmployees).getManager().getSurname() + ".");
							String selected;
							int selectedId, posInEmpOfManager = -1;
							boolean flag3 = false;
							do {
								System.out.println(
										"Enter the id of the new Manager or press Enter to return to central Menu.");
								selected = sc.nextLine();
								if (selected.equals("")) {
									flag3 = false;
									posInEmpOfManager = -1;
								} else {
									try {
										selectedId = Integer.parseInt(selected);
										posInEmpOfManager = empBinarySearch(selectedId);

										if (posInEmpOfManager != -1) {
											if (selectedId != Employee.Employees.get(posInEmployees).getManager()
													.getEmployee_Id()
													&& selectedId != Employee.Employees.get(posInEmployees)
															.getEmployee_Id()
													&& Employee.Employees.get(posInEmpOfManager) instanceof Manager) {
												// checks that the new Manager is not the same with the current or with
												// the
												// employee whom field we want to edit and that the new Manager is a
												// Manager
												// and not a basic Employee
												flag3 = false;
											} else {
												posInEmpOfManager = -1;
												flag3 = true;
												System.out.println("You are not allowed to do that.");
												continue;
											}
										} else {
											posInEmpOfManager = -1;
											flag3 = true;
											System.out.println("That is not a valid id.");
										}
									} catch (NumberFormatException d) {
										posInEmpOfManager = -1;
										System.out.println("Please insert an Integer.");
										flag3 = true;
									}
								}

								if (posInEmpOfManager != -1) {
									Manager newManager = (Manager) Employee.Employees.get(posInEmpOfManager);
									System.out.println("The new Manager is going to be: " + newManager.getFirstname()
											+ " " + newManager.getSurname() + "."
											+ "\nAre you sure you want to make that change?");
									boolean flag10 = false;
									do {
										System.out.println("yes/no");
										String verify = sc.nextLine();
										if (verify.toLowerCase().equals("yes")) {
											Employee.Employees.get(posInEmployees).setManager(newManager);
											System.out.println("The change has been made.");
											flag10 = false;
										} else if (verify.toLowerCase().equals("no")) {
											System.out.println("Change cancelled.");
											flag10 = false;
										} else {
											flag10 = true;
										}
									} while (flag10);
								}
							} while (flag3);
							break;
						case 3:
							menuflag = true;
							break;
						}

					} while (flag1);

				} else {
					menuflag = true;
				}
			} else if (selection == 7) {
				boolean shiftflag;
				do {
					shiftflag = false;
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
								posInEmployees = empBinarySearch(selectedId);
								if (posInEmployees == -1) {
									flag1 = true;
									System.out.println("That is not a valid Id.");
								} else if (posInEmployees == this.getEmployee_Id()) {
									flag1 = true;
									System.out.println("Hr Director doesn't have a shift."); // Hr Director doesn't have
																								// a shift
								} else {
									flag1 = false;
								}
							} catch (NumberFormatException b) {
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
							printShift(Employee.Employees.get(posInEmployees).getThisWeekShift());
							boolean anotherDay, dayChanged = false;
							int dayInt;
							String[] shiftStr = new String[8];
							for (int i = 0; i < 8; i++) {
								shiftStr[i] = Employee.Employees.get(posInEmployees).getShiftStr()[i];
							}
							do {
								dayInt = -1;
								anotherDay = false;
								boolean flag3;
								do {
									flag3 = false;
									System.out.println(
											"Insert the day of the week(eg Monday), press Enter to choose a different Id \n"
													+ "or type \"exit\" to return to the basic menu:");
									String dayOfWeek = sc.nextLine();
									dayOfWeek = dayOfWeek.toLowerCase();
									if (dayOfWeek.equals("")) {
										shiftflag = true;
									} else if (dayOfWeek.equals("monday")) {
										dayInt = 0;
										dayChanged = true;
									} else if (dayOfWeek.equals("tuesday")) {
										dayInt = 1;
										dayChanged = true;
									} else if (dayOfWeek.equals("wednesday")) {
										dayInt = 2;
										dayChanged = true;
									} else if (dayOfWeek.equals("thursday")) {
										dayInt = 3;
										dayChanged = true;
									} else if (dayOfWeek.equals("friday")) {
										dayInt = 4;
										dayChanged = true;
									} else if (dayOfWeek.equals("saturday")) {
										dayInt = 5;
										dayChanged = true;
									} else if (dayOfWeek.equals("sunday")) {
										dayInt = 6;
										dayChanged = true;
									} else if (dayOfWeek.equals("exit")) {
										menuflag = true;
										break;
									} else {
										System.out.println("That is not a valid input.");
										flag3 = true;
									}
								} while (flag3);
								if (dayInt != -1) {
									System.out.println("Insert the new shift for the day or type \"back\" to go back.");
									String inputShift = sc.nextLine();
									if (inputShift.toLowerCase().equals("back")) {
										flag2 = true;
										break;
									} else {
										if (dayInt == 0) { // monday is both at positions 0 and 7
											shiftStr[0] = inputShift;
											shiftStr[7] = inputShift;
										} else {
											shiftStr[dayInt] = inputShift;
										}
										System.out.println("Do you want to change the shift of another day?");
										boolean flag5;
										do {
											flag5 = false;
											System.out.println("yes/no");
											String verify = sc.nextLine();
											if (verify.toLowerCase().equals("yes")) {
												anotherDay = true;
											} else if (verify.toLowerCase().equals("no")) {
												anotherDay = false;
											} else {
												flag5 = true;
											}
										} while (flag5);
									}
									shiftflag = false;
								}
							} while (anotherDay);
							if (dayChanged && !shiftflag && !flag2) {
								try {
									Employee.Employees.get(posInEmployees)
											.setThisWeekShift(Shift.createShift(shiftStr));
									// if it moves on the input is correct as creatShift method throws Exception
									// for wrong input
									Employee.Employees.get(posInEmployees).setShiftStr(shiftStr);
									System.out.println("The change has been made.");
									shiftflag = false;
									break;
								} catch (ShiftException e) {
									System.err.println("Invalid input. " + e);
									flag2 = true;
								}
							}
						} while (flag2);

					} else {
						menuflag = true;
						shiftflag = false;
					}
				} while (shiftflag);
			} else if (selection == 8) {
				boolean flag1 = false;
				int sel = 0;

				System.out.println("------------- HIRE A NEW MEMBER ---- " + getDate() + " ---------------"
						+ "\n1) Hire a new Employee" + "\n2) Hire a new Manager" + "\n3) Exit"
						+ "\n-------------------- CHOOSE A NUMBER BETWEEN 1 AND 3 --------------------");

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
				if (sel == 1) {
					Employee employee = new Employee("", "", "", "", -1, null);
					String firstname = "", surname = "", position = "", password = "", managerName = "";
					int mngrId = -1, mngrPosition = -1;
					double salary = 0;
					do {
						flag1 = false;
						System.out.print("Insert Employee's first name or press Enter to return to central Menu:");
						firstname = sc.nextLine();
						if (firstname.equals("")) {
							break;
						}
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
												} else if (!Employee.checkDecimalsSalary(salary)) {
													flag5 = true;
													System.out.println("Insert a number with 2 or less decimals.");
													continue;
												}
											} catch (NumberFormatException b) {
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
													} else if (mngrPosition == 0) {// hr director is manager only for
																					// managers
														flag6 = true;
														System.out.println("You are not allowed to do that.");
														continue;
													} else {
														managerName = Employee.Employees.get(mngrPosition)
																.getFirstname();
														managerName += " "
																+ Employee.Employees.get(mngrPosition).getSurname();
													}
												} catch (NumberFormatException b) {
													flag6 = true;
													System.out.println("Please insert an Integer.");
													continue;
												}
											}
											boolean flag7;
											do {
												flag7 = false;
												String[] shiftStr = Shift.insertShiftStr();
												if (shiftStr[0].equals("no shift")) {
													flag6 = true;
													break;
												}
												try {
													employee.setThisWeekShift(Shift.createShift(shiftStr));
												} catch (ShiftException e) {
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
													} else if (verify.toLowerCase().equals("no")) {
														System.out.println(
																"Shift has not been saved, please insert a new shift.");
														flag7 = true;
													} else {
														flag8 = true;
													}
												} while (flag8);
											} while (flag7);
										} while (flag6);
									} while (flag5);
								} while (flag4);
							} while (flag3);
						} while (flag2);
					} while (flag1);
					if (!firstname.contentEquals("")) {
						System.out.println("Employee's data: \nfirst name: " + firstname + "\nsurname: " + surname
								+ "\nposition: " + position + "\npassword: " + password + "\nsalary: " + salary
								+ "\nmanager name :" + managerName + "\nDo you want to save?");
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
							} else if (verify.toLowerCase().equals("no")) {
								System.out.println("Employee has not been saved.");
								Employee.removeLastEmployee();
								flag1 = false;
							} else {
								flag1 = true;
							}
						} while (flag1);
					} else {
						Employee.removeLastEmployee();
					}
				} else if (sel == 2) {
					Manager manager = new Manager("", "", "", "", -1, null);
					String firstname = "", surname = "", position = "", password = "", managerName = "";
					int salary = 0, mngrId = -1, mngrPosition = -1;
					do {
						flag1 = false;
						System.out.print("Insert Manager's first name or press Enter to return to central menu:");
						firstname = sc.nextLine();
						if (firstname.equals("")) {
							break;
						}
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
												salary = Integer.parseInt(selected);
												if (salary < 0) {
													flag5 = true;
													System.out.println("Insert a non negative integer.");
													continue;
												}
											} catch (NumberFormatException b) {
												flag5 = true;
												System.out.println("Please insert an Integer.");
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
												} catch (NumberFormatException b) {
													flag6 = true;
													System.out.println("Please insert an Integer.");
												}
											}
											boolean flag7;
											do {
												flag7 = false;
												String[] shiftStr = Shift.insertShiftStr();
												if (shiftStr[0].equals("no shift")) {
													flag6 = true;
													break;
												}
												try {
													manager.setThisWeekShift(Shift.createShift(shiftStr));
												} catch (ShiftException e) {
													System.out.println("Mistake with the inserted shift. " + e);
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
													} else if (verify.toLowerCase().equals("no")) {
														System.out.println(
																"Shift has not been saved, please insert a new shift.");
														flag7 = true;
													} else {
														flag8 = true;
													}
												} while (flag8);
											} while (flag7);
										} while (flag6);
									} while (flag5);
								} while (flag4);
							} while (flag3);
						} while (flag2);
					} while (flag1);
					if (!firstname.equals("")) {
						System.out.println("Manager's data: \nfirst name: " + firstname + "\nsurname: " + surname
								+ "\nposition: " + position + "\npassword: " + password + "\nsalary: " + salary
								+ "\nmanager name :" + managerName + "\nDo you want to save?");
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
							} else if (verify.toLowerCase().equals("no")) {
								System.out.println("Manager has not been saved.");
								Employee.removeLastEmployee();
								flag1 = false;
							} else {
								flag1 = true;
							}
						} while (flag1);
					} else {
						Employee.removeLastEmployee();
					}
				} else if (sel == 3) {
					continue;
				}
			} else if (selection == 9) {
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
							posInEmployees = empBinarySearch(selectedId);
							if (posInEmployees == -1) {
								flag1 = true;
								System.out.println("That is not a valid Id.");
							} else if (posInEmployees == 0) {
								System.out.println("You are not allowed to do that.");
								flag1 = true;
								posInEmployees = -1;
							} else if (Employee.Employees.get(posInEmployees) instanceof Manager) {
								System.out.println("Managers cannot be deleted.");
								flag1 = true;
								posInEmployees = -1;
							} else {
								flag1 = false;
							}
						} catch (NumberFormatException b) {
							flag1 = true;
							System.out.println("Please insert an Integer.");
						}
					}
				} while (flag1);
				if (posInEmployees != -1) {
					System.out.println(Employee.Employees.get(posInEmployees).toString());
					System.out
							.println("You are going to remove " + Employee.Employees.get(posInEmployees).getFirstname()
									+ " " + Employee.Employees.get(posInEmployees).getSurname() + ". \nAre you sure?");
					boolean flag2;
					do {
						flag2 = false;
						System.out.println("yes/no");
						String verify = sc.nextLine();
						if (verify.toLowerCase().equals("yes")) {
							if (Employee.Employees.get(posInEmployees) instanceof Manager) {
								for (Employee a : Employee.Employees) {
									if (Employee.Employees.get(posInEmployees).equals(a.getManager())) {
										a.setManager(null);
									}
								}
							}
							Employee.Employees.remove(posInEmployees);
							System.out.println("The removal has been made.");
						} else if (verify.toLowerCase().equals("no")) {
							System.out.println("Removal cancelled.");
						} else {
							flag2 = true;
						}
					} while (flag2);
				}
			} else if (selection == 10) {
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
							posInEmployees = empBinarySearch(selectedId);
							if (posInEmployees == -1) {
								flag1 = true;
								System.out.println("That is not a valid Id.");
							} else {
								flag1 = false;
							}
						} catch (NumberFormatException b) {
							flag1 = true;
							System.out.println("Please insert an Integer.");
						}
					}
				} while (flag1);
				if (posInEmployees != -1) {
					if (Employee.Employees.get(posInEmployees) instanceof Manager == false) {
						System.out.println(Employee.Employees.get(posInEmployees).toString());
						System.out.println("You are going to promote "
								+ Employee.Employees.get(posInEmployees).getFirstname() + " "
								+ Employee.Employees.get(posInEmployees).getSurname() + " to Manager. \nAre you sure?");
						boolean flag2;
						do {
							flag2 = false;
							System.out.println("yes/no");
							String verify = sc.nextLine();
							if (verify.toLowerCase().equals("yes")) {
								Manager a = new Manager(Employee.Employees.get(posInEmployees));
								System.out.println("Promotion has been succesfully done.");
							} else if (verify.toLowerCase().equals("no")) {
								System.out.println("Promotion cancelled.");
							} else {
								flag2 = true;
							}
						} while (flag2);
					} else {
						System.out.println("Already a Manager.");
					}
				}
			} else if (selection == 11) {
				menuflag = false;
			}
		} while (menuflag);
	}

	/**
	 * Inmethod enter an id and returns -1 if the id is invalid or the employee's
	 * who requested it, or doesn't refer to a Manager, else returns the position in
	 * list Employees.
	 * 
	 * @return posInEmployees
	 */
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
					int posInEmployees = empBinarySearch(selectedId);
					if (posInEmployees != -1) {
						if (Employee.Employees.get(posInEmployees) instanceof Manager) {
							requested = posInEmployees;
							flag1 = false;
							break;
						} else {
							flag1 = true;
							System.out.println("That id doesn't refer to a Manager.");
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

	/**
	 * Gives the ability to the Director to set extra hours for the Manager.
	 * According to the extra hours, the salary is increased
	 */
	public void Yperoria() {

		Calendar calendar = Calendar.getInstance();
		boolean flag3 = false;
		Scanner in = new Scanner(System.in);
		int x = 0;
		boolean somethingWrong = true;
		x = enterManagerId();
		if (x != -1) {
			do {
				System.out.printf("How many hours of overtime for %s %s ?\n", Employee.Employees.get(x).getFirstname(),
						Employee.Employees.get(x).getSurname());

				int epilogh = 0;

				do {
					if (!in.hasNextInt()) {
						System.out.println("Give an Integer ");
						flag3 = true;
						in.next();

					} else {
						epilogh = in.nextInt();
						in.nextLine();
					}
				} while (flag3);
				if (epilogh == 0) {
					System.out.println("No extra hours added.");
					break;
				} else if (epilogh + Employee.Employees.get(x).getWresyperergasias_evdomadiaiws() > 5) {
					System.out.println(
							"According to law number 3863/2010, an employee does not have the right to work over 5 hours of overtime per week. ");
					System.out.printf("The employee has already worked %d hours this week\n",
							Employee.Employees.get(x).getWresyperergasias_evdomadiaiws());
					continue;
				} else if (epilogh < 0) {
					System.out.println("Insert an Integer, zero or higher.");
					continue;
				} else {
					somethingWrong = false;
				}
				int valueOfI = 6; // if the day is sunday
				if (calendar.get(Calendar.DAY_OF_WEEK) != 1) {// if the day is not sunday (calendar shows at the moment
																// the current day)
					valueOfI = calendar.get(Calendar.DAY_OF_WEEK) - 2;
				}

				int posInShift = shiftIndexToChange(epilogh, Employee.Employees.get(x).getThisWeekShift()[valueOfI]);
				if (posInShift == -1) {
					continue;
				} else {
					somethingWrong = false;
					System.out.printf("Are you sure tha you want %s to do " + epilogh + " hour(s) of overtime?\n",
							Employee.Employees.get(x).getFirstname(), Employee.Employees.get(x).getSurname());
					boolean flag4;
					do {
						System.out.println("yes/no");
						String verify = in.nextLine();
						if (verify.toLowerCase().equals("yes")) {
							String b = "The Manager has setted " + epilogh + " extra hours for today";
							Employee.Employees.get(x).newmail[Employee.Employees.get(x).maxmail] = b;
							Employee.Employees.get(x).maxmail++;
							Employee.Employees.get(x).setWresyperergasias_evdomadiaiws(
									Employee.Employees.get(x).getWresyperergasias_evdomadiaiws() + epilogh);
							Calendar[][] newShift = Employee.Employees.get(x).getThisWeekShift();
							Calendar newValue = Employee.Employees.get(x).getThisWeekShift()[valueOfI][posInShift];
							newValue.add(Calendar.HOUR_OF_DAY, epilogh);
							newShift[valueOfI][posInShift] = newValue;
							Employee.Employees.get(x).setThisWeekShift(newShift);
							// we increase the payment by 0.015 ofemployee's salary for every extra hour
							double paymentIncrease = epilogh * roundTo2(Employee.Employees.get(x).getSalary() * 0.015);
							Employee.Employees.get(x)
									.setMonthPayment(Employee.Employees.get(x).getMonthPayment() + paymentIncrease);
							flag4 = false;

						} else if (verify.toLowerCase().equals("no")) {
							Employee.Employees.get(x).setWresyperergasias_evdomadiaiws(
									Employee.Employees.get(x).getWresyperergasias_evdomadiaiws() - epilogh);
							break;
						} else {
							flag4 = true;
						}
					} while (flag4);
				}
			} while (somethingWrong);
		}

	}

	/** Shows the Managers. */

	private void showManagers() {
		boolean onefound = false;
		for (Employee a : Employee.Employees) {
			if (a instanceof Manager && !a.equals(this)) {
				onefound = true;
				System.out.println(a.toString());
			}
		}
		if (!onefound) {
			System.out.println("No Managers found.");
		}
	}

	/** Shows the check in status of the Managers. */
	private void showCheckInStatusManagers() {

		boolean onefound = false;
		String status;
		for (Employee a : Employee.Employees) {
			if (a instanceof Manager && !a.equals(this)) {
				onefound = true;
				if (a.getLastChecked().get(Calendar.YEAR) != 1990) {
					if (a.isCheckedIn()) {
						status = "Checked in";
					} else {
						status = "Checked out";
					}
					String timeChecked = String.format("%02d:%02d", a.getLastChecked().get(Calendar.HOUR_OF_DAY),
							a.getLastChecked().get(Calendar.MINUTE));
					String dayChecked = String.format("%d/%d", a.getLastChecked().get(Calendar.DAY_OF_MONTH),
							a.getLastChecked().get(Calendar.MONTH) + 1);
					System.out.println("Id: " + a.getEmployee_Id() + " " + a.getFirstname() + " " + a.getSurname()
							+ " status: " + status + " at " + timeChecked + " of " + dayChecked);
				} else { // if a.getLastChecked().get(Calendar.YEAR) == 1990 the Employee has never
							// checked in
					System.out.println("Id: " + a.getEmployee_Id() + " " + a.getFirstname() + " " + a.getSurname()
							+ " status: Checked out");
				}
			}
		}
		if (!onefound) {
			System.out.println("No Managers found.");
		}

	}

}
