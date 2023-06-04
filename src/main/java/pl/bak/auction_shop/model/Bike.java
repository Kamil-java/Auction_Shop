package pl.bak.auction_shop.model;

import java.math.BigDecimal;

public class Bike extends Vehicle {
    private Long id;
    private boolean isElectric;

    public Bike() {
    }

    public Bike(BigDecimal price, String model, String brand, Long id, boolean isElectric) {
        super(price, model, brand);
        this.id = id;
        this.isElectric = isElectric;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isElectric() {
        return isElectric;
    }

    public void setElectric(boolean electric) {
        isElectric = electric;
    }

    @Override
    public String toString() {
        return "Bike{" +
                "id=" + id +
                ", isElectric=" + isElectric +
                ", model=" + super.getModel() +
                ", brand=" + super.getBrand() +
                ", price=" + super.getPrice() +
                '}';
    }
}
