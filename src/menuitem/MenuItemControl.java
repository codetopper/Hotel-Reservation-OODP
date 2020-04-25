package menuitem;

// java apis
import java.util.ArrayList;

public class MenuItemControl {
	
	// load dao
	private MenuItemDAO dao = new MenuItemDAO();
	
	// interfaces
	// create a menu item
	public void create(String name, String description, double price) {
		MenuItem menuItem = new MenuItem(name, description, price);
		dao.add(menuItem);
	}

	// updates the menu item
	public void update(String name, String newName, String description, Double price) {
		MenuItem oldMenuItem = dao.getItemByName(name);
		MenuItem newMenuItem = dao.getItemByName(name);
		if (!newName.isBlank()) {
			newMenuItem.setName(newName);
		}
		if (!description.isBlank()) {
			newMenuItem.setDescription(description);
		}
		if (price != null) {
			newMenuItem.setPrice(price);
		}
		dao.update(oldMenuItem, newMenuItem);
		System.out.println(newMenuItem.toString());
	}

	// remove a menu item
	public void remove(String name) {
		dao.removeByName(name);
	}

	//display all menu items
	public void displayList() {
		ArrayList<MenuItem> menuItems = dao.getAllItem();

		if (menuItems.isEmpty()) {
			System.out.println("There are no items.");
			return;
		}

		for (MenuItem menuItem : menuItems) {
			System.out.println(menuItem.toString());
		}
	}

	// validates that the menu item exists
	public boolean validateName(String name) {
		MenuItem menuItem = dao.getItemByName(name);
		if (menuItem == null) {
			return false;
		}
		return true;
	}
}
