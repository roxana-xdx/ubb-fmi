package util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Settings {
    private String repoType;
    private String fileProgramare;
    private String filePacient;

    public Settings(){
    }

    public String getRepoType() {
        return repoType;
    }

    public String getFilePacient() {
        return filePacient;
    }

    public String getFileProgramare() {
        return fileProgramare;
    }

    private static Settings instance;

    public static Settings getInstance(String fileName){
        if(instance == null){
            Properties settings = new Properties();
            try{
                settings.load(new FileReader(fileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            instance = new Settings();
            instance.repoType = settings.getProperty("Repository");
            instance.filePacient = settings.getProperty("PacientFile");
            instance.fileProgramare = settings.getProperty("ProgramareFile");
        }
        return instance;
    }
}
