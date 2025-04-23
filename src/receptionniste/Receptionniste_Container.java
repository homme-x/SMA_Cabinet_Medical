package receptionniste;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Receptionniste_Container extends Application {
    public TableView<User> tableDemande;
    public TableView<User> tableEtat;
    public ObservableList<User> user1;
    public ObservableList<User> user2;
    public Button accepterBtn;
    private Receptionniste_Agent agent;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        startContainer();

        tableDemande = new TableView<>();
        tableEtat = new TableView<>();
        user1 = FXCollections.observableArrayList();
        user2 = FXCollections.observableArrayList();
        tableDemande.setItems(user1);
        tableEtat.setItems(user2);

        TableColumn<User, Integer> colId = new TableColumn<>("Numéro");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<User, String> colNom = new TableColumn<>("Nom");
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn<User, String> colSexe = new TableColumn<>("Sexe");
        colSexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));

        TableColumn<User, Integer> colAge = new TableColumn<>("Âge");
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<User, String> colStatut = new TableColumn<>("Statut");
        colStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));

        tableDemande.getColumns().addAll(colId, colNom, colSexe, colAge);
        tableEtat.getColumns().addAll(colId, colNom, colSexe, colAge, colStatut);

        accepterBtn = new Button("Acceptée");
        accepterBtn.setStyle("-fx-background-color: green; -fx-text-fill: white");
        accepterBtn.setOnAction(e -> {
            User selected = tableDemande.getSelectionModel().getSelectedItem();
            if (selected != null) {
                selected.setStatut("Acceptée");
                user2.add(selected);
                user1.remove(selected);
            }
        });

        VBox root = new VBox(10,
                new Label("Demande de Consultation"), tableDemande,
                new Label("État de la Consultation"), tableEtat,
                accepterBtn);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 700, 500);
        stage.setScene(scene);
        stage.setTitle("Interface Réceptionniste");
        stage.show();
    }

    private void startContainer() {
        Runtime rt = Runtime.instance();
        Profile profile = new ProfileImpl();
        AgentContainer container = rt.createAgentContainer(profile);
        try {
            AgentController ac = container.createNewAgent("Receptionniste_Agent", "receptionniste.Receptionniste_Agent", new Object[]{this});
            ac.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addUserToTable(User user) {
        System.out.println("Ajout à la table : " + user.getNom());
        user1.add(user);
    }

    public void setAgent(Receptionniste_Agent agent) {
        this.agent = agent;
    }
}