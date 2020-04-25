package room;

// java apis
import java.util.ArrayList;
// original packages
import data.DataUtil;
import data.Hotel;

public class RoomDAO {

	// load data access
    private DataUtil dataUtil = new DataUtil();

    private Room getItemById(ArrayList<Room> rooms, String id) { // for internal use
    	Room roomMatchingId = null;
    	
    	for (Room room : rooms) {
    		if (room.getId().equals(id)) {
    			roomMatchingId = room;
    			break;
    		}
    	}
    	
    	return roomMatchingId;
    }

    // interfaces

    //get the full list of rooms
    public ArrayList<Room> getAllItem() {
        Hotel hotel = dataUtil.readHotel();
        ArrayList<Room> rooms = hotel.getRooms();
        
        return rooms;
    }

    //get the room using room id
    public Room getItemById(String id) { // for external use
    	ArrayList<Room> rooms = getAllItem();
    	Room roomMatchingId = null;
    	
    	for (Room room : rooms) {
    		if (room.getId().equals(id)) {
    			roomMatchingId = room;
    			break;
    		}
    	}
    	
    	return roomMatchingId;
    }

    // update the room status of the given room id
    public void update(Room roomInput) {
        Hotel hotel = dataUtil.readHotel();
        ArrayList<Room> rooms = hotel.getRooms();
        Room roomMatchingId = getItemById(rooms, roomInput.getId());

        if (roomMatchingId == null) {
        	System.out.println("Cannot find room matching id.");
        	return;
        }
        
        // currently only updating status
        roomMatchingId.setStatus(roomInput.getStatus());
        dataUtil.write(hotel);
    }
}
