package csd214.bookstore.pojos;

import java.util.Scanner;
import java.util.UUID;

/**
 * DTO for {@link csd214.bookstore.entities.PublicationEntity}
 */
public abstract class Publication extends Product {
    private String title = "";
    private int copies = 0;

    public Publication() {
    }

    public Publication(String title, double price, int copies) {
        super(UUID.randomUUID().toString(), "publication", price);
        this.title = title;
        this.copies = copies;
    }

    @Override
    public void initialize(Scanner input) {
        super.initialize(input);
        System.out.println("Enter Title:");
        this.title = getInput(input, "Available Title");

        System.out.println("Enter copies:");
        this.copies = getInput(input, 0);
    }

    public void edit(Scanner input) {
        super.edit(input);
        System.out.println("Edit Title [" + this.title + "]:");
        this.title = getInput(input, this.title);
        System.out.println("Edit Copies [" + this.copies + "]:");
        this.copies = getInput(input, this.copies);

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }
   @Override
    public String toString() {
        return "Publication{" +
                "title='" + title + '\'' +
                ", copies=" + copies +
                "} " + super.toString();
    }

}
