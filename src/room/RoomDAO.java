package room;

import data.DataUtil;
import menuitem.MenuItem;

import java.util.ArrayList;

public class RoomDAO {
    String filename = "room.ser";

    public ArrayList<Room> getAllItem() {
        ArrayList<Room> rooms = (ArrayList<Room>) DataUtil.deserializeObject(filename);

        if (rooms == null) {
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
            DataUtil.serializeObject(rooms, filename);
        }

        return rooms;
    }

    public void update(Room room, ROOM_STATUS status) {
        ArrayList<Room> rooms = getAllItem();

        for (Room rm : rooms) {
            if(rm.getId().equals(room.getId())) {
                rm.setStatus(status);
                break;
            }
        }
        DataUtil.serializeObject(rooms, filename);
        return;
    }
}
