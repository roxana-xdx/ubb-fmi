package main.java.com.example.demo1.repository;

import com.example.demo1.domain.Entity;

import java.io.*;
import java.util.ArrayList;

public class BinaryFileRepository<T extends Entity> extends MemoryRepository<T> {
    String fileName;

    public BinaryFileRepository(String fileName) {
        this.fileName = fileName;
        loadFile();
    }

    @Override
    public void add(T entity) throws RepositoryException, IOException {
        super.add(entity);
        saveFile();
    }

    @Override
    public void delete(int id) throws RepositoryException, IOException {
        super.delete(id);
        saveFile();
    }

    @Override
    public void update(int id, T entity) throws RepositoryException, IOException {
        super.update(id, entity);
        saveFile();
    }

    protected void saveFile() {
        ObjectOutputStream out = null;
        try{
            out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(data);
            out.close();
        } catch (IOException e){
            System.out.println("Eroare la incarcarea fisierului:" + e.getMessage());
        }
    }

    protected void loadFile() {
        ObjectInputStream in = null;
        try{
            in = new ObjectInputStream(new FileInputStream(fileName));
            data = (ArrayList<T>) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException e){
            System.out.println("Eroare la citirea din fisier:" + e.getMessage());
        }
    }
}
