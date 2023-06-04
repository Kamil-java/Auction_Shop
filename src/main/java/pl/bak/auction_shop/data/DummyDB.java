package pl.bak.auction_shop.data;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalLong;

@Repository
public class DummyDB<T> {
    private final Map<Long, T> db = new HashMap<>();

    public Map<Long, T> getDb() {
        return db;
    }

    public Optional<T> getObjectById(Long id) {
        if (!getDb().containsKey(id)) {
            System.out.println("The Object with ID: " + id + " doesn't exist");
            return Optional.empty();
        }
        return Optional.of(getDb().get(id));
    }
    public T saveToDB(T dataObject) {
        getDb().put(getLastIndex() + 1, dataObject);
        System.out.println("The object " + dataObject.toString() + " has been created and saved");
        return getDb().get(getLastIndex());
    }

    public T updateDB(Long id, T dataObject) {
        if (!getDb().containsKey(id)) {
            System.out.println("Object with id: " + id + " doesn't exist");
            return saveToDB(dataObject);
        }
        getDb().put(id, dataObject);
        System.out.println("The Object " + dataObject.toString() + " has been updated");
        return getDb().get(id);
    }

    public void delete(Long id) {
        if (!getDb().containsKey(id)) {
            System.out.println("The Object with ID: " + id + " doesn't exist");
            return;
        }
        getDb().remove(id);
        System.out.println("The Object with ID: " + id + " has been deleted");
    }

    public Long getLastIndex() {
        OptionalLong max = getDb().keySet().stream().mapToLong(value -> value).max();
        return max.isPresent() ? max.getAsLong() : 0;
    }
}
