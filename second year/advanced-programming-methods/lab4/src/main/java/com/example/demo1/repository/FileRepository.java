package main.java.com.example.demo1.repository;

import com.example.demo1.domain.Entity;
import com.example.demo1.domain.EntityFactory;

import java.io.*;
import java.text.ParseException;

public class FileRepository<T extends Entity> extends MemoryRepository<T> {
    String fileName;
    EntityFactory<T> factory;

    public FileRepository(String fileName, EntityFactory<T> factory) {
        this.fileName = fileName;
        this.factory = factory;
        try{
            loadFile();
        } catch(RepositoryException | FileNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    public void add(T entity) throws RepositoryException, IOException {
        super.add(entity);
        saveFile();
    }

    public void delete(int id) throws RepositoryException, IOException {
        super.delete(id);
        saveFile();
    }

    public void update(int id, T entity) throws RepositoryException, IOException {
        super.update(id, entity);
        saveFile();
    }

    protected void loadFile() throws RepositoryException, FileNotFoundException {
        try(BufferedReader br = new BufferedReader(new FileReader(this.fileName))) {
            String line = br.readLine();
            while (line != null) {
                data.add(factory.createEntity(line));
                line = br.readLine();
            }
        } catch(FileNotFoundException | ParseException e){
            System.out.println("Fisierul de date este gol.");
        } catch(IOException e){
            throw new RepositoryException("Eroare la citirea fisierului.");
        }
    }

    protected void saveFile() throws RepositoryException {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(this.fileName))){
            for (var entity : data){
                String asString = entity.toString();
                bw.write(asString);
                bw.newLine();
            }
        } catch(IOException e){
            throw new RepositoryException("Eroare la scrierea fisierului.");
        }
    }
}
