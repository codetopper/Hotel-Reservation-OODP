package data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DataUtil {
	
    public static void serializeObject(Object objectToBeSaved, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(objectToBeSaved);
            System.out.println("Successfully Saved: " + filename);
        }
        catch (Exception ex) {
            //ex.printStackTrace();
            System.out.println("Failure to Save: " + filename);
        }
    }

    public static Object deserializeObject(String filename) {
        Object returnObj;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            returnObj = ois.readObject();
            //System.out.println("Successfully Loaded: " + filename);
            return returnObj;
        }
        catch (Exception ex) {
            //ex.printStackTrace();
            System.out.println("Failure to Load: " + filename);
            return null;
        }
    }

}
