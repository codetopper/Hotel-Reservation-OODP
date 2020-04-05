package Hotel;

public class StatisticReport {

    public String getVacantRooms(){
        int singleCount = 0, doubleCount = 0, deluxeCount = 0, vipCount = 0;
        String vacantRooms = "Vacant rooms:";
        String vacantSingle = "";
        String vacantDouble = "";
        String vacantDeluxe = "";
        String vacantVIP = "";
        for (Room room: Hotel.rooms) {
            if(room.getStatus() == ROOM_STATUS.VACANT)
            {
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
                }
            }
        }
        return vacantRooms + "\n\nSingle: " + singleCount + " out of 20\n\t" + vacantSingle +
                "\nDouble: " + doubleCount + " out of 20\n\t" + vacantDouble +
                "\nDeluxe: " + deluxeCount + " out of 7\n\t" + vacantDeluxe +
                "\nVIP: " + vipCount + " out of 1\n\t" + vacantVIP;
    }

    public String arrangeByStatus() {
        String vacant = "";
        String occupied = "";
        String reserved = "";
        String underMaintenance = "";
        for (Room room: Hotel.rooms) {
            switch (room.getRoomType()) {
                case SINGLE:
                    vacant += room.getRoomId() + ", ";
                    break;
                case DOUBLE:
                    occupied += room.getRoomId() + ", ";
                    break;
                case DELUXE:
                    reserved += room.getRoomId() + ", ";
                    break;
                case VIP_SUITE:
                    underMaintenance += room.getRoomId() + ", ";
                    break;
            }
        }
        return "Vacant:\n\t" + vacant + "\nOccupied:\n\t" + occupied + "\nReserved\n\t" + reserved + "\nUnder Maintenance\n\t" + underMaintenance;
    }
}
