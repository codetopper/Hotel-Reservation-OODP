package room;

// java apis
import java.util.ArrayList;
// original packages
import data.DataUtil;
import data.Hotel;

public class RoomDAO {

	// load data access
    private DataUtil dataUtil = new DataUtil();
    
    // implementations
    private Room getItemById(ArrayList<Room> rooms, String id) { // for internal use
    	Room roomMatchingName = null;
    	
    	for (Room room : rooms) {
    		if (room.getId().equals(id)) {
    			roomMatchingName = room;
    			break;
    		}
    	}
    	
    	return roomMatchingName;
    }

    // interfaces
    public ArrayList<Room> getAllItem() {
        Hotel hotel = dataUtil.readHotel();
        ArrayList<Room> rooms = hotel.getRooms();
        
        return rooms;
    }
 
    public Room getItemById(String id) { // for external use
    	ArrayList<Room> rooms = getAllItem();
    	Room roomMatchingName = null;
    	
    	for (Room room : rooms) {
    		if (room.getId().equals(id)) {
    			roomMatchingName = room;
    			break;
    		}
    	}
    	
    	return roomMatchingName;
    }
    
    public void replaceRooms(ArrayList<Room> rooms) { // for load default
    	Hotel hotel = dataUtil.readHotel();
    	hotel.setRooms(rooms);
    	
    	dataUtil.write(hotel);
    }

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
