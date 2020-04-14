package menuitem;

// java api
import java.util.ArrayList;
// original package
import data.DataUtil;

public class MenuItemDAO {

	String filename = "menuitem.ser";

	// implementations
	/*
	 * for internal use
	 */
	private MenuItem getItemByName(ArrayList<MenuItem> menuItems, String name) {
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
		ArrayList<MenuItem> menuItems = (ArrayList<MenuItem>) DataUtil.deserializeObject(filename);

		if (menuItems == null) {
			menuItems = new ArrayList<>();
			DataUtil.serializeObject(menuItems, filename);
		}

		return menuItems;
	}

	/*
	 * for external use
	 */
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
		ArrayList<MenuItem> menuItems = getAllItem();
		MenuItem menuItemMatchingName = getItemByName(menuItems, menuItemInput.getName());

		if (!(menuItemMatchingName == null)) {
			System.out.println("Menu item already exists.");
			return;
		}

		menuItems.add(menuItemInput);
		DataUtil.serializeObject(menuItems, filename);
	}

	public void update(MenuItem menuItemInput) {
		ArrayList<MenuItem> menuItems = getAllItem();
		MenuItem menuItemMatchingName = getItemByName(menuItems, menuItemInput.getName());

		if (menuItemMatchingName == null) {
			System.out.println("Cannot find menu item matching name.");
			return;
		}

		menuItemMatchingName.setDescription(menuItemInput.getDescription());
		menuItemMatchingName.setPrice(menuItemInput.getPrice());
		DataUtil.serializeObject(menuItems, filename);
	}

	public void removeByName(String name) {
		ArrayList<MenuItem> menuItems = getAllItem();
		MenuItem menuItemMatchingName = getItemByName(menuItems, name);

		if (menuItemMatchingName == null) {
			System.out.println("Cannot find menu item matching name.");
			return;
		}

		menuItems.remove(menuItemMatchingName);
		DataUtil.serializeObject(menuItems, filename);
	}

}
