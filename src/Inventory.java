
import java.util.ArrayList;
import java.util.Scanner;

public class Inventory {
	static Scanner sc = new Scanner(System.in);
	private String type; // the type of product. it can only be fixed, urgent or buffet//
	private String name; // name of product//
	private int stock; // the remaining amount of the given product//
	private int pricepreunit; // the price per unit of the given product//
	private int minstock; // the minimum amount that that should be in the inventory for the given
							// product//
	private Supplier suplier;// a list of the suppliers for the hotel//
	private double balance; // the amount of money that we have spent for the particular product//
	static private ArrayList<Inventory> fixedInventory = new ArrayList<Inventory>();// generates the list of for all the
	// inventory subjects//
	static private ArrayList<Inventory> urgentInventory = new ArrayList<Inventory>();
	static private int buffet = 300;// the number of people the buffet can go throughout without needing a new order
									// to be placed. the fixed price for the buffet per visitor is 50$//

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
		if (this.type.equals("fixed")) {// if statement that places the products to the according category which they
										// belong in//
			fixedInventory.add(this);
		} else if (this.type.equals("urgent")) {
			urgentInventory.add(this);
		}
	}

	// Start of the Inventory Menu Section//
	public static void displayInvMenu() {
		System.out.println("Welcome to the Inventory manager Menu");
		System.out.println(
				"1)To Update and ckeck the Fixed Invetory please input 1\n2)To Update ckeck the Urgent Invetory please input 2\n3)To Update and ckeck the Buffet please input 3");
		System.out.print("Selection: ");
	}

	public static void question() {
		System.out.println("Would you like to proceed or quit?");
		System.out.println("To proceed enter 9.");
		System.out.println("If you wish to quit enter 0.");
		Scanner q = new Scanner(System.in);

		switch (q.nextInt()) {
		case 0:
			System.out.println("Thank you and godbye.");
			break;

		case 9:
			System.out.println("Please proceed.");
			InvMenu();
			break;
		default:
			System.err.println("Unrecognized option");
			break;
		}
	}

	public static void InvMenu() {
		displayInvMenu();

		switch (sc.nextInt()) {
		case 1:
			updateFixed(100);
			checkFixed();
			question();
			break;

		case 2:
			updateUrgent();
			checkUrgent();
			question();
			break;

		case 3:
			updateBuffet(100);
			question();
			break;
		default:
			System.err.println("Unrecognized option");
			break;
		}
	}

//buffet section//
	public static void updateBuffet(int visitorswithbuffet) {// Updates the buffet based on how many visitors included
																// buffet to their package//
		buffet -= visitorswithbuffet;
		if (buffet <= 50) {// its <= 50 because we use the the supplies for 50 visitors as protection //
			orderBuffet();
		}
	}

	public static void orderBuffet() {// order either the fixed amount(for 300 visitors or orders by input
		System.out.println(
				"The supplies for the buffet are running low and there should be an order\n. Do you want to order the fixed supply (For 300 visitors), or order by input?\n( Select 0 for fixed amount or 1 for order by input)");
		int x;
		try {
			do {
				x = sc.nextInt();
			} while (x == 0 || x == 1);
		} catch (Exception e) {
			System.out.println("please instert only 1 or 0");
			x = sc.nextInt();
		}
		if (x == 1) {

			System.out.println(
					"For how many visitors do you want to order ?\n( the order cannot be 1000 or more at once or less than 50)");
			int y;
			try {
				do {
					y = sc.nextInt();
				} while (y < 50 || y > 1000);
			} catch (Exception e) {
				System.out.println("Please order an amount bitween 50 and 1000 that is not a double");
				y = sc.nextInt();
			}
			buffet += y;
			System.out.println("The nummber of visitors that the buffet can acompany is " + buffet + " ?");
		} else if (x == 0) {
			buffet += 300;
			System.out.println("The nummber of visitors that th buffet can acompany is " + buffet + " ?");
		}

	}

	public static int getBuffetFin(int visitorswithbuffet) {// Visitors with buffet will be calculated by the
		int x = visitorswithbuffet * 50;
		return x;
	}

//end of buffet section//

	// Start of fixedInventory section//
	public static void updateFixed(int totalnumberofpeople) {// gets the total number of people that are in the hotel
																// and subtracts it from the fixed list for very
																// product//
		for (int i = 0; i < fixedInventory.size(); i++) {
			fixedInventory.get(i).stock = -totalnumberofpeople;

		}
	}

	public static void checkFixed() {// checks the list or items that have minstock>stock//
		for (int i = 0; i < fixedInventory.size(); i++) {
			if (fixedInventory.get(i).stock < fixedInventory.get(i).minstock) {
				orderFixed(i);
			}
		}

	}

	public static void orderFixed(int i) {// updates the fixedlist item with index i//
		System.out.println("the suply of the product " + fixedInventory.get(i).name
				+ "is running low and there should be an order. Do you want to order the fixed amount ("
				+ fixedInventory.get(i).minstock * 2
				+ ") or order by input ( Select 0 for fixed amount or 1 for order by input)");
		int ans = sc.nextInt();// order starts
		if (ans == 0) {
			fixedInventory.get(i).stock += fixedInventory.get(i).minstock * 2;
			fixedInventory.get(i).balance += fixedInventory.get(i).minstock * 2 * fixedInventory.get(i).pricepreunit;

		} else if (ans == 1) {
			System.out.println("Give me the order");
			int order = sc.nextInt();
			fixedInventory.get(i).stock += order;
			fixedInventory.get(i).balance += order * fixedInventory.get(i).pricepreunit;// order ends
		}
	}

	public static double[] getInvFixFin() {// gives an array with all the balances of the products. The product names
											// can be accessed with fixedInventory.get(i).name; and there connected to
											// the array via the index.//
		double x[] = new double[fixedInventory.size()];
		for (int i = 0; i <= fixedInventory.size(); i++) {
			x[i] = fixedInventory.get(i).balance;
		}
		return x;
	}

	// End of fixedInventory section//

	// Start of urgentInventort section//
	public static void updateUrgent() {

	}

	public static void checkUrgent() {
		for (int i = 0; i < urgentInventory.size(); i++) {
			if (urgentInventory.get(i).stock < urgentInventory.get(i).minstock) {
				orderUrgent(i);

			}
		}

	}

	public static void orderUrgent(int i) {// takes the index of a product that is low on stock and an order should be
											// made//

		System.out.println("the suply of the product " + urgentInventory.get(i).name
				+ "is running low and there should be an order. Do you want to order the fixed amount ("
				+ urgentInventory.get(i).minstock * 2
				+ ") or order by input ( Select 0 for fixed amount or 1 for order by input)");
		int ans = sc.nextInt();// order starts
		if (ans == 0) {
			urgentInventory.get(i).stock += urgentInventory.get(i).minstock * 2;
			urgentInventory.get(i).balance += urgentInventory.get(i).minstock * 2 * urgentInventory.get(i).pricepreunit;
		} else if (ans == 1) {
			System.out.println("Give me the order");
			int order = sc.nextInt();
			urgentInventory.get(i).stock += order;// order ends
			urgentInventory.get(i).balance += order * urgentInventory.get(i).pricepreunit;
		}

	}

	public static double[] getInvUrgFin() {// gives an array with all the balances of the products. The product names
		// can be accessed with urgentInventory.get(i).name; and there connected to
		// the array via the index.//
		double x[] = new double[urgentInventory.size()];
		for (int i = 0; i <= urgentInventory.size(); i++) {
			x[i] = urgentInventory.get(i).balance;
		}
		return x;
	}
	// End of urgentInventory section//

}
