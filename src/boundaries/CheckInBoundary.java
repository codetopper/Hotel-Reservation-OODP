package boundaries;

import controls.ReservationController;
import controls.RoomController;
import entities.Reservation;
import entities.Room;
import main.MainBoundary;

import java.util.ArrayList;
import java.util.Scanner;

public class CheckInBoundary {

    ReservationController reservationController;
    MainBoundary mainBoundary;
    Scanner sc;

    public CheckInBoundary()
    {
        reservationController = ReservationController.getInstance();
        mainBoundary = new MainBoundary();
        sc = new Scanner(System.in);
    }

    public void display() {
        reservationController.expire();
        reservationController.checkIn();
    }
}
