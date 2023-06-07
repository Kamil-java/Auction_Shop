package pl.bak.auction_shop.domain.services;


import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.bak.auction_shop.data.DummyDB;
import pl.bak.auction_shop.domain.dao.CrudService;
import pl.bak.auction_shop.dto.UserDto;
import pl.bak.auction_shop.model.User;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements CrudService<Long, User> {

    private final DummyDB<User> userDummyDB;
    private final ModelMapper modelMapper;

    public UserService(DummyDB<User> userDummyDB, ModelMapper modelMapper) {
        this.userDummyDB = userDummyDB;
        this.modelMapper = modelMapper;
    }

    public Optional<User> getUserByUsername(String username) throws UsernameNotFoundException {
        User usr = userDummyDB.getDb().values().stream().filter(user -> user.getUsername().equals(username)).toList().get(0);
        return Optional.ofNullable(usr);
    }

    @Override
    public User save(User user) {
        Map<Long, User> db = userDummyDB.getDb();
        Set<String> emailSet = db.values().stream().map(User::getEmail).collect(Collectors.toSet());
        if (emailSet.contains(user.getEmail())) {
            return null;
        }
        user.setId(userDummyDB.getLastIndex() + 1);
        return modelMapper.map(userDummyDB.saveToDB(user), UserDto.class);
    }

    @Override
    public Optional<User> getById(Long id) {
        Optional<User> objectById = userDummyDB.getObjectById(id);
        return objectById.map(user -> modelMapper.map(user, UserDto.class));
    }

    @Override
    public User update(Long id, User user) {
        if (userDummyDB.getObjectById(id).isEmpty()) {
            return save(user);
        }
        user.setId(userDummyDB.getLastIndex() + 1);
        return modelMapper.map(userDummyDB.updateDB(id, user), UserDto.class);
    }

    @Override
    public void delete(Long id) {
        userDummyDB.delete(id);
    }
}
