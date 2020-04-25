package guest;

import app.AppBoundary;

import java.util.Scanner;

public class GuestBoundary {
		
	private GuestControl gc= new GuestControl();
	Scanner sc = AppBoundary.scanner;

	//attributes
	String name;
	String identity;
	String contact;
	String addr;
	String card;
	String input;
	int genderOption;
	String country;
	String nationality;

	public void display() {
		int option = -1;

		while (option != 0) {
			System.out.println("===== Guest Menu");
			System.out.println("1. Create");
			System.out.println("2. Update");
			System.out.println("3. Search");
			System.out.println("0. Back to Main Menu");
			System.out.println("=====");

			option = AppBoundary.inIntInRange("Option: ", 0, 3);

			/*to create Guest details*/
			if (option == 1) {
				System.out.println("Create Guest details");
				/*takes input for the identity*/
				System.out.print("Please enter the identity number: ");
				identity = sc.nextLine();
				/*takes input for the name*/
				System.out.print("Please enter the name: ");
				name = sc.nextLine();
				/*takes input for the address*/
				System.out.print("Please enter the address: ");
				addr = sc.nextLine();
				/*takes input for the contact number*/
				System.out.print("Please enter the contact number: ");
				contact = sc.nextLine();
				/*takes input for the credit card number*/
				System.out.print("Please enter the credit card number:");
				card = sc.nextLine();
				/*takes input for the country*/
				System.out.print("Please enter the country: ");
				country = sc.nextLine();
				/*takes input for the nationality*/
				System.out.print("Please enter the nationality: ");
				nationality = sc.nextLine();
				/*takes input for the gender*/
				System.out.print("Please select a gender-(1)male (2)female (3)others: ");
				genderOption = sc.nextInt();
				System.out.println();
				System.out.println(gc.createGuest(identity, name, addr, contact, card, country, nationality, genderOption));
				System.out.println();
			}
			/*to update Guest details*/
			if (option == 2) {
				System.out.println("Update Guest details");
				System.out.print("Please enter the identity number: ");
				String givenId = sc.nextLine();
				if (validateGuest(givenId)) {
					int choice = -1;
					while (choice != 0) {
						System.out.print("Please select the option you want to update:\n");
						System.out.println("1. Name");
						System.out.println("2. Country");
						System.out.println("3. Address");
						System.out.println("4. Nationality");
						System.out.println("5. Contact number");
						System.out.println("6. Gender");
						System.out.println("7. Credit card number");
						System.out.println("8. Identity");
						System.out.println("0. Finish update");
						choice = AppBoundary.inIntInRange("Option: ", 0, 8);
						Guest g = gc.dao.getItemById(givenId);
						switch (choice) {
							/*update name*/
							case 1:
								System.out.println("Previous name: " + g.getName());
								System.out.print("Please enter a new name: ");
								input = sc.nextLine();
								break;
							/*update country*/
							case 2:
								System.out.println("Previous country: " + g.getCountry());
								System.out.print("Please enter a new country: ");
								input = sc.nextLine();
								break;

							/*update address*/
							case 3:
								System.out.println("Previous address: " + g.getAddress());
								System.out.print("Please enter a new address: ");
								input = sc.nextLine();
								break;

							/*update nationality*/
							case 4:
								System.out.println("Previous nationality: " + g.getNationality());
								System.out.print("Please enter a new nationality: ");
								input = sc.nextLine();
								break;

							/*update contact number*/
							case 5:
								System.out.println("Previous contact number " + g.getContact());
								System.out.print("Please enter a new contact number: ");
								input = sc.nextLine();
								break;

							/*update gender*/
							case 6:
								System.out.println("Previous gender: " + g.getGender());
								System.out.print("Please enter a new gender: ");
								input = sc.nextLine();
								break;

							/*update credit card number*/
							case 7:
								System.out.println("Previous credit card number " + g.getCreditCard());
								System.out.print("Please enter a new credit card number: ");
								input = sc.nextLine();

								break;

							/*update identity*/
							case 8:
								System.out.println("Previous identity number: " + g.getId());
								System.out.print("Please enter a new identity: ");
								input = sc.nextLine();
								break;
						}
						System.out.println(gc.updateGuest(givenId, choice, input));
						System.out.println();
					}
				}
				else{
						System.out.println("There is no guest with the identity number " + givenId);
						System.out.println();
				}
			}
			/*to search Guest details*/
			if (option == 3) {
				System.out.print("Please enter the identity number: ");
				identity = sc.nextLine();
				System.out.print(gc.searchGuest(identity));
			}
		}
	}

	private boolean validateGuest (String givenId){
		Guest guestMatchingId = gc.dao.getItemById(givenId);

		if (guestMatchingId == null || givenId.isEmpty() || givenId.isBlank()) {
			return false;
		}
		return true;
	}
}








