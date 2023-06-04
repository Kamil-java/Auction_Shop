package pl.bak.auction_shop.domain.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.bak.auction_shop.domain.services.CarService;
import pl.bak.auction_shop.model.Car;

@RestController
@RequestMapping("/api/car")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/{id}")
    public Car getCarById(@PathVariable("id") Long id) {
        return carService.getById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Car createCar(@RequestBody Car car) {
        Car cr = carService.save(car);
        if (cr == null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        return cr;
    }

    @PutMapping("/{id}")
    public Car updateCar(@PathVariable("id") Long id, @RequestBody Car car) {
        return carService.update(id, car);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCarById(@PathVariable("id") Long id) {
        if (carService.getById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        carService.delete(id);
    }
}
