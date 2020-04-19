package data;

import entity_classes.Guest;
import entity_classes.Hotel;

import java.util.ArrayList;

public class GuestDAO {

    // load data access
    private DataUtil dataUtil = new DataUtil();

    public void resetGuestList() {
        Hotel hotel = dataUtil.readHotel();
        ArrayList<Guest> guests = new ArrayList<>();
        hotel.setGuests(guests);
        dataUtil.write(hotel);
    }

    // interfaces
    public ArrayList<Guest> getAllItem() {
        Hotel hotel = dataUtil.readHotel();
        ArrayList<Guest> guests = hotel.getGuests();
        return guests;
    }

    public Guest getItemById(String id) {
        ArrayList<Guest> guests = getAllItem();
        Guest guestMatchingId = null;

        for (Guest guest : guests) {
            if (guest.getId().equals(id)) {
                guestMatchingId = guest;
                break;
            }
        }
        return guestMatchingId;
    }

    public void add(Guest guest) {
        Hotel hotel = dataUtil.readHotel();
        ArrayList<Guest> guests = hotel.getGuests();
        guests.add(guest);
        dataUtil.write(hotel);
    }

    public void update(Guest guest) {
        Hotel hotel = dataUtil.readHotel();
        ArrayList<Guest> guests = hotel.getGuests();
        for (int i = 0; i < guests.size(); i++) {
            if (guests.get(i).getId().equals(guest.getId())) {
                guests.set(i, guest);
                break;
            }
        }
        dataUtil.write(hotel);
    }
}
