package src.main.java.gr.aueb.dmst.erp;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * 
 * @author giorgos_markou
 *
 */
public class Inventory implements Serializable {
	final static int maxsize = 80;
	static Scanner sc = new Scanner(System.in);
	private String type;
	/** the type of product. it can only be fixed, urgent */
	private String name;
	/** name of product */
	private int stock; // the remaining amount of the given product//
	private int pricepreunit; // the price per unit of the given product//
	private int minstock; // the minimum amount that that should be in the inventory for the given
							// product//
	private Supplier suplier;// a list of the suppliers for the hotel//
	private double balance; // the amount of money that we have spent for the particular product//
	static ArrayList<Inventory> fixedInventory = new ArrayList<Inventory>();// generates the list of for all the
	// inventory subjects//
	static ArrayList<Inventory> urgentInventory = new ArrayList<Inventory>();
	static private int buffet = 80;// the number of people the buffet can go throughout without needing a new order
	static private int buffetbalance; // to be placed. the fixed price for the buffet per visitor is 50$//

	public Inventory(String name, int stock, int pricepreunit, int minstock, Supplier suplier, String type,
			double balance) {
		super();
		this.name = name;
		this.stock = stock;
		this.pricepreunit = pricepreunit;
		this.minstock = minstock;
		this.suplier = suplier;
		this.type = type;
		this.balance = balance;
		if (this.type.equals("Fixed")) {// if statement that places the products to the according category which they
										// belong in//
			fixedInventory.add(this);
		} else if (this.type.equals("Urgent")) {
			urgentInventory.add(this);
		}
	}

	// Start of the Inventory Menu Section//
	/**
	 * Prints the visual side of the Inventory menu
	 */
	public static void displayInvMenu() {
		System.out.println("------------ INVENTORY MANAGEMENT MENU ---- " + getDate() + " --------");
		System.out.println(
				"1)To return to main menu intput 1\n2)To Update and check the Fixed Inventory please input 2\n3)To Update check the Urgent Inventory please input 3\n"
						+ "4)To Update and check the Buffet please input 4\n5)To check the balances please input 5");
		System.out.println("-------------------- CHOOSE A NUMBER BETWEEN 1 AND 5 --------------------");
	}

	public static void question() {
		System.out.println("Would you like to procced or quit?");
		System.out.println("To proceed enter 9.");
		System.out.println("If you wish to quit enter 0.");
		Scanner q = new Scanner(System.in);

		switch (q.nextInt()) {
		case 0:
			System.out.println("Thank you and goodbye.");
			break;

		case 9:
			System.out.println("Please proceed.");
			invMenu();
			break;
		default:
			System.out.println("Unrecognized option");
			invMenu();
			break;
		}
	}

	/**
	 * Initiates the Inventory manager Menu
	 */
	public static void invMenu() {
		displayInvMenu();
		int x = 0;
		do {
			try {
				do {
					x = sc.nextInt();
				} while (!(x == 1 || x == 2 || x == 3 || x == 4 || x == 5));
				break;
			} catch (Exception e) {
				System.out.println("Please select 1, 2, 3, 4 or 5.");
				sc.nextLine();
			}
		} while (true);

		switch (x) {
		case 1:
			mainClass.InputMenu();
			question();
		case 2:
			int people[] = Booking.getVisitors();
			updateFixed(people[0]);
			checkFixed();
			question();
			break;

		case 3:
			updateUrgent();
			checkUrgent();
			question();
			break;

		case 4:
			int peoplebuffet[] = Booking.getVisitors();
			updateBuffet(peoplebuffet[1]);
			question();
			break;
		case 5:
			printBalance();
			question();
			break;

		default:
			System.out.println("Unrecognized option");
			break;
		}
	}

//buffet section//
	/**
	 * Updates the buffet inventory based on the visitors that choose buffet in
	 * their package
	 */
	public static void updateBuffet(int visitorswithbuffet) {// Updates the buffet based on how many visitors included
																// buffet to their package//
		buffet -= visitorswithbuffet;
		if (buffet <= 30) {// its <= 30 because we use the the supplies for 50 visitors as protection //
			orderBuffet();
		} else {
			System.out.println("There is no need for an order");
		}
	}

