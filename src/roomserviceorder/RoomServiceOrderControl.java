package roomserviceorder;

import java.io.Serializable;
import java.util.ArrayList;

import menuitem.MenuItem;

public class RoomServiceOrderControl implements Serializable {

	// attributes
	private ArrayList<RoomServiceOrder> orders;
	
	// constructors
	public RoomServiceOrderControl() {
		orders = new ArrayList<>();
	}
	
	// implementation
	private RoomServiceOrder orderMatchingId(String id) {
		RoomServiceOrder orderMatchingId = null;
		
		for (RoomServiceOrder order : orders) {
			if (order.getId().equals(id)) {
				orderMatchingId = order;
				break;
			}
		}
		
		return orderMatchingId;
	}
	
	// interfaces
	public void create(MenuItem menuItem, String remarks) {
		if (menuItem == null) {
			System.out.println("Cannot find menu item matching name.");
			return;
		}
		
		RoomServiceOrder order = new RoomServiceOrder(menuItem, remarks);
		orders.add(order);
	}
	
	public void update(String id, String remarks, int status) {
		RoomServiceOrder orderMatchingId = orderMatchingId(id);
		
		if (orderMatchingId == null) {
			System.out.println("Cannot find room service order matching id.");
			return;
		}
		
		orderMatchingId.setRemarks(remarks);
		
		switch(status) {
			case 1:
				orderMatchingId.setStatus(ORDER_STATUS.CONFIRMED);
				break;
			case 2:
				orderMatchingId.setStatus(ORDER_STATUS.PREPARING);
				break;
			case 3:
				orderMatchingId.setStatus(ORDER_STATUS.DELIVERED);
				break;
		}
	}
	
	public void remove(String id) {
		RoomServiceOrder orderMatchingId = orderMatchingId(id);
		
		if (orderMatchingId == null) {
			System.out.println("Cannot find room service order matching id.");
			return;
		}
		
		orders.remove(orderMatchingId);
	}
	
	// for testing purpose
	public void displayList() {
		for (RoomServiceOrder order : orders) {
			System.out.println(order.toString());
		}
	}

}
