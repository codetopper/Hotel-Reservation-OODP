package boundaries;

import controls.MenuItemController;
import controls.OrderController;
import controls.RoomController;
import main.MainBoundary;

import java.util.Scanner;

public class RoomServiceBoundary{
    MainBoundary mainBoundary;
    Scanner sc;

    public RoomServiceBoundary()
    {
        mainBoundary = new MainBoundary();
        sc = new Scanner(System.in);
    }

    public void display() {
        int option = -1;
        MenuItemBoundary menuItemBoundary;
        OrderBoundary orderBoundary;

        while (!(option == 0)) {
            // display menu
            System.out.println();
            System.out.println("=== Room Service Menu ===");
            System.out.println("1. Update Menu Items");
            System.out.println("2. Orders");
            System.out.println("0. Back to Main Menu");
            System.out.println("=========================");

            // get option
            option = mainBoundary.getIntegerOnly("Option: ", 0, 2);

            switch(option) {
                case 1:
                    menuItemBoundary = new MenuItemBoundary();
                    menuItemBoundary.display();
                    break;
                case 2:
                    orderBoundary = new OrderBoundary();
                    orderBoundary.display();
                    break;
            }
        }
    }
}
