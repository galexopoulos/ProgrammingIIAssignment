package src.main.java.gr.aueb.dmst.erp;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * This class stores the customers data, creates customer accounts if needed and
 * gives benefits to the customers with accounts.
 * 
 * @author Georgios Alkis Alexopoulos
 *
 */
public class CustomerEntry {

	private static ArrayList<AccountCustomers> accCustomerBase = new ArrayList<AccountCustomers>();
	private static ArrayList<ReportingClients> customerBase = new ArrayList<ReportingClients>();

	public static double Entry(int daysofstay, double payment) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Is this a new Customer?");
		String ans = null;
		do {
			ans = sc.nextLine();
			if (!(ans.equalsIgnoreCase("yes")) && !(ans.equalsIgnoreCase("no"))) {
				System.out.println("answer yes or no");
			}
		} while (!(ans.equalsIgnoreCase("yes")) && !(ans.equalsIgnoreCase("no")));
		if (ans.equalsIgnoreCase("yes")) {
			String name = null;
			String surname = null;
			String email = null;
			int know = 0;
			int satisfuction = 0;
			int family_alone_friends = 0;
			int reasonfortravel = 0;

			System.out.println("Enter client's first name");

			name = sc.nextLine();

			System.out.println("Enter client's surname");
			surname = sc.nextLine();
			System.out.println("Enter client's email");
			email = sc.nextLine();
			do {
				System.out.println("Enter client's satisfuction from 1-5");
				try {
					satisfuction = sc.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("Insert an Integer!");
					sc.nextLine();
					continue;
				}
				if (satisfuction != 1 && satisfuction != 2 && satisfuction != 3 && satisfuction != 4
						&& satisfuction != 5) {
					System.out.println("Insert 1 or 2 or 3! ");
				}
			} while (satisfuction != 1 && satisfuction != 2 && satisfuction != 3 && satisfuction != 4
					&& satisfuction != 5);

			System.out.println("Enter how you got informed about our hotel:" + "\n from advertisements type 0"
					+ "\n from a friend that is a previous customer type 1" + "\n from another scource type 2");
			String t = sc.nextLine();
			do {
				know = sc.nextInt();

			} while (know != 0 && know != 1 && know != 2);
			do {
				System.out.println("Enter:" + "\n 1 for traveling with family" + "\n 2 for traveling alone"
						+ "\n 3 for traveling with friends");
				try {
					family_alone_friends = sc.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("Insert an Integer!");
					sc.nextLine();
					continue;
				}
				if (family_alone_friends != 1 && family_alone_friends != 2 && family_alone_friends != 3) {
					System.out.println("Insert 1 or 2 or 3! ");
				}
			} while (family_alone_friends != 1 && family_alone_friends != 2 && family_alone_friends != 3);
			System.out.println();
			do {
				System.out.println("Why did you chose to travel? " + "\nfor business type 1 " + "\nfor pleasure type 2"
						+ "\nfor group trips type 3" + "\nfor none of the above type 4");
				try {
					reasonfortravel = sc.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("Insert an Integer!");
					continue;
				}
				if (reasonfortravel != 1 && reasonfortravel != 2 && reasonfortravel != 3 && reasonfortravel != 4) {
					System.out.println("Insert 1 or 2 or 3 or 4!");
				}
			} while (reasonfortravel != 1 && reasonfortravel != 2 && reasonfortravel != 3 && reasonfortravel != 4);
			ReportingClients e = new ReportingClients(name, surname, satisfuction, payment, family_alone_friends,
					daysofstay, reasonfortravel, email, know);
			customerBase.add(e);

			System.out.println("Does the client want to make a hotel account?");
			String an = null;
			sc.nextLine();
			do {
				an = sc.nextLine();
				if (!(ans.equalsIgnoreCase("yes")) && !(ans.equalsIgnoreCase("no"))) {
					System.out.println("answer yes or no");
				}
			} while (!(ans.equalsIgnoreCase("yes")) && !(ans.equalsIgnoreCase("no")));
			if (an.equalsIgnoreCase("no")) {
				sc.close();
				return payment;
			} else if (an.equalsIgnoreCase("yes")) {
				sc.nextLine();
				System.out.println("Enter your username");
				String username = sc.nextLine();
				System.out.println("Enter your password");
				String password = sc.nextLine();
				double totalpayment = payment;
				String id = String.format("%07d", ReportingClients.getCounter_st());
				Membership membershipCode = MembershipSelection(payment);
				payment = payment - membershipCode.beneffitsPay() * payment;
				AccountCustomers ac = new AccountCustomers(username, password, totalpayment, id, membershipCode);
				accCustomerBase.add(ac);
				sc.close();
				return (payment);
			}
		} else {
            System.out.println("Existing Customer:");
			System.out.println("Please type the client Username");
			String usr = sc.nextLine();
			System.out.println("Please type the client password");
			String pswrd = sc.nextLine();
			for (int i = 0; i <= AccountCustomers.getCounteracc(); i++) {
				AccountCustomers t = accCustomerBase.get(i);
				if (t.getUsername() == usr) {
					if (t.getPassword() == pswrd) {
						System.out.println("Correct creditentials");
						ReportingClients t2 = customerBase.get(Integer.parseInt(t.getId()));
						t2.setDaysofstay(daysofstay);
						t.setPayment(t.getTotalPayment() + payment - t.getMembershipCode().beneffitsPay() * payment);

						sc.close();
						return (payment - t.getMembershipCode().beneffitsPay() * payment);

					}
				}
			}

		}
		return payment;

	}

	public static Membership MembershipSelection(double payment) {
		if (payment < 400) {
			return Membership.BRONZE;
		} else if (payment <= 800) {
			return Membership.SILVER;
		} else if (payment <= 1200) {
			return Membership.GOLD;
		} else {
			return Membership.DIAMOND;
		}
	}

}
