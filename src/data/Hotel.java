package data;

// java api
import java.io.Serializable;
import java.util.ArrayList;
// original package
import menuitem.MenuItem;
import order.Order;
import room.Room;

// should only include arraylist
public class Hotel implements Serializable {
	
	ArrayList<MenuItem> menuItems;
	ArrayList<Order> orders;
	ArrayList<Room> rooms;
	
	Hotel() {
		menuItems = new ArrayList<>();
		orders = new ArrayList<>();
		rooms = new ArrayList<>();
	}
	
	public ArrayList<MenuItem> getMenuItems() {
		return menuItems;
	}
	
	public ArrayList<Order> getOrders() {
		return orders;
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}
	
	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}

}
