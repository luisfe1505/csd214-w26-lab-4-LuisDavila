package csd214.bookstore.entities;

import jakarta.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "product_entity")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "product_type")
public abstract class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "product_id", unique = true)
    private String productId;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "name")
    private String name;

    // --- Constructors ---
    public ProductEntity() {
        // Generate a stable Business Key on creation
        this.productId = UUID.randomUUID().toString();
    }

    public ProductEntity(String name, double price) {
        this(); // Ensure ID generation
        this.name = name;
        this.price = price;
    }

    // --- Getters & Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    // --- Identity Logic (Business Key: productId) ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductEntity that)) return false;
        return Objects.equals(getProductId(), that.getProductId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId());
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", productId='" + productId + '\'' +
                ", price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}