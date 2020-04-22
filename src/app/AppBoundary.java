package app;

// java apis
import java.util.Scanner;
// original packages
import data.DataUtil;
import guest.GuestBoundary;
import menuitem.MenuItemBoundary;
import order.OrderBoundary;
import reservation.ReservationBoundary;
import room.RoomBoundary;

public class AppBoundary {
	
	public static Scanner scanner = new Scanner(System.in);
	// sub-boundaries
	ReservationBoundary reservationBoundary = new ReservationBoundary();
	MenuItemBoundary menuItemBoundary = new MenuItemBoundary();
	OrderBoundary orderBoundary = new OrderBoundary();
	RoomBoundary roomBoundary = new RoomBoundary();
	GuestBoundary guestBoundary = new GuestBoundary();
	DataUtil dataUtil = new DataUtil();
	
	public void display() {
		int option = -1;
		
		while (!(option == 0)) {
			// display menu
			System.out.println();
			System.out.println("=== Main Menu ===");
			System.out.println("1. Reservations");
			System.out.println("2. Menu Items");
			System.out.println("3. Room Service Order");
			System.out.println("4. Room Information");
			System.out.println("5. Guest Information");
			System.out.println("6. Reset Hotel");
			System.out.println("0. Quit");
			System.out.println("=================");
			
			// get option
			option = inIntInRange("Option: ", 0, 6);
			
			// process option
			switch(option) {
			case 1:
				reservationBoundary.display();
				break;
			case 2:
				menuItemBoundary.display();
				break;
			case 3:
				orderBoundary.display();
				break;
			case 4:
				roomBoundary.display();
				break;
			case 5:
				guestBoundary.display();
				break;
			case 6:
				dataUtil.resetHotel();
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
			System.out.print(message);
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