package csd214.bookstore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.util.Objects;

@Entity
@DiscriminatorValue("TICKET")
public class TicketEntity extends ProductEntity {

    @Column(name = "description")
    private String description;

    public TicketEntity() {
        super();
    }

    public TicketEntity(String description, double price) {
        this.description = description;
        this.setPrice(price);
        this.setName("Ticket: " + description); // Optional: Sync inherited name field
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TicketEntity that)) return false;
        // Check ID if persisted
        if (getId() != null && Objects.equals(getId(), that.getId())) return true;
        return Objects.equals(getDescription(), that.getDescription()) && Double.compare(getPrice(), that.getPrice()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDescription(), getPrice());
    }

    @Override
    public String toString() {
        return "TicketEntity{" +
                "description='" + description + '\'' +
                "} " + super.toString();
    }
}