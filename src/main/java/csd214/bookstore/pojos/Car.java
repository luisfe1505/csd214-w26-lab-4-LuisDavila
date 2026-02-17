package csd214.bookstore.pojos;

import java.util.Objects;
import java.util.Scanner;

// Subclass for Cars
public class Car extends Vehicle {
    private int numDoors;
    private String fuelType;
    public Car() {
    }
    public Car(String id, String name, double price, String make, String model, int year, int mileage, int numDoors, String fuelType) {
        super(id, name, price, make, model, year, mileage);
        this.numDoors = numDoors;
        this.fuelType = fuelType;
    }
    @Override
    public void initialize(Scanner input) {
        System.out.println("Enter Number of doors: ");
        this.numDoors = getInput(input, 0);
        System.out.println("Enter Fuel Type: ");
        this.fuelType = getInput(input, "no fuel type");
        super.initialize(input);

    }
    @Override
    public void edit(Scanner input) {
        System.out.println("Enter Number of doors: ");
        this.numDoors = getInput(input, getNumDoors());
        System.out.println("Enter Fuel Type: ");
        this.fuelType = getInput(input, getFuelType());
        super.edit(input);

    }

    @Override
    public String toString() {
        return "Car{" +
                "numDoors=" + numDoors +
                ", fuelType='" + fuelType + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Car car)) return false;
        if (!super.equals(o)) return false;
        return getNumDoors() == car.getNumDoors() && Objects.equals(getFuelType(), car.getFuelType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNumDoors(), getFuelType());
    }

    public int getNumDoors() {
        return numDoors;
    }

    public void setNumDoors(int numDoors) {
        this.numDoors = numDoors;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

}

