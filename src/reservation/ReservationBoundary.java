package reservation;

import java.util.Scanner;

import app.AppBoundary;
import room.RoomControl;



public class ReservationBoundary {
	
	// shorten variable name
	public static Scanner scanner = AppBoundary.scanner;
	// load control
	private ReservationControl reservationControl = new ReservationControl();
	private RoomControl roomControl = new RoomControl();
	public void display() {
		int option = -1;
		reservationControl.expire();
		while(option!=0) {
			// display menu
			System.out.println("===== Reservation Menu");
			System.out.println("1. Create reservation");
			System.out.println("2. Update reservation");
			System.out.println("3. Remove reservation");
			System.out.println("4. Print reservation");
			System.out.println("5. Print all reservations");
			System.out.println("6. CheckIn");
			System.out.println("7. CheckOut");
			System.out.println("8. Walk-in");
			System.out.println("0. Back to Main Menu");
			System.out.println("=====");
			
			// get option
            option = AppBoundary.inIntInRange("Option: ", 0, 8);
    		
            switch(option) {
            case 1:
        		System.out.println("Create reservation");
        		reservationControl.createReservation(false);
        		break;
        	case 2:
        		System.out.println("Update reservation");
        		reservationControl.updateReservation();
        		break;
        	case 3:
        		System.out.println("Remove reservation");
        		System.out.println("Please enter reservation number: ");
        		String reservationId = scanner.nextLine();
        		if(validateReservation(reservationId)) {
        			Reservation reservation = reservationControl.getReservation(reservationId);
        			String[] rooms = reservation.getRooms();
        			for(int k=0; k<rooms.length;k++) {
        				roomControl.updateStatus(rooms[k],1);
        			}
        			reservationControl.removeReservation(reservation);
        			System.out.println("Reservation removed!");
        		}
        		break;
        	case 4:
        		System.out.println("Print reservation");
        		System.out.println("Please enter reservation number: ");
        		reservationId = scanner.nextLine();
        		if(validateReservation(reservationId)) {
        			Reservation reservation = reservationControl.getReservation(reservationId);
        			reservationControl.printReservation(reservation);
        		}else {
        			System.out.println("Not a valid reservation number");
        		}
        		break;
        	case 5:
        		System.out.println("Print all reservations");
        		reservationControl.printAllReservations();
        		break;
        	case 6:
        		System.out.println("CheckIn");
        		reservationControl.checkIn();
        		break;
        	case 7:
        		System.out.println("CheckOut");
        		reservationControl.checkOut();
        		break;
        	case 8:
        		System.out.println("Walk-in");
        		reservationControl.createReservation(true);
        		break;
            }
		}
	}
	
	private boolean validateReservation (String givenId){
		Reservation reservationMatchingId = reservationControl.getReservation(givenId);

		if (reservationMatchingId == null || givenId.isEmpty()) {
			return false;
		}
		return true;
	}

	public static void print(String content) {
		System.out.println(content);
	}
}
