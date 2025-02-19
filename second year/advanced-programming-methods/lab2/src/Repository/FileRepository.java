package Repository;

import domain.Entity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileRepository<T extends Entity> implements Repository<T> {
    private static final String FILE_NAME = "entities.txt";
    private List<T> entities;

    public FileRepository() {
        entities = new ArrayList<>();
        loadEntities();
    }

    @Override
    public void add(T entity) throws Exception {
        if (getById(entity.getId()) != null) {
            throw new Exception("Entity with ID " + entity.getId() + " already exists.");
        }
        entities.add(entity);
        saveEntities();
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
        saveEntities();
    }

    @Override
    public void delete(int id) throws Exception {
        T entity = getById(id);
        if (entity != null) {
            entities.remove(entity);
            saveEntities();
        } else {
            throw new Exception("Entity with ID " + id + " not found.");
        }
    }

    // Load entities from the file
    private void loadEntities() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            entities = (List<T>) ois.readObject();
        } catch (FileNotFoundException e) {
            entities = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading entities: " + e.getMessage());
        }
    }

    // Save entities to the file
    private void saveEntities() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(entities);
        } catch (IOException e) {
            System.out.println("Error saving entities to file: " + e.getMessage());
        }
    }
}
