package main_app;

import java.sql.SQLException;
import java.util.Scanner;

import admin_details.Admin;
import bill_details.Bill;
import consumer_details.Consumer;
import consumer_details.GuestConsumer;
import consumer_details.RegisteredConsumer;

public class MainClass {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws ClassNotFoundException, SQLException  {

		Scanner scanner = new Scanner(System.in);
		int choice;
		System.out.println("---------WELCOME TO ELECTRICITY BILL CALCULATOR---------");
		System.out.println("Choose your role");
		System.out.println("1) Administrator");
		System.out.println("2) Consumer");
		System.out.println("3) Exit");
		choice = scanner.nextInt();
		scanner.nextLine();
		switch (choice) {
			case 1: {
					System.out.print("Enter Username :");
					String username = scanner.nextLine();
					System.out.print("Enter Password :");
					String password = scanner.nextLine();
					if (Admin.login(username, password)) {
						System.out.println("Welcome Admin!");
						boolean rule = true;
						String answer;
						while(rule) {			
							System.out.println("Choose an option: ");
							System.out.println("1) Add units consumed in a month for a consumer");
							System.out.println("2) Generate bill of specific month and year of a particular consumer");
							System.out.println("3) Generate all bills of all months and year for which a particular consumer has consumed electricity");
							System.out.println("4) Generate bills as per city and area ");

							int option = scanner.nextInt();
							switch (option) {
								case 1: {

										Admin.addUnitsConsumed();
								}
								break;
								case 2: {
									System.out.println("Enter Consumer Id");
									int id = scanner.nextInt();
									System.out.println("Enter year");
									int year = scanner.nextInt();
									scanner.nextLine();
									System.out.println("Enter month");
									String month = scanner.nextLine();
									Bill bill_1 = Admin.getBillByYearAndMonth(id, year, month);
									System.out.println("billId: "+ bill_1.getBillId()+" , consumer id: "+bill_1.getConsumerId()+" unitsConsumed: "+bill_1.getUnitsConsumed()+" , year: "+bill_1.getYear()+" , month: "+bill_1.getMonth()+", totalAmount: "+bill_1.getTotalAmount());		
								}
								break;
								case 3: {
									System.out.println("Enter Consumer Id");
									int id = scanner.nextInt();
									Admin.getAllBillsByYearAndMonth(id);
								}
								break;
								case 4: {
									Admin.getAllBillsByCityAndArea();
								}
								break;
								default:
									System.out.println("Invalid choice.");	
									rule = false;
							}
							System.out.println("Do you want to continue y or n");
							answer = scanner.next();
							scanner.nextLine();
							if (!answer.equalsIgnoreCase("y")) {
								rule = false;
							} else rule = true;
							
						} //while loop ends here
					} else 
						System.out.println("ERROR! entered details are wrong please enter correct details");
					
			} //case 1 ends here
			break;
			case 2: {
				System.out.println("Choose option");
				System.out.println("1) New User? Register");
				System.out.println("2) Existing User");
				int option1 = scanner.nextInt();
				scanner.nextLine();
				switch (option1) {
					case 1: {
						System.out.println("Enter consumer name");
						String name = scanner.nextLine();
						System.out.println("Enter city");
						String city = scanner.nextLine();
						System.out.println("Enter area");
						String area = scanner.nextLine();
						System.out.println("Enter connection type");
						String connection_type = scanner.nextLine();
						System.out.println("Enter password");
						String password = scanner.nextLine();
						int register = GuestConsumer.register(name, city, area, connection_type, password);
						if (register==0) {
							System.out.println("Sorry, registration failed.");
							return;
						}
						System.out.println("Registration is Successfull.");
						System.out.println("Your Consumer ID: " + GuestConsumer.getConsumerId(name, password));
					}
						break;
					case 2: {
						System.out.println("Enter your Consumer ID");
						int id = scanner.nextInt();
						scanner.nextLine();
						System.out.println("Enter your password");
						String password = scanner.nextLine();
						Consumer rgsConsumer = RegisteredConsumer.login(id, password);
						if (rgsConsumer == null) {
							System.out.println("Invalid id or password");
							break;
						}
						System.out.println("Welcome " + rgsConsumer.getName());
						String ch = "yes";
						while (ch.equalsIgnoreCase("yes") || ch.equalsIgnoreCase("y")) {
							System.out.println("Choose an option");
							System.out.println("1. Generate bill of specific month and year ");
							System.out.println("2. Generate all existing bills");
							System.out.print("Enter your choice: ");
							int option2 = scanner.nextInt();
							scanner.nextLine();
							System.out.println("-------------------------------");
							switch (option2) {
								case 1: {
									System.out.println("Enter year");
									int year = scanner.nextInt();
									scanner.nextLine();
									System.out.println("Enter month");
									String month = scanner.nextLine();
									Bill bill_1 = RegisteredConsumer.getBillByYearAndMonth(id, year, month);
									System.out.println("billId: "+ bill_1.getBillId()+" , consumer id: "+bill_1.getConsumerId()+" , unitsConsumed: "+bill_1.getUnitsConsumed()+" , year: "+bill_1.getYear()+" , month: "+bill_1.getMonth()+" , totalAmount:s "+bill_1.getTotalAmount());	
								}
									break;
								case 2: {
									RegisteredConsumer.getAllBillsByYearAndMonth(id);
								}
									break;
								default:
									System.out.println("Invalid choice");
							}
							System.out.print("Do you wish to continue? y or n");
							ch = scanner.nextLine();
						}
					}
						break;
					default: {
						System.out.println("Invalid choice");
					}
				}
			}
				break;
			case 3:{
				System.out.println("Thank you for using Electricity Bill Calculator.\n");
				return;
			}
			default:
				System.out.println("Invalid choice");
		}
		scanner.close();
		System.out.println("\nThank you for using Electricity Bill Calculator.");
		System.out.println("Directing you to the home page....\n");
		System.out.println("*********************************************************************\n");


	}

}
