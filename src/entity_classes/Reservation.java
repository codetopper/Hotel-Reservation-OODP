package entity_classes;

import enums.RESERVATION_STATUS;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

//import Hotel.Reservation.reservationStatus;

public class Reservation implements Serializable {

    // attributes
    private int numAdults;
    private int numChildren;
    private int billing;
    private String id;
    private String[] rooms;
    private RESERVATION_STATUS status;
    private Date checkInDate;
    private Date checkOutDate;
    private Guest guest;

    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");

    // constructors
    public Reservation(Guest guest, String id, int numAdults, int numChildren, RESERVATION_STATUS status, int billing, Date checkInDate, Date checkOutDate, String[] rooms) {
        this.guest = guest;
        this.id = id;
        this.numAdults = numAdults;
        this.numChildren = numChildren;
        this.status = status;
        this.billing = billing;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.rooms = rooms;
    }

    public Reservation() {}

    // getters
    public Guest getGuest() {
        return guest;
    }
    public String getId() {
        return id;
    }

    public int getNumAdults() {
        return numAdults;
    }

    public int getNumChildren() {
        return numChildren;
    }

    public RESERVATION_STATUS getStatus() {
        return status;
    }

    public int getBilling() {
        return billing;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public String[] getRooms() {
        return rooms;
    }

    // setters
    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public void setId(String id) {
        this.id=id;
    }

    public void setNumAdults(int numAdults) {
        this.numAdults = numAdults;
    }

    public void setNumChildren(int numChildren) {
        this.numChildren = numChildren;
    }

    public void setStatus(RESERVATION_STATUS status) {
        this.status = status;
    }

    public void setBilling(int billing) {
        this.billing = billing;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public void setRooms(String[] rooms) {
        this.rooms = rooms;
    }
}
