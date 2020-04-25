package reservation;

import java.util.ArrayList;

import data.DataUtil;
import data.Hotel;
import room.Room;

public class ReservationDAO {
	
	// load data access
    private static DataUtil dataUtil = new DataUtil();

    // interfaces

    //remove the given reservation
    public void removeReservation(Reservation reservation) {
    	Hotel hotel = dataUtil.readHotel();
    	ArrayList<Reservation> reservations = hotel.getReservations();
    	reservations.remove(reservation);
    	dataUtil.write(hotel);
    }

    // get a list of all reservations
    public ArrayList<Reservation> getAllItem() {
        Hotel hotel = dataUtil.readHotel();
        ArrayList<Reservation> reservations = hotel.getReservations();
        return reservations;
    }

    // get the reservation with the id
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
    
    // add a new reservation to the list
    public void add(Reservation reservation) {
        Hotel hotel = dataUtil.readHotel();
        ArrayList<Reservation> reservations = hotel.getReservations();
        reservations.add(reservation);
        dataUtil.write(hotel);
    }

    // update the given reservation
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

    // get the reservation that contains the roomId
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
