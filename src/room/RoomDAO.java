package room;

import data.DataUtil;
import data.Hotel;
import menuitem.MenuItem;

import java.io.Serializable;
import java.util.ArrayList;

public class RoomDAO implements Serializable {

    private DataUtil dataUtil = new DataUtil();

    public ArrayList<Room> getAllItem() {
        Hotel hotel = dataUtil.readHotel();
        ArrayList<Room> rooms = hotel.getRooms();
        return rooms;
    }

    public void update(Room room, ROOM_STATUS status) {
        Hotel hotel = dataUtil.readHotel();
        ArrayList<Room> rooms = hotel.getRooms();

        for (Room rm : rooms) {
            if(rm.getId().equals(room.getId())) {
                rm.setStatus(status);
                break;
            }
        }
        dataUtil.write(hotel);
    }
}
