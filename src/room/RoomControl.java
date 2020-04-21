package room;

import java.util.ArrayList;

public class RoomControl {

	// attributes
	private RoomDAO dao = new RoomDAO();

	// interfaces
	public String getAvailabilityByRoomId(String id) {
		Room roomMatchingId = dao.getItemById(id);
		
		if (roomMatchingId == null) {
			return "Cannot find room matching id.";
		}
		
		return String.format("Room is: %s\n", roomMatchingId.getStatus());
	}
	
	public String getAvailabilityByGuestName(String name) {
		// to be implemented i guess?
		return "";
	}

	public String updateStatus(String id, int choice) {
		ROOM_STATUS status = ROOM_STATUS.VACANT;
		Room roomMatchingId = dao.getItemById(id);
		
		if (roomMatchingId == null) {
			return "Cannot find room matching id.";

		}
		
		switch (choice) {
			case 1:
				status = ROOM_STATUS.VACANT;
				break;
			case 2:
				status = ROOM_STATUS.OCCUPIED;
				break;
			case 3:
				status = ROOM_STATUS.RESERVED;
				break;
			case 4:
				status = ROOM_STATUS.UNDER_MAINTENANCE;
				break;
		}
		
		roomMatchingId.setStatus(status);
		dao.update(roomMatchingId);
		return id + " is now " + status.toString();
	}

	public String getAvailabilityByRoomType() {
		ArrayList<Room> rooms = dao.getAllItem();
		int singleCount = 0, doubleCount = 0, deluxeCount = 0, vipCount = 0;
		String vacantRooms = "Vacant rooms:\n";
		String vacantSingle = "";
		String vacantDouble = "";
		String vacantDeluxe = "";
		String vacantVIP = "";
		for (Room room : rooms) {
			if (room.isAvailable()) {
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
		// the number of rooms should not be hard coded here
		// because the number of rooms should change according to the functional requirements
		return vacantRooms + "\nSingle: " + singleCount + " out of 20\n\t" + removeLastComma(vacantSingle) + "\nDouble: " + doubleCount
				+ " out of 20\n\t" + removeLastComma(vacantDouble) + "\nDeluxe: " + deluxeCount + " out of 7\n\t" + removeLastComma(vacantDeluxe)
				+ "\nVIP: " + vipCount + " out of 1\n\t" + removeLastComma(vacantVIP);
	}

	public String getByStatus() {
		ArrayList<Room> rooms = dao.getAllItem();
		String vacant = "";
		String occupied = "";
		String reserved = "";
		String underMaintenance = "";
		for (Room room : rooms) {
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
		return "Vacant:\n\t" + removeLastComma(vacant) + "\nOccupied:\n\t" + removeLastComma(occupied) + "\nReserved:\n\t" + removeLastComma(reserved)
				+ "\nUnder Maintenance:\n\t" + removeLastComma(underMaintenance);
	}

	public String removeLastComma(String str) {
		if (str != null && !str.isBlank() && !str.isEmpty()) {
			str = str.substring(0, str.length() - 2);
		}
		return str;
	}

	public void resetRooms() {
		dao.prepareDefaultRooms();
	}

	// to be transfered to price
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
