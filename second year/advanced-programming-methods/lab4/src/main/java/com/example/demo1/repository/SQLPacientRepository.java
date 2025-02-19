package main.java.com.example.demo1.repository;

import com.example.demo1.domain.Pacient;
import org.sqlite.SQLiteDataSource;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SQLPacientRepository extends MemoryRepository<Pacient> {
    private Connection connection;
    private String DB_URL = "jdbc:sqlite:pacient_db";
    public SQLPacientRepository() throws SQLException, RepositoryException {
        openConnection();
        createTable();
        loadData();
    }
    private void loadData() throws SQLException {
        Collection<Pacient> entities = getAll();
        data.addAll(entities);
    }

    private void openConnection() throws RepositoryException {
        SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl(DB_URL);
        try {
            if (connection == null || connection.isClosed()){
                connection = ds.getConnection();
            }
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    public void closeConnection(){
        if (connection != null)
            try{
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    }

    private void createTable(){
        String createSQL = "CREATE TABLE IF NOT EXISTS pacient (" +
                "id int, nume varchar(100), prenume varchar(100), varsta int," +
                "PRIMARY KEY (id))";
        try{
            Statement createStatement = connection.createStatement();
            createStatement.execute(createSQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(Pacient pacient) throws RepositoryException, IOException {
        super.add(pacient);
        String insertSQL = "INSERT INTO pacient VALUES (?,?,?,?);";
        try(PreparedStatement insertStatement = connection.prepareStatement(insertSQL)){
            insertStatement.setInt(1,pacient.getId());
            insertStatement.setString(2, pacient.getNume());
            insertStatement.setString(3, pacient.getPrenume());
            insertStatement.setInt(4, pacient.getVarsta());
            int numberOfRowsAffected = insertStatement.executeUpdate();
            System.out.println("Numarul de linii afectate in adaugare: " + numberOfRowsAffected);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) throws RepositoryException, IOException {
        super.delete(id);
        String deleteSQL = "DELETE FROM pacient WHERE id = (?);";
        try(PreparedStatement deleteStatement = connection.prepareStatement(deleteSQL)){
            deleteStatement.setInt(1,id);
            int numberOfRowsAffected = deleteStatement.executeUpdate();
            System.out.println("Numarul de linii afectate in stergere: " + numberOfRowsAffected);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void update(int id, Pacient pacient) throws RepositoryException, IOException {
        super.update(id,pacient);
        String updateSQL = "UPDATE pacient SET nume = (?), prenume = (?), varsta = (?) WHERE id = (?);";
        try(PreparedStatement updateStatement = connection.prepareStatement(updateSQL)){
            updateStatement.setString(1,pacient.getNume());
            updateStatement.setString(2,pacient.getPrenume());
            updateStatement.setInt(3,pacient.getVarsta());
            updateStatement.setInt(4,id);
            int numberOfRowsAffected = updateStatement.executeUpdate();
            System.out.println("Numarul de linii afectate in update: " + numberOfRowsAffected);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Collection<Pacient> getAll() {
        super.getAll();
        List<Pacient> resultList = new ArrayList<>();
        String selectSQL = "SELECT * FROM pacient;";
        try(PreparedStatement getAllStatement = connection.prepareStatement(selectSQL)){
            ResultSet result = getAllStatement.executeQuery();
            while (result.next()){
                int id = result.getInt("id");
                String nume = result.getString("nume");
                String prenume = result.getString("prenume");
                int varsta = result.getInt("varsta");
                Pacient t = new Pacient(id,nume,prenume,varsta);
                resultList.add(t);
            }
            return resultList;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Pacient find(int id) {
        super.find(id);
        String findSQL = "SELECT * FROM pacient WHERE id = (?);";
        try(PreparedStatement findStatement = connection.prepareStatement(findSQL)){
            findStatement.setInt(1,id);
            ResultSet result = findStatement.executeQuery();
            if (result.next()){
                String nume = result.getString("nume");
                String prenume = result.getString("prenume");
                int varsta = result.getInt("varsta");
                return new Pacient(id,nume,prenume,varsta);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return null;
    }
}
