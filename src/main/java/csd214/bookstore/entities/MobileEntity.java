package csd214.bookstore.entities;

import jakarta.persistence.Entity;
import java.util.Objects;

@Entity
public abstract class MobileEntity extends ProductEntity {
    private String color;
    private int storage;

    public MobileEntity() {
        super();
    }

    public MobileEntity(String name, double price, String color, int storage) {
        super(name, price);
        this.color = color;
        this.storage = storage;
    }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public int getStorage() { return storage; }
    public void setStorage(int storage) { this.storage = storage; }

    @Override
    public String toString() {
        return "MobileEntity{" +
                "color='" + color + '\'' +
                ", storage=" + storage + "GB" +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MobileEntity)) return false;
        MobileEntity that = (MobileEntity) o;
        return Objects.equals(getProductId(), that.getProductId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId());
    }
}