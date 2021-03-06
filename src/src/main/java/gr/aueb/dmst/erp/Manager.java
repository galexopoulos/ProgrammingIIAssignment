package src.main.java.gr.aueb.dmst.erp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * The class that refers to the Managers. Extends the Employee class and has not
 * any extra fields. Every Manager is responsible for the Employees who have him
 * as Manager.
 * 
 * @author Nikolaos Antonopoulos, Georgios Sideris
 *
 */

public class Manager extends Employee {

	/**
	 * Constructor of the Manager with arguments that initialize the basic fields.
	 * 
	 * @param firstname
	 * @param surname
	 * @param position
	 * @param password
	 * @param salary
	 * @param manager
	 */
	public Manager(String firstname, String surname, String position, String password, double salary, Manager manager) {
		super(firstname, surname, position, password, salary, manager);
	}

	/**
	 * Constructor using an Employee as parameter, this constructor is used to
	 * promote an Employee to Manager.
	 * 
	 * @param employee
	 */
	public Manager(Employee employee) {// for the promotion at the Hr Director class
		super(employee);
	}

	/**
	 * Constructor used for the Hr Director.
	 * 
	 * @param firstname
	 * @param surname
	 * @param password
	 * @param salary
	 */
	Manager(String firstname, String surname, String password, int salary) {
		super(firstname, surname, password, salary);
	}

	/**
	 * The method that prints the basic characteristics of a Manager, except of
	 * manager.
	 */
	@Override
	public String toString() {
		return "Manager [firstname=" + getFirstname() + ", surname=" + getSurname() + ", position=" + getPosition()
				+ ", employee_Id=" + getEmployee_Id() + ", salary=" + getSalary() + "]";
	}

