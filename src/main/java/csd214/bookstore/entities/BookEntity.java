package csd214.bookstore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.util.Objects;

@Entity
@DiscriminatorValue("BOOK")
public class BookEntity extends PublicationEntity {
    @Column(name = "author")
    private String author;

    // --- Constructors ---
    public BookEntity() {
        super();
    }

    public BookEntity(String author, String title, double price, int copies) {
        super(title, price, copies); // Passes data up the chain
        this.author = author;
    }

    // --- Getters & Setters ---
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    // --- Identity & String ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookEntity that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getAuthor(), that.getAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAuthor());
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "author='" + author + '\'' +
                "} " + super.toString();
    }
}