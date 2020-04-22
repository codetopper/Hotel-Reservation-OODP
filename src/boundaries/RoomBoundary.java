package boundaries;

import controls.MenuItemController;
import controls.RoomController;
import entities.Room;
import main.MainBoundary;

import java.util.Scanner;

public class RoomBoundary {

    private RoomController roomController;
    MainBoundary mainBoundary;
    Scanner sc;

    public RoomBoundary()
    {
        roomController = RoomController.getInstance();
        mainBoundary = new MainBoundary();
        sc = new Scanner(System.in);
    }

    public void display() {
        int choice = -1;
        String roomId;

        while (choice != 0) { //to be changed
            System.out.println();
            System.out.println("=== Room Information ===");
            System.out.println("1.\tCheck Room Details");
            System.out.println("2.\tUpdate Room Status");
            System.out.println("3.\tDisplay Availability by Room Type");
            System.out.println("4.\tSort Rooms by Status");
            System.out.println("0.\tBack to Main Menu");
            System.out.println("========================");

            choice = mainBoundary.getIntegerOnly("Option: ", 0, 4);

            switch (choice) {
                case 1:
                    Boolean found = false;
                    System.out.printf("Enter room id: ");
                    roomId = sc.next();
                    System.out.println();
                    for (Room room: roomController.rooms) {
                        if (room.getRoomId().equals(roomId)) {
                            System.out.println(room.roomDetails());
                            found = true;
                            break;
                        }
                    }
                    if (found==false) {
                        System.out.println("Room " + roomId + " does not exist.");
                    }
                    break;
                case 2:
                    System.out.printf("Enter room id: ");
                    roomId = sc.next();
                    System.out.println("Choose status:");
                    System.out.println("1. Vacant");
                    System.out.println("2. Occupied");
                    System.out.println("3. Reserved");
                    System.out.println("4. Maintenance");
                    choice = mainBoundary.getIntegerOnly("Option: ", 1, 4);
                    System.out.println();
                    System.out.println(roomController.updateStatus(roomId, choice));
                    break;
                case 3:
                    System.out.println();
                    System.out.println(roomController.showVacantRoomsByRoomType());
                    break;
                case 4:
                    System.out.println();
                    System.out.println(roomController.showRoomsByStatus());
                    break;
            }
        }
    }

}
