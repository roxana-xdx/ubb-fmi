package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SettingsTest {
    private static final Settings settings = Settings.getInstance("test.properties");

    @Test
    void getRepoType() {
        assertEquals("file",settings.getRepoType());
    }

    @Test
    void getFilePacient() {
        assertEquals("pacienti.txt",settings.getFilePacient());
    }


    @Test
    void getFileProgramare() {
        assertEquals("programari.txt",settings.getFileProgramare());
    }

    @Test
    void getInstance() {
        assertEquals(settings,Settings.getInstance("test.properties"));
    }

}