	/**
	 * Calls for an order. The order can be either the fixed amount(for 300
	 * visitors) or orders by input
	 */
	public static void orderBuffet() {
		String x;
		System.out.println("The supplies for the buffet are running low and there should be an order\n."
				+ " Do you want to order the fixed supply (For " + maxsize
				+ " visitors), or order by input?\n( Select 0 for fixed amount or 1 for order by input)");
		do {
			x = sc.nextLine();
		} while (!(x.equals("0") || x.equals("1")));
		if (x.equals("1")) {

			System.out.println("For how many visitors do you want to order ?");
			int y = 0;
			do {
				try {
					do {
						System.out.println("( the order cannot be 300 or more at once or less than 80)");
						y = sc.nextInt();
						buffetbalance = 50 * y;// 50 is the fee for having buffet
					} while (y < maxsize || y > 300);
					break;
				} catch (Exception e) {
					System.out.println("Please order an amount between 80 and 300 that is not a double");
					sc.nextLine();
					continue;
				}
			} while (true);
			buffet += y;
			System.out.println("The number of visitors that the buffet can acompany is " + buffet + "");
		} else if (x.equals("0")) {
			buffet += maxsize;
			buffetbalance = maxsize * 50;
			System.out.println("The number of visitors that the buffet can acompany is " + buffet + "");
		}

	}

	/** Send the finance team the expenses from the buffet inventory */
	public static int getBuffetFin() {
		return buffetbalance;
	}

	public static void setBuffetFin() {
		buffetbalance = 0;
	}
//end of buffet section//

// Start of fixedInventory section//

	/**
	 * Gets the total number of people that are in the hotel and subtracts it from
	 * the fixed list for very product.(because the products are made for every one
	 * of the people)
	 */
	public static void updateFixed(int totalnumberofpeople) {
		for (int i = 0; i < fixedInventory.size(); i++) {
			System.out.println("Stock for " + fixedInventory.get(i).name + " before update is : "
					+ fixedInventory.get(i).stock + "");
			fixedInventory.get(i).stock = fixedInventory.get(i).stock - totalnumberofpeople;
			System.out.println(
					"Stock after update is : " + fixedInventory.get(i).stock + "\n---------------------------");
		}
	}

	/**
	 * Checks the list for items that there stock is less than the minimal stock
	 * (minstock>stock)
	 */
	public static void checkFixed() {
		sc.nextLine();// clears the scanner
		for (int i = 0; i < fixedInventory.size(); i++) {
			if (fixedInventory.get(i).stock < fixedInventory.get(i).minstock) {
				orderFixed(i);
			}
		}
	}

	/**
	 * Updates the Fixedlist item with index i by ordering either by input or the
	 * fixed amount
	 */

	public static void orderFixed(int i) {
		System.out.println("\nOrder needed for " + fixedInventory.get(i).name
				+ " \nDo you want to order the fixed amount (80) or order by input");
		String ans;
		do {
			System.out.println("Select 0 for fixed amount or 1 to order by input");
			ans = sc.nextLine();
		} while (!(ans.equals("0") || ans.equals("1")));
		if (ans.equals("0")) {
			fixedInventory.get(i).stock += maxsize;
			fixedInventory.get(i).balance += maxsize * fixedInventory.get(i).pricepreunit;

		} else if (ans.equals("1")) {
			System.out.println("Give me the order");
			int order = 1;
			do {
				try {
					do {
						System.out.println("The order should be more than the max accomondation number (80)");
						order = sc.nextInt();
					} while (order < maxsize);
					break;
				} catch (Exception e) {
					System.out.println("Only integers are allowed");
					sc.nextLine();
					continue;
				}
			} while (true);
			fixedInventory.get(i).stock += order;
			fixedInventory.get(i).balance += order * fixedInventory.get(i).pricepreunit;// order ends
		}
	}

