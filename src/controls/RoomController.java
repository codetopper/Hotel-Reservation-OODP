package controls;

import data.DataController;
import entities.Room;
import entities.enums.BED_TYPE;
import entities.enums.ROOM_STATUS;
import entities.enums.ROOM_TYPE;

import java.io.Serializable;
import java.util.ArrayList;

public class RoomController implements Serializable {

    private static final long serialVersionUID = 2L;
    private static RoomController thisInstance = null;
    public ArrayList<Room> rooms;

    public static RoomController getInstance() {

        if (thisInstance==null) // not already loaded
        {
            DataController dataController = new DataController();
            thisInstance = dataController.loadRoomController();

            if (thisInstance==null)
                thisInstance = new RoomController(); // loading failure; resets controller
        }
        return thisInstance;
    }

    private RoomController() {
        resetRooms();
    }

    public String updateStatus(String id, int choice) {
        ROOM_STATUS status;
        for(Room room: rooms) {
            if(room.getRoomId().equals(id)) {
                switch(choice) {
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
                    default:
                        status = room.getStatus();
                        break;
                }
                room.setStatus(status);
                return "Room " + id + " is now " + status + ".";
            }
        }
        return "Room " + id + " does not exist.";
    }

    public String showVacantRoomsByRoomType() {
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
                        vacantSingle = vacantSingle + room.getRoomId() + ", ";
                        singleCount++;
                        break;
                    case DOUBLE:
                        vacantDouble = vacantDouble + room.getRoomId() + ", ";
                        doubleCount++;
                        break;
                    case DELUXE:
                        vacantDeluxe = vacantDeluxe + room.getRoomId() + ", ";
                        deluxeCount++;
                        break;
                    case VIP_SUITE:
                        vacantVIP = vacantVIP + room.getRoomId() + ", ";
                        vipCount++;
                        break;
                    default:
                        break;
                }
            }
        }
        return vacantRooms + "\nSingle: " + singleCount + " out of 20\n\t" + removeLastComma(vacantSingle) + "\nDouble: " + doubleCount
                + " out of 20\n\t" + removeLastComma(vacantDouble) + "\nDeluxe: " + deluxeCount + " out of 7\n\t" + removeLastComma(vacantDeluxe)
                + "\nVIP: " + vipCount + " out of 1\n\t" + removeLastComma(vacantVIP);
    }

    public String showRoomsByStatus() {
        String vacant = "";
        String occupied = "";
        String reserved = "";
        String underMaintenance = "";
        for (Room room : rooms) {
            switch (room.getStatus()) {
                case VACANT:
                    vacant += room.getRoomId() + ", ";
                    break;
                case OCCUPIED:
                    occupied += room.getRoomId() + ", ";
                    break;
                case RESERVED:
                    reserved += room.getRoomId() + ", ";
                    break;
                case UNDER_MAINTENANCE:
                    underMaintenance += room.getRoomId() + ", ";
                    break;
            }
        }
        return "Vacant:\n\t" + removeLastComma(vacant) + "\nOccupied:\n\t" + removeLastComma(occupied) + "\nReserved:\n\t" + removeLastComma(reserved)
                + "\nUnder Maintenance:\n\t" + removeLastComma(underMaintenance);
    }

    public void resetRooms() {
        rooms = new ArrayList<>();

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
    }

    public String removeLastComma(String str) {
        if (str != null && !str.isBlank() && !str.isEmpty()) {
            str = str.substring(0, str.length() - 2);
        }
        return str;
    }

    public Room getItemById(String id) {
        Room roomMatchingId = null;

        for (Room room : rooms) {
            if (room.getRoomId().equals(id)) {
                roomMatchingId = room;
                break;
            }
        }
        return roomMatchingId;
    }

}

