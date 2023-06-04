package pl.bak.auction_shop.model;

import java.math.BigDecimal;

public class Vehicle extends Product implements SalesRules {

    private String model;

    private String brand;

    public Vehicle() {
    }

    public Vehicle(BigDecimal price, String model, String brand) {
        super(price);
        this.model = model;
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public BigDecimal nettoCalculation(BigDecimal price) {
        return price;
    }

    @Override
    public BigDecimal bruttoCalculation(BigDecimal price, int vat) {
        return price.add(price.multiply(BigDecimal.valueOf(vat)));
    }

    @Override
    public BigDecimal discount(int percent, BigDecimal price) {
        return price.subtract(price.multiply(BigDecimal.valueOf(percent)));
    }
}
