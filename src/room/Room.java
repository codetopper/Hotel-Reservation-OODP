package room;

import java.io.Serializable;

public class Room implements Serializable {

	// attributes
    private String roomId;
    private ROOM_STATUS status;
    private ROOM_TYPE roomType;
    private BED_TYPE bedType;
    private boolean hasWifi;
    private boolean hasSeaView;
    private boolean isSmokable;
    private double price;
    public static double singlePrice = 50;
    public static double doublePrice = 80;
    public static double deluxePrice = 100;
    public static double vipPrice = 200;

    // constructors
    public Room(String id, ROOM_TYPE roomType, BED_TYPE bedType, boolean hasWifi, boolean hasSeaView, boolean isSmokable) {
        setRoomId(id);
        setStatus(ROOM_STATUS.VACANT);
        setRoomType(roomType);
        setBedType(bedType);
        setHasWifi(hasWifi);
        setHasSeaView(hasSeaView);
        setSmokable(isSmokable);
        setPrice(roomType);
    }
    
    // interfaces
    public boolean isAvailable() {
        if (status == ROOM_STATUS.VACANT) {
            return true;
        }
        return false;
    }

    public String roomDetails() {
        String description = "Room ID: " + getId() + "\n" +
                "Room Status: " + getStatus() + "\n" +
                "Room Type: " + getRoomType() + "\n" +
                "Bed Type: " + getBedType() + "\n" +
                "Has Wifi: " + hasWifi + "\n" +
                "Has View: " + hasSeaView + "\n" +
                "Smoking Allowed: " + isSmokable;
        return description;
    }
    
    // getters
    public String getId() {
        return roomId;
    }
    public ROOM_STATUS getStatus() {
        return status;
    }
    public ROOM_TYPE getRoomType() {
        return roomType;
    }
    public BED_TYPE getBedType() {
        return bedType;
    }
    public boolean isHasWifi() {
        return hasWifi;
    }
    public boolean isHasSeaView() {
        return hasSeaView;
    }
    public boolean isSmokable() {
        return isSmokable;
    }
    public double getPrice() { return price; }
    
    // setters
    public void setStatus(ROOM_STATUS status) {
        this.status = status;
    }
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    public void setRoomType(ROOM_TYPE roomType) {
        this.roomType = roomType;
    }
    public void setBedType(BED_TYPE bedType) {
        this.bedType = bedType;
    }
    public void setHasWifi(boolean hasWifi) {
        this.hasWifi = hasWifi;
    }
    public void setHasSeaView(boolean hasSeaView) {
        this.hasSeaView = hasSeaView;
    }
    public void setSmokable(boolean smokable) {
        isSmokable = smokable;
    }
    private void setPrice(ROOM_TYPE type) {
        switch(type) {
            case SINGLE:
                price = singlePrice;
                break;
            case DOUBLE:
                price = doublePrice;
                break;
            case DELUXE:
                price = deluxePrice;
                break;
            case VIP_SUITE:
                price = vipPrice;
                break;
        }
    }

}