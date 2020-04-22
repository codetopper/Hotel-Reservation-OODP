package room;

import reservation.Reservation;
import reservation.ReservationDAO;

import java.util.ArrayList;

public class RoomControl {

	// attributes
	private RoomDAO dao = new RoomDAO();
	private ReservationDAO reservationDAO = new ReservationDAO();

	// interfaces
	public String getAvailabilityByRoomId(String id) {
		Room roomMatchingId = dao.getItemById(id);
		
		return roomMatchingId.roomDetails();
	}
	
	public String getAvailabilityByGuestName(String name) {
		ArrayList<Reservation> reservations = reservationDAO.getAllItem();
		String roomInfo = "";
		for (Reservation reservation: reservations) {
			if (reservation.getGuest().getName().equals(name)) {
				for (Room room: reservation.getRooms()) {
					roomInfo += room.roomDetails();
				}
				return roomInfo;
			}
		}
		return name + " does not have a reservation currently.";
	}

	public String updateStatus(String id, int choice) {
		ROOM_STATUS status = ROOM_STATUS.VACANT;
		Room roomMatchingId = dao.getItemById(id);
		
		if (roomMatchingId == null) {
			return "There is no Room " + id + ".";

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
		return id + " is now " + status.toString() + ".";
	}

	public String getAvailabilityByRoomType() {
		ArrayList<Room> rooms = dao.getAllItem();
		int singleCount = 0, doubleCount = 0, deluxeCount = 0, vipCount = 0;
		int singleTotal = 0, doubleTotal = 0, deluxeTotal = 0, vipTotal = 0;
		String vacantRooms = "Vacant rooms:\n";
		String vacantSingle = "";
		String vacantDouble = "";
		String vacantDeluxe = "";
		String vacantVIP = "";
		for (Room room : rooms) {
			switch(room.getRoomType()) {
				case SINGLE:
					singleTotal++;
					break;
				case DOUBLE:
					doubleTotal++;
					break;
				case DELUXE:
					deluxeTotal++;
					break;
				case VIP_SUITE:
					vipTotal++;
					break;
			}
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
		return "Single: " + singleCount + " out of " + singleTotal + " vacant.\n\t" + removeLastComma(vacantSingle) + "\nDouble: " + doubleCount
				+ " out of " + doubleTotal + " vacant.\n\t" + removeLastComma(vacantDouble) + "\nDeluxe: " + deluxeCount + " out of " + deluxeTotal + " vacant.\n\t" + removeLastComma(vacantDeluxe)
				+ "\nVIP: " + vipCount + " out of " + vipTotal + " vacant.\n\t" + removeLastComma(vacantVIP);
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

	public boolean validateRoomId(String id) {
		Room roomMatchingId = dao.getItemById(id);

		if (roomMatchingId == null) {
			return false;
		}
		return true;
	}

}
