package reservation;

import java.util.Scanner;

import app.AppBoundary;
import room.RoomControl;



public class ReservationBoundary {
	
	// shorten variable name
	Scanner scanner = AppBoundary.scanner;
	// load control
	private ReservationControl reservationControl = new ReservationControl();
	private RoomControl roomControl = new RoomControl();
	Scanner sc = new Scanner(System.in);
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
			System.out.println("0. Back to Main Menu");
			System.out.println("=====");
			
			// get option
            option = AppBoundary.inIntInRange("Option: ", 0, 5);
    		
            switch(option) {
            case 1:
        		System.out.println("Create reservation");
        		reservationControl.createReservation(false);
        	case 2:
        		System.out.println("Update reservation");
        		reservationControl.updateReservation();
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
        	case 5:
        		System.out.println("Print all reservations");
        		reservationControl.printAllReservations();
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
}
