package reservation;

import java.util.ArrayList;
import java.util.Scanner;

import app.AppBoundary;
import room.Room;
import room.RoomControl;



public class ReservationBoundary {
	
	// shorten variable name
	public static Scanner scanner = AppBoundary.scanner;
	// load control
	private ReservationControl reservationControl = new ReservationControl();
	private ReservationDAO dao = new ReservationDAO();
	private RoomControl roomControl = new RoomControl();
	public void display() {
		int option = -1;
		reservationControl.expire();
		while(option!=0) {
			// display menu
			System.out.println();
			System.out.println("=== Reservations ===");
			System.out.println("1. Create a Reservation");
			System.out.println("2. Update a Reservation");
			System.out.println("3. Remove a Reservation");
			System.out.println("4. Print a Reservation");
			System.out.println("5. Print all Reservations");
			System.out.println("6. Check In");
			System.out.println("7. Check Out");
			System.out.println("0. Back to Main Menu");
			System.out.println("====================");
			
			// get option
            option = AppBoundary.inIntInRange("Option: ", 0, 7);
    		
            switch(option) {
            case 1:
        		reservationControl.createReservation(false);
        		break;
        	case 2:
        		reservationControl.updateReservation();
        		break;
        	case 3:
        		System.out.print("Please enter a Reservation ID: ");
        		String reservationId = scanner.nextLine();
        		if(validateReservation(reservationId)) {
        			Reservation reservation = dao.getItemById(reservationId);
					ArrayList<Room> rooms = reservation.getRooms();
        			for(int k=0; k<rooms.size();k++) {
        				roomControl.updateStatus(rooms.get(k).getId(),1);
        			}
        			reservationControl.removeReservation(reservation);
        			System.out.println();
        			System.out.println("Reservation is removed!");
					break;
        		}
				System.out.println();
				System.out.println("Reservation ID " + reservationId + " does not exist.");
        		break;
        	case 4:
        		System.out.print("Please enter a Reservation ID: ");
        		reservationId = scanner.nextLine();
				System.out.println();
        		if(validateReservation(reservationId)) {
        			Reservation reservation = dao.getItemById(reservationId);
        			reservationControl.printReservation(reservation);
        		}else {
					System.out.println("Reservation ID " + reservationId + " does not exist.");
        		}
        		break;
        	case 5:
        		reservationControl.printAllReservations();
        		break;
        	case 6:
        		reservationControl.checkIn();
        		break;
        	case 7:
				System.out.print("Please enter the Guest ID: ");
				String guestId = scanner.nextLine();
        		reservationControl.checkOut(guestId);
        		break;
            }
		}
	}

	// validates if the guest id exists in the database
	private boolean validateReservation (String givenId){
		Reservation reservationMatchingId = dao.getItemById(givenId);

		if (reservationMatchingId == null || givenId.isEmpty()) {
			return false;
		}
		return true;
	}
}