	/**
	 * Gives an array with all the balances of the products. The product names //
	 * can be accessed with fixedInventory.get(i).name; and there connected to //
	 * the array via the index.
	 */
	public static double[] getInvFixFin() {
		double x[] = new double[fixedInventory.size()];
		for (int i = 0; i < fixedInventory.size(); i++) {
			x[i] = fixedInventory.get(i).balance;
		}
		return x;
	}

	/**
	 * Resets the balances for the prodcts(Has to be used after getInvFixFin()
	 */
	public static void setInvFixFin() {
		for (int i = 0; i < urgentInventory.size(); i++) {
			urgentInventory.get(i).setBalance(0);
		}
	}
// End of fixedInventory section//

// Start of urgentInventort section//
	/**
	 * Updates every urgentList product by input depending on how many units were
	 * used
	 */
	public static void updateUrgent() {
		for (Inventory i : urgentInventory) {
			System.out.println("How many units of " + i.name + " were used?");
			int amount;
			do {
				try {
					do {
						System.out.println("Please insert a number between 0 and " + i.stock + "");
						amount = sc.nextInt();
					} while (amount >= i.stock || amount < 0);
					break;
				} catch (Exception e) {
					sc.nextLine();
					continue;
				}
			} while (true);
			i.stock -= amount;
		}

	}

	/**
	 * Checks the list for items that there stock is less than the minimal stock
	 * (minstock>stock)
	 */
	public static void checkUrgent() {
		sc.nextLine();
		for (int i = 0; i < urgentInventory.size(); i++) {
			if (urgentInventory.get(i).stock < urgentInventory.get(i).minstock) {
				orderUrgent(i);

			}
		}

	}

	/**
	 * Updates the Fixedlist item with index i by ordering either by input or the
	 * fixed amount
	 */
	public static void orderUrgent(int i) {

		System.out.println("\nThe suply of the product " + urgentInventory.get(i).name
				+ " is running low and there should be an order\n Do you want to order the fixed amount ("
				+ urgentInventory.get(i).minstock * 2 + ")\nor order by input");
		String ans;
		do {
			System.out.println(" Select 0 for fixed amount or 1 for order by input");
			ans = sc.nextLine();
		} while (!(ans.equals("0") || ans.equals("1")));
		if (ans.equals("0")) {
			urgentInventory.get(i).stock += urgentInventory.get(i).minstock * 3;
			urgentInventory.get(i).balance += urgentInventory.get(i).minstock * 3 * urgentInventory.get(i).pricepreunit;

		} else if (ans.equals("1")) {
			System.out.println("Give me the order");
			int order = 1;
			do {
				try {
					order = sc.nextInt();
					break;
				} catch (Exception e) {
					System.out.println("Only integers are allowed");
					sc.nextLine();
					continue;
				}
			} while (true);
			urgentInventory.get(i).stock += order;
			urgentInventory.get(i).balance += order * urgentInventory.get(i).pricepreunit;// order ends
		}
	}

	/**
	 * gives an array with all the balances of the products. The product names //
	 * can be accessed with fixedInventory.get(i).name; and there connected to //
	 * the array via the index.
	 */
	public static double[] getInvUrgFin() {
		double x[] = new double[urgentInventory.size()];
		for (int i = 0; i < fixedInventory.size(); i++) {
			x[i] = urgentInventory.get(i).balance;
		}
		return x;
	}

	/**
	 * Resets the balances for the prodcts(Has to be used after getInvUrgFin()
	 */
	public static void setInvUrgFin() {
		for (int i = 0; i < urgentInventory.size(); i++) {
			urgentInventory.get(i).setBalance(0);
		}
	}

