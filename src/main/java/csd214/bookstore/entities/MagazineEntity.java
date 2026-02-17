package csd214.bookstore.entities;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@DiscriminatorValue("MAGAZINE")
public class MagazineEntity extends PublicationEntity {

    @Column(name = "order_qty")
    private int orderQty;

    @Column(name = "issue_date")
    @Temporal(TemporalType.DATE) // Stores as DATE in MySQL (no time component)
    private Date currentIssue;

    public MagazineEntity() {
        super();
    }

    public MagazineEntity(String title, double price, int copies, int orderQty, Date currentIssue) {
        this.setTitle(title);
        this.setPrice(price);
        this.setCopies(copies);
        this.orderQty = orderQty;
        this.currentIssue = currentIssue;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public Date getCurrentIssue() {
        return currentIssue;
    }

    public void setCurrentIssue(Date currentIssue) {
        this.currentIssue = currentIssue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MagazineEntity that)) return false;
        if (!super.equals(o)) return false;
        return orderQty == that.orderQty && Objects.equals(currentIssue, that.currentIssue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), orderQty, currentIssue);
    }

    @Override
    public String toString() {
        return "MagazineEntity{" +
                "orderQty=" + orderQty +
                ", currentIssue=" + currentIssue +
                "} " + super.toString();
    }
}