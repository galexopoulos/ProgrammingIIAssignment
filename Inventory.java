
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
	static private int buffet = 300;// the number of people the buffet can go throught without needing a new order
									// to be placed//

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
			fixedInventory.get(i).stock += order;// order ends
		}

	}
}