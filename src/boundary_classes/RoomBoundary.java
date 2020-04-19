package boundary_classes;

// java apis
import java.util.Scanner;
// original packages
import main.MainBoundary;
import control_classes.RoomControl;

public class RoomBoundary {

    // shorten variable name
    Scanner scanner = MainBoundary.scanner;
    // load control
    private RoomControl roomControl = new RoomControl();

    public void display() {
        int option = -1;

        while (!(option == 0)) {
            // display menu
            System.out.println("===== Room Menu");
            System.out.println("1. Check availability");
            System.out.println("2. Update Room Status");
            System.out.println("3. Display availability by room type");
            System.out.println("4. Sort by status");
            System.out.println("5. Reset rooms to default");
            System.out.println("0. Back to Main Menu");
            System.out.println("=====");

            // get option
            option = MainBoundary.inIntInRange("Option: ", 0, 5);

            // process option
            int choice;
            String roomId;
            String customerName;

            switch(option) {
                case 1:
                    System.out.println("Choose option:");
                    System.out.println("1. Use room id");
                    System.out.println("2. Use Guest name");
                    choice = MainBoundary.inIntInRange("Option: ", 1, 2);
                    
                    switch(choice) {
                        case 1:
                            System.out.printf("Room id: ");
                            roomId = scanner.nextLine();
                            System.out.println(roomControl.getAvailabilityByRoomId(roomId));
                            break;
                        case 2:
                            System.out.printf("Guest name: ");
                            customerName = scanner.nextLine();
                            System.out.println(roomControl.getAvailabilityByGuestName(customerName));
                            break;
                    }
                    
                    System.out.println();
                    break;
                case 2:
                    System.out.printf("Enter room id to update: ");
                    roomId = scanner.nextLine();
                    System.out.println("Choose status:");
                    System.out.println("1. Vacant");
                    System.out.println("2. Occupied");
                    System.out.println("3. Reserved");
                    System.out.println("4. Maintenance");
                    choice = MainBoundary.inIntInRange("Option: ", 1, 4);
                    System.out.println(roomControl.updateStatus(roomId, choice));
                    System.out.println();
                    break;
                case 3:
                    System.out.println(roomControl.getAvailabilityByRoomType());
                    System.out.println();
                    break;
                case 4:
                    System.out.println(roomControl.getByStatus());
                    System.out.println();
                    break;
                case 5:
                    roomControl.resetRooms();
                    System.out.println("Room is reset to default.");
                    System.out.println();
                    break;
            }
        }
    }
}
