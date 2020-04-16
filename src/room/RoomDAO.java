package room;

// java apis
import java.util.ArrayList;
// original packages
import data.DataUtil;
import data.Hotel;

public class RoomDAO {

	// load data access
    private DataUtil dataUtil = new DataUtil();
    
    // constructors
    public RoomDAO() {
    	if (dataUtil.readHotel().getRooms().size() == 0) {
    		prepareDefaultRooms();
    	}
    }
    
    // implementations
	private void prepareDefaultRooms() {
		Hotel hotel = dataUtil.readHotel();
		ArrayList<Room> rooms = new ArrayList<>();
		
		for (int i = 1; i <= 1; i++) {
			rooms.add(new Room("0" + (700 + i), ROOM_TYPE.VIP_SUITE, BED_TYPE.MASTER, true, true, true));
		}
		for (int i = 1; i <= 7; i++) {
			rooms.add(new Room("0" + (600 + i), ROOM_TYPE.DELUXE, BED_TYPE.MASTER, true, true, true));
		}
		for (int i = 1; i <= 10; i++) {
			rooms.add(new Room("0" + (500 + i), ROOM_TYPE.DOUBLE, BED_TYPE.DOUBLE, true, true, false));
		}
		for (int i = 1; i <= 10; i++) {
			rooms.add(new Room("0" + (300 + i), ROOM_TYPE.DOUBLE, BED_TYPE.DOUBLE, true, false, false));
		}
		for (int i = 1; i <= 10; i++) {
			rooms.add(new Room("0" + (400 + i), ROOM_TYPE.SINGLE, BED_TYPE.SINGLE, true, true, false));
		}
		for (int i = 1; i <= 10; i++) {
			rooms.add(new Room("0" + (200 + i), ROOM_TYPE.SINGLE, BED_TYPE.SINGLE, true, false, false));
		}
		
		hotel.setRooms(rooms);
		dataUtil.write(hotel);
	}

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
