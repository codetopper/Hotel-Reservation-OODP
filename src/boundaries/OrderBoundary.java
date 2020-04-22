package boundaries;

import controls.MenuItemController;
import controls.OrderController;
import main.MainBoundary;

import java.util.Scanner;

public class OrderBoundary{

    // load controls
    private OrderController orderController;
    private MenuItemController menuItemController;
    MainBoundary mainBoundary;
    Scanner sc;

    public OrderBoundary()
    {
        menuItemController = MenuItemController.getInstance();
        orderController = OrderController.getInstance();
        mainBoundary = new MainBoundary();
        sc = new Scanner(System.in);
    }

    public void display() {
        int option = -1;

        while (!(option == 0)) {
            // display menu
            System.out.println();
            System.out.println("===== Room Service Order Menu");
            System.out.println("1. Display");
            System.out.println("2. Create");
            System.out.println("3. Update");
            System.out.println("0. Back to Main Menu");
            System.out.println("=====");

            // get option
            option = mainBoundary.getIntegerOnly("Option: ", 0, 3);

            // process option
            int choice;
            String orderId;
            String menuItemName;
            int quantity;
            String remarks;
            String roomId;

            switch(option) {
                case 1:
                    System.out.println();
                    orderController.displayList();
                    break;
                case 2:
                    // display menu
                    System.out.println();
                    System.out.println("Menu:");
                    menuItemController.displayList();
                    if (menuItemController.menuItems.isEmpty()) {
                        break;
                    }
                    System.out.println();

                    // get input
                    System.out.printf("Enter menu item name to order: ");
                    menuItemName = sc.nextLine();
                    System.out.printf("Enter quantity: ");
                    quantity = sc.nextInt();
                    sc.nextLine();
                    System.out.printf("Enter remarks: ");
                    remarks = sc.nextLine();
                    System.out.printf("Enter Room: ");
                    roomId = sc.nextLine();
                    System.out.println();
                    System.out.println("Order created.");
                    orderController.create(menuItemName, quantity, remarks, roomId);
                    break;
                case 3:
                    // get input
                    System.out.printf("Enter order id to update: ");
                    orderId = sc.nextLine();
                    System.out.println("Choose status: ");
                    System.out.println("1. Confirmed");
                    System.out.println("2. Preparing");
                    System.out.println("3. Delivered");
                    System.out.println("4. Cancelled");
                    choice = mainBoundary.getIntegerOnly("Option: ", 1, 4);

                    orderController.update(orderId, choice);
                    System.out.println();
                    break;
            }


        }
    }
}
