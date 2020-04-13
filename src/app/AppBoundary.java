package app;

import java.util.Scanner;

import hotel.Hotel;

public class AppBoundary {

    Scanner sc = new Scanner(System.in);

    public void start(Hotel hotel) {
        int choice = -1;
        
        while (!(choice == 0)) {
            System.out.println("Welcome, please select action:");
            System.out.println("0.\tQuit");
            System.out.println("1.\tFind vacant rooms.");
            System.out.println("2.\tView room status report.");
            System.out.println("3.\tUpdate room status.");
            
            System.out.println("4.\tCreate menu item");
            System.out.println("5.\tUpdate menu item");
            System.out.println("6.\tRemove menu item");
            System.out.println("7.\tDisplay menu item list"); // for room service order
            choice = getIntegerInRange("Option: ", 0, 7);
            
            // room
            String roomId;
    		int roomStatus;
    		// menu item
    		String menuItemName;
    		String menuItemDescription;
    		double menuItemPrice;
    		
            switch(choice) {
            	case 1:
            		System.out.println(hotel.getRoomControl().vacantToString());
            		System.out.println();
            		break;
            	case 2:
            		System.out.println(hotel.getRoomControl().byStatusToString());
            		System.out.println();
            		break;
            	case 3:
            		System.out.printf("Enter room id: ");
            		roomId = sc.nextLine();
            		
            		System.out.println("1.\tVacant");
                    System.out.println("2.\tOccupied");
                    System.out.println("3.\tReserved");
                    System.out.println("4.\tUnder Maintenance");
            		roomStatus = getIntegerInRange("Option: ", 1, 4);
            		
            		hotel.getRoomControl().updateRoom(roomId, roomStatus);
            		break;
            	case 4:
            		System.out.printf("Enter menu item name to create: ");
            		menuItemName = sc.nextLine();
            		
            		System.out.printf("Enter description: ");
            		menuItemDescription = sc.nextLine();
            		
            		menuItemPrice = getPosDouble("Enter price: ");
            		
            		hotel.getMenuItemControl().create(menuItemName, menuItemDescription, menuItemPrice);
            		break;
            	case 5:
            		System.out.printf("Enter menu item name to update: ");
            		menuItemName = sc.nextLine();
            		
            		System.out.printf("Enter new description: ");
            		menuItemDescription = sc.nextLine();
            		
            		menuItemPrice = getPosDouble("Enter new price: ");
            		
            		hotel.getMenuItemControl().update(menuItemName, menuItemDescription, menuItemPrice);
            		break;
            	case 6:
            		System.out.printf("Enter menu item name to remove: ");
            		menuItemName = sc.nextLine();
            		
            		hotel.getMenuItemControl().remove(menuItemName);
            		System.out.println();
            		break;
            	case 7:
            		hotel.getMenuItemControl().displayList();
            		System.out.println();
            		break;
            		
            }
        }
    }

    private int getIntegerInRange(String message, int low_inclusive, int high_inclusive) {
        int input;
        
        try {
        	System.out.printf(message);
            input = Integer.parseInt(sc.nextLine());
            
            if (input < low_inclusive || input > high_inclusive) {
                System.out.printf("Out of bounds. Please enter an integer from %d to %d.\n", low_inclusive, high_inclusive);
                input = getIntegerInRange(message, low_inclusive, high_inclusive);
            }
        } 
        catch (Exception e) {
            System.out.printf("Only integers are accepted. Please enter an integer from %d to %d.\n", low_inclusive, high_inclusive);
            input = getIntegerInRange(message, low_inclusive, high_inclusive);
        }
        
        System.out.println();
        return input;
    }
    
    private double getPosDouble(String message) {
    	double input;
    	
    	try {
    		System.out.printf(message);
    		input = Double.parseDouble(sc.nextLine());
    		if (input < 0) {
    			System.out.println("Only positive numbers are accepted.");
        		input = getPosDouble(message);
    		}
    	}
    	catch (Exception e) {
    		System.out.println("Only positive numbers are accepted.");
    		input = getPosDouble(message);
    	}
    	
    	System.out.println();
    	return input;
    }
}

