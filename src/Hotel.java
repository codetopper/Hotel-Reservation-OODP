import java.io.Serializable;
import java.util.ArrayList;

public class Hotel implements Serializable {

    private ArrayList<Room> rooms;

    Hotel() {
        rooms = new ArrayList<>();
        for(int i =1; i<=1; i++) {
            rooms.add(new Room("0"+(700+i), ROOM_TYPE.VIP_SUITE, BED_TYPE.MASTER, true, true, true));
        }
        for(int i =1; i<=7; i++) {
            rooms.add(new Room("0"+(600+i), ROOM_TYPE.DELUXE, BED_TYPE.MASTER, true, true, true));
        }
        for(int i =1; i<=10; i++) {
            rooms.add(new Room("0"+(500+i), ROOM_TYPE.DOUBLE, BED_TYPE.DOUBLE, true, true, false));
        }
        for(int i =1; i<=10; i++) {
            rooms.add(new Room("0"+(300+i), ROOM_TYPE.DOUBLE, BED_TYPE.DOUBLE, true, false, false));
        }
        for(int i =1; i<=10; i++) {
            rooms.add(new Room("0"+(400+i), ROOM_TYPE.SINGLE, BED_TYPE.SINGLE, true, true, false));
        }
        for(int i =1; i<=10; i++) {
            rooms.add(new Room("0"+(200+i), ROOM_TYPE.SINGLE, BED_TYPE.SINGLE, true, false, false));
        }
    }

    public void updateRoom(String id, int choice) {
        for(Room room: rooms) {
            if(room.getRoomId().equals(id)) {
                switch(choice) {
                    case 1:
                        room.setStatus(ROOM_STATUS.VACANT);
                        break;
                    case 2:
                        room.setStatus(ROOM_STATUS.OCCUPIED);
                        break;
                    case 3:
                        room.setStatus(ROOM_STATUS.RESERVED);
                        break;
                    case 4:
                        room.setStatus(ROOM_STATUS.UNDER_MAINTENANCE);
                        break;
                }
                break;
            }
        }
    }

    public boolean isAvailable(String id) {
        for (Room room : rooms) {
            if (room.getRoomId().equals(id)) {
                if (room.getStatus() == ROOM_STATUS.VACANT) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    //to be transfered to price
//    public String getRoomDetails(String id) {
//        for (Room room : rooms) {
//            if (room.getRoomId().equals(id)) {
//                String description = "Room ID:\t" + room.getRoomId() + "\n" +
//                        "Room Status:\t" + room.getStatus() + "\n" +
//                        "Room Type:\t" + room.getRoomType() + "\n" +
//                        "Bed Type:\t" + room.getBedType() + "\n" +
//                        "Has Wifi:\t" + room.isHasWifi() + "\n" +
//                        "Has View:\t" + room.isHasView() + "\n" +
//                        "Smoking Allowed:\t" + room.isSmokable();
//                return description;
//            }
//        }
//        return "Room does not exist.";
//    }
}
