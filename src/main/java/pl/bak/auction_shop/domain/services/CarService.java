package pl.bak.auction_shop.domain.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.bak.auction_shop.data.DummyDB;
import pl.bak.auction_shop.domain.dao.CrudService;
import pl.bak.auction_shop.dto.CarDto;
import pl.bak.auction_shop.model.Car;
import pl.bak.auction_shop.model.Product;

import java.util.Optional;

@Service
public class CarService implements CrudService<Long, Car> {
    private final ModelMapper modelMapper;
    private final DummyDB<Car> carDummyDB;

    public CarService(ModelMapper modelMapper, DummyDB<Car> carDummyDB) {
        this.modelMapper = modelMapper;
        this.carDummyDB = carDummyDB;
    }

    @Override
    public Car save(Car car) {
        car.setId(carDummyDB.getLastIndex() + 1);
        return modelMapper.map(carDummyDB.saveToDB(car), CarDto.class);
    }

    @Override
    public Optional<Car> getById(Long id) {
        Optional<Car> objectById = carDummyDB.getObjectById(id);
        return objectById.map(car -> modelMapper.map(car, CarDto.class));
    }

    @Override
    public Car update(Long id, Car car) {
        if (carDummyDB.getObjectById(id).isEmpty()) {
            return save(car);
        }
        car.setId(carDummyDB.getLastIndex() + 1);
        return modelMapper.map(carDummyDB.updateDB(id, car), CarDto.class);
    }

    @Override
    public void delete(Long id) {
        carDummyDB.delete(id);
    }
}
