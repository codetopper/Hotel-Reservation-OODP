package menuitem;

import java.io.Serializable;

public class MenuItem implements Serializable {
	
	// attributes
	private String name;
	private String description;
	private double price;
	
	// constructors
	public MenuItem(String name, String description, double price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	// interfaces
	
	public String toString() {
		return String.format("%s,\t%s,\t%.2f", name, description, price);
	}
	
	// getters
	public String getName() {
		return name;
	}
	
	// setters
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

}
