package hotel;

import java.io.Serializable;

import menuitem.MenuItemControl;
import room.RoomControl;

public class Hotel implements Serializable {
	
	public RoomControl roomControl;
	public MenuItemControl menuItemControl;

    public Hotel() {
    	roomControl = new RoomControl();
    	menuItemControl = new MenuItemControl();
    }
    
    public RoomControl getRoomControl() {
    	return roomControl;
    }
    
    public MenuItemControl getMenuItemControl() {
    	return menuItemControl;
    }
}
