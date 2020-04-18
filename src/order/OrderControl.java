package order;

// java apis
import java.util.ArrayList;
// original packages
import menuitem.MenuItem;
import menuitem.MenuItemDAO;

public class OrderControl {
	
	// load daos
	private OrderDAO orderDao = new OrderDAO();
	private MenuItemDAO menuItemDao = new MenuItemDAO();
	
	// interfaces
	public void create(String menuItemName, String remarks) {
		MenuItem menuItemMatchingName = menuItemDao.getItemByName(menuItemName);
		
		if (menuItemMatchingName == null) {
			System.out.println("Cannot find menu item matching name.");
			return;
		}
		
		Order order = new Order(menuItemMatchingName, remarks);
		orderDao.add(order);
	}
	
	public void update(String id, String remarks, int choice) {
		ORDER_STATUS status = ORDER_STATUS.CONFIRMED;
		Order orderMatchingId = orderDao.getItemById(id);
		
		if (orderMatchingId == null) {
			System.out.println("Cannot find order matching id.");
			return;
		}
		
		switch (choice) {
			case 1:
				status = ORDER_STATUS.CONFIRMED;
				break;
			case 2:
				status = ORDER_STATUS.PREPARING;
				break;
			case 3:
				status = ORDER_STATUS.DELIVERED;
				break;
		}
		
		orderMatchingId.setRemarks(remarks);
		orderMatchingId.setStatus(status);
		orderDao.update(orderMatchingId);
	}
	
	public void displayList() {
		ArrayList<Order> orders = orderDao.getAllItem();
		
		for (Order order : orders) {
			System.out.println(order.toString());
		}
	}

	public void resetOrders() {
		orderDao.resetOrders();
	}
	
}