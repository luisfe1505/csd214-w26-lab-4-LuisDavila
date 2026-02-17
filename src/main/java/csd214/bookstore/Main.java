package csd214.bookstore;

public class Main {
    public static void main(String[] args) {
        System.out.println("Fred Carella's Superstore - v2.0 (JPA Edition)");
        App app = new App();
        try {
            app.run();
        } finally {
            // Ensure connection closes even if app crashes
            app.shutdown();
        }
    }
}