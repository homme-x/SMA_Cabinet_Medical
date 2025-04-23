package medecin;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MedecinInterface extends Application {
    public TableView<User> table;
    public ObservableList<User> users = FXCollections.observableArrayList();
    public ObservableList<String> items = FXCollections.observableArrayList();
    public ListView<String> listView = new ListView<>(items);
    public TextArea textAreaSaisie = new TextArea();
    public Button Benvoyer = new Button("Envoyer");

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Interface Médecin");

        // TableView
        table = new TableView<>();
        table.setItems(users);

        TableColumn<User, Integer> colNumero = new TableColumn<>("Numéro");
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));

        TableColumn<User, String> colNom = new TableColumn<>("Nom");
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn<User, Integer> colAge = new TableColumn<>("Âge");
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<User, String> colSexe = new TableColumn<>("Sexe");
        colSexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));

        table.getColumns().addAll(colNumero, colNom, colAge, colSexe);

        // Onglets
        TabPane tabPane = new TabPane();
        Tab tabChat = new Tab("Conversation");
        Tab tabPatients = new Tab("Patients");

        // Tab Chat
        VBox vBoxChat = new VBox(10);
        textAreaSaisie.setPromptText("Saisir votre message...");
        textAreaSaisie.setPrefRowCount(3);
        vBoxChat.getChildren().addAll(listView, textAreaSaisie, Benvoyer);
        tabChat.setContent(vBoxChat);

        // Tab Patients
        VBox vBoxPatients = new VBox(10);
        vBoxPatients.getChildren().addAll(table);
        tabPatients.setContent(vBoxPatients);

        tabPane.getTabs().addAll(tabChat, tabPatients);

        VBox root = new VBox(10);
        root.getChildren().add(tabPane);

        Scene scene = new Scene(root, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
