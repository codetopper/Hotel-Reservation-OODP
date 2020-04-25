package menuitem;

// java api
import java.util.ArrayList;
// original package
import data.DataUtil;
import data.Hotel;

public class MenuItemDAO {
	
	// load data access
	private DataUtil dataUtil = new DataUtil();

	// implementations
	private MenuItem getItemByName(ArrayList<MenuItem> menuItems, String name) { // for internal use
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
	public ArrayList<MenuItem> getAllItem() {
		Hotel hotel = dataUtil.readHotel();
		ArrayList<MenuItem> menuItems = hotel.getMenuItems();
		
		return menuItems;
	}
	
	public MenuItem getItemByName(String name) {
		ArrayList<MenuItem> menuItems = getAllItem();
		MenuItem menuItemMatchingName = null;

		for (MenuItem menuItem : menuItems) {
			if (menuItem.getName().equals(name)) {
				menuItemMatchingName = menuItem;
				break;
			}
		}

		return menuItemMatchingName;
	}

	public void add(MenuItem menuItemInput) {
		Hotel hotel = dataUtil.readHotel();
		ArrayList<MenuItem> menuItems = hotel.getMenuItems();
		MenuItem menuItemMatchingName = getItemByName(menuItems, menuItemInput.getName());

		if (!(menuItemMatchingName == null)) {
			System.out.println();
			System.out.println("Menu item already exists.");
			return;
		}

		menuItems.add(menuItemInput);
		dataUtil.write(hotel);
	}

	public void update(MenuItem menuItemInput, MenuItem menuItemUpdate) {
		Hotel hotel = dataUtil.readHotel();
		ArrayList<MenuItem> menuItems = hotel.getMenuItems();
		MenuItem menuItemMatchingName = getItemByName(menuItems, menuItemInput.getName());
		menuItems.set(menuItems.indexOf(menuItemMatchingName), menuItemUpdate);
		dataUtil.write(hotel);
	}

	public void removeByName(String name) {
		Hotel hotel = dataUtil.readHotel();
		ArrayList<MenuItem> menuItems = hotel.getMenuItems();
		MenuItem menuItemMatchingName = getItemByName(menuItems, name);

		if (menuItemMatchingName == null) {
			System.out.println("Cannot find menu item matching name.");
			return;
		}

		menuItems.remove(menuItemMatchingName);
		dataUtil.write(hotel);
	}

}
