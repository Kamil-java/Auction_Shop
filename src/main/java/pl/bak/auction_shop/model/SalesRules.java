package pl.bak.auction_shop.model;

import java.math.BigDecimal;

public interface SalesRules {
    BigDecimal nettoCalculation(BigDecimal price);
    BigDecimal bruttoCalculation(BigDecimal price, int vat);
    BigDecimal discount(int percent, BigDecimal price);

}
