package csd214.bookstore.pojos;

import java.util.Scanner;

// Subclass for Trucks
/**
 * DTO for {@link csd214.bookstore.entities.TruckEntity}
 */
public class Truck extends Vehicle {
    private double towingCapacity;

    public Truck(String id, String name, double price, String make, String model, int year, int mileage, double towingCapacity) {
        super(id, name, price, make, model, year, mileage);
        this.towingCapacity = towingCapacity;
    }
    @Override
    public void initialize(Scanner input) {
        System.out.println("Enter towing capacity: ");
        this.towingCapacity = getInput(input, 0.0);
        super.initialize(input);

    }
    @Override
    public void edit(Scanner input) {
        System.out.println("Enter towing capacity: ");
        this.towingCapacity = getInput(input, getTowingCapacity());
        super.edit(input);

    }

    public Truck() {
    }

    public double getTowingCapacity() {
        return towingCapacity;
    }

    public void setTowingCapacity(double towingCapacity) {
        this.towingCapacity = towingCapacity;
    }
}