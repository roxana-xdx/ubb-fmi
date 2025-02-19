package main.java.com.example.demo1.repository;

import com.example.demo1.domain.Pacient;
import com.example.demo1.domain.Programare;
import org.sqlite.SQLiteDataSource;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SQLProgramareRepository extends MemoryRepository<Programare> {
    private Connection connection;
    private String DB_URL = "jdbc:sqlite:programare_db";
    public SQLProgramareRepository() throws RepositoryException, SQLException {
        openConnection();
        createTable();
        loadData();
    }
    private void loadData() throws SQLException {
        Collection<Programare> entities = getAll();
        data.addAll(entities);
    }

    private void openConnection() throws RepositoryException {
        SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl(DB_URL);
        try{
            if (connection == null || connection.isClosed()){
                connection = ds.getConnection();
            }
        }catch (SQLException e){
            throw new RepositoryException(e.getMessage());
        }
    }

    public void closeConnection() {
        if (connection != null)
            try{
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    }

    private void createTable(){
        String createSQL = "CREATE TABLE IF NOT EXISTS programare(" +
                "id int, id_pacient int, data date, scop varchar(100)," +
                "PRIMARY KEY (id))";
        try{
            Statement createStatement = connection.createStatement();
            createStatement.execute(createSQL);;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void add(Programare programare) throws RepositoryException, IOException {
        super.add(programare);
        String insertSQL = "INSERT INTO programare VALUES (?,?,?,?);";
        try(PreparedStatement insertStatement = connection.prepareStatement(insertSQL)){
            insertStatement.setInt(1,programare.getId());
            insertStatement.setInt(2,programare.getPacient().getId());
            insertStatement.setString(3,programare.getData().toString());
            insertStatement.setString(4,programare.getScop());
            int numberOfRowsAffected = insertStatement.executeUpdate();
            System.out.println("Numarul de linii afectate in adaugare: " + numberOfRowsAffected);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) throws RepositoryException, IOException {
        super.delete(id);
        String deleteSQL = "DELETE FROM programare WHERE id = (?);";
        try(PreparedStatement deleteStatement = connection.prepareStatement(deleteSQL)){
            deleteStatement.setInt(1,id);
            int numberOfRowsAffected = deleteStatement.executeUpdate();
            System.out.println("Numarul de linii afectate in stergere: " + numberOfRowsAffected);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void update(int id, Programare programare) throws RepositoryException, IOException {
        super.update(id,programare);
        String updateSQL = "UPDATE programare SET id_pacient = (?), data = (?), scop = (?) WHERE id = (?);";
        try(PreparedStatement updateStatement = connection.prepareStatement(updateSQL)){
            updateStatement.setInt(1,programare.getPacient().getId());
            updateStatement.setString(2,programare.getData().toString());
            updateStatement.setString(3,programare.getScop());
            updateStatement.setInt(4,id);
            int numberOfRowsAffected = updateStatement.executeUpdate();
            System.out.println("Numarul de linii afectate in update: " + numberOfRowsAffected);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Collection<Programare> getAll() {
        super.getAll();
        List<Programare> resultList = new ArrayList<>();
        String selectSQL = "SELECT * FROM programare;";
        try (PreparedStatement getAllStatement = connection.prepareStatement(selectSQL)) {
            ResultSet result = getAllStatement.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                int id_pacient = result.getInt("id_pacient");
                //Date data = result.getDate("data");
                String scop = result.getString("scop");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                resultList.add(new Programare(id,new Pacient(id_pacient,"", "",0),dateFormat.parse("2023-11-10 10:00"),scop));
            }
        } catch (SQLException | ParseException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    public Programare find(int id) {
        super.find(id);
        String findSQL = "SELECT * FROM programare WHERE id = (?);";
        try (PreparedStatement findStatement = connection.prepareStatement(findSQL)) {
            findStatement.setInt(1, id);
            ResultSet result = findStatement.executeQuery();
            if (result.next()) {
                int id_pacient = result.getInt("id_pacient");
                Date data = result.getDate("data");
                String scop = result.getString("scop");
                return new Programare(id, new Pacient(id_pacient,"","",0), data, scop);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
