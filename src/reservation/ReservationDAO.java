package reservation;

import java.util.ArrayList;

import data.DataUtil;
import data.Hotel;
import room.Room;

public class ReservationDAO {
	
	// load data access
    private static DataUtil dataUtil = new DataUtil();
    
    public void removeReservation(Reservation reservation) {
    	Hotel hotel = dataUtil.readHotel();
    	ArrayList<Reservation> reservations = hotel.getReservations();
    	reservations.remove(reservation);
    	dataUtil.write(hotel);
    }

    // interfaces
    public ArrayList<Reservation> getAllItem() {
        Hotel hotel = dataUtil.readHotel();
        ArrayList<Reservation> reservations = hotel.getReservations();
        return reservations;
    }

    public Reservation getItemById(String id) {
        ArrayList<Reservation> reservations = getAllItem();
        Reservation reservationMatchingId = null;

        for (Reservation reservation : reservations) {
            if (reservation.getId().equals(id)) {
                reservationMatchingId = reservation;
                break;
            }
        }
        return reservationMatchingId;
    }
    

    public void add(Reservation reservation) {
        Hotel hotel = dataUtil.readHotel();
        ArrayList<Reservation> reservations = hotel.getReservations();
        reservations.add(reservation);
        dataUtil.write(hotel);
    }

    public void update(Reservation reservation) {
        Hotel hotel = dataUtil.readHotel();
        ArrayList<Reservation> reservations = hotel.getReservations();
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getId().equals(reservation.getId())) {
                reservations.set(i, reservation);
                break;
            }
        }
        dataUtil.write(hotel);
    }

    public String getIdByRoom(String roomId) {
        Reservation reservationMatchingId;
        ArrayList<Reservation> reservations = getAllItem();

        for (Reservation reservation : reservations) {
            for (Room room: reservation.getRooms()) {
                if (room.getId().equals(roomId)) {
                    reservationMatchingId = reservation;
                    return reservationMatchingId.getId();
                }
            }
        }
        return null;
    }
}
