package pl.bak.auction_shop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import pl.bak.auction_shop.model.Car;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDto extends Car {

    public CarDto(BigDecimal price, String model, String brand, Long id, String engine) {
        super(price, model, brand, id, engine);
    }

    public CarDto() {
        super();

    }
}
