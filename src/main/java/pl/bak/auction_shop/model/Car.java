package pl.bak.auction_shop.model;

import java.math.BigDecimal;

public class Car extends Vehicle{
    private Long id;
    private String engine;

    public Car() {
    }

    public Car(BigDecimal price, String model, String brand, Long id, String engine) {
        super(price, model, brand);
        this.id = id;
        this.engine = engine;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", engine='" + engine + '\'' +
                ", model=" + super.getModel() +
                ", brand=" + super.getBrand() +
                ", price=" + super.getPrice() +
                '}';
    }
}
