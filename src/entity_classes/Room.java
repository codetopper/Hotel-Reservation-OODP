package entity_classes;

import enums.BED_TYPE;
import enums.ROOM_STATUS;
import enums.ROOM_TYPE;

import java.io.Serializable;

;

public class Room implements Serializable {

	// attributes
    private String id;
    private ROOM_STATUS status;
    private ROOM_TYPE roomType;
    private BED_TYPE bedType;
    private boolean hasWifi;
    private boolean hasView;
    private boolean isSmokable;
    private double price;

    // constructors
    public Room(String id, ROOM_TYPE roomType, BED_TYPE bedType, boolean hasWifi, boolean hasView, boolean isSmokable) {
        this.id = id;
        this.status = ROOM_STATUS.VACANT;
        this.roomType = roomType;
        this.bedType = bedType;
        this.hasWifi = hasWifi;
        this.hasView = hasView;
        this.isSmokable = isSmokable;
        setPrice(roomType);
    }
    
    // interfaces
    public boolean isAvailable() {
    	if (status == ROOM_STATUS.VACANT) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    // getters
    public String getId() {
    	return this.id;
    }
    
    public ROOM_TYPE getRoomType() {
    	return this.roomType;
    }
    
    // setters
    public void setStatus(ROOM_STATUS status) {
    	this.status = status;
    }

    public ROOM_STATUS getStatus() {
        return status;
    }

    private void setPrice(ROOM_TYPE type) {
        switch(type) {
            case SINGLE:
                price = 50;
                break;
            case DOUBLE:
                price = 80;
                break;
            case DELUXE:
                price = 100;
                break;
            case VIP_SUITE:
                price = 200;
                break;
        }
    }

}
