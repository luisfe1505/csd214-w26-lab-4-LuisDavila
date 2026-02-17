package csd214.bookstore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import java.util.Objects;

@Entity
public abstract class StationeryEntity extends ProductEntity {

    @Column(name = "brand")
    private String brand;

    public StationeryEntity() {
        super();
    }

    public StationeryEntity(String brand, double price) {
        this.brand = brand;
        this.setPrice(price);
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StationeryEntity that)) return false;
        // Check ID equality (if persisted) or fallback to field equality
        if (getId() != null && Objects.equals(getId(), that.getId())) return true;
        return Objects.equals(getBrand(), that.getBrand());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBrand());
    }

    @Override
    public String toString() {
        return "StationeryEntity{" +
                "brand='" + brand + '\'' +
                "} " + super.toString();
    }
}