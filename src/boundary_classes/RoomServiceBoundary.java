package boundary_classes;

import main.MainBoundary;

public class RoomServiceBoundary {

    MenuItemBoundary menuItemBoundary = new MenuItemBoundary();
    OrderBoundary orderBoundary = new OrderBoundary();

    public void display() {
        int option = -1;

        while (!(option == 0)) {
            // display menu
            System.out.println("===== Room Service Menu");
            System.out.println("1. Update Menu Items");
            System.out.println("2. Orders");
            System.out.println("0. Back to Main Menu");
            System.out.println("=====");

            // get option
            option = MainBoundary.inIntInRange("Option: ", 0, 2);

            switch(option) {
                case 1:
                    menuItemBoundary.display();
                    break;
                case 2:
                    orderBoundary.display();
                    break;

            }
        }
    }
}
