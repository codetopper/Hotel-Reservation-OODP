package main;

import boundaries.*;
import controls.*;

import java.util.Scanner;

public class MainBoundary {

    public Scanner sc;

    public MainBoundary ()
    {
        this.sc = new Scanner(System.in);
    }

    public void display() {

        int choice = -1;
        RoomBoundary roomBoundary;
        RoomServiceBoundary roomServiceBoundary;
        GuestBoundary guestBoundary;
        ReservationBoundary reservationBoundary;
        CheckInBoundary checkInBoundary;
        CheckOutBoundary checkOutBoundary;

        while (choice != 0) {
            System.out.println();
            System.out.println("=== Main Menu ===");
            System.out.println("1.\tRoom Information");
            System.out.println("2.\tRoom Service");
            System.out.println("3.\tGuest Information");
            System.out.println("4.\tReservation");
            System.out.println("5.\tCheck In");
            System.out.println("6.\tCheck Out");
            System.out.println("7.\tReset");
            System.out.println("0.\tQuit");
            System.out.println("=================");

            choice = getIntegerOnly("Option: ", 0, 7);

            switch (choice) {
                case 1:
                    roomBoundary = new RoomBoundary();
                    roomBoundary.display();
                    break;
                case 2:
                    roomServiceBoundary = new RoomServiceBoundary();
                    roomServiceBoundary.display();
                    break;
                case 3:
                    guestBoundary = new GuestBoundary();
                    guestBoundary.display();
                    break;
                case 4:
                    reservationBoundary = new ReservationBoundary();
                    reservationBoundary.display();
                    break;
                case 5:
                    checkInBoundary = new CheckInBoundary();
                    checkInBoundary.display();
                    break;
                case 6:
                    checkOutBoundary = new CheckOutBoundary();
                    checkOutBoundary.display();
                    break;
                case 7:
                    resetEverything();
                    System.out.println("Everything is reset to default.");
                    break;
            }
        }
        System.out.println();
        System.out.println("Session has ended.");
    }

    private void resetEverything() {
        RoomController roomController = RoomController.getInstance();
        MenuItemController menuItemController = MenuItemController.getInstance();
        OrderController orderController = OrderController.getInstance();
        GuestController guestController = GuestController.getInstance();
        ReservationController reservationController = ReservationController.getInstance();
        roomController.resetRooms();
        menuItemController.resetMenuItems();
        orderController.resetOrders();
        guestController.resetGuestList();
        reservationController.resetReservations();
    }

    // utility functions
    public int inInt(String message) {
        int input;

        try {
            System.out.printf(message);
            input = Integer.parseInt(sc.nextLine());
        }
        catch (Exception e) {
            System.out.println("Only integers are accepted.");
            input = inInt(message);
        }

        return input;
    }

    public double inDouble(String message) {
        double input;

        try {
            System.out.printf(message);
            input = Double.parseDouble(sc.nextLine());
        }
        catch (Exception e) {
            System.out.println("Only doubles are accepted.");
            input = inDouble(message);
        }
        return input;
    }

    public double inDoublePos(String message) {
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

    public int getIntegerOnly(String message, int low_inclusive, int high_inclusive) {
        int choice;
        System.out.print(message);
        try
        {
            choice = sc.nextInt();
            sc.nextLine();
            if (choice<low_inclusive || choice > high_inclusive)
            {
                System.out.printf("Out of bounds. Please enter an integer from %d to %d.", low_inclusive, high_inclusive);
                System.out.println();
                choice = getIntegerOnly(message, low_inclusive, high_inclusive);
            }
        }
        catch(Exception e)
        {
            System.out.printf("Only integers are accepted. Please enter an integer from %d to %d.", low_inclusive, high_inclusive);
            System.out.println();
            sc.nextLine();
            choice = getIntegerOnly(message, low_inclusive, high_inclusive);
        }
        return choice;
    }
}
