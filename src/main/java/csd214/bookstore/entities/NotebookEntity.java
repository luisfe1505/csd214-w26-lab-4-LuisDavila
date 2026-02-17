package csd214.bookstore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.util.Objects;

@Entity
@DiscriminatorValue("NOTEBOOK")
public class NotebookEntity extends StationeryEntity {

    @Column(name = "page_count")
    private int pageCount;

    public NotebookEntity() {
        super();
    }

    public NotebookEntity(String brand, double price, int pageCount) {
        super(brand, price);
        this.pageCount = pageCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotebookEntity that)) return false;
        if (!super.equals(o)) return false;
        return pageCount == that.pageCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pageCount);
    }

    @Override
    public String toString() {
        return "NotebookEntity{" +
                "pageCount=" + pageCount +
                "} " + super.toString();
    }
}