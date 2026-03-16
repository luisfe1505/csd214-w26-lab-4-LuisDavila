package csd214.bookstore.pojos;

import java.util.Objects;
import java.util.Scanner;

public abstract class Mobile extends Product {
    private String color;
    private int storage;

    public Mobile() {
        super();
    }

    public Mobile(String productID, String name, double price, String color, int storage) {
        super();
        this.color = color;
        this.storage = storage;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    @Override
    public double getPrice() {
        return 0.0;
    }

    @Override
    public void sellItem() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mobile)) return false;
        Mobile mobile = (Mobile) o;
        return getStorage() == mobile.getStorage() && Objects.equals(getColor(), mobile.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColor(), getStorage());
    }

    @Override
    public String toString() {
        return "Mobile{" +
                "color='" + color + '\'' +
                ", storage=" + storage +
                "} " + super.toString();
    }
}