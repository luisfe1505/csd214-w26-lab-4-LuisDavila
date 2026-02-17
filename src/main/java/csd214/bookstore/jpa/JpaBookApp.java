package csd214.bookstore.jpa;

import csd214.bookstore.entities.BookEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaBookApp {
    public static void main(String[] args) {
        // 1. Start Hibernate (Loads persistence.xml)
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookstore-pu");
        EntityManager em = emf.createEntityManager();
        // 2. Create Data
        em.getTransaction().begin();
        BookEntity book = new BookEntity();
        book.setPrice(34.99);
        book.setCopies(10);
        book.setAuthor("Maria Batista");
        em.persist(book);
        em.getTransaction().commit();

        // 3. Read Data (Polymorphic check)
        // Notice we can query specifically for Widgets
        List<BookEntity> books = em.createQuery(
                "SELECT w FROM BookEntity w", BookEntity.class).getResultList();

        for (BookEntity b : books) {
            System.out.println("Found: " + b);
        }

        em.close();


    }
}
