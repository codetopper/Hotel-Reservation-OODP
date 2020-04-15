package app;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import hotel.Hotel;

public class DataController {

    public Hotel loadHotel() {
    	Hotel hotel = (Hotel) deserializeObject("hotel.ser");
    	
        if (hotel == null) {
            hotel = new Hotel();
            System.out.println("You have no existing hotels, a new one has been created!");
        } else {
        	System.out.println("Your hotel information is loaded!");
        }

        return hotel;
    }

    public void serializeObject(Object objectToBeSaved, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(objectToBeSaved);
            System.out.println("Successfully Saved: " + filename);
        }
        catch (Exception ex) {
            //ex.printStackTrace();
            System.out.println("Failure to Save: " + filename);
        }
    }

    public Object deserializeObject(String filename) {
        Object returnObj;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            returnObj = ois.readObject();
            System.out.println("Successfully Loaded: " + filename);
            return returnObj;
        }
        catch (Exception ex) {
            //ex.printStackTrace();
            System.out.println("Failure to Load: " + filename);
            return null;
        }
    }
}
