package csd214.bookstore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.util.Date;
import java.util.Objects;

@Entity
@DiscriminatorValue("DISCMAG")
public class DiscMagEntity extends MagazineEntity {

    @Column(name = "has_disc")
    private boolean hasDisc;

    public DiscMagEntity() {
        super();
    }

    public DiscMagEntity(String title, double price, int copies, int orderQty, Date currentIssue, boolean hasDisc) {
        super(title, price, copies, orderQty, currentIssue);
        this.hasDisc = hasDisc;
    }

    public boolean isHasDisc() {
        return hasDisc;
    }

    public void setHasDisc(boolean hasDisc) {
        this.hasDisc = hasDisc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiscMagEntity that)) return false;
        if (!super.equals(o)) return false;
        return hasDisc == that.hasDisc;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hasDisc);
    }

    @Override
    public String toString() {
        return "DiscMagEntity{" +
                "hasDisc=" + hasDisc +
                "} " + super.toString();
    }
}