package pl.bak.auction_shop.domain.dao;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CrudService<K, T> {
    T save(T t);
    Optional<T> getById(K k);
    T update(K k, T t);
    void delete(K k);
}
