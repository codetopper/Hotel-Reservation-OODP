package reservation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import app.AppBoundary;
import guest.Guest;
import guest.GuestControl;
import guest.GuestDAO;
import payment.PaymentController;
import room.*;

public class ReservationControl {
	
	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy-HHmm");
	
	private ReservationDAO dao = new ReservationDAO();
	private RoomDAO roomDao = new RoomDAO();
	// Initialize attributes
	RoomControl roomControl = new RoomControl();
	GuestControl guestController = new GuestControl();
	GuestDAO guestDAO = new GuestDAO();
	Scanner scanner = ReservationBoundary.scanner;
	
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
		ArrayList<Reservation> reservations = dao.getAllItem();
		for (Reservation reservation : reservations) {
			printReservation(reservation);
		}
		System.out.println();
		System.out.println("There are " + reservations.size() + " reservations.");
	}
	
	public void checkOut(String guestId) {
		PaymentController paymentController = new PaymentController();
		ArrayList<Reservation> reservations = dao.getAllItem();
		Reservation reservation = null;
		for (Reservation rsv: reservations) {
			try {
				if (rsv.getGuest().getId().equals(guestId)) {
					reservation = rsv;
				}
			}
			catch (Exception ex) {
				System.out.println();
				System.out.println("Guest " + guestId + " has no reservations.");
				return;
			}
		}
		if (reservation==null) {
			System.out.println();
			System.out.println("Guest " + guestId + " has no reservations.");
			return;
		}
		ArrayList<Room> rooms = reservation.getRooms();
		reservation.setStatus(RESERVATION_STATUS.CHECKED_OUT);
		for (int i=0; i<rooms.size();i++) {
			roomControl.updateStatus(rooms.get(i).getId(), 1);
		}
		dao.update(reservation);
		paymentController.payment(reservation);
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
				ArrayList<Room> rooms = reservation.getRooms();
				reservation.setStatus(RESERVATION_STATUS.EXPIRED);
    			for (int i=0; i<rooms.size();i++) {
    				roomControl.updateStatus(rooms.get(i).getId(), 1);
    			}
			} else if (reservation.getStatus()==RESERVATION_STATUS.CHECKED_IN && dateNow.after(reservation.getCheckOutDate())) {
				ArrayList<Room> rooms = reservation.getRooms();
				reservation.setStatus(RESERVATION_STATUS.CHECKED_OUT);
    			for (int i=0; i<rooms.size();i++) {
    				roomControl.updateStatus(rooms.get(i).getId(), 1);
    			}
			}
			dao.update(reservation);
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
		System.out.println("Guest information\n1) Create new\n2) Existing guest");
		int guestOption = AppBoundary.inIntInRange("Option: ", 1, 2);
		if (guestOption==2) {
			System.out.print("Enter guest identity: ");
			String guestId = scanner.nextLine();
			guest = guestDAO.getItemById(guestId);
			if (guest == null) {
				System.out.println();
				System.out.println("Guest ID " + guestId + " not found, please create a new one.\n");
				guestOption = 1;
			}
			else {
				System.out.println();
				System.out.println("Guest " + guestId + " found!");
				System.out.print(guest.print());
			}
		}
		if (guestOption==1) {
			boolean guestCreated = false;
			while(guestCreated == false) {
				System.out.print("Create Guest details\n");
				/*takes input for the identity*/
				System.out.print("Please enter the identity number: ");
				String identity = scanner.nextLine();
				/*takes input for the name*/
				System.out.print("Please enter the name: ");
				String name = scanner.nextLine();
				/*takes input for the address*/
				System.out.print("Please enter the address: ");
				String addr = scanner.nextLine();
				/*takes input for the contact number*/
				System.out.print("Please enter the contact number: ");
				String contact = scanner.nextLine();
				/*takes input for the credit card number*/
				System.out.print("Please enter the credit card number: ");
				String card = scanner.nextLine();
				/*takes input for the country*/
				System.out.print("Please enter the country: ");
				String country = scanner.nextLine();
				/*takes input for the nationality*/
				System.out.print("Please enter the nationality: ");
				String nationality = scanner.nextLine();
				/*takes input for the gender*/
				System.out.println("Please select a gender-(1)male (2)female (3)others ");
				int genderOption = AppBoundary.inIntInRange("Option: ", 1, 3);
				String gender="Others";
				if(genderOption==1)
				{
					gender = "Male";
				}
				else if (genderOption==2)
				{
					gender = "Female";
				}
				System.out.println();
				String print = guestController.createGuest(identity, name, addr, contact, card, country, nationality, genderOption);
				guest = new Guest (identity, name, addr, contact, card, country, nationality, gender);
				System.out.println(print);
				System.out.println();
				if (print.equals("Guest already exists.") || print.equals("Guest is created!")) {
					guestCreated = true;
				}
			}
		}

		System.out.print("Number of adults: ");
		numAdults = scanner.nextInt();
		
		System.out.print("Number of children: ");
		numChildren = scanner.nextInt();
		
		int totalPeople = numAdults + numChildren;
		System.out.println();
		System.out.println(roomControl.getAvailabilityByRoomType());
		System.out.println();
		scanner.nextLine();
		int roomNr = 1;
		ArrayList<Room> rooms = new ArrayList<>();
		String available = "";
		while(totalPeople>0) {
			do {
				System.out.print("Room "+roomNr+": ");
				roomId = scanner.nextLine();
				if (roomDao.getItemById(roomId)==null) {
					System.out.println("Room " + roomId + " does not exist or is not vacant. Please enter a Room ID displayed above.");
					continue;
				}
				available = roomDao.getItemById(roomId).getStatus().toString();
			} while(!available.equals("VACANT"));
			roomControl.updateStatus(roomId, 3);
			rooms.add(roomDao.getItemById(roomId));
			if (roomDao.getItemById(roomId).getRoomType() != ROOM_TYPE.SINGLE) {
				totalPeople -= 2;
			}
			else {
				totalPeople -= 1;
			}
			roomNr++;
		}

		System.out.println();
		System.out.println("Choose billing:\n1. Credit Card\n2. Cash");
		billing = AppBoundary.inIntInRange("Option: ", 1, 2);
		checkInDate = new Date();
		boolean checkIn = false;
		System.out.println();
		if(!isWalkIn) {
			do {
				System.out.print("Check In (dd/MM/yyyy): ");
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
			System.out.println("Check In time is from 15:00.");
		}
		
		checkIn = false;
		
		do {
			System.out.print("Check Out (dd/MM/yyyy): ");
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
		System.out.println("Check Out time is until 12:00 noon.");
		System.out.println();

		RESERVATION_STATUS status = RESERVATION_STATUS.IN_WAITLIST;
		if(!isWalkIn) {
			System.out.println("Does client pay now?\n1)Yes\n2)No");
			int payNow = AppBoundary.inIntInRange("Option: ", 1, 2);
			if (payNow==1) {
				status = RESERVATION_STATUS.CONFIRMED;
			}
		}else {
			System.out.print("As this is walk-in, we require immediate payment.");
			status = RESERVATION_STATUS.CHECKED_IN;
			for (int l=0; l<rooms.size();l++) {
				roomControl.updateStatus(rooms.get(l).getId(), 2);
			}
		}
		
		Reservation reservation = new Reservation(guest, id, numAdults, numChildren, status, billing, checkInDate, checkOutDate, rooms);
		// GET ROOM PRICE!
		dao.add(reservation);
		System.out.print("Reservation made.");
		printReservation(reservation);
		PaymentController paymentController = new PaymentController();
		paymentController.printRoomInvoice(reservation);
	}
	

	public void updateReservation() {
		Date checkOutDate = null;
		Date checkInDate = null;
		System.out.print("Please enter reservation number: ");
		String reservationId = scanner.nextLine();
		if(validateReservation(reservationId)) {
			Reservation reservation = dao.getItemById(reservationId);
			int choice=-1;
			int number;
			boolean checkIn = false;
			while (choice != 0) {
				System.out.print("Please select the option you want to update:");
				System.out.print("0) Exit");
				System.out.print("1) Number of adults");
				System.out.print("2) Number of children");
				System.out.print("3) Room number");
				System.out.print("4) Check In Date");
				System.out.print("5) Check Out Date");
				System.out.print("6) Reservation status");
				int input = AppBoundary.inIntInRange("Option: ", 0, 6);
				
				switch(input) {
				case 1:
					System.out.print("Enter number of adults: ");
					number = scanner.nextInt();
					reservation.setNumAdults(number);
					break;
				case 2:
					System.out.print("Enter number of children: ");
					number = scanner.nextInt();
					reservation.setNumChildren(number);
					break;
				case 3:
					String newRoomId, oldRoomId;
					ArrayList<Room> rooms = reservation.getRooms();
					do {
						System.out.print("Old room id: ");
						// change room to vacant
						oldRoomId = scanner.nextLine();
						System.out.print("New room id: ");
						newRoomId = scanner.nextLine();
					} while(roomDao.getItemById(newRoomId).getStatus()!=ROOM_STATUS.VACANT && !Arrays.asList(rooms).contains(oldRoomId));

					rooms.set(rooms.indexOf(oldRoomId), roomDao.getItemById(newRoomId));
					reservation.setRooms(rooms);
					roomControl.updateStatus(oldRoomId, 1);
					roomControl.updateStatus(newRoomId, 3);
					break;
				case 4:
					do {
						System.out.print("CheckIn (dd/MM/yyyy):");
						
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
					break;
				case 5:
					
					do {
						System.out.print("CheckOut (dd/MM/yyyy):");
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
					break;
				case 6:
					System.out.print("Status 1) CONFIRMED 2) IN_WAITLIST 3) CHECKED_IN 4) CHECKED_OUT 5) EXPIRED");
					int next = AppBoundary.inIntInRange("Option: ", 1, 5);
					RESERVATION_STATUS arr[] = RESERVATION_STATUS.values();
					reservation.setStatus(arr[next]);
					break;
				}
			}
			dao.update(reservation);
		}
		System.out.println();
		System.out.println("Reservation ID " + reservationId + " does not exist.");
	}
	
	
	
	public void checkIn() {
		System.out.println("1) Reserved\n2) Walk-in");
		int choice = AppBoundary.inIntInRange("Option: ", 1, 2);
		if(choice==1) {
			System.out.print("Reservation Id: ");
			String reservationId = scanner.nextLine();
			if (!validateReservation(reservationId)) {
				System.out.println();
				System.out.println("Reservation Id " + reservationId + " does not exist.");
				return;
			}
			Reservation reservation = dao.getItemById(reservationId);
			
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
			if ((checkIn.equals(dateNow) || (checkIn.after(dateNow) && twoHoursLate.after(dateNow)))&& (reservation.getStatus()==RESERVATION_STATUS.CONFIRMED || reservation.getStatus()==RESERVATION_STATUS.IN_WAITLIST)) {
				if (reservation.getStatus()==RESERVATION_STATUS.IN_WAITLIST) {
					System.out.print("Guest must pay now! Checking in...");
				}
				reservation.setStatus(RESERVATION_STATUS.CHECKED_IN);
				for (int i=0; i<rooms.size();i++) {
					roomControl.updateStatus(rooms.get(i).getId(), 2);
				}
				
			} else if(dateNow.after(twoHoursLate)) {
				System.out.print("Reservation expired...");
				reservation.setStatus(RESERVATION_STATUS.EXPIRED);
				for (int i=0; i<rooms.size();i++) {
					roomControl.updateStatus(rooms.get(i).getId(), 1);
				}
			} else {
				for (int i=0; i<rooms.size();i++) {
					roomControl.updateStatus(rooms.get(i).getId(), 1);
				}
			}
			dao.update(reservation);
		}
		if (choice==2) {
			createReservation(true);
		}
	}
	
	public void removeReservation(Reservation reservation) {
		dao.removeReservation(reservation);
	}
	
	private boolean validateReservation (String givenId){
		Reservation reservationMatchingId = dao.getItemById(givenId);

		if (reservationMatchingId == null || givenId.isEmpty()) {
			return false;
		}
		return true;
	}
}
