import java.time.LocalDate;
import java.util.*;

public class Employee {
	private String firstname, surname, position, password;
	private int employee_Id, extraHoursWeek = 0 /* need to be set to 0 every month */;
	private double salary, monthPayment;
	private Calendar[][] thisWeekShift = new Calendar[7][8];
	private Manager manager;
	private static int add = 0;
	static ArrayList<Employee> Employees = new ArrayList<Employee>();
	private boolean checkedIn = false; // true when the employee has checked in but hasn't checked out
	private String[] shiftStr = new String[8];
	private Calendar lastChecked = Calendar.getInstance();
	String[] newmail = new String[200];
	int maxmail = 0;
	int wresyperergasias_evdomadiaiws = 0;
	boolean apagoreysh = true;

	public Employee(String firstname, String surname, String position, String password, double salary,
			Manager manager) {
		this.firstname = firstname;
		this.surname = surname;
		this.position = position;
		this.password = password;
		this.salary = salary;
		this.manager = manager;
		this.employee_Id = add;
		lastChecked.set(Calendar.YEAR, 1990);
		Employees.add(this);
		add++;
	}

	public Employee(Employee employee) {// for the second constructor at Manager
		this.firstname = employee.getFirstname();
		this.surname = employee.getSurname();
		this.position = employee.getPosition();
		this.password = employee.getPassword();
		this.salary = employee.getSalary();
		this.manager = employee.getManager();
		this.employee_Id = employee.getEmployee_Id();
		this.extraHoursWeek = employee.getExtraHoursWeek();
		this.monthPayment = employee.getMonthPayment();
		this.checkedIn = employee.isCheckedIn();
		this.shiftStr = employee.getShiftStr();
		this.thisWeekShift = employee.getThisWeekShift();
		this.lastChecked = employee.getLastChecked();
		for (int i = 0; i < Employees.size(); i++) {
			if (Employees.get(i).equals(employee)) {
				Employees.set(i, this);
			}

		}

	}

	public String[] getNewmail() {
		return newmail;
	}

	public void setNewmail(String[] newmail) {
		this.newmail = newmail;
	}

	public int getMaxmail() {
		return maxmail;
	}

	public void setMaxmail(int maxmail) {
		this.maxmail = maxmail;
	}

	public int getWresyperergasias_evdomadiaiws() {
		return wresyperergasias_evdomadiaiws;
	}

