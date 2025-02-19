package repository;

import domain.Entity;
import java.io.IOException;

public class MemoryRepository<T extends Entity> extends AbstractRepository<T> {

    @Override
    public void add(T elem) throws RepositoryException, IOException {
        if (find(elem.getId()) != null) {
            throw new DuplicateIDException("Deja exista o entitate cu acest id.");
        }
        data.add(elem);
    }

    @Override
    public void delete(int id) throws RepositoryException, IOException {
        T elem = find(id);
        if (elem == null) {
            throw new ObjectNotFoundException("Entitatea nu a fost gasita.");
        }
        data.remove(elem);
    }

    @Override
    public T find(int id) {
        for (T elem : data){
            if (id == elem.getId()) {
                return elem;
            }
        }
        return null;
    }

    private int getPosition(T elem) {
        for (int i = 0; i < data.size(); i++) {
            if (elem.getId() == data.get(i).getId()) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void update(int id, T elem) throws RepositoryException, IOException {
        T elem2 = find(id);
        if (elem2 == null) {
            throw new ObjectNotFoundException("Entitatea nu a fost gasita.");
        }
        int p = getPosition(elem2);
        data.set(p, elem);
    }

    @Override
    public int size(){
        return data.size();
    }
}
