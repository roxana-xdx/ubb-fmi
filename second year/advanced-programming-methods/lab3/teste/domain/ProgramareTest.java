package domain;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class ProgramareTest {

    @Test
    void getPacient() throws ParseException {
        Pacient p = new Pacient(1,"Pop", "Ana", 20);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Programare pr = new Programare(1,p,dateFormat.parse("2024-11-05 02:00"),"control");
        assertEquals(p,pr.getPacient());
    }

    @Test
    void setPacient() throws ParseException {
        Pacient p = new Pacient(1,"Pop", "Ana", 20);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Programare pr = new Programare(1,p,dateFormat.parse("2024-11-05 02:00"),"control");
        assertEquals(p,pr.getPacient());
        Pacient p1 = new Pacient(2,"Ion", "Clara", 30);
        pr.setPacient(p1);
        assertEquals(p1,pr.getPacient());
    }

    @Test
    void getData() throws ParseException {
        Pacient p = new Pacient(1,"Pop", "Ana", 20);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Programare pr = new Programare(1,p,dateFormat.parse("2024-11-05 02:00"),"control");
        assertEquals(dateFormat.parse("2024-11-05 02:00") ,pr.getData());
    }

    @Test
    void setData() throws ParseException {
        Pacient p = new Pacient(1,"Pop", "Ana", 20);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Programare pr = new Programare(1,p,dateFormat.parse("2024-11-05 02:00"),"control");
        assertEquals(dateFormat.parse("2024-11-05 02:00") ,pr.getData());
        pr.setData(dateFormat.parse("2024-12-07 03:00"));
        assertEquals(dateFormat.parse("2024-12-07 03:00") ,pr.getData());
    }

    @Test
    void getScop() throws ParseException {
        Pacient p = new Pacient(1,"Pop", "Ana", 20);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Programare pr = new Programare(1,p,dateFormat.parse("2024-11-05 02:00"),"control");
        assertEquals("control",pr.getScop());
    }

    @Test
    void setScop() throws ParseException {
        Pacient p = new Pacient(1,"Pop", "Ana", 20);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Programare pr = new Programare(1,p,dateFormat.parse("2024-11-05 02:00"),"control");
        assertEquals("control",pr.getScop());
        pr.setScop("chirurgie");
        assertEquals("chirurgie",pr.getScop());
    }

}