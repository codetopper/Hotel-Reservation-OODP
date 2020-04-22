package boundaries;

import controls.GuestController;
import controls.MenuItemController;
import controls.RoomController;
import main.MainBoundary;

import java.util.Scanner;

public class MenuItemBoundary{

    // load controls
    private MenuItemController menuItemController;
    MainBoundary mainBoundary;
    Scanner sc;

    public MenuItemBoundary()
    {
        menuItemController = MenuItemController.getInstance();
        mainBoundary = new MainBoundary();
        sc = new Scanner(System.in);
    }

    public void display() {
        int option = -1;

        while (!(option == 0)) {
            // display menu
            System.out.println();
            System.out.println("===== Menu Item Menu");
            System.out.println("1. Display");
            System.out.println("2. Create");
            System.out.println("3. Update");
            System.out.println("4. Remove");
            System.out.println("0. Back to Room Service");
            System.out.println("=====");

            // get option
            option = mainBoundary.getIntegerOnly("Option: ", 0, 4);

            // process option
            String name;
            String description;
            double price;

            switch(option) {
                case 1:
                    System.out.println();
                    menuItemController.displayList();
                    break;
                case 2:
                    System.out.printf("Enter new menu item name: ");
                    name = sc.nextLine();
                    System.out.printf("Enter description: ");
                    description = sc.nextLine();
                    price = mainBoundary.inDoublePos("Enter price: ");

                    menuItemController.create(name, description, price);
                    System.out.println();
                    System.out.println("Item Created: ");
                    menuItemController.displayItem(name);
                    break;
                case 3:
                    System.out.printf("Enter menu item name to update: ");
                    String oldName = sc.nextLine();
                    System.out.printf("Enter new Name: ");
                    String newName = sc.nextLine();
                    System.out.printf("Enter new description: ");
                    description = sc.nextLine();
                    price = mainBoundary.inDoublePos("Enter new price: ");
                    menuItemController.update(oldName, newName, description, price);
                    break;
                case 4:
                    System.out.printf("Enter menu item name to remove: ");
                    name = sc.nextLine();
                    menuItemController.remove(name);
                    System.out.println(name + " is removed from the list.");
                    break;

            }
        }
    }
}
