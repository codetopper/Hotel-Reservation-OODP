package boundaries;

import controls.GuestController;
import controls.RoomController;
import entities.Guest;
import main.MainBoundary;

import java.util.Scanner;

public class GuestBoundary {

    private GuestController guestController;
    MainBoundary mainBoundary;
    Scanner sc;


    public GuestBoundary()
    {
        guestController = GuestController.getInstance();
        mainBoundary = new MainBoundary();
        sc = new Scanner(System.in);
    }

    public void display() {
        int option = -1;

        while (option != 0) {
            System.out.println();
            System.out.println("=== Guest Menu ===");
            System.out.println("1. Update");
            System.out.println("2. Search");
            System.out.println("0. Back to Main Menu");
            System.out.println("==================");

            option = mainBoundary.getIntegerOnly("Option: ", 0, 2);

            /*to update Guest details*/
            if (option == 1) {
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
                        choice = mainBoundary.getIntegerOnly("Option: ", 0, 8);
                        String input = "";
                        Guest g = null;
                        for (Guest guest: guestController.guests ) {
                            if (guest.getId().equals(givenId)) {
                                g = guest;
                            }
                        }
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
                        System.out.println();
                        System.out.println(guestController.updateGuest(givenId, choice, input));
                    }
                }
                else{
                    System.out.println();
                    System.out.println("There is no guest with the identity number " + givenId);
                }
            }
            /*to search Guest details*/
            if (option == 2) {
                System.out.print("Please enter the identity number: ");
                String identity = sc.nextLine();
                System.out.print(guestController.searchGuest(identity));
            }
        }
    }

    private boolean validateGuest (String givenId){

        if (guestController.searchGuest(givenId).equals("Guest not found!\n\n")) {
            return false;
        }
        return true;
    }
    
}
