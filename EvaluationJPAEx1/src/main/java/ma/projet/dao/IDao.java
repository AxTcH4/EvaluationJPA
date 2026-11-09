package ma.projet.dao;

import java.util.List;

public interface IDao<T> {
    void create(T t);
    void update(T t);
    void delete(T t);
    T find(Class<T> clazz, int id);
    List<T> findAll(Class<T> clazz);
}