package Repository;

import java.util.List;

public interface Repository<T> {
    void add(T entity) throws Exception;
    T getById(int id) throws Exception;
    List<T> getAll();
    void update(T entity) throws Exception;
    void delete(int id) throws Exception;
}

