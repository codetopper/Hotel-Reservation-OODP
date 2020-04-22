package main;

import controls.*;
import data.DataController;

public class Main {

    public static void main(String[] args) {
        //Load data
        DataController dataController = new DataController();
        //Load data

        //main application
        MainBoundary mainBoundary = new MainBoundary();
        mainBoundary.display();
        //main application

        //Save data
        dataController.serializeObject(RoomController.getInstance(), "roomController.ser");
        dataController.serializeObject(MenuItemController.getInstance(), "menuItemController.ser");
        dataController.serializeObject(OrderController.getInstance(), "orderController.ser");
        dataController.serializeObject(GuestController.getInstance(), "guestController.ser");
        dataController.serializeObject(ReservationController.getInstance(), "reservationController.ser");
        //Save data
    }
}
