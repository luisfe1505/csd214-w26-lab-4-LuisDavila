package csd214.bookstore.pojos;

import java.util.Scanner;
import java.util.UUID;

public class Ticket extends Product {
    public String description = "";

    public Ticket() {
    }

    public Ticket(String description, double price) {
        super(UUID.randomUUID().toString(), "Ticket", price);
        this.description=description;
    }

    
    
    
    @Override
    public void sellItem() {
        System.out.println("Selling Ticket: " + description + " for " + getPrice());
    }


    @Override
    public void initialize(Scanner input) {
        System.out.println("Enter Description:");
        this.description = getInput(input, "Ticket");

        super.initialize(input);
    }

    @Override
    public void edit(Scanner input) {
        System.out.println("Edit Description [" + this.description + "]:");
        this.description = getInput(input, this.description);

        super.edit(input);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    
    @Override
    public String toString() {
        return "Ticket{desc='" + description + "', price=" + getPrice() + "}";
    }
}