	public void setWresyperergasias_evdomadiaiws(int wresyperergasias_evdomadiaiws) {
		this.wresyperergasias_evdomadiaiws = wresyperergasias_evdomadiaiws;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEmployee_Id() {
		return employee_Id;
	}

	public void setEmployee_Id(int employee_Id) {
		this.employee_Id = employee_Id;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public double getMonthPayment() {
		return monthPayment;
	}

	public synchronized void setMonthPayment(double monthPayment) {
		this.monthPayment = monthPayment;
	}

	public int getExtraHoursWeek() {
		return extraHoursWeek;
	}

	public synchronized void setExtraHoursWeek(int extraHoursWeek) {
		this.extraHoursWeek = extraHoursWeek;
	}

	public boolean isCheckedIn() {
		return checkedIn;
	}

	public void setCheckedIn(boolean checkedIn) {
		this.checkedIn = checkedIn;
	}

	public String[] getShiftStr() {
		return shiftStr;
	}

	public void setShiftStr(String[] shiftStr) {
		this.shiftStr = shiftStr;
	}

	public Calendar[][] getThisWeekShift() {
		return thisWeekShift;
	}

	public synchronized void setThisWeekShift(Calendar[][] thisWeekShift) {
		this.thisWeekShift = thisWeekShift;
	}

	public Calendar getLastChecked() {
		return lastChecked;
	}

	public void setLastChecked(Calendar lastChecked) {
		this.lastChecked = lastChecked;
	}

	@Override
	public String toString() {
		return "Employee [firstname=" + firstname + ", surname=" + surname + ", position=" + position + ", employee_Id="
				+ employee_Id + ", salary=" + salary + "]";
	}

	public void getMenu() {
		Scanner sc = new Scanner(System.in);
		boolean menuflag;
		System.out.println("Welcome!");
		do {
			menuflag = true;
			System.out.println("    MENU \n------------- \n Select: \n1)Check in.\n2)Check out. \n"
					+ "3)Day off request. \n4)Inbox. \n5)Show shift of the week. \n6)Log out.");
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
				if (!this.isCheckedIn()) {
					this.setLastChecked(Calendar.getInstance());
					this.setCheckedIn(true);
					System.out.println("Check in successful!");
				} else {
					System.out.println("Already checked in.");
				}
			} else if (selection == 2) {
				if (this.isCheckedIn()) {
					this.setLastChecked(Calendar.getInstance());
					this.setCheckedIn(false);
					System.out.println("Check out successful!");
				} else {
					System.out.println("Already checked out.");
				}
			} else if (selection == 3) {// you can request for free day only in the current week
				Calendar freeRequest = Calendar.getInstance();
				freeRequest = enterWeekDay();
				if (freeRequest.get(Calendar.YEAR) != 1990) {
					System.out.println("Day off request succesfully sent to Manager.");
					// inboxrelated
					// can be added an option to send a message with the request
				} // if year = 1990 the employee has requested to return to the central Menu

			} else if (selection == 4) {
				// call inbox

			} else if (selection == 5) {
				printShift(this.getThisWeekShift());
			} else if (selection == 6) {
				menuflag = false;
			}
		} while (menuflag);
	}

	public Calendar enterWeekDay() { // checks if the day that will be given is vaid and in the current week
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
							System.err.println("Insert an integer [1,31]:");
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
							System.err.println("Insert an integer [1,12]:");
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
			System.out.println("Insert the year or press Enter to return to the central menu.");
			flag3 = false;
			do {
				selected = sc.nextLine();
				if (selected.equals("")) {
					requested.set(Calendar.YEAR, 1990);// that means that the user exited without completing the request
					flag3 = false;
					break;

				} else {
					try {
						year = Integer.parseInt(selected);
						if (year != current.get(Calendar.YEAR) && year != current.get(Calendar.YEAR) + 1) {
							flag3 = true;
							System.err.println("Insert an integer [" + current.get(Calendar.YEAR) + ","
									+ (current.get(Calendar.YEAR) + 1) + "]:");
						} else {
							flag3 = false;
						}
					} catch (Exception a) {
						System.out.println("Please insert an Integer.");
						flag3 = true;
					}
				}
			} while (flag3);
			try {
				LocalDate.of(year, month, day);
			} catch (Exception e) { // checks that the date is valid, for example day == 31, month == 2 is invalid
				System.out.println("Invalid date, please try again. \n");
				flag2 = true;
				continue;
			}
			requested.set(Calendar.DAY_OF_MONTH, day);
			requested.set(Calendar.MONTH, month - 1);
			requested.set(Calendar.YEAR, year);
			lastDay = Shift.getCurrentMonday();
			lastDay.add(Calendar.DAY_OF_WEEK, 7); // to find next Monday
			if (current.after(requested) || requested.after(lastDay)) {
				System.out.println("Insert a date of the current week. \n");
				flag2 = true;
			}
		} while (flag2);
		return requested;
	}

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

	public static void removeLastEmployee() {
		Employees.remove(Employees.size() - 1);
		add--;
	}

