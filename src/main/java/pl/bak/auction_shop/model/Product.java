package pl.bak.auction_shop.model;

import java.math.BigDecimal;

public class Product {
    private BigDecimal price;

    public Product() {
    }

    public Product(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
