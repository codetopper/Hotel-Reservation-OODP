package Payment;

import java.util.Scanner;
import java.util.ArrayList;
import java.time.Period;
import java.time.DayOfWeek;
import java.time.temporal.ChronoField;

import order.ORDER_STATUS;
import order.Order;
import reservation.Reservation;

public class totalpayment {
	private double discount;
	private double serviceChargeRate;
	private double weekendRate;
	private  Reservation reserve;
	private  Scanner sc;
	
	//constructors
	public totalpayment(Reservation r, Scanner sc)
	{
		this.discount = 0.20;
		this.serviceChargeRate = 0.07;
		this.weekendRate = 1.20;
		this.reserve = r;
		this.sc = sc;
	}
	
	public double getDiscount() {
		return discount;
	}
	
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	public double getServiceChargeRate() {
		return serviceChargeRate;
	}
	
	public void setServiceChargeRate(double serviceChargeRate) {
		this.serviceChargeRate = serviceChargeRate;
	}
	
	public double getWeekendRate() {
		return weekendRate;
	}
	
	public void setWeekendRate(double weekendRate) {
		this.weekendRate = weekendRate;
	}
	
	//prints bill
	public void printBillInvoice() {
		System.out.println("\n Bill Invoice");
		System.out.println("Total Room Reservation Charges(Weekdays: "+ NumOfWeekdays() +", Weekends: "+ NumOfWeekends() +"): " + "$SGD" + TotalRoomCharge());
		if(reserve.getRoomServiceList().size() !=0) {
			System.out.println("Total Room Serivce Charges:");
			getRoomSerivicePriceList();
			System.out.printf("Total charge: + $SGD%.2f\n", reserve.getRoomServicePrice());
		}
		if(TotalDiscount() !=0) {
			System.out.printf("Discount: - $SGD.2f\n", TotalDiscount());
		}
			System.out.printf("Service Charge: + +SGD%.2f\n", TotalServiceCharge());
			System.out.printf("Total Bill: $SGD%.2f", TotalBill());	
		
	}
	
	// room service's order
	private void getRoomServicePriceList() {
		ArrayList<RoomService> rsList = reserve.getRoomServiceList();
		for (RoomService roomService : rsList ) {
			System.out.println("Room Service <"+ rsList.indexOf(roomService)+1 +">");
			
			//during payment, change all services to delivered.
			roomService.setStatus(ORDER_STATUS.DELIVERED);
			roomService.printOrder();
		}
	}
	
	//method for payment method
	
	private void paymentType() {
		
		System.out.println("How would you like to pay:");
		System.out.println("1. Cash");
		System.out.println("2. Credit Card");
		System.out.println("--------------------------");
		System.out.print("Enter option: ");
	}
	
	//method to choose payment type
	public void payment() {
		int Choice = -1;
		do
		{
			paymentType();
			Choice = validatedChoice(Choice, "Enter option: ");
			
			switch (Choice) {
			
			case 1:
				System.out.println("Payment Details:");
				System.out.println("Paid by: Cash");
				System.out.printf("Amount Paid: SGD$%.2f\n",TotalBill());
				System.out.println("Payment completed");
				break;
			
			case 2:
				System.out.println("Payment Details:");
				System.out.println("Paid by: Credit Card");
				System.out.println("Name: " + reserve.getGuest().getName());
				System.out.println("Card Number: " + reserve.getGuest().getCreditCard());
				System.out.println("Address: " + reserve.getGuest().getAddress());
				System.out.println("Payment completed");
				break;
			
			default:
				System.out.println("Invalid option");
				break;
			}
			
		} while (Choice != 1 && Choice != 2);
		
	}
	
	// Error checking
	private int validatedChoice(int Vchoice, String inputText) {
		boolean valid = false;
		
		while(!valid) {
			if (!sc.hasNextInt()) {
				System.out.println("Invalid input. Please try again");
				sc.nextLine();	
				System.out.print(inputText);
			}
			else {
				valid = true;
				Vchoice = sc.nextInt();
				sc.nextLine();
				}
		}
		return Vchoice;
	}
	
	//method to calculate number weekdays user spent during stay duration
	
	private int NumOfWeekdays() {
		int weekDays =0;
		Period duration = Period.between(reserve.getCheckInDate(), reserve.getCheckOutDate());
		int totalDuration = duration.getDays();
		for(int i=0; i<totalDuration; i++) {
			DayOfWeek day = DayOfWeek.of((reserve.getCheckInDate().plusDays(i).get(ChronoField.DAY_OF_WEEK)));
			if(day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY)
			{
				weekDays++;
			}
		}
		return weekDays;
	}
	
	//method to calculate number weekends user spent during stay duration

	private int NumOfWeekends() {
		
		int weekEnds =0;
		Period duration = Period.between(reserve.getCheckInDate(), reserve.getCheckOutDate());
		int totalDuration = duration.getDays();
		for(int i=0; i<totalDuration; i++) {
			DayOfWeek day = DayOfWeek.of((reserve.getCheckInDate().plusDays(i).get(ChronoField.DAY_OF_WEEK)));
			if(day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY)
			{
				weekEnds++;
			}
		}
		return weekEnds;
		
	}
	
	// Method to calculate total room charge
	
	private double TotalRoomCharge() {
		double total = 0;
		Period duration = Period.between(reserve.getCheckInDate(), reserve.getCheckOutDate());
		int totalDuration = duration.getDays();
		for(int i=0; i<totalDuration;i++) {
			DayOfWeek day = DayOfWeek.of((reserve.getCheckInDate().plusDays(i).get(ChronoField.DAY_OF_WEEK)));
			if(day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY){
				total += reserve.getRoom().getRate() * weekendRate;
			}
			else {
				total += reserve.getRooms().getRate();
				}
	
		}
		return total;
}
	
	//method to calculate total discount
		private double TotalDiscount() {
			return (TotalRoomCharge() + reserve.getRoomServicePrice() ) * discount;
		}
		
	//method to calculate total service charge
	private double TotalServiceCharge() {
		return (TotalRoomCharge() + reserve.getRoomServicePrice() - TotalDiscount()) * serviceChargeRate;
	}
		
	//method to calculate total bill after discount and GST
	private double TotalBill() {
		return (TotalRoomCharge() + reserve.getRoomServicePrice() + TotalServiceCharge() - TotalDiscount());
	}
	
	
	
}
