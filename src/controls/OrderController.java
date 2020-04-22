package controls;

import data.DataController;
import entities.MenuItem;
import entities.Order;
import entities.enums.ORDER_STATUS;
import entities.enums.ROOM_STATUS;
import main.MainBoundary;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderController implements Serializable {

    private static final long serialVersionUID = 6L;
    public ArrayList<Order> orders;
    MenuItemController menuItemController;
    RoomController roomController;
    ReservationController reservationController;
    private static OrderController thisInstance = null;

    public static OrderController getInstance() {

        if (thisInstance==null) // not already loaded
        {
            DataController dataController = new DataController();
            thisInstance = dataController.loadOrderController();

            if (thisInstance==null)
                thisInstance = new OrderController(); // loading failure; resets controller
        }
        return thisInstance;
    }

    private OrderController() {
        resetOrders();
    }

    public void resetOrders() {
        orders = new ArrayList<>();
    }

    public void create(String menuItemName, int quantity, String remarks, String roomId) {
        menuItemController = MenuItemController.getInstance();
        roomController = RoomController.getInstance();
        reservationController = ReservationController.getInstance();
        MenuItem menuItemMatchingName = menuItemController.getItemByName(menuItemName);

        if (menuItemMatchingName == null) {
            System.out.println("Cannot find menu item matching name.");
            return;
        }

        String reservationId = reservationController.getIdByRoom(roomId);

        if (roomController.getItemById(roomId).getStatus() == ROOM_STATUS.OCCUPIED) {
            Order order = new Order(menuItemMatchingName, quantity, remarks, roomId, reservationId);
            orders.add(order);
        }
        else {
            System.out.println("Room is not occupied!");
        }
    }

    public void update(String id, int choice) {
        ORDER_STATUS status = ORDER_STATUS.CONFIRMED;
        Order orderMatchingId = getItemById(id);

        if (orderMatchingId == null) {
            System.out.println("Cannot find order matching id.");
            return;
        }

        switch (choice) {
            case 1:
                status = ORDER_STATUS.CONFIRMED;
                break;
            case 2:
                status = ORDER_STATUS.PREPARING;
                break;
            case 3:
                status = ORDER_STATUS.DELIVERED;
                break;
            case 4:
                status = ORDER_STATUS.CANCELLED;
                break;
        }
        orderMatchingId.setStatus(status);
    }

    public void displayList() {
        for (Order order : orders) {
            System.out.println(order.toString());
        }
        System.out.println("There are " + orders.size() + " orders.");
    }

    public Order getItemById(String id) {
        Order orderMatchingId = null;

        for (Order order : orders) {
            if (order.getId().equals(id)) {
                orderMatchingId = order;
                break;
            }
        }

        return orderMatchingId;
    }

}
