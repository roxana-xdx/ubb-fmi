package domain;

import java.util.Date;

public class Programare extends Entity {
    private Pacient pacient;
    private Date data;
    private String scop;

    public Programare(int id, Pacient pacient, Date data, String scop) {
        this.id = id;
        this.pacient = pacient;
        this.data = data;
        this.scop = scop;
    }

    public Pacient getPacient() {
        return pacient;
    }

    public Date getData() {
        return data;
    }

    public String getScop() {
        return scop;
    }
}