//package controls;
//
//import entities.Room;
//import entities.enums.BED_TYPE;
//import entities.enums.ROOM_STATUS;
//import entities.enums.ROOM_TYPE;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//
//public class RoomController implements Serializable {
//
//    private static final long serialVersionUID = 2L;
//    public ArrayList<Room> rooms;
//
//    public RoomController() {
//        resetRooms();
//    }
//
//    public String updateStatus(String id, int choice) {
//        ROOM_STATUS status;
//        for(Room room: rooms) {
//            if(room.getRoomId().equals(id)) {
//                switch(choice) {
//                    case 1:
//                        status = ROOM_STATUS.VACANT;
//                        break;
//                    case 2:
//                        status = ROOM_STATUS.OCCUPIED;
//                        break;
//                    case 3:
//                        status = ROOM_STATUS.RESERVED;
//                        break;
//                    case 4:
//                        status = ROOM_STATUS.UNDER_MAINTENANCE;
//                        break;
//                    default:
//                        status = room.getStatus();
//                        break;
//                }
//                room.setStatus(status);
//                return "Room " + id + " is now " + status + ".";
//            }
//        }
//        return "Room " + id + " does not exist.";
//    }
//
//    public String showVacantRoomsByRoomType() {
//        int singleCount = 0, doubleCount = 0, deluxeCount = 0, vipCount = 0;
//        String vacantRooms = "Vacant rooms:";
//        String vacantSingle = "";
//        String vacantDouble = "";
//        String vacantDeluxe = "";
//        String vacantVIP = "";
//        for (Room room : rooms) {
//            if (room.isAvailable()) {
//                switch (room.getRoomType()) {
//                    case SINGLE:
//                        vacantSingle = vacantSingle + room.getRoomId() + ", ";
//                        singleCount++;
//                        break;
//                    case DOUBLE:
//                        vacantDouble = vacantDouble + room.getRoomId() + ", ";
//                        doubleCount++;
//                        break;
//                    case DELUXE:
//                        vacantDeluxe = vacantDeluxe + room.getRoomId() + ", ";
//                        deluxeCount++;
//                        break;
//                    case VIP_SUITE:
//                        vacantVIP = vacantVIP + room.getRoomId() + ", ";
//                        vipCount++;
//                        break;
//                    default:
//                        break;
//                }
//            }
//        }
//        return vacantRooms + "\nSingle: " + singleCount + " out of 20\n\t" + removeLastComma(vacantSingle) + "\nDouble: " + doubleCount
//                + " out of 20\n\t" + removeLastComma(vacantDouble) + "\nDeluxe: " + deluxeCount + " out of 7\n\t" + removeLastComma(vacantDeluxe)
//                + "\nVIP: " + vipCount + " out of 1\n\t" + removeLastComma(vacantVIP);
//    }
//
//    public String showRoomsByStatus() {
//        String vacant = "";
//        String occupied = "";
//        String reserved = "";
//        String underMaintenance = "";
//        for (Room room : rooms) {
//            switch (room.getStatus()) {
//                case VACANT:
//                    vacant += room.getRoomId() + ", ";
//                    break;
//                case OCCUPIED:
//                    occupied += room.getRoomId() + ", ";
//                    break;
//                case RESERVED:
//                    reserved += room.getRoomId() + ", ";
//                    break;
//                case UNDER_MAINTENANCE:
//                    underMaintenance += room.getRoomId() + ", ";
//                    break;
//            }
//        }
//        return "Vacant:\n\t" + removeLastComma(vacant) + "\nOccupied:\n\t" + removeLastComma(occupied) + "\nReserved:\n\t" + removeLastComma(reserved)
//                + "\nUnder Maintenance:\n\t" + removeLastComma(underMaintenance);
//    }
//
//    public void resetRooms() {
//        rooms = new ArrayList<>();
//
//        for (int i = 1; i <= 1; i++) {
//            rooms.add(new Room("0" + (700 + i), ROOM_TYPE.VIP_SUITE, BED_TYPE.MASTER, true, true, true));
//        }
//        for (int i = 1; i <= 7; i++) {
//            rooms.add(new Room("0" + (600 + i), ROOM_TYPE.DELUXE, BED_TYPE.MASTER, true, true, true));
//        }
//        for (int i = 1; i <= 10; i++) {
//            rooms.add(new Room("0" + (500 + i), ROOM_TYPE.DOUBLE, BED_TYPE.DOUBLE, true, true, false));
//        }
//        for (int i = 1; i <= 10; i++) {
//            rooms.add(new Room("0" + (300 + i), ROOM_TYPE.DOUBLE, BED_TYPE.DOUBLE, true, false, false));
//        }
//        for (int i = 1; i <= 10; i++) {
//            rooms.add(new Room("0" + (400 + i), ROOM_TYPE.SINGLE, BED_TYPE.SINGLE, true, true, false));
//        }
//        for (int i = 1; i <= 10; i++) {
//            rooms.add(new Room("0" + (200 + i), ROOM_TYPE.SINGLE, BED_TYPE.SINGLE, true, false, false));
//        }
//    }
//
//    public String removeLastComma(String str) {
//        if (str != null && !str.isBlank() && !str.isEmpty()) {
//            str = str.substring(0, str.length() - 2);
//        }
//        return str;
//    }
//
//    public Room getItemById(String id) {
//        Room roomMatchingId = null;
//
//        for (Room room : rooms) {
//            if (room.getRoomId().equals(id)) {
//                roomMatchingId = room;
//                break;
//            }
//        }
//        return roomMatchingId;
//    }
//
//}
