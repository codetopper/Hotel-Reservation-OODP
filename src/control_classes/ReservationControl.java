package control_classes;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import boundary_classes.ReservationBoundary;
import data.GuestDAO;
import data.ReservationDAO;
import data.RoomDAO;
import entity_classes.Guest;
import entity_classes.Reservation;
import enums.RESERVATION_STATUS;
import enums.ROOM_STATUS;
import enums.ROOM_TYPE;
import main.Main;
import main.MainBoundary;

public class ReservationControl {

    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy-HHmm");

    private ReservationDAO dao = new ReservationDAO();
    private RoomDAO roomDao = new RoomDAO();
    private GuestDAO guestDao = new GuestDAO();
    // Initialize attributes
    RoomControl roomControl = new RoomControl();
    GuestControl guestControl = new GuestControl();
    Scanner scanner = ReservationBoundary.scanner;

    public void printReservation(Reservation reservation) {
        ReservationBoundary.print("=====Your reservation:");
        ReservationBoundary.print("Reservation id: "+ reservation.getId());
        ReservationBoundary.print("Number of adults: " + reservation.getNumAdults());
        ReservationBoundary.print("Number of children: " + reservation.getNumChildren());
        ReservationBoundary.print("Reservation status: " + reservation.getStatus());
        String billing = "Credit Card";
        if (reservation.getBilling()==2) {
            billing = "Cash";
        }
        ReservationBoundary.print("Billing type: " + billing);
        ReservationBoundary.print("CheckIn Date: " + reservation.getCheckInDate());
        ReservationBoundary.print("CheckOut Date: " + reservation.getCheckOutDate());
        ReservationBoundary.print("Your room ID's: " + Arrays.toString(reservation.getRooms()));
        ReservationBoundary.print("=====");
        // total charge
    }

    public void printAllReservations() {
        ArrayList<Reservation> reservations = dao.getAllItem();
        for (Reservation reservation : reservations) {
            printReservation(reservation);
        }
    }

    public Reservation getReservation(String id) {
        Reservation reservation = dao.getItemById(id);
        return reservation;
    }

    public void checkOut(Reservation reservation) {
        String[] rooms = reservation.getRooms();
        reservation.setStatus(RESERVATION_STATUS.CHECKED_OUT);
        for (int i=0; i<rooms.length;i++) {
            roomControl.updateStatus(rooms[i], 1);
        }
        // PRINT BILL
    }

