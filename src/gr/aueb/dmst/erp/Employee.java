import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.DateTimeException;
import java.util.Scanner;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;

/**
 * The class that refers to all the Employees.
 * 
 * @author Nikolaos Antonopoulos, Georgios Sideris
 *
 */
public class Employee implements Serializable {
	/**
	 * firstname, surname, position, password of the employee (basic
	 * characteristics)
	 */
	private String firstname, surname, position, password;
	/** The id of the employee (basic characteristic) */
	private int employee_Id;
	/** salary of the employee (basic characteristic) */
	private double salary;
	/** Payment paid at the end of the month (includes payment for extra hours). */
	private double monthPayment;
	/** The shift of the particular week (Calendar type). */
	private Calendar[][] thisWeekShift = new Calendar[7][8];
	/** The Manager of the Employee (basic characteristic) */
	private Manager manager;
	/**
	 * A counter that counts how many Employees have been created, used for the
	 * employee_Id setting.
	 */
	private static int add = 0;
	/** The list of all the Employees created and not removed. */
	static ArrayList<Employee> Employees = new ArrayList<Employee>();
	/**
	 * A variable that is true when the employee has checked in but hasn't checked
	 * out.
	 */
	private boolean checkedIn = false;
	/**
	 * The standard shift of the Employee (String type), when the new week starts,
	 * it will define the thisWeekShift variable.
	 */
	private String[] shiftStr = new String[8];
	/**
	 * Shows the time that the Employee checked in or checked out for the last time.
	 */
	private Calendar lastChecked = Calendar.getInstance();
	/** An array that contains the mails sent to the Employee. */
	String[] newmail = new String[200];
	/**
	 * A variable that shows how many mails the employee has, gets back to zero when
	 * it reaches 200
	 */
	int maxmail = 0;
	/**
	 * A variable that shows how many extra hours has worked the Employee the
	 * current week max extra hours worked per week = 5
	 */
	int wresyperergasias_evdomadiaiws = 0;

	/**
	 * Constructor of the Employee with arguments that initialize the basic fields,
	 * (except id initialized using add variable).
	 * 
	 * @param firstname
	 * @param surname
	 * @param position
	 * @param password
	 * @param salary
	 * @param manager
	 */
	public Employee(String firstname, String surname, String position, String password, double salary,
			Manager manager) {
		this.firstname = firstname;
		this.surname = surname;
		this.position = position;
		this.password = password;
		this.salary = salary;
		this.manager = manager;
		this.setMonthPayment(salary);
		this.employee_Id = add;
		lastChecked.set(Calendar.YEAR, 1990);
		Employees.add(this);
		add++;
	}

	/**
	 * Constructor using an Employee as argument, this constructor is used to
	 * promote an Employee to Manager.
	 * 
	 * @param employee
	 */

	public Employee(Employee employee) {// for the second constructor at Manager
		this.firstname = employee.getFirstname();
		this.surname = employee.getSurname();
		this.position = employee.getPosition();
		this.password = employee.getPassword();
		this.salary = employee.getSalary();
		this.manager = employee.getManager();
		this.employee_Id = employee.getEmployee_Id();
		this.monthPayment = employee.getMonthPayment();
		this.checkedIn = employee.isCheckedIn();
		this.shiftStr = employee.getShiftStr();
		this.thisWeekShift = employee.getThisWeekShift();
		this.lastChecked = employee.getLastChecked();
		this.wresyperergasias_evdomadiaiws = employee.getWresyperergasias_evdomadiaiws();
		this.maxmail = employee.getMaxmail();
		this.newmail = employee.getNewmail();
		for (int i = 0; i < Employees.size(); i++) {
			if (Employees.get(i).equals(employee)) {
				Employees.set(i, this);
			}

		}

	}

	/**
	 * Constructor used for the Hr Director.
	 * 
	 * @param firstname
	 * @param surname
	 * @param password
	 * @param salary
	 */
	Employee(String firstname, String surname, String password, int salary) {
		this.firstname = firstname;
		this.surname = surname;
		this.password = password;
		this.salary = salary;
		this.employee_Id = add;
		this.setMonthPayment(salary);
		lastChecked.set(Calendar.YEAR, 1990);
		Employees.add(this);
		add++;
	}

	/** Getter of the mails. */
	public String[] getNewmail() {
		return newmail;
	}

	/** Setter of the mails. */
	public void setNewmail(String[] newmail) {
		this.newmail = newmail;
	}

	/** Getter of maxmail variable. */
	public int getMaxmail() {
		return maxmail;
	}

	/** Setter of maxmail variable. */
	public void setMaxmail(int maxmail) {
		this.maxmail = maxmail;
	}

	/** Getter of wresyperergasias_evdomadiaiws. */
	public int getWresyperergasias_evdomadiaiws() {
		return wresyperergasias_evdomadiaiws;
	}

