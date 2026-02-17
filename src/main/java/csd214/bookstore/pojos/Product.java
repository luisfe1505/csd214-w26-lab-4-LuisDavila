package csd214.bookstore.pojos;

import java.io.Serializable;
import java.util.Scanner;
import java.util.UUID;

// Marked abstract because it implements SaleableItem but doesn't implement getPrice()
// (Price is defined in children: Ticket and Publication)
/**
 * DTO for {@link csd214.bookstore.entities.ProductEntity}
 */
public abstract class Product extends Editable implements SaleableItem, Serializable {
    private String productId;
    private double price;
    private String name;

    public Product() {
        productId=UUID.randomUUID().toString();
        name="product";
    }
    public Product(String productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    @Override
    public void initialize(Scanner input) {
//        System.out.println("Enter name: ");
//        this.name = getInput(input, "name");
        System.out.println("Enter price: ");
        this.price = getInput(input, 0.0);

    }
    @Override
    public void edit(Scanner input) {
//        System.out.println("Edit name: ");
//        this.name = getInput(input, getName());
        System.out.println("Edit price: ");
        this.price = getInput(input, getPrice());

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", price=" + price + ", name=" + name + '}';
    }
    
}
