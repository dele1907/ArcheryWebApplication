package de.dele1907.Database.Repository;

import java.util.List;
import java.util.Optional;

public interface IBaseRepository<T, ID> {
    List<T> findAll();
    Optional<T> findById(ID id);
    boolean deleteById(ID id);
    boolean save(T entity);
    boolean update(T entity);
}
