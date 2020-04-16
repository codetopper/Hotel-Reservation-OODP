package menuitem;

// java apis
import java.util.Scanner;
// original packages
import app.AppBoundary;

public class MenuItemBoundary {
	
	// shorten variable name
	Scanner scanner = AppBoundary.scanner;
	// load control
	private MenuItemControl menuItemControl = new MenuItemControl();
	
	public void display() {
		int option = -1;
		
		while (!(option == 0)) {
			// display menu
			System.out.println("===== Menu Item Menu");
			System.out.println("1. Display");
			System.out.println("2. Create");
			System.out.println("3. Update");
			System.out.println("4. Remove");
			System.out.println("0. Back to Main Menu");
			System.out.println("=====");
			
			// get option
			option = AppBoundary.inIntInRange("Option: ", 0, 4);
			
			// process option
			String name;
			String description;
			double price;

			switch(option) {
				case 1:
					menuItemControl.displayList();
					System.out.println();
					break;
				case 2:
					System.out.printf("Enter menu item name to create: ");
					name = scanner.nextLine();
					System.out.printf("Enter description: ");
					description = scanner.nextLine();
					price = AppBoundary.inDoublePos("Enter price: ");
					
					menuItemControl.create(name, description, price);
					System.out.println();
					break;
				case 3:
					System.out.printf("Enter menu item name to update: ");
					name = scanner.nextLine();
					System.out.printf("Enter description: ");
					description = scanner.nextLine();
					price = AppBoundary.inDoublePos("Enter price: ");
					
					menuItemControl.update(name, description, price);
					System.out.println();
					break;
				case 4:
					System.out.printf("Enter menu item name to remove: ");
					name = scanner.nextLine();
					
					menuItemControl.remove(name);
					System.out.println();
					break;
					
			}
		}
	}

}
