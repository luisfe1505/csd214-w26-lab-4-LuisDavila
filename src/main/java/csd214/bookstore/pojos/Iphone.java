package csd214.bookstore.pojos;

import java.util.Objects;
import java.util.Scanner;

public class Iphone extends Mobile {
    private boolean hasMagSafe;

    public Iphone() {
        super();
    }

    public Iphone(String productID, String name, double price, String color, int storage, boolean hasMagSafe) {
        super(productID, name, price, color, storage);
        this.hasMagSafe = hasMagSafe;
    }

    @Override
    public void initialize(Scanner input) {
        System.out.println("Enter Color: ");
        setColor(getInput(input, "Default Color"));
        System.out.println("Enter Storage (GB): ");
        setStorage(getInput(input, 0));
        System.out.println("Has MagSafe (true/false): ");
        this.hasMagSafe = getInput(input, false);
    }

    @Override
    public void edit(Scanner input) {
        System.out.println("Enter Color: ");
        setColor(getInput(input, getColor()));
        System.out.println("Enter Storage (GB): ");
        setStorage(getInput(input, getStorage()));
        System.out.println("Has MagSafe (true/false): ");
        this.hasMagSafe = getInput(input, this.hasMagSafe);
    }

    public boolean isHasMagSafe() {
        return hasMagSafe;
    }

    public void setHasMagSafe(boolean hasMagSafe) {
        this.hasMagSafe = hasMagSafe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Iphone)) return false;
        if (!super.equals(o)) return false;
        Iphone iphone = (Iphone) o;
        return isHasMagSafe() == iphone.isHasMagSafe();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isHasMagSafe());
    }

    @Override
    public String toString() {
        return "Iphone{" +
                "hasMagSafe=" + hasMagSafe +
                "} " + super.toString();
    }
}