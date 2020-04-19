package entity_classes;

// java apis
import java.io.Serializable;
import java.util.Date;
// original packages
import enums.ORDER_STATUS;

public class Order implements Serializable {
	
	private static int counter = 0;
	
	// attributes
	private String id;
	private MenuItem menuItem;
	private String date;
	private String remarks;
	private ORDER_STATUS status;
	
	// constructors
	public Order(MenuItem menuItem, String remarks) {
		this.id = String.valueOf(nextId());
		
		Date date = new Date();
		this.date = date.toString();

		this.menuItem = menuItem;
		this.remarks = remarks;
		this.status = ORDER_STATUS.CONFIRMED;
	}
	
	// implementations
	private static int nextId() {
		counter++;
		return counter;
	}
	
	// interfaces
	public String getId() {
		return id;
	}
	
	public String getRemarks() {
		return remarks;
	}
	
	public ORDER_STATUS getStatus() {
		return status;
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