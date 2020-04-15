package data;

// java api
import java.io.Serializable;
import java.util.ArrayList;
// original package
import menuitem.MenuItem;
import room.BED_TYPE;
import room.ROOM_TYPE;
import room.Room;
import room.RoomControl;

public class Hotel implements Serializable {
	
	ArrayList<MenuItem> menuItems;
	ArrayList<Room> rooms;
	RoomControl roomControl = new RoomControl();
	
	Hotel() {
		menuItems = new ArrayList<>();
		rooms = roomControl.createRooms();
	}
	
	public ArrayList<MenuItem> getMenuItems() {
		return menuItems;
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}

	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}

}
