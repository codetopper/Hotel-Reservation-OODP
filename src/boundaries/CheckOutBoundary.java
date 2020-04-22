package boundaries;

import controls.ReservationController;
import entities.Reservation;
import main.MainBoundary;

import java.util.Scanner;

public class CheckOutBoundary {
    ReservationController reservationController;
    MainBoundary mainBoundary;
    Scanner sc;

    public CheckOutBoundary()
    {
        reservationController = ReservationController.getInstance();
        mainBoundary = new MainBoundary();
        sc = new Scanner(System.in);
    }

    public void display() {
        reservationController.expire();
        int option = -1;
        while(option!=0) {
            // display menu
            System.out.println();
            System.out.println("=== Check Out Menu ===");
            System.out.println("1. Check Out");
            System.out.println("0. Back to Main Menu");
            System.out.println("======================");

            // get option
            option = mainBoundary.getIntegerOnly("Option: ", 0, 5);
            switch (option) {
                case 1:
                    System.out.print("Enter Guest ID: ");
                    String id = sc.nextLine();
                    reservationController.checkOut(id);
                    break;
            }
        }
    }
}
