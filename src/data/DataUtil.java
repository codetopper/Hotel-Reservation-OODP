package data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DataUtil {
	
	String file = "hotel.ser";
	
	// implementations
	private Object read() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			return ois.readObject();
		}
		catch (Exception e) {
			System.out.printf("Failure to load: %s\n", file);
			return null;
		}
	}
	
	// interfaces
	public Hotel readHotel() {
		Hotel hotel = (Hotel) read();
		if (hotel == null) {
			hotel = new Hotel();
			System.out.println("Created new hotel.");
			write(hotel);
		}
		return hotel;
	}

	public void resetHotel() {
		Hotel hotel = new Hotel();
		write(hotel);
		System.out.println("Hotel is reset!");
	}
	
	public void write(Object obj) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			oos.writeObject(obj);
			System.out.printf("Successfully saved: %s\n", file);
		}
		catch (Exception e) {
			System.out.printf("Failure to save: %s\n", file);
		}
	}

}
