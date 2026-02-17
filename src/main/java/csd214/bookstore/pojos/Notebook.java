package csd214.bookstore.pojos;

import java.util.Objects;
import java.util.Scanner;

public class Notebook extends Stationery {

    private int pageCount;

    public int getPageCount() {
        return pageCount;
    }

    public Notebook() {
    }

    public Notebook(String brand, double price, int pageCount) {
        super(brand, price);
        this.pageCount = pageCount;
    }

    @Override
    public String toString() {
        return "Notebook{" +
                "pageCount=" + pageCount +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Notebook notebook)) return false;
        if (!super.equals(o)) return false;
        return getPageCount() == notebook.getPageCount();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPageCount());
    }

    public Notebook(int pageCount) {
        this.pageCount = pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public void initialize(Scanner input) {
        super.initialize(input);
        System.out.println("Page count:");
        this.pageCount = getInput(input, 0);
    }
    @Override
    public void edit(Scanner input) {
        System.out.println("Page count:");
        this.pageCount = getInput(input, getPageCount());
        super.initialize(input); 
    }

    @Override
    public void sellItem() {

    }
}
