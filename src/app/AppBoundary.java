package app;

// java apis
import java.util.Scanner;
// original packages
import menuitem.MenuItemBoundary;
import room.RoomBoundary;

public class AppBoundary {
	
	public static Scanner scanner = new Scanner(System.in);
	// sub-boundaries
	MenuItemBoundary menuItemBoundary = new MenuItemBoundary();
	RoomBoundary roomBoundary = new RoomBoundary();
	
	public void display() {
		int option = -1;
		
		while (!(option == 0)) {
			// display menu
			System.out.println("===== Main Menu");
			System.out.println("1. Menu Item");
			System.out.println("2. Room Service Order");
			System.out.println("3. Room Information");
			System.out.println("0. Quit");
			System.out.println("=====");
			
			// get option
			option = inIntInRange("Option: ", 0, 3);
			
			// process option
			switch(option) {
				case 1:
					menuItemBoundary.display();
					break;
				case 2:
					System.out.println("option 2");
					break;
				case 3:
					roomBoundary.display();
					break;
				default:
					break;
			}
		}
		
		System.out.println("Session has ended.");
	}
	
	
	// utility functions
	public static int inInt(String message) {
		int input;
		
		try {
			System.out.printf(message);
			input = Integer.parseInt(scanner.nextLine());
		}
		catch (Exception e) {
			System.out.println("Only integers are accepted.");
			input = inInt(message);
		}
		
		return input;
	}
	
	public static double inDouble(String message) {
		double input;
		
		try {
			System.out.printf(message);
			input = Double.parseDouble(scanner.nextLine());
		}
		catch (Exception e) {
			System.out.println("Only doubles are accepted.");
			input = inDouble(message);
		}
		
		return input;
	}
	
	public static int inIntInRange(String message, int lowInclusive, int highInclusive) {
		int input;
		
		try {
			input = inInt(message);
			if (input < lowInclusive || input > highInclusive) {
				throw new Exception();
			}
		}
		catch (Exception e) {
			System.out.println("Input out of bounds.");
			input = inIntInRange(message, lowInclusive, highInclusive);
		}
		
		return input;
	}
	
	public static double inDoublePos(String message) {
		double input;
		
		try {
			input = inDouble(message);
			if (input < 0) {
				throw new Exception();
			}
		}
		catch (Exception e) {
			System.out.println("Only positive doubles are accepted.");
			input = inDoublePos(message);
		}
		
		return input;
	}

}
