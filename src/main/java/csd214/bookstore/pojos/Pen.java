package csd214.bookstore.pojos;

import java.util.Objects;
import java.util.Scanner;

public class Pen extends Stationery {
    private String color;

    public Pen() {
    }

    public Pen(String brand, double price, String color) {
        super(brand, price);
        this.color = color;
    }

    @Override
    public void initialize(Scanner input) {
        super.initialize(input);
        System.out.println("Enter color:");
        this.color = getInput(input, "White");
    }
    @Override
    public void edit(Scanner input) {
        System.out.println("Enter color:");
        this.color = getInput(input, getColor());
        super.initialize(input); // Critical: let Parent ask for Name/Price

    }


    public Pen(String color) {
        this.color = color;
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    @Override
    public void sellItem() {
        System.out.println("Selling ["+getColor()+"] Pen...");
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pen pen)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getColor(), pen.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getColor());
    }

    @Override
    public String toString() {
        return "Pen{" +
                "color='" + color + '\'' +
                "} " + super.toString();
    }


}
