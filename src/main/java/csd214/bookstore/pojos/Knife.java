package csd214.bookstore.pojos;

import java.util.Objects;
import java.util.Scanner;

public class Knife extends Cutlery {
    private double bladeLengthCm;
    private boolean isSerrated; // True for steak knives, False for butter knives

    @Override
    public void sellItem() {

    }

    @Override
    public void initialize(Scanner input) {
        System.out.println("Enter blade length in cm: ");
        this.bladeLengthCm = getInput(input, 0);
        System.out.println("Is the balde serrated (true/false: ");
        this.isSerrated = getInput(input, false);

    }
    @Override
    public void edit(Scanner input) {
        System.out.println("Enter blade length in cm: ");
        this.bladeLengthCm = getInput(input, getBladeLengthCm());
        System.out.println("Is the balde serrated (true/false: ");
        this.isSerrated = getInput(input, isSerrated());

    }
    public Knife(String material, double bladeLengthCm, boolean isSerrated) {
        super(material);
        this.bladeLengthCm = bladeLengthCm;
        this.isSerrated = isSerrated;
    }

    public Knife(String productId, String name, double price, String material, double bladeLengthCm, boolean isSerrated) {
        super(productId, name, price, material);
        this.bladeLengthCm = bladeLengthCm;
        this.isSerrated = isSerrated;
    }

    public double getBladeLengthCm() {
        return bladeLengthCm;
    }

    public void setBladeLengthCm(double bladeLengthCm) {
        this.bladeLengthCm = bladeLengthCm;
    }

    public boolean isSerrated() {
        return isSerrated;
    }

    public void setSerrated(boolean serrated) {
        isSerrated = serrated;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Knife knife)) return false;
        if (!super.equals(o)) return false;
        return Double.compare(getBladeLengthCm(), knife.getBladeLengthCm()) == 0 && isSerrated() == knife.isSerrated();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getBladeLengthCm(), isSerrated());
    }

    @Override
    public String toString() {
        return "Knife{" +
                "bladeLengthCm=" + bladeLengthCm +
                ", isSerrated=" + isSerrated +
                "} " + super.toString();
    }
}