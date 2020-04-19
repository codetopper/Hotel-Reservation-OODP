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
import room.ROOM_STATUS;
import room.ROOM_TYPE;
import room.RoomControl;
import room.RoomDAO;

public class ReservationControl {
	
	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy-HHmm");
	
	private ReservationDAO dao = new ReservationDAO();
	private RoomDAO roomDao = new RoomDAO();
	// Initialize attributes
	RoomControl roomControl = new RoomControl();
	GuestControl guestControl = new GuestControl();
	Scanner scanner = AppBoundary.scanner;
	
	public void printReservation(Reservation reservation) {
		System.out.println("=====Your reservation:");
		System.out.println("Reservation id: "+ reservation.getId());
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
		System.out.println("Your room ID's: " + Arrays.toString(reservation.getRooms()));
		System.out.println("=====");
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
		System.out.println("Guest information\n1) Create new\n2) Existing guest");
		int guestOption = AppBoundary.inIntInRange("Option: ", 1, 2);
		if (guestOption==1) {
			System.out.println("Create Guest details");
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
			System.out.print("Please enter the credit card number:");
			String card = scanner.nextLine();
			/*takes input for the country*/
			System.out.print("Please enter the country: ");
			String country = scanner.nextLine();
			/*takes input for the nationality*/
			System.out.print("Please enter the nationality: ");
			String nationality = scanner.nextLine();
			/*takes input for the gender*/
			System.out.print("Please select a gender-(1)male (2)female (3)others: ");
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
			guest = new Guest(identity, name, card, addr, country, gender, nationality, contact);
		} else if (guestOption==2) {
			System.out.println("Enter guest identity: ");
			String guestId = scanner.nextLine();
			guest = guestControl.searchGuest2(guestId);
		}
		
		System.out.println("Number of adults: ");
		numAdults = scanner.nextInt();
		
		System.out.println("Number of children: ");
		numChildren = scanner.nextInt();
		
		int totalPeople = numAdults + numChildren;
		Scanner sc = new Scanner(System.in);
		System.out.println(roomControl.getByStatus());
		int roomNr =1;
		int i;
		String[] rooms = new String[(int) Math.ceil(totalPeople/2.0)];
		String available="";
		for(i=totalPeople;i>1;i-=2) {
    		do {
    			System.out.println("Room id for couple "+roomNr+": ");
    			roomId = sc.nextLine();
    			available = roomControl.getAvailabilityByRoomId(roomId);
    		} while(!available.equals("Room is: VACANT\n")&& roomDao.getItemById(roomId).getRoomType() != ROOM_TYPE.SINGLE);
    		roomControl.updateStatus(roomId, 3);
    		rooms[roomNr-1]=roomId;
    		roomNr++;
		}
		if(i==1) {
			System.out.println("Room id for last person: ");
			roomId = sc.nextLine();
			System.out.println(roomId);
			while (roomDao.getItemById(roomId).getStatus() != ROOM_STATUS.VACANT) {
    			System.out.println("Room id for last person "+roomNr+":");
    			roomId = scanner.nextLine();
    			available = roomControl.getAvailabilityByRoomId(roomId);
    		}
			roomControl.updateStatus(roomId, 3);
			rooms[rooms.length-1]=roomId;
		}
		
		
		System.out.println("Choose billing:\n1. Creditcard\n2. Cash\n");
		billing = AppBoundary.inIntInRange("Option: ", 1, 2);
		checkInDate = new Date();
		boolean checkIn = false;
		if(!isWalkIn) {
			do {
				System.out.print("CheckIn (dd/MM/yyyy):");
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
			System.out.println("CheckIn time is from 15:00");
		}
		
		checkIn = false;
		
		do {
			System.out.println("CheckOut (dd/MM/yyyy):");
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
		System.out.println("CheckOut time is until 12:00 noon");
		
		RESERVATION_STATUS status = RESERVATION_STATUS.IN_WAITLIST;
		if(!isWalkIn) {
			System.out.println("Does client pay now?\n1)Yes\n2)No");
			int payNow = AppBoundary.inIntInRange("Option: ", 1, 2);
			if (payNow==1) {
				status = RESERVATION_STATUS.CONFIRMED;
			}
		}else {
			System.out.println("As this is walk-in, we require immediate payment.");
			status = RESERVATION_STATUS.CHECKED_IN;
			for (int l=0; l<rooms.length;l++) {
				roomControl.updateStatus(rooms[l], 2);
			}
		}
		
		
		
		Reservation reservation = new Reservation(guest, id, numAdults, numChildren, status, billing, checkInDate, checkOutDate, rooms);
		// GET ROOM PRICE!
		System.out.println("Reservation made.");
		printReservation(reservation);
	}
	

	public void updateReservation() {
		Date checkOutDate = null;
		Date checkInDate = null;
		System.out.println("Please enter reservation number: ");
		String reservationId = scanner.nextLine();
		if(validateReservation(reservationId)) {
			Reservation reservation = getReservation(reservationId);
			int choice=-1;
			int number;
			boolean checkIn = false;
			while (choice != 0) {
				System.out.println("Please select the option you want to update:");
				System.out.println("0) Exit");
				System.out.println("1) Number of adults");
				System.out.println("2) Number of children");
				System.out.println("3) Room number");
				System.out.println("4) CheckIn Date");
				System.out.println("5) CheckOut Date");
				System.out.println("6) Reservation status");
				int input = AppBoundary.inIntInRange("Option: ", 0, 6);
				
				switch(input) {
				case 1:
					System.out.println("Enter number of adults: ");
					number = scanner.nextInt();
					reservation.setNumAdults(number);
				case 2:
					System.out.println("Enter number of children: ");
					number = scanner.nextInt();
					reservation.setNumChildren(number);
				case 3:
					String newRoomId, oldRoomId;
					String[] rooms = reservation.getRooms();
					do {
						System.out.println("Old room id: ");
						// change room to vacant
						oldRoomId = scanner.nextLine();
						System.out.println("New room id: ");
						newRoomId = scanner.nextLine();
					} while(roomDao.getItemById(newRoomId).getStatus()!=ROOM_STATUS.VACANT && !Arrays.asList(rooms).contains(oldRoomId));
					
					rooms[Arrays.binarySearch(rooms, oldRoomId)] = newRoomId;
					reservation.setRooms(rooms);
					roomControl.updateStatus(oldRoomId, 1);
					roomControl.updateStatus(newRoomId, 3);
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
				case 6:
					System.out.println("Status 1) CONFIRMED 2) IN_WAITLIST 3) CHECKED_IN 4) CHECKED_OUT 5) EXPIRED");
					int next = AppBoundary.inIntInRange("Option: ", 1, 5);
					RESERVATION_STATUS arr[] = RESERVATION_STATUS.values();
					reservation.setStatus(arr[next]);
				}
		}
		}
	}
	
	
	
	public void checkIn() {
		System.out.println("CheckIn\n1) Reservation\n2)Walk-in");
		int choice = AppBoundary.inIntInRange("Option: ", 1, 2);
		if(choice==1) {
			System.out.println("Reservation Id");
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
					System.out.println("Guest must pay now! Checking in...");
				}
				reservation.setStatus(RESERVATION_STATUS.CHECKED_IN);
				for (int i=0; i<rooms.length;i++) {
					roomControl.updateStatus(rooms[i], 2);
				}
				
			} else if(dateNow.after(twoHoursLate)) {
				System.out.println("Reservation expired...");
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
			System.out.println("You are walk-in, thus you checkIn immediately");
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
