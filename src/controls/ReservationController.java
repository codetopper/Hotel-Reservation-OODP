package controls;

import data.DataController;
import entities.Guest;
import entities.Reservation;
import entities.Room;
import entities.enums.RESERVATION_STATUS;
import entities.enums.ROOM_STATUS;
import entities.enums.ROOM_TYPE;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReservationController implements Serializable {

    private static final long serialVersionUID = 10L;
    DateFormat formatter;
    ArrayList<Reservation> reservations;
    RoomController roomControl;
    GuestController guestController;
    static Scanner sc;
    private static ReservationController thisInstance = null;

    public static ReservationController getInstance() {

        if (thisInstance==null) // not already loaded
        {
            sc = new Scanner(System.in);
            DataController dataController = new DataController();
            thisInstance = dataController.loadReservationController();

            if (thisInstance==null)
                thisInstance = new ReservationController(); // loading failure; resets controller
        }
        return thisInstance;
    }

    private ReservationController() {
        resetReservations();
        formatter = new SimpleDateFormat("dd/MM/yyyy-HHmm");
    }

    public void resetReservations() {
        reservations = new ArrayList<>();
    }

    public void printReservation(Reservation reservation) {
        System.out.println();
        System.out.println("Reservation id: "+ reservation.getId());
        System.out.println("Guest ID: "+ reservation.getGuest().getId());
        System.out.println("Number of adults: " + reservation.getNumAdults());
        System.out.println("Number of children: " + reservation.getNumChildren());
        System.out.println("Reservation status: " + reservation.getStatus());
        String billing = "Credit Card";
        if (reservation.getBilling()==2) {
            billing = "Cash";
        }
        System.out.println("Billing type: " + billing);
        System.out.println("CheckIn Date: " + reservation.getCheckInDate());
        System.out.println("CheckOut Date: " + reservation.getCheckOutDate());
        System.out.println("Your room IDs: " + reservation.getRoomId());
        // total charge
    }

    public void printAllReservations() {
        for (Reservation reservation : reservations) {
            printReservation(reservation);

        }
        System.out.println();
        System.out.println("There are " + reservations.size() + " reservations.");
    }

    public Reservation getReservation(String id) {
        Reservation reservation = null;
        for(Reservation rsv: reservations) {
            if (rsv.getId().equals(id)) {
                reservation = rsv;
            }
        }
        return reservation;
    }

    public void checkOut(String guestId) {
        roomControl = RoomController.getInstance();
        PaymentController paymentController = new PaymentController();
        Reservation reservation = null;
        for (Reservation rsv: reservations) {
            try {
                if (rsv.getGuest().getId().equals(guestId)) {
                    reservation = rsv;
                }
            }
            catch (Exception ex) {
                System.out.println();
                System.out.println("Guest " + guestId + " does not exist.");
                return;
            }

        }
        ArrayList<Room> rooms = reservation.getRooms();
        reservation.setStatus(RESERVATION_STATUS.CHECKED_OUT);
        for (int i=0; i<rooms.size();i++) {
            roomControl.updateStatus(rooms.get(i).getRoomId(), 1);
        }
        paymentController.payment(reservation);
    }

    public void expire() {
        Date dateNow = new Date();
        roomControl = RoomController.getInstance();
        for (Reservation reservation : reservations) {
            Date checkIn = reservation.getCheckInDate();
            Date twoHoursLate = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(checkIn);
            c.add(Calendar.HOUR_OF_DAY, 2);
            try {
                dateNow = (Date) formatter.parse(formatter.format(dateNow));
                twoHoursLate = (Date) formatter.parse(formatter.format(c.getTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if(dateNow.after(twoHoursLate) && reservation.getStatus()!= RESERVATION_STATUS.CHECKED_IN && reservation.getCheckOutDate().after(dateNow)) {
                ArrayList<Room> rooms = reservation.getRooms();
                reservation.setStatus(RESERVATION_STATUS.EXPIRED);
                for (int i=0; i<rooms.size();i++) {
                    roomControl.updateStatus(rooms.get(i).getRoomId(), 1);
                }
            } else if (reservation.getStatus()== RESERVATION_STATUS.CHECKED_IN && dateNow.after(reservation.getCheckOutDate())) {
                ArrayList<Room> rooms = reservation.getRooms();
                reservation.setStatus(RESERVATION_STATUS.CHECKED_OUT);
                for (int i=0; i<rooms.size();i++) {
                    roomControl.updateStatus(rooms.get(i).getRoomId(), 1);
                }
            }
        }
    }

    public void createReservation(boolean isWalkIn) {
        roomControl = RoomController.getInstance();
        guestController = GuestController.getInstance();
        String id, roomId;
        int billing;
        Date checkInDate, checkOutDate = null;
        int numAdults, numChildren;

        Date date = new Date();
        id = formatter.format(date).trim();
        Guest guest = null;
        System.out.println("Guest information\n1) Create new\n2) Existing guest");
        int guestOption = getIntegerOnly("Option: ", 1, 2);
        if (guestOption==2) {
            System.out.print("Enter guest identity: ");
            String guestId = sc.nextLine();
            for (Guest gs: guestController.guests) {
                if (gs.getId().equals(guestId)) {
                    guest = gs;
                    break;
                }
            }
            if (guest == null) {
                System.out.println("Guest " + guestId + " not found, please create a new one.\n");
                guestOption = 1;
            }
            else {
                System.out.println("Guest " + guestId + " found!");
                System.out.println(guest.print()+"\n");
            }
        }
        if (guestOption==1) {
            boolean guestCreated = false;
            while(guestCreated == false) {
                System.out.print("Create Guest details\n");
                /*takes input for the identity*/
                System.out.print("Please enter the identity number: ");
                String identity = sc.nextLine();
                /*takes input for the name*/
                System.out.print("Please enter the name: ");
                String name = sc.nextLine();
                /*takes input for the address*/
                System.out.print("Please enter the address: ");
                String addr = sc.nextLine();
                /*takes input for the contact number*/
                System.out.print("Please enter the contact number: ");
                String contact = sc.nextLine();
                /*takes input for the credit card number*/
                System.out.print("Please enter the credit card number: ");
                String card = sc.nextLine();
                /*takes input for the country*/
                System.out.print("Please enter the country: ");
                String country = sc.nextLine();
                /*takes input for the nationality*/
                System.out.print("Please enter the nationality: ");
                String nationality = sc.nextLine();
                /*takes input for the gender*/
                System.out.println("Please select a gender-(1)male (2)female (3)others ");
                int genderOption = getIntegerOnly("Option: ", 1, 3);
                String gender="Others";
                if(genderOption==1)
                {
                    gender = "Male";
                }
                else if (genderOption==2)
                {
                    gender = "Female";
                }
                String print = guestController.createGuest(identity, name, addr, contact, card, country, nationality, genderOption);
                guest = new Guest (identity, name, addr, contact, card, country, nationality, gender);
                System.out.println(print);
                if (print.equals("Guest already exists.") || print.equals("Guest is created!")) {
                    guestCreated = true;
                }
            }
        }

        System.out.print("Number of adults: ");
        numAdults = sc.nextInt();

        System.out.print("Number of children: ");
        numChildren = sc.nextInt();
        int totalPeople = numAdults + numChildren;
        sc.nextLine();
        System.out.println("Choose room(s): ");
        System.out.println(roomControl.showVacantRoomsByRoomType());
        int roomNr = 1;
        ArrayList<Room> rooms = new ArrayList<>();
        String available;
        while(totalPeople>0) {
            do {
                System.out.print("Room "+roomNr+": ");
                roomId = sc.nextLine();
                available = roomControl.getItemById(roomId).getStatus().toString();
            } while(!available.equals("VACANT"));
            roomControl.updateStatus(roomId, 3);
            rooms.add(roomControl.getItemById(roomId));
            if (roomControl.getItemById(roomId).getRoomType() != ROOM_TYPE.SINGLE) {
                totalPeople -= 2;
            }
            else {
                totalPeople -= 1;
            }
            roomNr++;
        }


        System.out.println();
        System.out.println("Choose billing:\n1. Credit Card\n2. Cash");
        billing = getIntegerOnly("Option: ", 1, 2);
        System.out.println();
        checkInDate = new Date();
        boolean checkIn = false;
        if(!isWalkIn) {
            do {
                System.out.print("CheckIn (dd/MM/yyyy): ");
                try {
                    checkInDate = (Date) formatter.parse(sc.nextLine()+"-1500");
                    Date dateNow = new Date();
                    dateNow = (Date) formatter.parse(formatter.format(dateNow));

                    if (checkInDate.equals(dateNow) || checkInDate.after(dateNow)) {
                        checkIn = true;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            } while(!checkIn);
            System.out.print("CheckIn time is from 15:00\n");
        }

        checkIn = false;

        do {
            System.out.print("CheckOut (dd/MM/yyyy): ");
            try {
                checkOutDate = (Date) formatter.parse(sc.nextLine()+"-1200");
                Date dateNow = new Date();
                dateNow = (Date) formatter.parse(formatter.format(dateNow));

                if (checkInDate.before(checkOutDate)) {
                    checkIn = true;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        } while(!checkIn);
        System.out.println("CheckOut time is until 12:00 noon\n");

        RESERVATION_STATUS status = RESERVATION_STATUS.IN_WAITLIST;
        if(!isWalkIn) {
            System.out.println("Does client pay now?\n1)Yes\n2)No");
            int payNow = getIntegerOnly("Option: ", 1, 2);
            if (payNow==1) {
                status = RESERVATION_STATUS.CONFIRMED;
            }
        }else {
            System.out.println("As this is walk-in, we require immediate payment.");
            status = RESERVATION_STATUS.CHECKED_IN;
            for (int l=0; l<rooms.size();l++) {
                roomControl.updateStatus(rooms.get(l).getRoomId(), 2);
            }
        }

        Reservation reservation = new Reservation(guest, id, numAdults, numChildren, status, billing, checkInDate, checkOutDate, rooms);
        reservations.add(reservation);
        System.out.println();
        System.out.print("Reservation made.");
        printReservation(reservation);
        PaymentController paymentController = new PaymentController();
        paymentController.printRoomInvoice(reservation);
    }


    public void updateReservation() {
        roomControl = RoomController.getInstance();
        Date checkOutDate = null;
        Date checkInDate = null;
        System.out.print("Please enter reservation number: ");
        String reservationId = sc.nextLine();
        if(validateReservation(reservationId)) {
            Reservation reservation = getReservation(reservationId);
            int input=-1;
            int number;
            boolean checkIn = false;
            while (input != 0) {
                System.out.println("Please select the option you want to update:");
                System.out.println("1) Number of adults");
                System.out.println("2) Number of children");
                System.out.println("3) Room number");
                System.out.println("4) CheckIn Date");
                System.out.println("5) CheckOut Date");
                System.out.println("6) Reservation status");
                System.out.println("0) Exit");
                input = getIntegerOnly("Option: ", 0, 6);

                switch(input) {
                    case 1:
                        System.out.println("Enter number of adults: ");
                        number = sc.nextInt();
                        reservation.setNumAdults(number);
                        break;
                    case 2:
                        System.out.println("Enter number of children: ");
                        number = sc.nextInt();
                        reservation.setNumChildren(number);
                        break;
                    case 3:
                        String newRoomId, oldRoomId;
                        ArrayList<Room> rooms = reservation.getRooms();
                        do {
                            System.out.println("Old room id: ");
                            // change room to vacant
                            oldRoomId = sc.nextLine();
                            System.out.println("New room id: ");
                            newRoomId = sc.nextLine();
                        } while(roomControl.getItemById(newRoomId).getStatus()!= ROOM_STATUS.VACANT && !Arrays.asList(rooms).contains(oldRoomId));

                        rooms.set(rooms.indexOf(oldRoomId), roomControl.getItemById(newRoomId));
                        reservation.setRooms(rooms);
                        roomControl.updateStatus(oldRoomId, 1);
                        roomControl.updateStatus(newRoomId, 3);
                        break;
                    case 4:
                        do {
                            System.out.println("CheckIn (dd/MM/yyyy):");

                            try {
                                checkInDate = (Date) formatter.parse(sc.nextLine()+"1500");
                                Date dateNow = new Date();
                                dateNow = (Date) formatter.parse(formatter.format(dateNow));

                                if ((checkInDate.equals(dateNow) || checkInDate.after(dateNow)) && checkInDate.before(reservation.getCheckOutDate())) {
                                    checkIn = true;
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                        } while(!checkIn);
                        reservation.setCheckInDate(checkInDate);
                        break;
                    case 5:

                        do {
                            System.out.println("CheckOut (dd/MM/yyyy):");
                            try {
                                checkOutDate = (Date) formatter.parse(sc.nextLine()+"1200");
                                Date dateNow = new Date();
                                dateNow = (Date) formatter.parse(formatter.format(dateNow));

                                if (checkInDate.before(checkOutDate)) {
                                    checkIn = true;
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                        } while(!checkIn);
                        reservation.setCheckOutDate(checkOutDate);
                        break;
                    case 6:
                        System.out.println("Status 1) CONFIRMED 2) IN_WAITLIST 3) CHECKED_IN 4) CHECKED_OUT 5) EXPIRED");
                        int next = getIntegerOnly("Option: ", 1, 5);
                        RESERVATION_STATUS arr[] = RESERVATION_STATUS.values();
                        reservation.setStatus(arr[next]);
                        break;
                }
            }
        }
    }



    public void checkIn() {
        roomControl = RoomController.getInstance();
        int choice = -1;
        while(choice !=0) {
            System.out.println();
            System.out.println("=== Check In Menu ===");
            System.out.println("1) Reservation");
            System.out.println("2) Walk-in");
            System.out.println("0) Back to Main Menu");
            System.out.println("=====================");
            choice = getIntegerOnly("Option: ", 0, 2);
            if (choice == 1) {
                System.out.println("Reservation Id");
                String reservationId = sc.nextLine();
                Reservation reservation = getReservation(reservationId);
                if (reservation==null) {
                    System.out.println("Reservation ID " + reservationId + " does not exist.");
                    continue;
                }
                Date dateNow = new Date();
                Date twoHoursLate = new Date();
                Calendar c = Calendar.getInstance();
                Date checkIn = reservation.getCheckInDate();
                c.setTime(checkIn);
                c.add(Calendar.HOUR_OF_DAY, 2);
                try {
                    dateNow = (Date) formatter.parse(formatter.format(dateNow));
                    twoHoursLate = (Date) formatter.parse(formatter.format(c.getTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                ArrayList<Room> rooms = reservation.getRooms();
                if ((checkIn.equals(dateNow) || (checkIn.after(dateNow) && twoHoursLate.after(dateNow))) && (reservation.getStatus() == RESERVATION_STATUS.CONFIRMED || reservation.getStatus() == RESERVATION_STATUS.IN_WAITLIST)) {
                    if (reservation.getStatus() == RESERVATION_STATUS.IN_WAITLIST) {
                        System.out.println("Guest must pay now! Checking in...");
                    }
                    reservation.setStatus(RESERVATION_STATUS.CHECKED_IN);
                    for (int i = 0; i < rooms.size(); i++) {
                        roomControl.updateStatus(rooms.get(i).getRoomId(), 2);
                    }

                } else if (dateNow.after(twoHoursLate)) {
                    System.out.println("Reservation expired...");
                    reservation.setStatus(RESERVATION_STATUS.EXPIRED);
                    for (int i = 0; i < rooms.size(); i++) {
                        roomControl.updateStatus(rooms.get(i).getRoomId(), 1);
                    }
                } else {
                    for (int i = 0; i < rooms.size(); i++) {
                        roomControl.updateStatus(rooms.get(i).getRoomId(), 1);
                    }
                }
            }
            if (choice == 2){
                System.out.println("Walk-In guest.");
                createReservation(true);
            }
        }
    }

    public void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    private boolean validateReservation (String givenId){
        Reservation reservationMatchingId = getReservation(givenId);

        if (reservationMatchingId == null || givenId.isEmpty()) {
            return false;
        }
        return true;
    }

    public String getIdByRoom(String roomId) {
        Reservation reservationMatchingId = null;

        for (Reservation reservation : reservations) {
            for (Room room: reservation.getRooms()) {
                if (room.getRoomId().equals(roomId)) {
                    reservationMatchingId = reservation;
                    return reservationMatchingId.getId();
                }
            }
        }
        return null;
    }

    public int getIntegerOnly(String message, int low_inclusive, int high_inclusive) {
        int choice;
        System.out.print(message);
        try
        {
            choice = sc.nextInt();
            sc.nextLine();
            if (choice<low_inclusive || choice > high_inclusive)
            {
                System.out.printf("Out of bounds. Please enter an integer from %d to %d.", low_inclusive, high_inclusive);
                System.out.println();
                choice = getIntegerOnly(message, low_inclusive, high_inclusive);
            }
        }
        catch(Exception e)
        {
            System.out.printf("Only integers are accepted. Please enter an integer from %d to %d.", low_inclusive, high_inclusive);
            System.out.println();
            sc.nextLine();
            choice = getIntegerOnly(message, low_inclusive, high_inclusive);
        }
        return choice;
    }
}
