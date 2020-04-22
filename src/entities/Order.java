package entities;

import entities.enums.ORDER_STATUS;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {

    private static final long serialVersionUID = 5L;
    private static int counter = 0;

    // attributes
    private String id;
    private String date;
    private MenuItem menuItem;
    private String roomId;
    private int quantity;
    private ORDER_STATUS status;
    private String reservationId;
    private String remarks;

    // constructors
    public Order(MenuItem menuItem, int quantity, String remarks, String roomId, String reservationId) {
        this.id = String.valueOf(nextId());
        this.remarks = remarks;
        Date date = new Date();
        this.date = date.toString();
        this.roomId = roomId;
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.status = ORDER_STATUS.CONFIRMED;
        this.reservationId = reservationId;
    }

    // implementations
    private static int nextId() {
        counter++;
        return counter;
    }

    // interfaces
    public String getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getReservationId() {
        return reservationId;
    }

    public ORDER_STATUS getStatus() {
        return status;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setStatus(ORDER_STATUS status) {
        this.status = status;
    }

    public String toString() {
        return String.format("Order %s:\nItem:\n%s\nDate: %s\nQuantity: %s\nStatus: %s\nRoom: %s\nRemarks: %s\nReservation ID: %s\n", id, menuItem.toString(), date, quantity, status, roomId, remarks, reservationId);
    }

}