	/** The method of the menu of a Manager. */
	public void getMenu() {
		Scanner sc = new Scanner(System.in);
		boolean menuflag = true;
		System.out.println("Welcome!");
		do {
			System.out.println("---------------- MANAGER MENU ---- " + getDate() + " ----------------" + "\n1) Check in"
					+ "\n2) Check out" + "\n3) Day off request" + "\n4) Inbox" + "\n5) Show shift of the week"
					+ "\n6) View Employees" + "\n7) Show check in status of Employees" + "\n8) Edit an Employee's shift"
					+ "\n9) Set  extra hours for an Employee" + "\n10) Edit an Employee's payment"
					+ "\n11) Edit an Employee's fields" + "\n12) Log out"
					+ "\n------------------- CHOOSE A NUMBER BETWEEN 1 AND 12 --------------------");
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
				checkIn();
			} else if (selection == 2) {
				checkOut();
			} else if (selection == 3) {// you can request for free day only in the current week
				Calendar freeRequest = Calendar.getInstance();
				freeRequest = enterWeekDay();
				if (freeRequest.get(Calendar.YEAR) != 1990) {
					System.out.println("Day off request succesfully sent to Manager.");
					adeia(freeRequest);
				} // if year = 1990 the employee has requested to return to the central Menu

			} else if (selection == 4) {
				int epilogh = 0;
				boolean flag3 = false;
				do {
					System.out.println("---------------- INBOX ---- " + getDate() + " ----------------"
							+ "\n1) Send Mail" + "\n2) View Mails" + "\n3) Exit"
							+ "\n---------------- CHOOSE A NUMBER BETWEEN 1 AND 3 ----------------");
					if (!sc.hasNextInt()) {
						System.out.println("����� 1 � 2 � 3");
						flag3 = true;
						sc.next();

					} else {
						epilogh = sc.nextInt();
						if (epilogh > 3 || epilogh < 1) {
							flag3 = true;
							System.out.println("input an integer [1,3]");
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
			} else if (selection == 5) {
				printShift(this.getThisWeekShift());
			} else if (selection == 6) {
				showMngrEmployees();
			} else if (selection == 7) {
				showCheckInStatusMngrEmp();
			} else if (selection == 8) {
				boolean shiftflag;
				do {
					shiftflag = false;
					int posInEmployees = enterEmpId();
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
									// if it moves on the input is correct as createShift method throws Exception
									// for wrong input
									Employee.Employees.get(posInEmployees).setShiftStr(shiftStr);
									System.out.println("The change has been made.");
									shiftflag = false;
									break;
								} catch (ShiftException e) {
									System.out.println("Invalid input. " + e);
									flag2 = true;
								}
							}
						} while (flag2);

					} else {
						menuflag = true;
						shiftflag = false;
					}
				} while (shiftflag);

			} else if (selection == 9) {
				Yperoria();
			} else if (selection == 10) {
				int posInEmployees = enterEmpId();
				if (posInEmployees != -1) {
					boolean flag1 = false;
					int sel = 0;
					System.out.println("----------- EDIT EMPLOYEE'S PAYMENT ---- " + getDate() + " -----------"
							+ "\n1) Edit salary" + "\n2) Edit payment for the current month" + "\n3) Exit"
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
										System.out.println("Insert a number greater than zero.");
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
									System.out.println("Change cancelled");
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
			} else if (selection == 11) {
				int posInEmployees = enterEmpId();
				if (posInEmployees != -1) {
					System.out.println(Employee.Employees.get(posInEmployees).toString());
					boolean flag1 = false;
					int sel = 0;

					System.out.println("--------- EDIT EMPLOYEE'S FIELDS ---- " + getDate() + " ---------"
							+ "\n1) Change position" + "\n2) Change manager" + "\n3) Exit"
							+ "\n----------------- CHOOSE A NUMBER BETWEEN 1 AND 3 ------------------");

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
						case 2:
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
											if (selectedId != this.getEmployee_Id()
													&& selectedId != Employee.Employees.get(posInEmployees)
															.getEmployee_Id()
													&& Employee.Employees.get(posInEmpOfManager) instanceof Manager) {
												// checks that the new Manager is not the same with the current or with
												// the
												// employee whom field we want to edit and that the new Manager is a
												// Manager and not a basic Employee
												if (selectedId == 0 && !(Employee.Employees
														.get(posInEmployees) instanceof Manager)) {
													// only managers can have hr director as manager
													posInEmpOfManager = -1;
													System.out.println("You are not allowed to do that.");
													flag3 = true;
												} else {
													flag3 = false;
												}
											} else {
												posInEmpOfManager = -1;
												flag3 = true;
												System.out.println("You are not allowed to do that.");
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
			} else if (selection == 12) {
				menuflag = false;
			}
		} while (menuflag);
	}

	/**
	 * Finds the position in the list Employees of the employee of the Manger with
	 * the inserted id.
	 * 
	 * @return the position in list Employees if there is an Employee with the
	 *         inserted id and has as manager the Manager who requested, else
	 *         returns -1.
	 */
	public int enterEmpId() {// a manager can only find the id of his employees
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
					int posInEmployees = empBinarySearch(selectedId);
					if (posInEmployees != -1) {
						if (this.equals(Employee.Employees.get(posInEmployees).getManager())) {
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
				} catch (NumberFormatException b) {
					flag1 = true;
					System.out.println("Please insert an Integer.");
				}
			}
		} while (flag1);
		return requested;
	}

	/**
	 * Finds the position in list Employees of the Manager with the input id.
	 * 
	 * @param id , the id of the Manager
	 * @return the position in list Employees if the input id is a valid Manager id,
	 *         else returns -1.
	 */
	public static int whereIsManager(int id) {
		for (int i = 0; i < Employee.Employees.size(); i++) {
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

	/**
	 * Gives the ability to a manager to set extra hours to an Employee. The salary
	 * of the employee is increased according to the extra hours
	 */
	public void Yperoria() {

		Calendar calendar = Calendar.getInstance();
		boolean flag3 = false;
		Scanner in = new Scanner(System.in);
		int x = 0;
		boolean somethingWrong = true;
		x = enterEmpId();
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
							"According to law number 3863/2010, an employee does not have the right to work over 5 hours of overtime per week.");
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

	/**
	 * the method rounds a number to 2 decimals
	 * 
	 * @param x
	 * @return x rounded at 2 decimals if needed
	 */
	public static double roundTo2(double x) {// static only for the junit
		return (double) Math.round(x * 100) / 100;
	}

	public static int shiftIndexToChange(int extraHours, Calendar[] dayShift) {// returns -1 if 1)extra hours overpass
																				// midnight
																				// 2)employee has a shift that continues
																				// the next day
																				// 3)extra hours added after the
																				// employee finished his shift
																				// 4)employee doesn't work the requested
																				// day
																				// (static only for the junit)
		boolean midnightError = false;
		for (int i = 7; i > 0; i = i - 2) {
			if (dayShift[i].get(Calendar.YEAR) != 1990) {
				if (i == 7 || dayShift[i + 1].get(Calendar.YEAR) == 1990) {// we want to check that the next index is
																			// empty
					Calendar rightNow = Calendar.getInstance();
					if (rightNow.after(dayShift[i])) {
						System.out.println("Employee's shift for today has ended.");
						return -1;

					}
					int dayAtFirst = dayShift[i].get(Calendar.DAY_OF_WEEK);
					dayShift[i].add(Calendar.HOUR_OF_DAY, extraHours);
					dayShift[i].add(Calendar.SECOND, -59); // for shifts that reach 24:00
					int dayAtEnd = dayShift[i].get(Calendar.DAY_OF_WEEK);
					if (dayAtFirst == dayAtEnd) {
						dayShift[i].add(Calendar.HOUR_OF_DAY, -extraHours);
						dayShift[i].add(Calendar.SECOND, 59);
						return i;
					} else {
						dayShift[i].add(Calendar.HOUR_OF_DAY, -extraHours);
						dayShift[i].add(Calendar.SECOND, 59);
						System.out.println("Mistake with the inserted value (overpassed midnight)");
						midnightError = true;
						break;
					}
				}
			}
		}
		if (!midnightError) {
			System.out.println("The Employee doesn't work today.");
		}
		return -1;
	}

	private void showMngrEmployees() {
		boolean onefound = false;
		for (Employee a : Employee.Employees) {
			if (this.equals(a.getManager())) {
				System.out.println(a.toString());
				onefound = true;
			}
		}
		if (!onefound) {
			System.out.println("No employees found.");
		}
	}

	/** Shows the check in status of the Employees of the Manager. */
	private void showCheckInStatusMngrEmp() {

		boolean onefound = false;
		String status;
		for (Employee a : Employee.Employees) {
			if (this.equals(a.getManager())) {
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
			System.out.println("No employees found.");
		}

	}

}
