package hotel;

import java.io.Serializable;

import menuitem.MenuItemControl;
import room.RoomControl;
import roomserviceorder.RoomServiceOrderControl;

public class Hotel implements Serializable {
	
	private RoomControl roomControl;
	private MenuItemControl menuItemControl;
	private RoomServiceOrderControl roomServiceOrderControl;
	

    public Hotel() {
    	roomControl = new RoomControl();
    	menuItemControl = new MenuItemControl();
    	roomServiceOrderControl = new RoomServiceOrderControl();
    }
    
    public RoomControl getRoomControl() {
    	return roomControl;
    }
    
    public MenuItemControl getMenuItemControl() {
    	return menuItemControl;
    }
    
    public RoomServiceOrderControl getRoomServiceOrderControl() {
    	return roomServiceOrderControl;
    }
}
