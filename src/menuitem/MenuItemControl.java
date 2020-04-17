package menuitem;

// java apis
import java.util.ArrayList;

public class MenuItemControl {
	
	// load dao
	private MenuItemDAO dao = new MenuItemDAO();
	
	// interfaces
	public void create(String name, String description, double price) {
		MenuItem menuItem = new MenuItem(name, description, price);
		dao.add(menuItem);
	}
	
	public void update(String name, String description, double price) {
		MenuItem menuItem = new MenuItem(name, description, price);
		dao.update(menuItem);
	}
	
	public void remove(String name) {
		dao.removeByName(name);
	}
	
	public void displayList() {
		ArrayList<MenuItem> menuItems = dao.getAllItem();
		
		for (MenuItem menuItem : menuItems) {
			System.out.println(menuItem.toString());
		}
	}

	public void resetMenuItems() {
		dao.resetMenuItems();
	}
}
