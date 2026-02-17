package csd214.bookstore.pojos;

import java.util.Objects;

public abstract class Cutlery extends Product {
    private String material; // 'protected' allows children to access this directly
    private boolean isDishwasherSafe;

    public Cutlery(String material) {
        this.material = material;
    }

    public Cutlery(String productId, String name, double price, String material) {
        super(productId, name, price);
        this.material = material;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public boolean isDishwasherSafe() {
        return isDishwasherSafe;
    }

    public void setDishwasherSafe(boolean dishwasherSafe) {
        isDishwasherSafe = dishwasherSafe;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Cutlery cutlery)) return false;
        return isDishwasherSafe() == cutlery.isDishwasherSafe() && Objects.equals(getMaterial(), cutlery.getMaterial());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMaterial(), isDishwasherSafe());
    }

    @Override
    public String toString() {
        return "Cutlery{" +
                "material='" + material + '\'' +
                ", isDishwasherSafe=" + isDishwasherSafe +
                "} " + super.toString();
    }
}