package pl.bak.auction_shop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import pl.bak.auction_shop.model.Bike;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BikeDto extends Bike {
    public BikeDto(BigDecimal price, String model, String brand, Long id, boolean isElectric) {
        super(price, model, brand, id, isElectric);
    }

    public BikeDto() {
    }
}
