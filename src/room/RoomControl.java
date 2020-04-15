package room;

import java.io.Serializable;
import java.util.ArrayList;

import app.AppBoundary;


public class RoomControl implements Serializable {
	
	// attributes
	private ArrayList<Room> rooms;
	
	// constructors
	public RoomControl() {
		rooms = new ArrayList<>();
		
		// create default rooms
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
	
	// interfaces
	public void updateRoom(String id, int status) {
		Room roomMatchingId = null;
		
		for (Room room : rooms) {
			if(room.getId().equals(id)) {
				roomMatchingId = room;
				break;
			}
		}
		
		if (roomMatchingId == null) {
			System.out.println("Cannot find room matching id.");
			return;
		}
		
		switch(status) {
        case 1:
            roomMatchingId.setStatus(ROOM_STATUS.VACANT);
            break;
        case 2:
        	roomMatchingId.setStatus(ROOM_STATUS.OCCUPIED);
            break;
        case 3:
        	roomMatchingId.setStatus(ROOM_STATUS.RESERVED);
            break;
        case 4:
        	roomMatchingId.setStatus(ROOM_STATUS.UNDER_MAINTENANCE);
            break;
		}
    }
    
    public String vacantToString(){
        int singleCount = 0, doubleCount = 0, deluxeCount = 0, vipCount = 0;
        String vacantRooms = "Vacant rooms:";
        String vacantSingle = "";
        String vacantDouble = "";
        String vacantDeluxe = "";
        String vacantVIP = "";
        for (Room room: rooms) {
            if(room.isAvailable())
            {
                switch (room.getRoomType()) {
                    case SINGLE:
                        vacantSingle = vacantSingle + room.getId() + ", ";
                        singleCount++;
                        break;
                    case DOUBLE:
                        vacantDouble = vacantDouble + room.getId() + ", ";
                        doubleCount++;
                        break;
                    case DELUXE:
                        vacantDeluxe = vacantDeluxe + room.getId() + ", ";
                        deluxeCount++;
                        break;
                    case VIP_SUITE:
                        vacantVIP = vacantVIP + room.getId() + ", ";
                        vipCount++;
                        break;
                }
            }
        }
        return vacantRooms + "\nSingle: " + singleCount + " out of 20\n\t" + vacantSingle +
                "\nDouble: " + doubleCount + " out of 20\n\t" + vacantDouble +
                "\nDeluxe: " + deluxeCount + " out of 7\n\t" + vacantDeluxe +
                "\nVIP: " + vipCount + " out of 1\n\t" + vacantVIP;
    }
    
    
    // What is this doing?
    public String byStatusToString() {
        String vacant = "";
        String occupied = "";
        String reserved = "";
        String underMaintenance = "";
        for (Room room: rooms) {
            switch (room.getStatus()) {
                case VACANT:
                    vacant += room.getId() + ", ";
                    break;
                case OCCUPIED:
                    occupied += room.getId() + ", ";
                    break;
                case RESERVED:
                    reserved += room.getId() + ", ";
                    break;
                case UNDER_MAINTENANCE:
                    underMaintenance += room.getId() + ", ";
                    break;
            }
        }
        return "Vacant:\n\t" + vacant + "\nOccupied:\n\t" + occupied + "\nReserved\n\t" + reserved + "\nUnder Maintenance\n\t" + underMaintenance;
    }

    //to be transfered to price
//    public String getRoomDetails(String id) {
//        for (Hotel.Room room : rooms) {
//            if (room.getRoomId().equals(id)) {
//                String description = "Hotel.Room ID:\t" + room.getRoomId() + "\n" +
//                        "Hotel.Room Status:\t" + room.getStatus() + "\n" +
//                        "Hotel.Room Type:\t" + room.getRoomType() + "\n" +
//                        "Bed Type:\t" + room.getBedType() + "\n" +
//                        "Has Wifi:\t" + room.isHasWifi() + "\n" +
//                        "Has View:\t" + room.isHasView() + "\n" +
//                        "Smoking Allowed:\t" + room.isSmokable();
//                return description;
//            }
//        }
//        return "Hotel.Room does not exist.";
//    }

}