    public void expire() {
        ArrayList<Reservation> reservations = dao.getAllItem();
        Date dateNow = new Date();

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

            if(dateNow.after(twoHoursLate) && reservation.getStatus()!=RESERVATION_STATUS.CHECKED_IN && reservation.getCheckOutDate().after(dateNow)) {
                String[] rooms = reservation.getRooms();
                reservation.setStatus(RESERVATION_STATUS.EXPIRED);
                for (int i=0; i<rooms.length;i++) {
                    roomControl.updateStatus(rooms[i], 1);
                }
            } else if (reservation.getStatus()==RESERVATION_STATUS.CHECKED_IN && dateNow.after(reservation.getCheckOutDate())) {
                String[] rooms = reservation.getRooms();
                reservation.setStatus(RESERVATION_STATUS.CHECKED_OUT);
                for (int i=0; i<rooms.length;i++) {
                    roomControl.updateStatus(rooms[i], 1);
                }
            }
        }
    }

    public void createReservation(boolean isWalkIn) {
        String id, roomId;
        int billing;
        Date checkInDate = null, checkOutDate = null;
        int numAdults, numChildren;
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy-HHmm");

        Date date = new Date();
        id = formatter.format(date).trim();
        Guest guest = null;
        ReservationBoundary.print("Guest information\n1) Create new\n2) Existing guest\n");
        int guestOption = MainBoundary.inIntInRange("Option: ", 1, 2);
        if (guestOption==2) {
            ReservationBoundary.print("Enter guest identity: ");
            String guestId = scanner.nextLine();
            guest = guestDao.getItemById(guestId);
            if (guest == null) {
                ReservationBoundary.print("Guest " + guestId + " not found, please create a new one.\n");
                guestOption = 1;
            }
            else {
                ReservationBoundary.print("Guest " + guestId + " found!");
                ReservationBoundary.print(guest.print()+"\n");
            }
        }
        if (guestOption==1) {
            ReservationBoundary.print("Create Guest details\n");
            /*takes input for the identity*/
            ReservationBoundary.print("Please enter the identity number: ");
            String identity = scanner.nextLine();
            /*takes input for the name*/
            ReservationBoundary.print("Please enter the name: ");
            String name = scanner.nextLine();
            /*takes input for the address*/
            ReservationBoundary.print("Please enter the address: ");
            String addr = scanner.nextLine();
            /*takes input for the contact number*/
            ReservationBoundary.print("Please enter the contact number: ");
            String contact = scanner.nextLine();
            /*takes input for the credit card number*/
            ReservationBoundary.print("Please enter the credit card number:");
            String card = scanner.nextLine();
            /*takes input for the country*/
            ReservationBoundary.print("Please enter the country: ");
            String country = scanner.nextLine();
            /*takes input for the nationality*/
            ReservationBoundary.print("Please enter the nationality: ");
            String nationality = scanner.nextLine();
            /*takes input for the gender*/
            ReservationBoundary.print("Please select a gender-(1)male (2)female (3)others: ");
            int genderOption = MainBoundary.inIntInRange("Option: ", 1, 3);
            String gender="Others";
            if(genderOption==1)
            {
                gender = "Male";
            }
            else if (genderOption==2)
            {
                gender = "Female";
            }
            guest = new Guest(identity, name, card, addr, country, gender, nationality, contact);
        }

        ReservationBoundary.print("Number of adults: ");
        numAdults = scanner.nextInt();

        ReservationBoundary.print("Number of children: ");
        numChildren = scanner.nextInt();

        int totalPeople = numAdults + numChildren;
        Scanner sc = new Scanner(System.in);
        ReservationBoundary.print(roomControl.getByStatus());
        int roomNr =1;
        int i;
        String[] rooms = new String[(int) Math.ceil(totalPeople/2.0)];
        String available="";
        for(i=totalPeople;i>1;i-=2) {
            do {
                ReservationBoundary.print("Room id for couple "+roomNr+": ");
                roomId = sc.nextLine();
                available = roomControl.getAvailabilityByRoomId(roomId);
            } while(!available.equals("Room is: VACANT\n")&& roomDao.getItemById(roomId).getRoomType() != ROOM_TYPE.SINGLE);
            roomControl.updateStatus(roomId, 3);
            rooms[roomNr-1]=roomId;
            roomNr++;
        }
        if(i==1) {
            ReservationBoundary.print("Room id for last person: ");
            roomId = sc.nextLine();
            ReservationBoundary.print(roomId);
            while (roomDao.getItemById(roomId).getStatus() != ROOM_STATUS.VACANT) {
                ReservationBoundary.print("Room id for last person "+roomNr+":");
                roomId = scanner.nextLine();
                available = roomControl.getAvailabilityByRoomId(roomId);
            }
            roomControl.updateStatus(roomId, 3);
            rooms[rooms.length-1]=roomId;
        }


        ReservationBoundary.print("Choose billing:\n1. Creditcard\n2. Cash\n");
        billing = MainBoundary.inIntInRange("Option: ", 1, 2);
        checkInDate = new Date();
        boolean checkIn = false;
        if(!isWalkIn) {
            do {
                ReservationBoundary.print("CheckIn (dd/MM/yyyy):");
                try {
                    checkInDate = (Date) formatter.parse(scanner.nextLine()+"-1500");
                    Date dateNow = new Date();
                    dateNow = (Date) formatter.parse(formatter.format(dateNow));

                    if (checkInDate.equals(dateNow) || checkInDate.after(dateNow)) {
                        checkIn = true;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            } while(!checkIn);
            ReservationBoundary.print("CheckIn time is from 15:00");
        }

        checkIn = false;

        do {
            ReservationBoundary.print("CheckOut (dd/MM/yyyy):");
            try {
                checkOutDate = (Date) formatter.parse(scanner.nextLine()+"-1200");
                Date dateNow = new Date();
                dateNow = (Date) formatter.parse(formatter.format(dateNow));

                if (checkInDate.before(checkOutDate)) {
                    checkIn = true;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        } while(!checkIn);
        ReservationBoundary.print("CheckOut time is until 12:00 noon");

        RESERVATION_STATUS status = RESERVATION_STATUS.IN_WAITLIST;
        if(!isWalkIn) {
            ReservationBoundary.print("Does client pay now?\n1)Yes\n2)No");
            int payNow = MainBoundary.inIntInRange("Option: ", 1, 2);
            if (payNow==1) {
                status = RESERVATION_STATUS.CONFIRMED;
            }
        }else {
            ReservationBoundary.print("As this is walk-in, we require immediate payment.");
            status = RESERVATION_STATUS.CHECKED_IN;
            for (int l=0; l<rooms.length;l++) {
                roomControl.updateStatus(rooms[l], 2);
            }
        }



        Reservation reservation = new Reservation(guest, id, numAdults, numChildren, status, billing, checkInDate, checkOutDate, rooms);
        // GET ROOM PRICE!
        ReservationBoundary.print("Reservation made.");
        printReservation(reservation);
    }


    public void updateReservation() {
        Date checkOutDate = null;
        Date checkInDate = null;
        ReservationBoundary.print("Please enter reservation number: ");
        String reservationId = scanner.nextLine();
        if(validateReservation(reservationId)) {
            Reservation reservation = getReservation(reservationId);
            int choice=-1;
            int number;
            boolean checkIn = false;
            while (choice != 0) {
                ReservationBoundary.print("Please select the option you want to update:");
                ReservationBoundary.print("0) Exit");
                ReservationBoundary.print("1) Number of adults");
                ReservationBoundary.print("2) Number of children");
                ReservationBoundary.print("3) Room number");
                ReservationBoundary.print("4) CheckIn Date");
                ReservationBoundary.print("5) CheckOut Date");
                ReservationBoundary.print("6) Reservation status");
                int input = MainBoundary.inIntInRange("Option: ", 0, 6);

                switch(input) {
                    case 1:
                        ReservationBoundary.print("Enter number of adults: ");
                        number = scanner.nextInt();
                        reservation.setNumAdults(number);
                    case 2:
                        ReservationBoundary.print("Enter number of children: ");
                        number = scanner.nextInt();
                        reservation.setNumChildren(number);
                    case 3:
                        String newRoomId, oldRoomId;
                        String[] rooms = reservation.getRooms();
                        do {
                            ReservationBoundary.print("Old room id: ");
                            // change room to vacant
                            oldRoomId = scanner.nextLine();
                            ReservationBoundary.print("New room id: ");
                            newRoomId = scanner.nextLine();
                        } while(roomDao.getItemById(newRoomId).getStatus()!=ROOM_STATUS.VACANT && !Arrays.asList(rooms).contains(oldRoomId));

                        rooms[Arrays.binarySearch(rooms, oldRoomId)] = newRoomId;
                        reservation.setRooms(rooms);
                        roomControl.updateStatus(oldRoomId, 1);
                        roomControl.updateStatus(newRoomId, 3);
                    case 4:
                        do {
                            ReservationBoundary.print("CheckIn (dd/MM/yyyy):");

                            try {
                                checkInDate = (Date) formatter.parse(scanner.nextLine()+"1500");
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
                    case 5:

                        do {
                            ReservationBoundary.print("CheckOut (dd/MM/yyyy):");
                            try {
                                checkOutDate = (Date) formatter.parse(scanner.nextLine()+"1200");
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
                    case 6:
                        ReservationBoundary.print("Status 1) CONFIRMED 2) IN_WAITLIST 3) CHECKED_IN 4) CHECKED_OUT 5) EXPIRED");
                        int next = MainBoundary.inIntInRange("Option: ", 1, 5);
                        RESERVATION_STATUS arr[] = RESERVATION_STATUS.values();
                        reservation.setStatus(arr[next]);
                }
            }
        }
    }



    public void checkIn() {
        ReservationBoundary.print("CheckIn\n1) Reservation\n2)Walk-in");
        int choice = MainBoundary.inIntInRange("Option: ", 1, 2);
        if(choice==1) {
            ReservationBoundary.print("Reservation Id");
            String reservationId = scanner.nextLine();
            Reservation reservation = getReservation(reservationId);

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
            String[] rooms = reservation.getRooms();
            if ((checkIn.equals(dateNow) || (checkIn.after(dateNow) && twoHoursLate.after(dateNow)))&& (reservation.getStatus()==RESERVATION_STATUS.CONFIRMED || reservation.getStatus()==RESERVATION_STATUS.IN_WAITLIST)) {
                if (reservation.getStatus()==RESERVATION_STATUS.IN_WAITLIST) {
                    ReservationBoundary.print("Guest must pay now! Checking in...");
                }
                reservation.setStatus(RESERVATION_STATUS.CHECKED_IN);
                for (int i=0; i<rooms.length;i++) {
                    roomControl.updateStatus(rooms[i], 2);
                }

            } else if(dateNow.after(twoHoursLate)) {
                ReservationBoundary.print("Reservation expired...");
                reservation.setStatus(RESERVATION_STATUS.EXPIRED);
                for (int i=0; i<rooms.length;i++) {
                    roomControl.updateStatus(rooms[i], 1);
                }
            } else {
                for (int i=0; i<rooms.length;i++) {
                    roomControl.updateStatus(rooms[i], 1);
                }
            }
        }
        else {
            ReservationBoundary.print("Walk-In guest.");
            createReservation(true);
        }
    }

    public void removeReservation(Reservation reservation) {
        dao.removeReservation(reservation);
    }

    private boolean validateReservation (String givenId){
        Reservation reservationMatchingId = getReservation(givenId);

        if (reservationMatchingId == null || givenId.isEmpty()) {
            return false;
        }
        return true;
    }
}