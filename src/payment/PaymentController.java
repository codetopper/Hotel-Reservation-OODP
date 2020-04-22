package payment;

import order.ORDER_STATUS;
import order.Order;
import order.OrderControl;
import order.OrderDAO;
import reservation.Reservation;
import room.Room;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class PaymentController {

    private double discount;
    private double serviceChargeRate;
    private double weekendRate;
    private Scanner sc;
    OrderDAO orderDAO = new OrderDAO();

    //constructors
    public PaymentController()
    {
        this.discount = 0.20;
        this.serviceChargeRate = 0.07;
        this.weekendRate = 1.20;
        sc = new Scanner(System.in);
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
    public void printBillInvoice(Reservation reserve) {
        ArrayList<Order> allOrders = orderDAO.getAllItem();
        ArrayList<Order> orders = new ArrayList<>();
        for (Order order: allOrders) {
            if (order.getReservationId().equals(reserve.getId()) && order.getStatus().equals(ORDER_STATUS.DELIVERED)) {
                orders.add(order);
            }
        }
        System.out.println("\n[Bill Invoice]");
        System.out.println("Total Room Reservation Charges(Weekdays: "+ NumOfWeekdays(reserve) +", Weekends: "+ NumOfWeekends(reserve) +"): " + "SGD" + TotalRoomCharge(reserve));
        getRoomServicePriceList(orders);
        System.out.printf("Total Room Serivce Charges: SGD%.2f\n", getRoomServicePrice(orders));
        if(TotalDiscount(reserve, orders) !=0) {
            System.out.printf("Discount: - SGD%.2f\n", TotalDiscount(reserve, orders));
        }
        System.out.printf("Service Charge: SGD%.2f\n", TotalServiceCharge(reserve, orders));
        System.out.printf("Total Bill: SGD%.2f", TotalBill(reserve, orders));
    }

    public void printRoomInvoice(Reservation reserve) {
        System.out.println("Total Room Reservation Charges(Weekdays: "+ NumOfWeekdays(reserve) +", Weekends: "+ NumOfWeekends(reserve) +"): " + "SGD" + TotalRoomCharge(reserve));
    }

    // room service's order
    void getRoomServicePriceList(ArrayList<Order> orders) {
        System.out.println("Room Service:");
        int count = 1;
        if (orders.isEmpty()) {
            System.out.println("No orders.");
            return;
        }
        for (Order order: orders) {
            System.out.println(count++ + ". " + order.getMenuItem().getName() + ": " + order.getQuantity() + " x SGD" + order.getMenuItem().getPrice() + " = SGD" + order.getQuantity()*order.getMenuItem().getPrice());
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
    public void payment(Reservation reserve) {
        int Choice = -1;
        do
        {
            paymentType();
            Choice = validatedChoice(Choice, "Enter option: ");

            switch (Choice) {

                case 1:
                    System.out.println("Payment Details:");
                    System.out.println("Paid by: Cash");
                    System.out.println("Payment completed");
                    printBillInvoice(reserve);
                    break;

                case 2:
                    System.out.println("Payment Details:");
                    System.out.println("Paid by: Credit Card");
                    System.out.println("Name: " + reserve.getGuest().getName());
                    System.out.println("Card Number: " + reserve.getGuest().getCreditCard());
                    System.out.println("Address: " + reserve.getGuest().getAddress());
                    System.out.println("Payment completed");
                    printBillInvoice(reserve);
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

    private int NumOfWeekdays(Reservation reserve) {
        int weekDays =0;
        long duration = Math.abs(reserve.getCheckInDate().getTime() - reserve.getCheckOutDate().getTime());
        int diff = (int) TimeUnit.DAYS.convert(duration, TimeUnit.MILLISECONDS);
        for(int i=0; i<diff; i++) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(reserve.getCheckInDate());
            cal.add(Calendar.DATE, i);
            DayOfWeek day = DayOfWeek.of((cal.get(Calendar.DAY_OF_WEEK)));
            if(day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY)
            {
                weekDays++;
            }
        }
        return weekDays;
    }

    //method to calculate number weekends user spent during stay duration

    private int NumOfWeekends(Reservation reserve) {

        int weekEnds =0;
        long duration = Math.abs(reserve.getCheckInDate().getTime() - reserve.getCheckOutDate().getTime());
        int diff = (int) TimeUnit.DAYS.convert(duration, TimeUnit.MILLISECONDS);
        for(int i=0; i<diff; i++) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(reserve.getCheckInDate());
            cal.add(Calendar.DATE, i);
            DayOfWeek day = DayOfWeek.of((cal.get(Calendar.DAY_OF_WEEK)));
            if(day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY)
            {
                weekEnds++;
            }
        }
        return weekEnds;

    }

    // Method to calculate total room charge

    private double TotalRoomCharge(Reservation reserve) {
        double total = 0;
        long duration = Math.abs(reserve.getCheckInDate().getTime() - reserve.getCheckOutDate().getTime());
        int diff = (int) TimeUnit.DAYS.convert(duration, TimeUnit.MILLISECONDS);
        for (Room room: reserve.getRooms()) {
            for (int i = 0; i < diff; i++) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(reserve.getCheckInDate());
                cal.add(Calendar.DATE, i);
                DayOfWeek day = DayOfWeek.of((cal.get(Calendar.DAY_OF_WEEK)));
                if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
                    total += room.getPrice() * weekendRate;
                } else {
                    total += room.getPrice();
                }
            }
        }
        return total;
    }

    //method to calculate total discount
    private double TotalDiscount(Reservation reserve, ArrayList<Order> orders) {
        return (TotalRoomCharge(reserve) + getRoomServicePrice(orders) ) * discount;
    }

    //method to calculate total service charge
    private double TotalServiceCharge(Reservation reserve, ArrayList<Order> orders) {
        return (TotalRoomCharge(reserve) + getRoomServicePrice(orders) - TotalDiscount(reserve, orders)) * serviceChargeRate;
    }

    //method to calculate total bill after discount and GST
    private double TotalBill(Reservation reserve, ArrayList<Order> orders) {
        return (TotalRoomCharge(reserve) + getRoomServicePrice(orders) + TotalServiceCharge(reserve, orders) - TotalDiscount(reserve, orders));
    }

    double getRoomServicePrice(ArrayList<Order> orders) {
        double total = 0;
        for (Order order: orders) {
            total += order.getQuantity()*order.getMenuItem().getPrice();
        }
        return total;
    }
}
