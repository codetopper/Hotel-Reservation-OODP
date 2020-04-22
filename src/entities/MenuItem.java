package entities;

import java.io.Serializable;

public class MenuItem implements Serializable {

    private static final long serialVersionUID = 1L;
    // attributes
    private String name;
    private String description;
    private Double price;

    // constructors
    public MenuItem(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // interfaces
    public String toString() {
        return String.format("Name: %s\nDescription: %s\nUnit Price: SGD%.2f\n", name, description, price);
    }

    // getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    // setters
    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

}
