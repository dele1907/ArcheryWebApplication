package de.dele1907.Service;

import java.util.List;

public interface IBaseService<E> {

    List<E> getAllEntities() throws Exception;

    E getEntityById(String id);

    boolean deleteEntityById(String id);

    boolean saveNewEntity(E entity);

    boolean updateEntity(E entity);
}
