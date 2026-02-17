package csd214.bookstore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.util.Objects;

@Entity
@DiscriminatorValue("PEN")
public class PenEntity extends StationeryEntity {

    @Column(name = "color")
    private String color;

    public PenEntity() {
        super();
    }

    public PenEntity(String brand, double price, String color) {
        super(brand, price);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PenEntity penEntity)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(color, penEntity.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), color);
    }

    @Override
    public String toString() {
        return "PenEntity{" +
                "color='" + color + '\'' +
                "} " + super.toString();
    }
}