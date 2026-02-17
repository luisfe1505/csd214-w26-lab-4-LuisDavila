package csd214.bookstore.pojos;

import java.util.Objects;
import java.util.Scanner;

public abstract class Stationery extends Product {
    private String brand;

    public Stationery(String brand, double price) {
        this.brand = brand;
        setPrice(price);
    }

    public Stationery() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public void initialize(Scanner input) {
        System.out.println("Enter Brand:");
        this.brand = getInput(input, "Generic");
        super.initialize(input);
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Stationery that)) return false;
        return Objects.equals(getBrand(), that.getBrand());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getBrand());
    }

    @Override
    public String toString() {
        return "Stationery{" +
                "brand='" + brand + '\'' +
                "} " + super.toString();
    }
}
