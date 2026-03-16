package csd214.bookstore.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.util.Objects;

@Entity
@DiscriminatorValue("IPHONE")
public class IphoneEntity extends MobileEntity {
    private boolean hasMagSafe;

    public IphoneEntity() {
        super();
    }

    public IphoneEntity(String name, double price, String color, int storage, boolean hasMagSafe) {
        super(name, price, color, storage);
        this.hasMagSafe = hasMagSafe;
    }

    public boolean isHasMagSafe() { return hasMagSafe; }
    public void setHasMagSafe(boolean hasMagSafe) { this.hasMagSafe = hasMagSafe; }

    @Override
    public String toString() {
        return "IphoneEntity{" +
                "hasMagSafe=" + hasMagSafe +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IphoneEntity)) return false;
        IphoneEntity that = (IphoneEntity) o;
        return Objects.equals(getProductId(), that.getProductId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId());
    }
}