package main;// java apis
import java.util.Scanner;
// original packages
import boundary_classes.*;
import data.DataUtil;

public class MainBoundary {
	
	public static Scanner scanner = new Scanner(System.in);
	// sub-boundaries
	RoomServiceBoundary roomServiceBoundary = new RoomServiceBoundary();
	RoomBoundary roomBoundary = new RoomBoundary();
	GuestBoundary guestBoundary = new GuestBoundary();
	ReservationBoundary reservationBoundary = new ReservationBoundary();
	CheckInBoundary checkInBoundary = new CheckInBoundary();
	CheckOutBoundary checkOutBoundary = new CheckOutBoundary();
	DataUtil dataUtil = new DataUtil();
	
	public void display() {
		int option = -1;
		
		while (!(option == 0)) {
			// display menu
			System.out.println("===== Main Menu");
			System.out.println("1. Room Service");
			System.out.println("2. Guest Information");
			System.out.println("3. Room Information");
			System.out.println("4. Reservation");
			System.out.println("5. Check In");
			System.out.println("6. Check Out");
			System.out.println("7. Reset Hotel");
			System.out.println("0. Quit");
			System.out.println("=====");
			
			// get option
			option = inIntInRange("Option: ", 0, 7);
			
			// process option
			switch(option) {
				case 1:
					roomServiceBoundary.display();
					break;
				case 2:
					guestBoundary.display();
					break;
				case 3:
					roomBoundary.display();
					break;
				case 4:
					reservationBoundary.display();
					break;
				case 5:
					checkInBoundary.display();
					break;
				case 6:
					checkOutBoundary.display();
					break;
				case 7:
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