	// end of the finance block
	// End of urgentInventory section//
	/**
	 * Prints the balance that has to be paid for the products depending on with
	 * list their on
	 */
	public static void printBalance() {
		System.out.println("Fixed list");
		for (Inventory i : fixedInventory) {
			System.out.println("The balance for " + i.name + " is: " + i.balance + "\n--------------------------");
		}
		System.out.println("Urgent list");
		for (Inventory i : urgentInventory) {
			System.out.println("The balance for " + i.name + " is: " + i.balance + "\n--------------------------");
		}
		System.out.println("Buffet\nThe balance for the buffet is " + buffetbalance + "\n--------------------------");
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

//	public static void main(String args[]) {
	// loadobjects();
	// invMenu();
	// Arxeia_markou.grapsimo_Inventory();;
	// Arxeia_markou.parsimo_Inventory();
//	}

	public static void loadobjects() {
		Supplier s1 = new Supplier("John", 1, "@1");
		Supplier s2 = new Supplier("Bob", 2, "@2");
		Supplier s3 = new Supplier("Mak", 3, "@3");
		Supplier s4 = new Supplier("Jim", 4, "@4");
		Supplier s5 = new Supplier("Chandler", 5, "@5");
		Inventory i1 = new Inventory("Toilet paper", 100, 5, 70, s1, "Fixed", 0);
		Inventory i2 = new Inventory("Towels", 150, 25, 20, s2, "Fixed", 0);
		Inventory i3 = new Inventory("Shampoo", 200, 50, 20, s3, "Fixed", 0);
		Inventory i4 = new Inventory("Conditioner", 120, 10, 10, s4, "Fixed", 0);
		Inventory i5 = new Inventory("Slippers", 125, 20, 30, s5, "Fixed", 0);
		Inventory iu1 = new Inventory("Vodka", 100, 5, 70, s1, "Urgent", 0);
		Inventory iu2 = new Inventory("Whisky", 150, 25, 20, s2, "Urgent", 0);
		Inventory iu3 = new Inventory("Coca-Cola", 200, 50, 20, s3, "Urgent", 0);
		Inventory iu4 = new Inventory("Sprite", 120, 10, 10, s4, "Urgent", 0);
		Inventory iu5 = new Inventory("Fanda", 125, 20, 30, s5, "Urgent", 0);
	}

	public static ArrayList<Inventory> getFixedInventory() {
		return fixedInventory;
	}

	public static void setFixedInventory(ArrayList<Inventory> fixedInventory) {
		Inventory.fixedInventory = fixedInventory;
	}

	public static ArrayList<Inventory> getUrgentInventory() {
		return urgentInventory;
	}

	public static void setUrgentInventory(ArrayList<Inventory> urgentInventory) {
		Inventory.urgentInventory = urgentInventory;
	}

	public static void setInvFixFin(ArrayList readObject) {

	}

	@Override
	public String toString() {
		return "Inventory [type=" + type + ", name=" + name + ", stock=" + stock + ", pricepreunit=" + pricepreunit
				+ ", minstock=" + minstock + ", suplier=" + suplier + ", balance=" + balance + "]";
	}

	public static void main(String args[]) {
		Supplier s1 = new Supplier("John", 1, "@1");
		Supplier s2 = new Supplier("Bob", 2, "@2");
		Supplier s3 = new Supplier("Mak", 3, "@3");
		Supplier s4 = new Supplier("Jim", 4, "@4");
		Supplier s5 = new Supplier("Chandler", 5, "@5");
		Inventory i1 = new Inventory("Toilet paper", 100, 5, 70, s1, "Fixed", 0);
		Inventory i2 = new Inventory("Towels", 150, 25, 20, s2, "Fixed", 0);
		Inventory i3 = new Inventory("Shampoo", 200, 50, 20, s3, "Fixed", 0);
		Inventory i4 = new Inventory("Conditioner", 120, 10, 10, s4, "Fixed", 0);
		Inventory i5 = new Inventory("Slippers", 125, 20, 30, s5, "Fixed", 0);
		Inventory iu1 = new Inventory("Vodka", 100, 5, 70, s1, "Urgent", 0);
		Inventory iu2 = new Inventory("Whisky", 150, 25, 20, s2, "Urgent", 0);
		Inventory iu3 = new Inventory("Coca-Cola", 200, 50, 20, s3, "Urgent", 0);
		Inventory iu4 = new Inventory("Sprite", 120, 10, 10, s4, "Urgent", 0);
		Inventory iu5 = new Inventory("Fanda", 125, 20, 30, s5, "Urgent", 0);
		invMenu();
	}

	public static String getDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy, HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		return formatter.format(date);
	}

}