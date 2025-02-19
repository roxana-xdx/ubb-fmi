package main.java.com.example.demo1.repository;

import com.example.demo1.domain.Entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractRepository<T extends Entity> implements Iterable<T> {
    protected List<T> data = new ArrayList<>();

    public abstract void add(T elem) throws RepositoryException, IOException;
    public abstract void delete(int id) throws RepositoryException, IOException;
    public abstract T find(int id);
    public abstract void update(int id,T elem) throws RepositoryException, IOException;
    public int size() {
        return data.size();
    }
    public Collection<T> getAll() {
        return new ArrayList<>(data);
    }
    public Iterator<T> iterator() {
        return data.iterator();
    }
}
