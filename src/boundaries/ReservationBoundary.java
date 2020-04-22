package boundaries;

import controls.*;
import entities.Reservation;
import entities.Room;
import main.MainBoundary;

import java.util.ArrayList;
import java.util.Scanner;

public class ReservationBoundary {

    ReservationController reservationController;
    RoomController roomController;
    MainBoundary mainBoundary;
    Scanner sc;

    public ReservationBoundary()
    {
        reservationController = ReservationController.getInstance();
        roomController = RoomController.getInstance();
        mainBoundary = new MainBoundary();
        sc = new Scanner(System.in);
    }

    public void display() {
        int option = -1;
        reservationController.expire();
        while(option!=0) {
            // display menu
            System.out.println();
            System.out.println("=== Reservation Menu ===");
            System.out.println("1. Create reservation");
            System.out.println("2. Update reservation");
            System.out.println("3. Remove reservation");
            System.out.println("4. Print reservation");
            System.out.println("5. Print all reservations");
            System.out.println("0. Back to Main Menu");
            System.out.println("========================");

            // get option
            option = mainBoundary.getIntegerOnly("Option: ", 0, 5);

            switch(option) {
                case 1:
                    System.out.println("Create reservation");
                    reservationController.createReservation(false);
                    break;
                case 2:
                    System.out.println();
                    System.out.println("Update reservation");
                    reservationController.updateReservation();
                    break;
                case 3:
                    System.out.println("Remove reservation");
                    System.out.print("Please enter Reservation ID: ");
                    String reservationId = sc.nextLine();
                    System.out.println();
                    if(validateReservation(reservationId)) {
                        Reservation reservation = reservationController.getReservation(reservationId);
                        ArrayList<Room> rooms = reservation.getRooms();
                        for(int k=0; k<rooms.size();k++) {
                            roomController.updateStatus(rooms.get(k).getRoomId(),1);
                        }
                        reservationController.removeReservation(reservation);
                        System.out.println("Reservation removed!");
                        break;
                    }
                    System.out.println(reservationId + " is not a valid Reservation ID.");
                    break;
                case 4:
                    System.out.println("Print reservation");
                    System.out.print("Please enter Reservation ID: ");
                    reservationId = sc.nextLine();
                    System.out.println();
                    if(validateReservation(reservationId)) {
                        Reservation reservation = reservationController.getReservation(reservationId);
                        reservationController.printReservation(reservation);
                    }else {
                        System.out.println(reservationId + " is not a valid Reservation ID.");
                    }
                    break;
                case 5:
                    System.out.println();
                    System.out.println("Print all reservations");
                    reservationController.printAllReservations();
                    break;
            }
        }
    }

    private boolean validateReservation (String givenId){
        Reservation reservationMatchingId = reservationController.getReservation(givenId);

        if (reservationMatchingId == null || givenId.isEmpty()) {
            return false;
        }
        return true;
    }

}
