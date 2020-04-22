package room;

// java apis
import java.util.Scanner;
// original packages
import app.AppBoundary;

public class RoomBoundary {

    // shorten variable name
    Scanner scanner = AppBoundary.scanner;
    // load control
    private RoomControl roomControl = new RoomControl();

    public void display() {
        int option = -1;

        while (!(option == 0)) {
            // display menu
            System.out.println();
            System.out.println("=== Room Information ===");
            System.out.println("1. Check Room Details");
            System.out.println("2. Update Room Status");
            System.out.println("3. Display Vacant Rooms by Room Type");
            System.out.println("4. Sort Rooms by Status");
            System.out.println("0. Back to Main Menu");
            System.out.println("========================");

            // get option
            option = AppBoundary.inIntInRange("Option: ", 0, 4);

            // process option
            int choice;
            String roomId;
            String customerName;

            switch(option) {
                case 1:
                    System.out.println("Choose option:");
                    System.out.println("1. Use Room ID");
                    System.out.println("2. Use Guest Name");
                    choice = AppBoundary.inIntInRange("Option: ", 1, 2);
                    switch(choice) {
                        case 1:
                            System.out.printf("Room ID: ");
                            roomId = scanner.nextLine();
                            if (!roomControl.validateRoomId(roomId)) {
                                System.out.println();
                                System.out.println("There is no Room " + roomId + ".");
                                break;
                            }
                            System.out.println();
                            System.out.println(roomControl.getAvailabilityByRoomId(roomId));
                            break;
                        case 2:
                            System.out.printf("Guest Name: ");
                            customerName = scanner.nextLine();
                            System.out.println();
                            System.out.println(roomControl.getAvailabilityByGuestName(customerName));
                            break;
                    }
                    break;
                case 2:
                    System.out.printf("Enter room id to update: ");
                    roomId = scanner.nextLine();
                    if (!roomControl.validateRoomId(roomId)) {
                        System.out.println();
                        System.out.println("There is no Room " + roomId + ".");
                        break;
                    }
                    System.out.println("Choose status:");
                    System.out.println("1. Vacant");
                    System.out.println("2. Occupied");
                    System.out.println("3. Reserved");
                    System.out.println("4. Under Maintenance");
                    choice = AppBoundary.inIntInRange("Option: ", 1, 4);
                    System.out.println();
                    System.out.println(roomControl.updateStatus(roomId, choice));
                    break;
                case 3:
                    System.out.println();
                    System.out.println(roomControl.getAvailabilityByRoomType());
                    break;
                case 4:
                    System.out.println();
                    System.out.println(roomControl.getByStatus());
                    break;
            }
        }
    }
}
