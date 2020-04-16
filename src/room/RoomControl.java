package room;

import java.util.ArrayList;

public class RoomControl {

	// attributes
	private RoomDAO dao = new RoomDAO();

	// interfaces
	public void prepareDefaultRooms() {
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
		
		dao.replaceRooms(rooms);
	}
	
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

	public void updateStatus(String id, int option) {
		ROOM_STATUS status = ROOM_STATUS.VACANT;
		Room roomMatchingId = dao.getItemById(id);
		
		if (roomMatchingId == null) {
			System.out.println("Cannot find room matching id.");
			return;
		}
		
		switch (option) {
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
	}

	public String getAvailabilityByRoomType() {
		ArrayList<Room> rooms = dao.getAllItem();
		int singleCount = 0, doubleCount = 0, deluxeCount = 0, vipCount = 0;
		String vacantRooms = "Vacant rooms:";
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
		return vacantRooms + "\nSingle: " + singleCount + " out of 20\n\t" + vacantSingle + "\nDouble: " + doubleCount
				+ " out of 20\n\t" + vacantDouble + "\nDeluxe: " + deluxeCount + " out of 7\n\t" + vacantDeluxe
				+ "\nVIP: " + vipCount + " out of 1\n\t" + vacantVIP;
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
		return "Vacant:\n\t" + vacant + "\nOccupied:\n\t" + occupied + "\nReserved:\n\t" + reserved
				+ "\nUnder Maintenance:\n\t" + underMaintenance;
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
