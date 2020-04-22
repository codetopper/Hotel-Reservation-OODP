package order;

// java apis
import java.util.Scanner;
// original packages
import app.AppBoundary;
import menuitem.MenuItemControl;
import menuitem.MenuItemDAO;

public class OrderBoundary {
	
	// shorten variable name
	private Scanner scanner = AppBoundary.scanner;
	// load controls
	private OrderControl orderControl = new OrderControl();
	private MenuItemControl menuItemControl = new MenuItemControl();
	private MenuItemDAO menuItemDAO = new MenuItemDAO();
	
	public void display() {
		int option = -1;
		
		while (!(option == 0)) {
			// display menu
			System.out.println();
			System.out.println("=== Room Service Order ===");
			System.out.println("1. Display all Orders");
			System.out.println("2. Create an Order");
			System.out.println("3. Update an Order");
			System.out.println("0. Back to Main Menu");
			System.out.println("==========================");
			
			// get option
			option = AppBoundary.inIntInRange("Option: ", 0, 3);
			
			// process option
			int choice;
			String orderId;
			String menuItemName;
			String remarks;
			
			switch(option) {
				case 1:
					System.out.println();
					orderControl.displayList();
					break;
				case 2:
					// display menu
					System.out.println();
					System.out.println("Menu:");
					if (menuItemDAO.getAllItem().isEmpty()) {
						System.out.printf("There are no items available.");
						break;
					}
					menuItemControl.displayList();
					System.out.println();
					
					// get input
					System.out.printf("Enter menu item name to order: ");
					menuItemName = scanner.nextLine();
					int quantity = AppBoundary.inInt("Enter quantity: ");
					System.out.printf("Enter remarks: ");
					remarks = scanner.nextLine();
					System.out.printf("Enter Room: ");
					String roomId = scanner.nextLine();
					System.out.println();
					System.out.println("Order created.");
					
					orderControl.create(menuItemName, quantity, remarks, roomId);
					break;
				case 3:
					// get input
					System.out.printf("Enter order id to update: ");
					orderId = scanner.nextLine();
					System.out.println("Choose status: ");
					System.out.println("1. Confirmed");
					System.out.println("2. Preparing");
					System.out.println("3. Delivered");
					System.out.println("4. Cancelled");
					choice = AppBoundary.inIntInRange("Option: ", 1, 4);
					
					orderControl.update(orderId, choice);
					break;
			}
			
			
		}
	}

}
