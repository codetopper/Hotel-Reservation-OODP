package Hotel;

import java.io.Serializable;

enum ROOM_STATUS {VACANT, OCCUPIED, RESERVED, UNDER_MAINTENANCE};
enum ROOM_TYPE {SINGLE, DOUBLE,DELUXE, VIP_SUITE}
enum BED_TYPE {SINGLE, DOUBLE, MASTER};

public class Room implements Serializable {

    private String roomId;
    private ROOM_STATUS status;
    private ROOM_TYPE roomType;
    private BED_TYPE bedType;
    private boolean hasWifi;
    private boolean hasView;
    private boolean isSmokable;

    public Room(String id, ROOM_TYPE roomType, BED_TYPE bedType, boolean hasWifi, boolean hasView, boolean isSmokable) {
        setRoomId(id);
        setStatus(ROOM_STATUS.VACANT);
        setRoomType(roomType);
        setBedType(bedType);
        setHasWifi(hasWifi);
        setHasView(hasView);
        setSmokable(isSmokable);
    }


    public String getRoomId() {
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
    public boolean isHasView() {
        return hasView;
    }
    public boolean isSmokable() {
        return isSmokable;
    }

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
    public void setHasView(boolean hasView) {
        this.hasView = hasView;
    }
    public void setSmokable(boolean smokable) {
        isSmokable = smokable;
    }
}
