package Payment;

public class Payment {

	private Reservation reservation;

	public Payment(Reservation reservation) {
		this.reservation = reservation;
	}
	
	public Reservation getReservation() {
		return reservation;
	}
	
	public double getRoomServiceTotal() {
		RoomService rsList[] = new Roomservice().findAll("reservationId", getReservation().getReservationCode());
		
		double total = 0.0;
		for (RoomService rs : rsList)
			total += rs.getTotal();
		
		return total;
	}
	
	public double getRoomTotal() {
		Room r = getReservation().getRoom();
		Date checkIn = getReservation().getCheckInDate();
		Date checkOut = getReservation().getCheckOutDate();
		
		int totalOfDays = DateManager.getTotalDays(checkIn, checkOut);
		int totalOfWeekends = DateManager.getTotalWeekends(checkIn, checkOut);
		
		double weekdayTotal = (totalOfDays - totalOfWeekends) * r.getRoomRate();
		double weekendTotal = totalOfWeekends * r.getRoomWeekendRate();
		
		return weekdayTotal + weekendTotal;
	}
	
	
	
}
