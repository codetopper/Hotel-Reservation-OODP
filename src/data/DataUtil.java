package data;

import java.io.*;

public class DataUtil implements Serializable {
	
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
	
	public void write(Object obj) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			oos.writeObject(obj);
			System.out.printf("Successfully saved: %s\n", file);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.printf("Failure to save: %s\n", file);
		}
	}

}
