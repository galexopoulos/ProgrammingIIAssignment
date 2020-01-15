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

	public static double Entry(int daysofstay, double payment) {
		/** Scanner used for input. */
		Scanner sc = new Scanner(System.in);
		for (;;) {
			System.out.println("Does this customer already have an account? (Enter 'yes' or 'no')");
			String ans = null;
			do {
				/** Checks if the customer has already an account. */
				ans = sc.nextLine();
				/** False data entry check. */
				if (!(ans.equalsIgnoreCase("yes")) && !(ans.equalsIgnoreCase("no"))) {
					System.out.println("answer yes or no");
				}
			} while (!(ans.equalsIgnoreCase("yes")) && !(ans.equalsIgnoreCase("no")));
			if (ans.equalsIgnoreCase("no")) {
				/** Customers first name. */
				String name = null;
				/** Customers surname. */
				String surname = null;
				/** Customers email. */
				String email = null;
				/** Data about how the customer got informed of our hotel. */
				int know = 0;
				/** Customers satisfaction from our amenities. */
				int satisfuction = 0;
				/** Customers travel companion. */
				int family_alone_friends = 0;
				/** Customers reason for traveling. */
				int reasonfortravel = 0;
				/** Data entry. */
				System.out.println("Enter client's first name :");

				name = sc.nextLine();

				System.out.println("Enter client's surname :");
				surname = sc.nextLine();
				System.out.println("Enter client's email :");
				email = sc.nextLine();
				do {
					System.out.println("Enter client's satisfuction :" + "\n 1) Not satisfied at all!"
							+ "\n 2) Not satisfied" + "\n 3) Not happy but not satisfied either" + "\n 4) Satisfied"
							+ "\n 5) Very Satisfied!");
					try {
						satisfuction = sc.nextInt();
					} catch (InputMismatchException e) {
						/** False data entry check. */
						System.out.println("Insert an Integer!");
						sc.nextLine();
						continue;
					}
					if (satisfuction != 1 && satisfuction != 2 && satisfuction != 3 && satisfuction != 4
							&& satisfuction != 5) {
						/** False data entry check. */
						System.out.println("Insert one number from 1 to 5! ");
					}
					/** False data entry check. */
				} while (satisfuction != 1 && satisfuction != 2 && satisfuction != 3 && satisfuction != 4
						&& satisfuction != 5);
				sc.nextLine();
				do {
					System.out.println("Enter how the customer got informed about our hotel :"
							+ "\n 1) from advertisements " + "\n 2) from a friend that is a previous customer "
							+ "\n 3) from another scource");
					/** Data entry. */
					try {
						/** Data entry. */
						know = sc.nextInt();
					} catch (InputMismatchException e) {
						/** False data entry check. */
						System.out.println("Insert an Integer!");
						sc.nextLine();
						continue;
					}
					/** False data entry check. */
					if (know != 1 && know != 2 && know != 3) {
						System.out.println("Insert 1 or 2 or 3! ");
					}
				} while (know != 3 && know != 1 && know != 2);
				do {
					System.out.println("Enter traveling with :" + "\n 1) Family" + "\n 2) Alone" + "\n 3) Friends");
					try {
						/** Data entry. */
						family_alone_friends = sc.nextInt();
					} catch (InputMismatchException e) {
						/** False data entry check. */
						System.out.println("Insert an Integer!");
						sc.nextLine();
						continue;
					}
					/** False data entry check. */
					if (family_alone_friends != 1 && family_alone_friends != 2 && family_alone_friends != 3) {
						System.out.println("Insert 1 or 2 or 3! ");
					}
				} while (family_alone_friends != 1 && family_alone_friends != 2 && family_alone_friends != 3);
				System.out.println();
				do {
					System.out.println("Enter travel reason : " + "\n1) For business purposes.  "
							+ "\n2) For relaxation." + "\n3) For the hotels location." + "\n4) For none of the above.");
					try {
						/** Data entry. */
						reasonfortravel = sc.nextInt();
						/** False data entry check. */
					} catch (InputMismatchException e) {
						System.out.println("Insert an Integer!");
						sc.nextLine();
						continue;
					}
					/** False data entry check. */
					if (reasonfortravel != 1 && reasonfortravel != 2 && reasonfortravel != 3 && reasonfortravel != 4) {
						System.out.println("Insert 1 or 2 or 3 or 4!");
					}
				} while (reasonfortravel != 1 && reasonfortravel != 2 && reasonfortravel != 3 && reasonfortravel != 4);
				new ReportingClients(name, surname, satisfuction, payment, family_alone_friends, daysofstay,
						reasonfortravel, email, know);
				/** Checks if the customer would wish to create a hotel account */
				sc.nextLine();
				System.out.println("Does the client want to make a customer account? (Enter 'yes' or 'no')");
				String an = null;
				do {
					/** Data entry. */
					an = sc.nextLine();
					/** False data entry check. */
					if (!(an.equalsIgnoreCase("yes")) && !(an.equalsIgnoreCase("no"))) {
						System.out.println("answer yes or no");
					}
				} while (!(an.equalsIgnoreCase("yes")) && !(an.equalsIgnoreCase("no")));
				/** Case that the customer does not wish to create an account. */
				if (an.equalsIgnoreCase("no")) {
					return payment;
					/** Customer account creation process. */
				} else if (an.equalsIgnoreCase("yes")) {
					String username = null;
					boolean exists = false;
					do {
						exists = false;
						/** Customers account user name. */
						System.out.println("Enter your username:");
						username = sc.nextLine();
						for (AccountCustomers t : AccountCustomers.accCustomerBase) {
							if (t.getUsername().equals(username)) {
								exists = true;
								System.out.println("This username already exists!");
								break;
							}
						}
					} while (exists);
					/** Customers account password. */
					System.out.println("Enter your password:");
					String password = sc.nextLine();

					/**
					 * Variable in order to store the total payment that the customer will make over
					 * the years.
					 */
					double totalpayment = payment;
					/**
					 * Variable that shows the customers position in the Reporting Clients array
					 * list.
					 */
					String id = String.format("%07d", ReportingClients.getCounter_st());
					/** Membership Selection. */
					Membership membershipCode = MembershipSelection(payment);
					/** Discount in customers payment. */
					payment = payment - membershipCode.beneffitsPay() * payment;
					/** Creates new AccountCustomers object. */
					AccountCustomers ac = new AccountCustomers(username, password, totalpayment, id, membershipCode);
					System.out.println("You are " + ac.getMembershipCode() + " member!" + "\n That means you have a "
							+ ac.getMembershipCode().beneffitsPay() * 100 + "% discount");
					return (payment);
				}
			} else {
				/** Existing Customer process. */
				System.out.println("Existing Customer,");
				System.out.println("Please type the client Username :");
				String usr = sc.nextLine();
				System.out.println("Please type the client password :");
				String pswrd = sc.nextLine();
				try {
					for (AccountCustomers t : AccountCustomers.accCustomerBase) {
						/** Credentials check. */
						if (t.getUsername().equals(usr)) {
							if (t.getPassword().equals(pswrd)) {
								System.out.println("Correct credentials!");
								ReportingClients t2 = ReportingClients.coustomerBase.get(Integer.parseInt(t.getId()));
								t2.setDaysofstay(t2.getDaysofstay() + daysofstay);
								t.setMembershipCode(MembershipSelection(t.getTotalPayment() + payment));
								t.setPayment(
										t.getTotalPayment() + payment - t.getMembershipCode().beneffitsPay() * payment);
								System.out.println(
										"You are " + t.getMembershipCode() + " member!" + "\n That means you have a "
												+ t.getMembershipCode().beneffitsPay() * 100 + " % discount");
								return (payment - t.getMembershipCode().beneffitsPay() * payment);

							}
						}
					}
				} catch (Exception e) {
					System.out.println("No account customer's available.");
				}

			}
			System.out.println("Wrong Credentials.." + "\n Let's try again.");
		}
	}

	/** This method returns and enum object depending on the payment */
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
