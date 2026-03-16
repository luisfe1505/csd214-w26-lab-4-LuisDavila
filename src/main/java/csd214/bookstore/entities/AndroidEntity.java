package csd214.bookstore.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.util.Objects;

@Entity
@DiscriminatorValue("ANDROID")
public class AndroidEntity extends MobileEntity {
    private boolean hasExpandableStorage;

    public AndroidEntity() {
        super();
    }

    public AndroidEntity(String name, double price, String color, int storage, boolean hasExpandableStorage) {
        super(name, price, color, storage);
        this.hasExpandableStorage = hasExpandableStorage;
    }

    public boolean isHasExpandableStorage() { return hasExpandableStorage; }
    public void setHasExpandableStorage(boolean hasExpandableStorage) { this.hasExpandableStorage = hasExpandableStorage; }

    @Override
    public String toString() {
        return "AndroidEntity{" +
                "hasExpandableStorage=" + hasExpandableStorage +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AndroidEntity)) return false;
        AndroidEntity that = (AndroidEntity) o;
        return Objects.equals(getProductId(), that.getProductId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId());
    }
}