	public static int login(int idGiven, String passwordGiven) { // returns -1 if the id and password are wrong or the
																	// position in Employees if it is correct
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

	public static boolean checkDecimalsSalary(double x) {
		String text = Double.toString(Math.abs(x));
		int integerPlaces = text.indexOf('.');
		int decimalPlaces = text.length() - integerPlaces - 1;
		if (decimalPlaces <= 2) {
			return true;
		}
		return false;
	}


public  void mhnyma() {
	
	// System.out.println(this.getEmployee_Id());
	// String ans1 = JOptionPane.showInputDialog("Πληκτρολογήστε το μήνυμα που
	// επιθυμείτε να στείλετε");
	Scanner in = new Scanner(System.in);
	int epilogh = enterEmpId();

	int thesh = binarySearch(Employees, epilogh);// binarySearch(Employee.Employees, epilogh);
	System.out.printf("Πληκτρολογήστε το μήνυμα που επιθυμείτε να στείλετε στον %s %s\n",
			Employee.Employees.get(thesh).getFirstname(), Employee.Employees.get(thesh).getSurname());
	String a = "Mail from " + this.getFirstname() + this.getSurname() + ":\n";
	a = a + in.nextLine();
	Employee.Employees.get(thesh).newmail[Employee.Employees.get(thesh).maxmail] = a;
	Employee.Employees.get(thesh).maxmail++;

	// System.out.printf("%s", Employee.mail[Employee.max_mail[thesh]][thesh]);

	// System.out.println(thesh);
	System.out.println();
	if (Employee.Employees.get(thesh).getMaxmail() == 201) {
		String b = "Τα εισερχόμενα σας είναι πάνω απο 200. Τα νέα εισερχόμενα θα λάβουν τη θέση των παλαιότερων";
		Employee.Employees.get(thesh).setMaxmail(0);
		Employee.Employees.get(thesh).newmail[Employee.Employees.get(thesh).maxmail] = b;
		Employee.Employees.get(thesh).maxmail++;
	}
}
	public void Yperoria() {

		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		boolean flag3 = false;
		Scanner in = new Scanner(System.in);
		int x = 0;
		boolean wrongValue;

		wrongValue = false;
		x = enterEmpId();
		do {
			if (x != -1) {
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
						if (Employee.Employees.get(x).getWresyperergasias_evdomadiaiws() < 5) {
							if (epilogh + Employee.Employees.get(x).getWresyperergasias_evdomadiaiws() > 5
									|| epilogh < 1) {
								flag3 = true;
								System.out.println(
										"Δώσε έγυκρο αριθμό!\nΟ υπάλληλος επιτρέπεται να δουλέψει εως και 5 ώρες υπερωρίας εβδομαδιαίως ");
								System.out.printf("Ο υπάλληλος έχει δουλέψει ήδη %d ώρες αυτή τη βδομάδα\n",
										Employee.Employees.get(x).getWresyperergasias_evdomadiaiws());
							} else {
								flag3 = false;
								Employee.Employees.get(x).setWresyperergasias_evdomadiaiws(
										Employee.Employees.get(x).getWresyperergasias_evdomadiaiws() + epilogh);
							}
							in.nextLine();
						} else {
							System.err.println(
									"Συμφωνα με τον Ν.3863/2010 ο υπάλληλος απαγορεύεται να δουλέψει περισσότερες ώρες υπερωρίας αυτήν την εδβομάδα ");
							apagoreysh = false;
							flag3 = false;
						}
					}
				} while (flag3);
				if (apagoreysh) {
					System.out.printf("Είστε σίγουρος οτι θέλετε ο %s να κάνει" + epilogh + "ωρες υπερωρίας;\n",
							Employee.Employees.get(x).getFirstname(), Employee.Employees.get(x).getSurname());
					do {
						System.out.println("yes/no");
						String verify = in.nextLine();
						if (verify.toLowerCase().equals("yes")) {
							//Ypografh1.ypografh();
							flag3 = false;

							/*
							 * System.out.println(thisweekShift[0][0].get(Calendar.YEAR)); for (int i = 7; i
							 * > 0; i = i -2) { if (thisweekShift[Calendar.DAY_OF_WEEK -
							 * 1][i].get(Calendar.YEAR) != 1990) { int dayAtFirst =
							 * thisweekShift[Calendar.DAY_OF_WEEK - 1][i].get(Calendar.DAY_OF_WEEK);
							 * thisweekShift[Calendar.DAY_OF_WEEK - 1][i - 1].add(Calendar.HOUR_OF_DAY,
							 * epilogh); int dayAtEnd = thisweekShift[Calendar.DAY_OF_WEEK -
							 * 1][i].get(Calendar.DAY_OF_WEEK); if (dayAtFirst == dayAtEnd) { break; }else {
							 * System.out.println("Mistake with the inserted value (overpassed midnight)");
							 * thisweekShift[Calendar.DAY_OF_WEEK - 1][i - 1].add(Calendar.HOUR_OF_DAY,
							 * -epilogh); wrongValue = true; break; } } }
							 */
						} else if (verify.toLowerCase().equals("no")) {
							Employee.Employees.get(x).setWresyperergasias_evdomadiaiws(
									Employee.Employees.get(x).getWresyperergasias_evdomadiaiws() - epilogh);
							break;
						} else {
							flag3 = true;
						}
					} while (flag3);
				}
				apagoreysh = true;

			}
		} while (wrongValue);

	}

	public void adeia() {
		if (this.getManager() != null) {
			String mail_ston_Supervisor = this.getFirstname() + this.getSurname() + " whose id is: "
					+ this.getEmployee_Id() + "would like to take a day off on \n";
			this.getManager().newmail[this.getManager().getMaxmail()] = mail_ston_Supervisor;
			mail_ston_Supervisor = mail_ston_Supervisor + "Inform the Employee whether you approve the day off \n ";
			this.getManager().setMaxmail(this.getManager().getMaxmail() + 1);
			;
			if (this.getManager().getMaxmail() == 201) {
				this.getManager().setMaxmail(0);
			}
		}

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
					int posInEmployees = binarySearch(Employees, selectedId);
					if (posInEmployees != -1) {
						if (this.equals(Employees.get(posInEmployees).getManager())) {
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

	public static int binarySearch(List<Employee> Employees, int x) {
		int l = 0, r = Employees.size() - 1;
		while (l <= r) {
			int m = l + (r - l) / 2;
			if (Employees.get(m).getEmployee_Id() == x)
				return m;

			if (Employees.get(m).getEmployee_Id() < x)
				l = m + 1;
			else
				r = m - 1;
		}
		return -1;
	}

}
