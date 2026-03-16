package csd214.bookstore.jpa;

import csd214.bookstore.entities.IphoneEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class JpaIphoneApp {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookstore-pu");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("\n[Step 1] Creating a new Iphone...");
            em.getTransaction().begin();
            IphoneEntity myIphone = new IphoneEntity("Iphone 15 Pro", 1199.99, "Titanium", 256, true);
            em.persist(myIphone);
            em.getTransaction().commit();
            System.out.println("Iphone saved with Database ID: " + myIphone.getId());

            listIphones(em, "[Step 2] Current Inventory:");

            System.out.println("\n[Step 3] Editing Iphone Price...");
            em.getTransaction().begin();
            IphoneEntity iphoneToEdit = em.find(IphoneEntity.class, myIphone.getId());
            if (iphoneToEdit != null) {
                iphoneToEdit.setPrice(1099.99);
            }
            em.getTransaction().commit();

            listIphones(em, "[Step 4] After Price Update: ");

            System.out.println("\n[Step 5] Deleting the Iphone...");
            em.getTransaction().begin();
            IphoneEntity iphoneToDelete = em.find(IphoneEntity.class, myIphone.getId());
            if (iphoneToDelete != null) {
                em.remove(iphoneToDelete);
            }
            em.getTransaction().commit();

            listIphones(em, "[Step 6] Final Inventory (should be empty):");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }

    private static void listIphones(EntityManager em, String header) {
        System.out.println("\n" + header);
        List<IphoneEntity> iphones = em.createQuery("SELECT i FROM IphoneEntity i", IphoneEntity.class).getResultList();

        if (iphones.isEmpty()) {
            System.out.println("No iphones found in database.");
        } else {
            iphones.forEach(i -> System.out.println(" > " + i));
        }
    }
}