package Repository;

import domain.Entity;

import java.util.ArrayList;
import java.util.List;

public class MemoryRepository<T extends Entity> implements Repository<T> {
    private List<T> entities = new ArrayList<>();

    @Override
    public void add(T entity) throws Exception {
//        if (getById(entity.getId()) != null) {
//            throw new Exception("Entity with ID " + entity.getId() + " already exists.");
//        }
        entities.add(entity);
    }

    @Override
    public T getById(int id) throws Exception {
        for (T entity : entities) {
            if (entity.getId() == id) {
                return entity;
            }
        }
        throw new Exception("Entity with ID " + id + " not found.");
    }

    @Override
    public List<T> getAll() {
        return entities;
    }

    @Override
    public void update(T entity) throws Exception {
        T existingEntity = getById(entity.getId());
        if (existingEntity == null) {
            throw new Exception("Entity with ID " + entity.getId() + " not found.");
        }
        int index = entities.indexOf(existingEntity);
        entities.set(index, entity);
    }

    @Override
    public void delete(int id) throws Exception {
        T entity = getById(id);
        if (entity != null) {
            entities.remove(entity);
        } else {
            throw new Exception("Entity with ID " + id + " not found.");
        }
    }
}
