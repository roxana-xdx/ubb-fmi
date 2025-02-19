package main.java.com.example.demo1.repository;

public class ObjectNotFoundException extends RepositoryException {
    public ObjectNotFoundException(String message) {
        super(message);
    }
}
