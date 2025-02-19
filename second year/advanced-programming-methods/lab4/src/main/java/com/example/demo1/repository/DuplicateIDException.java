package main.java.com.example.demo1.repository;

public class DuplicateIDException extends RepositoryException {
    public DuplicateIDException(String message) {
        super(message);
    }
}
