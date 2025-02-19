package domain;

import java.text.ParseException;

public interface EntityFactory<T extends Entity> {
    T createEntity(String line) throws ParseException;
}
