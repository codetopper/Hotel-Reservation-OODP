package data;

// java api
import java.io.Serializable;
import java.util.ArrayList;
// original package
import menuitem.MenuItem;

public class Hotel implements Serializable {
	
	ArrayList<MenuItem> menuItems;
	
	public Hotel() {
		menuItems = new ArrayList<>();
	}
	
	public ArrayList<MenuItem> getMenuItems() {
		return menuItems;
	}
	
	public void setMenuItems(ArrayList<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

}
