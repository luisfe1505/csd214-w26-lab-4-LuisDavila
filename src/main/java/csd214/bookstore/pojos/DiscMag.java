package csd214.bookstore.pojos;

import java.util.Date;
import java.util.Scanner;

public class DiscMag extends Magazine {
    private boolean hasDisc;

    public DiscMag() {
    }

    public DiscMag(boolean hasDisc, int orderQty, Date currentIssue, String title, double price, int copies) {
        super(orderQty, currentIssue, title, price, copies);
        this.hasDisc = hasDisc;
    }

    @Override
    public void initialize(Scanner input) {
        System.out.println("Has Disc? (true/false):");
        this.hasDisc = getInput(input, false);
        super.initialize(input); // Title, Qty, Date, Copies, Price

    }

    @Override
    public void edit(Scanner input) {
        System.out.println("Edit Has Disc [" + this.hasDisc + "]:");
        this.hasDisc = getInput(input, this.hasDisc);
        super.edit(input); // Title, Price, Copies, OrderQty, Date
    }

    @Override
    public void sellItem() {
        System.out.println("Selling Disc Magazine (Disc: " + hasDisc + ")");
        setCopies(getCopies() - 1);
    }

    public boolean isHasDisc() { return hasDisc; }
    public void setHasDisc(boolean h) { this.hasDisc = h; }

    @Override
    public String toString() {
        return "DiscMag{hasDisc=" + hasDisc + ", " + super.toString() + "}";
    }
}
