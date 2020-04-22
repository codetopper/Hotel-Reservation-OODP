package order;

// java apis
import java.util.ArrayList;
// original packages
import menuitem.MenuItem;
import menuitem.MenuItemDAO;
import reservation.Reservation;
import reservation.ReservationDAO;
import room.ROOM_STATUS;
import room.RoomDAO;

public class OrderControl {
	
	// load daos
	private OrderDAO orderDao = new OrderDAO();
	private MenuItemDAO menuItemDao = new MenuItemDAO();
	private ReservationDAO reservationDAO = new ReservationDAO();
	private RoomDAO roomDAO = new RoomDAO();
	
	// interfaces
	public void create(String menuItemName, int quantity, String remarks, String roomId) {
		MenuItem menuItemMatchingName = menuItemDao.getItemByName(menuItemName);

		if (roomDAO.getItemById(roomId)==null) {
			System.out.println("Room " + roomId + " does not exist.");
			return;
		}

		if (menuItemMatchingName == null) {
			System.out.println("Cannot find menu item matching name.");
			return;
		}
		String reservationId = reservationDAO.getIdByRoom(roomId);

		if (roomDAO.getItemById(roomId).getStatus() == ROOM_STATUS.OCCUPIED) {
			Order order = new Order(menuItemMatchingName, quantity, remarks, roomId, reservationId);
			orderDao.add(order);
		}
		else {
			System.out.println("Room is not occupied!");
		}
	}
	
	public void update(String id, int choice) {
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
			case 4:
				status = ORDER_STATUS.CANCELLED;
				break;
		}

		orderMatchingId.setStatus(status);
		orderDao.update(orderMatchingId);
	}
	
	public void displayList() {
		ArrayList<Order> orders = orderDao.getAllItem();
		
		for (Order order : orders) {
			System.out.println(order.toString());
		}
	}
}