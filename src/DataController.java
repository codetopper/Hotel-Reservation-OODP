import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class DataController {

    static Scanner sc;

    public Hotel loadHotel() {
        Hotel hotel = (Hotel)deserializeObject("hotel.ser");
        if (hotel==null) {
            System.out.println("You have no hotels yet, please create a new hotel!");
            System.out.print("Please enter the number of VIP Suites: ");
            int vipQuantity = sc.nextInt();
            System.out.print("Please enter the number of Deluxe Rooms: ");
            int deluxeQuantity = sc.nextInt();
            System.out.print("Please enter the number of Double Rooms: ");
            int doubleQuantity = sc.nextInt();
            System.out.print("Please enter the number of Single Rooms: ");
            int singleQuantity = sc.nextInt();
            hotel = new Hotel(vipQuantity, deluxeQuantity, doubleQuantity,singleQuantity);
            System.out.println("Your new hotel is created!");
            return hotel;
        }
        System.out.println("Your hotel information is loaded!");
        return hotel;
    }

    public void serializeObject(Object objectToBeSaved, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(objectToBeSaved);
            System.out.println("Successfully Saved: " + filename);
        }
        catch (Exception ex) {
            System.out.println();
            ex.printStackTrace();
            System.out.println("Failure to Save: " + filename);
        }
    }

    public Object deserializeObject(String filename) {
        Object returnObj = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            returnObj = ois.readObject();
            System.out.println("Successfully Loaded: " + filename);
            return returnObj;
        }
        catch (Exception ex) {
            System.out.println();
            ex.printStackTrace();
            System.out.println("Failure to Load: " + filename);
            return null;
        }
    }
}