	/** Setter of wresyperergasias_evdomadiaiws. */
	public void setWresyperergasias_evdomadiaiws(int wresyperergasias_evdomadiaiws) {
		this.wresyperergasias_evdomadiaiws = wresyperergasias_evdomadiaiws;
	}

	/** Getter of firstname. */
	public String getFirstname() {
		return firstname;
	}

	/** Setter of firstname. */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/** Getter of surname. */
	public String getSurname() {
		return surname;
	}

	/** Setter of surname. */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/** Getter of position. */
	public String getPosition() {
		return position;
	}

	/** Setter of position. */
	public void setPosition(String position) {
		this.position = position;
	}

	/** Getter of password. */
	public String getPassword() {
		return password;
	}

	/** Setter of password. */
	public void setPassword(String password) {
		this.password = password;
	}

	/** Getter of employee_Id. */
	public int getEmployee_Id() {
		return employee_Id;
	}

	/** Setter of employee_Id. */
	public void setEmployee_Id(int employee_Id) {
		this.employee_Id = employee_Id;
	}

	/** Getter of salary. */
	public double getSalary() {
		return salary;
	}

	/** Setter of salary. */
	public void setSalary(double salary) {
		this.salary = salary;
	}

	/** Getter of manager. */
	public Manager getManager() {
		return manager;
	}

	/** Setter of manager. */
	public void setManager(Manager manager) {
		this.manager = manager;
	}

	/** Getter of monthPayment. */
	public double getMonthPayment() {
		return monthPayment;
	}

	/** Setter of monthPayment. */
	public synchronized void setMonthPayment(double monthPayment) {
		this.monthPayment = monthPayment;
	}

	/** Getter of checkedIn. */
	public boolean isCheckedIn() {
		return checkedIn;
	}

	/** Setter of checkedIn. */
	public void setCheckedIn(boolean checkedIn) {
		this.checkedIn = checkedIn;
	}

	/** Getter of shiftStr. */
	public String[] getShiftStr() {
		return shiftStr;
	}

	/** Setter of shiftStr. */
	public void setShiftStr(String[] shiftStr) {
		this.shiftStr = shiftStr;
	}

	/** Getter of thisWeekShift. */
	public Calendar[][] getThisWeekShift() {
		return thisWeekShift;
	}

	/** Setter of thisWeekShift. */
	public synchronized void setThisWeekShift(Calendar[][] thisWeekShift) {
		this.thisWeekShift = thisWeekShift;
	}

	/** Getter of lastChecked. */
	public Calendar getLastChecked() {
		return lastChecked;
	}

	/** Setter of lastChecked. */
	public void setLastChecked(Calendar lastChecked) {
		this.lastChecked = lastChecked;
	}

	/**
	 * The method that prints the basic characteristics of an Employee, except of
	 * manager.
	 */
	@Override
	public String toString() {
		return "Employee [firstname=" + firstname + ", surname=" + surname + ", position=" + position + ", employee_Id="
				+ employee_Id + ", salary=" + salary + "]";
	}

