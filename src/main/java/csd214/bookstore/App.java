package csd214.bookstore;

import com.github.javafaker.Faker;
import csd214.bookstore.entities.*;
import csd214.bookstore.pojos.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class App {
    // Database Infrastructure
    private EntityManagerFactory emf;
    private EntityManager em;

    // UI & Logic
    private CashTill cashTill = new CashTill();
    private Scanner input = new Scanner(System.in);

    public App() {
        // Initialize JPA
        this.emf = Persistence.createEntityManagerFactory("bookstore-pu");
        this.em = emf.createEntityManager();
    }

    public void shutdown() {
        if (em != null) em.close();
        if (emf != null) emf.close();
    }

    public void run() {
        // Optional: Populate only if empty
        long count = em.createQuery("SELECT count(p) FROM ProductEntity p", Long.class).getSingleResult();
        if (count == 0) {
            populate();
        }

        int choice = 0;
        while (choice != 99) {
            System.out.println("\n***********************");
            System.out.println("      JPA STORE        ");
            System.out.println("***********************");
            System.out.println(" 1. Add Items");
            System.out.println(" 2. Edit Items");
            System.out.println(" 3. Delete Items");
            System.out.println(" 4. Sell item(s)");
            System.out.println(" 5. List items");
            System.out.println("99. Quit");
            System.out.println("***********************");
            System.out.print("Enter choice: ");

            try {
                String line = input.nextLine();
                if (line.trim().isEmpty()) continue;
                choice = Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
                choice = 0;
            }

            switch (choice) {
                case 1: addItem(); break;
                case 2: editItem(); break;
                case 3: deleteItem(); break;
                case 4: sellItem(); break;
                case 5: listAny(); break;
                case 99: System.out.println("Goodbye."); break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    // ==========================================
    // 1. ADD ITEMS (POJO Input -> Entity Save)
    // ==========================================
    public void addItem() {
        System.out.println("\n--- Add an item ---");
        System.out.println("1. Book");
        System.out.println("2. Magazine");
        System.out.println("3. DiscMag");
        System.out.println("4. Ticket");
        System.out.println("5. Pen");
        System.out.println("6. Notebook");
        System.out.println("99. Back");

        int choice = getIntInput();
        if (choice == 99) return;

        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            switch(choice) {
                case 1:
                    Book bPojo = new Book();
                    bPojo.initialize(input); // Use POJO for input
                    // Map to Entity
                    BookEntity bEnt = new BookEntity();
                    bEnt.setTitle(bPojo.getTitle());
                    bEnt.setPrice(bPojo.getPrice());
                    bEnt.setCopies(bPojo.getCopies());
                    bEnt.setAuthor(bPojo.getAuthor());
                    em.persist(bEnt);
                    break;
                case 2:
                    Magazine mPojo = new Magazine();
                    mPojo.initialize(input);
                    MagazineEntity mEnt = new MagazineEntity();
                    mEnt.setTitle(mPojo.getTitle());
                    mEnt.setPrice(mPojo.getPrice());
                    mEnt.setCopies(mPojo.getCopies());
                    mEnt.setOrderQty(mPojo.getOrderQty());
                    mEnt.setCurrentIssue(mPojo.getCurrentIssue());
                    em.persist(mEnt);
                    break;
                case 3:
                    DiscMag dPojo = new DiscMag();
                    dPojo.initialize(input);
                    DiscMagEntity dEnt = new DiscMagEntity();
                    dEnt.setTitle(dPojo.getTitle());
                    dEnt.setPrice(dPojo.getPrice());
                    dEnt.setCopies(dPojo.getCopies());
                    dEnt.setOrderQty(dPojo.getOrderQty());
                    dEnt.setCurrentIssue(dPojo.getCurrentIssue());
                    dEnt.setHasDisc(dPojo.isHasDisc());
                    em.persist(dEnt);
                    break;
                case 4:
                    Ticket tPojo = new Ticket();
                    tPojo.initialize(input);
                    TicketEntity tEnt = new TicketEntity();
                    tEnt.setDescription(tPojo.getDescription());
                    tEnt.setPrice(tPojo.getPrice());
                    tEnt.setName("Ticket: " + tPojo.getDescription());
                    em.persist(tEnt);
                    break;
                case 5:
                    Pen pPojo = new Pen();
                    pPojo.initialize(input);
                    PenEntity pEnt = new PenEntity();
                    pEnt.setBrand(pPojo.getBrand());
                    pEnt.setPrice(pPojo.getPrice());
                    pEnt.setColor(pPojo.getColor());
                    pEnt.setName(pPojo.getColor() + " " + pPojo.getBrand() + " Pen");
                    em.persist(pEnt);
                    break;
                case 6:
                    Notebook nPojo = new Notebook();
                    nPojo.initialize(input);
                    NotebookEntity nEnt = new NotebookEntity();
                    nEnt.setBrand(nPojo.getBrand());
                    nEnt.setPrice(nPojo.getPrice());
                    nEnt.setPageCount(nPojo.getPageCount());
                    nEnt.setName(nPojo.getPageCount() + "pg " + nPojo.getBrand() + " Notebook");
                    em.persist(nEnt);
                    break;
                default:
                    System.out.println("Invalid type.");
            }

            tx.commit();
            System.out.println("Item saved to Database!");

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
    }

    // ==========================================
    // 2. LIST ITEMS (Query Entities)
    // ==========================================
    public void listAny() {
        // Query database for all products
        List<ProductEntity> results = em.createQuery("SELECT p FROM ProductEntity p", ProductEntity.class).getResultList();

        System.out.println("\n--- Inventory List (" + results.size() + ") ---");
        for (int i = 0; i < results.size(); i++) {
            System.out.println("[" + i + "] " + results.get(i));
        }
    }

    // ==========================================
    // 3. EDIT ITEMS (Entity -> POJO -> Entity)
    // ==========================================
    public void editItem() {
        List<ProductEntity> results = em.createQuery("SELECT p FROM ProductEntity p", ProductEntity.class).getResultList();
        if (results.isEmpty()) {
            System.out.println("No items to edit.");
            return;
        }

        listAny();
        System.out.println("Select index to edit:");
        int idx = getIntInput();

        if (idx < 0 || idx >= results.size()) return;

        ProductEntity entity = results.get(idx);
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // Manual Mapping: Entity -> POJO to use 'edit()' method
            if (entity instanceof BookEntity) {
                BookEntity be = (BookEntity) entity;
                Book pojo = new Book(be.getAuthor(), be.getTitle(), be.getPrice(), be.getCopies());
                pojo.edit(input); // User interaction
                // Map Back
                be.setAuthor(pojo.getAuthor());
                be.setTitle(pojo.getTitle());
                be.setPrice(pojo.getPrice());
                be.setCopies(pojo.getCopies());
            }
            else if (entity instanceof PenEntity) {
                PenEntity pe = (PenEntity) entity;
                Pen pojo = new Pen(pe.getBrand(), pe.getPrice(), pe.getColor());
                pojo.edit(input);
                pe.setBrand(pojo.getBrand());
                pe.setPrice(pojo.getPrice());
                pe.setColor(pojo.getColor());
            }
            // ... (Other types would follow similar pattern) ...
            else {
                System.out.println("Editing not fully implemented for this type in this demo.");
            }

            tx.commit();
            System.out.println("Update successful.");

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
    }

    // ==========================================
    // 4. DELETE ITEMS
    // ==========================================
    public void deleteItem() {
        List<ProductEntity> results = em.createQuery("SELECT p FROM ProductEntity p", ProductEntity.class).getResultList();
        listAny();
        System.out.println("Select index to delete:");
        int idx = getIntInput();

        if (idx < 0 || idx >= results.size()) return;

        ProductEntity toRemove = results.get(idx);

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(toRemove);
        tx.commit();
        System.out.println("Deleted.");
    }

    // ==========================================
    // 5. SELL ITEMS
    // ==========================================
    public void sellItem() {
        List<ProductEntity> results = em.createQuery("SELECT p FROM ProductEntity p", ProductEntity.class).getResultList();
        listAny();
        System.out.println("Select index to sell:");
        int idx = getIntInput();
        if (idx < 0 || idx >= results.size()) return;

        ProductEntity item = results.get(idx);

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // Business Logic
        if (item instanceof PublicationEntity) {
            PublicationEntity pub = (PublicationEntity) item;
            if (pub.getCopies() > 0) {
                pub.setCopies(pub.getCopies() - 1);
                System.out.println("Sold: " + pub.getTitle());
                // Add to transient cash till
                // Since Entities don't implement SaleableItem, we handle price manually
                // Or create a wrapper. simpler here:
                cashTill.sellItem(new SaleableItem() {
                    public void sellItem() {}
                    public double getPrice() { return pub.getPrice(); }
                });
            } else {
                System.out.println("Out of stock!");
            }
        } else {
            System.out.println("Sold " + item.getName());
            cashTill.sellItem(new SaleableItem() {
                public void sellItem() {}
                public double getPrice() { return item.getPrice(); }
            });
        }

        tx.commit();
    }

    // ==========================================
    // POPULATE (Faker -> Entities)
    // ==========================================
    public void populate() {
        System.out.println("Populating Database with Faker...");
        Faker faker = new Faker();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        for (int i = 0; i < 3; i++) {
            // Book
            BookEntity b = new BookEntity();
            b.setAuthor(faker.book().author());
            b.setTitle(faker.book().title());
            b.setPrice(faker.number().randomDouble(2, 10, 50));
            b.setCopies(faker.number().numberBetween(1, 20));
            em.persist(b);

            // Ticket
            TicketEntity t = new TicketEntity();
            String band = faker.rockBand().name();
            t.setDescription("Concert: " + band);
            t.setPrice(faker.number().randomDouble(2, 50, 150));
            t.setName("Ticket: " + band);
            em.persist(t);

            // Pen
            PenEntity p = new PenEntity();
            p.setBrand(faker.company().name());
            p.setColor(faker.color().name());
            p.setPrice(faker.number().randomDouble(2, 1, 5));
            p.setName("Pen " + p.getBrand());
            em.persist(p);
        }

        tx.commit();
    }

    private int getIntInput() {
        try {
            String line = input.nextLine();
            if (line.trim().isEmpty()) return -1;
            return Integer.parseInt(line.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}