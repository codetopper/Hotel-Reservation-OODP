// for reference

package room;

import java.io.Serializable;

enum ROOM_STATUS {VACANT, OCCUPIED, RESERVED, UNDER_MAINTENANCE};

public class Room implements Serializable {

	// attributes
    private String id;
    private ROOM_STATUS status;
    private ROOM_TYPE roomType;
    private BED_TYPE bedType;
    private boolean hasWifi;
    private boolean hasView;
    private boolean isSmokable;

    // constructors
    public Room(String id, ROOM_TYPE roomType, BED_TYPE bedType, boolean hasWifi, boolean hasView, boolean isSmokable) {
        this.id = id;
        this.status = ROOM_STATUS.VACANT;
        this.roomType = roomType;
        this.bedType = bedType;
        this.hasWifi = hasWifi;
        this.hasView = hasView;
        this.isSmokable = isSmokable;
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

}
