package pl.bak.auction_shop.domain.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.bak.auction_shop.data.DummyDB;
import pl.bak.auction_shop.domain.dao.CrudService;
import pl.bak.auction_shop.dto.BikeDto;
import pl.bak.auction_shop.model.Bike;
import pl.bak.auction_shop.model.Product;

import java.util.Optional;

@Service
public class BikeService implements CrudService<Long, Bike> {

    private final ModelMapper modelMapper;
    private final DummyDB<Bike> bikeDummyDB;

    public BikeService(ModelMapper modelMapper, DummyDB<Bike> bikeDummyDB) {
        this.modelMapper = modelMapper;
        this.bikeDummyDB = bikeDummyDB;
    }

    @Override
    public Bike save(Bike bike) {
        bike.setId(bikeDummyDB.getLastIndex() + 1);
        return modelMapper.map(bikeDummyDB.saveToDB(bike), BikeDto.class);
    }

    @Override
    public Optional<Bike> getById(Long id) {
        Optional<Bike> objectById = bikeDummyDB.getObjectById(id);
        return objectById.map(bike -> modelMapper.map(bike, BikeDto.class));
    }

    @Override
    public Bike update(Long id, Bike bike) {
        if (bikeDummyDB.getObjectById(id).isEmpty()) {
            return save(bike);
        }
        bike.setId(bikeDummyDB.getLastIndex() + 1);
        return modelMapper.map(bikeDummyDB.updateDB(id, bike), BikeDto.class);
    }

    @Override
    public void delete(Long id) {
        bikeDummyDB.delete(id);
    }
}
