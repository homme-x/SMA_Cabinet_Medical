package patient;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class InscriptionInterface {
    public TextField nomField;
    public TextField prenomField;
    public TextField ageField;
    public RadioButton sexeF;
    public RadioButton sexeM;
    public TextField adresseField;
    public PasswordField telephoneField;
    public Button boutonEnregistrer;
    public Button boutonConsulter;
    public Label labelEtat;

    public void start(Stage stage) {
        stage.setTitle("Patient");

        Label titre = new Label("Mes Informations");
        titre.setStyle("-fx-font-size: 16px; -fx-font-style: italic;");
        HBox hTitre = new HBox(titre);
        hTitre.setAlignment(Pos.CENTER);

        nomField = new TextField();
        nomField.setPromptText("Entrez votre nom");

        prenomField = new TextField();
        prenomField.setPromptText("Entrez votre prénom");

        ageField = new TextField();
        ageField.setPromptText("Âge");

        Label sexeLabel = new Label("Sexe");
        sexeF = new RadioButton("F");
        sexeM = new RadioButton("M");
        ToggleGroup sexeGroup = new ToggleGroup();
        sexeF.setToggleGroup(sexeGroup);
        sexeM.setToggleGroup(sexeGroup);
        HBox sexeBox = new HBox(10, sexeF, sexeM);

        adresseField = new TextField();
        adresseField.setPromptText("Entrez votre adresse");

        telephoneField = new PasswordField();
        telephoneField.setPromptText("****");

        boutonEnregistrer = new Button("Enregistrer");
        boutonEnregistrer.setStyle("-fx-background-color: green; -fx-text-fill: white;");

        boutonConsulter = new Button("Consulter");
        boutonConsulter.setStyle("-fx-background-color: green; -fx-text-fill: white;");

        labelEtat = new Label("");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        grid.add(new Label("Nom(s)"), 0, 0);
        grid.add(nomField, 1, 0);

        grid.add(new Label("Prénom(s)"), 0, 1);
        grid.add(prenomField, 1, 1);

        grid.add(new Label("Âge"), 0, 2);
        grid.add(ageField, 1, 2);

        grid.add(sexeLabel, 0, 3);
        grid.add(sexeBox, 1, 3);

        grid.add(new Label("Adresse"), 0, 4);
        grid.add(adresseField, 1, 4);

        grid.add(new Label("Téléphone"), 0, 5);
        grid.add(telephoneField, 1, 5);

        grid.add(boutonEnregistrer, 0, 6);
        grid.add(boutonConsulter, 1, 6);
        grid.add(labelEtat, 1, 7);

        VBox root = new VBox(15, hTitre, grid);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 400, 420);
        stage.setScene(scene);
        stage.show();
    }
}