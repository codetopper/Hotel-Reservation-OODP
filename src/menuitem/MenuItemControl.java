package menuitem;

import java.io.Serializable;
import java.util.ArrayList;

public class MenuItemControl implements Serializable {
	
	// attributes
	private ArrayList<MenuItem> menuItems;
	
	// constructors
	public MenuItemControl() {
		menuItems = new ArrayList<>();
		
		// create default menuItems
		menuItems.add(new MenuItem("Morning Set", "A great way to start your morning.", 10.5));
		menuItems.add(new MenuItem("Lunch Set", "A great deal.", 5.0));
		menuItems.add(new MenuItem("Dinner Set", "A great way to end your day.", 16.5));
	}
	
	// implementations
	private MenuItem menuItemMatchingName(String name) {
		MenuItem menuItemMatchingName = null;
		
		for (MenuItem menuItem : menuItems) {
			if (menuItem.getName().equals(name)) {
				menuItemMatchingName = menuItem;
				break;
			}
		}
		
		return menuItemMatchingName;
	}
	
	// interfaces
	public void create(String name, String description, double price) {
		MenuItem menuItemMatchingName = menuItemMatchingName(name);
		
		if (!(menuItemMatchingName == null)) {
			System.out.println("Menu item already exists.");
			return;
		}
		
		MenuItem menuItem = new MenuItem(name, description, price);
		menuItems.add(menuItem);
	}
	
	public void update(String name, String description, double price) {
		MenuItem menuItemMatchingName = menuItemMatchingName(name);
		
		if (menuItemMatchingName == null) {
			System.out.println("Cannot find menu item matching name.");
			return;
		}
		
		menuItemMatchingName.setDescription(description);
		menuItemMatchingName.setPrice(price);
	}

	public void remove(String name) {
		MenuItem menuItemMatchingName = menuItemMatchingName(name);
		
		if (menuItemMatchingName == null) {
			System.out.println("Cannot find menu item matching name.");
			return;
		}
		
		menuItems.remove(menuItemMatchingName);
	}

	public void displayList() {
		for (MenuItem menuItem : menuItems) {
			System.out.println(menuItem.toString());
		}
	}
	
}
