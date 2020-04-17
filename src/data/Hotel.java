package data;

// java api
import java.io.Serializable;
import java.util.ArrayList;
// original package
import guest.Guest;
import menuitem.MenuItem;
import order.Order;
import room.Room;

// should only include arraylist
public class Hotel implements Serializable {
	
	ArrayList<MenuItem> menuItems;
	ArrayList<Order> orders;
	ArrayList<Room> rooms;
	ArrayList<Guest> guests;

	Hotel() {
		menuItems = new ArrayList<>();
		orders = new ArrayList<>();
		rooms = new ArrayList<>();
		guests = new ArrayList<>();
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

	public ArrayList<Guest> getGuests() {
		return guests;
	}

	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}

	public void setMenuItems(ArrayList<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}

	public void setGuests(ArrayList<Guest> guests) {
		this.guests = guests;
	}

}
