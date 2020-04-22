package data;

import controls.*;


import java.io.*;

public class DataController{

    public RoomController loadRoomController() {
        RoomController roomController = (RoomController) deserializeObject("roomController.ser");
        return roomController;
    }

    public MenuItemController loadMenuItemController() {
        MenuItemController menuItemController = (MenuItemController) deserializeObject("menuItemController.ser");
        return menuItemController;
    }

    public OrderController loadOrderController() {
        OrderController orderController = (OrderController) deserializeObject("orderController.ser");
        return orderController;
    }

    public GuestController loadGuestController() {
        GuestController guestController = (GuestController) deserializeObject("guestController.ser");
        return guestController;
    }

    public ReservationController loadReservationController() {
        ReservationController reservationController = (ReservationController) deserializeObject("reservationController.ser");
        return reservationController;
    }

    public void serializeObject(Object objectToBeSaved, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(objectToBeSaved);
            System.out.println();
            System.out.println("Successfully Saved: " + filename);
        }
        catch (Exception ex) {
            System.out.println();
            ex.printStackTrace();
            System.out.println("Failure to Save: " + filename);
        }
    }

    public Object deserializeObject(String filename) {
        Object returnObj;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            returnObj = ois.readObject();
            System.out.println("Successfully Loaded: " + filename);
            return returnObj;
        }
        catch (Exception ex) {
            System.out.println();
            System.out.println("Failure to Load: " + filename);
            return null;
        }
    }
}
