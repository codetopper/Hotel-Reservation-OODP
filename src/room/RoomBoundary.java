package room;

import app.AppBoundary;
import menuitem.MenuItemControl;

import java.util.Scanner;

public class RoomBoundary {
    private RoomControl roomControl = new RoomControl();

    // shorten variable name
    Scanner scanner = AppBoundary.scanner;

    public void display() {
        int option = -1;

        while (!(option == 0)) {
            // display menu
            System.out.println("===== Room Menu");
            System.out.println("1. Check Room Availability");
            System.out.println("2. Update Room Status");
            System.out.println("3. Find Empty Rooms by Room Type");
            System.out.println("4. Sort Rooms by Room Status");
            System.out.println("0. Back to Main Menu");
            System.out.println("=====");

            // get option
            option = AppBoundary.inIntInRange("Option: ", 0, 4);

            // process option
            String roomId;
            String customerName;

            switch(option) {
                case 1:
                    System.out.printf("Enter 1 to input Room ID.\nEnter 2 to input Customer's Name.\n");
                    option = AppBoundary.inIntInRange("Option: ", 1, 2);
                    switch(option) {
                        case 1:
                            System.out.printf("Please enter the Room ID: ");
                            roomId = scanner.nextLine();
                            System.out.println(roomControl.checkRoomAvailability(option, roomId));
                            break;
                        case 2:
                            System.out.printf("Please enter the Customer's Name: ");
                            customerName = scanner.nextLine();
                            System.out.println(roomControl.checkRoomAvailability(option, customerName));
                            break;
                        default:
                            break;
                    }
                case 2:
                    System.out.printf("Please enter the Room ID to be updated: ");
                    roomId = scanner.nextLine();
                    System.out.println("Enter 1 for \"Vacant\".");
                    System.out.println("Enter 2 for \"Occupied\".");
                    System.out.println("Enter 3 for \"Reserved\".");
                    System.out.println("Enter 4 for \"Under Maintenance\".");
                    System.out.println("Please enter " + roomId + " new status: ");
                    option = AppBoundary.inIntInRange("Option: ", 1, 4);
                    System.out.println(roomControl.updateRoomStatus(roomId, option));
                    break;
                case 3:
                    System.out.println(roomControl.findVacantByRoomType());
                    break;
                case 4:
                    System.out.println(roomControl.sortByStatus());
                    break;
                default:
                    break;
            }
        }
    }
}
