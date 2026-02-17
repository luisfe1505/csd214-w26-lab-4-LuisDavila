package csd214.bookstore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import java.util.Objects;

@Entity
public abstract class PublicationEntity extends ProductEntity {
    @Column(name = "title")
    private String title;

    @Column(name = "copies")
    private int copies;

    // --- Constructors ---
    public PublicationEntity() {
        super();
    }

    public PublicationEntity(String title, double price, int copies) {
        super(title, price); // Passes Name and Price to ProductEntity
        this.title = title;
        this.copies = copies;
    }

    // --- Getters & Setters ---
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getCopies() { return copies; }
    public void setCopies(int copies) { this.copies = copies; }

    // --- Identity & String ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PublicationEntity that)) return false;
        if (!super.equals(o)) return false;
        return getCopies() == that.getCopies() && Objects.equals(getTitle(), that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTitle(), getCopies());
    }

    @Override
    public String toString() {
        return "PublicationEntity{" +
                "title='" + title + '\'' +
                ", copies=" + copies +
                "} " + super.toString();
    }
}