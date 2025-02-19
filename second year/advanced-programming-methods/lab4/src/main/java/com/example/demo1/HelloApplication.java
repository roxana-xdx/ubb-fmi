package main.java.com.example.demo1;

import com.example.demo1.domain.*;
import com.example.demo1.repository.*;
import com.example.demo1.service.ServicePacient;
import com.example.demo1.service.ServiceProgramare;
import com.example.demo1.util.Settings;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HelloApplication extends Application {

    public static MemoryRepository<Pacient> getRepository1() throws SQLException, RepositoryException {

        Settings settings = Settings.getInstance("src/settings.properties");

        if ("file".equals(settings.getRepoType())) {
            return new FileRepository<>(settings.getFilePacient(), new PacientFactory());
        } else if ("bin".equals(settings.getRepoType())) {
            return new BinaryFileRepository<>(settings.getFilePacient());
        } else if ("memory".equals(settings.getRepoType())) {
            return new MemoryRepository<>();
        } else if ("sql".equals(settings.getRepoType())) {
            return new SQLPacientRepository();
        }
        throw new IllegalArgumentException("Fisierul de setari e gresit.");
    }

    public static MemoryRepository<Programare> getRepository2() throws RepositoryException, SQLException {

        Settings settings = Settings.getInstance("src/settings.properties");

        if ("file".equals(settings.getRepoType())) {
            return new FileRepository<>(settings.getFileProgramare(), new ProgramareFactory());
        } else if ("bin".equals(settings.getRepoType())) {
            return new BinaryFileRepository<>(settings.getFileProgramare());
        } else if ("memory".equals(settings.getRepoType())) {
            return new MemoryRepository<>();
        } else if ("sql".equals(settings.getRepoType())) {
            return new SQLProgramareRepository();
        }
        throw new IllegalArgumentException("Fisierul de setari e gresit.");
    }

    @Override
    public void start(Stage stage) throws IOException, SQLException, RepositoryException, ParseException {

        MemoryRepository<Pacient> repo1 = getRepository1();
        ServicePacient service1 = new ServicePacient(repo1);
        MemoryRepository<Programare> repo2 = getRepository2();
        ServiceProgramare service2 = new ServiceProgramare(repo2);

        ListView<Pacient> pacientListView = new ListView<>();
        ObservableList<Pacient> pacientObservableList = FXCollections.observableArrayList(service1.getAll());
        pacientListView.setItems(pacientObservableList);

        ListView<Programare> programareListView = new ListView<>();
        ObservableList<Programare> programareObservableList = FXCollections.observableArrayList(service2.getAll());
        programareListView.setItems(programareObservableList);

        Button addButton = new Button("Add pacient");
        Button deleteButton = new Button("Delete pacient");
        Button updateButton = new Button("Update pacient");

        Button add2Button = new Button("Add programare");
        Button delete2Button = new Button("Delete programare");
        Button update2Button = new Button("Update programare");

        Button generateEntitiesButton = new Button("Generate entities");

        HBox buttonBox = new HBox();
        buttonBox.getChildren().add(addButton);
        buttonBox.getChildren().add(deleteButton);
        buttonBox.getChildren().add(updateButton);
        buttonBox.setSpacing(10);

        HBox buttonBox2 = new HBox();
        buttonBox2.getChildren().add(add2Button);
        buttonBox2.getChildren().add(delete2Button);
        buttonBox2.getChildren().add(update2Button);
        buttonBox2.setSpacing(10);

        Label idLabel = new Label("Id");
        Label numeLabel = new Label("Nume");
        Label prenumeLabel = new Label("Prenume");
        Label varstaLabel = new Label("Varsta");

        TextField idTextField = new TextField();
        TextField numeTextField = new TextField();
        TextField prenumeTextField = new TextField();
        TextField varstaTextField = new TextField();

        GridPane gridPane = new GridPane();
        gridPane.add(idLabel, 0, 0);
        gridPane.add(idTextField, 1, 0);
        gridPane.add(numeLabel, 0, 1);
        gridPane.add(numeTextField, 1, 1);
        gridPane.add(prenumeLabel, 0, 2);
        gridPane.add(prenumeTextField, 1, 2);
        gridPane.add(varstaLabel, 0, 3);
        gridPane.add(varstaTextField, 1, 3);

        Label id2label = new Label("Id");
        Label numar2label = new Label("Id Pacient");
        Label nume2label = new Label("Scop");
        Label datelabel = new Label("Data");

        TextField id2TextField = new TextField();
        TextField numar2TextField = new TextField();
        TextField nume2TextField = new TextField();
        TextField dateTextField = new TextField();

        GridPane gridPane2 = new GridPane();
        gridPane2.add(id2label, 0, 0);
        gridPane2.add(id2TextField, 1, 0);
        gridPane2.add(numar2label, 0, 1);
        gridPane2.add(numar2TextField, 1, 1);
        gridPane2.add(nume2label, 0, 2);
        gridPane2.add(nume2TextField, 1, 2);
        gridPane2.add(datelabel, 0, 3);
        gridPane2.add(dateTextField, 1, 3);

        TableView<Pacient> pacientTableView = new TableView<>();
        TableColumn<Pacient, Integer> idColumn = new TableColumn<>("Id");
        TableColumn<Pacient, String> numeColumn = new TableColumn<>("Nume");
        TableColumn<Pacient, String> prenumeColumn = new TableColumn<>("Prenume");
        TableColumn<Pacient, String> varstaColumn = new TableColumn<>("Varsta");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        numeColumn.setCellValueFactory(new PropertyValueFactory<>("nume"));
        prenumeColumn.setCellValueFactory(new PropertyValueFactory<>("prenume"));
        varstaColumn.setCellValueFactory(new PropertyValueFactory<>("varsta"));
        pacientTableView.getColumns().add(idColumn);
        pacientTableView.getColumns().add(numeColumn);
        pacientTableView.getColumns().add(prenumeColumn);
        pacientTableView.getColumns().add(varstaColumn);
        pacientTableView.setItems(pacientObservableList);

        TableView<Programare> programareTableView = new TableView<>();
        TableColumn<Programare, Integer> programareIdColumn = new TableColumn<>("Id");
        TableColumn<Programare, Integer> programareNumarColumn = new TableColumn<>("Id Pacient");
        TableColumn<Programare, String> programareNumeColumn = new TableColumn<>("Scop");
        TableColumn<Programare, String> programareListaColumn = new TableColumn<>("Data");
        programareIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        programareNumarColumn.setCellValueFactory(new PropertyValueFactory<>("id_pacient"));
        programareNumeColumn.setCellValueFactory(new PropertyValueFactory<>("scop"));
        programareListaColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
        programareTableView.getColumns().add(programareIdColumn);
        programareTableView.getColumns().add(programareNumarColumn);
        programareTableView.getColumns().add(programareNumeColumn);
        programareTableView.getColumns().add(programareListaColumn);
        programareTableView.setItems(programareObservableList);

        HBox mainBox = new HBox();
        VBox rightBox = new VBox();
        rightBox.getChildren().add(buttonBox);
        rightBox.getChildren().add(gridPane);
        rightBox.setSpacing(20);
        rightBox.getChildren().add(buttonBox2);
        rightBox.getChildren().add(gridPane2);
        rightBox.getChildren().add(generateEntitiesButton);

        mainBox.getChildren().add(pacientListView);
        mainBox.getChildren().add(rightBox);
        mainBox.getChildren().add(programareListView);
        mainBox.setSpacing(10);

        generateEntitiesButton.setOnMouseClicked(new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Settings settings = Settings.getInstance("src/main/settings.properties");
                if ("sql".equals(settings.getRepoType())) {
                    Random rand = new Random();
                    List<String> nume = new ArrayList<>();
                    nume.add("Pop");
                    nume.add("Popa");
                    nume.add("Popescu");
                    List<String> prenume = new ArrayList<>();
                    prenume.add("Ana");
                    prenume.add("Andreea");
                    prenume.add("Crina");
                    for (int i = 1; i <= 100; i++) {
                        int randomIndex = rand.nextInt(nume.size());
                        String randomNume = nume.get(randomIndex);
                        String randomPrenume = prenume.get(randomIndex);
                        int randomVarsta = rand.nextInt(20, 80);
                        try {
                            service1.add(i, randomNume,randomPrenume,randomVarsta);
                            pacientObservableList.setAll(service1.getAll());
                        } catch (Exception e) {
                            System.out.println("Nu s-a putut efectua adaugarea.");
                        }
                    }


                    for (int i = 1; i <= 100; i++) {
                        String x = "2025-" + (i % 12 + 1) + "-" + (i % 28 + 1);
                        Date newDate = Date.valueOf(x);
                        int randomIdPacient = rand.nextInt(1, 3);
                        List<String> scop = new ArrayList<>();
                        scop.add("control");
                        scop.add("chirurgie");
                        scop.add("consult");
                        int randomIndex = rand.nextInt(scop.size());
                        String randomScop = scop.get(randomIndex);
                        try {
                            service2.add(i, new Pacient(randomIdPacient,"","",0), newDate, randomScop);
                            programareObservableList.setAll(service2.getAll());
                        } catch (RepositoryException | IOException e) {
                            System.out.println("Nu s-a putut adauga entitatea.");
                        }
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Eroare");
                    alert.setContentText("Nu este folosita o baza de date.");
                    alert.show();
                }
            }
        });

        addButton.setOnMouseClicked(new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    int id = Integer.parseInt(idTextField.getText());
                    String nume = numeTextField.getText();
                    String prenume = prenumeTextField.getText();
                    int varsta = Integer.parseInt(varstaTextField.getText());
                    service1.add(id, nume, prenume, varsta);
                    pacientObservableList.setAll(service1.getAll());
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Eroare");
                    alert.setContentText(e.getMessage());
                    alert.show();
                }
            }
        });

        deleteButton.setOnMouseClicked(new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int id = Integer.parseInt(idTextField.getText());
                try {
                    service1.remove(id);
                    pacientObservableList.setAll(service1.getAll());
                } catch (RepositoryException | IOException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Eroare");
                    alert.setContentText(e.getMessage());
                    alert.show();
                }
            }
        });

        updateButton.setOnMouseClicked(new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int id = Integer.parseInt(idTextField.getText());
                try {
                    String nume = numeTextField.getText();
                    String prenume = prenumeTextField.getText();
                    int varsta = Integer.parseInt(varstaTextField.getText());
                    service1.update(id, nume, prenume, varsta);
                    pacientObservableList.setAll(service1.getAll());
                } catch (RepositoryException | IOException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Eroare");
                    alert.setContentText(e.getMessage());
                    alert.show();
                }
            }
        });

        pacientListView.setOnMouseClicked(new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Pacient selectedE1 = pacientListView.getSelectionModel().getSelectedItem();
                if (selectedE1 != null) {
                    idTextField.setText(String.valueOf(selectedE1.getId()));
                    numeTextField.setText(selectedE1.getNume());
                    prenumeTextField.setText(selectedE1.getPrenume());
                    varstaTextField.setText(String.valueOf(selectedE1.getVarsta()));
                } else {
                    System.out.println();
                }
            }
        });

        add2Button.setOnMouseClicked(new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    int id = Integer.parseInt(id2TextField.getText());
                    int id_pacient = Integer.parseInt(numar2TextField.getText());
                    String scop = nume2TextField.getText();
                    Date data = Date.valueOf(dateTextField.getText());
                    service2.add(id, new Pacient(id_pacient,"","",0), data, scop);
                    programareObservableList.setAll(service2.getAll());
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Eroare");
                    alert.setContentText(e.getMessage());
                    alert.show();
                }
            }
        });

        delete2Button.setOnMouseClicked(new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int id = Integer.parseInt(id2TextField.getText());
                try {
                    service2.remove(id);
                    programareObservableList.setAll(service2.getAll());
                } catch (RepositoryException | IOException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Eroare");
                    alert.setContentText(e.getMessage());
                    alert.show();
                }
            }
        });

        update2Button.setOnMouseClicked(new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int id = Integer.parseInt(id2TextField.getText());
                int id_pacient = Integer.parseInt(numar2TextField.getText());
                String scop = nume2TextField.getText();
                Date data = Date.valueOf(dateTextField.getText());

                service2.update(id, new Pacient(id_pacient,"","",0), data, scop);
                programareObservableList.setAll(service2.getAll());
            }
        });

        programareListView.setOnMouseClicked(new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Programare selectedE2 = programareListView.getSelectionModel().getSelectedItem();
                if (selectedE2 != null) {
                    id2TextField.setText(String.valueOf(selectedE2.getId()));
                    numar2TextField.setText(String.valueOf(selectedE2.getPacient().getNume()));
                    nume2TextField.setText(String.valueOf(selectedE2.getScop()));
                    dateTextField.setText(selectedE2.getData().toString());
                } else {
                    System.out.println("Nu este selectata nicio entitate.");
                }
            }
        });

        Scene scene = new Scene(mainBox, 1000, 600);
        stage.setTitle("Programari app");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();

//        try{
//            MemoryRepository<Pacient> repository1 = getRepository1();
//            ServicePacient service1 = new ServicePacient(repository1);
//            MemoryRepository<Programare> repository2 = getRepository2();
//            ServiceProgramare service2 = new ServiceProgramare(repository2);
//            ConsoleUI ui = new ConsoleUI(service1, service2);
//            ui.run();
//        } catch (RepositoryException e) {
//            System.out.println(e.getMessage());
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
    }
}