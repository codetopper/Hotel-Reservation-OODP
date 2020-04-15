package room;

import java.io.Serializable;
import java.util.ArrayList;

import app.AppBoundary;
import menuitem.MenuItem;
import menuitem.MenuItemDAO;


public class RoomControl implements Serializable {
	
	// attributes
	private ArrayList<Room> rooms;
    private RoomDAO dao = new RoomDAO();

    public String checkRoomAvailability(int option, String info) {
        if(option == 1) {
            ArrayList<Room> rooms = dao.getAllItem();
            for (Room room : rooms) {
                if (room.getId().equals(info)){
                    return "The room is " + room.getStatus();
                }
            }
            return "The room does not exist.";
        }
        else if(option == 2) {
            return "The customer does not have a room yet.";
        }
        return "Invalid input.";
    }

    // interfaces
	public String updateRoomStatus(String id, int status) {
		Room roomMatchingId = null;
        ArrayList<Room> rooms = dao.getAllItem();

		for (Room room : rooms) {
			if(room.getId().equals(id)) {
				roomMatchingId = room;
				break;
			}
		}
		
		if (roomMatchingId == null) {
			return "Cannot find matching Room ID.";
		}
		
		switch(status) {
            case 1:
                dao.update(roomMatchingId, ROOM_STATUS.VACANT);
                return roomMatchingId.getId() + " new status \"Vacant\" is updated.";
            case 2:
                dao.update(roomMatchingId, ROOM_STATUS.OCCUPIED);
                return roomMatchingId.getId() + " new status \"Occupied\" is updated.";
            case 3:
                dao.update(roomMatchingId, ROOM_STATUS.RESERVED);
                return roomMatchingId.getId() + " new status \"Reserved\" is updated.";
            case 4:
                dao.update(roomMatchingId, ROOM_STATUS.UNDER_MAINTENANCE);
                return roomMatchingId.getId() + " new status \"Under Maintenance\" is updated.";
            default:
                break;
		}
		return "Nothing is updated";
    }
    
    public String findVacantByRoomType(){
        ArrayList<Room> rooms = dao.getAllItem();
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
                    default:
                        break;
                }
            }
        }
        return vacantRooms + "\nSingle: " + singleCount + " out of 20\n\t" + vacantSingle +
                "\nDouble: " + doubleCount + " out of 20\n\t" + vacantDouble +
                "\nDeluxe: " + deluxeCount + " out of 7\n\t" + vacantDeluxe +
                "\nVIP: " + vipCount + " out of 1\n\t" + vacantVIP;
    }
    
    

    public String sortByStatus() {
        ArrayList<Room> rooms = dao.getAllItem();
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
                default:
                    break;
            }
        }
        return "Vacant:\n\t" + vacant + "\nOccupied:\n\t" + occupied + "\nReserved:\n\t" + reserved + "\nUnder Maintenance:\n\t" + underMaintenance;
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
