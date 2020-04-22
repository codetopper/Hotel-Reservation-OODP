package controls;

import data.DataController;
import entities.MenuItem;
import entities.Room;

import java.io.Serializable;
import java.util.ArrayList;

public class MenuItemController implements Serializable {

    private static final long serialVersionUID = 4L;
    public ArrayList<MenuItem> menuItems;
    private static MenuItemController thisInstance = null;

    public static MenuItemController getInstance() {

        if (thisInstance==null) // not already loaded
        {
            DataController dataController = new DataController();
            thisInstance = dataController.loadMenuItemController();

            if (thisInstance==null)
                thisInstance = new MenuItemController(); // loading failure; resets controller
        }
        return thisInstance;
    }

    private MenuItemController() {
        resetMenuItems();
    }

    // interfaces
    public void create(String name, String description, Double price) {
        MenuItem menuItem = new MenuItem(name, description, price);
        menuItems.add(menuItem);
    }

    public void update(String oldName, String newName, String description, Double price) {
        for (MenuItem menuItem: menuItems) {
            if (menuItem.getName().equals(oldName)) {
                if (!newName.isBlank()) {
                    menuItem.setName(newName);
                }
                if (!description.isBlank()) {
                    menuItem.setDescription(description);
                }
                if (price != null) {
                    menuItem.setPrice(price);
                }
                System.out.println();
                System.out.println("Item Updated: ");
                displayItem(menuItem.getName());
            }
        }
    }

    public void remove(String name) {
        menuItems.removeIf(menuItem -> menuItem.getName().equals(name));
    }

    public void displayList() {
        int count = 1;
        for (MenuItem menuItem : menuItems) {
            System.out.println("Item " + count++ +":");
            System.out.println(menuItem.toString());
        }
        System.out.println("There are " + menuItems.size() + " menu items.");
    }

    public void displayItem(String name) {
        for (MenuItem menuItem : menuItems) {
            if (menuItem.getName().equals(name)) {
                System.out.print(menuItem.toString());
                return;
            }
        }
        System.out.print(name + "does not exist in the list currently.");
    }

    public void resetMenuItems() {
        menuItems = new ArrayList<>();
    }

    public MenuItem getItemByName(String name) {
        MenuItem menuItemMatchingName = null;

        for (MenuItem menuItem : menuItems) {
            if (menuItem.getName().equals(name)) {
                menuItemMatchingName = menuItem;
                break;
            }
        }

        return menuItemMatchingName;
    }
}
