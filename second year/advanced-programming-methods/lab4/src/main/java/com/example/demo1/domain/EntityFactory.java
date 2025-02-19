package main.java.com.example.demo1.domain;

import java.text.ParseException;

public interface EntityFactory<T extends Entity> {
    T createEntity(String line) throws ParseException;
}