	/** The method of the menu of a simple Employee. */
	public void getMenu() {
		Scanner sc = new Scanner(System.in);
		boolean menuflag;
		System.out.println("Welcome!");
		do {
			menuflag = true;
			System.out.println("---------------- EMPLOYEE MENU ---- " + getDate() + " ---------------" + "\n1) Check in"
					+ "\n2) Check out" + "\n3) Day off request" + "\n4) Inbox" + "\n5) Show shift of the week"
					+ "\n6) Log out" + "\n------------------- CHOOSE A NUMBER BETWEEN 1 AND 6 --------------------");
			boolean flag = false;
			int selection = 0;
			do {
				if (!sc.hasNextInt()) {
					System.out.println("Please insert 1 or 2 or 3 or 4 or 5 or 6");
					flag = true;
					sc.next();

				} else {
					selection = sc.nextInt();
					if (selection > 6 || selection < 1) {
						flag = true;
						System.out.println("input an integer [1,6]");
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
					System.out.println("---------------- INBOX ---- " + getDate() + " ---------------"
							+ "\n1) Send Mai" + "\n2) View Mails " + "\n3) Exit"
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
			} else if (selection == 5) {
				printShift(this.getThisWeekShift());
			} else if (selection == 6) {
				menuflag = false;
			}
		} while (menuflag);
	}

	/**
	 * In the method an Employee enters a date, which gets checked if it is valid
	 * and in the next 7 days, we need this check for the day that an employee
	 * requests for free day.
	 * 
	 * @return day, a Calendar with YEAR = 1990 if the user wants to return to
	 *         central menu or a valid Calendar.
	 */
	public Calendar enterWeekDay() {
		Scanner sc = new Scanner(System.in);
		Calendar current = Calendar.getInstance();
		current.add(Calendar.SECOND, -1); // in order to be able to get the current day
		Calendar lastDay = Calendar.getInstance();
		Calendar requested = Calendar.getInstance();
		int day = 0, month = 0, year = 0;
		boolean flag2;
		String selected;
		do {
			flag2 = false;
			System.out.println("Insert the day of the month or press Enter to return to the central menu.");
			boolean flag3 = false;
			do {
				selected = sc.nextLine();
				if (selected.equals("")) {
					requested.set(Calendar.YEAR, 1990);// that means that the user exited without completing the request
					flag3 = false;
					break;
				} else {
					try {
						day = Integer.parseInt(selected);
						if (day > 31 || day < 1) {
							flag3 = true;
							System.out.println("Insert an integer [1,31]:");
						} else {
							flag3 = false;
						}
					} catch (Exception a) {
						System.out.println("Please insert an Integer.");
						flag3 = true;
					}
				}
			} while (flag3);
			if (requested.get(Calendar.YEAR) == 1990) {
				break;
			}
			System.out.println("Insert the month or press Enter to return to the central menu.");
			flag3 = false;
			do {
				selected = sc.nextLine();
				if (selected.equals("")) {
					requested.set(Calendar.YEAR, 1990);// that means that the user exited without completing the request
					flag3 = false;
					break;

				} else {
					try {
						month = Integer.parseInt(selected);
						if (month > 12 || month < 1) {
							flag3 = true;
							System.out.println("Insert an integer [1,12]:");
						} else {
							flag3 = false;
						}
					} catch (NumberFormatException a) {
						System.out.println("Please insert an Integer.");
						flag3 = true;
					}
				}
			} while (flag3);
			if (requested.get(Calendar.YEAR) == 1990) {
				break;
			}
			try {
				LocalDate.of(current.get(Calendar.YEAR), month, day);
			} catch (DateTimeException e) { // checks that the given month and day are valid, for example day == 31,
											// month == 2 is invalid
				System.out.println("Invalid date, please try again. \n");
				flag2 = true;
				continue;
			}
			requested.set(Calendar.DAY_OF_MONTH, day);
			requested.set(Calendar.MONTH, month - 1);
			lastDay.add(Calendar.DAY_OF_WEEK, 7); // to find the day 7 days after today
			if (current.after(requested) || requested.after(lastDay)) {
				requested.add(Calendar.YEAR, 1);
				if (current.after(requested) || requested.after(lastDay)) {
					System.out.println("Insert a date of the current week. \n");
					flag2 = true;
					requested.add(Calendar.YEAR, -1);
				}
			}
		} while (flag2);
		return requested;
	}

	/**
	 * Prints the Employee's shift of the week.
	 * 
	 * @param shift
	 */
	public static void printShift(Calendar[][] shift) {
		for (int i = 0; i < 7; i++) {
			String dayName;
			switch (i) {
			case 0:
				dayName = "Monday";
				break;
			case 1:
				dayName = "Tuesday";
				break;
			case 2:
				dayName = "Wednesday";
				break;
			case 3:
				dayName = "Thursday";
				break;
			case 4:
				dayName = "Friday";
				break;
			case 5:
				dayName = "Saturday";
				break;
			default:
				dayName = "Sunday";
				break;
			}
			System.out.println(dayName);
			String time;
			for (int j = 0; j < 8; j = j + 2) {
				if (shift[i][j].get(Calendar.YEAR) != 1990) {
					time = String.format("%02d:%02d", shift[i][j].get(Calendar.HOUR_OF_DAY),
							shift[i][j].get(Calendar.MINUTE));
					System.out.print(time + " - ");
					if (shift[i][j + 1].get(Calendar.YEAR) != 1990) {
						time = String.format("%02d:%02d", shift[i][j + 1].get(Calendar.HOUR_OF_DAY),
								shift[i][j + 1].get(Calendar.MINUTE));
						System.out.println(time);
					} else {
						System.out.println();
					}
				} else {
					if (shift[i][j + 1].get(Calendar.YEAR) != 1990) {
						time = String.format("%02d:%02d", shift[i][j + 1].get(Calendar.HOUR_OF_DAY),
								shift[i][j + 1].get(Calendar.MINUTE));
						System.out.println("- " + time);
					} else if (j == 0) {// for an empty day
						System.out.println("-");
					}
				}
			}

		}

	}

	/** Removes the last Employee of the list Employees. */
	public static void removeLastEmployee() {
		Employees.remove(Employees.size() - 1);
		add--;
	}

	/**
	 * Returns -1 if the id and password are wrong or the position in list Employees
	 * they are correct.
	 * 
	 * @param idGiven
	 * @param passwordGiven
	 * @return
	 */
	public static int login(int idGiven, String passwordGiven) {
		int j = -1;
		for (int i = 0; i < Employees.size(); i++) {
			int id = Employees.get(i).getEmployee_Id();
			String password = Employees.get(i).getPassword();
			if (idGiven == id) {
				if (passwordGiven.equals(password)) {
					return i;
				} else {
					break;
				}

			}
		}

		return -1;
	}

	/**
	 * checks that the number has no more than 2 decimals
	 * 
	 * @param x a double
	 * @return true when the number has 2 or less decimals, else false
	 */
	public static boolean checkDecimalsSalary(double x) {
		String text = Double.toString(Math.abs(x));
		int integerPlaces = text.indexOf('.');
		int decimalPlaces = text.length() - integerPlaces - 1;
		if (decimalPlaces <= 2) {
			return true;
		}
		return false;
	}

	/**
	 * Gives the ability to an Employee to send a string to another employee according to his id. The string is saved to the Employee's array called mail. 
	 */
	public void mhnyma() {
		int thesh = 0;
		Scanner in = new Scanner(System.in);

		int epilogh = enterAnyId();
		thesh = empBinarySearch(epilogh);

		if (thesh != -1) {
			System.out.printf("Type the text you wish tou send to %s %s\n",
					Employee.Employees.get(thesh).getFirstname(), Employee.Employees.get(thesh).getSurname());
			int mtbl = Employee.Employees.get(thesh).maxmail + 1;
			String a = "       -------\n" + mtbl + ")Mail from " + this.getFirstname() + " " + this.getSurname()
					+ ":\n";
			a = a + in.nextLine();
			Employee.Employees.get(thesh).newmail[Employee.Employees.get(thesh).maxmail] = a;
			Employee.Employees.get(thesh).maxmail++;
			System.out.println();
			if (Employee.Employees.get(thesh).getMaxmail() == 201) {
				String b = "Your inbox exceeds the limit of 200 mails. The new mails will replace the old ones!";
				Employee.Employees.get(thesh).setMaxmail(0);
				Employee.Employees.get(thesh).newmail[Employee.Employees.get(thesh).maxmail] = b;
				Employee.Employees.get(thesh).maxmail++;
			}
		}
	}

	/**
	 * Sends a mail to the Manager of the Employee with the day off request.
	 * 
	 * @param dayOff The
	 */
	public void adeia(Calendar dayOff) {
		if (this.getManager() != null) {
			String time = String.format("%d/%d", dayOff.get(Calendar.DAY_OF_MONTH), dayOff.get(Calendar.MONTH) + 1);
			int mtbl = this.getManager().getMaxmail() + 1;
			String mail_ston_Supervisor = "       -------\n" + mtbl + ")" + this.getFirstname() + " "
					+ this.getSurname() + " whose id is:" + this.getEmployee_Id() + " would like to take a day off on "
					+ time + ".\n";
			this.getManager().newmail[this.getManager().getMaxmail()] = mail_ston_Supervisor;
			mail_ston_Supervisor = mail_ston_Supervisor + "Inform the Employee whether you approve the day off \n ";
			this.getManager().setMaxmail(this.getManager().getMaxmail() + 1);
			if (this.getManager().getMaxmail() == 201) {
				String b = "       -------\nYour inbox exceeds the limit of 200 mails. The new mails will replace the old ones.";

				this.getManager().setMaxmail(0);
				this.getManager().newmail[getMaxmail()] = b;
				this.getManager().setMaxmail(getMaxmail() + 1);
			}
		}

	}

	/**
	 * Binary search moderated to be used in the list Employees.
	 * 
	 * @param Employees
	 * @param           x, the requested id
	 * @return the position in list Employees if the id is valid, else returns -1.
	 */
	public static int empBinarySearch(int x) {// x the id you want to find in the Employees and returns the position in
		// Employees (static only for the junit)
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

	/**
	 * Inmethod enter an id and returns -1 if the id is invalid or the employee's
	 * who requested it, else returns the position in list Employees.
	 * 
	 * @return posInEmployees
	 */
	public int enterAnyId() {
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
						if (!this.equals(Employee.Employees.get(posInEmployees))) {
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

	public static String getDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy, HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		return formatter.format(date);
	}

	void checkIn() {
		if (!this.isCheckedIn()) {
			this.setLastChecked(Calendar.getInstance());
			this.setCheckedIn(true);
			System.out.println("Check in successful!");
		} else {
			System.out.println("Already checked in.");
		}
	}

	void checkOut() {
		if (this.isCheckedIn()) {
			this.setLastChecked(Calendar.getInstance());
			this.setCheckedIn(false);
			System.out.println("Check out successful!");
		} else {
			System.out.println("Already checked out.");
		}
	}

}
