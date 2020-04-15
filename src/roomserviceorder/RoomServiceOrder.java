/*
// for reference

package roomserviceorder;

import java.io.Serializable;
import java.util.Date;

import menuitem.MenuItem;

enum ORDER_STATUS {CONFIRMED, PREPARING, DELIVERED};

public class RoomServiceOrder implements Serializable {
	
	private static int ClassId = 0;
	
	// attributes
	private String id;
	private MenuItem menuItem;
	private String date;
	private String remarks;
	private ORDER_STATUS status;
	
	// constructors
	public RoomServiceOrder(MenuItem menuItem, String remarks) {
		this.id = String.valueOf(nextId());
		
		Date date = new Date();
		this.date = date.toString();

		this.menuItem = menuItem;
		this.remarks = remarks;
		this.status = ORDER_STATUS.CONFIRMED;
	}
	
	// implementations
	private static int nextId() {
		ClassId++;
		return ClassId;
	}
	
	// interfaces
	public String getId() {
		return id;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public void setStatus(ORDER_STATUS status) {
		this.status = status;
	}
	
	// for testing purpose
	public String toString() {
		return String.format("%s,\t%s,\t%s,\t%s,\t%s", id, menuItem.toString(), date, remarks, String.valueOf(status));
	}

}
*/