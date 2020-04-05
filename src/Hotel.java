import java.util.ArrayList;

public class Hotel {

    private ArrayList<Room> rooms;

    public Hotel(int VIP_QUANTITY, int DELUXE_QUANTITY, int DOUBLE_QUANTITY, int SINGLE_QUANTITY) {
        rooms = new ArrayList<>();
        for(int i =0; i<VIP_QUANTITY; i++) {
            rooms.add(new Room(Integer.toString(i), ROOM_TYPE.VIP_SUITE, BED_TYPE.MASTER, true, true, true));
        }
        for(int i =0; i<DELUXE_QUANTITY; i++) {
            rooms.add(new Room(Integer.toString(i), ROOM_TYPE.DELUXE, BED_TYPE.MASTER, true, false, true));
        }
        for(int i =0; i<DOUBLE_QUANTITY; i++) {
            rooms.add(new Room(Integer.toString(i), ROOM_TYPE.DOUBLE, BED_TYPE.DOUBLE, true, false, false));
        }
        for(int i =0; i<SINGLE_QUANTITY; i++) {
            rooms.add(new Room(Integer.toString(i), ROOM_TYPE.SINGLE, BED_TYPE.SINGLE, true, false, false));
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

    public String getRoomDetails(String id) {
        for (Room room : rooms) {
            if (room.getRoomId().equals(id)) {
                String description = "Room ID:\t" + room.getRoomId() + "\n" +
                        "Room Status:\t" + room.getStatus() + "\n" +
                        "Room Type:\t" + room.getRoomType() + "\n" +
                        "Bed Type:\t" + room.getBedType() + "\n" +
                        "Has Wifi:\t" + room.isHasWifi() + "\n" +
                        "Has View:\t" + room.isHasView() + "\n" +
                        "Smoking Allowed:\t" + room.isSmokable() + "\n";
                return description;
            }
        }
        return "Room does not exist.";
    }
}
