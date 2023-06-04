package pl.bak.auction_shop.domain.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.bak.auction_shop.domain.services.BikeService;
import pl.bak.auction_shop.model.Bike;

@RestController
@RequestMapping("/api/bike")
public class BikeController {

    private final BikeService bikeService;

    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @GetMapping("/{id}")
    public Bike getBikeById(@PathVariable("id") Long id) {
        return bikeService.getById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Bike createBike(@RequestBody Bike bike) {
        Bike bke = bikeService.save(bike);
        if (bke == null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        return bke;
    }

    @PutMapping("/{id}")
    public Bike updateBike(@PathVariable("id") Long id, @RequestBody Bike bike) {
        return bikeService.update(id, bike);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBikeById(@PathVariable("id") Long id) {
        if (bikeService.getById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        bikeService.delete(id);
    }
}
