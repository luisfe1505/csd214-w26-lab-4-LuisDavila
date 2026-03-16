package csd214.bookstore.pojos;

import java.util.Objects;
import java.util.Scanner;

public class Android extends Mobile {
    private boolean hasExpandableStorage;

    public Android() {
        super();
    }

    public Android(String productID, String name, double price, String color, int storage, boolean hasExpandableStorage) {
        super(productID, name, price, color, storage);
        this.hasExpandableStorage = hasExpandableStorage;
    }

    @Override
    public void initialize(Scanner input) {
        System.out.println("Enter Color: ");
        setColor(getInput(input, "Default Color"));
        System.out.println("Enter Storage (GB): ");
        setStorage(getInput(input, 0));
        System.out.println("Has Expandable Storage (true/false): ");
        this.hasExpandableStorage = getInput(input, false);
    }

    @Override
    public void edit(Scanner input) {
        System.out.println("Enter Color: ");
        setColor(getInput(input, getColor()));
        System.out.println("Enter Storage (GB): ");
        setStorage(getInput(input, getStorage()));
        System.out.println("Has Expandable Storage (true/false): ");
        this.hasExpandableStorage = getInput(input, this.hasExpandableStorage);
    }

    public boolean isHasExpandableStorage() {
        return hasExpandableStorage;
    }

    public void setHasExpandableStorage(boolean hasExpandableStorage) {
        this.hasExpandableStorage = hasExpandableStorage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Android)) return false;
        if (!super.equals(o)) return false;
        Android android = (Android) o;
        return isHasExpandableStorage() == android.isHasExpandableStorage();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isHasExpandableStorage());
    }

    @Override
    public String toString() {
        return "Android{" +
                "hasExpandableStorage=" + hasExpandableStorage +
                "} " + super.toString();
    }
}
