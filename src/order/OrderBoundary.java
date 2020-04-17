package order;

// java apis
import java.util.Scanner;
// original packages
import app.AppBoundary;
import menuitem.MenuItemControl;

public class OrderBoundary {
	
	// shorten variable name
	private Scanner scanner = AppBoundary.scanner;
	// load controls
	private OrderControl orderControl = new OrderControl();
	private MenuItemControl menuItemControl = new MenuItemControl();
	
	public void display() {
		int option = -1;
		
		while (!(option == 0)) {
			// display menu
			System.out.println("===== Room Service Order Menu");
			System.out.println("1. Display");
			System.out.println("2. Create");
			System.out.println("3. Update");
			System.out.println("4. Reset Orders");
			System.out.println("0. Back to Main Menu");
			System.out.println("=====");
			
			// get option
			option = AppBoundary.inIntInRange("Option: ", 0, 4);
			
			// process option
			int choice;
			String orderId;
			String menuItemName;
			String remarks;
			
			switch(option) {
				case 1:
					orderControl.displayList();
					System.out.println();
					break;
				case 2:
					// display menu
					System.out.println("Menu:");
					menuItemControl.displayList();
					System.out.println();
					
					// get input
					System.out.printf("Enter menu item name to order: ");
					menuItemName = scanner.nextLine();
					System.out.printf("Enter remarks: ");
					remarks = scanner.nextLine();
					
					orderControl.create(menuItemName, remarks);
					System.out.println();
					break;
				case 3:
					// get input
					System.out.printf("Enter order id to update: ");
					orderId = scanner.nextLine();
					System.out.printf("Enter remarks: ");
					remarks = scanner.nextLine();
					System.out.println("Choose status: ");
					System.out.println("1. Confirmed");
					System.out.println("2. Preparing");
					System.out.println("3. Delivered");
					choice = AppBoundary.inIntInRange("Option: ", 1, 3);
					
					orderControl.update(orderId, remarks, choice);
					System.out.println();
					break;
				case 4:
					orderControl.resetOrders();
					System.out.println("Orders are reset to default.");
					System.out.println();
					break;
			}
			
			
		}
	}

}
